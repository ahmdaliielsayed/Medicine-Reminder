package com.ahmdalii.medicinereminder.db.room.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.model.User;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE userId LIKE :userId " + "LIMIT 1")
    User getUser(String userId);
}
