package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdmy implements zzddj {
    private final zzdnm zzhdd;
    private final zzdea zzhde;
    private final int zzhdf;

    public zzdmy(zzdnm zzdnmVar, zzdea zzdeaVar, int i) {
        this.zzhdd = zzdnmVar;
        this.zzhde = zzdeaVar;
        this.zzhdf = i;
    }

    @Override // com.google.android.gms.internal.ads.zzddj
    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZzo = this.zzhdd.zzo(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        return zzdlu.zza(bArrZzo, this.zzhde.zzk(zzdlu.zza(bArr2, bArrZzo, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8))));
    }
}
