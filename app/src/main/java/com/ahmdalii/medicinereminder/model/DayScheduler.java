package com.ahmdalii.medicinereminder.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "day_scheduler")
public class DayScheduler {
    @PrimaryKey
    @NonNull
    private String id;
    private String medID;
    private String day;
    private Boolean isSync;

    public DayScheduler() {
    }

    public DayScheduler(@NonNull String id, String medID, String day, Boolean isSync) {
        this.id = id;
        this.medID = medID;
        this.day = day;
        this.isSync = isSync;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMedID() {
        return medID;
    }

    public void setMedID(String medID) {
        this.medID = medID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }
}
