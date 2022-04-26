package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;
import com.ahmdalii.medicinereminder.model.MedicineUnit;


public class AddMedFormFragment extends Fragment implements View.OnClickListener {


    Bundle savedInstanceState;
    String medForm;

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
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("What form is the med?");
        this.savedInstanceState = savedInstanceState;

        view.findViewById(R.id.text_view_pills_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_solution_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_injection_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_powder_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_drops_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_inhaler_add_med_form).setOnClickListener(this);
        view.findViewById(R.id.text_view_topical_add_med_form).setOnClickListener(this);

        ((ImageView) view.findViewById(R.id.image_view_add_header)).setImageResource(R.drawable.ic_pills);

    }

    @Override
    public void onClick(View v) {
        if(((TextView) v).getId() == R.id.text_view_pills_add_med_form) {
            medForm = MedicineForm.PILLS.getForm();
            addUnits(MedicineForm.PILLS);
        }
        else if(((TextView) v).getId() == R.id.text_view_solution_add_med_form) {
            medForm = MedicineForm.SOLUTION.getForm();
            addUnits(MedicineForm.SOLUTION);
        }
        else if(((TextView) v).getId() == R.id.text_view_injection_add_med_form) {
            medForm = MedicineForm.INJECTION.getForm();
            addUnits(MedicineForm.INJECTION);
        }
        else if(((TextView) v).getId() == R.id.text_view_powder_add_med_form) {
            medForm = MedicineForm.POWDER.getForm();
            addUnits(MedicineForm.POWDER);
        }
        else if(((TextView) v).getId() == R.id.text_view_drops_add_med_form) {
            medForm = MedicineForm.DROPS.getForm();
            addUnits(MedicineForm.DROPS);
        }
        else if(((TextView) v).getId() == R.id.text_view_inhaler_add_med_form) {
            medForm = MedicineForm.INHALER.getForm();
            addUnits(MedicineForm.INHALER);
        }
        else if(((TextView) v).getId() == R.id.text_view_topical_add_med_form) {
            medForm = MedicineForm.TOPICAL.getForm();
            addUnits(MedicineForm.TOPICAL);
        }
        ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setForm(medForm);
        ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedStrengthFragment());
    }

    private void addUnits(MedicineForm form) {
        if(form == MedicineForm.PILLS) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.g.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.IU.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mEq.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mg.getUnit());
        }
        else if(form == MedicineForm.SOLUTION) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.g.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.IU.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mEq.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.ml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.percent.getUnit());
        }
        else if(form == MedicineForm.INJECTION) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.IU.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mEq.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.ml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.percent.getUnit());
        }
        else if(form == MedicineForm.POWDER) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.g.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mg.getUnit());
            }
        else if(form == MedicineForm.DROPS) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.IU.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mEq.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.percent.getUnit());
        }
        else if(form == MedicineForm.INHALER) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mgperml.getUnit());
        }
        else if(form == MedicineForm.TOPICAL) {
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.g.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mg.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mcgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.mgperml.getUnit());
            ((AddMedActivityInterface) getActivity()).getAddMedPresenter().addUnit(MedicineUnit.percent.getUnit());
        }
    }
}