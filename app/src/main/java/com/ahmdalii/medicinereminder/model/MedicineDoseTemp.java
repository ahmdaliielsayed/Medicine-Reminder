package com.ahmdalii.medicinereminder.model;

public class MedicineDoseTemp {
    private String medID;
    private String day;
    private String time;
    private Integer amount;
    private String status;
    private String giverID;
    private Boolean isSync;

    public MedicineDoseTemp() {
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
