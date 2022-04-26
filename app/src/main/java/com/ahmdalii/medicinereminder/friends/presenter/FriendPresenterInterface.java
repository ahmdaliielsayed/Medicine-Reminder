package com.ahmdalii.medicinereminder.friends.presenter;

import com.ahmdalii.medicinereminder.healthtaker.repository.RequestPojo;

import java.util.List;

public interface FriendPresenterInterface {
    public List<RequestPojo> getFriends(String receiverId);
}
