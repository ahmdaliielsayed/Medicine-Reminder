package com.ahmdalii.medicinereminder.friendrequest.presenter;

import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.util.List;

public interface FriendRequestPresenterInterface {
    public List<RequestPojo> getRequests(String receiverId);
    public void addFriend(String receiverId, String senderId);
}
