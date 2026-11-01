package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsu implements zzcra<zzcsv> {
    private ScheduledExecutorService zzfez;
    private zzana zzgha;
    private Context zzlk;

    public zzcsu(zzana zzanaVar, ScheduledExecutorService scheduledExecutorService, Context context) {
        this.zzgha = zzanaVar;
        this.zzfez = scheduledExecutorService;
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcsv> zzalr() {
        return zzdcf.zzb(zzdcf.zza(this.zzgha.zzq(this.zzlk), ((Long) zzuo.zzoj().zzd(zzyt.zzcoy)).longValue(), TimeUnit.MILLISECONDS, this.zzfez), zzcsx.zzdoi, zzawx.zzdvx);
    }
}
