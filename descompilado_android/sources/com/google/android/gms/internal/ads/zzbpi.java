package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpi implements zzdvi<Set<zzbqc<zzbmg>>> {
    private final zzbox zzfhi;

    private zzbpi(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpi zzq(zzbox zzboxVar) {
        return new zzbpi(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfhi.zzafz(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
