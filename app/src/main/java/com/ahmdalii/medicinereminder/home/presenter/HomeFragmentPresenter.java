package com.ahmdalii.medicinereminder.home.presenter;

import com.ahmdalii.medicinereminder.home.repository.HomeFragmentRepoInterface;
import com.ahmdalii.medicinereminder.home.view.HomeFragmentInterface;

import java.util.Date;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface {

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
}
