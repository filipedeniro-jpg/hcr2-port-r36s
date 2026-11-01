package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzrc implements Runnable {
    private final /* synthetic */ zzrd zzbqu;

    zzrc(zzrd zzrdVar) {
        this.zzbqu = zzrdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbqu.disconnect();
    }
}
