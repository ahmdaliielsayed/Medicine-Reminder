package com.ahmdalii.medicinereminder.editmed.presenter;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface EditMedPresenterInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine);
    ArrayList<MedicineDose> getDoses();
    void setDoses(ArrayList<MedicineDose> doses);
}
