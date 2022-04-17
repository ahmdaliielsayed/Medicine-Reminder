package com.ahmdalii.medicinereminder.model;

public enum DoseStatus {
    TAKEN("taken"),
    SKIPPED("skipped"),
    UNKNOWN("unknown");

    private String status;

    DoseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
