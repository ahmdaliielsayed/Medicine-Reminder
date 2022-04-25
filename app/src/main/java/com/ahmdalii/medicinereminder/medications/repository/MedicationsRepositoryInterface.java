package com.ahmdalii.medicinereminder.medications.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface MedicationsRepositoryInterface {
    public LiveData<List<MedsPojo>> getActiveMeds();

    public LiveData<List<MedsPojo>> getInactiveMeds();
}
