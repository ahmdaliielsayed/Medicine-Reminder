package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.util.Pair;

import com.ahmdalii.medicinereminder.addmed.model.WeekDays;

import java.util.ArrayList;

public interface AddMedWeekDaysFragmentInterface {
    void setDay(int index, boolean isSelected);
    ArrayList<Pair<WeekDays, Boolean>> getDays();
}
