package com.ahmdalii.medicinereminder;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.ahmdalii.medicinereminder.medicationreminder.view.MedicationReminderActivity;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public class Notification extends ContextWrapper {

    public static final String CHANNEL_ID = "Channel ID";
    public static final String CHANNEL_NAME = "Channel Name";
    public static final String CHANNEL_DESCRIPTION = "Channel Name";

    private NotificationManager mManager;
    public static int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);

    Medicine medicine;
    MedicineDose dose;

    public Notification(Context base, Medicine medicine, MedicineDose dose) {
        super(base);

        this.medicine = medicine;
        this.dose = dose;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {                                                      // NotificationManager.IMPORTANCE_DEFAULT
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(CHANNEL_DESCRIPTION);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {         // Context.NOTIFICATION_SERVICE
            mManager = getSystemService(NotificationManager.class);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {

        Intent intent = new Intent(getApplicationContext(), MedicationReminderActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        intent.putExtra(Constants.MEDICINE_KEY, medicine);
        intent.putExtra(Constants.DOSE_KEY, dose);

        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getActivity(this, uniqueInt, intent, PendingIntent.FLAG_ONE_SHOT);

        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(medicine.getName())
                .setContentText(medicine.getInstructions()) // getText(R.string.open_dialogue)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // you can delete this line
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);
    }
}
