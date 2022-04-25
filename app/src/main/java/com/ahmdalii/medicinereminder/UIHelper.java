package com.ahmdalii.medicinereminder;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public class UIHelper {

    public static void showAlert(Context context, int title, String message, int icon) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {

                })
                .setIcon(icon)
                .show();
    }

    public static void openNotification(MedicineDose medicineDose, Medicine medicine, Context context) {
        Notification notificationHelper = new Notification(context, medicine, medicineDose);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(medicineDose.getId().hashCode(), nb.build());
    }
}
