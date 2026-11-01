package com.appsflyer;

import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

/* JADX INFO: loaded from: classes.dex */
public class GcmInstanceIdListener extends InstanceIDListenerService {
    @Override // com.google.android.gms.iid.InstanceIDListenerService
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String string = AppsFlyerProperties.getInstance().getString("gcmProjectNumber");
        long jCurrentTimeMillis = System.currentTimeMillis();
        String token = null;
        try {
            token = InstanceID.getInstance(getApplicationContext()).getToken(string, "GCM", null);
        } catch (Throwable th) {
            AFLogger.afErrorLog("Error registering for uninstall tracking", th);
        }
        if (token != null) {
            AFLogger.afInfoLog("GCM Refreshed Token = ".concat(String.valueOf(token)));
            c.a.C0031c c0031cM113 = c.a.C0031c.m113(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            c.a.C0031c c0031c = new c.a.C0031c(jCurrentTimeMillis, token);
            if (c0031cM113.m115(c0031c)) {
                u.m217(getApplicationContext(), c0031c);
            }
        }
    }
}
