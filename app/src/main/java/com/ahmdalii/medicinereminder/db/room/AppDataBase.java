package com.ahmdalii.medicinereminder.db.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ahmdalii.medicinereminder.db.room.dayscheduler.DaySchedulerDAO;
import com.ahmdalii.medicinereminder.db.room.medicine.MedicineDAO;
import com.ahmdalii.medicinereminder.db.room.timescheduler.TimeSchedulerDAO;
import com.ahmdalii.medicinereminder.db.room.user.UserDAO;
import com.ahmdalii.medicinereminder.model.DayScheduler;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.TimeScheduler;
import com.ahmdalii.medicinereminder.model.User;

@Database(entities = {User.class, Medicine.class, DayScheduler.class, TimeScheduler.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    // singleton
    private static AppDataBase instance = null;
    public abstract UserDAO userDAO();
    public abstract MedicineDAO medicineDAO();
    public abstract DaySchedulerDAO daySchedulerDAO();
    public abstract TimeSchedulerDAO timeSchedulerDAO();

    // one thread at a time to access this method
    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "medical_reminder_db")
                    .build();
        }

        return instance;
    }
}
