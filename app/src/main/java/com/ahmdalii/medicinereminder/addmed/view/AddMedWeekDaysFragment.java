package com.ahmdalii.medicinereminder.addmed.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;


public class AddMedWeekDaysFragment extends Fragment {


    public AddMedWeekDaysFragment() {
        // Required empty public constructor
    }

    public static AddMedWeekDaysFragment newInstance(String param1, String param2) {
        AddMedWeekDaysFragment fragment = new AddMedWeekDaysFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_week_days, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Days");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("Choose days");


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_week_days_add_med);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        WeekDaysAdapter adapter = new WeekDaysAdapter(getContext());
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.button_next_add_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMedActivity) getActivity()).nextStep(savedInstanceState, new AddMedTimesFragment());
            }
        });
    }
}