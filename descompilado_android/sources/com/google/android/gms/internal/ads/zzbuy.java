package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbuy implements zzdvi<zzbuw> {
    private final zzdvv<zzbxn> zzemz;
    private final zzdvv<Clock> zzfbv;

    public zzbuy(zzdvv<zzbxn> zzdvvVar, zzdvv<Clock> zzdvvVar2) {
        this.zzemz = zzdvvVar;
        this.zzfbv = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbuw(this.zzemz.get(), this.zzfbv.get());
    }
}
