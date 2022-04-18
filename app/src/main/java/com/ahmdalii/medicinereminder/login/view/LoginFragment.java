package com.ahmdalii.medicinereminder.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.UIHelper;
import com.ahmdalii.medicinereminder.home.view.HomeActivity;
import com.ahmdalii.medicinereminder.login.presenter.LoginPresenter;
import com.ahmdalii.medicinereminder.login.presenter.LoginPresenterInterface;
import com.ahmdalii.medicinereminder.login.repository.LoginRepo;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginFragment extends Fragment implements LoginFragmentInterface {

    private View view;
    private LoginPresenterInterface presenterInterface;

    private TextView txtViewRegister, txtViewForgotPassword;
    private ProgressBar progressBar;

    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private Button btnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        initComponents();

        txtViewRegister.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_loginFragment_to_registerFragment));
        txtViewForgotPassword.setOnClickListener(view13 -> Navigation.findNavController(view13).navigate(R.id.action_loginFragment_to_forgotPasswordFragment));
        btnLogin.setOnClickListener(view12 -> {
            if (checkInputsData()) {
                progressBar.setVisibility(View.VISIBLE);
                presenterInterface.signInWithEmailAndPassword(
                        Objects.requireNonNull(textInputEditTextEmail.getText()).toString().trim(),
                        Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim());
            }
        });
    }

    private void initComponents() {
        presenterInterface = new LoginPresenter(this,
                LoginRepo.getInstance(FirebaseClient.getInstance(), view.getContext()));

        txtViewRegister = view.findViewById(R.id.txtViewRegister);
        txtViewForgotPassword = view.findViewById(R.id.txtViewForgotPassword);
        progressBar = view.findViewById(R.id.progressBar);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
    }

    private boolean checkInputsData() {
        boolean validInputs = true;

        if (Objects.requireNonNull(textInputEditTextEmail.getText()).toString().trim().isEmpty()) {
            textInputEditTextEmail.setError(getText(R.string.type_your_email));
            textInputEditTextEmail.requestFocus();
            validInputs = false;
        } else if (Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim().isEmpty()) {
            textInputEditTextPassword.setError(getText(R.string.type_your_password));
            textInputEditTextPassword.requestFocus();
            validInputs = false;
        }

        return validInputs;
    }

    @Override
    public void navigateToHomeScreen() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void onError(String error) {
        UIHelper.showAlert(view.getContext(), R.string.error, error, R.drawable.error_icon);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }
}