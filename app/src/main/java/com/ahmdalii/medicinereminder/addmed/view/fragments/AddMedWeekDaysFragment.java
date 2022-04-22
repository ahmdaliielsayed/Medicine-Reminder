package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.util.Pair;
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
import com.ahmdalii.medicinereminder.addmed.model.WeekDays;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;
import com.ahmdalii.medicinereminder.addmed.view.adapters.WeekDaysAdapter;

import java.util.ArrayList;


public class AddMedWeekDaysFragment extends Fragment implements AddMedWeekDaysFragmentInterface {

    ArrayList<Pair<WeekDays, Boolean>> days;

    Button nextButton;

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
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("Choose days");

        nextButton = view.findViewById(R.id.button_next_add_med);
        nextButton.setVisibility(View.GONE);


        initDaysList();

        setupRecyclerView(view);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<WeekDays> selectedDays = new ArrayList<>();
                StringBuilder selectedWeekDaysStr = new StringBuilder("");
                for(int i = 0; i < days.size(); i++) {
                    if(days.get(i).second) {
                        selectedDays.add(days.get(i).first);
                        selectedWeekDaysStr.append(days.get(i).first.getDay() + ", ");
                    }
                }
                selectedWeekDaysStr.delete(0, selectedWeekDaysStr.length() - 2);
                ((AddMedActivityInterface) getActivity()).closeKeyboard(view);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setDays(selectedDays);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setWeekDays(selectedWeekDaysStr.toString());
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedTimesFragment());
            }
        });
    }

    private void initDaysList() {
        days = new ArrayList<>();
        days.add(new Pair<>(WeekDays.SATURDAY, false));
        days.add(new Pair<>(WeekDays.SUNDAY, false));
        days.add(new Pair<>(WeekDays.MONDAY, false));
        days.add(new Pair<>(WeekDays.TUESDAY, false));
        days.add(new Pair<>(WeekDays.WEDNESDAY, false));
        days.add(new Pair<>(WeekDays.THURSDAY, false));
        days.add(new Pair<>(WeekDays.FRIDAY, false));
    }

    private void setupRecyclerView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_week_days_add_med);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        WeekDaysAdapter adapter = new WeekDaysAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
    }

    public void setDay(int index, boolean isSelected) {
        days.set(index, new Pair(days.get(index).first, isSelected));
        if(isAnyDaySelected()) {
            nextButton.setVisibility(View.VISIBLE);
        }
        else {
            nextButton.setVisibility(View.GONE);
        }
    }

    private boolean isAnyDaySelected() {
        for(int i = 0; i < days.size(); i++) {
            if(days.get(i).second) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Pair<WeekDays, Boolean>> getDays() {
        return days;
    }

}