package com.ahmdalii.medicinereminder.db.room.medicinedose;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.List;

@Dao
public interface MedicineDoseDAO {
    @Query("SELECT * FROM medicine_dose")
    LiveData<List<MedicineDose>> getAllMedicineDoses();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMedicineDose(MedicineDose medicineDose);

    @Delete
    void deleteMedicineDose(MedicineDose medicineDose);

//    //          dose_id   med_id  time_onCard (كاام حبااية) (DoseStatus)  med_name  الوحدة (جم / ملغم)MedicineUnit  500 gm الكمية نفسهاا
//    @Query("SELECT md.id, md.medID, md.time, md.amount, md.status, medicine.name, medicine.unit, medicine.strength FROM medicine_dose AS md INNER JOIN medicine ON medID = medicine.id")
//    LiveData<List<POJO>> getAllDosesWithMedicineName();
}
