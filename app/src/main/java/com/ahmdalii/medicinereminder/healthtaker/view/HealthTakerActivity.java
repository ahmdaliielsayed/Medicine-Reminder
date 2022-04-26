package com.ahmdalii.medicinereminder.healthtaker.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;
import com.ahmdalii.medicinereminder.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class HealthTakerActivity extends AppCompatActivity {

    DatabaseReference checkEmailRef,reqRef;

    TextView emailText;
    Button sendBtn;

    String senderId;
    String senderUsername;

    User user;
    Boolean flag;

    //receiver data
    String email;
    String receiverId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_taker);

        emailText = findViewById(R.id.emailTextId);
        sendBtn = findViewById(R.id.sendBtnId);

        Intent inIntent = getIntent();
        senderId = inIntent.getStringExtra("userId");
        senderUsername = inIntent.getStringExtra("userName");

        checkEmailRef = FirebaseDatabase.getInstance().getReference().child("Users");
        reqRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        sendBtn.setOnClickListener(v -> {
            email = emailText.getText().toString();
            if(NetworkConnection.isNetworkAvailable(this)){
                Toast.makeText(this, "You are connected", Toast.LENGTH_SHORT).show();
                checkEmailExisting(email);
//                if(checkEmailExisting(email)){
//                    Toast.makeText(this, "True Email", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(this, "False Email", Toast.LENGTH_SHORT).show();
//                }
            }

            else
                Toast.makeText(this, "You are not connected", Toast.LENGTH_SHORT).show();

        });
    }

    private void checkEmailExisting(String email){
        flag = false;
        checkEmailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    user = dataSnapshot.getValue(User.class);
                    if(user.getEmail().equals(email)){
                        receiverId = user.getUserId();
                        Log.i("TAG", "onDataChange: receiverid:  "+ receiverId);
                        sendRequest(receiverId);
                        flag = true;
                        break;
                    }

                }
                if(flag){
                    Toast.makeText(getApplicationContext(), "True Email", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "False Email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendRequest(String receiverId) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("status", "pending");
//        reqRef.child(receiverId).child(senderId).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
//            @Override
//            public void onComplete(@NonNull Task task) {
//                if(task.isSuccessful())
//                    Toast.makeText(getApplicationContext(), "You have sent req", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(getApplicationContext(), "An error happened, try again later", Toast.LENGTH_SHORT).show();
//            }
//        });
        //databaseReference.child("medicine").child(medID).setValue(medicine).addOnSuccessListener(

        reqRef.child(receiverId).setValue(new RequestPojo(senderId,senderUsername,"pending")).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
}