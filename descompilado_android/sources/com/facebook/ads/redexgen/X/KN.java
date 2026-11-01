package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KN {
    private KN() {
    }

    public static KO B(Context context) {
        int result = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
        if (result != 0) {
            return KO.UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return KO.NONE;
        }
        if (activeNetworkInfo.getType() == 0) {
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return KO.MOBILE_2G;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return KO.MOBILE_3G;
                case 13:
                    return KO.MOBILE_4G;
                default:
                    return KO.UNKNOWN;
            }
        }
        return KO.MOBILE_INTERNET;
    }
}
