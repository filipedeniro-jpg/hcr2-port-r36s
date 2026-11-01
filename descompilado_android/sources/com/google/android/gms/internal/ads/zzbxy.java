package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzbxy implements com.google.android.gms.ads.internal.overlay.zzv {
    private final zzbnb zzfpm;

    private zzbxy(zzbnb zzbnbVar) {
        this.zzfpm = zzbnbVar;
    }

    static com.google.android.gms.ads.internal.overlay.zzv zza(zzbnb zzbnbVar) {
        return new zzbxy(zzbnbVar);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzv
    public final void zzsv() {
        this.zzfpm.onAdLeftApplication();
    }
}
