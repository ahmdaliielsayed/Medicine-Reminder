package com.ahmdalii.medicinereminder.register.repository;

import android.net.Uri;

import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkRegisterDelegate;
import com.ahmdalii.medicinereminder.network.NetworkImageProfileDelegate;

public interface RegisterRepoInterface {

    void uploadProfileImage(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage);
    void createUserOnFirebase(NetworkRegisterDelegate networkRegisterDelegate, String name, String email, String password, String profileImageURI);

    void insertUserToRoom(User user);

    void setUserLogin(boolean isLogin);
    void setUserId(String userId);
}
