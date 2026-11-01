package com.fingersoft.ads_sdk.advertising.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;

/* JADX INFO: loaded from: classes.dex */
public class AdNetworkMonitor extends BroadcastReceiver {
    public static boolean isConnected = false;
    public static NetworkChangeListener listener;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            AdUtils.log("Received...");
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
            AdUtils.log("Connected = " + isConnected);
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
