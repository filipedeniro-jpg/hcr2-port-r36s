package com.google.android.gms.internal.ads;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdnz {
    public static String zza(zzdni zzdniVar) throws GeneralSecurityException {
        zzdob.zzc(zzdniVar);
        String strValueOf = String.valueOf(zzdniVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 9);
        sb.append(strValueOf);
        sb.append("withECDSA");
        return sb.toString();
    }

    public static String zzb(zzdni zzdniVar) throws GeneralSecurityException {
        int i = zzdny.zzhel[zzdniVar.ordinal()];
        if (i == 1) {
            return CommonUtils.SHA1_INSTANCE;
        }
        if (i == 2) {
            return CommonUtils.SHA256_INSTANCE;
        }
        if (i == 3) {
            return "SHA-384";
        }
        if (i == 4) {
            return "SHA-512";
        }
        String strValueOf = String.valueOf(zzdniVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 17);
        sb.append("Unsupported hash ");
        sb.append(strValueOf);
        throw new GeneralSecurityException(sb.toString());
    }

    public static boolean zzawy() {
        try {
            Class.forName("android.app.Application", false, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static BigInteger zzw(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static byte[] zza(BigInteger bigInteger, int i) throws GeneralSecurityException {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i) {
            return byteArray;
        }
        int i2 = i + 1;
        if (byteArray.length > i2) {
            throw new GeneralSecurityException("integer too large");
        }
        if (byteArray.length == i2) {
            if (byteArray[0] == 0) {
                return Arrays.copyOfRange(byteArray, 1, byteArray.length);
            }
            throw new GeneralSecurityException("integer too large");
        }
        byte[] bArr = new byte[i];
        System.arraycopy(byteArray, 0, bArr, i - byteArray.length, byteArray.length);
        return bArr;
    }

    public static byte[] zza(byte[] bArr, int i, zzdni zzdniVar) throws GeneralSecurityException {
        MessageDigest messageDigestZzhf = zzdnb.zzhdo.zzhf(zzb(zzdniVar));
        int digestLength = messageDigestZzhf.getDigestLength();
        byte[] bArr2 = new byte[i];
        int length = 0;
        for (int i2 = 0; i2 <= (i - 1) / digestLength; i2++) {
            messageDigestZzhf.reset();
            messageDigestZzhf.update(bArr);
            messageDigestZzhf.update(zza(BigInteger.valueOf(i2), 4));
            byte[] bArrDigest = messageDigestZzhf.digest();
            System.arraycopy(bArrDigest, 0, bArr2, length, Math.min(bArrDigest.length, bArr2.length - length));
            length += bArrDigest.length;
        }
        return bArr2;
    }
}
