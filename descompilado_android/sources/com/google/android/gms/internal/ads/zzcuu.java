package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcuu implements zzcmc<zzbyf> {
    private final /* synthetic */ zzcuv zzgii;

    zzcuu(zzcuv zzcuvVar) {
        this.zzgii = zzcuvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmc
    public final void zzalg() {
        synchronized (this.zzgii) {
            this.zzgii.zzgid = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmc
    public final /* synthetic */ void onSuccess(zzbyf zzbyfVar) {
        zzbyf zzbyfVar2 = zzbyfVar;
        synchronized (this.zzgii) {
            this.zzgii.zzgid = zzbyfVar2;
            this.zzgii.zzgid.zzaew();
        }
    }
}
