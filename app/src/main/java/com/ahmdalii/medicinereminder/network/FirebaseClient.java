package com.ahmdalii.medicinereminder.network;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseClient implements RemoteSource {

    private static FirebaseClient firebaseClient = null;

    StorageReference storageReference;

    public static FirebaseClient getInstance(){
        if (firebaseClient == null) {
            firebaseClient = new FirebaseClient();
        }

        return firebaseClient;
    }

    @Override
    public void enqueueCall(NetworkImageProfileDelegate networkImageProfileDelegate) {
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Image").child(System.currentTimeMillis() + ".jpg");
    }
}
