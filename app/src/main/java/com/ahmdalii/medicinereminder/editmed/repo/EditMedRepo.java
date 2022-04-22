package com.ahmdalii.medicinereminder.editmed.repo;

import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.network.RemoteSource;

public class EditMedRepo implements EditMedRepoInterface {

    private static EditMedRepo instance = null;
    RemoteSource remoteSource;
    LocalSourceMedicine localSourceMedicine;
    LocalSourceMedicineDose localSourceMedicineDose;

    private EditMedRepo(RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        this.remoteSource = remoteSource;
        this.localSourceMedicine = localSourceMedicine;
        this.localSourceMedicineDose = localSourceMedicineDose;
    }

    public static EditMedRepo getInstance(RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        if(instance == null) {
            instance = new EditMedRepo(remoteSource, localSourceMedicine, localSourceMedicineDose);
        }
        return instance;
    }
}
