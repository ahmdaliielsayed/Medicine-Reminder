package com.ahmdalii.medicinereminder.notificationdialog.repo;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public interface NotificationDialogRepoInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine);
    MedicineDose getDose();
    void setDose(MedicineDose dose);


    void deleteDose();

    void takeDose();

    void skipDose();
}
