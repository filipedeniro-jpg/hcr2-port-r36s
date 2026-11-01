package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahe implements Runnable {
    private final /* synthetic */ zzagk zzczq;
    private final /* synthetic */ zzagt zzczs;
    private final /* synthetic */ zzaho zzczt;

    zzahe(zzagt zzagtVar, zzaho zzahoVar, zzagk zzagkVar) {
        this.zzczs = zzagtVar;
        this.zzczt = zzahoVar;
        this.zzczq = zzagkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzczs.lock) {
            if (this.zzczt.getStatus() != -1 && this.zzczt.getStatus() != 1) {
                this.zzczt.reject();
                zzdcs zzdcsVar = zzawx.zzdwa;
                zzagk zzagkVar = this.zzczq;
                zzagkVar.getClass();
                zzdcsVar.execute(zzahd.zzb(zzagkVar));
                zzatm.zzdy("Could not receive loaded message in a timely manner. Rejecting.");
            }
        }
    }
}
