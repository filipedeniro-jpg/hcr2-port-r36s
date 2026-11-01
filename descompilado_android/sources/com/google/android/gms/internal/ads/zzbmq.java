package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmq implements zzdvi<zzbmo> {
    private final zzdvv<Set<zzbqc<zzbmt>>> zzfdr;

    private zzbmq(zzdvv<Set<zzbqc<zzbmt>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbmq zzh(zzdvv<Set<zzbqc<zzbmt>>> zzdvvVar) {
        return new zzbmq(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbmo(this.zzfdr.get());
    }
}
