package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzamb implements Runnable {
    private final /* synthetic */ AdOverlayInfoParcel zzdeu;
    private final /* synthetic */ zzalz zzdev;

    zzamb(zzalz zzalzVar, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzdev = zzalzVar;
        this.zzdeu = adOverlayInfoParcel;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.gms.ads.internal.zzp.zzjx();
        com.google.android.gms.ads.internal.overlay.zzl.zza(this.zzdev.zzdes, this.zzdeu, true);
    }
}
