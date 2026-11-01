package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzhe {
    static final /* synthetic */ int[] zzqm;
    static final /* synthetic */ int[] zzqn = new int[zzhs.values().length];

    static {
        try {
            zzqn[zzhs.BYTE_STRING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzqn[zzhs.MESSAGE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzqn[zzhs.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        zzqm = new int[zzhh.values().length];
        try {
            zzqm[zzhh.MAP.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzqm[zzhh.VECTOR.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzqm[zzhh.SCALAR.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
