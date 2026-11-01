package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahf implements zzaxh {
    private final /* synthetic */ zzagt zzczs;
    private final /* synthetic */ zzaho zzczu;

    zzahf(zzagt zzagtVar, zzaho zzahoVar) {
        this.zzczs = zzagtVar;
        this.zzczu = zzahoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxh
    public final void run() {
        synchronized (this.zzczs.lock) {
            this.zzczs.status = 1;
            zzatm.zzdy("Failed loading new engine. Marking new engine destroyable.");
            this.zzczu.zzre();
        }
    }
}
