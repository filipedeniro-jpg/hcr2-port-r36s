package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqc<T> {
    public Executor executor;
    public T zzfhn;

    public static <T> zzbqc<T> zzb(T t, Executor executor) {
        return new zzbqc<>(t, executor);
    }

    public zzbqc(T t, Executor executor) {
        this.zzfhn = t;
        this.executor = executor;
    }
}
