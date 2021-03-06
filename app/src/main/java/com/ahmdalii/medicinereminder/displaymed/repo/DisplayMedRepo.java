package com.ahmdalii.medicinereminder.displaymed.repo;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;

import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.WorkRequestManager;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.displaymed.presenter.DeleteMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

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
            if(dose.getStatus().equals(DoseStatus.FUTURE.getStatus())) {
                return dose;
            }
        }
        return null;
    }

    @Override
    public void updateMedicine(AddMedicineNetworkDelegate networkDelegate, Context context) {
        if(NetworkConnection.isNetworkAvailable(context)) {
            remoteSource.enqueueCall(networkDelegate, medicine, doses);
        }
        else {
            updateMedicineLocal(medicine, doses);
        }
    }

    public void updateMedicineLocal(Medicine medicine, ArrayList<MedicineDose> doses) {
        localSourceMedicine.insertMedicine(medicine);
        for(MedicineDose dose: doses) {
            localSourceMedicineDose.insertMedicineDose(dose);
        }
    }

    @Override
    public void getStoredDoses(DisplayMedNetworkDelegate networkDelegate, Context context, LifecycleOwner owner) {
        if(NetworkConnection.isNetworkAvailable(context)) {
            remoteSource.enqueueCall(networkDelegate, medicine.getId());
        }
        else {
            Log.i("TAG", "getStoredDoses: " + medicine.getId());
            localSourceMedicineDose.getAllMedicineDoses(medicine.getId()).observe(owner, storedDoses -> {
                doses = ((ArrayList<MedicineDose>) storedDoses);
                Log.i("TAG", "getStoredDoses: " + doses);
                networkDelegate.onSuccessLocal();
            });
        }
    }

    @Override
    public void deleteMedicine(DeleteMedicineNetworkDelegate networkDelegate, Context context) {
        if(NetworkConnection.isNetworkAvailable(context)) {
            remoteSource.enqueueCall(networkDelegate, medicine, doses);

            localSourceMedicine.deleteMedicine(medicine);
            for(MedicineDose dose: doses) {
                localSourceMedicineDose.deleteMedicineDose(dose);
            }
        }
        else {
            localSourceMedicine.deleteMedicine(medicine);
            for(MedicineDose dose: doses) {
                localSourceMedicineDose.deleteMedicineDose(dose);
            }
        }

        WorkRequestManager.removeWork(medicine.getId(), context);
    }

    @Override
    public void getStoredMedicineAndDoses(DisplayMedNetworkDelegate networkDelegate, Context context, LifecycleOwner owner, String medID) {
        localSourceMedicine.getMedicine(medID).observe(owner, med -> {
            medicine = med;
            if(med != null) {
                getStoredDoses(networkDelegate, context, owner);
            }
        });
    }
}
