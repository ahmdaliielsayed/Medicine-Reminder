package com.ahmdalii.medicinereminder.friends.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.friends.view.FriendsViewInterface;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FriendRepo implements FriendRepoInterface{
    private static FriendRepo friendRepo = null;
    List<RequestPojo> friends;
    DatabaseReference freindRef;
    RequestPojo friend;
    FriendsViewInterface view;

    private FriendRepo(FriendsViewInterface view){
        this.view = view;
    }

    public static FriendRepo getInstance(FriendsViewInterface view){
        if(friendRepo == null)
            friendRepo = new FriendRepo(view);
        return friendRepo;
    }

    public List<RequestPojo> getFriends(String receiverId){
        friends = new ArrayList<>();
        freindRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        freindRef.orderByChild("receiverId").equalTo(receiverId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot temp : snapshot.getChildren()){
                        friend = temp.getValue(RequestPojo.class);
                        if(friend.getStatus().equals("accept")){
                            Log.i("TAG", "onDataChange: hello repoooooooooooo");
                            friends.add(friend);
                        }

                    }
                }
                view.setData(friends);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return friends;
    }
}
