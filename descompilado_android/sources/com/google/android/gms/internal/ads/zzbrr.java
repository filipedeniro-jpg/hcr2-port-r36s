package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrr implements zzdvi<zzbqc<zzbom>> {
    private final zzdvv<Executor> zzfbt;
    private final zzbrd zzfia;

    private zzbrr(zzbrd zzbrdVar, zzdvv<Executor> zzdvvVar) {
        this.zzfia = zzbrdVar;
        this.zzfbt = zzdvvVar;
    }

    public static zzbrr zzd(zzbrd zzbrdVar, zzdvv<Executor> zzdvvVar) {
        return new zzbrr(zzbrdVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(this.zzfia.zzb(this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
