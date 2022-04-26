package com.ahmdalii.medicinereminder.home.view;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;
import java.util.Map;

public interface HomeFragmentInterface {

    void setDosesToAdapter(Map<Medicine, MedicineDose> allDosesWithMedicineName);
    void onError(String error);
}
