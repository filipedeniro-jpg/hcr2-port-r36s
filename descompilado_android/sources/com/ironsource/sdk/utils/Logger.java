package com.ironsource.sdk.utils;

import android.text.TextUtils;
import android.util.Log;
import com.ironsource.sdk.data.SSAEnums;

/* JADX INFO: loaded from: classes2.dex */
public class Logger {
    private static boolean enableLogging;

    public static void enableLogging(int i) {
        if (SSAEnums.DebugMode.MODE_0.getValue() == i) {
            enableLogging = false;
        } else {
            enableLogging = true;
        }
    }

    public static void i(String str, String str2) {
        if (enableLogging) {
            Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (!enableLogging || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2, th);
    }

    public static void e(String str, String str2) {
        if (enableLogging) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (enableLogging) {
            Log.e(str, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (enableLogging) {
            Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (enableLogging) {
            Log.w(str, str2, th);
        }
    }

    public static void d(String str, String str2) {
        if (enableLogging) {
            Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (enableLogging) {
            Log.d(str, str2, th);
        }
    }

    public static void v(String str, String str2) {
        if (enableLogging) {
            Log.v(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (enableLogging) {
            Log.v(str, str2, th);
        }
    }
}
