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


public class AddMedTimeFrequencyFragment extends Fragment implements View.OnClickListener {

    Bundle savedInstanceState;

    public AddMedTimeFrequencyFragment() {
        // Required empty public constructor
    }

    public static AddMedTimeFrequencyFragment newInstance(String param1, String param2) {
        AddMedTimeFrequencyFragment fragment = new AddMedTimeFrequencyFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_time_frequency, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("How much do you take this med?");



        this.savedInstanceState = savedInstanceState;

        view.findViewById(R.id.text_view_once_daily_add_med_time_frequency).setOnClickListener(this);
        view.findViewById(R.id.text_view_twice_daily_add_med_time_frequency).setOnClickListener(this);
        view.findViewById(R.id.text_view_3_times_a_day_add_med_time_frequency).setOnClickListener(this);
        view.findViewById(R.id.text_view_4_times_a_day_add_med_time_frequency).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(((TextView) v).getId() == R.id.text_view_once_daily_add_med_time_frequency) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setTimeFrequency(1);
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setTimeFrequency(1);
        }
        else if(((TextView) v).getId() == R.id.text_view_twice_daily_add_med_time_frequency) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setTimeFrequency(2);
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setTimeFrequency(2);
        }
        else if(((TextView) v).getId() == R.id.text_view_3_times_a_day_add_med_time_frequency) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setTimeFrequency(3);
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setTimeFrequency(3);
        }
        else if(((TextView) v).getId() == R.id.text_view_4_times_a_day_add_med_time_frequency) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setTimeFrequency(4);
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setTimeFrequency(4);
        }
        ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedTimesFragment());
    }
}