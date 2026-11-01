package com.ironsource.environment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkStateReceiver extends BroadcastReceiver {
    private boolean mConnected;
    private NetworkStateReceiverListener mListener;
    private ConnectivityManager mManager;

    public interface NetworkStateReceiverListener {
        void onNetworkAvailabilityChanged(boolean z);
    }

    public NetworkStateReceiver(Context context, NetworkStateReceiverListener networkStateReceiverListener) {
        this.mListener = networkStateReceiverListener;
        this.mManager = (ConnectivityManager) context.getSystemService("connectivity");
        checkAndSetState();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null || !checkAndSetState()) {
            return;
        }
        notifyState();
    }

    private boolean checkAndSetState() {
        boolean z = this.mConnected;
        NetworkInfo activeNetworkInfo = this.mManager.getActiveNetworkInfo();
        this.mConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        return z != this.mConnected;
    }

    private void notifyState() {
        NetworkStateReceiverListener networkStateReceiverListener = this.mListener;
        if (networkStateReceiverListener != null) {
            if (this.mConnected) {
                networkStateReceiverListener.onNetworkAvailabilityChanged(true);
            } else {
                networkStateReceiverListener.onNetworkAvailabilityChanged(false);
            }
        }
    }
}
