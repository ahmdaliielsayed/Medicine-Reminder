package com.ahmdalii.medicinereminder.splash.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.home.view.HomeActivity;
import com.ahmdalii.medicinereminder.splash.presenter.SplashPresenter;
import com.ahmdalii.medicinereminder.splash.presenter.SplashPresenterInterface;
import com.ahmdalii.medicinereminder.splash.repository.SplashRepo;

import java.util.Objects;

public class SplashFragment extends Fragment implements SplashFragmentInterface {

    private SplashPresenterInterface presenterInterface;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        presenterInterface = new SplashPresenter(this, SplashRepo.getInstance(view.getContext()));
        presenterInterface.isBoardingFinish();
    }

    @Override
    public void isBoardingFinish(boolean isFinish) {
        new Handler().postDelayed(() -> {
            if (isFinish) {
                presenterInterface.isUserLogin();
            } else {
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_viewPagerFragment);
            }
        }, 3000);
    }

    @Override
    public void isUserLogin(boolean isLogin) {
        if (isLogin) {
            startActivity(new Intent(getActivity(), HomeActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        } else {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
        }
    }
}