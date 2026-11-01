package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmh implements zzdvi<zzbmf> {
    private final zzdvv<Set<zzbqc<zzth>>> zzfdr;

    private zzbmh(zzdvv<Set<zzbqc<zzth>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbmh zzg(zzdvv<Set<zzbqc<zzth>>> zzdvvVar) {
        return new zzbmh(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbmf(this.zzfdr.get());
    }
}
