package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzdmu {
    static final /* synthetic */ int[] zzhcu;
    static final /* synthetic */ int[] zzhcv = new int[zzdmx.values().length];

    static {
        try {
            zzhcv[zzdmx.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzhcv[zzdmx.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzhcv[zzdmx.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        zzhcu = new int[zzdmz.values().length];
        try {
            zzhcu[zzdmz.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzhcu[zzdmz.DO_NOT_USE_CRUNCHY_UNCOMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzhcu[zzdmz.COMPRESSED.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
