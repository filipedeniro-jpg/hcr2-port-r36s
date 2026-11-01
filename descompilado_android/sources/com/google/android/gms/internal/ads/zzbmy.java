package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmy implements zzdvi<zzbmv> {
    private final zzdvv<Set<zzbqc<zzbms>>> zzfdr;

    private zzbmy(zzdvv<Set<zzbqc<zzbms>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbmy zzi(zzdvv<Set<zzbqc<zzbms>>> zzdvvVar) {
        return new zzbmy(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbmv(this.zzfdr.get());
    }
}
