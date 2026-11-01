package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrw implements com.google.android.gms.ads.internal.overlay.zzp {
    private final zzbnu zzfib;
    private final zzbpy zzfic;

    public zzbrw(zzbnu zzbnuVar, zzbpy zzbpyVar) {
        this.zzfib = zzbnuVar;
        this.zzfic = zzbpyVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzse() {
        this.zzfib.zzse();
        this.zzfic.onHide();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
        this.zzfib.onPause();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
        this.zzfib.onResume();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzsf() {
        this.zzfib.zzsf();
        this.zzfic.zzagk();
    }
}
