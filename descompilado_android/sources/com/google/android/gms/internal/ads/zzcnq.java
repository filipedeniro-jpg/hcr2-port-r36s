package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnq implements zzcra<zzcrb<Bundle>> {
    private final Executor executor;
    private final zzatb zzblz;

    zzcnq(Executor executor, zzatb zzatbVar) {
        this.executor = executor;
        this.zzblz = zzatbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrb<Bundle>> zzalr() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmx)).booleanValue()) {
            return zzdcf.zzah(null);
        }
        return zzdcf.zzb(this.zzblz.zzuk(), zzcnt.zzdoi, this.executor);
    }
}
