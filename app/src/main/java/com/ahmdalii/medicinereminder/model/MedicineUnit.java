package com.ahmdalii.medicinereminder.model;

public enum MedicineUnit {
    g("g"),
    mg("mg"),
    IU("IU"),
    mcg("mcg"),
    mEq("mEq"),
    ml("ml"),
    mgperml("mg/ml"),
    mcgperml("mcg/ml"),
    percent("%");


    private String unit;

    MedicineUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
