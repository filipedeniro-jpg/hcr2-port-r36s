package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpe implements zzdvi<Set<zzbqc<zzth>>> {
    private final zzbox zzfhi;

    private zzbpe(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpe zzm(zzbox zzboxVar) {
        return new zzbpe(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfhi.zzagf(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
