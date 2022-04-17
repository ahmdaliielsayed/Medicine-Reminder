package com.ahmdalii.medicinereminder.db.room.medicine;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.db.room.AppDataBase;
import com.ahmdalii.medicinereminder.model.Medicine;

import java.util.List;

public class ConcreteLocalSourceMedicine implements LocalSourceMedicine {

    private MedicineDAO dao;
    private static ConcreteLocalSourceMedicine localSource = null;
    private LiveData<List<Medicine>> storedMedicinesLiveData;

    private ConcreteLocalSourceMedicine(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context.getApplicationContext());
        dao = dataBase.medicineDAO();
        storedMedicinesLiveData = dao.getAllMedicines();
    }

    public static ConcreteLocalSourceMedicine getInstance(Context context) {
        if (localSource == null) {
            localSource = new ConcreteLocalSourceMedicine(context);
        }

        return localSource;
    }

    @Override
    public void insertMedicine(Medicine medicine) {
        new Thread(() -> {
            dao.insertMedicine(medicine);
        }).start();
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        new Thread(() -> {
            dao.deleteMedicine(medicine);
        }).start();
    }

    @Override
    public LiveData<List<Medicine>> getAllStoredMedicines() {
        return storedMedicinesLiveData;
    }
}
