package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaxz implements Runnable {
    private final /* synthetic */ zzaxr zzdxf;

    zzaxz(zzaxr zzaxrVar) {
        this.zzdxf = zzaxrVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzdxf.zzdxe != null) {
            this.zzdxf.zzdxe.zzws();
        }
    }
}
