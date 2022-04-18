package com.ahmdalii.medicinereminder.login.presenter;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public interface LoginPresenterInterface {

    void signInWithEmailAndPassword(String email, String password);
    GoogleSignInClient getGoogleSignInClient(Activity activity);
    void signInWithGoogle(String idToken);
}
