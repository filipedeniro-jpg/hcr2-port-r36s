package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.util.Base64;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LV {
    private LV() {
    }

    public static void B(HttpsURLConnection uc, Set<String> set, Set<String> set2) throws Exception {
        if (Build.VERSION.SDK_INT == 15 && "4.0.3".equals(Build.VERSION.RELEASE)) {
            return;
        }
        try {
            for (Certificate certificate : uc.getServerCertificates()) {
                X509Certificate x509Certificate = (X509Certificate) certificate;
                String strC = C(x509Certificate.getEncoded(), CommonUtils.SHA1_INSTANCE);
                if (set != null && set.contains(strC)) {
                    return;
                }
                String strC2 = C(x509Certificate.getPublicKey().getEncoded(), CommonUtils.SHA1_INSTANCE);
                if (set2 != null && set2.contains(strC2)) {
                    return;
                }
            }
            throw new CertificateException("Unable to find valid certificate or public key.");
        } catch (Exception e) {
            throw e;
        }
    }

    private static String C(byte[] bytes, String format) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(format);
        messageDigest.reset();
        return Base64.encodeToString(messageDigest.digest(bytes), 0);
    }
}
