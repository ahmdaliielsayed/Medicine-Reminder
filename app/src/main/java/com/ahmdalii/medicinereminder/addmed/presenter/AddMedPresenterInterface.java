package com.ahmdalii.medicinereminder.addmed.presenter;

import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.model.WeekDays;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public interface AddMedPresenterInterface {
    Medicine getMedicine();
    MedicineDose getMedicineDose();
    void addUnit(String unit);
    ArrayList<String> getUnits();
    MedicineDayFrequency getDayFrequency();
    void setDayFrequency(MedicineDayFrequency dayFrequency);
    void setDaysBetweenDoses(Integer daysBetweenDoses);
    void setTimeFrequency(int frequency);
    int getTimeFrequency();
    void putTime(int index, Time time);
    void setAmounts(ArrayList<Integer> amounts);
    ArrayList<Time> getTimes();
    void setDays(ArrayList<WeekDays> days);
    LocalDate getStartDate();
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);

    void addMedFinished();

}
