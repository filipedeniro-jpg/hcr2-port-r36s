package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdfj implements zzdds {
    private final zzdec<zzdds> zzgsv;

    public zzdfj(zzdec<zzdds> zzdecVar) {
        this.zzgsv = zzdecVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzdlu.zza(this.zzgsv.zzapq().zzapp(), this.zzgsv.zzapq().zzapm().zzc(bArr, bArr2));
    }
}
