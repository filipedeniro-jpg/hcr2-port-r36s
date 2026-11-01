package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzla {
    private static final zzla zztm;
    private static final zzla zztn;

    private zzla() {
    }

    abstract void zza(Object obj, long j);

    abstract <L> void zza(Object obj, Object obj2, long j);

    static zzla zzdt() {
        return zztm;
    }

    static zzla zzdu() {
        return zztn;
    }

    static {
        zzlb zzlbVar = null;
        zztm = new zzlc();
        zztn = new zzld();
    }
}
