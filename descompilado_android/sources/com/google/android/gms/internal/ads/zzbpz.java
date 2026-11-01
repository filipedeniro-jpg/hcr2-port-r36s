package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpz implements zzdvi<zzbpy> {
    private final zzdvv<Set<zzbqc<zzbpv>>> zzfdr;

    private zzbpz(zzdvv<Set<zzbqc<zzbpv>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbpz zzr(zzdvv<Set<zzbqc<zzbpv>>> zzdvvVar) {
        return new zzbpz(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbpy(this.zzfdr.get());
    }
}
