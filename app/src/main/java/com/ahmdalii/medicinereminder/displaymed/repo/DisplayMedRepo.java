package com.ahmdalii.medicinereminder.displaymed.repo;

import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.util.ArrayList;

public class DisplayMedRepo implements DisplayMedRepoInterface {

    private static DisplayMedRepo instance = null;
    RemoteSource remoteSource;
    LocalSourceMedicine localSourceMedicine;
    LocalSourceMedicineDose localSourceMedicineDose;

    Medicine medicine;
    ArrayList<MedicineDose> doses;

    private DisplayMedRepo(RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        this.remoteSource = remoteSource;
        this.localSourceMedicine = localSourceMedicine;
        this.localSourceMedicineDose = localSourceMedicineDose;
        doses = new ArrayList<>();
    }

    public static DisplayMedRepo getInstance(RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        if(instance == null) {
            instance = new DisplayMedRepo(remoteSource, localSourceMedicine, localSourceMedicineDose);
        }
        return instance;
    }

    public Medicine getMedicine() {
        return medicine;
    }

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

    public void getDosesFromFirebase(DisplayMedNetworkDelegate networkDelegate, String medID) {
        remoteSource.enqueueCall(networkDelegate, medID);
    }

    @Override
    public MedicineDose getUpcomingDose() {
        for(MedicineDose dose: doses) {
            if(dose.getStatus().equals(DoseStatus.FUTURE)) {
                return dose;
            }
        }
        return null;
    }
}
