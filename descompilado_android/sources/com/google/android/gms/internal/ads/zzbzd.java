package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbzd implements zzams {
    private final /* synthetic */ zzbyt zzfqf;

    zzbzd(zzbyt zzbytVar) {
        this.zzfqf = zzbytVar;
    }

    @Override // com.google.android.gms.internal.ads.zzams
    public final void zzc(int i, int i2, int i3, int i4) {
        this.zzfqf.zzffi.onAdOpened();
    }

    @Override // com.google.android.gms.internal.ads.zzams
    public final void zzsh() {
        this.zzfqf.zzffi.onAdClosed();
    }

    @Override // com.google.android.gms.internal.ads.zzams
    public final void zzsi() {
        this.zzfqf.zzfqc.zzafa();
    }
}
