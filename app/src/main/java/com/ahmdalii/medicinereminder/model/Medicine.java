package com.ahmdalii.medicinereminder.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine")
public class Medicine {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String form;
    private String unit;
    private Integer strength;
    private String reason;
    private String daySchedulerID;
    private String instructions;
    private Integer imageResourceID;
    private String startDate;
    private String endDate;
    private Integer remainingMedAmount;
    private Integer reminderMedAmount;
    private String refillReminderTime;
    private Boolean isActivated;
    private Boolean isSync;
    private String userID;

    public Medicine() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDaySchedulerID() {
        return daySchedulerID;
    }

    public void setDaySchedulerID(String daySchedulerID) {
        this.daySchedulerID = daySchedulerID;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getImageResourceID() {
        return imageResourceID;
    }

    public void setImageResourceID(Integer imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getRemainingMedAmount() {
        return remainingMedAmount;
    }

    public void setRemainingMedAmount(Integer remainingMedAmount) {
        this.remainingMedAmount = remainingMedAmount;
    }

    public Integer getReminderMedAmount() {
        return reminderMedAmount;
    }

    public void setReminderMedAmount(Integer reminderMedAmount) {
        this.reminderMedAmount = reminderMedAmount;
    }

    public String getRefillReminderTime() {
        return refillReminderTime;
    }

    public void setRefillReminderTime(String refillReminderTime) {
        this.refillReminderTime = refillReminderTime;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
