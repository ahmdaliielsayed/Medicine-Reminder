package com.ahmdalii.medicinereminder.splash.repository;

import android.content.Context;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;

public class ThirdScreenRepo implements ThirdScreenRepoInterface {

    Context context;
    private static ThirdScreenRepo thirdScreenRepo = null;

    private ThirdScreenRepo(Context context) {
        this.context = context;
    }

    public static ThirdScreenRepo getInstance(Context context) {
        if (thirdScreenRepo == null) {
            thirdScreenRepo = new ThirdScreenRepo(context);
        }

        return thirdScreenRepo;
    }

    @Override
    public void setBoardingFinish(boolean isFinish) {
        SharedPrefManager.getInstance(context, Constants.ON_BOARDING_FILE).setValue(Constants.ON_BOARDING_FINISH_KEY, isFinish);
    }
}
