package com.ahmdalii.medicinereminder.resetpassword.repository;

import com.ahmdalii.medicinereminder.network.NetworkDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;

public class ForgotPasswordRepo implements ForgotPasswordRepoInterface {

    RemoteSource remoteSource;
    private static ForgotPasswordRepo forgotPasswordRepo = null;

    private ForgotPasswordRepo(RemoteSource remoteSource) {
        this.remoteSource = remoteSource;
    }

    public static ForgotPasswordRepo getInstance(RemoteSource remoteSource) {
        if (forgotPasswordRepo == null) {
            forgotPasswordRepo = new ForgotPasswordRepo(remoteSource);
        }

        return forgotPasswordRepo;
    }

    @Override
    public void sendPasswordResetEmail(NetworkDelegate networkDelegate, String email) {
        remoteSource.enqueueCall(networkDelegate, email);
    }
}
