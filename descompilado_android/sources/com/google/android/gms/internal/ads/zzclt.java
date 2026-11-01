package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzclt implements zzdcg<zzbrc> {
    private final /* synthetic */ zzbry zzgcc;
    private final /* synthetic */ zzclu zzgcd;

    zzclt(zzclu zzcluVar, zzbry zzbryVar) {
        this.zzgcd = zzcluVar;
        this.zzgcc = zzbryVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        synchronized (this.zzgcd) {
            zzclu.zza(this.zzgcd, (zzdcp) null);
            this.zzgcc.zzacj().onAdFailedToLoad(zzcce.zzd(th));
            zzcvt.zzc(th, "InterstitialAdManagerShim.onFailure");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbrc zzbrcVar) {
        zzbrc zzbrcVar2 = zzbrcVar;
        synchronized (this.zzgcd) {
            zzclu.zza(this.zzgcd, (zzdcp) null);
            this.zzgcd.zzgcf = zzbrcVar2;
            zzbrcVar2.zzaew();
        }
    }
}
