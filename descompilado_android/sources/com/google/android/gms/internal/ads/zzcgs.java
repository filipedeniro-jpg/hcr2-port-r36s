package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcgs implements zzdcg<zzbhy> {
    private final /* synthetic */ zzcgn zzfxr;

    zzcgs(zzcgn zzcgnVar) {
        this.zzfxr = zzcgnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        this.zzfxr.zzfhg.onAdFailedToLoad(zzcce.zzd(th));
        zzcvt.zzc(th, "DelayedBannerAd.onFailure");
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbhy zzbhyVar) {
        zzbhyVar.zzaew();
    }
}
