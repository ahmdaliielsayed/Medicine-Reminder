package com.ahmdalii.medicinereminder.medications.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.db.room.AppDataBase;
import com.ahmdalii.medicinereminder.db.room.medicine.MedicineDAO;

import java.util.List;

public class MedicationsLocalSource implements MedicationsLocalSourceInterface{

    private static MedicationsLocalSource localSource = null;

    Context context;
    LiveData<List<MedsPojo>> activeMeds;
    LiveData<List<MedsPojo>> inActiveMeds;
    MedicineDAO medDAO;


    private MedicationsLocalSource(Context context) {
        this.context = context;
        AppDataBase dataBase = AppDataBase.getInstance(context);
        medDAO = dataBase.medicineDAO();
        activeMeds = medDAO.getActiveMeds();
        inActiveMeds = medDAO.getInactiveMeds();
    }

    public static MedicationsLocalSource getInstance(Context context){
        if(localSource == null)
            localSource = new MedicationsLocalSource(context);
        return localSource;
    }

    public LiveData<List<MedsPojo>> getLocalActiveMeds(){
        return activeMeds;
    }

    public LiveData<List<MedsPojo>> getLocalInactiveMeds(){
        return inActiveMeds;
    }
}
