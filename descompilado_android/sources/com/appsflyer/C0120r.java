package com.appsflyer;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.util.Formatter;

/* JADX INFO: renamed from: com.appsflyer.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0120r {
    C0120r() {
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    public static String m180(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return m178(messageDigest.digest());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to SHA1");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    public static String m179(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return m178(messageDigest.digest());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to MD5");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    public static String m177(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE);
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to SHA-256");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String m178(byte[] bArr) {
        Formatter formatter = new Formatter();
        for (byte b : bArr) {
            formatter.format("%02x", Byte.valueOf(b));
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }
}
