package com.ahmdalii.medicinereminder.splash.presenter;

import com.ahmdalii.medicinereminder.splash.repository.ThirdScreenRepoInterface;
import com.ahmdalii.medicinereminder.splash.view.onboarding.screens.ThirdScreenFragmentInterface;

public class ThirdScreenPresenter implements ThirdScreenPresenterInterface {

    private ThirdScreenFragmentInterface viewFragmentInterface;
    private ThirdScreenRepoInterface repoInterface;

    public ThirdScreenPresenter(ThirdScreenFragmentInterface viewFragmentInterface, ThirdScreenRepoInterface repoInterface) {
        this.viewFragmentInterface = viewFragmentInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void setBoardingFinish(boolean isFinish) {
        repoInterface.setBoardingFinish(isFinish);
        viewFragmentInterface.setBoardingFinishComplete();
    }
}
