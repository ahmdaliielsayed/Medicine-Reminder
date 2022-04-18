package com.ahmdalii.medicinereminder.resetpassword.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.UIHelper;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.resetpassword.presenter.ForgotPasswordPresenter;
import com.ahmdalii.medicinereminder.resetpassword.presenter.ForgotPasswordPresenterInterface;
import com.ahmdalii.medicinereminder.resetpassword.repository.ForgotPasswordRepo;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ForgotPasswordFragment extends Fragment implements ForgotPasswordFragmentInterface {

    private View view;
    private ForgotPasswordPresenterInterface presenterInterface;

    private ProgressBar progressBar;
    private TextInputEditText textInputEditTextEmail;
    private Button btnReset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        initComponents();

        btnReset.setOnClickListener(view1 -> {
            if (checkInputsData()) {
                progressBar.setVisibility(View.VISIBLE);
                presenterInterface.sendPasswordResetEmail(
                        Objects.requireNonNull(textInputEditTextEmail.getText()).toString().trim());
            }
        });
    }

    private void initComponents() {
        presenterInterface = new ForgotPasswordPresenter(this,
                ForgotPasswordRepo.getInstance(FirebaseClient.getInstance()));

        progressBar = view.findViewById(R.id.progressBar);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmail);
        btnReset = view.findViewById(R.id.btnReset);
    }

    @Override
    public void linkSentSuccessfully() {
        Toast.makeText(view.getContext(), R.string.reset_link_sent, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToLoginScreen() {
        Navigation.findNavController(view).navigate(R.id.action_forgotPasswordFragment_to_loginFragment);
    }

    @Override
    public void onError(String error) {
        UIHelper.showAlert(view.getContext(), R.string.error, error, R.drawable.error_icon);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    private boolean checkInputsData() {
        boolean validInputs = true;

        if (Objects.requireNonNull(textInputEditTextEmail.getText()).toString().trim().isEmpty()) {
            textInputEditTextEmail.setError(getText(R.string.type_your_email));
            textInputEditTextEmail.requestFocus();
            validInputs = false;
        }

        return validInputs;
    }
}