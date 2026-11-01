package com.appsflyer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class SingleInstallBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        if (intent == null) {
            return;
        }
        try {
            stringExtra = intent.getStringExtra("referrer");
        } catch (Throwable th) {
            AFLogger.afErrorLog("error in BroadcastReceiver ", th);
            stringExtra = null;
        }
        if (stringExtra != null) {
            if (stringExtra.contains("AppsFlyer_Test") && intent.getStringExtra("TestIntegrationMode") != null) {
                AppsFlyerLib.getInstance().m77(context, intent);
                return;
            } else if (context.getSharedPreferences("appsflyer-data", 0).getString("referrer", null) != null) {
                AppsFlyerLib.getInstance();
                AppsFlyerLib.m34(context, stringExtra);
                return;
            }
        }
        String string = AppsFlyerProperties.getInstance().getString("referrer_timestamp");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (string == null || jCurrentTimeMillis - Long.valueOf(string).longValue() >= 2000) {
            AFLogger.afInfoLog("SingleInstallBroadcastReceiver called");
            AppsFlyerLib.getInstance().m77(context, intent);
            AppsFlyerProperties.getInstance().set("referrer_timestamp", String.valueOf(System.currentTimeMillis()));
        }
    }
}
