package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzadb extends zzaci {
    private final UnifiedNativeAd.OnUnifiedNativeAdLoadedListener zzcwl;

    public zzadb(UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
        this.zzcwl = onUnifiedNativeAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzacf
    public final void zza(zzacq zzacqVar) {
        this.zzcwl.onUnifiedNativeAdLoaded(new zzacr(zzacqVar));
    }
}
