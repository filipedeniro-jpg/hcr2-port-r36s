package com.inmobi.commons.core.utilities;

import android.util.Log;
import com.google.android.gms.games.GamesStatusCodes;

/* JADX INFO: loaded from: classes2.dex */
public final class Logger {
    private static InternalLogLevel a = InternalLogLevel.NONE;

    public enum InternalLogLevel {
        NONE,
        ERROR,
        DEBUG,
        INTERNAL
    }

    /* JADX INFO: renamed from: com.inmobi.commons.core.utilities.Logger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[InternalLogLevel.values().length];

        static {
            try {
                a[InternalLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[InternalLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[InternalLogLevel.INTERNAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void a(InternalLogLevel internalLogLevel, String str, String str2) {
        if (internalLogLevel.ordinal() <= a.ordinal()) {
            int i = AnonymousClass1.a[internalLogLevel.ordinal()];
            if (i == 1) {
                Log.e("[InMobi]", str2);
                return;
            }
            if (i == 2) {
                Log.d("[InMobi]", str2);
                return;
            }
            if (i != 3) {
                return;
            }
            if (str2.length() <= 4000) {
                Log.d(str, str2);
                return;
            }
            while (str2.length() > 4000) {
                Log.d(str, str2.substring(0, GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND));
                str2 = str2.substring(GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND);
            }
            Log.d(str, str2);
        }
    }

    public static void a(InternalLogLevel internalLogLevel, String str, String str2, Throwable th) {
        if (internalLogLevel.ordinal() <= a.ordinal()) {
            int i = AnonymousClass1.a[internalLogLevel.ordinal()];
            if (i == 1) {
                Log.e("[InMobi]", str2, th);
            } else if (i == 2) {
                Log.d("[InMobi]", str2, th);
            } else {
                if (i != 3) {
                    return;
                }
                Log.d(str, str2, th);
            }
        }
    }

    public static void a(InternalLogLevel internalLogLevel) {
        a = internalLogLevel;
    }
}
