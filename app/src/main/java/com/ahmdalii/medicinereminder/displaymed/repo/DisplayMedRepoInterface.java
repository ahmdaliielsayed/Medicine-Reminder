package com.ahmdalii.medicinereminder.displaymed.repo;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DeleteMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface DisplayMedRepoInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine);
    ArrayList<MedicineDose> getDoses();
    void setDoses(ArrayList<MedicineDose> doses);
    void getDosesFromFirebase(DisplayMedNetworkDelegate networkDelegate, String medID);

    MedicineDose getUpcomingDose();

    void updateMedicine(AddMedicineNetworkDelegate networkDelegate, Context context);
    void updateMedicineLocal(Medicine medicine, ArrayList<MedicineDose> doses);

    void getStoredDoses(DisplayMedNetworkDelegate networkDelegate, Context context, LifecycleOwner owner);

    void deleteMedicine(DeleteMedicineNetworkDelegate networkDelegate, Context context);

    void getStoredMedicineAndDoses(DisplayMedNetworkDelegate networkDelegate, Context context, LifecycleOwner owner, String medID);
}
