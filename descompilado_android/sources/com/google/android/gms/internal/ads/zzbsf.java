package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsf implements zzbmg {
    private final zzbmo zzfig;
    private final zzcvb zzfih;

    public zzbsf(zzbmo zzbmoVar, zzcvb zzcvbVar) {
        this.zzfig = zzbmoVar;
        this.zzfih = zzcvbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdClosed() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdLeftApplication() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoCompleted() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoStarted() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void zzb(zzape zzapeVar, String str, String str2) {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdOpened() {
        if (this.zzfih.zzgjh == 0 || this.zzfih.zzgjh == 1) {
            this.zzfig.onAdImpression();
        }
    }
}
