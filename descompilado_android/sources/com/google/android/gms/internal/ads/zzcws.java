package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcws implements zzdvi<ScheduledExecutorService> {
    private final zzdvv<ThreadFactory> zzgku;

    public zzcws(zzdvv<ThreadFactory> zzdvvVar) {
        this.zzgku = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (ScheduledExecutorService) zzdvo.zza(new ScheduledThreadPoolExecutor(1, this.zzgku.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
