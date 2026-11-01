package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcss implements zzdvi<zzcsq> {
    private final zzdvv<zzaiu> zzesq;
    private final zzdvv<ScheduledExecutorService> zzfom;
    private final zzdvv<ApplicationInfo> zzfty;
    private final zzdvv<Boolean> zzggz;

    public zzcss(zzdvv<zzaiu> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<Boolean> zzdvvVar3, zzdvv<ApplicationInfo> zzdvvVar4) {
        this.zzesq = zzdvvVar;
        this.zzfom = zzdvvVar2;
        this.zzggz = zzdvvVar3;
        this.zzfty = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcsq(this.zzesq.get(), this.zzfom.get(), this.zzggz.get().booleanValue(), this.zzfty.get());
    }
}
