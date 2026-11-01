package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzae extends Exception {
    private long zzad;
    private final zzo zzbk;

    public zzae() {
        this.zzbk = null;
    }

    public zzae(zzo zzoVar) {
        this.zzbk = zzoVar;
    }

    public zzae(String str) {
        super(str);
        this.zzbk = null;
    }

    public zzae(Throwable th) {
        super(th);
        this.zzbk = null;
    }

    final void zza(long j) {
        this.zzad = j;
    }
}
