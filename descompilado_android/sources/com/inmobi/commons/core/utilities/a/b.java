package com.inmobi.commons.core.utilities.a;

import android.annotation.SuppressLint;
import android.util.Base64;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: RequestEncryptionUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    private static final String a = "b";
    private static String b = "AES";
    private static String c = "AES/CBC/PKCS7Padding";
    private static byte[] d;

    public static String a(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, String str2, String str3) {
        try {
            byte[] bArrB = b(str.getBytes("UTF-8"), bArr, bArr2);
            byte[] bArrB2 = b(bArrB, bArr3);
            byte[] bArrA = a(bArrB);
            byte[] bArrA2 = a(bArrB2);
            return new String(Base64.encode(a(a(a(a(a(a(bArr), a(bArr3)), a(bArr2)), str3, str2)), a(bArrA, bArrA2)), 8));
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating secret message; ").append(e.getMessage());
            return null;
        }
    }

    @SuppressLint({"TrulyRandom"})
    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, b);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher cipher = Cipher.getInstance(c);
            cipher.init(1, secretKeySpec, ivParameterSpec);
            cipher.init(1, secretKeySpec, ivParameterSpec);
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted AES bytes; ").append(th.getMessage());
            return null;
        }
    }

    private static byte[] b() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom());
            SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
            if (secretKeyGenerateKey != null) {
                return secretKeyGenerateKey.getEncoded();
            }
            return null;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating AES public key; ").append(e.getMessage());
            return null;
        }
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, 0, bArr2.length, "HmacSHA1");
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            return mac.doFinal(bArr);
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        long length = bArr.length;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putLong(length);
        byte[] bArrArray = byteBufferAllocate.array();
        byte[] bArr2 = new byte[bArrArray.length + bArr.length];
        System.arraycopy(bArrArray, 0, bArr2, 0, bArrArray.length);
        System.arraycopy(bArr, 0, bArr2, bArrArray.length, bArr.length);
        return bArr2;
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(str2, 16), new BigInteger(str, 16)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/nopadding");
            cipher.init(1, rSAPublicKey);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted RSA bytes; ").append(th.getMessage());
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static synchronized byte[] a() {
        a aVar = new a();
        try {
            if ((System.currentTimeMillis() / 1000) - aVar.a.b("last_generated_ts", 0L) > 86400) {
                byte[] bArrB = b();
                d = bArrB;
                aVar.a(Base64.encodeToString(bArrB, 0));
            } else if (d == null) {
                try {
                    d = Base64.decode(aVar.a.c("aes_public_key"), 0);
                } catch (IllegalArgumentException unused) {
                    byte[] bArrB2 = b();
                    d = bArrB2;
                    aVar.a(Base64.encodeToString(bArrB2, 0));
                }
            }
        } catch (Exception unused2) {
        }
        return d;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, b);
        try {
            Cipher cipher = Cipher.getInstance(c);
            cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in decrypting AES response; ").append(th.getMessage());
            return null;
        }
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        try {
            new SecureRandom().nextBytes(bArr);
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating crypto key; ").append(e.getMessage());
        }
        return bArr;
    }
}
