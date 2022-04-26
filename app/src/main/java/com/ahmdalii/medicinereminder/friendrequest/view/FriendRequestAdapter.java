package com.ahmdalii.medicinereminder.friendrequest.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsSectionPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;
import com.ahmdalii.medicinereminder.medications.view.MedicationsMainAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.ViewHolder>{

    Context context;
    List<FriendRequestPojo> data;

    public FriendRequestAdapter(List<FriendRequestPojo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.friend_request_row, parent, false);
        FriendRequestAdapter.ViewHolder vh = new FriendRequestAdapter.ViewHolder(v);

        return vh;
    }

    public void setList(List<FriendRequestPojo> updatedData){
        this.data = updatedData;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdapter.ViewHolder holder, int position) {
        holder.friendReqName.setText(data.get(position).getUsername());
        //holder.friendReqImg
        Glide.with(context).load(data.get(position).getProfile_image_uri())
                .apply(new RequestOptions().override(200,200))
                .into(holder.friendReqImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView friendReqImg;
        TextView friendReqName;
        Button confirmBtn;
        Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendReqImg = itemView.findViewById(R.id.friendReqImgId);
            friendReqName = itemView.findViewById(R.id.friendReqNameId);
            confirmBtn = itemView.findViewById(R.id.confirmBtnId);
            deleteBtn = itemView.findViewById(R.id.deleteBtnId);
        }
    }
}