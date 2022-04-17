package com.ahmdalii.medicinereminder.db.room.dayscheduler;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.model.DayScheduler;

import java.util.List;

public interface LocalSourceDayScheduler {
    LiveData<List<DayScheduler>> getAllDaySchedulers();
    void insertDayScheduler(DayScheduler dayScheduler);
    void deleteDayScheduler(DayScheduler dayScheduler);
}
