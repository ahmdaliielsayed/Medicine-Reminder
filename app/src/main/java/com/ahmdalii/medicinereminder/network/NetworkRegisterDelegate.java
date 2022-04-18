package com.ahmdalii.medicinereminder.network;

public interface NetworkRegisterDelegate {

    void onResponse(String userId);
    void onFailure(String error);
}
