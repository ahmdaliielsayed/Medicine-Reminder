package com.ahmdalii.medicinereminder.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.UIHelper;
import com.ahmdalii.medicinereminder.db.room.user.ConcreteLocalSourceUser;
import com.ahmdalii.medicinereminder.home.view.HomeActivity;
import com.ahmdalii.medicinereminder.login.presenter.LoginPresenter;
import com.ahmdalii.medicinereminder.login.presenter.LoginPresenterInterface;
import com.ahmdalii.medicinereminder.login.repository.LoginRepo;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginFragment extends Fragment implements LoginFragmentInterface {

    private View view;
    private LoginPresenterInterface presenterInterface;

    private TextView txtViewRegister, txtViewForgotPassword;
    private ProgressBar progressBar;

    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private Button btnLogin;
    private ImageView imgViewGooglePlus, imgViewFacebook;

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
        imgViewGooglePlus.setOnClickListener(view14 -> {
            Intent signInIntent = presenterInterface.getGoogleSignInClient(getActivity()).getSignInIntent();
            signInResultLauncher.launch(signInIntent);
        });
        imgViewFacebook.setOnClickListener(view15 -> Toast.makeText(view15.getContext(), R.string.coming_soon, Toast.LENGTH_LONG).show());
    }

    private final ActivityResultLauncher<Intent> signInResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // here we will handle the result of our intent
                if (result.getResultCode() == Activity.RESULT_OK) {
                    progressBar.setVisibility(View.VISIBLE);
                    Task<GoogleSignInAccount> signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    try {
                        GoogleSignInAccount account = signedInAccountFromIntent.getResult(ApiException.class);
                        presenterInterface.signInWithGoogle(account.getIdToken());
                    } catch (ApiException e) {
                        UIHelper.showAlert(view.getContext(), R.string.error, getString(R.string.google_sign_in_failed) + e.getMessage(), R.drawable.error_icon);
                        e.printStackTrace();
                    }
                } else {
                    // cancelled
                    Toast.makeText(view.getContext(), String.valueOf(result.getResultCode()), Toast.LENGTH_SHORT).show();
                }
            }
    );

    private void initComponents() {
        presenterInterface = new LoginPresenter(this,
                LoginRepo.getInstance(FirebaseClient.getInstance(), ConcreteLocalSourceUser.getInstance(view.getContext()), view.getContext()));

        txtViewRegister = view.findViewById(R.id.txtViewRegister);
        txtViewForgotPassword = view.findViewById(R.id.txtViewForgotPassword);
        progressBar = view.findViewById(R.id.progressBar);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        imgViewGooglePlus = view.findViewById(R.id.imgViewGooglePlus);
        imgViewFacebook = view.findViewById(R.id.imgViewFacebook);
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