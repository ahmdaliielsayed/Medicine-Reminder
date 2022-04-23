package com.ahmdalii.medicinereminder.editmed.repo;

import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface EditMedRepoInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine, DisplayMedNetworkDelegate networkDelegate);
    ArrayList<MedicineDose> getDoses();
    void setDoses(ArrayList<MedicineDose> doses);
    void addDose(MedicineDose dose);
    void updateMedInFirebase(AddMedicineNetworkDelegate networkDelegate);
    void updateMedInRoom();
}
