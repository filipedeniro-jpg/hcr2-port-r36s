package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcrb;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpw<S extends zzcrb<?>> implements zzcra<S> {
    private final ScheduledExecutorService zzfck;
    private final zzcra<S> zzgen;
    private final long zzgfa;

    public zzcpw(zzcra<S> zzcraVar, long j, ScheduledExecutorService scheduledExecutorService) {
        this.zzgen = zzcraVar;
        this.zzgfa = j;
        this.zzfck = scheduledExecutorService;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<S> zzalr() {
        zzdcp<S> zzdcpVarZzalr = this.zzgen.zzalr();
        long j = this.zzgfa;
        if (j > 0) {
            zzdcpVarZzalr = zzdcf.zza(zzdcpVarZzalr, j, TimeUnit.MILLISECONDS, this.zzfck);
        }
        return zzdcf.zzb(zzdcpVarZzalr, Throwable.class, zzcpz.zzbks, zzawx.zzdwb);
    }
}
