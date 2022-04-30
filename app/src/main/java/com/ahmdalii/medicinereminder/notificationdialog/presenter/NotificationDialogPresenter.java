package com.ahmdalii.medicinereminder.notificationdialog.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.ahmdalii.medicinereminder.WorkRequestManager;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.db.room.medicine.ConcreteLocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.notificationdialog.repo.NotificationDialogRepo;
import com.ahmdalii.medicinereminder.notificationdialog.repo.NotificationDialogRepoInterface;
import com.ahmdalii.medicinereminder.notificationdialog.view.NotificationDialogInterface;

import java.util.ArrayList;

public class NotificationDialogPresenter implements NotificationDialogPresenterInterface, DisplayMedNetworkDelegate, AddMedicineNetworkDelegate {

    NotificationDialogInterface notificationDialogView;
    NotificationDialogRepoInterface repo;

    public NotificationDialogPresenter(NotificationDialogInterface notificationDialogView, Medicine medicine, MedicineDose dose) {
        this.notificationDialogView = notificationDialogView;
        repo = NotificationDialogRepo.getInstance(
                FirebaseClient.getInstance(),
                ConcreteLocalSourceMedicine.getInstance(notificationDialogView.getViewContext()),
                ConcreteLocalSourceMedicineDose.getInstance(notificationDialogView.getViewContext())
        );
        repo.setMedicine(medicine);
        repo.setDose(dose);

        repo.getStoredDoses(notificationDialogView.getViewContext(), this, notificationDialogView.getLifecycleOwner());

    }

    @Override
    public Medicine getMedicine() {
        return repo.getMedicine();
    }

    @Override
    public void setMedicine(Medicine medicine) {
        repo.setMedicine(medicine);
    }

    @Override
    public MedicineDose getDose() {
        return repo.getDose();
    }

    @Override
    public void setDose(MedicineDose dose) {
        repo.setDose(dose);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void deleteDose() {

        Log.i("TAG", "deleteDose: ");
        for(int i = 0; i < repo.getDoses().size(); i++) {
            if(repo.getDoses().get(i).getId().equals(repo.getDose().getId())) {
                Log.i("TAG", "deleteDose: if ");
                repo.getDoses().remove(repo.getDose());
            }
        }

        if(isThisTheUpcomingDose()) {
            MedicineDose upcomingDose = getUpcomingDose();
            WorkRequestManager.removeWork(repo.getMedicine().getId(), notificationDialogView.getViewContext());
            WorkRequestManager.createWorkRequest(upcomingDose, repo.getMedicine(), notificationDialogView.getViewContext());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void takeDose() {
        boolean flag = false;
        if(isThisTheUpcomingDose()) {
            Log.i("TAG", "takeDose: ");

            WorkRequestManager.removeWork(repo.getDose().getId(), notificationDialogView.getViewContext());
            flag = true;
        }
        repo.getMedicine().setRemainingMedAmount(repo.getMedicine().getRemainingMedAmount() - repo.getDose().getAmount());
        repo.getDose().setStatus(DoseStatus.TAKEN.getStatus());

        if(repo.getMedicine().getRemainingMedAmount() <= repo.getMedicine().getReminderMedAmount()) {
            notificationDialogView.showRefillReminderDialog();
        }

        for(int i = 0; i < repo.getDoses().size(); i++) {
            if(repo.getDoses().get(i).getId().equals(repo.getDose().getId())) {
                repo.getDoses().set(i, repo.getDose());
            }
        }

        repo.updateMedicineAndDose(notificationDialogView.getViewContext(), this);

        if(flag) {
            MedicineDose upcomingDose = getUpcomingDose();
            //WorkRequestManager.createWorkRequest(upcomingDose, repo.getMedicine(), notificationDialogView.getViewContext());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void skipDose() {

        boolean flag = false;
        if(isThisTheUpcomingDose()) {
            flag = true;
            WorkRequestManager.removeWork(repo.getDose().getId(), notificationDialogView.getViewContext());
        }
        repo.getMedicine().setRemainingMedAmount(repo.getMedicine().getRemainingMedAmount() - repo.getDose().getAmount());
        repo.getDose().setStatus(DoseStatus.SKIPPED.getStatus());

        for(int i = 0; i < repo.getDoses().size(); i++) {
            if(repo.getDoses().get(i).getId().equals(repo.getDose().getId())) {
                repo.getDoses().set(i, repo.getDose());
            }
        }

        repo.updateMedicineAndDose(notificationDialogView.getViewContext(), this);

        if(flag) {
            MedicineDose upcomingDose = getUpcomingDose();
            //WorkRequestManager.createWorkRequest(upcomingDose, repo.getMedicine(), notificationDialogView.getViewContext());
        }

    }

    @Override
    public void onSuccess(ArrayList<MedicineDose> doses) {
        repo.setDoses(doses);
    }

    @Override
    public void onSuccess(Medicine medicine, ArrayList<MedicineDose> doses) {
        repo.updateMedicineAndDoseLocal(medicine, doses);
    }

    @Override
    public void onFailure() {
        notificationDialogView.showToast("Operation failed");
    }

    @Override
    public void onSuccessLocal() {

    }

    private boolean isThisTheUpcomingDose() {
        MedicineDose upcomingDose = null;
        for(MedicineDose dose: repo.getDoses()) {
            if(dose.getStatus().equals(DoseStatus.FUTURE.getStatus())) {
                upcomingDose = dose;
                break;
            }
        }
        if(upcomingDose == null) {
            return false;
        }

        Log.i("TAG", "isThisTheUpcomingDose: upcoming: " + upcomingDose);
        Log.i("TAG", "isThisTheUpcomingDose: current: " + repo.getDose());
        return upcomingDose.getId().equals(repo.getDose().getId());
    }

    private MedicineDose getUpcomingDose() {
        MedicineDose upcomingDose = null;
        for(MedicineDose dose: repo.getDoses()) {
            if(dose.getStatus().equals(DoseStatus.FUTURE.getStatus())) {
                upcomingDose = dose;
                break;
            }
        }
        return upcomingDose;
    }
}
