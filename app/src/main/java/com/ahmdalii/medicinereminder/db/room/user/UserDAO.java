package com.ahmdalii.medicinereminder.db.room.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.ahmdalii.medicinereminder.model.User;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);
}
