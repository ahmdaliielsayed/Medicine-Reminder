package com.ahmdalii.medicinereminder.notificationdialog.presenter;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public interface NotificationDialogPresenterInterface {
    Medicine getMedicine();
    void setMedicine(Medicine medicine);
    MedicineDose getDose();
    void setDose(MedicineDose dose);

    void deleteDose();

    void takeDose();

    void skipDose();
}
