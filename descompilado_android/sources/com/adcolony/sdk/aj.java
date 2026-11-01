package com.adcolony.sdk;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
class aj {
    aj() {
    }

    boolean a() {
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) activityC.getApplicationContext().getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
        } catch (SecurityException e) {
            new aa.a().a("SecurityException - please ensure you added the ").a("ACCESS_NETWORK_STATE permission: ").a(e.toString()).a(aa.g);
            return false;
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving activeNetworkInfo in ").a("ADCNetwork.using_wifi(): ").a(e2.toString()).a(aa.h);
            return false;
        }
    }

    boolean b() {
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) activityC.getApplicationContext().getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            int type = activeNetworkInfo.getType();
            return type == 0 || type >= 2;
        } catch (SecurityException e) {
            new aa.a().a("SecurityException - please ensure you added the ").a("ACCESS_NETWORK_STATE permission: ").a(e.toString()).a(aa.g);
            return false;
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving activeNetworkInfo in ").a("ADCNetwork.using_mobile(): ").a(e2.toString()).a(aa.h);
            return false;
        }
    }

    String c() {
        if (a()) {
            return "wifi";
        }
        return b() ? com.my.target.i.T : "none";
    }
}
