package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcmg implements zzdcg<zzbju> {
    private final /* synthetic */ zzcmc zzgcu;
    private final /* synthetic */ zzbsr zzgcv;
    private final /* synthetic */ zzcme zzgcw;

    zzcmg(zzcme zzcmeVar, zzcmc zzcmcVar, zzbsr zzbsrVar) {
        this.zzgcw = zzcmeVar;
        this.zzgcu = zzcmcVar;
        this.zzgcv = zzbsrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        this.zzgcv.zzacj().onAdFailedToLoad(zzcce.zzd(th));
        zzcvt.zzc(th, "NativeAdLoader.onFailure");
        this.zzgcu.zzalg();
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbju zzbjuVar) {
        zzbju zzbjuVar2 = zzbjuVar;
        synchronized (this.zzgcw) {
            this.zzgcu.onSuccess(zzbjuVar2);
        }
    }
}
