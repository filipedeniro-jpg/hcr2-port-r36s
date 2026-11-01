package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
class zzdcc<V> extends zzdbz<V> implements zzdbf.zzg<V> {
    zzdcc() {
    }

    @Override // com.google.android.gms.internal.ads.zzdbf, java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (V) super.get(j, timeUnit);
    }
}
