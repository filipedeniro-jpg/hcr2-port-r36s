package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbns implements zzdvi<zzbnq> {
    private final zzdvv<Set<zzbqc<zzbnv>>> zzfdr;

    private zzbns(zzdvv<Set<zzbqc<zzbnv>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbns zzm(zzdvv<Set<zzbqc<zzbnv>>> zzdvvVar) {
        return new zzbns(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbnq(this.zzfdr.get());
    }
}
