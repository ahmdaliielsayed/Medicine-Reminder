package com.ahmdalii.medicinereminder.home.presenter;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.NetworkHomeDelegate;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HomeFragmentPresenterInterface {

    void getAllDosesWithMedicineName(Date currentDate);
    void getAllDosesWithMedicineNameForUser(Date currentDate, String uid, NetworkHomeDelegate networkHomeDelegate);
}
