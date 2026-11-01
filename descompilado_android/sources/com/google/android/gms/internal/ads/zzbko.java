package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbko implements com.google.android.gms.ads.internal.overlay.zzp {
    private final zzbnb zzffi;
    private AtomicBoolean zzffj = new AtomicBoolean(false);

    public zzbko(zzbnb zzbnbVar) {
        this.zzffi = zzbnbVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzse() {
        this.zzffj.set(true);
        this.zzffi.onAdClosed();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzsf() {
        this.zzffi.onAdOpened();
    }

    public final boolean isClosed() {
        return this.zzffj.get();
    }
}
