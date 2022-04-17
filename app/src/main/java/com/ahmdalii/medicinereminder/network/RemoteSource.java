package com.ahmdalii.medicinereminder.network;

import android.net.Uri;

public interface RemoteSource {

    void enqueueCall(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage);
    void enqueueCall(NetworkDelegate networkDelegate, String name, String email, String password, String profileImageURI);
}
