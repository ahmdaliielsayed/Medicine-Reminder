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

public class AddMedTimesFragment extends Fragment {


    public AddMedTimesFragment() {
        // Required empty public constructor
    }

    public static AddMedTimesFragment newInstance(String param1, String param2) {
        AddMedTimesFragment fragment = new AddMedTimesFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Times");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("When do you need to take this med?");

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_med_times_add_med);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MedTimesAdapter medTimesAdapter = new MedTimesAdapter(getContext(), 4);
        recyclerView.setAdapter(medTimesAdapter);

        view.findViewById(R.id.button_next_add_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMedActivity) getActivity()).nextStep(savedInstanceState, new AddMedStartDateFragment());
            }
        });

    }
}