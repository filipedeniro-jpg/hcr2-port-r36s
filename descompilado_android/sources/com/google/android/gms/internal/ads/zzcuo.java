package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcuo implements zzcmc<zzbyf> {
    private final /* synthetic */ zzcup zzgia;

    zzcuo(zzcup zzcupVar) {
        this.zzgia = zzcupVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmc
    public final void zzalg() {
        synchronized (this.zzgia) {
            this.zzgia.zzgid = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmc
    public final /* synthetic */ void onSuccess(zzbyf zzbyfVar) {
        zzbyf zzbyfVar2 = zzbyfVar;
        synchronized (this.zzgia) {
            this.zzgia.zzgid = zzbyfVar2;
            this.zzgia.zzgid.zzaew();
        }
    }
}
