package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbaf implements Runnable {
    private final /* synthetic */ zzbac zzecs;

    zzbaf(zzbac zzbacVar) {
        this.zzecs = zzbacVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.gms.ads.internal.zzp.zzku().zzb(this.zzecs);
    }
}
