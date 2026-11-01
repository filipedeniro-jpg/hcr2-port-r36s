package com.chartboost.sdk.impl;

import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class aq {
    private static String a = "CBTrace";
    private static final boolean b = a();

    private static boolean a() {
        File fileB;
        try {
            if (Log.isLoggable(a, 4) && s.a().c().equals("mounted") && (fileB = s.a().b()) != null) {
                return new File(fileB, ".chartboost/log_trace").exists();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void a(String str, String str2) {
        if (b) {
            Log.i(a, str + ": " + str2);
        }
    }

    public static void a(String str, boolean z) {
        if (b) {
            Log.i(a, str + ": " + z);
        }
    }

    public static void a(String str, Object obj) {
        if (b) {
            if (obj != null) {
                Log.i(a, str + ": " + obj.getClass().getName() + " " + obj.hashCode());
                return;
            }
            Log.i(a, str + ": null");
        }
    }

    public static void a(String str) {
        if (b) {
            Log.i(a, str);
        }
    }
}
