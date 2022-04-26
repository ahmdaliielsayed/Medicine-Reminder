package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;


public class AddMedReasonFragment extends Fragment {




    public AddMedReasonFragment() {
        // Required empty public constructor
    }

    public static AddMedReasonFragment newInstance(String param1, String param2) {
        AddMedReasonFragment fragment = new AddMedReasonFragment();

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
        return inflater.inflate(R.layout.fragment_add_med_reason, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("What are you taking it for?");
        EditText reasonEditText = view.findViewById(R.id.edit_text_med_reason_add_med);
        Button nextButton = view.findViewById(R.id.button_submit_edit_med);

        nextButton.setVisibility(View.GONE);

        reasonEditText.addTextChangedListener(new TextWatcher() {
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
                else{
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });

        view.findViewById(R.id.button_submit_edit_med).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMedActivityInterface) getActivity()).closeKeyboard(view);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setReason(reasonEditText.getText().toString());
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedDayFrequenceFragment());
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