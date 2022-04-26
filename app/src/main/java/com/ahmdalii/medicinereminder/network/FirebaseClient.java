package com.ahmdalii.medicinereminder.network;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DeleteMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.MedicineDoseTemp;
import com.ahmdalii.medicinereminder.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class FirebaseClient implements RemoteSource {

    private static FirebaseClient firebaseClient = null;

    private final FirebaseAuth mAuth;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceMed;
    private DatabaseReference databaseReferenceMedDos;

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
    public void enqueueCall(NetworkRegisterDelegate networkRegisterDelegate, String name, String email, String password, String profileImageURI) {
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
                                                .addOnCompleteListener(task2 -> networkRegisterDelegate.onResponse(uid))
                                                .addOnFailureListener(e -> networkRegisterDelegate.onFailure(e.getMessage()));
                                    })
                                    .addOnFailureListener(e -> networkRegisterDelegate.onFailure(e.getMessage()));
                        }
                    }
                })
                .addOnFailureListener(e -> networkRegisterDelegate.onFailure(e.getMessage()));
    }

    // login user with email and password
    @Override
    public void enqueueCall(NetworkLoginDelegate networkDelegate, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        getUser(networkDelegate, Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
                    }
                })
                .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
    }

    private void getUser(NetworkLoginDelegate networkDelegate, String uid) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User value = dataSnapshot.getValue(User.class);
                    if (Objects.requireNonNull(value).getUserId().equals(uid)) {
                        networkDelegate.onResponse(value);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                networkDelegate.onFailure(error.getMessage());
            }
        });
    }

    // reset password
    @Override
    public void enqueueCall(NetworkDelegate networkDelegate, String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        networkDelegate.onResponse();
                    }
                })
                .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
    }

    @Override
    public GoogleSignInClient getGoogleSignInClient(Activity activity) {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("271946629486-t05podnl4iif8rf367qbe5cnprh9iire.apps.googleusercontent.com")
                .requestEmail()
                .build();

        return GoogleSignIn.getClient(activity, gso);


        //return null;
    }

    @Override
    public void signInWithGoogle(NetworkLoginDelegate networkDelegate, String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    String uid = Objects.requireNonNull(authResult.getUser()).getUid();
                    String displayName = authResult.getUser().getDisplayName();
                    String email = authResult.getUser().getEmail();
                    String photo_uri = Objects.requireNonNull(authResult.getUser().getPhotoUrl()).toString();

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                    User user = new User(uid, displayName, email, null, photo_uri, null);
                    databaseReference.child(uid).setValue(user)
                            .addOnCompleteListener(task2 -> networkDelegate.onResponse(user))
                            .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
                })
                .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
    }

    //Add Medicine and its doses
    @Override
    public void enqueueCall(AddMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        String medID = medicine.getId();
        if (medID == null || medID.equals("")) {
            medID = databaseReference.child("medicine").push().getKey();
        }
        medicine.setUserID(uid);
        medicine.setId(medID);
        String finalMedID = medID;
        databaseReference.child("medicine").child(medID).setValue(medicine).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                for (int i = 0; i < doses.size(); i++) {
                    doses.get(i).setMedID(finalMedID);
                    String doseID = doses.get(i).getId();
                    if (doseID == null || doseID.equals("")) {
                        doseID = databaseReference.child("dose").push().getKey();
                    }
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

    //Get doses of a certain medicine
    @Override
    public void enqueueCall(DisplayMedNetworkDelegate networkDelegate, String medID) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("dose").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    GenericTypeIndicator<Map<String, MedicineDoseTemp>> t = new GenericTypeIndicator<Map<String, MedicineDoseTemp>>() {
                    };
                    Map<String, MedicineDoseTemp> dosesMap = task.getResult().getValue(t);

                    ArrayList<MedicineDose> doses = new ArrayList<>();


                    for (int i = 0; i < dosesMap.size(); i++) {
                        if (((MedicineDoseTemp) dosesMap.values().toArray()[i]).getMedID().equals(medID)) {
                            MedicineDose dose = new MedicineDose();
                            dose.setId((String) dosesMap.keySet().toArray()[i]);
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

                } else {
                    networkDelegate.onFailure();
                }
            }
        });
    }

    public void signOut() {
        mAuth.signOut();
    }

    @Override
    public void enqueueCall(DeleteMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        String medID = medicine.getId();

        medicine.setUserID(uid);
        medicine.setId(medID);
        String finalMedID = medID;
        databaseReference.child("medicine").child(medID).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                for(int i = 0; i < doses.size(); i++) {
                    String doseID = doses.get(i).getId();

                    databaseReference.child("dose").child(doseID).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                networkDelegate.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                networkDelegate.onFailure();
            }
        });
    }
    
    public void syncMedicineListToFirebase(NetworkSyncDelegate networkDelegate, List<Medicine> unSyncedMedicines) {
        databaseReference = FirebaseDatabase.getInstance().getReference("medicine");

        for (Medicine medicine : unSyncedMedicines) {
            medicine.setSync(true);
            databaseReference.child(medicine.getId()).setValue(medicine)
                    .addOnSuccessListener(unused -> {
                        networkDelegate.onResponse(medicine, true);
                    })
                    .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
        }
    }

    @Override
    public void syncMedicineDosesListToFirebase(NetworkSyncDelegate networkDelegate, List<MedicineDose> unSyncedMedicinesDoses) {
        databaseReference = FirebaseDatabase.getInstance().getReference("dose");

        for (MedicineDose medicineDose : unSyncedMedicinesDoses) {
            medicineDose.setSync(true);
            databaseReference.child(medicineDose.getId()).setValue(medicineDose)
                    .addOnSuccessListener(unused -> {
                        networkDelegate.onResponse(medicineDose);
                    })
                    .addOnFailureListener(e -> networkDelegate.onFailure(e.getMessage()));
        }
    }

    @Override
    public void getAllDosesWithMedicineNameForUser(Date currentDate, String uid, NetworkHomeDelegate networkHomeDelegate) {
        Map<Medicine, List<MedicineDose>> listMap = new HashMap<>();
        List<MedicineDose> medicineDoseList = new ArrayList<>();

        databaseReferenceMed = FirebaseDatabase.getInstance().getReference("medicine");
        databaseReferenceMed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot medicineSnapshot : snapshot.getChildren()) {
                    Medicine medicine = medicineSnapshot.getValue(Medicine.class);
                    if (Objects.requireNonNull(medicine).getUserID().equals(uid)) {
                        databaseReferenceMedDos = FirebaseDatabase.getInstance().getReference("dose");
                        databaseReferenceMedDos.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot medicineDoseSnapshot : snapshot.getChildren()) {
                                    MedicineDose medicineDose = medicineDoseSnapshot.getValue(MedicineDose.class);
                                    if (Objects.requireNonNull(medicineDose).getMedID().equals(medicine.getId())) {
                                        medicineDoseList.add(medicineDose);
                                        Log.d("asdfg:", medicineDose.toString());
                                    }
                                }
                                listMap.put(medicine, medicineDoseList);

                                Log.d("asdfg:size", String.valueOf(listMap.size()));
//                                networkHomeDelegate.onResponse(listMap, currentDate);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                networkHomeDelegate.onFailure(error.getMessage());
                            }
                        });
                    }
                }

                Map<Medicine, MedicineDose> returnedMedDosMap = new HashMap<>();

                for (Map.Entry<Medicine, List<MedicineDose>> entry : listMap.entrySet()) {
                    Medicine key = entry.getKey();
                    List<MedicineDose> value = entry.getValue();
                    Log.d("asdfg:Cdate", currentDate.toString());
                    for (int i=0; i<value.size(); i++) {
                        String[] dateTime = value.get(i).getTime().split("T"); // 2022-04-25 T 03:41
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date parsedDate = formatter.parse(dateTime[0]);

                            Log.d("asdfg:Pdate", parsedDate.toString());
                            if (Objects.requireNonNull(parsedDate).equals(currentDate)) {
                                returnedMedDosMap.put(key, value.get(i));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.d("asdfg:sizeL", String.valueOf(listMap.size()));
                Log.d("asdfg:sizeRL", String.valueOf(returnedMedDosMap.size()));
                networkHomeDelegate.onResponse(returnedMedDosMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                networkHomeDelegate.onFailure(error.getMessage());
            }
        });
    }
}
