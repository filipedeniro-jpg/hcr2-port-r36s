package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class zzaxi<T> {
    private final zzaxf<T> zzdwh = new zzaxf<>();
    private final AtomicInteger zzdwi = new AtomicInteger(0);

    public zzaxi() {
        zzdcf.zza(this.zzdwh, new zzaxl(this), zzawx.zzdwb);
    }

    @Deprecated
    public final void zza(zzaxj<T> zzaxjVar, zzaxh zzaxhVar) {
        zzdcf.zza(this.zzdwh, new zzaxk(this, zzaxjVar, zzaxhVar), zzawx.zzdwb);
    }

    @Deprecated
    public final void zzm(T t) {
        this.zzdwh.set(t);
    }

    @Deprecated
    public final void reject() {
        this.zzdwh.setException(new Exception());
    }

    @Deprecated
    public final int getStatus() {
        return this.zzdwi.get();
    }
}
