package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zziq {
    private static final zziq zza;
    private static final zziq zzb;

    private zziq() {
    }

    abstract <L> List<L> zza(Object obj, long j);

    abstract <L> void zza(Object obj, Object obj2, long j);

    abstract void zzb(Object obj, long j);

    static zziq zza() {
        return zza;
    }

    static zziq zzb() {
        return zzb;
    }

    static {
        zzit zzitVar = null;
        zza = new zzis();
        zzb = new zziv();
    }
}
