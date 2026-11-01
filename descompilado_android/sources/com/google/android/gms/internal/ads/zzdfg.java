package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
class zzdfg implements zzdee<zzddp> {
    private static final Logger logger = Logger.getLogger(zzdfg.class.getName());

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static class zza implements zzddp {
        private final zzdec<zzddp> zzgsv;

        public zza(zzdec<zzddp> zzdecVar) {
            this.zzgsv = zzdecVar;
        }
    }

    zzdfg() {
    }

    @Override // com.google.android.gms.internal.ads.zzdee
    public final Class<zzddp> zzapj() {
        return zzddp.class;
    }

    @Override // com.google.android.gms.internal.ads.zzdee
    public final /* synthetic */ zzddp zza(zzdec<zzddp> zzdecVar) throws GeneralSecurityException {
        return new zza(zzdecVar);
    }
}
