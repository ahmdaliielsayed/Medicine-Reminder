package com.ahmdalii.medicinereminder.workmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.MyService;
import com.ahmdalii.medicinereminder.Notification;
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.UIHelper;
import com.ahmdalii.medicinereminder.medicationreminder.view.MedicationReminderActivity;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.Map;

public class MedicineWorkManager extends Worker {

    private final Context context;

    public MedicineWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i("TAGS", "doWork: WORKING");

        Map<String, Object> map =  getInputData().getKeyValueMap();
        Medicine medicine = JSONSerializer.deserializeMedicine((String) map.get("medicine"));
        MedicineDose dose = JSONSerializer.deserializeMedicineDose((String) map.get("dose"));

        Log.d("asdfg:", "test test1");

        Intent startIntent = new Intent(context, MyService.class);
        startIntent.putExtra(Constants.MEDICINE_KEY, medicine);
        startIntent.putExtra(Constants.DOSE_KEY, dose);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(startIntent);
        } else {
            context.startService(startIntent);
        }

        Log.d("asdfg:", "test test2");

        /*UIHelper.openNotification(dose, medicine, context, getApplicationContext().getString(R.string.medicine_time));

        Log.d("asdfg:", "test test1");

        Intent startIntent = new Intent(context, MedicationReminderActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        startIntent.putExtra(Constants.MEDICINE_KEY, medicine);
        startIntent.putExtra(Constants.DOSE_KEY, dose);

        context.startActivity(startIntent);

        Log.d("asdfg:", "test test2");*/

        return Result.success();
    }
}
