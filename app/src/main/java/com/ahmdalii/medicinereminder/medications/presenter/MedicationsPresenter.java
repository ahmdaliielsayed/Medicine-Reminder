package com.ahmdalii.medicinereminder.medications.presenter;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.medications.repository.MedicationsRepository;
import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;
import com.ahmdalii.medicinereminder.medications.view.MedicationsFragment;
import com.ahmdalii.medicinereminder.medications.view.MedicationsViewInterface;

import java.util.List;

public class MedicationsPresenter implements MedicationsPresenterInterface{

    MedicationsViewInterface view;
    MedicationsRepository repo;

    public MedicationsPresenter(MedicationsViewInterface view, MedicationsRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public LiveData<List<MedsPojo>> getActiveMeds() {
        return repo.getActiveMeds();
    }

    @Override
    public LiveData<List<MedsPojo>> getInactiveMeds() {
        return repo.getInactiveMeds();
    }
}
