package com.ahmdalii.medicinereminder.editmed.view;

import android.content.Context;

public interface EditMedActivityInterface {
    Context getViewContext();
    void closeView();
    void showSuccessToast();
    void showFailureToast();

    void setUI();

    void showProgressDialog();

    void hideProgressDialog();
}
