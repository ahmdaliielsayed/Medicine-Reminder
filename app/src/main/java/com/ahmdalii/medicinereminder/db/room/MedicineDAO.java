package com.ahmdalii.medicinereminder.db.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineDAO {

//    @Query("SELECT * FROM medicines")
//    LiveData<List<Medicine>> getAllMedicines();

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insertMedicine(Medicine medicine);

//    @Delete
//    void deleteMedicine(Medicine medicine);
}