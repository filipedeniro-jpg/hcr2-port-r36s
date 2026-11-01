package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpj implements zzdvi<Set<zzbqc<zzbnv>>> {
    private final zzbox zzfhi;

    private zzbpj(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpj zzr(zzbox zzboxVar) {
        return new zzbpj(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfhi.zzagh(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
