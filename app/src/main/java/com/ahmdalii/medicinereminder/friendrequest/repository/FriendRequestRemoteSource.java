package com.ahmdalii.medicinereminder.friendrequest.repository;

import android.content.Context;
import android.hardware.lights.LightsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FriendRequestRemoteSource implements FriendRequestRemoteSourceInterface{
    private static FriendRequestRemoteSource remoteSource = null;
    List<FriendRequestPojo> requests;
    DatabaseReference getData,getReqs;

    private FriendRequestRemoteSource() {
    }

    public static FriendRequestRemoteSource getInstance(){
        if(remoteSource == null)
            remoteSource = new FriendRequestRemoteSource();
        return remoteSource;
    }

    public List<FriendRequestPojo> getRequests(){
        getData = FirebaseDatabase.getInstance().getReference().child("Users");
        getReqs = FirebaseDatabase.getInstance().getReference().child("Requests");
        //getReqs.addValueEventListener()

//        checkEmailRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    user = dataSnapshot.getValue(User.class);
//                    if(user.getEmail().equals(email)){
//                        receiverId = user.getUserId();
//                        Log.i("TAG", "onDataChange: receiverid:  "+ receiverId);
//                        sendRequest(receiverId);
//                        flag = true;
//                        break;
//                    }
//
//                }
//                if(flag){
//                    Toast.makeText(getApplicationContext(), "True Email", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "False Email", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return requests;
    }

}
