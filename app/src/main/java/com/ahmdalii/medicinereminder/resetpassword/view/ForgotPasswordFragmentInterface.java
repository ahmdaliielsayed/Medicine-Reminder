package com.ahmdalii.medicinereminder.resetpassword.view;

public interface ForgotPasswordFragmentInterface {

    void linkSentSuccessfully();
    void navigateToLoginScreen();
    void onError(String error);
    void hideProgressbar();
}
