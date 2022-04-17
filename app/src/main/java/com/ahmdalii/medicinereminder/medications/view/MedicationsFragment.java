package com.ahmdalii.medicinereminder.medications.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsSectionPojo;

import java.util.Arrays;
import java.util.List;


public class MedicationsFragment extends Fragment {

    List<MedicationsPojo> activeData;
    List<MedicationsPojo> inactiveData;
    List<MedicationsSectionPojo> data;
    RecyclerView recyclerView;

    public MedicationsFragment() {
        // Required empty public constructor
        //hello from fragment
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        activeData = Arrays.asList(new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Lopid", "5 mg", 5, R.drawable.temppill));
        inactiveData = Arrays.asList(new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Plavix", "7 mg", 9, R.drawable.temppill));
        data =  Arrays.asList(new MedicationsSectionPojo("Active meds", activeData), new MedicationsSectionPojo("Inactive meds", inactiveData));

        MedicationsMainAdapter mainAdapter = new MedicationsMainAdapter(data);
        recyclerView.setAdapter(mainAdapter);

    }
}