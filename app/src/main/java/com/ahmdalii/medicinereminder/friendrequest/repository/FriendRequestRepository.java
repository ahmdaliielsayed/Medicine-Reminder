package com.ahmdalii.medicinereminder.friendrequest.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahmdalii.medicinereminder.medications.repository.MedicationRemoteSourceInterface;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsLocalSourceInterface;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsRepository;
import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;

import java.util.List;

public class FriendRequestRepository implements FriendRequestRepositoryInterface{
    private Context context;
    FriendRequestRemoteSourceInterface remoteSource;
    //localSource;
    private static FriendRequestRepository repo = null;

    private FriendRequestRepository(Context context, FriendRequestRemoteSourceInterface remoteSource) {
        this.context = context;
        //this.localSource
        this.remoteSource = remoteSource;
    }

    public static FriendRequestRepository getInstance(Context context, FriendRequestRemoteSourceInterface remoteSource){
        if(repo == null)
            repo = new FriendRequestRepository(context, remoteSource);
        return repo;
    }

    @Override
    public List<FriendRequestPojo> getRequests() {
        return remoteSource.getRequests();
    }


}
