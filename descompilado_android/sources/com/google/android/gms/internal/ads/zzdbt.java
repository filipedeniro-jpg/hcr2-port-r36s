package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdbt<V> extends zzdbl<Object, V> {
    zzdbt(zzdaf<? extends zzdcp<?>> zzdafVar, boolean z, Executor executor, Callable<V> callable) {
        zzd(new zzdby(this, zzdafVar, z, new zzdbw(this, callable, executor)));
    }
}
