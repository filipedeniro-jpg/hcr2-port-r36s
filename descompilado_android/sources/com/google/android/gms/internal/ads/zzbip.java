package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbip implements zzdvi<zzbqc<zzpl>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbjp> zzfcs;
    private final zzbib zzfdq;

    public zzbip(zzbib zzbibVar, zzdvv<zzbjp> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfdq = zzbibVar;
        this.zzfcs = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
