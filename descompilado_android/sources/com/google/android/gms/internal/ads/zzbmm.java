package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmm implements zzdvi<zzbmk> {
    private final zzdvv<Set<zzbqc<zzbmp>>> zzfdr;

    public zzbmm(zzdvv<Set<zzbqc<zzbmp>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbmk(this.zzfdr.get());
    }
}
