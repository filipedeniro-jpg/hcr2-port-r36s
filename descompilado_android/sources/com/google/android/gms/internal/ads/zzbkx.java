package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkx implements zzbmg, zzbmt, zzbnm, zzboi, zzth {
    private final Clock zzbmd;
    private final zzasw zzffv;

    public zzbkx(Clock clock, zzasw zzaswVar) {
        this.zzbmd = clock;
        this.zzffv = zzaswVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdLeftApplication() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdOpened() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoCompleted() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoStarted() {
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zzb(zzaok zzaokVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void zzb(zzape zzapeVar, String str, String str2) {
    }

    @Override // com.google.android.gms.internal.ads.zzth
    public final void onAdClicked() {
        this.zzffv.zztr();
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final void onAdLoaded() {
        this.zzffv.zzag(true);
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final void onAdImpression() {
        this.zzffv.zztq();
    }

    public final void zzf(zztp zztpVar) {
        this.zzffv.zze(zztpVar);
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zza(zzcvj zzcvjVar) {
        this.zzffv.zzes(this.zzbmd.elapsedRealtime());
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdClosed() {
        this.zzffv.zzts();
    }

    public final String zztt() {
        return this.zzffv.zztt();
    }
}
