package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdri {
    private static final zzdrg zzhmj = zzbas();
    private static final zzdrg zzhmk = new zzdrj();

    static zzdrg zzbaq() {
        return zzhmj;
    }

    static zzdrg zzbar() {
        return zzhmk;
    }

    private static zzdrg zzbas() {
        try {
            return (zzdrg) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
