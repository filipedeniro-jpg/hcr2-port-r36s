package com.google.android.gms.internal.ads;

import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccd {
    private final zzdcs zzfnq;
    private final zzdcs zzftc;
    private final zzcdk zzftd;
    private final zzdvc<zzcdt> zzfte;

    public zzccd(zzdcs zzdcsVar, zzdcs zzdcsVar2, zzcdk zzcdkVar, zzdvc<zzcdt> zzdvcVar) {
        this.zzftc = zzdcsVar;
        this.zzfnq = zzdcsVar2;
        this.zzftd = zzcdkVar;
        this.zzfte = zzdvcVar;
    }

    public final zzdcp<InputStream> zzc(final zzaok zzaokVar) {
        final zzdcp<InputStream> zzdcpVarZzf;
        String str = zzaokVar.packageName;
        com.google.android.gms.ads.internal.zzp.zzjy();
        if (zzatv.zzeh(str)) {
            zzdcpVarZzf = zzdcf.zzi(new zzcdr(0));
        } else {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrj)).booleanValue()) {
                zzdcpVarZzf = zzdcf.zzb(this.zzftc.submit(new Callable(this, zzaokVar) { // from class: com.google.android.gms.internal.ads.zzccg
                    private final zzccd zzftf;
                    private final zzaok zzftg;

                    {
                        this.zzftf = this;
                        this.zzftg = zzaokVar;
                    }

                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return this.zzftf.zzd(this.zzftg);
                    }
                }), ExecutionException.class, zzccf.zzbks, this.zzfnq);
            } else {
                zzdcpVarZzf = this.zzftd.zzf(zzaokVar);
            }
        }
        zzdcp<InputStream> zzdcpVarZzb = zzdcf.zzb(zzdcpVarZzf, zzcdr.class, new zzdbq(this, zzaokVar) { // from class: com.google.android.gms.internal.ads.zzcci
            private final zzccd zzftf;
            private final zzaok zzftg;

            {
                this.zzftf = this;
                this.zzftg = zzaokVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzftf.zza(this.zzftg, (zzcdr) obj);
            }
        }, this.zzfnq);
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcrj)).booleanValue()) {
            zzdcpVarZzb.addListener(new Runnable(zzdcpVarZzf) { // from class: com.google.android.gms.internal.ads.zzcch
                private final zzdcp zzfoj;

                {
                    this.zzfoj = zzdcpVarZzf;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzfoj.cancel(true);
                }
            }, zzawx.zzdwb);
        }
        return zzdcpVarZzb;
    }

    final /* synthetic */ zzdcp zza(zzaok zzaokVar, zzcdr zzcdrVar) throws Exception {
        return this.zzfte.get().zzh(zzaokVar);
    }

    final /* synthetic */ InputStream zzd(zzaok zzaokVar) throws Exception {
        return this.zzftd.zzf(zzaokVar).get(((Integer) zzuo.zzoj().zzd(zzyt.zzcri)).intValue(), TimeUnit.SECONDS);
    }
}
