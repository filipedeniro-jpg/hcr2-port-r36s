package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcam implements zzbml, zzbnm, zzboi {
    private final zzcar zzfri;
    private final zzcay zzfrj;

    public zzcam(zzcar zzcarVar, zzcay zzcayVar) {
        this.zzfri = zzcarVar;
        this.zzfrj = zzcayVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final void onAdLoaded() {
        this.zzfrj.zzm(this.zzfri.zzpx());
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final void onAdFailedToLoad(int i) {
        this.zzfrj.zzm(this.zzfri.zzpx());
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zzb(zzaok zzaokVar) {
        this.zzfri.zzi(zzaokVar.zzdlo);
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zza(zzcvj zzcvjVar) {
        this.zzfri.zzb(zzcvjVar);
    }
}
