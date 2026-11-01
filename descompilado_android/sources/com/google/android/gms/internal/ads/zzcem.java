package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcem implements zzdvi<zzbqc<zzbml>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzcet> zzfca;

    private zzcem(zzdvv<zzcet> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfca = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzcem zzag(zzdvv<zzcet> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        return new zzcem(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfca.get(), this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
