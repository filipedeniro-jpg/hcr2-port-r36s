package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdrc implements zzdrk {
    private zzdrk[] zzhme;

    zzdrc(zzdrk... zzdrkVarArr) {
        this.zzhme = zzdrkVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzdrk
    public final boolean zzc(Class<?> cls) {
        for (zzdrk zzdrkVar : this.zzhme) {
            if (zzdrkVar.zzc(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzdrk
    public final zzdrl zzd(Class<?> cls) {
        for (zzdrk zzdrkVar : this.zzhme) {
            if (zzdrkVar.zzc(cls)) {
                return zzdrkVar.zzd(cls);
            }
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "No factory is available for message type: ".concat(strValueOf) : new String("No factory is available for message type: "));
    }
}
