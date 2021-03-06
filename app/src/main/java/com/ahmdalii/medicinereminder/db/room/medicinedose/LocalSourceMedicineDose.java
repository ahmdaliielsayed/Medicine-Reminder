package com.ahmdalii.medicinereminder.db.room.medicinedose;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;
import java.util.Map;

public interface LocalSourceMedicineDose {
    LiveData<List<MedicineDose>> getAllMedicineDoses(String medID);
    void insertMedicineDose(MedicineDose medicineDose);
    void deleteMedicineDose(MedicineDose medicineDose);
    void updateMedicineDose(MedicineDose medicineDose);
    void updateMedicine(Medicine medicine);
    MedicineDose getNextMedicineDose();
    Medicine getNextMedicine(String med_id);
    Map<Medicine, List<MedicineDose>> getAllDosesWithMedicineName(String uid);
    LiveData<List<MedicineDose>> getAllUnSyncMedicineDoses();
    void updateMedicineDosesInRoom(MedicineDose updatedList);
}
