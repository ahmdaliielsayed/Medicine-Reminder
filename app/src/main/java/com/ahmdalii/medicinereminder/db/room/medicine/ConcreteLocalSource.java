package com.ahmdalii.medicinereminder.db.room.medicine;

import android.content.Context;

public class ConcreteLocalSource implements LocalSource {

    private MedicineDAO dao;
    private static ConcreteLocalSource localSource = null;
//    private LiveData<List<Medicine>> storedMedicinesLiveData;

    private ConcreteLocalSource(Context context) {
//        AppDataBase dataBase = AppDataBase.getInstance(context.getApplicationContext());
//        dao = dataBase.medicineDAO();
//        storedMoviesLiveData = dao.getAllMedicines();
    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (localSource == null) {
            localSource = new ConcreteLocalSource(context);
        }

        return localSource;
    }

//    @Override
//    public void insertMedicine(Medicine medicine) {
//        new Thread(() -> {
//            dao.insertMedicine(medicine);
//        }).start();
//    }

//    @Override
//    public void deleteMedicine(Medicine medicine) {
//        new Thread(() -> {
//            dao.deleteMedicine(medicine);
//        }).start();
//    }

//    @Override
//    public LiveData<List<Medicine>> getAllStoredMedicines() {
//        return storedMedicinesLiveData;
//    }
}
