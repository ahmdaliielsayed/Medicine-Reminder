package com.ahmdalii.medicinereminder.home.presenter;

import android.content.Context;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;

public interface HomePresenterInterface {

    void getUserFromRoom(Context context);
    void signOut(Context context);
    void getAllUnSyncMedicines();
    void getAllUnSyncMedicineDoses();
    void syncMedicineListToFirebase(List<Medicine> unSyncedMedicines);
    void syncMedicineDosesListToFirebase(List<MedicineDose> unSyncedMedicineDoses);
}
