package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdll implements zzdea {
    private final int zzhaa;
    private final SecretKey zzhag;
    private byte[] zzhah;
    private byte[] zzhai;

    private static Cipher zzawp() throws GeneralSecurityException {
        return zzdnb.zzhdl.zzhf("AES/ECB/NoPadding");
    }

    public zzdll(byte[] bArr, int i) throws GeneralSecurityException {
        zzdob.zzfh(bArr.length);
        this.zzhag = new SecretKeySpec(bArr, "AES");
        this.zzhaa = 16;
        Cipher cipherZzawp = zzawp();
        cipherZzawp.init(1, this.zzhag);
        this.zzhah = zzdlq.zzp(cipherZzawp.doFinal(new byte[16]));
        this.zzhai = zzdlq.zzp(this.zzhah);
    }

    @Override // com.google.android.gms.internal.ads.zzdea
    public final byte[] zzk(byte[] bArr) throws GeneralSecurityException {
        byte[] bArrZzd;
        Cipher cipherZzawp = zzawp();
        cipherZzawp.init(1, this.zzhag);
        double length = bArr.length;
        Double.isNaN(length);
        int iMax = Math.max(1, (int) Math.ceil(length / 16.0d));
        if ((iMax << 4) == bArr.length) {
            bArrZzd = zzdlu.zza(bArr, (iMax - 1) << 4, this.zzhah, 0, 16);
        } else {
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, (iMax - 1) << 4, bArr.length);
            if (bArrCopyOfRange.length >= 16) {
                throw new IllegalArgumentException("x must be smaller than a block.");
            }
            byte[] bArrCopyOf = Arrays.copyOf(bArrCopyOfRange, 16);
            bArrCopyOf[bArrCopyOfRange.length] = -128;
            bArrZzd = zzdlu.zzd(bArrCopyOf, this.zzhai);
        }
        byte[] bArrDoFinal = new byte[16];
        for (int i = 0; i < iMax - 1; i++) {
            bArrDoFinal = cipherZzawp.doFinal(zzdlu.zza(bArrDoFinal, 0, bArr, i << 4, 16));
        }
        byte[] bArrZzd2 = zzdlu.zzd(bArrZzd, bArrDoFinal);
        byte[] bArr2 = new byte[this.zzhaa];
        System.arraycopy(cipherZzawp.doFinal(bArrZzd2), 0, bArr2, 0, this.zzhaa);
        return bArr2;
    }
}
