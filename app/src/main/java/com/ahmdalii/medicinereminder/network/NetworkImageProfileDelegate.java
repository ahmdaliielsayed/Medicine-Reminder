package com.ahmdalii.medicinereminder.network;

import java.util.List;

public interface NetworkImageProfileDelegate {

    void onResponseSuccess(String uri);
    void onFailureResult(String error);
}
