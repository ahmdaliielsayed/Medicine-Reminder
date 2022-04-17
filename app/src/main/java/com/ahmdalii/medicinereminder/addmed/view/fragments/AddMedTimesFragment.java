package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;
import com.ahmdalii.medicinereminder.addmed.view.adapters.MedTimesAdapter;

import java.sql.Time;
import java.util.ArrayList;

public class AddMedTimesFragment extends Fragment implements AddMedTimesFragmentInterface {

    private int timeFrequency = 1;
    private ArrayList<Integer> amounts;

    Button nextButton;

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
        nextButton = view.findViewById(R.id.button_next_add_med);
        nextButton.setVisibility(View.GONE);

        MedicineDayFrequency dayFrequency = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getDayFrequency();
        if(dayFrequency == MedicineDayFrequency.EVERYDAY) {
            timeFrequency = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getTimeFrequency();
        }

        initDoseAmount();

        setupRecyclerView(view);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedStartDateFragment());
            }
        });

    }

    private void initDoseAmount() {
        amounts = new ArrayList<>(timeFrequency);
        for(int i = 0; i < timeFrequency; i++) {
            amounts.add(null);
        }
    }


    private void setupRecyclerView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_med_times_add_med);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MedTimesAdapter medTimesAdapter = new MedTimesAdapter(getContext(), timeFrequency, this);
        recyclerView.setAdapter(medTimesAdapter);
    }


    @Override
    public void putAmount(int index, Integer amount) {
        amounts.set(index, amount);
        if(!isAllAmountsSet()) {
            nextButton.setVisibility(View.GONE);
        }
        else {
            nextButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void putTime(int index, Time time) {
        ((AddMedActivityInterface) getActivity()).getAddMedPresenter().putTime(index, time);
    }

    private boolean isAllAmountsSet() {
        for(int i = 0; i < amounts.size(); i++) {
            if(amounts.get(i) == null) {
                return false;
            }
        }
        return  true;
    }
}