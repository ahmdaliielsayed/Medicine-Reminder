package com.ahmdalii.medicinereminder.db.room.timescheduler;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.db.room.AppDataBase;
import com.ahmdalii.medicinereminder.model.TimeScheduler;

import java.util.List;

public class ConcreteLocalSourceTimeScheduler implements LocalSourceTimeScheduler {

    private static ConcreteLocalSourceTimeScheduler instance = null;
    private TimeSchedulerDAO dao;
    private LiveData<List<TimeScheduler>> storedTimeSchedulersLiveData;

    private ConcreteLocalSourceTimeScheduler(Context context) {
        AppDataBase database = AppDataBase.getInstance(context.getApplicationContext());
        dao = database.timeSchedulerDAO();
        storedTimeSchedulersLiveData = dao.getAllTimeSchedulers();
    }

    public static ConcreteLocalSourceTimeScheduler getInstance(Context context) {
        if(instance == null) {
            instance = new ConcreteLocalSourceTimeScheduler(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public LiveData<List<TimeScheduler>> getAllTimeSchedulers() {
        return storedTimeSchedulersLiveData;
    }

    @Override
    public void insertTimeScheduler(TimeScheduler timeScheduler) {
        new Thread() {
            @Override
            public void run() {
                dao.insertTimeScheduler(timeScheduler);
            }
        }.start();
    }

    @Override
    public void deleteTimeScheduler(TimeScheduler timeScheduler) {
        new Thread() {
            @Override
            public void run() {
                dao.deleteTimeScheduler(timeScheduler);
            }
        }.start();
    }
}
