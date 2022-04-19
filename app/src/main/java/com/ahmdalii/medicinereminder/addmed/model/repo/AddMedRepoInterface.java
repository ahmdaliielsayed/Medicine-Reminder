package com.ahmdalii.medicinereminder.addmed.model.repo;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.AddMedicineNetworkDelegate;

import java.util.ArrayList;

public interface AddMedRepoInterface {
    void insertMedicineInFirebase(AddMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses);
    void insertMedicineInRoom(Medicine medicine, ArrayList<MedicineDose>doses);
}
