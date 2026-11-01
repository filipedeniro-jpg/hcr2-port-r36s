package com.fingersoft.liveops_sdk.utils;

/* JADX INFO: loaded from: classes.dex */
public class Log {
    public static boolean LOG = false;

    public static void i(String str, String str2) {
        if (LOG) {
            android.util.Log.i(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (LOG) {
            android.util.Log.e(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (LOG) {
            android.util.Log.d(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (LOG) {
            android.util.Log.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (LOG) {
            android.util.Log.w(str, str2);
        }
    }
}
