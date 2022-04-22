package com.ahmdalii.medicinereminder.editmed.presenter;

import com.ahmdalii.medicinereminder.db.room.medicine.ConcreteLocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.editmed.repo.EditMedRepo;
import com.ahmdalii.medicinereminder.editmed.repo.EditMedRepoInterface;
import com.ahmdalii.medicinereminder.editmed.view.EditMedActivityInterface;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.util.ArrayList;

public class EditMedPresenter implements EditMedPresenterInterface {

    EditMedActivityInterface editMedView;
    EditMedRepoInterface repo;

    Medicine medicine;
    ArrayList<MedicineDose> doses;

    public EditMedPresenter(EditMedActivityInterface editMedView) {
        this.editMedView = editMedView;
        repo = EditMedRepo.getInstance(
                FirebaseClient.getInstance(),
                ConcreteLocalSourceMedicine.getInstance(editMedView.getViewContext()),
                ConcreteLocalSourceMedicineDose.getInstance(editMedView.getViewContext())
        );
    }

    public Medicine getMedicine() {
        return medicine;
    }

    @Override
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public ArrayList<MedicineDose> getDoses() {
        return doses;
    }

    @Override
    public void setDoses(ArrayList<MedicineDose> doses) {
        this.doses = doses;
    }
}
