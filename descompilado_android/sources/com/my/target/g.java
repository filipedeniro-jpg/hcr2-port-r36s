package com.my.target;

import android.util.Log;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: Tracer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    private static final String TAG = "[myTarget]";
    public static boolean enabled = false;

    public static void a(@Nullable String str) {
        if (enabled) {
            if (str == null) {
                str = "null";
            }
            Log.d(TAG, str);
        }
    }

    public static void b(@Nullable String str) {
        if (enabled) {
            if (str == null) {
                str = "null";
            }
            Log.e(TAG, str);
        }
    }

    public static void c(@Nullable String str) {
        if (str == null) {
            str = "null";
        }
        Log.i(TAG, str);
    }

    private g() {
    }
}
