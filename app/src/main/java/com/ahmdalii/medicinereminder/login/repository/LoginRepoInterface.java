package com.ahmdalii.medicinereminder.login.repository;

import com.ahmdalii.medicinereminder.network.NetworkDelegate;

public interface LoginRepoInterface {

    void signInWithEmailAndPassword(NetworkDelegate networkDelegate, String email, String password);
    void setUserLogin(boolean isLogin);
}
