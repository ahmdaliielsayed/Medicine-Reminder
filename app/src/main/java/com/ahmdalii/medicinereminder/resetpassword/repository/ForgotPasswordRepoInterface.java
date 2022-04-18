package com.ahmdalii.medicinereminder.resetpassword.repository;

import com.ahmdalii.medicinereminder.network.NetworkDelegate;

public interface ForgotPasswordRepoInterface {

    void sendPasswordResetEmail(NetworkDelegate networkDelegate, String email);
}
