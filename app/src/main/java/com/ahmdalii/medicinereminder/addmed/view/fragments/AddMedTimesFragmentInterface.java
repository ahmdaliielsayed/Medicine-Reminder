package com.ahmdalii.medicinereminder.addmed.view.fragments;

import java.sql.Time;
import java.time.LocalTime;

public interface AddMedTimesFragmentInterface {
    void putAmount(int index, Integer amount);
    void putTime(int index, LocalTime time);
}
