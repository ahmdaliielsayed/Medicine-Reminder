package com.ahmdalii.medicinereminder.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.bumptech.glide.Glide;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {

    private final Context context;
    Map<Medicine, MedicineDose> allDosesWithMedicineName;
    List<Medicine> keysList = new ArrayList<>();
    List<MedicineDose> dosesList = new ArrayList<>();
//    private OnFavoriteClickListener onFavoriteClickListener;

    Set<String> timeSlot = new HashSet<>();

    public HomeFragmentAdapter(Context context, Map<Medicine, MedicineDose> allDosesWithMedicineName/*, OnFavoriteClickListener onFavoriteClickListener*/) {
        this.context = context;
        this.allDosesWithMedicineName = allDosesWithMedicineName;

        createKeysAndDosesLists(allDosesWithMedicineName);
//        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    private void createKeysAndDosesLists(Map<Medicine, MedicineDose> allDosesWithMedicineName) {
        allDosesWithMedicineName = sortByValue(allDosesWithMedicineName);
        keysList.clear();
        dosesList.clear();
        timeSlot.clear();

        for (Map.Entry<Medicine, MedicineDose> entries : allDosesWithMedicineName.entrySet()) {
            timeSlot.add(entries.getValue().getTime().split("T")[1]);

            Medicine key = entries.getKey();
            keysList.add(key);
            MedicineDose value = entries.getValue();
            dosesList.add(value);
        }
        for (String time : timeSlot) {
            Log.d("setcount:", time);
        }
        Log.d("setset:", String.valueOf(timeSlot.size()));
    }

    private static Map<Medicine, MedicineDose> sortByValue(Map<Medicine, MedicineDose> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Medicine, MedicineDose>> list = new LinkedList<>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Medicine, MedicineDose>>() {
            public int compare(Map.Entry<Medicine, MedicineDose> o1,
                               Map.Entry<Medicine, MedicineDose> o2) {
                return (o1.getValue().getTime()).compareTo(o2.getValue().getTime());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Medicine, MedicineDose> sortedMap = new LinkedHashMap<Medicine, MedicineDose>();
        for (Map.Entry<Medicine, MedicineDose> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    @NonNull
    @Override                                      // recyclerview
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.upcoming_medicines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getTxtViewMedicineName().setText(keysList.get(position).getName());
        holder.getTxtViewStrengthUnit().setText(String.valueOf(keysList.get(position).getStrength()).concat(" ").concat(keysList.get(position).getUnit()));
        holder.getTxtViewDayFrequency().setText(keysList.get(position).getDayFrequency());
        holder.getTxtViewInstructions().setText(keysList.get(position).getInstructions());
        holder.getTxtViewStatus().setText(dosesList.get(position).getStatus());
        holder.getTxtViewTime().setText(dosesList.get(position).getTime().split("T")[1]);
        holder.getTxtViewAmountForm().setText(String.valueOf(dosesList.get(position).getAmount()).concat(" ").concat(keysList.get(position).getForm()));

        switch (keysList.get(position).getForm()) {
            case "pills":
                Glide.with(context).load(R.drawable.ic_pills).into(holder.getImgViewPill());
                break;
            case "solution":
                Glide.with(context).load(R.drawable.ic_solution).into(holder.getImgViewPill());
                break;
            case "injection":
                Glide.with(context).load(R.drawable.ic_injection).into(holder.getImgViewPill());
                break;
            case "powder":
                Glide.with(context).load(R.drawable.ic_powder).into(holder.getImgViewPill());
                break;
            case "drops":
                Glide.with(context).load(R.drawable.ic_drops).into(holder.getImgViewPill());
                break;
            case "inhaler":
                Glide.with(context).load(R.drawable.ic_inhaler).into(holder.getImgViewPill());
                break;
            case "topical":
                Glide.with(context).load(R.drawable.ic_topical).into(holder.getImgViewPill());
                break;
        }

//        holder.getBtnAddToFavorite().setOnClickListener(view -> onFavoriteClickListener.onRemoveClick(movies.get(position)));
    }

    @Override
    public int getItemCount() {
        return allDosesWithMedicineName.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataToAdapter(Map<Medicine, MedicineDose> allDosesWithMedicineName) {
        this.allDosesWithMedicineName = allDosesWithMedicineName;
        createKeysAndDosesLists(allDosesWithMedicineName);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private CircleImageView imgViewPill;
        private TextView txtViewMedicineName, txtViewStrengthUnit, txtViewDayFrequency, txtViewInstructions, txtViewStatus, txtViewTime, txtViewAmountForm;

        private ConstraintLayout constraint_layout;
        private View card_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            card_layout = itemView;
//            constraint_layout = itemView.findViewById(R.id.constraint_layout);
        }

        public CardView getCardView() {
            if (cardView == null) {
                cardView = itemView.findViewById(R.id.cardView);
            }
            return cardView;
        }

        public CircleImageView getImgViewPill() {
            if (imgViewPill == null) {
                imgViewPill = itemView.findViewById(R.id.imgViewPill);
            }
            return imgViewPill;
        }

        public TextView getTxtViewMedicineName() {
            if (txtViewMedicineName == null) {
                txtViewMedicineName = itemView.findViewById(R.id.txtViewMedicineName);
            }
            return txtViewMedicineName;
        }
        public TextView getTxtViewStrengthUnit() {
            if (txtViewStrengthUnit == null) {
                txtViewStrengthUnit = itemView.findViewById(R.id.txtViewStrengthUnit);
            }
            return txtViewStrengthUnit;
        }
        public TextView getTxtViewDayFrequency() {
            if (txtViewDayFrequency == null) {
                txtViewDayFrequency = itemView.findViewById(R.id.txtViewDayFrequency);
            }
            return txtViewDayFrequency;
        }
        public TextView getTxtViewInstructions() {
            if (txtViewInstructions == null) {
                txtViewInstructions = itemView.findViewById(R.id.txtViewInstructions);
            }
            return txtViewInstructions;
        }
        public TextView getTxtViewStatus() {
            if (txtViewStatus == null) {
                txtViewStatus = itemView.findViewById(R.id.txtViewStatus);
            }
            return txtViewStatus;
        }
        public TextView getTxtViewTime() {
            if (txtViewTime == null) {
                txtViewTime = itemView.findViewById(R.id.txtViewTime);
            }
            return txtViewTime;
        }
        public TextView getTxtViewAmountForm() {
            if (txtViewAmountForm == null) {
                txtViewAmountForm = itemView.findViewById(R.id.txtViewAmountForm);
            }
            return txtViewAmountForm;
        }
    }
}
