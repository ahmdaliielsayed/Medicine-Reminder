package com.ahmdalii.medicinereminder.friends.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestPojo;
import com.ahmdalii.medicinereminder.friendrequest.view.FriendRequestAdapter;
import com.ahmdalii.medicinereminder.friends.repository.FriendPojo;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{

    Context context;
    List<RequestPojo> data;
    private OnBtnClickListener onBtnClickListener;

    public FriendsAdapter(Context context, List<RequestPojo> data, OnBtnClickListener onBtnClickListener) {
        this.context = context;
        this.data = data;
        this.onBtnClickListener = onBtnClickListener;
        Log.i("TAG", "FriendsAdapter: yadata");
    }

    @NonNull
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.friend_row, parent, false);
        FriendsAdapter.ViewHolder vh = new FriendsAdapter.ViewHolder(v);

        return vh;
    }

    public void setList(List<RequestPojo> updatedData){
        Log.i("TAG", "setList: " + updatedData.size());
        this.data = updatedData;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.ViewHolder holder, int position) {
        int i = position;
        Log.i("TAG", "onBindViewHolder: hellllllllllo bnding");
        holder.friendName.setText(data.get(i).getSenderUsername());

        Glide.with(context).load(data.get(i).getProfile_image_uri())
                .apply(new RequestOptions().override(200,200))
                .into(holder.friendImg);

        holder.friendRowId.setOnClickListener(view -> onBtnClickListener.onRowClick(data.get(position).getSenderId()));
    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "getItemCount: " + data);
        if(data == null)
            return 0;
        Log.i("TAG", "getItemCount: size = " + data.size());
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView friendImg;
        TextView friendName;
        ConstraintLayout friendRowId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendImg = itemView.findViewById(R.id.friendImgId);
            friendName = itemView.findViewById(R.id.friendNameId);
            friendRowId = itemView.findViewById(R.id.friendRowId);
        }
    }
}
