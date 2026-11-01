package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbbi implements com.google.android.gms.ads.internal.overlay.zzp {
    private com.google.android.gms.ads.internal.overlay.zzp zzdhm;
    private zzbbc zzees;

    public zzbbi(zzbbc zzbbcVar, com.google.android.gms.ads.internal.overlay.zzp zzpVar) {
        this.zzees = zzbbcVar;
        this.zzdhm = zzpVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzsf() {
        this.zzdhm.zzsf();
        this.zzees.zzsq();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzse() {
        this.zzdhm.zzse();
        this.zzees.zzyr();
    }
}
