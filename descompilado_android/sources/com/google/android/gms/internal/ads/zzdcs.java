package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public interface zzdcs extends ExecutorService {
    <T> zzdcp<T> zzd(Callable<T> callable);

    zzdcp<?> zzg(Runnable runnable);
}
