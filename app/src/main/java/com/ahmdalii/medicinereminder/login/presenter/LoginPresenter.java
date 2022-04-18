package com.ahmdalii.medicinereminder.login.presenter;

import com.ahmdalii.medicinereminder.login.repository.LoginRepoInterface;
import com.ahmdalii.medicinereminder.login.view.LoginFragmentInterface;
import com.ahmdalii.medicinereminder.network.NetworkDelegate;

public class LoginPresenter implements LoginPresenterInterface, NetworkDelegate {

    private final LoginFragmentInterface viewFragmentInterface;
    private final LoginRepoInterface repoInterface;

    public LoginPresenter(LoginFragmentInterface viewFragmentInterface, LoginRepoInterface repoInterface) {
        this.viewFragmentInterface = viewFragmentInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void signInWithEmailAndPassword(String email, String password) {
        repoInterface.signInWithEmailAndPassword(this, email, password);
    }

    @Override
    public void onResponse() {
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
