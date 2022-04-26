package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;

import java.time.LocalDate;


public class AddMedEndDateFragment extends Fragment {

    public AddMedEndDateFragment() {
        // Required empty public constructor
    }

    public static AddMedEndDateFragment newInstance(String param1, String param2) {
        AddMedEndDateFragment fragment = new AddMedEndDateFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_end_date, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("When do you need to stop taking this med?");

        DatePicker endDatePicker = view.findViewById(R.id.date_picker_end_date_add_med);

        LocalDate startDate = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getStartDate();
        startDate = startDate.plusDays(1);
        endDatePicker.setMinDate(startDate.toEpochDay()*24*60*60*1000);

        LocalDate maxDate = startDate.plusYears(1);
        endDatePicker.setMaxDate(maxDate.toEpochDay()*24*60*60*1000);

        view.findViewById(R.id.button_submit_edit_med).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                LocalDate pickerEndDate = LocalDate.of(endDatePicker.getYear(), endDatePicker.getMonth() + 1, endDatePicker.getDayOfMonth());
                String endDate = pickerEndDate.toString();
                ((AddMedActivityInterface) getActivity()).closeKeyboard(view);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().setEndDate(pickerEndDate);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setEndDate(endDate);
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedRefillReminderFragment());
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
}