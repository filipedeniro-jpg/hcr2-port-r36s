package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
class zzdfr implements zzdee<zzdea> {
    private static final Logger logger = Logger.getLogger(zzdfr.class.getName());

    zzdfr() {
    }

    @Override // com.google.android.gms.internal.ads.zzdee
    public final Class<zzdea> zzapj() {
        return zzdea.class;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static class zza implements zzdea {
        private final zzdec<zzdea> zzgsv;
        private final byte[] zzgte;

        private zza(zzdec<zzdea> zzdecVar) {
            this.zzgte = new byte[]{0};
            this.zzgsv = zzdecVar;
        }

        @Override // com.google.android.gms.internal.ads.zzdea
        public final byte[] zzk(byte[] bArr) throws GeneralSecurityException {
            if (this.zzgsv.zzapq().zzapo().equals(zzdjq.LEGACY)) {
                return zzdlu.zza(this.zzgsv.zzapq().zzapp(), this.zzgsv.zzapq().zzapm().zzk(zzdlu.zza(bArr, this.zzgte)));
            }
            return zzdlu.zza(this.zzgsv.zzapq().zzapp(), this.zzgsv.zzapq().zzapm().zzk(bArr));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdee
    public final /* synthetic */ zzdea zza(zzdec<zzdea> zzdecVar) throws GeneralSecurityException {
        return new zza(zzdecVar);
    }
}
