package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcun implements zzdcg<zzbyf> {
    private final /* synthetic */ zzcmc zzgcu;
    private final /* synthetic */ zzcul zzghz;

    zzcun(zzcul zzculVar, zzcmc zzcmcVar) {
        this.zzghz = zzculVar;
        this.zzgcu = zzcmcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        synchronized (this.zzghz) {
            ((zzbym) this.zzghz.zzght.zzamp()).zzacj().onAdFailedToLoad(zzcce.zzd(th));
            zzcvt.zzc(th, "RewardedAdLoader.onFailure");
            this.zzgcu.zzalg();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbyf zzbyfVar) {
        zzbyf zzbyfVar2 = zzbyfVar;
        synchronized (this.zzghz) {
            this.zzgcu.onSuccess(zzbyfVar2);
            this.zzghz.zzghs.onAdMetadataChanged();
        }
    }
}
