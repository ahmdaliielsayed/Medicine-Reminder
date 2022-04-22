package com.ahmdalii.medicinereminder.displaymed.presenter;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface DisplayMedPresenterInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine);
    ArrayList<MedicineDose> getDoses();
    void addOneTimeWorkRequest();
    void removeOneTimeWorkRequest();
}
