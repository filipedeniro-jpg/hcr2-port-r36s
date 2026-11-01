package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcge implements zzbjr {
    private final zzcvs zzfxd;

    private zzcge(zzcvs zzcvsVar) {
        this.zzfxd = zzcvsVar;
    }

    static zzbjr zza(zzcvs zzcvsVar) {
        return new zzcge(zzcvsVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbjr
    public final zzwk getVideoController() {
        return this.zzfxd.getVideoController();
    }
}
