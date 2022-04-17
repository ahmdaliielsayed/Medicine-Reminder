package com.ahmdalii.medicinereminder.addmed.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;

import java.util.ArrayList;

public class WeekDaysAdapter extends RecyclerView.Adapter<WeekDaysAdapter.ViewHolder> {

    ArrayList<Pair<String, Boolean>> days;

    Context context;

    public WeekDaysAdapter(Context context) {
        this.context = context;
        days = new ArrayList<>();
        days.add(new Pair<>("Saturday", false));
        days.add(new Pair<>("Sunday", false));
        days.add(new Pair<>("Monday", false));
        days.add(new Pair<>("Tuesday", false));
        days.add(new Pair<>("Wednesday", false));
        days.add(new Pair<>("Thursday", false));
        days.add(new Pair<>("Friday", false));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.week_day_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position;
        holder.dayTextView.setText(days.get(i).first);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days.set(i, new Pair<>(days.get(i).first, !days.get(i).second));
                if(days.get(i).second == true) {
                    holder.dayTextView.setBackgroundColor(context.getResources().getColor(R.color.dodger_blue));
                    holder.dayTextView.setTextColor(Color.WHITE);
                }
                else {
                    holder.dayTextView.setBackgroundColor(Color.WHITE);
                    holder.dayTextView.setTextColor(context.getResources().getColor(R.color.grey));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayTextView;
        ConstraintLayout constraintLayout;
        View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;

            constraintLayout = v.findViewById(R.id.constraint_layout_week_day_row_add_med);
            dayTextView = v.findViewById(R.id.text_view_week_day_add_med);
        }

        public TextView getDayTextView() {
            return dayTextView;
        }

        public void setDayTextView(TextView dayTextView) {
            this.dayTextView = dayTextView;
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
