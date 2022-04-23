package com.ahmdalii.medicinereminder.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "medicine")
public class Medicine implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String form;
    private String unit;
    private Integer strength;
    private String reason;
    private String instructions;
    private String dayFrequency;
    private Integer everyNDays;
    private String weekDays;
    private Integer timeFrequency;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDayFrequency() {
        return dayFrequency;
    }

    public void setDayFrequency(String dayFrequency) {
        this.dayFrequency = dayFrequency;
    }

    public Integer getEveryNDays() {
        return everyNDays;
    }

    public void setEveryNDays(Integer everyNDays) {
        this.everyNDays = everyNDays;
    }

    public String getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String weekDays) {
        this.weekDays = weekDays;
    }

    public Integer getTimeFrequency() {
        return timeFrequency;
    }

    public void setTimeFrequency(Integer timeFrequency) {
        this.timeFrequency = timeFrequency;
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
                ", dayFrequency='" + dayFrequency + '\'' +
                ", everyNDays=" + everyNDays +
                ", weekDays='" + weekDays + '\'' +
                ", timeFrequency=" + timeFrequency +
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
