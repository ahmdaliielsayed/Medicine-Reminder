package com.ahmdalii.medicinereminder.splash.view.onboarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.splash.view.onboarding.screens.FirstScreenFragment;
import com.ahmdalii.medicinereminder.splash.view.onboarding.screens.SecondScreenFragment;
import com.ahmdalii.medicinereminder.splash.view.onboarding.screens.ThirdScreenFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewPagerFragment extends Fragment {

    ArrayList<Fragment> fragmentArrayList;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentArrayList = new ArrayList<>(Arrays.asList(
                new FirstScreenFragment(),
                new SecondScreenFragment(),
                new ThirdScreenFragment()));

        viewPagerAdapter = new ViewPagerAdapter(fragmentArrayList, requireActivity().getSupportFragmentManager(), getLifecycle());

        viewPager2 = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        viewPager2.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
//            tab.setText("Tab " + (position + 1));
        }).attach();
    }
}