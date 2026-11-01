package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzces implements Callable {
    private final zzcen zzfvl;

    private zzces(zzcen zzcenVar) {
        this.zzfvl = zzcenVar;
    }

    static Callable zza(zzcen zzcenVar) {
        return new zzces(zzcenVar);
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.zzfvl.getWritableDatabase();
    }
}
