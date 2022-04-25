package com.ahmdalii.medicinereminder.medications.repository;

public class MedsPojo {
    String id;
    String name;
    Integer strength;
    Integer remainingMedAmount;
    String form;

    public MedsPojo(String name, Integer strength, Integer remainingMedAmount, String form, String id) {
        this.name = name;
        this.strength = strength;
        this.remainingMedAmount = remainingMedAmount;
        this.form = form;
        this.id = id;
    }

    public MedsPojo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getRemainingMedAmount() {
        return remainingMedAmount;
    }

    public void setRemainingMedAmount(Integer remainingMedAmount) {
        this.remainingMedAmount = remainingMedAmount;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

}
