package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzatl implements Runnable {
    private final /* synthetic */ zzati zzdrn;

    zzatl(zzati zzatiVar) {
        this.zzdrn = zzatiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdrn.thread = Thread.currentThread();
        this.zzdrn.zzst();
    }
}
