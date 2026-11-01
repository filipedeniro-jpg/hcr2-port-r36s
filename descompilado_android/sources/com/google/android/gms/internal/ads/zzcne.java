package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcne implements zzdvi<zzcnd> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzdcp<String>> zzgdn;

    private zzcne(zzdvv<zzdcp<String>> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzgdn = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzcne zzaj(zzdvv<zzdcp<String>> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        return new zzcne(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcnd(this.zzgdn.get(), this.zzfbt.get());
    }
}
