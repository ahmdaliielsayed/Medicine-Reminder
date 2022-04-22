package com.ahmdalii.medicinereminder.network;

import android.app.Activity;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class FirebaseClient implements RemoteSource {

    private static FirebaseClient firebaseClient = null;

    private final FirebaseAuth mAuth;
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
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        return GoogleSignIn.getClient(activity, gso);
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

    @Override
    public void signOut() {
        mAuth.signOut();
    }
}
