package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsq implements zzcra<zzcsr> {
    private ApplicationInfo applicationInfo;
    private boolean zzdlp;
    private ScheduledExecutorService zzfez;
    private zzaiu zzggy;

    public zzcsq(zzaiu zzaiuVar, ScheduledExecutorService scheduledExecutorService, boolean z, ApplicationInfo applicationInfo) {
        this.zzggy = zzaiuVar;
        this.zzfez = scheduledExecutorService;
        this.zzdlp = z;
        this.applicationInfo = applicationInfo;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcsr> zzalr() {
        if (!this.zzdlp) {
            return zzdcf.zzi(new Exception("Auto Collect Location is false."));
        }
        return zzdcf.zzb(zzdcf.zza(this.zzggy.zza(this.applicationInfo), ((Long) zzuo.zzoj().zzd(zzyt.zzcoj)).longValue(), TimeUnit.MILLISECONDS, this.zzfez), zzcst.zzdoi, zzawx.zzdvx);
    }
}
