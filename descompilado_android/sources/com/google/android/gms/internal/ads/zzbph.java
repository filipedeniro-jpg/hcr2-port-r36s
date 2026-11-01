package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbph implements zzdvi<Set<zzbqc<zzbnm>>> {
    private final zzbox zzfhi;

    private zzbph(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbph zzo(zzbox zzboxVar) {
        return new zzbph(zzboxVar);
    }

    public static Set<zzbqc<zzbnm>> zzp(zzbox zzboxVar) {
        return (Set) zzdvo.zza(zzboxVar.zzaga(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzp(this.zzfhi);
    }
}
