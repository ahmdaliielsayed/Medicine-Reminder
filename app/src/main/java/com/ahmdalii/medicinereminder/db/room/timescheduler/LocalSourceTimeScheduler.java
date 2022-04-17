package com.ahmdalii.medicinereminder.db.room.timescheduler;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.model.TimeScheduler;

import java.util.List;

public interface LocalSourceTimeScheduler {
    LiveData<List<TimeScheduler>> getAllTimeSchedulers();
    void insertTimeScheduler(TimeScheduler timeScheduler);
    void deleteTimeScheduler(TimeScheduler timeScheduler);
}
