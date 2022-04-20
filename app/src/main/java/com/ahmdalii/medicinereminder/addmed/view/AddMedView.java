package com.ahmdalii.medicinereminder.addmed.view;

import android.content.Context;

public interface AddMedView {
    void closeActivity();
    void showToast(String text);
    Context getContext();
}
