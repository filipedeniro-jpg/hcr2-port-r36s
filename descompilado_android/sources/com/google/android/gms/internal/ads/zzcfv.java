package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcfv implements zzbjr {
    private final zzbbc zzehf;

    private zzcfv(zzbbc zzbbcVar) {
        this.zzehf = zzbbcVar;
    }

    static zzbjr zzp(zzbbc zzbbcVar) {
        return new zzcfv(zzbbcVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbjr
    public final zzwk getVideoController() {
        return this.zzehf.zzxj();
    }
}
