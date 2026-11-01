package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxv implements zzdvi<zzcxm> {
    private final zzdvv<Set<zzbqc<zzcxt>>> zzfdr;

    private zzcxv(zzdvv<Set<zzbqc<zzcxt>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzcxv zzam(zzdvv<Set<zzbqc<zzcxt>>> zzdvvVar) {
        return new zzcxv(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcxm(this.zzfdr.get());
    }
}
