package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;
import com.ahmdalii.medicinereminder.addmed.view.adapters.MedTimesAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddMedTimesFragment extends Fragment implements AddMedTimesFragmentInterface {

    private int timeFrequency = 1;
    private ArrayList<Integer> amounts;
    private ArrayList<LocalDateTime> times;

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
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("When do you need to take this med?");
        nextButton = view.findViewById(R.id.button_submit_edit_med);
        nextButton.setVisibility(View.GONE);

        MedicineDayFrequency dayFrequency = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getDayFrequency();
        if(dayFrequency == MedicineDayFrequency.EVERYDAY) {
            timeFrequency = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getTimeFrequency();
        }

        initDoseAmount();

        setupRecyclerView(view);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                times = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getTimes();
                setUnchangedTimes();
                ((AddMedActivityInterface) getActivity()).closeKeyboard(view);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setAmounts(amounts);
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedStartDateFragment());
            }
        });

        if(((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getForm().equals(MedicineForm.PILLS.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_pills);
        }
        else if(((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getForm().equals(MedicineForm.SOLUTION.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_solution);
        }
        else if(((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getForm().equals(MedicineForm.DROPS.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_drops);
        }
        else if(((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getForm().equals(MedicineForm.INHALER.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_inhaler);
        }
        else if(((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getForm().equals(MedicineForm.TOPICAL.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_topical);
        }
        else if(((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getForm().equals(MedicineForm.POWDER.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_powder);
        }
        else {
            ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_injection);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUnchangedTimes() {
        for(int i = 0; i < times.size(); i++) {
            if(times.get(i) == null) {
                times.set(i, LocalDateTime.now());
            }
        }
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
    public void putTime(int index, LocalDateTime time) {
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