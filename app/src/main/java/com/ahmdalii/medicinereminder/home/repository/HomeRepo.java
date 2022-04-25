package com.ahmdalii.medicinereminder.home.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.db.room.user.LocalSourceUser;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkDelegate;
import com.ahmdalii.medicinereminder.network.NetworkSyncDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.util.List;

public class HomeRepo implements HomeRepoInterface {

    RemoteSource remoteSource;
    LocalSourceUser localSourceUser;
    LocalSourceMedicine localSourceMedicine;
    LocalSourceMedicineDose localSourceMedicineDose;
    private static HomeRepo homeRepo = null;

    private HomeRepo(LocalSourceUser localSourceUser, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose, RemoteSource remoteSource) {
        this.localSourceUser = localSourceUser;
        this.remoteSource = remoteSource;
        this.localSourceMedicine = localSourceMedicine;
        this.localSourceMedicineDose = localSourceMedicineDose;
    }

    public static HomeRepo getInstance(LocalSourceUser localSourceUser, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose, RemoteSource remoteSource) {
        if (homeRepo == null) {
            homeRepo = new HomeRepo(localSourceUser, localSourceMedicine, localSourceMedicineDose, remoteSource);
        }

        return homeRepo;
    }

    @Override
    public User getUserFromRoom(Context context) {
        String userId = SharedPrefManager.getInstance(context, Constants.USERS_FILE).getStringValue(Constants.USER_ID_KEY);

        return localSourceUser.getUser(userId);
    }

    @Override
    public void signOut(Context context) {
        remoteSource.signOut();
        SharedPrefManager.getInstance(context, Constants.USERS_FILE).setValue(Constants.USER_LOGIN_KEY, false);
    }

    @Override
    public LiveData<List<Medicine>> getAllUnSyncMedicines() {
        return localSourceMedicine.getAllUnSyncMedicines();
    }

    @Override
    public LiveData<List<MedicineDose>> getAllUnSyncMedicineDoses() {
        return localSourceMedicineDose.getAllUnSyncMedicineDoses();
    }

    @Override
    public void syncMedicineListToFirebase(NetworkSyncDelegate networkDelegate, List<Medicine> unSyncedMedicines) {
        remoteSource.syncMedicineListToFirebase(networkDelegate, unSyncedMedicines);
    }

    @Override
    public void syncMedicineDosesListToFirebase(NetworkSyncDelegate networkDelegate, List<MedicineDose> unSyncedMedicineDoses) {
        remoteSource.syncMedicineDosesListToFirebase(networkDelegate, unSyncedMedicineDoses);
    }

    @Override
    public void updateMedicinesInRoom(Medicine updatedList) {
        localSourceMedicine.updateMedicinesInRoom(updatedList);
    }

    @Override
    public void updateMedicineDosesInRoom(MedicineDose updatedList) {
        localSourceMedicineDose.updateMedicineDosesInRoom(updatedList);
    }
}
