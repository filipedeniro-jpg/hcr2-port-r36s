package com.inmobi.commons.core.utilities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: SystemBroadcastObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    private static final String a = "g";
    private static HashMap<String, CopyOnWriteArrayList<b>> b = new HashMap<>();
    private static HashMap<String, a> c = new HashMap<>();
    private static HashMap<String, ConnectivityManager.NetworkCallback> d = new HashMap<>();
    private static final Object e = new Object();
    private static volatile g f;

    /* JADX INFO: compiled from: SystemBroadcastObserver.java */
    public interface b {
        void a(boolean z);
    }

    public static g a() {
        g gVar = f;
        if (gVar == null) {
            synchronized (e) {
                gVar = f;
                if (gVar == null) {
                    gVar = new g();
                    f = gVar;
                }
            }
        }
        return gVar;
    }

    public final void a(String str, b bVar) {
        Context contextB;
        CopyOnWriteArrayList<b> copyOnWriteArrayList = b.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.add(bVar);
        } else {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            copyOnWriteArrayList.add(bVar);
        }
        b.put(str, copyOnWriteArrayList);
        if (copyOnWriteArrayList.size() != 1 || (contextB = com.inmobi.commons.a.a.b()) == null) {
            return;
        }
        if ("SYSTEM_CONNECTIVITY_CHANGE".equals(str)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) contextB.getSystemService("connectivity");
            if (connectivityManager != null) {
                ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.inmobi.commons.core.utilities.g.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onAvailable(Network network) {
                        super.onAvailable(network);
                        g.b(true, "SYSTEM_CONNECTIVITY_CHANGE");
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onLost(Network network) {
                        super.onLost(network);
                        g.b(false, "SYSTEM_CONNECTIVITY_CHANGE");
                    }
                };
                d.put(str, networkCallback);
                connectivityManager.registerDefaultNetworkCallback(networkCallback);
                return;
            }
            return;
        }
        a aVar = new a();
        c.put(str, aVar);
        contextB.registerReceiver(aVar, new IntentFilter(str));
    }

    public final void a(b bVar) {
        if (Build.VERSION.SDK_INT < 28) {
            a("android.net.conn.CONNECTIVITY_CHANGE", bVar);
        } else {
            a("SYSTEM_CONNECTIVITY_CHANGE", bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(boolean z, String str) {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = b.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator<b> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(z);
                } catch (Exception e2) {
                    new StringBuilder("SDK encountered unexpected error in SystemBroadcastObserver.onServiceChanged event handler; ").append(e2.getMessage());
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(b bVar, String str) {
        Context contextB;
        CopyOnWriteArrayList<b> copyOnWriteArrayList = b.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(bVar);
            if (copyOnWriteArrayList.size() != 0 || (contextB = com.inmobi.commons.a.a.b()) == null) {
                return;
            }
            if ("SYSTEM_CONNECTIVITY_CHANGE".equals(str) && d.get(str) != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) contextB.getSystemService("connectivity");
                if (connectivityManager != null) {
                    connectivityManager.unregisterNetworkCallback(d.get(str));
                    d.remove(str);
                    return;
                }
                return;
            }
            if (c.get(str) != null) {
                contextB.unregisterReceiver(c.get(str));
                c.remove(str);
            }
        }
    }

    /* JADX INFO: compiled from: SystemBroadcastObserver.java */
    static final class a extends BroadcastReceiver {
        private static final String a = "a";

        a() {
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"MissingPermission"})
        public final void onReceive(Context context, Intent intent) {
            NetworkInfo activeNetworkInfo;
            if (intent != null) {
                try {
                    if (intent.getAction() != null) {
                        boolean z = false;
                        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                                z = true;
                            }
                        } else if (!"android.os.action.DEVICE_IDLE_MODE_CHANGED".equalsIgnoreCase(intent.getAction())) {
                            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                                z = true;
                            }
                        } else {
                            PowerManager powerManager = (PowerManager) context.getSystemService("power");
                            if (powerManager != null && Build.VERSION.SDK_INT >= 23 && powerManager.isDeviceIdleMode()) {
                                z = true;
                            }
                        }
                        g.b(z, intent.getAction());
                        StringBuilder sb = new StringBuilder();
                        sb.append(intent.getAction());
                        sb.append(" Availability:");
                        sb.append(z);
                    }
                } catch (Exception e) {
                    new StringBuilder("SDK encountered unexpected error in SystemBroadReceiver.onReceive handler; ").append(e.getMessage());
                }
            }
        }
    }
}
