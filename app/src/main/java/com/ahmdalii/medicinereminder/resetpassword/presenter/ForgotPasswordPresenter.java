package com.ahmdalii.medicinereminder.resetpassword.presenter;

import com.ahmdalii.medicinereminder.network.NetworkDelegate;
import com.ahmdalii.medicinereminder.resetpassword.repository.ForgotPasswordRepoInterface;
import com.ahmdalii.medicinereminder.resetpassword.view.ForgotPasswordFragmentInterface;

public class ForgotPasswordPresenter implements ForgotPasswordPresenterInterface, NetworkDelegate {

    private final ForgotPasswordFragmentInterface viewFragmentInterface;
    private final ForgotPasswordRepoInterface repoInterface;

    public ForgotPasswordPresenter(ForgotPasswordFragmentInterface viewFragmentInterface, ForgotPasswordRepoInterface repoInterface) {
        this.viewFragmentInterface = viewFragmentInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void sendPasswordResetEmail(String email) {
        repoInterface.sendPasswordResetEmail(this, email);
    }

    @Override
    public void onResponse() {
        viewFragmentInterface.linkSentSuccessfully();
        viewFragmentInterface.hideProgressbar();
        viewFragmentInterface.navigateToLoginScreen();
    }

    @Override
    public void onFailure(String error) {
        viewFragmentInterface.onError(error);
        viewFragmentInterface.hideProgressbar();
    }
}
