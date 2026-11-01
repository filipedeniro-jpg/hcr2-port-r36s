package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcfx implements Runnable {
    private final zzbbc zzehf;

    private zzcfx(zzbbc zzbbcVar) {
        this.zzehf = zzbbcVar;
    }

    static Runnable zzh(zzbbc zzbbcVar) {
        return new zzcfx(zzbbcVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzehf.zzzl();
    }
}
