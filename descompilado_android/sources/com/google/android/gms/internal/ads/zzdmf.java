package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdmf implements zzddp {
    private static final byte[] zzgss = new byte[0];
    private final String zzhbl;
    private final byte[] zzhbm;
    private final zzdmz zzhbn;
    private final zzdmc zzhbo;
    private final ECPrivateKey zzhbp;
    private final zzdmh zzhbq;

    public zzdmf(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzdmz zzdmzVar, zzdmc zzdmcVar) throws GeneralSecurityException {
        this.zzhbp = eCPrivateKey;
        this.zzhbq = new zzdmh(eCPrivateKey);
        this.zzhbm = bArr;
        this.zzhbl = str;
        this.zzhbn = zzdmzVar;
        this.zzhbo = zzdmcVar;
    }
}
