package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;


public class AddMedNameFragment extends Fragment {


    public AddMedNameFragment() {
        // Required empty public constructor
    }

    public static AddMedNameFragment newInstance(String param1, String param2) {
        AddMedNameFragment fragment = new AddMedNameFragment();

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
        return inflater.inflate(R.layout.fragment_add_med_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText("Medication Name");
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("What med would you like to add?");

        Button nextButton = ((Button) view.findViewById(R.id.button_next_add_med));
        nextButton.setVisibility(View.GONE);
        EditText nameEditText = ((EditText) view.findViewById(R.id.edit_text_med_name_add_med));

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() == 0) {
                    nextButton.setVisibility(View.GONE);
                }
                else {
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });

        view.findViewById(R.id.button_next_add_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setName(nameEditText.getText().toString());
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedFormFragment());
            }
        });
    }
}