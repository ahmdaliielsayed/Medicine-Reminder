package com.ahmdalii.medicinereminder.addmed.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StrengthUnitsAdapter extends RecyclerView.Adapter<StrengthUnitsAdapter.ViewHolder> {

    Context context;
    ArrayList<String> units;

    public StrengthUnitsAdapter(Context context, ArrayList<String> units) {
        this.context = context;
        this.units = units;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.add_med_strength_unit_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position;
        holder.strengthUnitTextView.setText(units.get(i));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call a method in fragment to set the text view with the new unit
            }
        });
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView getStrengthUnitTextView() {
            return strengthUnitTextView;
        }

        public void setStrengthUnitTextView(TextView strengthUnitTextView) {
            this.strengthUnitTextView = strengthUnitTextView;
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

        private TextView strengthUnitTextView;
        private ConstraintLayout constraintLayout;
        private View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;

            constraintLayout = v.findViewById(R.id.constraint_layout_add_med_strength_cell);
            strengthUnitTextView = v.findViewById(R.id.text_view_add_med_strength_cell);
        }
    }
}
