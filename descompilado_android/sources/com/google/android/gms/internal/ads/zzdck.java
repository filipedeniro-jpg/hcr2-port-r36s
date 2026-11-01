package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdck<V> {
    private final boolean zzgqe;
    private final zzdak<zzdcp<? extends V>> zzgra;

    private zzdck(boolean z, zzdak<zzdcp<? extends V>> zzdakVar) {
        this.zzgqe = false;
        this.zzgra = zzdakVar;
    }

    public final <C> zzdcp<C> zza(Callable<C> callable, Executor executor) {
        return new zzdbt(this.zzgra, this.zzgqe, executor, callable);
    }

    /* synthetic */ zzdck(boolean z, zzdak zzdakVar, zzdci zzdciVar) {
        this(false, zzdakVar);
    }
}
