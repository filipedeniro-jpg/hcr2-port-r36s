package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqq implements zzdvi<zzbqm> {
    private final zzdvv<Set<zzbqc<zzaeh>>> zzfdr;

    public zzbqq(zzdvv<Set<zzbqc<zzaeh>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbqm(this.zzfdr.get());
    }
}
