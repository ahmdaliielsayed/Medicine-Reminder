package com.ahmdalii.medicinereminder.notificationdialog.presenter;

import com.ahmdalii.medicinereminder.db.room.medicine.ConcreteLocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.notificationdialog.repo.NotificationDialogRepo;
import com.ahmdalii.medicinereminder.notificationdialog.repo.NotificationDialogRepoInterface;
import com.ahmdalii.medicinereminder.notificationdialog.view.NotificationDialogInterface;

public class NotificationDialogPresenter implements NotificationDialogPresenterInterface {

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

    @Override
    public void deleteDose() {

    }

    @Override
    public void takeDose() {

    }

    @Override
    public void skipDose() {

    }
}
