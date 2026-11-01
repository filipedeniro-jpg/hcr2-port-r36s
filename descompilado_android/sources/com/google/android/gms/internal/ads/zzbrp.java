package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrp implements zzdvi<zzbqc<zzbmg>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbsj> zzfcs;
    private final zzbrd zzfia;

    private zzbrp(zzbrd zzbrdVar, zzdvv<zzbsj> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfia = zzbrdVar;
        this.zzfcs = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzbrp zzb(zzbrd zzbrdVar, zzdvv<zzbsj> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        return new zzbrp(zzbrdVar, zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
