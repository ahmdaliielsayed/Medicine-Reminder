package com.ahmdalii.medicinereminder.medicationreminder.presenter;

import android.content.Context;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public interface MedicationReminderPresenterInterface {

    void getUserFromRoom(Context context);
    void updateDose(MedicineDose medicineDose, Medicine medicine, Context context);
    void snoozeDose(MedicineDose medicineDose, Medicine medicine, Context context);
}
