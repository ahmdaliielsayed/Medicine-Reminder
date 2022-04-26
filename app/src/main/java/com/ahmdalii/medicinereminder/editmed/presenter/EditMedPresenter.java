package com.ahmdalii.medicinereminder.editmed.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.WorkRequestManager;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.db.room.medicine.ConcreteLocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.editmed.repo.EditMedRepo;
import com.ahmdalii.medicinereminder.editmed.repo.EditMedRepoInterface;
import com.ahmdalii.medicinereminder.editmed.view.EditMedActivityInterface;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EditMedPresenter implements EditMedPresenterInterface, AddMedicineNetworkDelegate, DisplayMedNetworkDelegate {

    EditMedActivityInterface editMedView;
    EditMedRepoInterface repo;

    public EditMedPresenter(EditMedActivityInterface editMedView) {
        this.editMedView = editMedView;
        repo = EditMedRepo.getInstance(
                FirebaseClient.getInstance(),
                ConcreteLocalSourceMedicine.getInstance(editMedView.getViewContext()),
                ConcreteLocalSourceMedicineDose.getInstance(editMedView.getViewContext())
        );
    }

    public Medicine getMedicine() {
        return repo.getMedicine();
    }

    @Override
    public void setMedicine(Medicine medicine) {
        repo.setMedicine(medicine, this);
    }

    public ArrayList<MedicineDose> getDoses() {
        return repo.getDoses();
    }

    @Override
    public void setDoses(ArrayList<MedicineDose> doses) {
        repo.setDoses(doses);
    }

    @Override
    public void addDose(MedicineDose dose) {
        repo.addDose(dose);
    }

    @Override
    public void submitUpdates() {
        if(NetworkConnection.isNetworkAvailable(editMedView.getViewContext())) {
            repo.updateMedInFirebase(this);
        }
        else {
            repo.updateMedInRoom();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSuccess(Medicine medicine, ArrayList<MedicineDose> doses) {
        repo.setMedicine(medicine, this);
        repo.setDoses(doses);
        repo.updateMedInRoom();
        editMedView.showSuccessToast();
        WorkRequestManager.removeWork(getMedicine().getId(), editMedView.getViewContext());


        MedicineDose upcomingDose = null;
        for(MedicineDose dose: getDoses()) {
            if(dose.getStatus().equals(DoseStatus.FUTURE.getStatus())) {
                upcomingDose = dose;
                break;
            }
        }

        WorkRequestManager.createWorkRequest(
                upcomingDose,
                getMedicine(),
                editMedView.getViewContext()
        );

        editMedView.closeView();

    }

    @Override
    public void onSuccess(ArrayList<MedicineDose> doses) {
        repo.setDoses(doses);
        editMedView.hideProgressDialog();
        editMedView.setUI();
    }

    @Override
    public void onFailure() {
        editMedView.showFailureToast();
    }

    @Override
    public void onSuccessLocal() {

    }
}
