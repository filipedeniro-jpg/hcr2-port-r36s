package com.fingersoft.hcr2;

/* JADX INFO: loaded from: classes.dex */
public class Log {
    public static void d(String str, String str2) {
        if (Config.ENABLE_ANDROID_LOG) {
            android.util.Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (Config.ENABLE_ANDROID_LOG) {
            android.util.Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (Config.ENABLE_ANDROID_LOG) {
            android.util.Log.w(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (Config.ENABLE_ANDROID_LOG) {
            android.util.Log.e(str, str2);
        }
    }
}
