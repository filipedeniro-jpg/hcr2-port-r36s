package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcln implements zzdcg<zzbhy> {
    private final /* synthetic */ zzbit zzgbr;
    private final /* synthetic */ zzclo zzgbs;

    zzcln(zzclo zzcloVar, zzbit zzbitVar) {
        this.zzgbs = zzcloVar;
        this.zzgbr = zzbitVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        synchronized (this.zzgbs) {
            zzclo.zza(this.zzgbs, (zzdcp) null);
            this.zzgbr.zzacj().onAdFailedToLoad(zzcce.zzd(th));
            this.zzgbs.zzgbw.zzde(60);
            zzcvt.zzc(th, "BannerAdManagerShim.onFailure");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbhy zzbhyVar) {
        zzbhy zzbhyVar2 = zzbhyVar;
        synchronized (this.zzgbs) {
            zzclo.zza(this.zzgbs, (zzdcp) null);
            if (this.zzgbs.zzgbd != null) {
                this.zzgbs.zzgbd.destroy();
            }
            this.zzgbs.zzgbd = zzbhyVar2;
            this.zzgbs.zzfcw.removeAllViews();
            this.zzgbs.zzfcw.addView(zzbhyVar2.zzaer());
            zzbhyVar2.zzaew();
            this.zzgbs.zzgbw.zzde(zzbhyVar2.zzaev());
        }
    }
}
