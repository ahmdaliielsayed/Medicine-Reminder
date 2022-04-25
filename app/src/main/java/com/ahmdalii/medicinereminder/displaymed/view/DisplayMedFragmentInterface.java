package com.ahmdalii.medicinereminder.displaymed.view;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;

public interface DisplayMedFragmentInterface {
    Context getViewContext();
    void refreshView();
    void showProgressBar();
    void hideProgressBar();
    void showToast(String message);
    void closeView();

    LifecycleOwner getViewLifecycle();
}
