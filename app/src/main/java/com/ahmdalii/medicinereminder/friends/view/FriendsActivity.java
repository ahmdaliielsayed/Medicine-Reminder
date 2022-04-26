package com.ahmdalii.medicinereminder.friends.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.friends.presenter.FriendPresenter;
import com.ahmdalii.medicinereminder.friends.repository.FriendPojo;
import com.ahmdalii.medicinereminder.friends.repository.FriendRepo;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendsActivity extends AppCompatActivity implements FriendsViewInterface {

    FriendPresenter presenter;

    FriendsAdapter adapter;
    List<FriendPojo> tempData;
    List<RequestPojo> friendsData;
    RecyclerView recyclerView;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        recyclerView = findViewById(R.id.friendRecyclerId);

        Intent inIntent = getIntent();
        userId = inIntent.getStringExtra("userId");

        presenter = new FriendPresenter(this, FriendRepo.getInstance(this));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //tempData = Arrays.asList(new FriendPojo("1","Emy","https://firebasestorage.googleapis.com/v0/b/medicine-reminder-91bf9.appspot.com/o/Profile%20Image%2F1650664925668.jpg?alt=media&token=c1edd658-a25f-4ebd-badb-003776e7837b"), new FriendPojo("2","Ahmed","https://lh3.googleusercontent.com/a-/AOh14GgLBxNEv3de1mQnBtNj7IRyHm-pmiLGFTGtdpYjPQ=s96-c"));
        friendsData = presenter.getFriends(userId);
        adapter = new FriendsAdapter(this, friendsData);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setData(List<RequestPojo> friends) {
        friendsData = friends;
        adapter.setList(friendsData);
        adapter.notifyDataSetChanged();
    }
}