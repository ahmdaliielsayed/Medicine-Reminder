package com.ahmdalii.medicinereminder.network;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;

public interface NetworkSyncDelegate {

    void onResponse(Medicine syncedMedicinesList, boolean isMed);
    void onResponse(MedicineDose syncedMedicineDosesList);
    void onFailure(String error);
}
