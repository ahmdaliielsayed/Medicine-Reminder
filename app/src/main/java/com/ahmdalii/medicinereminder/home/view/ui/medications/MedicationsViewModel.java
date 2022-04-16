package com.ahmdalii.medicinereminder.home.view.ui.medications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MedicationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}