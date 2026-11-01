package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Locale;

/* JADX INFO: compiled from: EncryptionUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class ce {
    @Nullable
    public static String P(@NonNull String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(str.getBytes(Charset.forName("UTF-8")));
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02X", Byte.valueOf(b)));
            }
            return sb.toString().toLowerCase(Locale.ROOT);
        } catch (Exception unused) {
            return null;
        }
    }
}
