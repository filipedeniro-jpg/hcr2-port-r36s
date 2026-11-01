package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.ads.zzcrb;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcon<S extends zzcrb<?>> implements zzcra<S> {
    private final Clock zzbmd;
    private final AtomicReference<zzcom<S>> zzgem = new AtomicReference<>();
    private final zzcra<S> zzgen;
    private final long zzgeo;

    public zzcon(zzcra<S> zzcraVar, long j, Clock clock) {
        this.zzbmd = clock;
        this.zzgen = zzcraVar;
        this.zzgeo = j;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<S> zzalr() {
        zzcom<S> zzcomVar = this.zzgem.get();
        if (zzcomVar == null || zzcomVar.hasExpired()) {
            zzcomVar = new zzcom<>(this.zzgen.zzalr(), this.zzgeo, this.zzbmd);
            this.zzgem.set(zzcomVar);
        }
        return zzcomVar.zzgek;
    }
}
