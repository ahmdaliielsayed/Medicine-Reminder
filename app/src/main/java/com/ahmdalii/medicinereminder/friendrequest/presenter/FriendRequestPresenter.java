package com.ahmdalii.medicinereminder.friendrequest.presenter;

import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestRepository;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestRepositoryInterface;
import com.ahmdalii.medicinereminder.friendrequest.view.FriendRequestViewInterface;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.util.List;

public class FriendRequestPresenter implements FriendRequestPresenterInterface{
    FriendRequestViewInterface view;
    FriendRequestRepositoryInterface repo;

    public FriendRequestPresenter(FriendRequestViewInterface view, FriendRequestRepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    public List<RequestPojo> getRequests(String receiverId) {
        return repo.getRequests(receiverId);
    }
}
