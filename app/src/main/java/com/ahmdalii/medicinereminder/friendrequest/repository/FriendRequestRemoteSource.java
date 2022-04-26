package com.ahmdalii.medicinereminder.friendrequest.repository;

import android.content.Context;
import android.hardware.lights.LightsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.friendrequest.view.FriendRequestViewInterface;
import com.ahmdalii.medicinereminder.friends.view.FriendsViewInterface;
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

    private FriendRequestRemoteSource() {
        this.view = view;
    }

    public static FriendRequestRemoteSource getInstance(){
        if(remoteSource == null)
            remoteSource = new FriendRequestRemoteSource();
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
                        if(request.getStatus().equals("pending"))
                            requests.add(request);
                    }
                }
                view.setData(requests);
                Log.i("TAG", "onDataChange: yalaaaaaaaaaaaa " + requests.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return requests;
    }

    public void addFriend(String receiverId, String senderId){
        final String[] id = new String[1];
        reqRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        reqRef.orderByChild("receiverId").equalTo(receiverId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot temp : snapshot.getChildren()){
                        request = temp.getValue(RequestPojo.class);
                        if(request.getSenderId().equals(senderId)){
                            id[0] = temp.getKey();
                            request.setStatus("accept");
                            reqRef.child(id[0]).setValue(request);
                            Log.i("TAG", "onDataChange: key of add " + id[0]);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void setView(FriendRequestViewInterface view) {
        this.view = view;
    }

}
