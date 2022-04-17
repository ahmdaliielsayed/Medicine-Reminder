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


public class AddMedFormFragment extends Fragment implements View.OnClickListener {


    Bundle savedInstanceState;

    public AddMedFormFragment() {
        // Required empty public constructor
    }


    public static AddMedFormFragment newInstance(String param1, String param2) {
        AddMedFormFragment fragment = new AddMedFormFragment();
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

        return inflater.inflate(R.layout.fragment_add_med_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Form");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("What form is the med?");
        this.savedInstanceState = savedInstanceState;

        view.findViewById(R.id.text_view_pills_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_solution_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_injection_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_powder_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_drops_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_inhaler_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_topical_add_med_form).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(((TextView) v).getId() == R.id.text_view_pills_add_med_form) {

        }
        else if(((TextView) v).getId() == R.id.text_view_solution_add_med_form) {

        }
        else if(((TextView) v).getId() == R.id.text_view_injection_add_med_form) {

        }
        else if(((TextView) v).getId() == R.id.text_view_powder_add_med_form) {

        }
        else if(((TextView) v).getId() == R.id.text_view_drops_add_med_form) {

        }
        else if(((TextView) v).getId() == R.id.text_view_inhaler_add_med_form) {

        }
        else if(((TextView) v).getId() == R.id.text_view_topical_add_med_form) {

        }
        ((AddMedActivity) getActivity()).nextStep(savedInstanceState, new AddMedStrengthFragment());
    }
}