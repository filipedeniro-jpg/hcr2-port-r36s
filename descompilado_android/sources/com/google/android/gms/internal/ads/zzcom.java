package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.ads.zzcrb;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcom<S extends zzcrb<?>> {
    private final Clock zzbmd;
    public final zzdcp<S> zzgek;
    private final long zzgel;

    public zzcom(zzdcp<S> zzdcpVar, long j, Clock clock) {
        this.zzgek = zzdcpVar;
        this.zzbmd = clock;
        this.zzgel = clock.elapsedRealtime() + j;
    }

    public final boolean hasExpired() {
        return this.zzgel < this.zzbmd.elapsedRealtime();
    }
}
