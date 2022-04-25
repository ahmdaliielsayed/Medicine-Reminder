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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class HealthTakerActivity extends AppCompatActivity {

    DatabaseReference reqRef;

    TextView emailText;
    Button sendBtn;

    String userId;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_taker);

        emailText = findViewById(R.id.emailTextId);
        sendBtn = findViewById(R.id.sendBtnId);

        Intent inIntent = getIntent();
        userId = inIntent.getStringExtra("userId");

        reqRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        sendBtn.setOnClickListener(v -> {
            if(NetworkConnection.isNetworkAvailable(this)){
                Log.i("TAG", "onCreate: userId : " + userId);
                sendRequest(userId);
                Toast.makeText(this, "You are connected", Toast.LENGTH_SHORT).show();
            }

            else
                Toast.makeText(this, "You are not connected", Toast.LENGTH_SHORT).show();

        });
    }

    private void sendRequest(String senderUserId) {
        String receiverUserId = "123";
        HashMap hashMap = new HashMap();
        hashMap.put("status", "pending");
        reqRef.child(senderUserId).child(receiverUserId).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful())
                    Toast.makeText(getApplicationContext(), "You have sent req", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "An error happened, try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}