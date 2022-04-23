package com.ahmdalii.medicinereminder.db.room.medicinedose;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;

@Dao
public interface MedicineDoseDAO {
    @Query("SELECT * FROM medicine_dose")
    LiveData<List<MedicineDose>> getAllMedicineDoses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedicineDose(MedicineDose medicineDose);

    @Delete
    void deleteMedicineDose(MedicineDose medicineDose);
}
