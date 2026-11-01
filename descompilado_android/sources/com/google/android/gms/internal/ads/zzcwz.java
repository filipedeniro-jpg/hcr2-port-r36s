package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzcwz<E> {
    private static final zzdcp<?> zzgla = zzdcf.zzah(null);
    private final ScheduledExecutorService zzfck;
    private final zzdcs zzfnq;
    private final zzcxl<E> zzglb;

    public zzcwz(zzdcs zzdcsVar, ScheduledExecutorService scheduledExecutorService, zzcxl<E> zzcxlVar) {
        this.zzfnq = zzdcsVar;
        this.zzfck = scheduledExecutorService;
        this.zzglb = zzcxlVar;
    }

    protected abstract String zzv(E e);

    public final zzcxd zzu(E e) {
        return new zzcxd(this, e);
    }

    public final <I> zzcxf<I> zza(E e, zzdcp<I> zzdcpVar) {
        return new zzcxf<>(this, e, zzdcpVar, Collections.singletonList(zzdcpVar), zzdcpVar);
    }

    public final zzcxb zza(E e, zzdcp<?>... zzdcpVarArr) {
        return new zzcxb(this, e, Arrays.asList(zzdcpVarArr));
    }
}
