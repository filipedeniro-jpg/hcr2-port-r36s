package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzag {
    private static String TAG = "Volley";
    public static boolean DEBUG = Log.isLoggable("Volley", 2);
    private static final String CLASS_NAME = zzag.class.getName();

    public static void v(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, zza(str, objArr));
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static class zza {
        public static final boolean zzbl = zzag.DEBUG;
        private final List<zzai> zzbm = new ArrayList();
        private boolean zzbn = false;

        zza() {
        }

        public final synchronized void zza(String str, long j) {
            if (this.zzbn) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.zzbm.add(new zzai(str, j, SystemClock.elapsedRealtime()));
        }

        public final synchronized void zzc(String str) {
            this.zzbn = true;
            long j = this.zzbm.size() == 0 ? 0L : this.zzbm.get(this.zzbm.size() - 1).time - this.zzbm.get(0).time;
            if (j <= 0) {
                return;
            }
            long j2 = this.zzbm.get(0).time;
            zzag.d("(%-4d ms) %s", Long.valueOf(j), str);
            for (zzai zzaiVar : this.zzbm) {
                long j3 = zzaiVar.time;
                zzag.d("(+%-4d) [%2d] %s", Long.valueOf(j3 - j2), Long.valueOf(zzaiVar.zzbo), zzaiVar.name);
                j2 = j3;
            }
        }

        protected final void finalize() throws Throwable {
            if (this.zzbn) {
                return;
            }
            zzc("Request on the loose");
            zzag.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }

    public static void d(String str, Object... objArr) {
        Log.d(TAG, zza(str, objArr));
    }

    public static void e(String str, Object... objArr) {
        Log.e(TAG, zza(str, objArr));
    }

    public static void zza(Throwable th, String str, Object... objArr) {
        Log.e(TAG, zza(str, objArr), th);
    }

    private static String zza(String str, Object... objArr) {
        String string;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClassName().equals(CLASS_NAME)) {
                String className = stackTrace[i].getClassName();
                String strSubstring = className.substring(className.lastIndexOf(46) + 1);
                String strSubstring2 = strSubstring.substring(strSubstring.lastIndexOf(36) + 1);
                String methodName = stackTrace[i].getMethodName();
                StringBuilder sb = new StringBuilder(String.valueOf(strSubstring2).length() + 1 + String.valueOf(methodName).length());
                sb.append(strSubstring2);
                sb.append(".");
                sb.append(methodName);
                string = sb.toString();
                return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), string, str);
            }
        }
        string = "<unknown>";
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), string, str);
    }
}
