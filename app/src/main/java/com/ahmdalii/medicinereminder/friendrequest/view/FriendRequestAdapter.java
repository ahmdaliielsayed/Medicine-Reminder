package com.ahmdalii.medicinereminder.friendrequest.view;

import android.content.Context;
import android.util.Log;
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
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestRemoteSource;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.ViewHolder>{

    Context context;
    List<RequestPojo> data;
    OnBtnClickListener listener;

    public FriendRequestAdapter(List<RequestPojo> data, Context context, OnBtnClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FriendRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.friend_request_row, parent, false);
        FriendRequestAdapter.ViewHolder vh = new FriendRequestAdapter.ViewHolder(v);

        return vh;
    }

    public void setList(List<RequestPojo> updatedData){
        Log.i("TAG", "setList: hello from setList " + updatedData.size());
        this.data = updatedData;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdapter.ViewHolder holder, int position) {
        holder.friendReqName.setText(data.get(position).getSenderUsername());
        //holder.friendReqImg
        Glide.with(context).load(data.get(position).getProfile_image_uri())
                .apply(new RequestOptions().override(200,200))
                .into(holder.friendReqImg);
        holder.confirmBtn.setOnClickListener(v -> {
            listener.onConfirmClick(data.get(position).getReceiverId(), data.get(position).getSenderId());
        });
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
        View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendReqImg = itemView.findViewById(R.id.friendReqImgId);
            friendReqName = itemView.findViewById(R.id.friendReqNameId);
            confirmBtn = itemView.findViewById(R.id.confirmBtnId);
            deleteBtn = itemView.findViewById(R.id.deleteBtnId);
        }
    }
}
