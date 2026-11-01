package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnv implements zzdvi<zzcnq> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzatb> zzgdr;

    private zzcnv(zzdvv<Executor> zzdvvVar, zzdvv<zzatb> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzgdr = zzdvvVar2;
    }

    public static zzcnv zzal(zzdvv<Executor> zzdvvVar, zzdvv<zzatb> zzdvvVar2) {
        return new zzcnv(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcnq(this.zzfbt.get(), this.zzgdr.get());
    }
}
