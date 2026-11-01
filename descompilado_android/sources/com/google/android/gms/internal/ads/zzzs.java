package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzzs extends zzzq {
    private final OnCustomRenderedAdLoadedListener zzcej;

    public zzzs(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzcej = onCustomRenderedAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzzn
    public final void zza(zzzm zzzmVar) {
        this.zzcej.onCustomRenderedAdLoaded(new zzzj(zzzmVar));
    }
}
