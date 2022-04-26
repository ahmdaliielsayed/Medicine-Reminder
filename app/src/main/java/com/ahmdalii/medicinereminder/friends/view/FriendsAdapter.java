package com.ahmdalii.medicinereminder.friends.view;

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
import com.ahmdalii.medicinereminder.friendrequest.view.FriendRequestAdapter;
import com.ahmdalii.medicinereminder.friends.repository.FriendPojo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{

    Context context;
    List<FriendPojo> data;

    public FriendsAdapter(Context context, List<FriendPojo> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.friend_row, parent, false);
        FriendsAdapter.ViewHolder vh = new FriendsAdapter.ViewHolder(v);

        return vh;
    }

    public void setList(List<FriendPojo> updatedData){
        this.data = updatedData;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.ViewHolder holder, int position) {
        holder.friendName.setText(data.get(position).getFriendName());

        Glide.with(context).load(data.get(position).getProfile_image_uri())
                .apply(new RequestOptions().override(200,200))
                .into(holder.friendImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView friendImg;
        TextView friendName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendImg = itemView.findViewById(R.id.friendImgId);
            friendName = itemView.findViewById(R.id.friendNameId);
        }
    }
}
