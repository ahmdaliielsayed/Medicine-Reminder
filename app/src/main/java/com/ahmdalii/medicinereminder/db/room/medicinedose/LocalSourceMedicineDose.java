package com.ahmdalii.medicinereminder.db.room.medicinedose;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;

public interface LocalSourceMedicineDose {
    LiveData<List<MedicineDose>> getAllMedicineDoses();
    void insertMedicineDose(MedicineDose medicineDose);
    void deleteMedicineDose(MedicineDose medicineDose);
}
