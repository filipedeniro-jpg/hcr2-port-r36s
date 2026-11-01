package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdlm implements zzddj {
    private final byte[] zzhaj;
    private final byte[] zzhak;
    private final SecretKeySpec zzhal;
    private final int zzham;

    public zzdlm(byte[] bArr, int i) throws GeneralSecurityException {
        if (i != 12 && i != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.zzham = i;
        zzdob.zzfh(bArr.length);
        this.zzhal = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
        cipher.init(1, this.zzhal);
        this.zzhaj = zzn(cipher.doFinal(new byte[16]));
        this.zzhak = zzn(this.zzhaj);
    }

    private static byte[] zzd(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] zzn(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        while (i < 15) {
            int i2 = i + 1;
            bArr2[i] = (byte) ((bArr[i] << 1) ^ ((bArr[i2] & 255) >>> 7));
            i = i2;
        }
        bArr2[15] = (byte) ((bArr[15] << 1) ^ ((bArr[0] & 128) != 0 ? TsExtractor.TS_STREAM_TYPE_E_AC3 : 0));
        return bArr2;
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws BadPaddingException, IllegalBlockSizeException {
        byte[] bArrZzd;
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzd(bArr2, this.zzhaj));
        }
        byte[] bArrDoFinal = cipher.doFinal(bArr2);
        byte[] bArrDoFinal2 = bArrDoFinal;
        int i4 = 0;
        while (i3 - i4 > 16) {
            for (int i5 = 0; i5 < 16; i5++) {
                bArrDoFinal2[i5] = (byte) (bArrDoFinal2[i5] ^ bArr[(i2 + i4) + i5]);
            }
            bArrDoFinal2 = cipher.doFinal(bArrDoFinal2);
            i4 += 16;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i4 + i2, i2 + i3);
        if (bArrCopyOfRange.length == 16) {
            bArrZzd = zzd(bArrCopyOfRange, this.zzhaj);
        } else {
            byte[] bArrCopyOf = Arrays.copyOf(this.zzhak, 16);
            for (int i6 = 0; i6 < bArrCopyOfRange.length; i6++) {
                bArrCopyOf[i6] = (byte) (bArrCopyOf[i6] ^ bArrCopyOfRange[i6]);
            }
            bArrCopyOf[bArrCopyOfRange.length] = (byte) (bArrCopyOf[bArrCopyOfRange.length] ^ 128);
            bArrZzd = bArrCopyOf;
        }
        return cipher.doFinal(zzd(bArrDoFinal2, bArrZzd));
    }

    @Override // com.google.android.gms.internal.ads.zzddj
    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzham;
        if (length > (Integer.MAX_VALUE - i) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr3 = new byte[bArr.length + i + 16];
        byte[] bArrZzfg = zzdnq.zzfg(i);
        System.arraycopy(bArrZzfg, 0, bArr3, 0, this.zzham);
        Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
        cipher.init(1, this.zzhal);
        byte[] bArrZza = zza(cipher, 0, bArrZzfg, 0, bArrZzfg.length);
        byte[] bArr4 = bArr2 == null ? new byte[0] : bArr2;
        byte[] bArrZza2 = zza(cipher, 1, bArr4, 0, bArr4.length);
        Cipher cipher2 = Cipher.getInstance("AES/CTR/NOPADDING");
        cipher2.init(1, this.zzhal, new IvParameterSpec(bArrZza));
        cipher2.doFinal(bArr, 0, bArr.length, bArr3, this.zzham);
        byte[] bArrZza3 = zza(cipher, 2, bArr3, this.zzham, bArr.length);
        int length2 = bArr.length + this.zzham;
        for (int i2 = 0; i2 < 16; i2++) {
            bArr3[length2 + i2] = (byte) ((bArrZza2[i2] ^ bArrZza[i2]) ^ bArrZza3[i2]);
        }
        return bArr3;
    }
}
