package com.ahmdalii.medicinereminder.home.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.ahmdalii.medicinereminder.home.repository.HomeFragmentRepoInterface;
import com.ahmdalii.medicinereminder.home.view.HomeFragmentInterface;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.NetworkHomeDelegate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface, NetworkHomeDelegate {

    HomeFragmentInterface viewInterface;
    HomeFragmentRepoInterface repoInterface;

    public HomeFragmentPresenter(HomeFragmentInterface viewInterface, HomeFragmentRepoInterface repoInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void getAllDosesWithMedicineName(Date currentDate, Context context) {
        viewInterface.setDosesToAdapter(repoInterface.getAllDosesWithMedicineName(currentDate, context));
    }

    @Override
    public void getAllDosesWithMedicineNameForUser(Date currentDate, String uid) {
        repoInterface.getAllDosesWithMedicineNameForUser(currentDate, uid, this);
    }

    @Override
    public void onResponse(Map<Medicine, MedicineDose> listMap) {
        viewInterface.setDosesToAdapter(listMap);
    }

    @Override
    public void onFailure(String error) {
        viewInterface.onError(error);
    }
}
