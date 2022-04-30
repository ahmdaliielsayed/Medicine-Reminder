package com.ahmdalii.medicinereminder.addmed.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedPresenter;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedPresenterInterface;
import com.ahmdalii.medicinereminder.addmed.view.fragments.AddMedNameFragment;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class AddMedActivity extends AppCompatActivity implements AddMedActivityInterface, AddMedView {

    private AddMedPresenterInterface addMedPresenter;

    StepView stepper;
    ProgressDialog dialog;

    FragmentManager manager;
    ArrayList<Fragment> fragments;
    Fragment activeFragment;
    FragmentTransaction transaction;

    int stepIndex = 0;
    int stepsNumber = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        addMedPresenter = new AddMedPresenter(this);

        stepper = findViewById(R.id.stepper_add_med);
        initToolbar();
        manager = getSupportFragmentManager();
        initFragmentsList();
        initStepper();
    }

    private void initStepper() {
        stepper.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(stepsNumber)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
    }

    private void initFragmentsList() {
        fragments = new ArrayList<>();
        fragments.add(new AddMedNameFragment());
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_display_med);

        setSupportActionBar(null);
        setSupportActionBar(toolbar);
    }

    public void nextStep(Bundle savedInstanceState, Fragment fragment) {
        if(savedInstanceState == null) {
            stepIndex++;
            activeFragment = fragment;
            fragments.add(fragment);
            transaction = manager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.add(R.id.fragment_container_view_add_med, fragment, "fragment" + stepIndex);
            transaction.commit();
            stepper.setStepsNumber(stepsNumber);
        }
        else {
            activeFragment = manager.findFragmentByTag("fragment" + stepIndex);
        }
        stepper.go(stepIndex, true);
    }

    public void prevStep(Fragment fragment) {
        stepIndex--;
        activeFragment = fragment;
        fragments.remove(fragment);
        activeFragment = fragments.get(fragments.size() - 1);
        transaction = manager.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.remove(fragment);
        transaction.commit();
        stepper.go(stepIndex, true);
        stepper.setStepsNumber(stepsNumber);
    }


    @Override
    public AddMedPresenterInterface getAddMedPresenter() {
        return addMedPresenter;
    }

    @Override
    public void onBackPressed() {
        if(stepIndex != 0) {
            prevStep(activeFragment);
        }
        else {
            finish();
        }
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void closeKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.show();
    }

    @Override
    public void hideProgressDialog() {
        dialog.dismiss();
    }
}