package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.api.BuildConfigApi;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MU {
    private static final String B = MU.class.getSimpleName();

    private MU() {
    }

    @Nullable
    public static String B(Context context) {
        try {
            return G(context);
        } catch (Exception e) {
            return null;
        }
    }

    public static MT C() {
        try {
            boolean isRooted = F() || D() || E("su");
            if (isRooted) {
                return MT.ROOTED;
            }
            return MT.UNROOTED;
        } catch (Throwable th) {
            if (BuildConfigApi.isDebug()) {
                Log.e(B, "Rooted check failed", th);
            }
            return MT.UNKNOWN;
        }
    }

    private static boolean D() {
        String buildTags = Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private static boolean E(String binary) {
        File[] fileArrListFiles;
        for (String str : System.getenv("PATH").split(":")) {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File pathDir : fileArrListFiles) {
                    if (pathDir.getName().equals(binary)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean F() {
        File superUserApk = new File("/system/app/Superuser.apk");
        return superUserApk.exists();
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private static String G(Context context) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException, CertificateException {
        StringBuilder sb = new StringBuilder();
        for (Signature signature : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
            sb.append(C0689Ma.C(MessageDigest.getInstance("SHA1").digest(H(signature).getEncoded())));
            sb.append(";");
        }
        return sb.toString();
    }

    private static PublicKey H(Signature signature) throws CertificateException {
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        Certificate cert = certFactory.generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        return cert.getPublicKey();
    }
}
