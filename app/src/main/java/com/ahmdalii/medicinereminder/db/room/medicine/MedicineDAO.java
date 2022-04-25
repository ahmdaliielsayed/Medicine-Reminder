package com.ahmdalii.medicinereminder.db.room.medicine;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;
import com.ahmdalii.medicinereminder.model.Medicine;

import java.util.List;

@Dao
public interface MedicineDAO {

    @Query("SELECT * FROM medicine")
    LiveData<List<Medicine>> getAllMedicines();

    @Query("SELECT * FROM medicine WHERE medicine.id=:medID")
    LiveData<Medicine> getMedicine(String medID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedicine(Medicine medicine);

    @Delete
    void deleteMedicine(Medicine medicine);

    @Query("SELECT name, strength, remainingMedAmount, form FROM medicine where isActivated = 1")
    LiveData<List<MedsPojo>> getActiveMeds();

    @Query("SELECT name, strength, remainingMedAmount, form FROM medicine where isActivated = 0")
    LiveData<List<MedsPojo>> getInactiveMeds();

}
