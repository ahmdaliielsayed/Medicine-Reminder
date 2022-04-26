package com.ahmdalii.medicinereminder.notificationdialog.repo;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface NotificationDialogRepoInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine);
    MedicineDose getDose();
    void setDose(MedicineDose dose);
    ArrayList<MedicineDose> getDoses();
    void setDoses(ArrayList<MedicineDose> doses);

    void getStoredDoses(Context context, DisplayMedNetworkDelegate networkDelegate, LifecycleOwner owner);

    void deleteDose();

    void updateMedicineAndDose(Context context, AddMedicineNetworkDelegate networkDelegate);
    void updateMedicineAndDoseLocal(Medicine medicine, ArrayList<MedicineDose> doses);

    void skipDose();
}
