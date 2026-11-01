package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbnn implements zzdvi<zzbnl> {
    private final zzdvv<Set<zzbqc<zzbnm>>> zzfdr;

    private zzbnn(zzdvv<Set<zzbqc<zzbnm>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbnn zzk(zzdvv<Set<zzbqc<zzbnm>>> zzdvvVar) {
        return new zzbnn(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbnl(this.zzfdr.get());
    }
}
