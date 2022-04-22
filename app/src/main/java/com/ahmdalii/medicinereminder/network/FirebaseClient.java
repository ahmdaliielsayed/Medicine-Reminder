package com.ahmdalii.medicinereminder.network;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.MedicineDoseTemp;
import com.ahmdalii.medicinereminder.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseClient implements RemoteSource {

    private static FirebaseClient firebaseClient = null;

    private FirebaseAuth mAuth;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private FirebaseClient() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseClient getInstance() {
        if (firebaseClient == null) {
            firebaseClient = new FirebaseClient();
        }

        return firebaseClient;
    }

    // save profile image on firebase storage
    @Override
    public void enqueueCall(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage) {
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Image").child(System.currentTimeMillis() + ".jpg");
        storageReference.putFile(uriProfileImage)
                .addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> downloadUrl = storageReference.getDownloadUrl();
                    downloadUrl
                            .addOnSuccessListener(uri -> networkImageProfileDelegate.onResponseSuccess(uri.toString()))
                            .addOnFailureListener(e -> networkImageProfileDelegate.onFailureResult(e.getMessage()));
                })
                .addOnFailureListener(e -> networkImageProfileDelegate.onFailureResult(e.getMessage()));
    }

    // register user with email and password and create user profile on firebase
    @Override
    public void enqueueCall(NetworkDelegate networkDelegate, String name, String email, String password, String profileImageURI) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .setPhotoUri(Uri.parse(profileImageURI))
                                    .build();

                            currentUser.updateProfile(profileChangeRequest)
                                    .addOnCompleteListener(task1 -> {
                                        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                                        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                        User user = new User(uid, name, email, password, profileImageURI, null);
                                        databaseReference.child(uid).setValue(user)
                                                .addOnCompleteListener(task2 -> networkDelegate.onResponse(uid))
                                                .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
                                    })
                                    .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
                        }
                    }
                })
                .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
    }

    @Override
    public void enqueueCall(AddMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        String medID = databaseReference.child("medicine").push().getKey();

        medicine.setUserID(uid);
        medicine.setId(medID);
        databaseReference.child("medicine").child(medID).setValue(medicine).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                for(int i = 0; i < doses.size(); i++) {
                    doses.get(i).setMedID(medID);
                    String doseID = databaseReference.child("dose").push().getKey();
                    doses.get(i).setId(doseID);
                    databaseReference.child("dose").child(doseID).setValue(doses.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            networkDelegate.onFailure();
                        }
                    });
                }
                networkDelegate.onSuccess(medicine, doses);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                networkDelegate.onFailure();
            }
        });
    }

    @Override
    public void enqueueCall(DisplayMedNetworkDelegate networkDelegate, String medID) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("dose").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {

                    GenericTypeIndicator<Map<String, MedicineDoseTemp>> t = new GenericTypeIndicator<Map<String, MedicineDoseTemp>>() {};
                    Map<String, MedicineDoseTemp> dosesMap = task.getResult().getValue(t);

                    ArrayList<MedicineDose> doses = new ArrayList<>();


                    for(int i = 0; i < dosesMap.size(); i++) {
                        if(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getMedID().equals(medID)) {
                            MedicineDose dose = new MedicineDose();
                            dose.setId((String) dosesMap.keySet().toArray()[i]);
                            dose.setDay(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getDay());
                            dose.setTime(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getTime());
                            dose.setAmount(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getAmount());
                            dose.setStatus(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getStatus());
                            dose.setGiverID(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getGiverID());
                            dose.setSync(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getSync());
                            dose.setMedID(((MedicineDoseTemp) dosesMap.values().toArray()[i]).getMedID());
                            doses.add(dose);
                        }
                    }
                    networkDelegate.onSuccess(doses);

                }
                else {
                    networkDelegate.onFailure();
                }
            }
        });
    }
}
