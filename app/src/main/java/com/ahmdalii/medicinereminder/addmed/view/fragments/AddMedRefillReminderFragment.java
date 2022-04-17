package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;


public class AddMedRefillReminderFragment extends Fragment {

    public AddMedRefillReminderFragment() {
        // Required empty public constructor
    }

    public static AddMedRefillReminderFragment newInstance(String param1, String param2) {
        AddMedRefillReminderFragment fragment = new AddMedRefillReminderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_med_refill_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Refill Reminder");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("Medication Refill Reminder");

        view.findViewById(R.id.button_next_add_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedInstructionsFragment());
            }
        });
    }
}