package com.ahmdalii.medicinereminder.db.room.medicine;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.model.Medicine;

import java.util.List;

public interface LocalSourceMedicine {

    void insertMedicine(Medicine medicine);
    void deleteMedicine(Medicine medicine);
    LiveData<List<Medicine>> getAllStoredMedicines();
}
