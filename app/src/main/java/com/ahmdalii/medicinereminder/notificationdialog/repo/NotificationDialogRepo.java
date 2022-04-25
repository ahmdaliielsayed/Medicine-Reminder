package com.ahmdalii.medicinereminder.notificationdialog.repo;

import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.util.ArrayList;

public class NotificationDialogRepo implements NotificationDialogRepoInterface {

    Medicine medicine;
    MedicineDose dose;

    private static NotificationDialogRepo instance = null;

    RemoteSource remoteSource;
    LocalSourceMedicine localSourceMedicine;
    LocalSourceMedicineDose localSourceMedicineDose;

    private NotificationDialogRepo(RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        this.remoteSource = remoteSource;
        this.localSourceMedicine = localSourceMedicine;
        this.localSourceMedicineDose = localSourceMedicineDose;
    }

    public static NotificationDialogRepo getInstance(RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        if(instance == null) {
            instance = new NotificationDialogRepo(remoteSource, localSourceMedicine, localSourceMedicineDose);
        }
        return instance;
    }


    public Medicine getMedicine() {
        return medicine;
    }

    @Override
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public MedicineDose getDose() {
        return dose;
    }

    @Override
    public void setDose(MedicineDose dose) {
        this.dose = dose;
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
