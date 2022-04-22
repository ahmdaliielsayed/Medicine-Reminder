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
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;


public class AddMedDayFrequenceFragment extends Fragment implements View.OnClickListener {

    Bundle savedInstanceState;

    public AddMedDayFrequenceFragment() {
        // Required empty public constructor
    }

    public static AddMedDayFrequenceFragment newInstance(String param1, String param2) {
        AddMedDayFrequenceFragment fragment = new AddMedDayFrequenceFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_day_frequence, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("How much do you take this med?");

        this.savedInstanceState = savedInstanceState;

        view.findViewById(R.id.text_view_everyday_add_med).setOnClickListener(this);
        view.findViewById(R.id.text_view_specific_days_of_the_week_add_med).setOnClickListener(this);
        view.findViewById(R.id.text_view_every_number_of_days_add_med).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(((TextView) v).getId() == R.id.text_view_everyday_add_med) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setDayFrequency(MedicineDayFrequency.EVERYDAY);
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setDayFrequency(MedicineDayFrequency.EVERYDAY.getFrequency());
            ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedTimeFrequencyFragment());
        }
        else if(((TextView) v).getId() == R.id.text_view_specific_days_of_the_week_add_med) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setTimeFrequency(1);
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setDayFrequency(MedicineDayFrequency.SPECIFIC_DAYS.getFrequency());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setDayFrequency(MedicineDayFrequency.SPECIFIC_DAYS);
            ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedWeekDaysFragment());
        }
        else if(((TextView) v).getId() == R.id.text_view_every_number_of_days_add_med) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setTimeFrequency(1);
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setDayFrequency(MedicineDayFrequency.EVERY_NUMBER_OF_DAYS.getFrequency());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setDayFrequency(MedicineDayFrequency.EVERY_NUMBER_OF_DAYS);
            ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedEveryNumberOfDaysFragment());
        }
    }
}