package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdqb implements zzdrk {
    private static final zzdqb zzhjy = new zzdqb();

    private zzdqb() {
    }

    public static zzdqb zzazg() {
        return zzhjy;
    }

    @Override // com.google.android.gms.internal.ads.zzdrk
    public final boolean zzc(Class<?> cls) {
        return zzdqd.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.ads.zzdrk
    public final zzdrl zzd(Class<?> cls) {
        if (!zzdqd.class.isAssignableFrom(cls)) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unsupported message type: ".concat(strValueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzdrl) zzdqd.zzf(cls.asSubclass(zzdqd.class)).zza(zzdqd.zzd.zzhkj, (Object) null, (Object) null);
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(strValueOf2.length() != 0 ? "Unable to get message info for ".concat(strValueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
