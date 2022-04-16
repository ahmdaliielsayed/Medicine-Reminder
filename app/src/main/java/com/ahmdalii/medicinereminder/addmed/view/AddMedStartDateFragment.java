package com.ahmdalii.medicinereminder.addmed.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddMedStartDateFragment extends Fragment {


    public AddMedStartDateFragment() {
        // Required empty public constructor
    }

    public static AddMedStartDateFragment newInstance(String param1, String param2) {
        AddMedStartDateFragment fragment = new AddMedStartDateFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_start_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Start Date");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("When do you need to start taking this med?");

        view.findViewById(R.id.button_next_add_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).nextStep(savedInstanceState);
            }
        });

    }
}