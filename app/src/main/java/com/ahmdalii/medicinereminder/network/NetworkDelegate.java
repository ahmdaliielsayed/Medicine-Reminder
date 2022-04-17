package com.ahmdalii.medicinereminder.network;

public interface NetworkDelegate {

    void onResponse(String userId);
    void onFailure(String error);
}
