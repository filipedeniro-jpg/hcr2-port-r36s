package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsw implements zzdvi<zzcsu> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzana> zzesq;
    private final zzdvv<ScheduledExecutorService> zzfom;

    public zzcsw(zzdvv<zzana> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<Context> zzdvvVar3) {
        this.zzesq = zzdvvVar;
        this.zzfom = zzdvvVar2;
        this.zzejn = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcsu(this.zzesq.get(), this.zzfom.get(), this.zzejn.get());
    }
}
