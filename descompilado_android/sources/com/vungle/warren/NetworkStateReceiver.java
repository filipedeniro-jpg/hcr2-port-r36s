package com.vungle.warren;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkStateReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context != null) {
            if (!Vungle.isInitialized()) {
                enableBroadcastReceiver(context, false);
            } else {
                Vungle.handleApkDirectDownloads(context);
            }
        }
    }

    public static void enableBroadcastReceiver(Context context, boolean z) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) NetworkStateReceiver.class), z ? 1 : 2, 1);
    }
}
