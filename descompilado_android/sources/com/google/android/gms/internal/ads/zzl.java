package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzl implements Runnable {
    private final zzq zzw;
    private final zzz zzx;
    private final Runnable zzy;

    public zzl(zzq zzqVar, zzz zzzVar, Runnable runnable) {
        this.zzw = zzqVar;
        this.zzx = zzzVar;
        this.zzy = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzw.isCanceled();
        if (this.zzx.zzbi == null) {
            this.zzw.zza(this.zzx.result);
        } else {
            this.zzw.zzb(this.zzx.zzbi);
        }
        if (this.zzx.zzbj) {
            this.zzw.zzb("intermediate-response");
        } else {
            this.zzw.zzc("done");
        }
        Runnable runnable = this.zzy;
        if (runnable != null) {
            runnable.run();
        }
    }
}
