package com.ahmdalii.medicinereminder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.workmanager.MedicineWorkManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class WorkRequestManager {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createWorkRequest(MedicineDose dose, Medicine medicine, Context context) {

        @SuppressLint("RestrictedApi") Data data = new Data.Builder()
                .put("medicine", JSONSerializer.serializeMedicine(medicine))
                .put("dose", JSONSerializer.serializeMedicineDose(dose))
                .build();
        LocalDateTime dateTime = LocalDateTime.parse(dose.getTime());

        Log.i("TAG", "createWorkRequest: " + Duration.between(LocalDateTime.now(), dateTime).toMillis());
        OneTimeWorkRequest addMedRequest = new OneTimeWorkRequest.Builder(MedicineWorkManager.class)
                .setInitialDelay(Duration.between(LocalDateTime.now(), dateTime).toMillis(), TimeUnit.MILLISECONDS)
                .setInputData(data)
                .addTag(medicine.getId())
                .addTag(dose.getId())
                .build();

        WorkManager worker = WorkManager.getInstance(context);
        worker.enqueue(addMedRequest);
    }

    public static void removeWork(String tag, Context context) {
        WorkManager worker = WorkManager.getInstance(context);
        worker.cancelAllWorkByTag(tag);
    }
}
