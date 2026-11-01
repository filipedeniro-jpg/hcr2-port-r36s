package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahg implements zzaxj<zzagk> {
    private final /* synthetic */ zzagt zzczs;
    private final /* synthetic */ zzaho zzczu;

    zzahg(zzagt zzagtVar, zzaho zzahoVar) {
        this.zzczs = zzagtVar;
        this.zzczu = zzahoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxj
    public final /* synthetic */ void zzh(zzagk zzagkVar) {
        synchronized (this.zzczs.lock) {
            this.zzczs.status = 0;
            if (this.zzczs.zzczh != null && this.zzczu != this.zzczs.zzczh) {
                zzatm.zzdy("New JS engine is loaded, marking previous one as destroyable.");
                this.zzczs.zzczh.zzre();
            }
            this.zzczs.zzczh = this.zzczu;
        }
    }
}
