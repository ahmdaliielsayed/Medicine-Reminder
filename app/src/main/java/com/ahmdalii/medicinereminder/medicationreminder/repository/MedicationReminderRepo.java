package com.ahmdalii.medicinereminder.medicationreminder.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.Notification;
import com.ahmdalii.medicinereminder.WorkRequestManager;
import com.ahmdalii.medicinereminder.db.room.medicinedose.LocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.db.room.user.LocalSourceUser;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.workmanager.MedicineWorkManager;
import com.google.firebase.auth.FirebaseAuth;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class MedicationReminderRepo implements MedicationReminderRepoInterface {

    LocalSourceUser localSourceUser;
    LocalSourceMedicineDose localSourceMedicineDose;
    private static MedicationReminderRepo medicationReminderRepo = null;

    private MedicationReminderRepo(LocalSourceUser localSourceUser, LocalSourceMedicineDose localSourceMedicineDose) {
        this.localSourceUser = localSourceUser;
        this.localSourceMedicineDose = localSourceMedicineDose;
    }

    public static MedicationReminderRepo getInstance(LocalSourceUser localSourceUser, LocalSourceMedicineDose localSourceMedicineDose) {
        if (medicationReminderRepo == null) {
            medicationReminderRepo = new MedicationReminderRepo(localSourceUser, localSourceMedicineDose);
        }

        return medicationReminderRepo;
    }

    @Override
    public User getUserFromRoom(Context context) {
        String userId = SharedPrefManager.getInstance(context, Constants.USERS_FILE).getStringValue(Constants.USER_ID_KEY);

        return localSourceUser.getUser(userId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void updateDose(MedicineDose medicineDose,  Medicine medicine, Context context) {
        localSourceMedicineDose.updateMedicineDose(medicineDose);
        localSourceMedicineDose.updateMedicine(medicine);
        MedicineDose nextMedicineDose = localSourceMedicineDose.getNextMedicineDose();
        Medicine nextMedicine = localSourceMedicineDose.getNextMedicine(nextMedicineDose.getMedID());
        WorkRequestManager.createWorkRequest(nextMedicineDose, nextMedicine, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void snoozeDose(MedicineDose medicineDose, Medicine medicine, Context context) {
        String[] dateTime = medicineDose.getTime().split("T");
        String[] hoursMinutes = dateTime[1].split(":");
        int minutes = Integer.parseInt(hoursMinutes[1]) + 5;
        if (minutes >= 60) {
            hoursMinutes[0] += minutes % 60;
            minutes /= 60;
        }

        medicineDose.setTime(dateTime[0] + "T" + hoursMinutes[0] + ":" + minutes);
        localSourceMedicineDose.updateMedicineDose(medicineDose);

        @SuppressLint("RestrictedApi")
        Data data = new Data.Builder()
                .put("medicine", JSONSerializer.serializeMedicine(medicine))
                .put("dose", JSONSerializer.serializeMedicineDose(medicineDose))
                .build();

        OneTimeWorkRequest addMedRequest = new OneTimeWorkRequest.Builder(MedicineWorkManager.class)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .setInputData(data)
                .addTag(medicine.getId())
                .addTag(medicineDose.getId())
                .build();

        WorkManager worker = WorkManager.getInstance(context);
        worker.enqueue(addMedRequest);
    }
}
