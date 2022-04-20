package com.ahmdalii.medicinereminder.db.room.medicinedose;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.db.room.AppDataBase;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;

public class ConcreteLocalSourceMedicineDose implements LocalSourceMedicineDose {

    private static ConcreteLocalSourceMedicineDose instance = null;
    private MedicineDoseDAO dao;
    private LiveData<List<MedicineDose>> storedMedicineDosesLiveData;

    private ConcreteLocalSourceMedicineDose(Context context) {
        AppDataBase database = AppDataBase.getInstance(context.getApplicationContext());
        dao = database.timeSchedulerDAO();
        storedMedicineDosesLiveData = dao.getAllMedicineDoses();
    }

    public static ConcreteLocalSourceMedicineDose getInstance(Context context) {
        if(instance == null) {
            instance = new ConcreteLocalSourceMedicineDose(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public LiveData<List<MedicineDose>> getAllMedicineDoses() {
        return null;
    }

    @Override
    public void insertMedicineDose(MedicineDose medicineDose) {

    }

    @Override
    public void deleteMedicineDose(MedicineDose medicineDose) {

    }
}
