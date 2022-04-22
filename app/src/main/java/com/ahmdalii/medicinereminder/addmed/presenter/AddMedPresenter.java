package com.ahmdalii.medicinereminder.addmed.presenter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.model.WeekDays;
import com.ahmdalii.medicinereminder.addmed.model.repo.AddMedRepo;
import com.ahmdalii.medicinereminder.addmed.model.repo.AddMedRepoInterface;
import com.ahmdalii.medicinereminder.addmed.view.AddMedView;
import com.ahmdalii.medicinereminder.db.room.medicine.ConcreteLocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.workmanager.MedicineWorkManager;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AddMedPresenter implements AddMedPresenterInterface, AddMedicineNetworkDelegate {

    private AddMedView addMedView;
    private AddMedRepoInterface repo;

    private Medicine medicine;
    private MedicineDose medicineDose;
    ArrayList<String> units = new ArrayList<String>();
    MedicineDayFrequency dayFrequency;
    Integer daysBetweenDoses;
    int timeFrequency;
    ArrayList<LocalDateTime> times;
    ArrayList<Integer> amounts;
    ArrayList<WeekDays> days;
    LocalDate startDate;
    LocalDate endDate;

    ArrayList<MedicineDose> doses;


    public AddMedPresenter(AddMedView addMedView) {
        this.addMedView = addMedView;
        medicine = new Medicine();
        repo = AddMedRepo.getInstance(
                FirebaseClient.getInstance(),
                ConcreteLocalSourceMedicine.getInstance(addMedView.getContext()),
                ConcreteLocalSourceMedicineDose.getInstance(addMedView.getContext())
        );
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
        days = new ArrayList<>();
    }

    public void setDaysBetweenDoses(Integer daysBetweenDoses) {
        this.daysBetweenDoses = daysBetweenDoses;
    }

    @Override
    public void setTimeFrequency(int frequency) {
        timeFrequency = frequency;
        times = new ArrayList<>(timeFrequency);
        for(int i = 0; i < timeFrequency; i++) {
            times.add(null);
        }
        amounts = new ArrayList<>(timeFrequency);
        for(int i = 0; i < timeFrequency; i++) {
            amounts.add(null);
        }
    }

    @Override
    public int getTimeFrequency() {
        return timeFrequency;
    }

    @Override
    public void putTime(int index, LocalDateTime time) {
        times.set(index, time);
    }

    @Override
    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }

    @Override
    public ArrayList<LocalDateTime> getTimes() {
        return times;
    }

    @Override
    public void setDays(ArrayList<WeekDays> days) {
        this.days = days;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addMedFinished() {

        medicine.setActivated(true);
        medicine.setSync(false);

        createSchedule();

        repo.insertMedicineInFirebase(this, medicine, doses);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createSchedule() {
        doses = new ArrayList<>();
        if(dayFrequency == MedicineDayFrequency.EVERYDAY) {
            createEverydaySchedule();
        }
        else if(dayFrequency == MedicineDayFrequency.EVERY_NUMBER_OF_DAYS) {
            createEveryNDaySchedule();
        }
        else {
            createSpecificDaysSchedule();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createEverydaySchedule() {
        while(!startDate.isAfter(endDate)) {

            for(int i = 0; i < timeFrequency; i++) {
                if(times.get(i).isAfter(LocalDateTime.now())) {
                    addDose(i);
                }
            }
            startDate = startDate.plusDays(1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createEveryNDaySchedule() {
        while(!startDate.isAfter(endDate)) {

            for(int i = 0; i < timeFrequency; i++) {
                if(times.get(i).isAfter(LocalDateTime.now())) {
                    addDose(i);
                }
            }
            startDate = startDate.plusDays(daysBetweenDoses);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createSpecificDaysSchedule() {
        while(!startDate.isAfter(endDate)) {
            if(isSelectedDay()) {
                for (int i = 0; i < timeFrequency; i++) {
                    if(times.get(i).isAfter(LocalDateTime.now())) {
                        addDose(i);
                    }
                }
            }
            startDate = startDate.plusDays(1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addDose(int i) {
        MedicineDose dose = new MedicineDose();
        LocalDateTime time = LocalDateTime.of(startDate, times.get(i).toLocalTime());
        dose.setTime(time.toString());
        dose.setAmount(amounts.get(i));
        dose.setStatus(DoseStatus.FUTURE.getStatus());
        dose.setSync(false);
        doses.add(dose);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isSelectedDay() {
        if(days.stream().anyMatch(day -> day.getDay().equals(startDate.getDayOfWeek().toString().toLowerCase(Locale.ROOT)))) {
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSuccess(Medicine medicine, ArrayList<MedicineDose> doses) {
        Log.i("INSER", "onSuccess: " + medicine);
        Log.i("INSER", "onSuccess: " + doses);
        repo.insertMedicineInRoom(medicine, doses);

        createWorkRequest();

        addMedView.showToast("Medicine Added Successfully");
        addMedView.closeActivity();
    }

    @Override
    public void onFailure() {
        addMedView.showToast("Can't Add Medicine");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createWorkRequest() {
        
        LocalDateTime dateTime = LocalDateTime.parse(doses.get(0).getTime());

        @SuppressLint("RestrictedApi") Data data = new Data.Builder()
                .put("medicine", JSONSerializer.serializeMedicine(medicine))
                .put("dose", JSONSerializer.serializeMedicineDose(doses.get(0)))
                .build();

        OneTimeWorkRequest addMedRequest = new OneTimeWorkRequest.Builder(MedicineWorkManager.class)
                .setInitialDelay(Duration.between(LocalDateTime.now(), dateTime).toMillis(), TimeUnit.MILLISECONDS)
                .setInputData(data)
                .addTag(medicine.getId())
                .build();

        WorkManager worker = WorkManager.getInstance(addMedView.getContext());
        worker.enqueue(addMedRequest);
    }

}
