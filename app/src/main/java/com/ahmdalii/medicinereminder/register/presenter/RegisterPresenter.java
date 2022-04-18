package com.ahmdalii.medicinereminder.register.presenter;

import android.net.Uri;

import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkRegisterDelegate;
import com.ahmdalii.medicinereminder.network.NetworkImageProfileDelegate;
import com.ahmdalii.medicinereminder.register.repository.RegisterRepoInterface;
import com.ahmdalii.medicinereminder.register.view.RegisterFragmentInterface;

public class RegisterPresenter implements RegisterPresenterInterface, NetworkImageProfileDelegate, NetworkRegisterDelegate {

    private final RegisterFragmentInterface viewFragmentInterface;
    private final RegisterRepoInterface repoInterface;

    private String name, email, password, profileImageURI;

    public RegisterPresenter(RegisterFragmentInterface viewFragmentInterface, RegisterRepoInterface repoInterface) {
        this.viewFragmentInterface = viewFragmentInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void uploadProfileImage(Uri uriProfileImage) {
        repoInterface.uploadProfileImage(this, uriProfileImage);
    }

    @Override
    public void createUserOnFirebase(String name, String email, String password, String profileImageURI) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImageURI = profileImageURI;
        repoInterface.createUserOnFirebase(this, name, email, password, profileImageURI);
    }

    @Override
    public void onResponseSuccess(String uri) {
        viewFragmentInterface.setProfileImageURI(uri);
        viewFragmentInterface.hideImgProgressbar();
    }

    @Override
    public void onFailureResult(String error) {
        viewFragmentInterface.onImgUploadError(error);
        viewFragmentInterface.hideImgProgressbar();
    }

    @Override
    public void onResponse(String userId) {
        repoInterface.insertUserToRoom(new User(userId, name, email, password, profileImageURI, null));
        repoInterface.setUserLogin(true);
        viewFragmentInterface.hideProgressbar();
        viewFragmentInterface.navigateToHomeScreen();
    }

    @Override
    public void onFailure(String error) {
        viewFragmentInterface.onError(error);
        viewFragmentInterface.hideProgressbar();
    }
}
