package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdln implements zzdnm {
    private final SecretKeySpec zzhal;
    private final int zzhan;
    private final int zzhao;

    public zzdln(byte[] bArr, int i) throws GeneralSecurityException {
        zzdob.zzfh(bArr.length);
        this.zzhal = new SecretKeySpec(bArr, "AES");
        this.zzhao = zzdnb.zzhdl.zzhf("AES/CTR/NoPadding").getBlockSize();
        if (i < 12 || i > this.zzhao) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzhan = i;
    }

    @Override // com.google.android.gms.internal.ads.zzdnm
    public final byte[] zzo(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzhan;
        if (length > Integer.MAX_VALUE - i) {
            int i2 = Integer.MAX_VALUE - i;
            StringBuilder sb = new StringBuilder(43);
            sb.append("plaintext length can not exceed ");
            sb.append(i2);
            throw new GeneralSecurityException(sb.toString());
        }
        byte[] bArr2 = new byte[bArr.length + i];
        byte[] bArrZzfg = zzdnq.zzfg(i);
        System.arraycopy(bArrZzfg, 0, bArr2, 0, this.zzhan);
        int length2 = bArr.length;
        int i3 = this.zzhan;
        Cipher cipherZzhf = zzdnb.zzhdl.zzhf("AES/CTR/NoPadding");
        byte[] bArr3 = new byte[this.zzhao];
        System.arraycopy(bArrZzfg, 0, bArr3, 0, this.zzhan);
        cipherZzhf.init(1, this.zzhal, new IvParameterSpec(bArr3));
        if (cipherZzhf.doFinal(bArr, 0, length2, bArr2, i3) == length2) {
            return bArr2;
        }
        throw new GeneralSecurityException("stored output's length does not match input's length");
    }
}
