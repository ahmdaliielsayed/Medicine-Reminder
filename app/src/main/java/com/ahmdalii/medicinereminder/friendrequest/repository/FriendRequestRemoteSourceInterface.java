package com.ahmdalii.medicinereminder.friendrequest.repository;

import com.ahmdalii.medicinereminder.friendrequest.view.FriendRequestViewInterface;
import com.ahmdalii.medicinereminder.friends.view.FriendsViewInterface;
import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.util.List;

public interface FriendRequestRemoteSourceInterface {
    public List<RequestPojo> getRequests(String receiverId);
    public void addFriend(String receiverId, String senderId);
    public void setView(FriendRequestViewInterface view);
}
