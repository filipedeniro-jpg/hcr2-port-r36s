package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcaf implements zzdvi<Set<zzbqc<zzcxt>>> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<String> zzfra;
    private final zzdvv<Map<zzcxk, zzcak>> zzfrb;

    public zzcaf(zzdvv<String> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<Executor> zzdvvVar3, zzdvv<Map<zzcxk, zzcak>> zzdvvVar4) {
        this.zzfra = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfbt = zzdvvVar3;
        this.zzfrb = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        Set setEmptySet;
        final String str = this.zzfra.get();
        Context context = this.zzejn.get();
        Executor executor = this.zzfbt.get();
        Map<zzcxk, zzcak> map = this.zzfrb.get();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqd)).booleanValue()) {
            zzrz zzrzVar = new zzrz(new zzse(context));
            zzrzVar.zza(new zzry(str) { // from class: com.google.android.gms.internal.ads.zzcah
                private final String zzcys;

                {
                    this.zzcys = str;
                }

                @Override // com.google.android.gms.internal.ads.zzry
                public final void zza(zztd zztdVar) {
                    zztdVar.zzcad = this.zzcys;
                }
            });
            setEmptySet = Collections.singleton(new zzbqc(new zzcai(zzrzVar, map), executor));
        } else {
            setEmptySet = Collections.emptySet();
        }
        return (Set) zzdvo.zza(setEmptySet, "Cannot return null from a non-@Nullable @Provides method");
    }
}
