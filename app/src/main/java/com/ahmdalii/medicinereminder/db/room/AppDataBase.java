package com.ahmdalii.medicinereminder.db.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ahmdalii.medicinereminder.db.room.user.UserDAO;
import com.ahmdalii.medicinereminder.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    // singleton
    private static AppDataBase instance = null;
    public abstract UserDAO userDAO();
//    public abstract MedicineDAO medicineDAO();

    // one thread at a time to access this method
    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "medical_reminder_db")
                    .build();
        }

        return instance;
    }
}
