package com.ahmdalii.medicinereminder.addmed.model;

public enum MedicineForm {
    PILLS("pills"),
    SOLUTION("solution"),
    INJECTION("injection"),
    POWDER("powder"),
    DROPS("drops"),
    INHALER("inhaler"),
    TOPICAL("topical");

    private String form;

    MedicineForm(String form) {
        this.form = form;
    }

    public String getForm() {
        return form;
    }
}
