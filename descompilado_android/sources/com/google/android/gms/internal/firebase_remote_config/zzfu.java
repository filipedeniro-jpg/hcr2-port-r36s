package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzfu {
    private static final Class<?> zzon = zzbi("libcore.io.Memory");
    private static final boolean zzoo;

    static boolean zzeu() {
        return (zzon == null || zzoo) ? false : true;
    }

    static Class<?> zzev() {
        return zzon;
    }

    private static <T> Class<T> zzbi(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        zzoo = zzbi("org.robolectric.Robolectric") != null;
    }
}
