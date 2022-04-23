package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;

import java.time.LocalDate;


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("When do you need to start taking this med?");

        DatePicker startDatePicker = view.findViewById(R.id.date_picker_start_date_add_med);
        startDatePicker.setMinDate(System.currentTimeMillis());

        view.findViewById(R.id.button_submit_edit_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate pickerStartDate = LocalDate.of(startDatePicker.getYear(), startDatePicker.getMonth() + 1 , startDatePicker.getDayOfMonth());
                String startDate = pickerStartDate.toString();
                ((AddMedActivityInterface) getActivity()).closeKeyboard(view);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setStartDate(pickerStartDate);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setStartDate(startDate);
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedEndDateFragment());
            }
        });

    }
}