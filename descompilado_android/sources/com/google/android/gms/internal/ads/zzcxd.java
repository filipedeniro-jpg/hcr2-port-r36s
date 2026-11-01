package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxd {
    private final E zzgkx;
    private final /* synthetic */ zzcwz zzgld;

    private zzcxd(zzcwz zzcwzVar, E e) {
        this.zzgld = zzcwzVar;
        this.zzgkx = e;
    }

    public final <O> zzcxf<O> zzb(zzdcp<O> zzdcpVar) {
        return new zzcxf<>(this.zzgld, this.zzgkx, zzcwz.zzgla, Collections.emptyList(), zzdcpVar);
    }

    public final <O> zzcxf<O> zzc(Callable<O> callable) {
        return zza(callable, this.zzgld.zzfnq);
    }

    private final <O> zzcxf<O> zza(Callable<O> callable, zzdcs zzdcsVar) {
        return new zzcxf<>(this.zzgld, this.zzgkx, zzcwz.zzgla, Collections.emptyList(), zzdcsVar.submit(callable));
    }

    public final zzcxf<?> zza(final zzcwx zzcwxVar, zzdcs zzdcsVar) {
        return zza(new Callable(zzcwxVar) { // from class: com.google.android.gms.internal.ads.zzcxc
            private final zzcwx zzgle;

            {
                this.zzgle = zzcwxVar;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zzgle.run();
                return null;
            }
        }, zzdcsVar);
    }
}
