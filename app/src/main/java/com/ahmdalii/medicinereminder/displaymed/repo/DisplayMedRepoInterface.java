package com.ahmdalii.medicinereminder.displaymed.repo;

import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface DisplayMedRepoInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine);
    ArrayList<MedicineDose> getDoses();
    void setDoses(ArrayList<MedicineDose> doses);
    void getDosesFromFirebase(DisplayMedNetworkDelegate networkDelegate, String medID);

    MedicineDose getUpcomingDose();
}
