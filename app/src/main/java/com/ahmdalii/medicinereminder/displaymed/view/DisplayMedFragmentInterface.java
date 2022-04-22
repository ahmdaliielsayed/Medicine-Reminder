package com.ahmdalii.medicinereminder.displaymed.view;

import android.content.Context;
import android.view.View;

public interface DisplayMedFragmentInterface {
    Context getViewContext();
    void refreshView();
    void showProgressBar();
    void hideProgressBar();
}
