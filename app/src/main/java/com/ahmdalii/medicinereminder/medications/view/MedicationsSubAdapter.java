package com.ahmdalii.medicinereminder.medications.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;

import java.util.ArrayList;
import java.util.List;

public class MedicationsSubAdapter extends RecyclerView.Adapter<MedicationsSubAdapter.ViewHolder>{
    List<MedsPojo> data = new ArrayList<>();

    public MedicationsSubAdapter(List<MedsPojo> data) {
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
            imageView = itemView.findViewById(R.id.friendReqImgId);
            nameText = itemView.findViewById(R.id.friendReqNameId);
            strengthText = itemView.findViewById(R.id.text_view_med_name_display_med);
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

    public void setList(List<MedsPojo> updatedData){
        this.data = updatedData;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Log.i("TAG", "onBindViewHolder: id=" + data.get(position).getId());
        if(data.get(position).getForm().toString().equals("topical"))
            holder.imageView.setImageResource(R.drawable.ic_topical);
        else if(data.get(position).getForm().toString().equals("solution"))
            holder.imageView.setImageResource(R.drawable.ic_solution);
        else if(data.get(position).getForm().toString().equals("injection"))
            holder.imageView.setImageResource(R.drawable.ic_injection);
        else if(data.get(position).getForm().toString().equals("powder"))
            holder.imageView.setImageResource(R.drawable.ic_powder);
        else if(data.get(position).getForm().toString().equals("drops"))
            holder.imageView.setImageResource(R.drawable.ic_drops);
        else if(data.get(position).getForm().toString().equals("inhaler"))
            holder.imageView.setImageResource(R.drawable.ic_inhaler);
        else
            holder.imageView.setImageResource(R.drawable.ic_pills);

        holder.nameText.setText(data.get(position).getName());
        holder.strengthText.setText(data.get(position).getStrength().toString());
        holder.pillText.setText(data.get(position).getRemainingMedAmount().toString() + " Pill(s) left");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: you clicked a medication row " + data.get(position).getName());
                Bundle args = new Bundle();
                args.putString("medicineID", data.get(position).getId());
                Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_displayMedFragment, args);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
