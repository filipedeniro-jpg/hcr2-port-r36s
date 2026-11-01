package com.google.android.gms.internal.ads;

import android.view.Surface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpd implements Runnable {
    private final /* synthetic */ zzox zzbjd;
    private final /* synthetic */ Surface zzbjl;

    zzpd(zzox zzoxVar, Surface surface) {
        this.zzbjd = zzoxVar;
        this.zzbjl = surface;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbjd.zzbje.zza(this.zzbjl);
    }
}
