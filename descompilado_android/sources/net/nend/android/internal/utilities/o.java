package net.nend.android.internal.utilities;

import android.net.Uri;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: StringUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o {
    public static String a(String str) {
        byte[] bArrDigest = new byte[16];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            bArrDigest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            j.b(e.getMessage(), e);
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArrDigest) {
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }

    public static String a(String str, HashMap<String, String> map) {
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builderBuildUpon.build().toString();
    }
}
