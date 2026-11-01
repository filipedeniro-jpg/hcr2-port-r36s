package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaxx implements Runnable {
    private final /* synthetic */ zzaxr zzdxf;
    private final /* synthetic */ int zzdxi;
    private final /* synthetic */ int zzdxj;

    zzaxx(zzaxr zzaxrVar, int i, int i2) {
        this.zzdxf = zzaxrVar;
        this.zzdxi = i;
        this.zzdxj = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzdxf.zzdxe != null) {
            this.zzdxf.zzdxe.zzj(this.zzdxi, this.zzdxj);
        }
    }
}
