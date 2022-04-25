package com.ahmdalii.medicinereminder.medicationreminder.presenter;

import android.content.Context;

import com.ahmdalii.medicinereminder.medicationreminder.repository.MedicationReminderRepoInterface;
import com.ahmdalii.medicinereminder.medicationreminder.view.MedicationReminderInterface;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public class MedicationReminderPresenter implements MedicationReminderPresenterInterface {

    private final MedicationReminderInterface viewInterface;
    private final MedicationReminderRepoInterface repoInterface;

    public MedicationReminderPresenter(MedicationReminderInterface viewInterface, MedicationReminderRepoInterface repoInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void getUserFromRoom(Context context) {
        viewInterface.displayUserInformation(repoInterface.getUserFromRoom(context));
    }

    @Override
    public void updateDose(MedicineDose medicineDose, Medicine medicine, Context context) {
        repoInterface.updateDose(medicineDose, medicine, context);
    }

    @Override
    public void snoozeDose(MedicineDose medicineDose, Medicine medicine, Context context) {
        repoInterface.snoozeDose(medicineDose, medicine, context);
    }
}
