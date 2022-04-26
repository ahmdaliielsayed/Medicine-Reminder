package com.ahmdalii.medicinereminder.friendrequest.repository;

import android.content.Context;
import android.hardware.lights.LightsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.friendrequest.view.FriendRequestViewInterface;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;
import com.ahmdalii.medicinereminder.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestRemoteSource implements FriendRequestRemoteSourceInterface{
    private static FriendRequestRemoteSource remoteSource = null;
    List<RequestPojo> requests;
    DatabaseReference getData,reqRef;
    RequestPojo request;
    FriendRequestViewInterface view;

    private FriendRequestRemoteSource(FriendRequestViewInterface view) {
        this.view = view;
    }

    public static FriendRequestRemoteSource getInstance(FriendRequestViewInterface view){
        if(remoteSource == null)
            remoteSource = new FriendRequestRemoteSource(view);
        return remoteSource;
    }

    public List<RequestPojo> getRequests(String receiverId){

        requests = new ArrayList<>();
        getData = FirebaseDatabase.getInstance().getReference().child("Users");
        reqRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        reqRef.orderByChild("receiverId").equalTo(receiverId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot temp : snapshot.getChildren()){
                        request = temp.getValue(RequestPojo.class);
                        requests.add(request);
                    }
                }
                view.setData(requests);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return requests;
    }

}
