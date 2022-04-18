package com.ahmdalii.medicinereminder;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

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
}
