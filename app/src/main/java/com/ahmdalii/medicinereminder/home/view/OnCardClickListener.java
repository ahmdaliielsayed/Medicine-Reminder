package com.ahmdalii.medicinereminder.home.view;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public interface OnCardClickListener {

    void onCardClick(Medicine medicine, MedicineDose medicineDose);
}
