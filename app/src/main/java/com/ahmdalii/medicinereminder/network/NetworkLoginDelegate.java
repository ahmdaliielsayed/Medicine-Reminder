package com.ahmdalii.medicinereminder.network;

import com.ahmdalii.medicinereminder.model.User;

public interface NetworkLoginDelegate {

    void onResponse(String userId);
    void onResponse(User user);
    void onFailure(String error);
}
