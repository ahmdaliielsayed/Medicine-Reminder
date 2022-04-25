package com.ahmdalii.medicinereminder.home.repository;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HomeFragmentRepoInterface {

    Map<Medicine, MedicineDose> getAllDosesWithMedicineName(Date currentDate);
}
