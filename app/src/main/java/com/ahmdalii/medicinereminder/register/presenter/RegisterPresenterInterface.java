package com.ahmdalii.medicinereminder.register.presenter;

import android.net.Uri;

public interface RegisterPresenterInterface {

    void uploadProfileImage(Uri uriProfileImage);
    void createUserOnFirebase(String name, String email, String password, String profileImageURI);
}
