package com.inmobi.a.b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.inmobi.a.o;
import com.inmobi.a.p;
import com.inmobi.commons.core.utilities.e;

/* JADX INFO: compiled from: WifiInfoUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    static boolean a(int i) {
        return !a(i, 2);
    }

    private static a a(boolean z, boolean z2) {
        a aVar;
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) contextB.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                String bssid = connectionInfo.getBSSID();
                String ssid = connectionInfo.getSSID();
                if (bssid != null && !a(z, ssid)) {
                    aVar = new a();
                    try {
                        aVar.a = a(bssid);
                        String strSubstring = (ssid != null && ssid.startsWith("\"") && ssid.endsWith("\"")) ? ssid.substring(1, ssid.length() - 1) : ssid;
                        if (z2) {
                            strSubstring = null;
                        }
                        aVar.b = strSubstring;
                        aVar.c = connectionInfo.getRssi();
                        aVar.d = connectionInfo.getIpAddress();
                        return aVar;
                    } catch (Exception e) {
                        e = e;
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        return aVar;
                    }
                }
            }
            return null;
        } catch (Exception e2) {
            e = e2;
            aVar = null;
        }
    }

    static boolean a(boolean z, String str) {
        return z && str != null && str.endsWith("_nomap");
    }

    static long a(String str) {
        String[] strArrSplit = str.split("\\:");
        byte[] bArr = new byte[6];
        for (int i = 0; i < 6; i++) {
            try {
                bArr[i] = (byte) Integer.parseInt(strArrSplit[i], 16);
            } catch (NumberFormatException unused) {
                return 0L;
            }
        }
        return ((((long) bArr[0]) & 255) << 40) | ((((long) bArr[3]) & 255) << 16) | (((long) bArr[5]) & 255) | ((((long) bArr[4]) & 255) << 8) | ((((long) bArr[2]) & 255) << 24) | ((((long) bArr[1]) & 255) << 32);
    }

    public static a a() {
        boolean z = false;
        if (!(com.inmobi.commons.a.a.a() && e.a(com.inmobi.commons.a.a.b(), "signals", "android.permission.ACCESS_WIFI_STATE"))) {
            return null;
        }
        p.b bVar = o.a().a.a;
        if (bVar.l && bVar.a) {
            z = true;
        }
        if (!z) {
            return null;
        }
        int i = o.a().a.a.j;
        return a(a(i), a(i, 1));
    }
}
