package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpg implements zzdvi<Set<zzbqc<zzbmp>>> {
    private final zzbox zzfhi;

    public zzbpg(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfhi.zzagc(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
