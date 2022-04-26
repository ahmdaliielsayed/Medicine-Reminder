package com.ahmdalii.medicinereminder.friends.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.friends.presenter.FriendPresenter;
import com.ahmdalii.medicinereminder.friends.repository.FriendPojo;
import com.ahmdalii.medicinereminder.friends.repository.FriendRepo;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;
import com.ahmdalii.medicinereminder.home.view.HomeActivity;
import com.ahmdalii.medicinereminder.home.view.HomeFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendsActivity extends AppCompatActivity implements FriendsViewInterface, OnBtnClickListener {

    FriendPresenter presenter;

    FriendsAdapter adapter;
    List<RequestPojo> friendsData;
    RecyclerView recyclerView;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        recyclerView = findViewById(R.id.friendRecyclerId);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent inIntent = getIntent();
        userId = inIntent.getStringExtra("userId");

        presenter = new FriendPresenter(this, FriendRepo.getInstance());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //tempData = Arrays.asList(new FriendPojo("1","Emy","https://firebasestorage.googleapis.com/v0/b/medicine-reminder-91bf9.appspot.com/o/Profile%20Image%2F1650664925668.jpg?alt=media&token=c1edd658-a25f-4ebd-badb-003776e7837b"), new FriendPojo("2","Ahmed","https://lh3.googleusercontent.com/a-/AOh14GgLBxNEv3de1mQnBtNj7IRyHm-pmiLGFTGtdpYjPQ=s96-c"));
        friendsData = presenter.getFriends(userId);
        adapter = new FriendsAdapter(this, friendsData, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setData(List<RequestPojo> friends) {
        Log.i("TAG", "setData: this " + this);
        Log.i("TAG", "setData: " + friends.size());
        friendsData = friends;
        adapter.setList(friendsData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRowClick(String uid) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("uid", uid);
        startActivity(intent);
        finish();
    }
}