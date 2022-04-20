package com.ahmdalii.medicinereminder.addmed.model;

public enum WeekDays {

    SATURDAY("saturday"),
    SUNDAY("sunday"),
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday");

    private String day;

    WeekDays(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
