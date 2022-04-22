package com.ahmdalii.medicinereminder.addmed.presenter;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface AddMedicineNetworkDelegate {
    void onSuccess(Medicine medicine, ArrayList<MedicineDose>doses);
    void onFailure();
}
