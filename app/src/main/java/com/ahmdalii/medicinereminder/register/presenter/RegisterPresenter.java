package com.ahmdalii.medicinereminder.register.presenter;

import android.net.Uri;

import com.ahmdalii.medicinereminder.network.NetworkImageProfileDelegate;
import com.ahmdalii.medicinereminder.register.repository.RegisterRepoInterface;
import com.ahmdalii.medicinereminder.register.view.RegisterFragmentInterface;

public class RegisterPresenter implements RegisterPresenterInterface, NetworkImageProfileDelegate {

    private RegisterFragmentInterface viewFragmentInterface;
    private RegisterRepoInterface repoInterface;

    public RegisterPresenter(RegisterFragmentInterface viewFragmentInterface, RegisterRepoInterface repoInterface) {
        this.viewFragmentInterface = viewFragmentInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void uploadProfileImage(Uri uriProfileImage) {
        repoInterface.uploadProfileImage(uriProfileImage);
    }

    @Override
    public void onResponseSuccess(String uri) {

    }

    @Override
    public void onFailureResult(String error) {

    }
}
