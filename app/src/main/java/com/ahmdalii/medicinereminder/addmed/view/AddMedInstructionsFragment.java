package com.ahmdalii.medicinereminder.addmed.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;


public class AddMedInstructionsFragment extends Fragment implements View.OnClickListener {

    Bundle savedInstanceState;

    public AddMedInstructionsFragment() {
        // Required empty public constructor
    }

    public static AddMedInstructionsFragment newInstance(String param1, String param2) {
        AddMedInstructionsFragment fragment = new AddMedInstructionsFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_instructions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        this.savedInstanceState = savedInstanceState;

        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Refill Reminder");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("Medication Refill Reminder");



        view.findViewById(R.id.text_view_before_eating_add_med_instructions).setOnClickListener(this);
        view.findViewById(R.id.text_view_while_eating_add_med_instructions).setOnClickListener(this);
        view.findViewById(R.id.text_view_after_eating_add_med_instructions).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.text_view_before_eating_add_med_instructions) {

        }
        else if(v.getId() == R.id.text_view_while_eating_add_med_instructions) {

        }
        else if(v.getId() == R.id.text_view_after_eating_add_med_instructions) {

        }

        ((AddMedActivity) getActivity()).addMedFinished();
    }
}