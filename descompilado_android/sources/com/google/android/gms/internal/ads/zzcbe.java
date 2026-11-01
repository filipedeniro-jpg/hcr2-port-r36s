package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbe implements zzdvi<zzcbb> {
    private final zzdvv<Clock> zzfbv;

    public zzcbe(zzdvv<Clock> zzdvvVar) {
        this.zzfbv = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcbb(this.zzfbv.get());
    }
}
