package com.ahmdalii.medicinereminder.friendrequest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.friendrequest.presenter.FriendRequestPresenter;
import com.ahmdalii.medicinereminder.friendrequest.presenter.FriendRequestPresenterInterface;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestPojo;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestRemoteSource;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestRepository;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity implements FriendRequestViewInterface, OnBtnClickListener{

    FriendRequestPresenterInterface presenter;

    FriendRequestAdapter adapter;
    //List<FriendRequestPojo> tempData;
    List<RequestPojo> requestsData;
    RecyclerView recyclerView;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        recyclerView = findViewById(R.id.friendReqRecyclerId);

        Intent inIntent = getIntent();
        userId = inIntent.getStringExtra("userId");
        Log.i("TAG", "onCreate: I am the user id " + userId);

        presenter = new FriendRequestPresenter(this, FriendRequestRepository.getInstance(getApplicationContext(), FriendRequestRemoteSource.getInstance()));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //tempData = Arrays.asList(new FriendRequestPojo("1","Emy","https://firebasestorage.googleapis.com/v0/b/medicine-reminder-91bf9.appspot.com/o/Profile%20Image%2F1650664925668.jpg?alt=media&token=c1edd658-a25f-4ebd-badb-003776e7837b"), new FriendRequestPojo("2","Adam","https://lh3.googleusercontent.com/a-/AOh14GgLBxNEv3de1mQnBtNj7IRyHm-pmiLGFTGtdpYjPQ=s96-c"));
        requestsData = presenter.getRequests(userId);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void setData(List<RequestPojo> requests) {
        Log.i("TAG", "setData: sizeeeee " + requests.size());
        requestsData = requests;
        Log.i("TAG", "onCreate: request data size " + requestsData.size());
        adapter = new FriendRequestAdapter(requestsData,this, this);
        recyclerView.setAdapter(adapter);
        adapter.setList(requestsData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onConfirmClick(String receiverId, String senderId) {
        presenter.addFriend(receiverId, senderId);
    }
}