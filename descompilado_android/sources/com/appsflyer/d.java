package com.appsflyer;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
final class d implements InstallReferrerStateListener {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private a f121;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private InstallReferrerClient f122;

    d() {
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    protected final void m117(Context context, a aVar) {
        this.f121 = aVar;
        try {
            this.f122 = InstallReferrerClient.newBuilder(context).build();
            this.f122.startConnection(this);
        } catch (Throwable th) {
            AFLogger.afErrorLog("referrerClient -> startConnection", th);
        }
    }

    @Override // com.android.installreferrer.api.InstallReferrerStateListener
    public final void onInstallReferrerSetupFinished(int i) {
        HashMap map = new HashMap();
        map.put("code", String.valueOf(i));
        ReferrerDetails installReferrer = null;
        if (i == 0) {
            try {
                AFLogger.afDebugLog("InstallReferrer connected");
                if (this.f122.isReady()) {
                    installReferrer = this.f122.getInstallReferrer();
                    this.f122.endConnection();
                } else {
                    AFLogger.afWarnLog("ReferrerClient: InstallReferrer is not ready");
                    map.put(NotificationCompat.CATEGORY_ERROR, "ReferrerClient: InstallReferrer is not ready");
                }
            } catch (Throwable th) {
                StringBuilder sb = new StringBuilder("Failed to get install referrer: ");
                sb.append(th.getMessage());
                AFLogger.afWarnLog(sb.toString());
                map.put(NotificationCompat.CATEGORY_ERROR, th.getMessage());
            }
        } else if (i == 1 || i == 2) {
            AFLogger.afWarnLog("InstallReferrer not supported");
        } else {
            AFLogger.afWarnLog("responseCode not found.");
        }
        if (installReferrer != null) {
            try {
                if (installReferrer.getInstallReferrer() != null) {
                    map.put("val", installReferrer.getInstallReferrer());
                }
                map.put("clk", Long.toString(installReferrer.getReferrerClickTimestampSeconds()));
                map.put("install", Long.toString(installReferrer.getInstallBeginTimestampSeconds()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put("val", "-1");
                map.put("clk", "-1");
                map.put("install", "-1");
            }
        }
        a aVar = this.f121;
        if (aVar != null) {
            aVar.onHandleReferrer(map);
        }
    }

    @Override // com.android.installreferrer.api.InstallReferrerStateListener
    public final void onInstallReferrerServiceDisconnected() {
        AFLogger.afDebugLog("Install Referrer service disconnected");
    }
}
