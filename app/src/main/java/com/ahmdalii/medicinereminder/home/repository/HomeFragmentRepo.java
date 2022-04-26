package com.ahmdalii.medicinereminder.home.repository;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.NetworkHomeDelegate;
import com.ahmdalii.medicinereminder.network.RemoteSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HomeFragmentRepo implements HomeFragmentRepoInterface {

    RemoteSource remoteSource;
    ConcreteLocalSourceMedicineDose localSourceMedicineDose;
    private static HomeFragmentRepo homeFragmentRepo = null;
    Map<Medicine, MedicineDose> returnedMedDosMap;

    private HomeFragmentRepo(ConcreteLocalSourceMedicineDose localSourceMedicineDose, RemoteSource remoteSource) {
        this.localSourceMedicineDose = localSourceMedicineDose;
        this.remoteSource = remoteSource;
    }

    public static HomeFragmentRepo getInstance(ConcreteLocalSourceMedicineDose localSourceMedicineDose, RemoteSource remoteSource) {
        if (homeFragmentRepo == null) {
            homeFragmentRepo = new HomeFragmentRepo(localSourceMedicineDose, remoteSource);
        }

        return homeFragmentRepo;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Map<Medicine, MedicineDose> getAllDosesWithMedicineName(Date currentDate) {
        returnedMedDosMap = new HashMap<>();

        Map<Medicine, List<MedicineDose>> allDosesWithMedicineName = localSourceMedicineDose.getAllDosesWithMedicineName();

        for (Map.Entry<Medicine, List<MedicineDose>> entry : allDosesWithMedicineName.entrySet()) {
            Medicine key = entry.getKey();
            List<MedicineDose> value = entry.getValue();
            for (int i=0; i<value.size(); i++) {
                String[] dateTime = value.get(i).getTime().split("T"); // 2022-04-25 T 03:41
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date parsedDate = formatter.parse(dateTime[0]);

                    if (Objects.requireNonNull(parsedDate).equals(currentDate)) {
                        returnedMedDosMap.put(key, value.get(i));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return returnedMedDosMap;
    }

    @Override
    public void getAllDosesWithMedicineNameForUser(Date currentDate, String uid, NetworkHomeDelegate networkHomeDelegate) {
        remoteSource.getAllDosesWithMedicineNameForUser(currentDate, uid, networkHomeDelegate);
    }

//    public List<Date> getDaysBetweenDates (Date startDate, Date endDate) {
//        Calendar myCalendar = Calendar.getInstance();
//        myCalendar.setTime(endDate);
//        myCalendar.add(Calendar.DATE, 1);
//        endDate = myCalendar.getTime();
//
//        List<Date> dates = new ArrayList<>();
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(startDate);
//
//        while (calendar.getTime().before(endDate)) {
//            Date result = calendar.getTime();
//            dates.add(result);
//            calendar.add(Calendar.DATE, 1);
//        }
//        return dates;
//    }

}
