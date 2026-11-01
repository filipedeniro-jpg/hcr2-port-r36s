package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdoq {
    private static final Class<?> zzhff = zzhg("libcore.io.Memory");
    private static final boolean zzhfg;

    static boolean zzaxg() {
        return (zzhff == null || zzhfg) ? false : true;
    }

    static Class<?> zzaxh() {
        return zzhff;
    }

    private static <T> Class<T> zzhg(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        zzhfg = zzhg("org.robolectric.Robolectric") != null;
    }
}
