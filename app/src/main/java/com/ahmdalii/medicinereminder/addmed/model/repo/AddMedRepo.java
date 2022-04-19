package com.ahmdalii.medicinereminder.addmed.model.repo;

import android.content.Context;
import android.util.Log;

import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.util.ArrayList;

public class AddMedRepo implements AddMedRepoInterface {

    private static AddMedRepo instance = null;
    Context context;
    RemoteSource remoteSource;
    LocalSourceMedicine localSourceMedicine;
    LocalSourceMedicineDose localSourceMedicineDose;

    public AddMedRepo(Context context, RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.localSourceMedicine = localSourceMedicine;
        this.localSourceMedicineDose = localSourceMedicineDose;
    }

    public static AddMedRepo getInstance(Context context, RemoteSource remoteSource, LocalSourceMedicine localSourceMedicine, LocalSourceMedicineDose localSourceMedicineDose) {
        if(instance == null) {
            instance = new AddMedRepo(context, remoteSource, localSourceMedicine, localSourceMedicineDose);
        }
        return instance;
    }

    @Override
    public void insertMedicineInFirebase(AddMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses) {
        remoteSource.enqueueCall(networkDelegate, medicine, doses);
    }

    @Override
    public void insertMedicineInRoom(Medicine medicine, ArrayList<MedicineDose> doses) {
        Log.i("INSERT", "insertMedicineInRoom: ");
        medicine.setSync(true);
        localSourceMedicine.insertMedicine(medicine);
        for(MedicineDose dose: doses) {
            dose.setSync(true);
            localSourceMedicineDose.insertMedicineDose(dose);
        }
    }
}
