package com.ahmdalii.medicinereminder.home.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.NetworkHomeDelegate;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HomeFragmentRepoInterface {

    Map<Medicine, MedicineDose> getAllDosesWithMedicineName(Date currentDate, Context context);
    void getAllDosesWithMedicineNameForUser(Date currentDate, String uid, NetworkHomeDelegate networkHomeDelegate);
}
