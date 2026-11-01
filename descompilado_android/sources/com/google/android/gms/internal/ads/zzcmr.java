package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmr implements zzdvi<zzdcp<zzcmv>> {
    private final zzdvv<zzcxn> zzfdw;
    private final zzdvv<zzdcp<zzaok>> zzffm;
    private final zzdvv<zzcmq> zzgdd;

    public zzcmr(zzdvv<zzcxn> zzdvvVar, zzdvv<zzcmq> zzdvvVar2, zzdvv<zzdcp<zzaok>> zzdvvVar3) {
        this.zzfdw = zzdvvVar;
        this.zzgdd = zzdvvVar2;
        this.zzffm = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzdcp) zzdvo.zza(this.zzfdw.get().zza(zzcxk.GENERATE_SIGNALS, this.zzffm.get()).zza(this.zzgdd.get()).zza(((Integer) zzuo.zzoj().zzd(zzyt.zzcri)).intValue(), TimeUnit.SECONDS).zzano(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
