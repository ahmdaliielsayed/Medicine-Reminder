package com.ahmdalii.medicinereminder.home.view;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.User;

import java.util.List;

public interface HomeActivityInterface {

    void displayUserInformation(User user);
    void navigateToLoginScreen();
    void syncMedicines(LiveData<List<Medicine>> unSyncMedicines);
    void syncMedicineDoses(LiveData<List<MedicineDose>> unSyncMedicineDoses);
    void showSyncError(String error);
}
