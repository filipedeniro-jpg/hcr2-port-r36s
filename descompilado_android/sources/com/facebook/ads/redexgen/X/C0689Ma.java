package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ma, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0689Ma {
    private C0689Ma() {
    }

    @Nullable
    public static final String B(String path) throws Exception {
        return D(new File(path));
    }

    public static String C(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int i = (b >>> 4) & 15;
            int i2 = 0;
            while (true) {
                buf.append((i < 0 || i > 9) ? (char) ((i - 10) + 97) : (char) (i + 48));
                i = b & 15;
                int i3 = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i3;
            }
        }
        return buf.toString();
    }

    @Nullable
    private static String D(File file) throws Exception {
        InputStream fis;
        int i;
        try {
            try {
                fis = new FileInputStream(file);
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    byte[] bArr = new byte[1024];
                    do {
                        i = fis.read(bArr);
                        if (i > 0) {
                            messageDigest.update(bArr, 0, i);
                        }
                    } while (i != -1);
                    String strE = E(messageDigest.digest());
                    try {
                        fis.close();
                    } catch (IOException e) {
                    }
                    return strE;
                } catch (IOException e2) {
                    throw new Exception("IO exception.");
                } catch (NoSuchAlgorithmException e3) {
                    throw new Exception("No such algorithm.");
                }
            } catch (FileNotFoundException e4) {
                throw new Exception("File not found or not accessible.");
            }
        } catch (Throwable th) {
            try {
                fis.close();
            } catch (IOException e5) {
            }
            throw th;
        }
    }

    private static String E(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }
}
