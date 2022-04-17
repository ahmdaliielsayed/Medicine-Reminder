package com.ahmdalii.medicinereminder.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "time_scheduler")
public class TimeScheduler {
    @PrimaryKey
    @NonNull
    private String id;
    private String daySchedulerID;
    private String time;
    private Integer amount;
    private String status;
    private String giverID;
    private Boolean isSync;

    public TimeScheduler() {
    }

    public TimeScheduler(@NonNull String id, String daySchedulerID, String time, Integer amount, String status, String giverID, Boolean isSync) {
        this.id = id;
        this.daySchedulerID = daySchedulerID;
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

    public String getDaySchedulerID() {
        return daySchedulerID;
    }

    public void setDaySchedulerID(String daySchedulerID) {
        this.daySchedulerID = daySchedulerID;
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
}
