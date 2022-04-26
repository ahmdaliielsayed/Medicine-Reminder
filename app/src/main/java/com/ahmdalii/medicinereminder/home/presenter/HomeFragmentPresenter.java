package com.ahmdalii.medicinereminder.home.presenter;

import android.annotation.SuppressLint;

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
    public void getAllDosesWithMedicineName(Date currentDate) {
        viewInterface.setDosesToAdapter(repoInterface.getAllDosesWithMedicineName(currentDate));
    }

    @Override
    public void getAllDosesWithMedicineNameForUser(Date currentDate, String uid, NetworkHomeDelegate networkHomeDelegate) {
        repoInterface.getAllDosesWithMedicineNameForUser(currentDate, uid, networkHomeDelegate);
    }

    @Override
    public void onResponse(Map<Medicine, MedicineDose> listMap) {
//        Map<Medicine, MedicineDose> returnedMedDosMap = new HashMap<>();
//
//        for (Map.Entry<Medicine, List<MedicineDose>> entry : listMap.entrySet()) {
//            Medicine key = entry.getKey();
//            List<MedicineDose> value = entry.getValue();
//            for (int i=0; i<value.size(); i++) {
//                String[] dateTime = value.get(i).getTime().split("T"); // 2022-04-25 T 03:41
//                @SuppressLint("SimpleDateFormat")
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    Date parsedDate = formatter.parse(dateTime[0]);
//
//                    if (Objects.requireNonNull(parsedDate).equals(currentDate)) {
//                        returnedMedDosMap.put(key, value.get(i));
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        viewInterface.setDosesToAdapter(listMap);
    }

    @Override
    public void onFailure(String error) {
        viewInterface.onError(error);
    }
}
