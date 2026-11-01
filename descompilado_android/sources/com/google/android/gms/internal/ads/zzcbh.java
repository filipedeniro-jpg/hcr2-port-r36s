package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbh implements zzdvi<Set<zzbqc<zzbnm>>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzcbn> zzfqx;
    private final zzcbd zzfsc;

    private zzcbh(zzcbd zzcbdVar, zzdvv<zzcbn> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfsc = zzcbdVar;
        this.zzfqx = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzcbh zzc(zzcbd zzcbdVar, zzdvv<zzcbn> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        return new zzcbh(zzcbdVar, zzdvvVar, zzdvvVar2);
    }

    public static Set<zzbqc<zzbnm>> zza(zzcbd zzcbdVar, zzcbn zzcbnVar, Executor executor) {
        return (Set) zzdvo.zza(zzcbd.zzc(zzcbnVar, executor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfsc, this.zzfqx.get(), this.zzfbt.get());
    }
}
