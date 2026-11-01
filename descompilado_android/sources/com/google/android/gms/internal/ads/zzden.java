package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
class zzden implements zzdee<zzddj> {
    private static final Logger logger = Logger.getLogger(zzden.class.getName());

    zzden() {
    }

    @Override // com.google.android.gms.internal.ads.zzdee
    public final Class<zzddj> zzapj() {
        return zzddj.class;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static class zza implements zzddj {
        private final zzdec<zzddj> zzgsr;

        private zza(zzdec<zzddj> zzdecVar) {
            this.zzgsr = zzdecVar;
        }

        @Override // com.google.android.gms.internal.ads.zzddj
        public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            return zzdlu.zza(this.zzgsr.zzapq().zzapp(), this.zzgsr.zzapq().zzapm().zzc(bArr, bArr2));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdee
    public final /* synthetic */ zzddj zza(zzdec<zzddj> zzdecVar) throws GeneralSecurityException {
        return new zza(zzdecVar);
    }
}
