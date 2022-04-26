package com.ahmdalii.medicinereminder.db.room.medicinedose;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.db.room.AppDataBase;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;
import java.util.Map;

public class ConcreteLocalSourceMedicineDose implements LocalSourceMedicineDose {

    private static ConcreteLocalSourceMedicineDose instance = null;
    private final MedicineDoseDAO dao;
    private LiveData<List<MedicineDose>> storedMedicineDosesLiveData;

    private ConcreteLocalSourceMedicineDose(Context context) {
        AppDataBase database = AppDataBase.getInstance(context.getApplicationContext());
        dao = database.timeSchedulerDAO();
    }

    public static ConcreteLocalSourceMedicineDose getInstance(Context context) {
        if(instance == null) {
            instance = new ConcreteLocalSourceMedicineDose(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public LiveData<List<MedicineDose>> getAllMedicineDoses(String medID) {
        storedMedicineDosesLiveData = dao.getAllMedicineDoses(medID);
        return storedMedicineDosesLiveData;
    }

    @Override
    public void insertMedicineDose(MedicineDose medicineDose) {
        new Thread() {
            @Override
            public void run() {
                dao.insertMedicineDose(medicineDose);
            }
        }.start();
    }

    @Override
    public void deleteMedicineDose(MedicineDose medicineDose) {
        new Thread() {
            @Override
            public void run() {
                dao.deleteMedicineDose(medicineDose);
            }
        }.start();
    }

    @Override
    public void updateMedicineDose(MedicineDose medicineDose) {
//        new Thread(() -> dao.updateMedicineDose(medicineDose)).start();
        dao.updateMedicineDose(medicineDose);
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        dao.updateMedicine(medicine);
    }

    @Override
    public MedicineDose getNextMedicineDose() {
        return dao.getNextMedicineDose(DoseStatus.FUTURE.getStatus());
    }

    @Override
    public Medicine getNextMedicine(String med_id) {
        return dao.getNextMedicine(med_id);
    }

    @Override
    public Map<Medicine, List<MedicineDose>> getAllDosesWithMedicineName(String uid) {
        return dao.getAllDosesWithMedicineName(uid);
    }

    @Override
    public LiveData<List<MedicineDose>> getAllUnSyncMedicineDoses() {
        return dao.getAllUnSyncMedicineDoses();
    }

    @Override
    public void updateMedicineDosesInRoom(MedicineDose updatedList) {
        new Thread(() -> {
            dao.updateMedicineDose(updatedList);
        }).start();
    }
}
