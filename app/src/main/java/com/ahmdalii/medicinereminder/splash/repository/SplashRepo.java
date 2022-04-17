package com.ahmdalii.medicinereminder.splash.repository;

import android.content.Context;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.db.sharedpreference.SharedPrefManager;

public class SplashRepo implements SplashRepoInterface {

    Context context;
    private static SplashRepo splashRepo = null;

    private SplashRepo(Context context) {
        this.context = context;
    }

    public static SplashRepo getInstance(Context context) {
        if (splashRepo == null) {
            splashRepo = new SplashRepo(context);
        }

        return splashRepo;
    }

    @Override
    public boolean isBoardingFinish() {
        return SharedPrefManager.getInstance(context, Constants.ON_BOARDING_FILE).getBooleanValue(Constants.ON_BOARDING_FINISH_KEY);
    }

    @Override
    public boolean isUserLogin() {
        return SharedPrefManager.getInstance(context, Constants.USERS_FILE).getBooleanValue(Constants.USER_LOGIN_KEY);
    }
}
