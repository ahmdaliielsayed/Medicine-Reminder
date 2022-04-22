package com.ahmdalii.medicinereminder.login.repository;

import android.app.Activity;

import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkLoginDelegate;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public interface LoginRepoInterface {

    void signInWithEmailAndPassword(NetworkLoginDelegate networkDelegate, String email, String password);
    void setUserLogin(boolean isLogin);
    GoogleSignInClient getGoogleSignInClient(Activity activity);
    void signInWithGoogle(NetworkLoginDelegate networkDelegate, String idToken);
    void insertUserToRoom(User user);
    void setUserId(String userId);
}
