package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpf implements zzdvi<Set<zzbqc<zzbmt>>> {
    private final zzbox zzfhi;

    private zzbpf(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpf zzn(zzbox zzboxVar) {
        return new zzbpf(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfhi.zzagg(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
