package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbiu implements zzdvi<zzcra<zzcmu>> {
    private final zzdvv<ScheduledExecutorService> zzfcy;
    private final zzdvv<zzcmx> zzfdu;

    public zzbiu(zzdvv<zzcmx> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2) {
        this.zzfdu = zzdvvVar;
        this.zzfcy = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcra) zzdvo.zza(new zzcpw(this.zzfdu.get(), 0L, this.zzfcy.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
