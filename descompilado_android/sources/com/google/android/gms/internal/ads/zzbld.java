package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbld implements zzdvi<zzbqc<zzbnm>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbgj> zzfcs;

    private zzbld(zzdvv<zzbgj> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfcs = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzbld zzf(zzdvv<zzbgj> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        return new zzbld(zzdvvVar, zzdvvVar2);
    }

    public static zzbqc<zzbnm> zza(zzbgj zzbgjVar, Executor executor) {
        return (zzbqc) zzdvo.zza(new zzbqc(zzbgjVar, executor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfcs.get(), this.zzfbt.get());
    }
}
