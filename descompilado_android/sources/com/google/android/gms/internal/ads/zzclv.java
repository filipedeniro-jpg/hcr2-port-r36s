package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzclv implements zzcmc<zzbju> {
    private final /* synthetic */ zzclw zzgch;

    zzclv(zzclw zzclwVar) {
        this.zzgch = zzclwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmc
    public final void zzalg() {
        synchronized (this.zzgch) {
            zzclw.zza(this.zzgch, false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmc
    public final /* synthetic */ void onSuccess(zzbju zzbjuVar) {
        zzbju zzbjuVar2 = zzbjuVar;
        synchronized (this.zzgch) {
            zzclw.zza(this.zzgch, false);
            this.zzgch.zzgcj = zzbjuVar2.getMediationAdapterClassName();
            this.zzgch.zzgck = zzbjuVar2.zzjp();
            zzbjuVar2.zzaew();
        }
    }
}
