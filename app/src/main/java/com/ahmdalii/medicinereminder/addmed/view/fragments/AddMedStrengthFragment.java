package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;
import com.ahmdalii.medicinereminder.addmed.view.adapters.StrengthUnitsAdapter;

import java.util.ArrayList;


public class AddMedStrengthFragment extends Fragment implements AddMedStrengthFragmentInterface {

    ArrayList<String> units;
    Integer strength;
    String unit;
    TextView unitTextView;

    public AddMedStrengthFragment() {
        // Required empty public constructor
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

        units = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getUnits();
        unit = units.get(0);
        setupRecyclerView(view);

        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("What strength is the med?");
        unitTextView = view.findViewById(R.id.text_view_unit_add_med_strength);
        EditText strengthEditText = view.findViewById(R.id.edit_text_strength_add_med);
        Button nextButton = view.findViewById(R.id.button_next_add_med);

        nextButton.setVisibility(View.GONE);

        unitTextView.setText(unit);

        strengthEditText.addTextChangedListener(new TextWatcher() {
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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strength = Integer.parseInt(strengthEditText.getText().toString());
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setUnit(unit);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setStrength(strength);
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedReasonFragment());
            }
        });
    }

    private void setupRecyclerView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.strength_units_recycler_view_add_med);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        StrengthUnitsAdapter strengthUnitsAdapter = new StrengthUnitsAdapter(getContext(), units, this);
        recyclerView.setAdapter(strengthUnitsAdapter);
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
        unitTextView.setText(unit);
    }
}