package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkb {
    private final Executor executor;
    private volatile boolean zzacz = true;
    private final ScheduledExecutorService zzfez;
    private final zzdcp<zzbka> zzffa;

    public zzbkb(Executor executor, ScheduledExecutorService scheduledExecutorService, zzdcp<zzbka> zzdcpVar) {
        this.executor = executor;
        this.zzfez = scheduledExecutorService;
        this.zzffa = zzdcpVar;
    }

    public final void zza(zzdcg<zzbju> zzdcgVar) {
        zzdcf.zza(this.zzffa, new zzbki(this, zzdcgVar), this.executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(List<? extends zzdcp<? extends zzbju>> list, final zzdcg<zzbju> zzdcgVar) {
        if (list == null || list.isEmpty()) {
            this.executor.execute(new Runnable(zzdcgVar) { // from class: com.google.android.gms.internal.ads.zzbke
                private final zzdcg zzffb;

                {
                    this.zzffb = zzdcgVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzffb.zzb(new zzcce(3));
                }
            });
            return;
        }
        zzdcp zzdcpVarZzah = zzdcf.zzah(null);
        for (final zzdcp<? extends zzbju> zzdcpVar : list) {
            zzdcpVarZzah = zzdcf.zzb(zzdcf.zzb(zzdcpVarZzah, Throwable.class, new zzdbq(zzdcgVar) { // from class: com.google.android.gms.internal.ads.zzbkd
                private final zzdcg zzffb;

                {
                    this.zzffb = zzdcgVar;
                }

                @Override // com.google.android.gms.internal.ads.zzdbq
                public final zzdcp zzf(Object obj) {
                    this.zzffb.zzb((Throwable) obj);
                    return zzdcf.zzah(null);
                }
            }, this.executor), new zzdbq(this, zzdcgVar, zzdcpVar) { // from class: com.google.android.gms.internal.ads.zzbkg
                private final zzbkb zzffc;
                private final zzdcg zzffd;
                private final zzdcp zzffe;

                {
                    this.zzffc = this;
                    this.zzffd = zzdcgVar;
                    this.zzffe = zzdcpVar;
                }

                @Override // com.google.android.gms.internal.ads.zzdbq
                public final zzdcp zzf(Object obj) {
                    return this.zzffc.zza(this.zzffd, this.zzffe, (zzbju) obj);
                }
            }, this.executor);
        }
        zzdcf.zza(zzdcpVarZzah, new zzbkh(this, zzdcgVar), this.executor);
    }

    public final boolean isLoading() {
        return this.zzacz;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzafj() {
        zzawx.zzdwa.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbkf
            private final zzbkb zzffc;

            {
                this.zzffc = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzffc.zzafk();
            }
        });
    }

    final /* synthetic */ void zzafk() {
        this.zzacz = false;
    }

    final /* synthetic */ zzdcp zza(zzdcg zzdcgVar, zzdcp zzdcpVar, zzbju zzbjuVar) throws Exception {
        if (zzbjuVar != null) {
            zzdcgVar.onSuccess(zzbjuVar);
        }
        return zzdcf.zza(zzdcpVar, ((Long) zzuo.zzoj().zzd(zzyt.zzcmh)).longValue(), TimeUnit.MILLISECONDS, this.zzfez);
    }
}
