package com.ahmdalii.medicinereminder.db.room.medicinedose;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;
import java.util.Map;

@Dao
public interface MedicineDoseDAO {
    @Query("SELECT * FROM medicine_dose")
    LiveData<List<MedicineDose>> getAllMedicineDoses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedicineDose(MedicineDose medicineDose);

    @Delete
    void deleteMedicineDose(MedicineDose medicineDose);

    @Update
    void updateMedicineDose(MedicineDose medicineDose);

    @Query("SELECT * FROM medicine_dose WHERE status = :futureStatus ORDER BY time LIMIT 1")
    MedicineDose getNextMedicineDose(String futureStatus);

    @Query("SELECT * FROM medicine WHERE id = :med_id LIMIT 1")
    Medicine getNextMedicine(String med_id);

//    //          dose_id   med_id  time_onCard (كاام حبااية) (DoseStatus)  med_name  الوحدة (جم / ملغم)MedicineUnit  500 gm الكمية نفسهاا
//    @Query("SELECT md.id, md.medID, md.time, md.amount, md.status, medicine.name, medicine.unit, medicine.strength FROM medicine_dose AS md INNER JOIN medicine ON medID = medicine.id")
//    LiveData<List<POJO>> getAllDosesWithMedicineName();


    @Query("SELECT DISTINCT * FROM medicine INNER JOIN medicine_dose ON medicine.id = medicine_dose.medID AND medicine.isActivated = 1")
    Map<Medicine, List<MedicineDose>> getAllDosesWithMedicineName();
}
