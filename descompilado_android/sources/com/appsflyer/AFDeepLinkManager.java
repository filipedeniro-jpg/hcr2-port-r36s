package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AFDeepLinkManager {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static Uri f1;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static AFDeepLinkManager f2;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static Uri f3;
    protected int currentActivityHash = -1;

    private AFDeepLinkManager() {
    }

    public static AFDeepLinkManager getInstance() {
        if (f2 == null) {
            f2 = new AFDeepLinkManager();
        }
        return f2;
    }

    protected void processIntentForDeepLink(Intent intent, Context context, Map<String, Object> map) {
        Uri data = (intent == null || !"android.intent.action.VIEW".equals(intent.getAction())) ? null : intent.getData();
        if (data != null) {
            boolean z = AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false);
            boolean z2 = (intent.getFlags() & 4194304) == 0;
            if (intent.hasExtra("appsflyer_click_ts") && !z) {
                long longExtra = intent.getLongExtra("appsflyer_click_ts", 0L);
                long j = AppsFlyerProperties.getInstance().getLong("appsflyer_click_consumed_ts", 0L);
                if (longExtra != 0 && longExtra != j) {
                    AppsFlyerLib.getInstance().handleDeepLinkCallback(context, map, data);
                    AppsFlyerProperties.getInstance().set("appsflyer_click_consumed_ts", longExtra);
                    return;
                }
                StringBuilder sb = new StringBuilder("skipping re-use of previously consumed deep link: ");
                sb.append(data.toString());
                sb.append(" w/Ex: ");
                sb.append(String.valueOf(longExtra));
                AFLogger.afInfoLog(sb.toString());
                return;
            }
            if (z || z2) {
                Boolean boolValueOf = Boolean.valueOf(z2);
                Uri uri = f1;
                if (uri == null || !data.equals(uri)) {
                    AppsFlyerLib.getInstance().handleDeepLinkCallback(context, map, data);
                    f1 = data;
                    return;
                } else {
                    StringBuilder sb2 = new StringBuilder("skipping re-use of previously consumed deep link: ");
                    sb2.append(data.toString());
                    sb2.append(boolValueOf.booleanValue() ? " w/sT" : " w/cAPI");
                    AFLogger.afInfoLog(sb2.toString());
                    return;
                }
            }
            if (this.currentActivityHash != AppsFlyerProperties.getInstance().getInt("lastActivityHash", 0)) {
                AppsFlyerLib.getInstance().handleDeepLinkCallback(context, map, data);
                AppsFlyerProperties.getInstance().set("lastActivityHash", this.currentActivityHash);
                return;
            }
            StringBuilder sb3 = new StringBuilder("skipping re-use of previously consumed deep link: ");
            sb3.append(data.toString());
            sb3.append(" w/hC: ");
            sb3.append(String.valueOf(this.currentActivityHash));
            AFLogger.afInfoLog(sb3.toString());
            return;
        }
        if (f3 != null) {
            AppsFlyerLib.getInstance().handleDeepLinkCallback(context, map, f3);
            StringBuilder sb4 = new StringBuilder("using trampoline Intent fallback with URI : ");
            sb4.append(f3.toString());
            AFLogger.afInfoLog(sb4.toString());
            f3 = null;
            return;
        }
        if (AppsFlyerLib.getInstance().latestDeepLink != null) {
            AppsFlyerLib.getInstance().handleDeepLinkCallback(context, map, AppsFlyerLib.getInstance().latestDeepLink);
        }
    }

    protected void collectIntentsFromActivities(Intent intent) {
        if (((intent == null || !"android.intent.action.VIEW".equals(intent.getAction())) ? null : intent.getData()) == null || intent.getData() == f3) {
            return;
        }
        f3 = intent.getData();
    }
}
