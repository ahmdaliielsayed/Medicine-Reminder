package com.ahmdalii.medicinereminder.addmed.view;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedPresenterInterface;

public interface AddMedActivityInterface {
    void nextStep(Bundle savedInstanceState, Fragment fragment);

    AddMedPresenterInterface getAddMedPresenter();
    void closeKeyboard(View view);
}
