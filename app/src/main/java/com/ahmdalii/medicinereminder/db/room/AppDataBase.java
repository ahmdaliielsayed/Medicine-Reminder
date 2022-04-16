package com.ahmdalii.medicinereminder.db.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {Medicine.class}, version = 1)
//public abstract class AppDataBase extends RoomDatabase {
//
//    // singleton
//    private static AppDataBase instance = null;
//    public abstract MedicineDAO medicineDAO();
//
//    // one thread at a time to access this method
//    public static synchronized AppDataBase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "medicines")
//                    .build();
//        }
//
//        return instance;
//    }
//}
