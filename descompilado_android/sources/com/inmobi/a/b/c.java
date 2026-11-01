package com.inmobi.a.b;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.inmobi.a.o;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: WifiScanner.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final String a = "c";

    @SuppressLint({"StaticFieldLeak"})
    private static Context b = null;
    private static a c = null;
    private static Handler d = null;
    private static boolean e = false;
    private static List<com.inmobi.a.b.a> g;
    private static final IntentFilter f = new IntentFilter("android.net.wifi.SCAN_RESULTS");
    private static Runnable h = new Runnable() { // from class: com.inmobi.a.b.c.1
        @Override // java.lang.Runnable
        public final void run() {
            a aVar = c.c;
            c.f();
            if (aVar != null) {
                aVar.a();
            }
        }
    };

    @SuppressLint({"WifiManagerPotentialLeak", "MissingPermission"})
    private static final BroadcastReceiver i = new BroadcastReceiver() { // from class: com.inmobi.a.b.c.2
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            a aVar = c.c;
            WifiManager wifiManager = (WifiManager) c.b.getSystemService("wifi");
            c.f();
            if (aVar == null || wifiManager == null) {
                return;
            }
            List<ScanResult> scanResults = wifiManager.getScanResults();
            int i2 = o.a().a.a.j;
            boolean zA = b.a(i2);
            boolean zA2 = b.a(i2, 1);
            ArrayList arrayList = new ArrayList();
            if (scanResults != null) {
                for (ScanResult scanResult : scanResults) {
                    if (!b.a(zA, scanResult.SSID)) {
                        com.inmobi.a.b.a aVar2 = null;
                        if (scanResult != null) {
                            com.inmobi.a.b.a aVar3 = new com.inmobi.a.b.a();
                            aVar3.a = b.a(scanResult.BSSID);
                            aVar3.b = zA2 ? null : scanResult.SSID;
                            aVar3.c = scanResult.level;
                            aVar2 = aVar3;
                        }
                        arrayList.add(aVar2);
                    }
                }
            }
            List unused = c.g = arrayList;
            aVar.a(c.g);
        }
    };

    /* JADX INFO: compiled from: WifiScanner.java */
    public interface a {
        void a();

        void a(List<com.inmobi.a.b.a> list);
    }

    public static boolean a(a aVar) {
        b = com.inmobi.commons.a.a.b();
        return a(Looper.myLooper(), aVar);
    }

    public static List<com.inmobi.a.b.a> a() {
        return g;
    }

    @SuppressLint({"MissingPermission"})
    private static synchronized boolean a(Looper looper, a aVar) {
        if (d != null) {
            return false;
        }
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return false;
        }
        WifiManager wifiManager = (WifiManager) contextB.getSystemService("wifi");
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            c = aVar;
            Handler handler = new Handler(looper);
            d = handler;
            handler.postDelayed(h, 10000L);
            if (!e) {
                e = true;
                b.registerReceiver(i, f, null, d);
            }
            return wifiManager.startScan();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void f() {
        if (d == null) {
            return;
        }
        d.removeCallbacks(h);
        if (e) {
            e = false;
            try {
                b.unregisterReceiver(i);
            } catch (IllegalArgumentException unused) {
            }
        }
        d = null;
        c = null;
        b = null;
    }
}
