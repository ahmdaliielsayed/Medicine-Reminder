package com.ahmdalii.medicinereminder.medications.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface MedicationsLocalSourceInterface {
    public LiveData<List<MedsPojo>> getLocalActiveMeds();

    public LiveData<List<MedsPojo>> getLocalInactiveMeds();
}
