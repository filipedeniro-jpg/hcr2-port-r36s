package com.fingersoft.account_sdk;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AdUtils {
    public static String TAG = "Android_account_sdk";
    public static boolean logEnabled = false;

    public static void log(String str) {
        if (str != null && logEnabled) {
            Log.d(TAG, str);
        }
    }
}
