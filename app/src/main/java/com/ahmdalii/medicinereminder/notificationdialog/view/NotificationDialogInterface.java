package com.ahmdalii.medicinereminder.notificationdialog.view;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

public interface NotificationDialogInterface {
    Context getViewContext();

    LifecycleOwner getLifecycleOwner();

    void showToast(String message);

    void showRefillReminderDialog();
}
