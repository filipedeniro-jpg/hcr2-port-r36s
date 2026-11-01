package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztl extends zzuu {
    private final AdListener zzcbm;

    public zztl(AdListener adListener) {
        this.zzcbm = adListener;
    }

    @Override // com.google.android.gms.internal.ads.zzur
    public final void onAdClosed() {
        this.zzcbm.onAdClosed();
    }

    @Override // com.google.android.gms.internal.ads.zzur
    public final void onAdFailedToLoad(int i) {
        this.zzcbm.onAdFailedToLoad(i);
    }

    @Override // com.google.android.gms.internal.ads.zzur
    public final void onAdLeftApplication() {
        this.zzcbm.onAdLeftApplication();
    }

    @Override // com.google.android.gms.internal.ads.zzur
    public final void onAdLoaded() {
        this.zzcbm.onAdLoaded();
    }

    @Override // com.google.android.gms.internal.ads.zzur
    public final void onAdOpened() {
        this.zzcbm.onAdOpened();
    }

    @Override // com.google.android.gms.internal.ads.zzur
    public final void onAdClicked() {
        this.zzcbm.onAdClicked();
    }

    @Override // com.google.android.gms.internal.ads.zzur
    public final void onAdImpression() {
        this.zzcbm.onAdImpression();
    }

    public final AdListener getAdListener() {
        return this.zzcbm;
    }
}
