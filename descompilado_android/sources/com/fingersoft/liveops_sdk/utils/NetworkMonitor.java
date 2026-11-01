package com.fingersoft.liveops_sdk.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
public class NetworkMonitor extends BroadcastReceiver {
    public static boolean isConnected = false;
    public static NetworkListener listener;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            CommonUtils.log("Received...");
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
            CommonUtils.log("Connected = " + isConnected);
            if (listener != null) {
                listener.onConnectivityChanged(isConnected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasConnection() {
        return isConnected;
    }
}
