package com.appsflyer;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseInstanceIdListener extends FirebaseInstanceIdService {
    /* JADX WARN: Multi-variable type inference failed */
    public void onTokenRefresh() {
        String token;
        super.onTokenRefresh();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            token = FirebaseInstanceId.getInstance().getToken();
        } catch (Throwable th) {
            AFLogger.afErrorLog("Error registering for uninstall tracking", th);
            token = null;
        }
        if (token != null) {
            AFLogger.afInfoLog("Firebase Refreshed Token = ".concat(String.valueOf(token)));
            c.a.C0031c c0031cM113 = c.a.C0031c.m113(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            c.a.C0031c c0031c = new c.a.C0031c(jCurrentTimeMillis, token);
            if (c0031cM113.m115(c0031c)) {
                u.m217(getApplicationContext(), c0031c);
            }
        }
    }
}
