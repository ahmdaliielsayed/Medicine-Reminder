package com.ahmdalii.medicinereminder.displaymed.presenter;

import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface DisplayMedNetworkDelegate {
    void onSuccess(ArrayList<MedicineDose> doses);
    void onFailure();
    void onSuccessLocal();
}
