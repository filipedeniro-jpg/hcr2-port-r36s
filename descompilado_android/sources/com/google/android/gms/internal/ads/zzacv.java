package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzacv extends zzabr {
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzcwg;

    public zzacv(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzcwg = onAppInstallAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzabs
    public final void zza(zzabg zzabgVar) {
        this.zzcwg.onAppInstallAdLoaded(new zzabh(zzabgVar));
    }
}
