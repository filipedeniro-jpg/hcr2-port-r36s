package com.google.android.gms.internal.firebase_remote_config;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzia {
    private static final zzia zzva;
    private static final zzia zzvb;

    private zzia() {
    }

    abstract <L> List<L> zza(Object obj, long j);

    abstract <L> void zza(Object obj, Object obj2, long j);

    abstract void zzb(Object obj, long j);

    static zzia zzhs() {
        return zzva;
    }

    static zzia zzht() {
        return zzvb;
    }

    static {
        zzhz zzhzVar = null;
        zzva = new zzic();
        zzvb = new zzib();
    }
}
