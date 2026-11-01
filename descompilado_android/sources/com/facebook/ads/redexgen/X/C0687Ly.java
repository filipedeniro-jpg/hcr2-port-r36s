package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ly, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0687Ly {
    private C0687Ly() {
    }

    public static void B(byte[] buffer, long offset, int length) {
        C0684Lv.B(buffer, "Buffer must be not null!");
        C0684Lv.D(offset >= 0, "Data offset must be positive!");
        C0684Lv.D(length >= 0 && length <= buffer.length, "Length must be in range [0..buffer.length]");
    }

    public static void C(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e("ProxyCache", "Error closing resource", e);
            }
        }
    }

    public static String D(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return H(messageDigest.digest(string.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String E(String url) {
        try {
            return URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error decoding url", e);
        }
    }

    public static String F(String url) {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding url", e);
        }
    }

    public static String G(String url) {
        MimeTypeMap mimes = MimeTypeMap.getSingleton();
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(url);
        if (TextUtils.isEmpty(fileExtensionFromUrl)) {
            return null;
        }
        return mimes.getMimeTypeFromExtension(fileExtensionFromUrl);
    }

    private static String H(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bytes) {
            stringBuffer.append(String.format(Locale.US, "%02x", Byte.valueOf(b)));
        }
        return stringBuffer.toString();
    }
}
