package com.ahmdalii.medicinereminder.splash.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.ahmdalii.medicinereminder.ConnectivityReceiver;
import com.ahmdalii.medicinereminder.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.NetworkStateReceiverListener {

    // Receiver that detects network state changes
    private ConnectivityReceiver networkStateReceiver;

    View connectionLostMain;
    FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        connectionLostMain = findViewById(R.id.connectionLostMain);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);

        startNetworkBroadcastReceiver(this);
    }

    public void startNetworkBroadcastReceiver(Context currentContext) {
        networkStateReceiver = new ConnectivityReceiver();
        networkStateReceiver.addListener((ConnectivityReceiver.NetworkStateReceiverListener) currentContext);
        registerNetworkBroadcastReceiver(currentContext);
    }

    /**
     * Register the NetworkStateReceiver with your activity
     */
    public void registerNetworkBroadcastReceiver(Context currentContext) {
        currentContext.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    /**
     Unregister the NetworkStateReceiver with your activity
     */
    public void unregisterNetworkBroadcastReceiver(Context currentContext) {
        currentContext.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void networkAvailable() {
        //Proceed with online actions in activity (e.g. hide offline UI from user, start services, etc...)
        fragmentContainerView.setVisibility(View.VISIBLE);
        connectionLostMain.setVisibility(View.GONE);
    }

    @Override
    public void networkUnavailable() {
        //Proceed with offline actions in activity (e.g. sInform user they are offline, stop services, etc...)
        fragmentContainerView.setVisibility(View.GONE);
        connectionLostMain.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        unregisterNetworkBroadcastReceiver(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        registerNetworkBroadcastReceiver(this);
        super.onResume();
    }
}