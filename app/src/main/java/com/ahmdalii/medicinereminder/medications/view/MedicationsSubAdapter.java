package com.ahmdalii.medicinereminder.medications.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsPojo;

import java.util.List;

public class MedicationsSubAdapter extends RecyclerView.Adapter<MedicationsSubAdapter.ViewHolder>{
    List<MedicationsPojo> data;

    public MedicationsSubAdapter(List<MedicationsPojo> data) {
        //this.context = context;
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameText;
        TextView strengthText;
        TextView pillText;
        View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewId);
            nameText = itemView.findViewById(R.id.textView1);
            strengthText = itemView.findViewById(R.id.textView2);
            pillText = itemView.findViewById(R.id.textView3);
            layout = itemView.findViewById(R.id.innerRowId);
        }
    }

    @NonNull
    @Override
    public MedicationsSubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.medications_row_inner, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(data.get(position).getIcon());
        holder.nameText.setText(data.get(position).getName());
        holder.strengthText.setText(data.get(position).getStrength());
        holder.pillText.setText(data.get(position).getLeftNum() + " Pill(s) left");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("emy", "onClick: you clicked a medication row " + data.get(position).getName());

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
