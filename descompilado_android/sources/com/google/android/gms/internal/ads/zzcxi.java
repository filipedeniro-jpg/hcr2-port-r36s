package com.google.android.gms.internal.ads;

/* JADX INFO: Add missing generic type declarations: [O] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcxi<O> implements zzdcg<O> {
    private final /* synthetic */ zzcww zzgli;
    private final /* synthetic */ zzcxf zzglj;

    zzcxi(zzcxf zzcxfVar, zzcww zzcwwVar) {
        this.zzglj = zzcxfVar;
        this.zzgli = zzcwwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void onSuccess(O o) {
        this.zzglj.zzgld.zzglb.zzc(this.zzgli);
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        this.zzglj.zzgld.zzglb.zza(this.zzgli, th);
    }
}
