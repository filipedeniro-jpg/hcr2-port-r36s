package com.chartboost.sdk.impl;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class bh {
    public static final BigInteger a = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
    public static final BigInteger b;
    public static final BigInteger c;
    public static final BigInteger d;
    public static final BigInteger e;
    public static final BigInteger f;
    public static final BigInteger g;
    public static final BigInteger h;
    public static final File[] i;
    private static final Charset j;

    static {
        BigInteger bigInteger = a;
        b = bigInteger.multiply(bigInteger);
        c = a.multiply(b);
        d = a.multiply(c);
        e = a.multiply(d);
        f = a.multiply(e);
        g = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID).multiply(BigInteger.valueOf(1152921504606846976L));
        h = a.multiply(g);
        i = new File[0];
        j = Charset.forName("UTF-8");
    }

    public static FileInputStream a(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
            return new FileInputStream(file);
        }
        throw new FileNotFoundException("File '" + file + "' does not exist");
    }

    public static byte[] b(File file) throws Throwable {
        FileInputStream fileInputStreamA;
        try {
            fileInputStreamA = a(file);
            try {
                byte[] bArrA = bi.a(fileInputStreamA, file.length());
                bi.a((InputStream) fileInputStreamA);
                return bArrA;
            } catch (Throwable th) {
                th = th;
                bi.a((InputStream) fileInputStreamA);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStreamA = null;
        }
    }
}
