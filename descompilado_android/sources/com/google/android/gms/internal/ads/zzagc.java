package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzagc extends zzbdf {
    private final /* synthetic */ zzafw zzcyp;

    private zzagc(zzafw zzafwVar) {
        this.zzcyp = zzafwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zza(zzbde zzbdeVar) {
        if (this.zzcyp.zzcyn != null) {
            this.zzcyp.zzcyn.zzra();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zzb(zzbde zzbdeVar) {
        this.zzcyp.zzg(zzbdeVar.uri);
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final boolean zzc(zzbde zzbdeVar) {
        return this.zzcyp.zzg(zzbdeVar.uri);
    }
}
