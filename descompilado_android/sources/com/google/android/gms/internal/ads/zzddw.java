package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzddw {
    private zzdje zzgsb;

    private zzddw(zzdje zzdjeVar) {
        this.zzgsb = zzdjeVar;
    }

    static final zzddw zza(zzdje zzdjeVar) throws GeneralSecurityException {
        if (zzdjeVar == null || zzdjeVar.zzauf() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        return new zzddw(zzdjeVar);
    }

    final zzdje zzapl() {
        return this.zzgsb;
    }

    public final String toString() {
        return zzdei.zzc(this.zzgsb).toString();
    }
}
