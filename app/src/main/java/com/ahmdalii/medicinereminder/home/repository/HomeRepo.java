package com.ahmdalii.medicinereminder.home.repository;

import android.content.Context;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.db.room.user.LocalSourceUser;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;

public class HomeRepo implements HomeRepoInterface {

    RemoteSource remoteSource;
    LocalSourceUser localSourceUser;
    private static HomeRepo homeRepo = null;

    private HomeRepo(LocalSourceUser localSourceUser, RemoteSource remoteSource) {
        this.localSourceUser = localSourceUser;
        this.remoteSource = remoteSource;
    }

    public static HomeRepo getInstance(LocalSourceUser localSourceUser, RemoteSource remoteSource) {
        if (homeRepo == null) {
            homeRepo = new HomeRepo(localSourceUser, remoteSource);
        }

        return homeRepo;
    }

    @Override
    public User getUserFromRoom(Context context) {
        String userId = SharedPrefManager.getInstance(context, Constants.USERS_FILE).getStringValue(Constants.USER_ID_KEY);

        return localSourceUser.getUser(userId);
    }

    @Override
    public void signOut(Context context) {
        remoteSource.signOut();
        SharedPrefManager.getInstance(context, Constants.USERS_FILE).setValue(Constants.USER_LOGIN_KEY, false);
    }
}
