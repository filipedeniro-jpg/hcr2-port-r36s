package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbf implements zzdvi<Set<zzbqc<zzbml>>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzcbn> zzfqx;
    private final zzcbd zzfsc;

    private zzcbf(zzcbd zzcbdVar, zzdvv<zzcbn> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfsc = zzcbdVar;
        this.zzfqx = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzcbf zza(zzcbd zzcbdVar, zzdvv<zzcbn> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        return new zzcbf(zzcbdVar, zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(zzcbd.zzd(this.zzfqx.get(), this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
