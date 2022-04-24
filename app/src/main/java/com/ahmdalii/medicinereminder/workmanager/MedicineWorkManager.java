package com.ahmdalii.medicinereminder.workmanager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.medicationreminder.view.MedicationReminderActivity;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.Map;

public class MedicineWorkManager extends Worker {

    private Context context;

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

        Log.i("TAGS", "doWork: " + medicine);
        Log.i("TAGS", "doWork: " + dose);

        Intent startIntent = new Intent(context, MedicationReminderActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startIntent.putExtra(Constants.MEDICINE_KEY, medicine);
        startIntent.putExtra(Constants.DOSE_KEY, dose);

        context.startActivity(startIntent);

        return Result.success();
    }
}
