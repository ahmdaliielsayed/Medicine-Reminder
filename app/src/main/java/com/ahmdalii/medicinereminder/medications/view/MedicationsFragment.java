package com.ahmdalii.medicinereminder.medications.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivity;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsSectionPojo;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.MedicineUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MedicationsFragment extends Fragment {

    List<MedicationsPojo> activeData;
    List<MedicationsPojo> inactiveData;
    List<MedicationsSectionPojo> data;
    RecyclerView recyclerView;

    Button addMedBtn;

    public MedicationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewId);
        addMedBtn = view.findViewById(R.id.addMedId);

        //testNavigationToDisplay(view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        activeData = Arrays.asList(new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Lopid", "5 mg", 5, R.drawable.temppill));
        inactiveData = Arrays.asList(new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Plavix", "7 mg", 9, R.drawable.temppill));
        data =  Arrays.asList(new MedicationsSectionPojo("Active meds", activeData), new MedicationsSectionPojo("Inactive meds", inactiveData));

        MedicationsMainAdapter mainAdapter = new MedicationsMainAdapter(data);
        recyclerView.setAdapter(mainAdapter);

        addMedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("emy", "onClick: you clicked");
                startActivity(new Intent(getContext(), AddMedActivity.class));
            }
        });

    }

    private void testNavigationToDisplay(@NonNull View view) {
        Bundle args = new Bundle();
        Medicine medicine = new Medicine();
        medicine.setName("Panadol");
        medicine.setUserID("asdf");
        medicine.setSync(true);
        medicine.setActivated(true);
        medicine.setInstructions("Before eating");
        medicine.setReminderMedAmount(2);
        medicine.setReason("Covid");
        medicine.setStartDate("2022-04-19");
        medicine.setStrength(2);
        medicine.setUnit(MedicineUnit.g.getUnit());
        medicine.setRefillReminderTime("20:15:00");
        medicine.setForm(MedicineForm.INHALER.getForm());
        medicine.setEndDate("2022-05-19");
        medicine.setRemainingMedAmount(20);
        medicine.setDayFrequency(MedicineDayFrequency.SPECIFIC_DAYS.getFrequency());
        medicine.setWeekDays("saturday, wednesday, friday");
        medicine.setId("-N07sFZYrJYuGhYN3T-c");

        args.putSerializable("medicine", medicine);
        Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_displayMedFragment, args);
    }
}