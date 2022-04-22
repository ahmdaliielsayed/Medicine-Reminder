package com.ahmdalii.medicinereminder.login.repository;

import android.app.Activity;
import android.content.Context;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.db.room.user.LocalSourceUser;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkLoginDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class LoginRepo implements LoginRepoInterface {

    RemoteSource remoteSource;
    LocalSourceUser localSourceUser;
    Context context;
    private static LoginRepo loginRepo = null;

    private LoginRepo(RemoteSource remoteSource, LocalSourceUser localSourceUser, Context context) {
        this.remoteSource = remoteSource;
        this.localSourceUser = localSourceUser;
        this.context = context;
    }

    public static LoginRepo getInstance(RemoteSource remoteSource, LocalSourceUser localSourceUser, Context context) {
        if (loginRepo == null) {
            loginRepo = new LoginRepo(remoteSource, localSourceUser, context);
        }

        return loginRepo;
    }

    @Override
    public void signInWithEmailAndPassword(NetworkLoginDelegate networkDelegate, String email, String password) {
        remoteSource.enqueueCall(networkDelegate, email, password);
    }

    @Override
    public void setUserLogin(boolean isLogin) {
        SharedPrefManager.getInstance(context, Constants.USERS_FILE).setValue(Constants.USER_LOGIN_KEY, isLogin);
    }

    @Override
    public GoogleSignInClient getGoogleSignInClient(Activity activity) {
        return remoteSource.getGoogleSignInClient(activity);
    }

    @Override
    public void signInWithGoogle(NetworkLoginDelegate networkDelegate, String idToken) {
        remoteSource.signInWithGoogle(networkDelegate, idToken);
    }

    @Override
    public void insertUserToRoom(User user) {
        localSourceUser.insertUser(user);
    }

    @Override
    public void setUserId(String userId) {
        SharedPrefManager.getInstance(context, Constants.USERS_FILE).setValue(Constants.USER_ID_KEY, userId);
    }
}
