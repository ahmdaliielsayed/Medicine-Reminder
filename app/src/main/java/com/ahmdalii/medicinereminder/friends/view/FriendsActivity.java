package com.ahmdalii.medicinereminder.friends.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.friends.repository.FriendPojo;

import java.util.Arrays;
import java.util.List;

public class FriendsActivity extends AppCompatActivity implements FriendsViewInterface {

    FriendsAdapter adapter;
    List<FriendPojo> tempData;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        recyclerView = findViewById(R.id.friendRecyclerId);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        tempData = Arrays.asList(new FriendPojo("1","Emy","https://firebasestorage.googleapis.com/v0/b/medicine-reminder-91bf9.appspot.com/o/Profile%20Image%2F1650664925668.jpg?alt=media&token=c1edd658-a25f-4ebd-badb-003776e7837b"), new FriendPojo("2","Ahmed","https://lh3.googleusercontent.com/a-/AOh14GgLBxNEv3de1mQnBtNj7IRyHm-pmiLGFTGtdpYjPQ=s96-c"));
        adapter = new FriendsAdapter(this, tempData);
        recyclerView.setAdapter(adapter);
    }
}