package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzx implements zzdvi<Set<zzbqc<zzcxt>>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzcai> zzfca;

    private zzbzx(zzdvv<Executor> zzdvvVar, zzdvv<zzcai> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzfca = zzdvvVar2;
    }

    public static zzbzx zzr(zzdvv<Executor> zzdvvVar, zzdvv<zzcai> zzdvvVar2) {
        return new zzbzx(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        Set setEmptySet;
        Executor executor = this.zzfbt.get();
        zzcai zzcaiVar = this.zzfca.get();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqd)).booleanValue()) {
            setEmptySet = Collections.singleton(new zzbqc(zzcaiVar, executor));
        } else {
            setEmptySet = Collections.emptySet();
        }
        return (Set) zzdvo.zza(setEmptySet, "Cannot return null from a non-@Nullable @Provides method");
    }
}
