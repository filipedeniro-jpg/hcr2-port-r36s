package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdrx {
    private static final zzdrv zzhnd = zzbaz();
    private static final zzdrv zzhne = new zzdru();

    static zzdrv zzbax() {
        return zzhnd;
    }

    static zzdrv zzbay() {
        return zzhne;
    }

    private static zzdrv zzbaz() {
        try {
            return (zzdrv) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
