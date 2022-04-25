package com.ahmdalii.medicinereminder.db.room.medicinedose;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;
import java.util.Map;

@Dao
public interface MedicineDoseDAO {
    @Query("SELECT * FROM medicine_dose WHERE medID=:medID")
    LiveData<List<MedicineDose>> getAllMedicineDoses(String medID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedicineDose(MedicineDose medicineDose);

    @Delete
    void deleteMedicineDose(MedicineDose medicineDose);

    @Update
    void updateMedicineDose(MedicineDose medicineDose);

    @Update
    void updateMedicine(Medicine medicine);

    @Query("SELECT * FROM medicine_dose WHERE status = :futureStatus ORDER BY time LIMIT 1")
    MedicineDose getNextMedicineDose(String futureStatus);

    @Query("SELECT * FROM medicine WHERE id = :med_id LIMIT 1")
    Medicine getNextMedicine(String med_id);


    @Query("SELECT DISTINCT * FROM medicine INNER JOIN medicine_dose ON medicine.id = medicine_dose.medID AND medicine.isActivated = 1")
    Map<Medicine, List<MedicineDose>> getAllDosesWithMedicineName();

    @Query("SELECT * FROM medicine_dose WHERE isSync = 0")
    LiveData<List<MedicineDose>> getAllUnSyncMedicineDoses();
}
