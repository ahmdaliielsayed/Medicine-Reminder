package com.ahmdalii.medicinereminder.home.presenter;

import android.content.Context;

import com.ahmdalii.medicinereminder.home.repository.HomeRepoInterface;
import com.ahmdalii.medicinereminder.home.view.HomeActivityInterface;

public class HomePresenter implements HomePresenterInterface {

    private final HomeActivityInterface viewInterface;
    private final HomeRepoInterface repoInterface;

    public HomePresenter(HomeActivityInterface viewInterface, HomeRepoInterface repoInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void getUserFromRoom(Context context) {
        viewInterface.displayUserInformation(repoInterface.getUserFromRoom(context));
    }

    @Override
    public void signOut(Context context) {
        repoInterface.signOut(context);
        viewInterface.navigateToLoginScreen();
    }
}
