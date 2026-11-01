package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbon implements zzdvi<zzbol> {
    private final zzdvv<Set<zzbqc<zzbom>>> zzfdr;

    private zzbon(zzdvv<Set<zzbqc<zzbom>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbon zzo(zzdvv<Set<zzbqc<zzbom>>> zzdvvVar) {
        return new zzbon(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbol(this.zzfdr.get());
    }
}
