package com.ahmdalii.medicinereminder.medications.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicationsRepository implements MedicationsRepositoryInterface {
    private Context context;
    MedicationRemoteSourceInterface remoteSource;
    MedicationsLocalSourceInterface localSource;
    private static MedicationsRepository repo = null;

    private MedicationsRepository(Context context, MedicationsLocalSourceInterface localSource) {
        this.context = context;
        //this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    public static MedicationsRepository getInstance(Context context, MedicationsLocalSourceInterface localSource){
        if(repo == null)
            repo = new MedicationsRepository(context, localSource);
        return repo;
    }


    @Override
    public LiveData<List<MedsPojo>> getActiveMeds() {
        return localSource.getLocalActiveMeds();
    }

    @Override
    public LiveData<List<MedsPojo>> getInactiveMeds() {
        return localSource.getLocalInactiveMeds();
    }
}
