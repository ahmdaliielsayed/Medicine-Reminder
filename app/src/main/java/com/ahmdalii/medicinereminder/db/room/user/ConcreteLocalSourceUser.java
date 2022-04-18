package com.ahmdalii.medicinereminder.db.room.user;

import android.content.Context;

import com.ahmdalii.medicinereminder.db.room.AppDataBase;
import com.ahmdalii.medicinereminder.model.User;

public class ConcreteLocalSourceUser implements LocalSourceUser {

    private final UserDAO dao;
    private static ConcreteLocalSourceUser localSourceUser;

    private ConcreteLocalSourceUser(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context.getApplicationContext());
        dao = dataBase.userDAO();
    }

    public static ConcreteLocalSourceUser getInstance(Context context) {
        if (localSourceUser == null) {
            localSourceUser = new ConcreteLocalSourceUser(context);
        }

        return localSourceUser;
    }

    @Override
    public void insertMedicine(User user) {
        new Thread(() -> dao.insertUser(user)).start();
    }
}
