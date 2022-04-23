package com.ahmdalii.medicinereminder.network;

import android.app.Activity;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface RemoteSource {

    void enqueueCall(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage);
    void enqueueCall(NetworkRegisterDelegate networkRegisterDelegate, String name, String email, String password, String profileImageURI);
    void enqueueCall(NetworkLoginDelegate networkDelegate, String email, String password);
    void enqueueCall(NetworkDelegate networkDelegate, String email);

    GoogleSignInClient getGoogleSignInClient(Activity activity);
    void signInWithGoogle(NetworkLoginDelegate networkDelegate, String idToken);
    void enqueueCall(AddMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses);
    void enqueueCall(DisplayMedNetworkDelegate networkDelegate, String medID);


    void signOut();
}
