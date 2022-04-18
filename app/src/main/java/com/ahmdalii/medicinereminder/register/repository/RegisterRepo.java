package com.ahmdalii.medicinereminder.register.repository;

import android.content.Context;
import android.net.Uri;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.db.room.user.LocalSourceUser;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkRegisterDelegate;
import com.ahmdalii.medicinereminder.network.NetworkImageProfileDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;

public class RegisterRepo implements RegisterRepoInterface {

    RemoteSource remoteSource;
    LocalSourceUser localSourceUser;
    Context context;
    private static RegisterRepo registerRepo = null;

    private RegisterRepo(RemoteSource remoteSource, LocalSourceUser localSourceUser, Context context) {
        this.remoteSource = remoteSource;
        this.localSourceUser = localSourceUser;
        this.context = context;
    }

    public static RegisterRepo getInstance(RemoteSource remoteSource, LocalSourceUser localSourceUser, Context context) {
        if (registerRepo == null) {
            registerRepo = new RegisterRepo(remoteSource, localSourceUser, context);
        }

        return registerRepo;
    }

    @Override
    public void uploadProfileImage(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage) {
        remoteSource.enqueueCall(networkImageProfileDelegate, uriProfileImage);
    }

    @Override
    public void createUserOnFirebase(NetworkRegisterDelegate networkRegisterDelegate, String name, String email, String password, String profileImageURI) {
        remoteSource.enqueueCall(networkRegisterDelegate, name, email, password, profileImageURI);
    }

    @Override
    public void insertUserToRoom(User user) {
        localSourceUser.insertUser(user);
    }

    @Override
    public void setUserLogin(boolean isLogin) {
        SharedPrefManager.getInstance(context, Constants.USERS_FILE).setValue(Constants.USER_LOGIN_KEY, isLogin);
    }
}
