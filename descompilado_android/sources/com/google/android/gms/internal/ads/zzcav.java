package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcav implements zzdvi<zzcat> {
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<zzcar> zzfrp;
    private final zzdvv<Set<zzcaw>> zzfrq;

    private zzcav(zzdvv<zzcar> zzdvvVar, zzdvv<Set<zzcaw>> zzdvvVar2, zzdvv<Clock> zzdvvVar3) {
        this.zzfrp = zzdvvVar;
        this.zzfrq = zzdvvVar2;
        this.zzfbv = zzdvvVar3;
    }

    public static zzcav zzm(zzdvv<zzcar> zzdvvVar, zzdvv<Set<zzcaw>> zzdvvVar2, zzdvv<Clock> zzdvvVar3) {
        return new zzcav(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcat(this.zzfrp.get(), this.zzfrq.get(), this.zzfbv.get());
    }
}
