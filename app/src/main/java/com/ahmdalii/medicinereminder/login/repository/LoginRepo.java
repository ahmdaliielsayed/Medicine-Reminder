package com.ahmdalii.medicinereminder.login.repository;

import android.content.Context;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;
import com.ahmdalii.medicinereminder.network.NetworkDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;

public class LoginRepo implements LoginRepoInterface {

    RemoteSource remoteSource;
    Context context;
    private static LoginRepo loginRepo = null;

    private LoginRepo(RemoteSource remoteSource, Context context) {
        this.remoteSource = remoteSource;
        this.context = context;
    }

    public static LoginRepo getInstance(RemoteSource remoteSource, Context context) {
        if (loginRepo == null) {
            loginRepo = new LoginRepo(remoteSource, context);
        }

        return loginRepo;
    }

    @Override
    public void signInWithEmailAndPassword(NetworkDelegate networkDelegate, String email, String password) {
        remoteSource.enqueueCall(networkDelegate, email, password);
    }

    @Override
    public void setUserLogin(boolean isLogin) {
        SharedPrefManager.getInstance(context, Constants.USERS_FILE).setValue(Constants.USER_LOGIN_KEY, isLogin);
    }
}
