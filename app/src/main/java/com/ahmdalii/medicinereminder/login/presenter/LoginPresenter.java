package com.ahmdalii.medicinereminder.login.presenter;

import android.app.Activity;

import com.ahmdalii.medicinereminder.login.repository.LoginRepoInterface;
import com.ahmdalii.medicinereminder.login.view.LoginFragmentInterface;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.NetworkLoginDelegate;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class LoginPresenter implements LoginPresenterInterface, NetworkLoginDelegate {

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
    public GoogleSignInClient getGoogleSignInClient(Activity activity) {
        return repoInterface.getGoogleSignInClient(activity);
    }

    @Override
    public void signInWithGoogle(String idToken) {
        repoInterface.signInWithGoogle(this, idToken);
    }

    @Override
    public void onResponse(String userId) {
        repoInterface.setUserLogin(true);
        repoInterface.setUserId(userId);
        viewFragmentInterface.hideProgressbar();
        viewFragmentInterface.navigateToHomeScreen();
    }

    @Override
    public void onResponse(User user) {
        repoInterface.insertUserToRoom(user);
        onResponse(user.getUserId());
    }

    @Override
    public void onFailure(String error) {
        viewFragmentInterface.onError(error);
        viewFragmentInterface.hideProgressbar();
    }
}
