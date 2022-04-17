package com.ahmdalii.medicinereminder.addmed.presenter;

import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.sql.Time;
import java.util.ArrayList;

public interface AddMedPresenterInterface {
    Medicine getMedicine();
    MedicineDose getMedicineDose();
    void addUnit(String unit);
    ArrayList<String> getUnits();
    MedicineDayFrequency getDayFrequency();
    void setDayFrequency(MedicineDayFrequency dayFrequency);
    void setTimeFrequency(int frequency);
    int getTimeFrequency();
    void putTime(int index, Time time);
    ArrayList<Time> getTimes();
}
