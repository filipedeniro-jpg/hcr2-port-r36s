package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzwt extends zzun {
    private final /* synthetic */ zzwu zzced;

    zzwt(zzwu zzwuVar) {
        this.zzced = zzwuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzun, com.google.android.gms.ads.AdListener
    public final void onAdLoaded() {
        this.zzced.zzceg.zza(this.zzced.zzdd());
        super.onAdLoaded();
    }

    @Override // com.google.android.gms.internal.ads.zzun, com.google.android.gms.ads.AdListener
    public final void onAdFailedToLoad(int i) {
        this.zzced.zzceg.zza(this.zzced.zzdd());
        super.onAdFailedToLoad(i);
    }
}
