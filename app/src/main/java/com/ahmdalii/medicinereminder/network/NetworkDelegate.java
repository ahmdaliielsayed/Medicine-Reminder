package com.ahmdalii.medicinereminder.network;

public interface NetworkDelegate {

    void onResponse();
    void onFailure(String error);
}
