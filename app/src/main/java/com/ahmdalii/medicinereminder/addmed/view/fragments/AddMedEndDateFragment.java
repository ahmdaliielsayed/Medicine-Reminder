package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AddMedEndDateFragment extends Fragment {

    public AddMedEndDateFragment() {
        // Required empty public constructor
    }

    public static AddMedEndDateFragment newInstance(String param1, String param2) {
        AddMedEndDateFragment fragment = new AddMedEndDateFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_end_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication End Date");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("When do you need to stop taking this med?");

        DatePicker endDatePicker = view.findViewById(R.id.date_picker_end_date_add_med);


        view.findViewById(R.id.button_next_add_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date pickerEndDate = new Date(endDatePicker.getYear() - 1900, endDatePicker.getMonth(), endDatePicker.getDayOfMonth());
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                String endDate = dateFormat.format(pickerEndDate);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setEndDate(endDate);
                Log.i("TAG", "onClick: " + ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getStartDate() + "---------------" + ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getEndDate());
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedRefillReminderFragment());
            }
        });
    }
}