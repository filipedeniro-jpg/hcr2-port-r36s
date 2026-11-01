package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgn implements zzcfk<zzbhy> {
    private final ScheduledExecutorService zzfez;
    private final zzbmj zzfhg;
    private final zzdcs zzfnq;
    private final zzbit zzfxm;
    private final zzcft zzfxn;

    public zzcgn(zzbit zzbitVar, zzcft zzcftVar, zzbmj zzbmjVar, ScheduledExecutorService scheduledExecutorService, zzdcs zzdcsVar) {
        this.zzfxm = zzbitVar;
        this.zzfxn = zzcftVar;
        this.zzfhg = zzbmjVar;
        this.zzfez = scheduledExecutorService;
        this.zzfnq = zzdcsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return zzcvjVar.zzgjo.zzffn.zzamv() != null && this.zzfxn.zza(zzcvjVar, zzcvbVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<zzbhy> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        return this.zzfnq.submit(new Callable(this, zzcvjVar, zzcvbVar) { // from class: com.google.android.gms.internal.ads.zzcgq
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzcgn zzfxp;

            {
                this.zzfxp = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzfxp.zzc(this.zzfno, this.zzfdo);
            }
        });
    }

    final /* synthetic */ zzbhy zzc(final zzcvj zzcvjVar, final zzcvb zzcvbVar) throws Exception {
        return this.zzfxm.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzbjf(zzcvjVar.zzgjo.zzffn.zzamv(), new Runnable(this, zzcvjVar, zzcvbVar) { // from class: com.google.android.gms.internal.ads.zzcgp
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzcgn zzfxp;

            {
                this.zzfxp = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfxp.zzd(this.zzfno, this.zzfdo);
            }
        })).zzadb();
    }

    final /* synthetic */ void zzd(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        zzdcf.zza(zzdcf.zza(this.zzfxn.zzb(zzcvjVar, zzcvbVar), zzcvbVar.zzgjf, TimeUnit.SECONDS, this.zzfez), new zzcgs(this), this.zzfnq);
    }
}
