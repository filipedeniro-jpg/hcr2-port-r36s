package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdme implements zzdds {
    private static final byte[] zzgss = new byte[0];
    private final zzdmg zzhbk;
    private final String zzhbl;
    private final byte[] zzhbm;
    private final zzdmz zzhbn;
    private final zzdmc zzhbo;

    public zzdme(ECPublicKey eCPublicKey, byte[] bArr, String str, zzdmz zzdmzVar, zzdmc zzdmcVar) throws GeneralSecurityException {
        zzdmv.zza(eCPublicKey);
        this.zzhbk = new zzdmg(eCPublicKey);
        this.zzhbm = bArr;
        this.zzhbl = str;
        this.zzhbn = zzdmzVar;
        this.zzhbo = zzdmcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzdmj zzdmjVarZza = this.zzhbk.zza(this.zzhbl, this.zzhbm, bArr2, this.zzhbo.zzapv(), this.zzhbn);
        byte[] bArrZzc = this.zzhbo.zzl(zzdmjVarZza.zzaws()).zzc(bArr, zzgss);
        byte[] bArrZzawr = zzdmjVarZza.zzawr();
        return ByteBuffer.allocate(bArrZzawr.length + bArrZzc.length).put(bArrZzawr).put(bArrZzc).array();
    }
}
