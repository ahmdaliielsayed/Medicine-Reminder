package com.ahmdalii.medicinereminder.addmed.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivityInterface;
import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class AddMedRefillReminderFragment extends Fragment {

    EditText remainingAmountEditText;
    EditText refillAmountEditText;
    LocalDateTime time;

    public AddMedRefillReminderFragment() {
        // Required empty public constructor
    }

    public static AddMedRefillReminderFragment newInstance(String param1, String param2) {
        AddMedRefillReminderFragment fragment = new AddMedRefillReminderFragment();
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
        return inflater.inflate(R.layout.fragment_add_med_refill_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String toolbarTitle = ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().getName();
        ((TextView) view.findViewById(R.id.text_view_toolbar_title)).setText(toolbarTitle);
        ((TextView) view.findViewById(R.id.text_view_add_header)).setText("Medication Refill Reminder");
        remainingAmountEditText = view.findViewById(R.id.edit_text_remaining_amount_refill_reminder_add_med);
        refillAmountEditText = view.findViewById(R.id.edit_text_remind_me_when_refill_reminder_add_med);
        TimePicker timePicker = view.findViewById(R.id.time_picker_refill_reminder_add_med);
        Button nextButton = view.findViewById(R.id.button_next_add_med);
        nextButton.setVisibility(View.GONE);

        timePicker.setIs24HourView(true);



        remainingAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isFormValid()) {
                    nextButton.setVisibility(View.VISIBLE);
                }
                else {
                    nextButton.setVisibility(View.GONE);
                }
            }
        });

        refillAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isFormValid()) {
                    nextButton.setVisibility(View.VISIBLE);
                }
                else {
                    nextButton.setVisibility(View.GONE);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int remainingAmount = Integer.parseInt(remainingAmountEditText.getText().toString());
                int refillAmount = Integer.parseInt(refillAmountEditText.getText().toString());

                time = LocalDateTime.of(LocalDate.now(), LocalTime.of(timePicker.getHour(), timePicker.getMinute(), 0));

                ((AddMedActivityInterface) getActivity()).closeKeyboard(view);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setRemainingMedAmount(remainingAmount);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setReminderMedAmount(refillAmount);
                ((AddMedActivityInterface) getActivity()).getAddMedPresenter().getMedicine().setRefillReminderTime(time.toString());
                ((AddMedActivityInterface) getActivity()).nextStep(savedInstanceState, new AddMedInstructionsFragment());
            }
        });
    }

    private boolean isFormValid() {
        if(remainingAmountEditText.getText().toString().length() != 0 &&
                refillAmountEditText.getText().toString().length() != 0) {
            return true;
        }
        return false;
    }
}