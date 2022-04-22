package com.ahmdalii.medicinereminder.home.repository;

import android.content.Context;

import com.ahmdalii.medicinereminder.model.User;

public interface HomeRepoInterface {

    User getUserFromRoom(Context context);
    void signOut(Context context);
}
