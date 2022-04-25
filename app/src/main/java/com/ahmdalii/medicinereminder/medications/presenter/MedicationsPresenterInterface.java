package com.ahmdalii.medicinereminder.medications.presenter;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;

import java.util.List;

public interface MedicationsPresenterInterface {
    public LiveData<List<MedsPojo>> getActiveMeds();

    public LiveData<List<MedsPojo>> getInactiveMeds();
}
