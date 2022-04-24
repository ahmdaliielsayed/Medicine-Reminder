package com.ahmdalii.medicinereminder;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public class ForegroundService extends Service {

    Medicine medicine;
    MedicineDose dose;


    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        medicine = (Medicine) intent.getSerializableExtra(Constants.MEDICINE_KEY);
        dose = (MedicineDose) intent.getSerializableExtra(Constants.DOSE_KEY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            openNotification(dose, medicine, this);

        // fire work manager here

        return super.onStartCommand(intent, flags, startId);
    }

    public void openNotification(MedicineDose medicineDose, Medicine medicine, Context context) {
        Notification notificationHelper = new Notification(context, medicine, medicineDose);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(medicineDose.getId().hashCode(), nb.build());
    }
}