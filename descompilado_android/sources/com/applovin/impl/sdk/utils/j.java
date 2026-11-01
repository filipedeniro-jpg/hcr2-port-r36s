package com.applovin.impl.sdk.utils;

import android.text.TextUtils;
import android.util.Base64;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class j {
    public static String a(String str, String str2) {
        return a(str, str2, new byte[]{-83, -98, -53, -112, -29, -118, 55, 117, 59, 8, -12, -15, 73, 110, -67, 57, 117, 4, -26, 97, 66, -12, 125, 91, -119, -103, -30, 114, 123, 54, 51, -77});
    }

    public static String a(String str, String str2, long j) {
        return a(str, str2, j, new byte[]{-83, -98, -53, -112, -29, -118, 55, 117, 59, 8, -12, -15, 73, 110, -67, 57, 117, 4, -26, 97, 66, -12, 125, 91, -119, -103, -30, 114, 123, 54, 51, -77});
    }

    private static String a(String str, String str2, long j, byte[] bArr) {
        if (str2 == null) {
            throw new IllegalArgumentException("No SDK key specified");
        }
        if (str2.length() < 80) {
            throw new IllegalArgumentException("SDK key is too short");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("No salt specified");
        }
        char c = ' ';
        if (bArr.length < 32) {
            throw new IllegalArgumentException("Salt is too short");
        }
        if (str == null || str.isEmpty()) {
            return str;
        }
        try {
            String strSubstring = str2.substring(32);
            String strSubstring2 = str2.substring(0, 32);
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bArrA = a(strSubstring2, bArr);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(((byte) ((j >> 0) & 255)) ^ bArrA[0]);
            byteArrayOutputStream.write(((byte) ((j >> 8) & 255)) ^ bArrA[1]);
            byteArrayOutputStream.write(((byte) ((j >> 16) & 255)) ^ bArrA[2]);
            byteArrayOutputStream.write(((byte) ((j >> 24) & 255)) ^ bArrA[3]);
            byteArrayOutputStream.write(((byte) ((j >> 32) & 255)) ^ bArrA[4]);
            byteArrayOutputStream.write(((byte) ((j >> 40) & 255)) ^ bArrA[5]);
            byteArrayOutputStream.write(((byte) ((j >> 48) & 255)) ^ bArrA[6]);
            byteArrayOutputStream.write(((byte) ((j >> 56) & 255)) ^ bArrA[7]);
            int i = 0;
            while (i < bytes.length) {
                long j2 = j + ((long) i);
                long j3 = (j2 ^ (j2 >> 33)) * (-4417276706812531889L);
                long j4 = (j3 ^ (j3 >> 29)) * (-8796714831421723037L);
                long j5 = j4 ^ (j4 >> c);
                int i2 = i + 0;
                byteArrayOutputStream.write((byte) (((long) ((i2 >= bytes.length ? (byte) 0 : bytes[i2]) ^ bArrA[i2 % bArrA.length])) ^ ((j5 >> 0) & 255)));
                int i3 = i + 1;
                byteArrayOutputStream.write((byte) (((long) (bArrA[i3 % bArrA.length] ^ (i3 >= bytes.length ? (byte) 0 : bytes[i3]))) ^ ((j5 >> 8) & 255)));
                int i4 = i + 2;
                byteArrayOutputStream.write((byte) (((long) (bArrA[i4 % bArrA.length] ^ (i4 >= bytes.length ? (byte) 0 : bytes[i4]))) ^ ((j5 >> 16) & 255)));
                int i5 = i + 3;
                byteArrayOutputStream.write((byte) (((long) (bArrA[i5 % bArrA.length] ^ (i5 >= bytes.length ? (byte) 0 : bytes[i5]))) ^ ((j5 >> 24) & 255)));
                int i6 = i + 4;
                byteArrayOutputStream.write((byte) (((long) (bArrA[i6 % bArrA.length] ^ (i6 >= bytes.length ? (byte) 0 : bytes[i6]))) ^ ((j5 >> 32) & 255)));
                int i7 = i + 5;
                byteArrayOutputStream.write((byte) (((long) (bArrA[i7 % bArrA.length] ^ (i7 >= bytes.length ? (byte) 0 : bytes[i7]))) ^ ((j5 >> 40) & 255)));
                int i8 = i + 6;
                byteArrayOutputStream.write((byte) (((long) (bArrA[i8 % bArrA.length] ^ (i8 >= bytes.length ? (byte) 0 : bytes[i8]))) ^ ((j5 >> 48) & 255)));
                int i9 = i + 7;
                byteArrayOutputStream.write((byte) (((long) (bArrA[i9 % bArrA.length] ^ (i9 >= bytes.length ? (byte) 0 : bytes[i9]))) ^ ((j5 >> 56) & 255)));
                i += 8;
                c = ' ';
            }
            String strB = b(byteArrayOutputStream.toByteArray());
            return "1:" + a(bArr) + ":" + strSubstring + ":" + strB;
        } catch (IOException unused) {
            return null;
        }
    }

    private static String a(String str, String str2, byte[] bArr) {
        if (str2 == null) {
            throw new IllegalArgumentException("No SDK key specified");
        }
        if (str2.length() < 80) {
            throw new IllegalArgumentException("SDK key is too short");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("No salt specified");
        }
        if (bArr.length < 32) {
            throw new IllegalArgumentException("Salt is too short");
        }
        if (TextUtils.isEmpty(str) || str.trim().startsWith("{")) {
            return str;
        }
        String[] strArrSplit = str.split(":");
        try {
            if (!"1".equals(strArrSplit[0]) || strArrSplit.length != 4) {
                return null;
            }
            char c = 1;
            String str3 = strArrSplit[1];
            String str4 = strArrSplit[2];
            byte[] bArrA = a(strArrSplit[3]);
            if (str2.endsWith(str4) && a(bArr).equals(str3)) {
                byte[] bArrA2 = a(str2.substring(0, 32), bArr);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrA);
                char c2 = '\b';
                try {
                    long j = (((long) ((byteArrayInputStream.read() ^ bArrA2[5]) & 255)) << 40) | (((long) ((byteArrayInputStream.read() ^ bArrA2[0]) & 255)) << 0) | (((long) ((byteArrayInputStream.read() ^ bArrA2[1]) & 255)) << 8) | (((long) ((byteArrayInputStream.read() ^ bArrA2[2]) & 255)) << 16) | (((long) ((byteArrayInputStream.read() ^ bArrA2[3]) & 255)) << 24) | (((long) ((byteArrayInputStream.read() ^ bArrA2[4]) & 255)) << 32) | (((long) ((byteArrayInputStream.read() ^ bArrA2[6]) & 255)) << 48) | (((long) ((byteArrayInputStream.read() ^ bArrA2[7]) & 255)) << 56);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr2 = new byte[8];
                    int i = byteArrayInputStream.read(bArr2);
                    int i2 = 0;
                    while (i >= 0) {
                        long j2 = ((long) i2) + j;
                        long j3 = (j2 ^ (j2 >> 33)) * (-4417276706812531889L);
                        long j4 = (j3 ^ (j3 >> 29)) * (-8796714831421723037L);
                        long j5 = j4 ^ (j4 >> 32);
                        byteArrayOutputStream.write((byte) (((long) (bArr2[0] ^ bArrA2[(i2 + 0) % bArrA2.length])) ^ ((j5 >> 0) & 255)));
                        byteArrayOutputStream.write((byte) (((long) (bArr2[c] ^ bArrA2[(i2 + 1) % bArrA2.length])) ^ ((j5 >> c2) & 255)));
                        byteArrayOutputStream.write((byte) (((long) (bArrA2[(i2 + 2) % bArrA2.length] ^ bArr2[2])) ^ ((j5 >> 16) & 255)));
                        byteArrayOutputStream.write((byte) (((long) (bArr2[3] ^ bArrA2[(i2 + 3) % bArrA2.length])) ^ ((j5 >> 24) & 255)));
                        byteArrayOutputStream.write((byte) (((long) (bArrA2[(i2 + 4) % bArrA2.length] ^ bArr2[4])) ^ ((j5 >> 32) & 255)));
                        byteArrayOutputStream.write((byte) (((long) (bArr2[5] ^ bArrA2[(i2 + 5) % bArrA2.length])) ^ ((j5 >> 40) & 255)));
                        byteArrayOutputStream.write((byte) (((long) (bArrA2[(i2 + 6) % bArrA2.length] ^ bArr2[6])) ^ ((j5 >> 48) & 255)));
                        byteArrayOutputStream.write((byte) (((long) (bArrA2[(i2 + 7) % bArrA2.length] ^ bArr2[7])) ^ ((j5 >> 56) & 255)));
                        i2 += 8;
                        i = byteArrayInputStream.read(bArr2);
                        j = j;
                        c2 = '\b';
                        c = 1;
                    }
                    return new String(byteArrayOutputStream.toByteArray(), "UTF-8").trim();
                } catch (IOException unused) {
                    return null;
                }
            }
            return null;
        } catch (IOException unused2) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            messageDigest.update(bArr);
            return k.a(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not found", e);
        }
    }

    private static byte[] a(String str) {
        return Base64.decode(b(str), 0);
    }

    private static byte[] a(String str, byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE);
            messageDigest.update(bArr);
            messageDigest.update(str.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not found", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("SHA-1 algorithm not found", e2);
        }
    }

    private static String b(String str) {
        return str.replace('-', '+').replace('_', '/').replace('*', '=');
    }

    private static String b(byte[] bArr) throws UnsupportedEncodingException {
        return c(Base64.encode(bArr, 2));
    }

    private static String c(byte[] bArr) throws UnsupportedEncodingException {
        return new String(bArr, "UTF-8").replace('+', '-').replace('/', '_').replace('=', '*');
    }
}
