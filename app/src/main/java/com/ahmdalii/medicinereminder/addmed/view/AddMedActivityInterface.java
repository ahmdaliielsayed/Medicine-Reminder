package com.ahmdalii.medicinereminder.addmed.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedPresenterInterface;

public interface AddMedActivityInterface {
    void nextStep(Bundle savedInstanceState, Fragment fragment);

    AddMedPresenterInterface getAddMedPresenter();
}
