package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.model.MedicineInstruction;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;


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
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("Medication Refill Reminder");

        view.findViewById(R.id.text_view_before_eating_add_med_instructions).setOnClickListener(this);
        view.findViewById(R.id.text_view_while_eating_add_med_instructions).setOnClickListener(this);
        view.findViewById(R.id.text_view_after_eating_add_med_instructions).setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.text_view_before_eating_add_med_instructions) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setInstructions(MedicineInstruction.BEFORE_EATING.getInstruction());
        }
        else if(v.getId() == R.id.text_view_while_eating_add_med_instructions) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setInstructions(MedicineInstruction.WHILE_EATING.getInstruction());
        }
        else if(v.getId() == R.id.text_view_after_eating_add_med_instructions) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setInstructions(MedicineInstruction.AFTER_EATING.getInstruction());
        }

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.show();

        ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addMedFinished();
    }
}