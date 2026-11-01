package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzayf implements Runnable {
    private final zzaya zzdyf;

    private zzayf(zzaya zzayaVar) {
        this.zzdyf = zzayaVar;
    }

    static Runnable zza(zzaya zzayaVar) {
        return new zzayf(zzayaVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdyf.stop();
    }
}
