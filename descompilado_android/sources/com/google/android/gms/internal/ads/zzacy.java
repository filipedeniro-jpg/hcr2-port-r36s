package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeContentAd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzacy extends zzabw {
    private final NativeContentAd.OnContentAdLoadedListener zzcwi;

    public zzacy(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzcwi = onContentAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzabt
    public final void zza(zzabk zzabkVar) {
        this.zzcwi.onContentAdLoaded(new zzabl(zzabkVar));
    }
}
