package net.nend.android.internal.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: NetworkChecker.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    public static boolean a(Context context) {
        NetworkInfo networkInfoC = c(context);
        return networkInfoC != null && networkInfoC.isConnectedOrConnecting();
    }

    public static boolean b(Context context) {
        NetworkInfo networkInfoC = c(context);
        return networkInfoC != null && networkInfoC.getType() == 1;
    }

    private static NetworkInfo c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static void a(Context context, @NonNull BroadcastReceiver broadcastReceiver) {
        context.registerReceiver(broadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void b(Context context, @NonNull BroadcastReceiver broadcastReceiver) {
        context.unregisterReceiver(broadcastReceiver);
    }
}
