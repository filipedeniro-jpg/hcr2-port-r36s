package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbng implements zzdvi<zzbnb> {
    private final zzdvv<Set<zzbqc<zzbmg>>> zzfdr;

    private zzbng(zzdvv<Set<zzbqc<zzbmg>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbng zzj(zzdvv<Set<zzbqc<zzbmg>>> zzdvvVar) {
        return new zzbng(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbnb(this.zzfdr.get());
    }
}
