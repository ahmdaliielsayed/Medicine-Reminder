package com.ahmdalii.medicinereminder.displaymed.presenter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.db.room.medicine.ConcreteLocalSourceMedicine;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.displaymed.repo.DisplayMedRepo;
import com.ahmdalii.medicinereminder.displaymed.repo.DisplayMedRepoInterface;
import com.ahmdalii.medicinereminder.displaymed.view.DisplayMedFragmentInterface;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.workmanager.MedicineWorkManager;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DisplayMedPresenter implements DisplayMedPresenterInterface, DisplayMedNetworkDelegate {

    DisplayMedFragmentInterface displayMedView;
    DisplayMedRepoInterface repo;

    public DisplayMedPresenter(DisplayMedFragmentInterface displayMedView) {
        this.displayMedView = displayMedView;

        repo = DisplayMedRepo.getInstance(FirebaseClient.getInstance(),
                ConcreteLocalSourceMedicine.getInstance(displayMedView.getViewContext()),
                ConcreteLocalSourceMedicineDose.getInstance(displayMedView.getViewContext())
        );
    }

    public void setMedicine(Medicine medicine) {
        displayMedView.showProgressBar();
        repo.setMedicine(medicine);
        repo.getDosesFromFirebase(this, medicine.getId());
    }

    public Medicine getMedicine() {
        return repo.getMedicine();
    }

    @Override
    public ArrayList<MedicineDose> getDoses() {
        return repo.getDoses();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addOneTimeWorkRequest() {
        MedicineDose upcomingDose = repo.getUpcomingDose();
        LocalDate date = LocalDate.parse(upcomingDose.getDay());
        LocalTime time = LocalTime.parse(upcomingDose.getTime());
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        @SuppressLint("RestrictedApi") Data data = new Data.Builder()
                .put("medicine", JSONSerializer.serializeMedicine(repo.getMedicine()))
                .put("dose", JSONSerializer.serializeMedicineDose(repo.getUpcomingDose()))
                .build();

        OneTimeWorkRequest addMedRequest = new OneTimeWorkRequest.Builder(MedicineWorkManager.class)
                .setInitialDelay(Duration.between(LocalDateTime.now(), dateTime).toMillis(), TimeUnit.MILLISECONDS)
                .setInputData(data)
                .addTag(repo.getMedicine().getId())
                .build();

        WorkManager worker = WorkManager.getInstance(displayMedView.getViewContext());
        worker.enqueue(addMedRequest);
    }

    @Override
    public void removeOneTimeWorkRequest() {
        WorkManager worker = WorkManager.getInstance(displayMedView.getViewContext());
        worker.cancelAllWorkByTag(repo.getMedicine().getId());
    }

    @Override
    public void onSuccess(ArrayList<MedicineDose> doses) {
        Log.i("TAG", "onSuccess: " + doses);
        displayMedView.hideProgressBar();
        repo.setDoses(doses);
        displayMedView.refreshView();
    }

    @Override
    public void onFailure() {

    }
}