package com.ahmdalii.medicinereminder.editmed.repo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicine.MedicineDAO;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class EditMedRepo implements EditMedRepoInterface {

    private static EditMedRepo instance = null;
    RemoteSource remoteSource;
    LocalSourceMedicine localSourceMedicine;
    LocalSourceMedicineDose localSourceMedicineDose;

    Medicine medicine;
    ArrayList<MedicineDose> doses;

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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine, DisplayMedNetworkDelegate networkDelegate) {
        this.medicine = medicine;
        remoteSource.enqueueCall(networkDelegate, medicine.getId());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<MedicineDose> getDoses() {
        doses.sort(new Comparator<MedicineDose>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int compare(MedicineDose m1, MedicineDose m2) {
                if(LocalDateTime.parse(m1.getTime()).isAfter(LocalDateTime.parse(m2.getTime()))) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        return doses;
    }

    public void setDoses(ArrayList<MedicineDose> doses) {
        this.doses = doses;
    }

    @Override
    public void addDose(MedicineDose dose) {
        doses.add(dose);
    }

    @Override
    public void updateMedInFirebase(AddMedicineNetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate, medicine, doses);
    }

    @Override
    public void updateMedInRoom() {
        localSourceMedicine.insertMedicine(medicine);
        for(MedicineDose dose: doses)
        localSourceMedicineDose.insertMedicineDose(dose);
    }
}
