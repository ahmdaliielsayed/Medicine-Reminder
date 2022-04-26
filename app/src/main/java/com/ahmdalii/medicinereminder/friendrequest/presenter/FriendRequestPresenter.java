package com.ahmdalii.medicinereminder.friendrequest.presenter;

import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestRepository;
import com.ahmdalii.medicinereminder.friendrequest.repository.FriendRequestRepositoryInterface;
import com.ahmdalii.medicinereminder.friendrequest.view.FriendRequestViewInterface;

public class FriendRequestPresenter implements FriendRequestPresenterInterface{
    FriendRequestViewInterface view;
    FriendRequestRepositoryInterface repo;

    public FriendRequestPresenter(FriendRequestViewInterface view, FriendRequestRepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }
}
