package com.ahmdalii.medicinereminder.addmed.model;

public enum MedicineDayFrequency {

    EVERYDAY("everyday"),
    EVERY_NUMBER_OF_DAYS("everynumberofdays"),
    SPECIFIC_DAYS("specificdays");

    private String frequency;

    MedicineDayFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }
}
