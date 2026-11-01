package com.chartboost.sdk.Libraries;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    private c() {
    }

    public static synchronized byte[] a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            com.chartboost.sdk.Tracking.a.a(c.class, "sha1", e);
            return null;
        } catch (Exception e2) {
            com.chartboost.sdk.Tracking.a.a(c.class, "sha1", e2);
            return null;
        }
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        return String.format(Locale.US, "%0" + (bArr.length << 1) + "x", bigInteger);
    }
}
