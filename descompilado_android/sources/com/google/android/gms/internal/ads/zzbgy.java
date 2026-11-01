package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgy implements zzdvi<zzbgl> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzpf> zzfcc;
    private final zzdvv<zzaim> zzfcd;

    private zzbgy(zzdvv<zzpf> zzdvvVar, zzdvv<zzaim> zzdvvVar2, zzdvv<Executor> zzdvvVar3) {
        this.zzfcc = zzdvvVar;
        this.zzfcd = zzdvvVar2;
        this.zzfbt = zzdvvVar3;
    }

    public static zzbgy zzc(zzdvv<zzpf> zzdvvVar, zzdvv<zzaim> zzdvvVar2, zzdvv<Executor> zzdvvVar3) {
        return new zzbgy(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzpf zzpfVar = this.zzfcc.get();
        return (zzbgl) zzdvo.zza(new zzbgl(zzpfVar.zzld(), this.zzfcd.get(), this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
