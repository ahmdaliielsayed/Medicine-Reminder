package com.ahmdalii.medicinereminder.friends.presenter;

import com.ahmdalii.medicinereminder.friends.repository.FriendRepo;
import com.ahmdalii.medicinereminder.friends.repository.FriendRepoInterface;
import com.ahmdalii.medicinereminder.friends.view.FriendsViewInterface;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.util.List;

public class FriendPresenter implements FriendPresenterInterface{

    FriendsViewInterface view;
    FriendRepoInterface repo;

    public FriendPresenter(FriendsViewInterface view, FriendRepoInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public List<RequestPojo> getFriends(String receiverId) {
        return repo.getFriends(receiverId);
    }
}
