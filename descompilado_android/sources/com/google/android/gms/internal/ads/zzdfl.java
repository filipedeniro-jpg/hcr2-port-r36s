package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzdfl {
    static final /* synthetic */ int[] zzgsw;
    static final /* synthetic */ int[] zzgsx;
    static final /* synthetic */ int[] zzgsy = new int[zzdhg.values().length];

    static {
        try {
            zzgsy[zzdhg.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzgsy[zzdhg.DO_NOT_USE_CRUNCHY_UNCOMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzgsy[zzdhg.COMPRESSED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        zzgsx = new int[zzdii.values().length];
        try {
            zzgsx[zzdii.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzgsx[zzdii.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzgsx[zzdii.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
        zzgsw = new int[zzdin.values().length];
        try {
            zzgsw[zzdin.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zzgsw[zzdin.SHA256.ordinal()] = 2;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            zzgsw[zzdin.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError unused9) {
        }
    }
}
