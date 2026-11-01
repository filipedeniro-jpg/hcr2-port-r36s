package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbyu implements com.google.android.gms.ads.internal.zzk {
    private final /* synthetic */ zzbyr zzfqd;

    zzbyu(zzbyr zzbyrVar) {
        this.zzfqd = zzbyrVar;
    }

    @Override // com.google.android.gms.ads.internal.zzk
    public final void zzju() {
        this.zzfqd.zzfpz.onPause();
    }

    @Override // com.google.android.gms.ads.internal.zzk
    public final void zzjv() {
        this.zzfqd.zzfpz.onResume();
    }
}
