package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcjg implements Runnable {
    private final zzbzh zzfyb;

    private zzcjg(zzbzh zzbzhVar) {
        this.zzfyb = zzbzhVar;
    }

    static Runnable zza(zzbzh zzbzhVar) {
        return new zzcjg(zzbzhVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzfyb.zzaji();
    }
}
