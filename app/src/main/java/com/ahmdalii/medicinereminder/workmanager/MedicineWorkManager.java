package com.ahmdalii.medicinereminder.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class MedicineWorkManager extends Worker {

    public MedicineWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
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
        return null;
    }
}
