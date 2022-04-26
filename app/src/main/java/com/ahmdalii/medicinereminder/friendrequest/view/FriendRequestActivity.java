package com.ahmdalii.medicinereminder.friendrequest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.friendrequest.presenter.FriendRequestPresenter;
import com.ahmdalii.medicinereminder.friendrequest.presenter.FriendRequestPresenterInterface;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestPojo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity implements FriendRequestViewInterface{

    FriendRequestPresenterInterface presenter;

    FriendRequestAdapter adapter;
    List<FriendRequestPojo> tempData;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        recyclerView = findViewById(R.id.friendReqRecyclerId);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        tempData = Arrays.asList(new FriendRequestPojo("1","Emy","https://firebasestorage.googleapis.com/v0/b/medicine-reminder-91bf9.appspot.com/o/Profile%20Image%2F1650664925668.jpg?alt=media&token=c1edd658-a25f-4ebd-badb-003776e7837b"), new FriendRequestPojo("2","Adam","https://lh3.googleusercontent.com/a-/AOh14GgLBxNEv3de1mQnBtNj7IRyHm-pmiLGFTGtdpYjPQ=s96-c"));
        adapter = new FriendRequestAdapter(tempData,this);
        recyclerView.setAdapter(adapter);



    }
}