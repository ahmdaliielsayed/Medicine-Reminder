package com.ahmdalii.medicinereminder.friends.repository;

import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.util.List;

public interface FriendRepoInterface {
    public List<RequestPojo> getFriends(String receiverId);
}
