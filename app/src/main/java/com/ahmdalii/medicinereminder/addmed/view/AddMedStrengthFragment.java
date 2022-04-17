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

import java.util.ArrayList;


public class AddMedStrengthFragment extends Fragment {

    ArrayList<String> units = new ArrayList<String>();

    public AddMedStrengthFragment() {
        // Required empty public constructor
        units.add("gm");
        units.add("mg");
        units.add("ml");
        units.add("%");
        units.add("IU");
        units.add("IU");
        units.add("IU");
        units.add("IU");
        units.add("IU");
        units.add("IU");
        units.add("IU");
        units.add("IU");
    }

    public static AddMedStrengthFragment newInstance(String param1, String param2) {
        AddMedStrengthFragment fragment = new AddMedStrengthFragment();

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
        return inflater.inflate(R.layout.fragment_add_med_strength, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Strength");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("What strength is the med?");

        RecyclerView recyclerView = view.findViewById(R.id.strength_units_recycler_view_add_med);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        StrengthUnitsAdapter strengthUnitsAdapter = new StrengthUnitsAdapter(getContext(), units);
        recyclerView.setAdapter(strengthUnitsAdapter);


        view.findViewById(R.id.button_next_add_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedReasonFragment());
            }
        });
    }
}