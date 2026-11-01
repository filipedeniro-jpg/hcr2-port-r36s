package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblb implements zzdvi<zzbqc<zzbmg>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbgj> zzfcs;

    private zzblb(zzdvv<zzbgj> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfcs = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzblb zzd(zzdvv<zzbgj> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        return new zzblb(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
