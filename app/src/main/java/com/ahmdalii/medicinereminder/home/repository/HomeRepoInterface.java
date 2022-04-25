package com.ahmdalii.medicinereminder.home.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkDelegate;
import com.ahmdalii.medicinereminder.network.NetworkSyncDelegate;

import java.util.List;

public interface HomeRepoInterface {

    User getUserFromRoom(Context context);
    void signOut(Context context);
    LiveData<List<Medicine>> getAllUnSyncMedicines();
    LiveData<List<MedicineDose>> getAllUnSyncMedicineDoses();
    void syncMedicineListToFirebase(NetworkSyncDelegate networkDelegate, List<Medicine> unSyncedMedicines);
    void syncMedicineDosesListToFirebase(NetworkSyncDelegate networkDelegate, List<MedicineDose> unSyncedMedicineDoses);

    void updateMedicinesInRoom(Medicine updatedList);
    void updateMedicineDosesInRoom(MedicineDose updatedList);
}
