package com.ahmdalii.medicinereminder.addmed.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class AddMedActivity extends AppCompatActivity implements AddMedActivityInterface {

    StepView stepper;

    FragmentManager manager;
    ArrayList<Fragment> fragments;
    Fragment activeFragment;
    FragmentTransaction transaction;

    int stepIndex = 0;
    int stepsNumber = 11;

    Medicine medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        medicine = new Medicine();

        Toolbar toolbar = findViewById(R.id.toolbar_add_med);

        setSupportActionBar(null);
        setSupportActionBar(toolbar);

        stepper = findViewById(R.id.stepper_add_med);

        fragments = new ArrayList<>();
        fragments.add(new AddMedNameFragment());

        manager = getSupportFragmentManager();
        stepper.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(stepsNumber)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
    }

    public void nextStep(Bundle savedInstanceState, Fragment fragment) {
        if(savedInstanceState == null) {
            stepIndex++;
            stepper.setStepsNumber(stepsNumber);
            fragments.add(fragment);
            transaction = manager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.add(R.id.fragment_container_view_add_med, fragment, "fragment" + stepIndex);
            transaction.commit();
        }
        else {
            activeFragment = manager.findFragmentByTag("fragment" + stepIndex);
        }
        stepper.go(stepIndex, true);
    }

    public void addMedFinished() {

    }

    public void decrementMaxNumberOfSteps() {
        stepsNumber--;
    }
}