package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcop implements zzdvi<zzcon<zzcoj>> {
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<zzcoi> zzgep;

    public zzcop(zzdvv<zzcoi> zzdvvVar, zzdvv<Clock> zzdvvVar2) {
        this.zzgep = zzdvvVar;
        this.zzfbv = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcon) zzdvo.zza(new zzcon(this.zzgep.get(), 10000L, this.zzfbv.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
