package com.ahmdalii.medicinereminder;

import static com.ahmdalii.medicinereminder.Notification.CHANNEL_DESCRIPTION;
import static com.ahmdalii.medicinereminder.Notification.CHANNEL_ID;
import static com.ahmdalii.medicinereminder.Notification.CHANNEL_NAME;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.ahmdalii.medicinereminder.home.view.HomeActivity;
import com.ahmdalii.medicinereminder.medicationreminder.view.MedicationReminderActivity;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

public class MyService extends Service {

    public static final int CH_ID = 101;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        buildNotification();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Medicine medicine = (Medicine) intent.getSerializableExtra(Constants.MEDICINE_KEY);
        MedicineDose dose = (MedicineDose) intent.getSerializableExtra(Constants.DOSE_KEY);

        UIHelper.openNotification(dose, medicine, this, getApplicationContext().getString(R.string.medicine_time));

        Log.d("asdfg:", "test test1");

        Intent startIntent = new Intent(this, MedicationReminderActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        startIntent.putExtra(Constants.MEDICINE_KEY, medicine);
        startIntent.putExtra(Constants.DOSE_KEY, dose);

        startActivity(startIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannelclass is new and not in the support library
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);

            // Register the channel with the system;
            // you can't change the importance or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void buildNotification() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        startForeground(CH_ID, notification);
    }
}