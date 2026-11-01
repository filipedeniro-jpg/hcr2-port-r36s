package com.google.android.gms.games.internal;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.GmsLogger;

/* JADX INFO: loaded from: classes.dex */
public final class zzbd {
    private static final GmsLogger zzjd = new GmsLogger("Games");
    private static final GservicesValue<Boolean> zzje = GservicesValue.value("games.play_games_dogfood", false);

    public static void i(String str, String str2, Throwable th) {
        zzjd.i(str, str2, th);
    }

    public static void w(String str, String str2) {
        zzjd.w(str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        zzjd.w(str, str2, th);
    }

    public static void e(String str, String str2) {
        zzjd.e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        zzjd.e(str, str2, th);
    }
}
