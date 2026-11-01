package com.appsflyer;

import android.util.Log;
import androidx.annotation.NonNull;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AFLogger {

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static long f13 = System.currentTimeMillis();

    public enum LogLevel {
        NONE(0),
        ERROR(1),
        WARNING(2),
        INFO(3),
        DEBUG(4),
        VERBOSE(5);


        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private int f15;

        LogLevel(int i) {
            this.f15 = i;
        }

        public final int getLevel() {
            return this.f15;
        }
    }

    public static void afInfoLog(String str, boolean z) {
        if (LogLevel.INFO.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.i(AppsFlyerLib.LOG_TAG, m17(str, false));
        }
        if (z) {
            s.m187().m193("I", m17(str, true));
        }
    }

    public static void resetDeltaTime() {
        f13 = System.currentTimeMillis();
    }

    @NonNull
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static String m17(String str, boolean z) {
        if (!z && LogLevel.VERBOSE.getLevel() > AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            return str;
        }
        StringBuilder sb = new StringBuilder("(");
        sb.append(m16(System.currentTimeMillis() - f13));
        sb.append(") [");
        sb.append(Thread.currentThread().getName());
        sb.append("] ");
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static void m19(String str, Throwable th, boolean z) {
        if ((LogLevel.ERROR.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) && z) {
            Log.e(AppsFlyerLib.LOG_TAG, m17(str, false), th);
        }
        s.m187().m198(th);
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static void m18(String str) {
        if (LogLevel.WARNING.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.w(AppsFlyerLib.LOG_TAG, m17(str, false));
        }
        s.m187().m193("W", m17(str, true));
    }

    public static void afRDLog(String str) {
        if (LogLevel.VERBOSE.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.v(AppsFlyerLib.LOG_TAG, m17(str, false));
        }
        s.m187().m193("V", m17(str, true));
    }

    public static void afInfoLog(String str) {
        afInfoLog(str, true);
    }

    public static void afErrorLog(String str, Throwable th) {
        m19(str, th, false);
    }

    public static void afErrorLog(String str, Throwable th, boolean z) {
        m19(str, th, z);
    }

    public static void afWarnLog(String str) {
        m18(str);
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String m16(long j) {
        long hours = TimeUnit.MILLISECONDS.toHours(j);
        long millis = j - TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long millis2 = millis - TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis2);
        return String.format(Locale.getDefault(), "%02d:%02d:%02d:%03d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds), Long.valueOf(TimeUnit.MILLISECONDS.toMillis(millis2 - TimeUnit.SECONDS.toMillis(seconds))));
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static void m20(String str) {
        if (!AppsFlyerProperties.getInstance().isLogsDisabledCompletely()) {
            Log.d(AppsFlyerLib.LOG_TAG, m17(str, false));
        }
        s.m187().m193("F", str);
    }

    public static void afDebugLog(String str) {
        if (LogLevel.DEBUG.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            Log.d(AppsFlyerLib.LOG_TAG, m17(str, false));
        }
        s.m187().m193("D", m17(str, true));
    }
}
