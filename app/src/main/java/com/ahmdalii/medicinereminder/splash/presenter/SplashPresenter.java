package com.ahmdalii.medicinereminder.splash.presenter;

import com.ahmdalii.medicinereminder.splash.repository.SplashRepoInterface;
import com.ahmdalii.medicinereminder.splash.view.SplashFragmentInterface;

public class SplashPresenter implements SplashPresenterInterface {

    private final SplashFragmentInterface viewFragmentInterface;
    private final SplashRepoInterface repoInterface;

    public SplashPresenter(SplashFragmentInterface viewFragmentInterface, SplashRepoInterface repoInterface) {
        this.viewFragmentInterface = viewFragmentInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void isBoardingFinish() {
        viewFragmentInterface.isBoardingFinish(repoInterface.isBoardingFinish());
    }

    @Override
    public void isUserLogin() {
        viewFragmentInterface.isUserLogin(repoInterface.isUserLogin());
    }
}
