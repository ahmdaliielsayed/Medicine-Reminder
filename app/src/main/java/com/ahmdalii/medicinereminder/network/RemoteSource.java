package com.ahmdalii.medicinereminder.network;

import android.net.Uri;

import com.ahmdalii.medicinereminder.addmed.presenter.AddMedicineNetworkDelegate;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedNetworkDelegate;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.util.ArrayList;

public interface RemoteSource {

    void enqueueCall(NetworkImageProfileDelegate networkImageProfileDelegate, Uri uriProfileImage);
    void enqueueCall(NetworkDelegate networkDelegate, String name, String email, String password, String profileImageURI);
    void enqueueCall(AddMedicineNetworkDelegate networkDelegate, Medicine medicine, ArrayList<MedicineDose> doses);
    void enqueueCall(DisplayMedNetworkDelegate networkDelegate, String medID);

}
