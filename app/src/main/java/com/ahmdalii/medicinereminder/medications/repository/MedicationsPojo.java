package com.ahmdalii.medicinereminder.medications.repository;

public class MedicationsPojo {
    String name;
    String strength;
    int leftNum;
    int icon;

    public MedicationsPojo(String name, String strength, int leftNum, int icon) {
        this.name = name;
        this.strength = strength;
        this.leftNum = leftNum;
        this.icon = icon;
    }

    public MedicationsPojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
