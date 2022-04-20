package com.ahmdalii.medicinereminder.network;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface AddMedicineNetworkDelegate {
    void onSuccess(Medicine medicine, ArrayList<MedicineDose>doses);
    void onFailure();
}
