package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdct implements Runnable {
    private final /* synthetic */ Runnable zzgrf;
    private final /* synthetic */ zzdcu zzgrg;

    zzdct(zzdcu zzdcuVar, Runnable runnable) {
        this.zzgrg = zzdcuVar;
        this.zzgrf = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzgrg.zzgrh = false;
        this.zzgrf.run();
    }
}
