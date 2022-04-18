package com.ahmdalii.medicinereminder.network;

import android.net.Uri;

public interface RemoteSource {

    void enqueueCall(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage);
    void enqueueCall(NetworkRegisterDelegate networkRegisterDelegate, String name, String email, String password, String profileImageURI);
    void enqueueCall(NetworkDelegate networkDelegate, String email, String password);
    void enqueueCall(NetworkDelegate networkDelegate, String email);
}
