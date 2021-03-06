package com.ahmdalii.medicinereminder.network;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface NetworkHomeDelegate {

    void onResponse(Map<Medicine, MedicineDose> listMap);
    void onFailure(String error);
}
