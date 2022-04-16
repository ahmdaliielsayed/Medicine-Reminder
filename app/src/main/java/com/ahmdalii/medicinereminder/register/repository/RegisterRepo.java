package com.ahmdalii.medicinereminder.register.repository;

import android.content.Context;
import android.net.Uri;

import com.ahmdalii.medicinereminder.db.room.LocalSource;
import com.ahmdalii.medicinereminder.network.RemoteSource;

public class RegisterRepo implements RegisterRepoInterface {

    RemoteSource remoteSource;
    Context context;
    private static RegisterRepo registerRepo = null;

    private RegisterRepo(RemoteSource remoteSource, Context context) {
        this.remoteSource = remoteSource;
        this.context = context;
    }

    public static RegisterRepo getInstance(RemoteSource remoteSource, Context context) {
        if (registerRepo == null) {
            registerRepo = new RegisterRepo(remoteSource, context);
        }

        return registerRepo;
    }

    @Override
    public void uploadProfileImage(Uri uriProfileImage) {

    }
}
