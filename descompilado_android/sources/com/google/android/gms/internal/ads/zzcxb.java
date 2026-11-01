package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxb {
    private final E zzgkx;
    private final List<zzdcp<?>> zzglc;
    private final /* synthetic */ zzcwz zzgld;

    private zzcxb(zzcwz zzcwzVar, E e, List<zzdcp<?>> list) {
        this.zzgld = zzcwzVar;
        this.zzgkx = e;
        this.zzglc = list;
    }

    public final <O> zzcxf<O> zzb(Callable<O> callable) {
        zzdck zzdckVarZzh = zzdcf.zzh(this.zzglc);
        zzdcp zzdcpVarZza = zzdckVarZzh.zza(zzcxa.zzgeu, zzawx.zzdwb);
        zzcwz zzcwzVar = this.zzgld;
        return new zzcxf<>(zzcwzVar, this.zzgkx, zzdcpVarZza, this.zzglc, zzdckVarZzh.zza(callable, zzcwzVar.zzfnq));
    }
}
