package com.ahmdalii.medicinereminder.addmed.view.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.fragments.AddMedTimesFragmentInterface;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MedTimesAdapter extends RecyclerView.Adapter<MedTimesAdapter.ViewHolder> {


    Context context;
    int count;
    AddMedTimesFragmentInterface fragment;

//    ArrayList<Time> dosesTimes;
//    ArrayList<Integer> dosesValues;

    public MedTimesAdapter(Context context, int count, AddMedTimesFragmentInterface fragment) {
        this.context = context;
        this.count = count;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.med_time_row, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position;
        if(i == 0) {
            holder.doseNumberTextView.setText("First dose");
        }
        else if(i == 1) {
            holder.doseNumberTextView.setText("Second dose");
        }
        else if(i == 2) {
            holder.doseNumberTextView.setText("Third dose");
        }
        else if(i == 3) {
            holder.doseNumberTextView.setText("Fourth dose");
        }

        holder.unitNameTextView.setText("Pills");

        holder.numberOfUnitsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) {
                    fragment.putAmount(i, null);
                } else {
                    fragment.putAmount(i, Integer.parseInt(s.toString()));

                }
            }
        });

        holder.timePicker.setIs24HourView(true);
        holder.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                fragment.putTime(i, new Time(TimeUnit.HOURS.toMillis(hourOfDay) + TimeUnit.MINUTES.toMillis(minute)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView doseNumberTextView;
        TextView unitNameTextView;
        EditText numberOfUnitsEditText;
        TimePicker timePicker;
        ConstraintLayout constraintLayout;
        View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;

            constraintLayout = v.findViewById(R.id.constraint_layout_med_time_row);
            timePicker = v.findViewById(R.id.time_picker_med_time_row_add_med);
            doseNumberTextView = v.findViewById(R.id.text_view_dose_number_med_time_row);
            unitNameTextView = v.findViewById(R.id.text_view_unit_med_time_row_add_med);
            numberOfUnitsEditText = v.findViewById(R.id.edit_text_number_med_time_row_add_med);
        }

        public TextView getDoseNumberTextView() {
            return doseNumberTextView;
        }

        public void setDoseNumberTextView(TextView doseNumberTextView) {
            this.doseNumberTextView = doseNumberTextView;
        }

        public TimePicker getTimePicker() {
            return timePicker;
        }

        public void setTimePicker(TimePicker timePicker) {
            this.timePicker = timePicker;
        }

        public TextView getUnitNameTextView() {
            return unitNameTextView;
        }

        public void setUnitNameTextView(TextView unitNameTextView) {
            this.unitNameTextView = unitNameTextView;
        }

        public EditText getNumberOfUnitsEditText() {
            return numberOfUnitsEditText;
        }

        public void setNumberOfUnitsEditText(EditText numberOfUnitsEditText) {
            this.numberOfUnitsEditText = numberOfUnitsEditText;
        }

        public ConstraintLayout getConstraintLayout() {
            return constraintLayout;
        }

        public void setConstraintLayout(ConstraintLayout constraintLayout) {
            this.constraintLayout = constraintLayout;
        }

        public View getLayout() {
            return layout;
        }

        public void setLayout(View layout) {
            this.layout = layout;
        }
    }
}
