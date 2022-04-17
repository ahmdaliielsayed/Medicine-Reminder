package com.ahmdalii.medicinereminder.db.room.dayscheduler;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.db.room.AppDataBase;
import com.ahmdalii.medicinereminder.model.DayScheduler;

import java.util.List;

public class ConcreteLocalSourceDayScheduler implements LocalSourceDayScheduler{

    private static ConcreteLocalSourceDayScheduler instance = null;
    private DaySchedulerDAO dao;
    private LiveData<List<DayScheduler>> storedDaySchedulersLiveData;

    private ConcreteLocalSourceDayScheduler(Context context) {
        AppDataBase database = AppDataBase.getInstance(context.getApplicationContext());
        dao = database.daySchedulerDAO();
        storedDaySchedulersLiveData = dao.getAllDaySchedulers();
    }

    public static ConcreteLocalSourceDayScheduler getInstance(Context context) {
        if(instance == null) {
            instance = new ConcreteLocalSourceDayScheduler(context);
        }
        return instance;
    }

    @Override
    public LiveData<List<DayScheduler>> getAllDaySchedulers() {
        return storedDaySchedulersLiveData;
    }

    @Override
    public void insertDayScheduler(DayScheduler dayScheduler) {
        new Thread() {
            @Override
            public void run() {
                dao.insertDayScheduler(dayScheduler);
            }
        }.start();
    }

    @Override
    public void deleteDayScheduler(DayScheduler dayScheduler) {
        new Thread() {
            @Override
            public void run() {
                dao.deleteDayScheduler(dayScheduler);
            }
        }.start();
    }
}
