package com.ahmdalii.medicinereminder.notificationdialog.repo;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.db.room.medicine.LocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NotificationDialogRepo implements NotificationDialogRepoInterface {

    Medicine medicine;
    MedicineDose dose;

    ArrayList<MedicineDose> doses;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDoses(ArrayList<MedicineDose> doses) {
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
        this.doses = doses;
    }

    @Override
    public void getStoredDoses(Context context, DisplayMedNetworkDelegate networkDelegate, LifecycleOwner owner) {
        if(NetworkConnection.isNetworkAvailable(context)) {
            remoteSource.enqueueCall(networkDelegate, medicine.getId());
        }
        else {
            localSourceMedicineDose.getAllMedicineDoses(medicine.getId()).observe(owner, new Observer<List<MedicineDose>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onChanged(List<MedicineDose> medicineDoses) {
                    setDoses((ArrayList<MedicineDose>) medicineDoses);
                }
            });
        }
    }

    @Override
    public void deleteDose() {

    }

    @Override
    public void updateMedicineAndDose(Context context, AddMedicineNetworkDelegate networkDelegate) {
        if(NetworkConnection.isNetworkAvailable(context)) {
            remoteSource.enqueueCall(networkDelegate, medicine, doses);
        }
        else {
            updateMedicineAndDoseLocal(medicine, doses);
        }

    }

    @Override
    public void updateMedicineAndDoseLocal(Medicine medicine, ArrayList<MedicineDose> doses) {
        localSourceMedicine.insertMedicine(medicine);
        for(MedicineDose dose: doses) {
            localSourceMedicineDose.insertMedicineDose(dose);
        }
    }

    @Override
    public void skipDose() {

    }
}
