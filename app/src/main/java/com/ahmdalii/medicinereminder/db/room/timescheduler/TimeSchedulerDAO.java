package com.ahmdalii.medicinereminder.db.room.timescheduler;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.model.DayScheduler;
import com.ahmdalii.medicinereminder.model.TimeScheduler;

import java.util.List;

@Dao
public interface TimeSchedulerDAO {
    @Query("SELECT * FROM day_scheduler")
    LiveData<List<TimeScheduler>> getAllTimeSchedulers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTimeScheduler(TimeScheduler timeScheduler);

    @Delete
    void deleteTimeScheduler(TimeScheduler timeScheduler);
}
