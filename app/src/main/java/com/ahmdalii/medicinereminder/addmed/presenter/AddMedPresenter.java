package com.ahmdalii.medicinereminder.addmed.presenter;

import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.view.AddMedView;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.sql.Time;
import java.util.ArrayList;

public class AddMedPresenter implements AddMedPresenterInterface {

    private AddMedView addMedView;
    private Medicine medicine;
    private MedicineDose medicineDose;
    ArrayList<String> units = new ArrayList<String>();
    MedicineDayFrequency dayFrequency;
    int timeFrequency;
    ArrayList<Time> times;

    public AddMedPresenter(AddMedView addMedView) {
        this.addMedView = addMedView;
        medicine = new Medicine();
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public MedicineDose getMedicineDose() {
        return medicineDose;
    }

    public void setMedicineDose(MedicineDose medicineDose) {
        this.medicineDose = medicineDose;
    }

    public AddMedView getAddMedView() {
        return addMedView;
    }

    public void setAddMedView(AddMedView addMedView) {
        this.addMedView = addMedView;
    }

    public void addUnit(String unit) {
        units.add(unit);
    }

    public ArrayList<String> getUnits() {
        return units;
    }

    public MedicineDayFrequency getDayFrequency() {
        return dayFrequency;
    }

    public void setDayFrequency(MedicineDayFrequency dayFrequency) {
        this.dayFrequency = dayFrequency;
    }

    @Override
    public void setTimeFrequency(int frequency) {
        timeFrequency = frequency;
        times = new ArrayList<>(timeFrequency);
    }

    @Override
    public int getTimeFrequency() {
        return timeFrequency;
    }

    @Override
    public void putTime(int index, Time time) {
        times.set(index, time);
    }

    @Override
    public ArrayList<Time> getTimes() {
        return times;
    }
}
