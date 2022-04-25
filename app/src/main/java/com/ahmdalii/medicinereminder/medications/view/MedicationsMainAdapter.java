package com.ahmdalii.medicinereminder.medications.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsSectionPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;

import java.util.List;

public class MedicationsMainAdapter extends RecyclerView.Adapter<MedicationsMainAdapter.ViewHolder>{

    List<MedicationsSectionPojo> data;
    //MedicationsSubAdapter medAdapter;

    public MedicationsMainAdapter(List<MedicationsSectionPojo> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MedicationsMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.medications_row_outer, parent, false);
        MedicationsMainAdapter.ViewHolder vh = new MedicationsMainAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationsMainAdapter.ViewHolder holder, int position) {
        holder.sectionName.setText(data.get(position).getSectionName());

        List<MedsPojo> medList = data.get(position).getMedPojo();
        MedicationsSubAdapter medAdapter = new MedicationsSubAdapter(medList);
        holder.childRecycler.setAdapter(medAdapter);
    }

    public void setList(List<MedicationsSectionPojo> updatedData){
        this.data = updatedData;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sectionName;
        RecyclerView childRecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.sectionTextId);
            childRecycler = itemView.findViewById(R.id.childId);
        }
    }
}
