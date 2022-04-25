package com.ahmdalii.medicinereminder.network;

import android.app.Activity;
import android.net.Uri;

import com.ahmdalii.medicinereminder.displaymed.presenter.DeleteMedicineNetworkDelegate;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;
import java.util.List;

public interface RemoteSource {

    void enqueueCall(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage);
    void enqueueCall(NetworkRegisterDelegate networkRegisterDelegate, String name, String email, String password, String profileImageURI);
    void enqueueCall(NetworkLoginDelegate networkDelegate, String email, String password);
    void enqueueCall(NetworkDelegate networkDelegate, String email);

    GoogleSignInClient getGoogleSignInClient(Activity activity);
    void signInWithGoogle(NetworkLoginDelegate networkDelegate, String idToken);
    void enqueueCall(AddMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses);
    void enqueueCall(DisplayMedNetworkDelegate networkDelegate, String medID);
    void enqueueCall(DeleteMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses);

    void signOut();

    void syncMedicineListToFirebase(NetworkDelegate networkDelegate, List<Medicine> unSyncedMedicines);
    void syncMedicineDosesListToFirebase(NetworkDelegate networkDelegate, List<MedicineDose> unSyncedMedicinesDoses);
}
