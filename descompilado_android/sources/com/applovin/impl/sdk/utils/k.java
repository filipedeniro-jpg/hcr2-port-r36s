package com.applovin.impl.sdk.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class k {
    private static final char[] a = "0123456789abcdef".toCharArray();

    public static int a(String str) {
        return a(str, 0);
    }

    public static int a(String str, int i) {
        return d(str) ? Integer.parseInt(str) : i;
    }

    public static String a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (i > str.length()) {
            i = str.length();
        }
        return str.substring(0, i);
    }

    public static String a(String str, com.applovin.impl.sdk.h hVar) {
        return a(str, (Integer) hVar.a(com.applovin.impl.sdk.b.b.ab), (String) hVar.a(com.applovin.impl.sdk.b.b.aa));
    }

    private static String a(String str, Integer num, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No algorithm specified");
        }
        if (str == null || str.length() < 1) {
            return "";
        }
        if (str2.length() < 1 || "none".equals(str2)) {
            return str;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes("UTF-8"));
            String strA = a(messageDigest.digest());
            return (strA == null || num.intValue() <= 0) ? strA : strA.substring(0, Math.min(num.intValue(), strA.length()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Programming error: UTF-8 is not know encoding", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Unknown algorithm \"" + str2 + "\"", e2);
        }
    }

    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return str;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        builderBuildUpon.appendQueryParameter(str2, str3);
        return builderBuildUpon.build().toString();
    }

    public static String a(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            return str;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builderBuildUpon.build().toString();
    }

    public static String a(boolean z) {
        return z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("No data specified");
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            char[] cArr2 = a;
            cArr[i2] = cArr2[(bArr[i] & 240) >>> 4];
            cArr[i2 + 1] = cArr2[bArr[i] & 15];
        }
        return new String(cArr);
    }

    public static boolean a(String str, String str2) {
        return b(str) && b(str2) && str.toLowerCase().contains(str2.toLowerCase());
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static String c(String str) {
        return str == null ? "" : str;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char cCharAt = str.charAt(0);
        int i = (cCharAt == '-' || cCharAt == '+') ? 1 : 0;
        int length = str.length();
        if (i == 1 && length == 1) {
            return false;
        }
        while (i < length) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
