package com.ahmdalii.medicinereminder.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "medicine_dose")
public class MedicineDose implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String medID;
    private String time;
    private Integer amount;
    private String status;
    private String giverID;
    private Boolean isSync;

    public MedicineDose() {
    }

    public MedicineDose(@NonNull String id, String medID, String time, Integer amount, String status, String giverID, Boolean isSync) {
        this.id = id;
        this.medID = medID;
        this.time = time;
        this.amount = amount;
        this.status = status;
        this.giverID = giverID;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGiverID() {
        return giverID;
    }

    public void setGiverID(String giverID) {
        this.giverID = giverID;
    }

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }

    @Override
    public String toString() {
        return "MedicineDose{" +
                "id='" + id + '\'' +
                ", medID='" + medID + '\'' +
                ", time='" + time + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", giverID='" + giverID + '\'' +
                ", isSync=" + isSync +
                '}';
    }
}
