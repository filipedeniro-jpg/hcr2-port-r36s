package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzamc implements com.google.android.gms.ads.internal.overlay.zzp {
    private final /* synthetic */ zzalz zzdev;

    zzamc(zzalz zzalzVar) {
        this.zzdev = zzalzVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzse() {
        zzawo.zzdv("AdMobCustomTabsAdapter overlay is closed.");
        this.zzdev.zzdet.onAdClosed(this.zzdev);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
        zzawo.zzdv("AdMobCustomTabsAdapter overlay is paused.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
        zzawo.zzdv("AdMobCustomTabsAdapter overlay is resumed.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzsf() {
        zzawo.zzdv("Opening AdMobCustomTabsAdapter overlay.");
        this.zzdev.zzdet.onAdOpened(this.zzdev);
    }
}
