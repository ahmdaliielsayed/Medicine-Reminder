package com.ahmdalii.medicinereminder.splash.view.onboarding.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.splash.presenter.ThirdScreenPresenter;
import com.ahmdalii.medicinereminder.splash.presenter.ThirdScreenPresenterInterface;
import com.ahmdalii.medicinereminder.splash.repository.ThirdScreenRepo;

public class ThirdScreenFragment extends Fragment implements ThirdScreenFragmentInterface {

    private ThirdScreenPresenterInterface presenterInterface;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        presenterInterface = new ThirdScreenPresenter(this, ThirdScreenRepo.getInstance(view.getContext()));

        view.findViewById(R.id.btnFinish).setOnClickListener(view1 -> {
            presenterInterface.setBoardingFinish(true);
        });
    }

    @Override
    public void setBoardingFinishComplete() {
        Navigation.findNavController(view).navigate(R.id.action_viewPagerFragment_to_loginFragment);
    }
}