package com.inmobi.commons.core.utilities.a;

import android.annotation.SuppressLint;
import android.util.Base64;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/* JADX INFO: compiled from: UidEncryptionUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final String a = "c";

    @SuppressLint({"TrulyRandom"})
    public static String a(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger("C10F7968CFE2C76AC6F0650C877806D4514DE58FC239592D2385BCE5609A84B2A0FBDAF29B05505EAD1FDFEF3D7209ACBF34B5D0A806DF18147EA9C0337D6B5B", 16), new BigInteger("010001", 16)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/nopadding");
            cipher.init(1, rSAPublicKey);
            return new String(Base64.encode(a(str.getBytes("UTF-8"), cipher), 0));
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted UID-map; ").append(e.getMessage());
            return null;
        }
    }

    private static byte[] a(byte[] bArr, Cipher cipher) throws BadPaddingException, IllegalBlockSizeException {
        byte[] bArrA = new byte[0];
        int length = bArr.length;
        byte[] bArr2 = new byte[64];
        for (int i = 0; i < length; i++) {
            if (i > 0 && i % 64 == 0) {
                bArrA = a(bArrA, cipher.doFinal(bArr2));
                bArr2 = new byte[i + 64 > length ? length - i : 64];
            }
            bArr2[i % 64] = bArr[i];
        }
        return a(bArrA, cipher.doFinal(bArr2));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
