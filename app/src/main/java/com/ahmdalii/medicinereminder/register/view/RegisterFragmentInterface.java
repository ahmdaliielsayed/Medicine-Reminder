package com.ahmdalii.medicinereminder.register.view;

public interface RegisterFragmentInterface {

    void setProfileImageURI(String uri);
    void onImgUploadError(String error);
    void hideImgProgressbar();

    void navigateToHomeScreen();
    void onError(String error);
    void hideProgressbar();
}
