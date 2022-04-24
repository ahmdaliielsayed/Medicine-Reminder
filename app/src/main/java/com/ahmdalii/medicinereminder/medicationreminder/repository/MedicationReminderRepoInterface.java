package com.ahmdalii.medicinereminder.medicationreminder.repository;

import android.content.Context;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.User;

public interface MedicationReminderRepoInterface {

    User getUserFromRoom(Context context);
    void updateDose(MedicineDose medicineDose, Context context);
    void snoozeDose(MedicineDose medicineDose, Medicine medicine, Context context);
}
