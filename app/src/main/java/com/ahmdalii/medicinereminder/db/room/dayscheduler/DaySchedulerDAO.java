package com.ahmdalii.medicinereminder.db.room.dayscheduler;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.model.DayScheduler;
import com.ahmdalii.medicinereminder.model.Medicine;

import java.util.List;

@Dao
public interface DaySchedulerDAO {
    @Query("SELECT * FROM day_scheduler")
    LiveData<List<DayScheduler>> getAllDaySchedulers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDayScheduler(DayScheduler dayScheduler);

    @Delete
    void deleteDayScheduler(DayScheduler dayScheduler);
}
