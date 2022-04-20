package com.ahmdalii.medicinereminder.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine")
public class Medicine {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "form")
    private String form;
    @ColumnInfo(name = "unit")
    private String unit;
    @ColumnInfo(name = "strength")
    private Integer strength;
    @ColumnInfo(name = "reason")
    private String reason;
    @ColumnInfo(name = "instructions")
    private String instructions;
    @ColumnInfo(name = "startDate")
    private String startDate;
    @ColumnInfo(name = "endDate")
    private String endDate;
    @ColumnInfo(name = "remainingMedAmount")
    private Integer remainingMedAmount;
    @ColumnInfo(name = "reminderMedAmount")
    private Integer reminderMedAmount;
    @ColumnInfo(name = "refillReminderTime")
    private String refillReminderTime;
    @ColumnInfo(name = "isActivated")
    private Boolean isActivated;
    @ColumnInfo(name = "isSync")
    private Boolean isSync;
    @ColumnInfo(name = "userID")
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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


    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", form='" + form + '\'' +
                ", unit='" + unit + '\'' +
                ", strength=" + strength +
                ", reason='" + reason + '\'' +
                ", instructions='" + instructions + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", remainingMedAmount=" + remainingMedAmount +
                ", reminderMedAmount=" + reminderMedAmount +
                ", refillReminderTime='" + refillReminderTime + '\'' +
                ", isActivated=" + isActivated +
                ", isSync=" + isSync +
                ", userID='" + userID + '\'' +
                '}';
    }
}
