package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzada extends zzacc {
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzcwk;

    public zzada(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzcwk = onCustomTemplateAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzabz
    public final void zzb(zzabo zzaboVar) {
        this.zzcwk.onCustomTemplateAdLoaded(zzabp.zza(zzaboVar));
    }
}
