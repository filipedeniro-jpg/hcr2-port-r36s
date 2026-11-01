package com.appsflyer;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import com.ironsource.sdk.constants.Constants;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.Enumeration;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes.dex */
class AFKeystoreWrapper {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private KeyStore f9;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private Context f11;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private final Object f12 = new Object();

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private String f10 = "";

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private int f8 = 0;

    public AFKeystoreWrapper(Context context) {
        this.f11 = context;
        AFLogger.afInfoLog("Initialising KeyStore..");
        try {
            this.f9 = KeyStore.getInstance("AndroidKeyStore");
            this.f9.load(null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            AFLogger.afErrorLog("Couldn't load keystore instance of type: AndroidKeyStore", e);
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m11(String str) {
        this.f10 = str;
        this.f8 = 0;
        m9(m10());
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m14() {
        String strM10 = m10();
        synchronized (this.f12) {
            this.f8++;
            AFLogger.afInfoLog("Deleting key with alias: ".concat(String.valueOf(strM10)));
            try {
                synchronized (this.f12) {
                    this.f9.deleteEntry(strM10);
                }
            } catch (KeyStoreException e) {
                StringBuilder sb = new StringBuilder("Exception ");
                sb.append(e.getMessage());
                sb.append(" occurred");
                AFLogger.afErrorLog(sb.toString(), e);
            }
        }
        m9(m10());
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final boolean m12() {
        boolean z;
        synchronized (this.f12) {
            z = true;
            if (this.f9 != null) {
                try {
                    Enumeration<String> enumerationAliases = this.f9.aliases();
                    while (enumerationAliases.hasMoreElements()) {
                        String strNextElement = enumerationAliases.nextElement();
                        if (strNextElement != null && strNextElement.startsWith("com.appsflyer")) {
                            String[] strArrSplit = strNextElement.split(",");
                            if (strArrSplit.length == 3) {
                                AFLogger.afInfoLog("Found a matching AF key with alias:\n".concat(String.valueOf(strNextElement)));
                                try {
                                    String[] strArrSplit2 = strArrSplit[1].trim().split(Constants.RequestParameters.EQUAL);
                                    String[] strArrSplit3 = strArrSplit[2].trim().split(Constants.RequestParameters.EQUAL);
                                    if (strArrSplit2.length == 2 && strArrSplit3.length == 2) {
                                        this.f10 = strArrSplit2[1].trim();
                                        this.f8 = Integer.parseInt(strArrSplit3[1].trim());
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    StringBuilder sb = new StringBuilder("Couldn't list KeyStore Aliases: ");
                                    sb.append(th.getClass().getName());
                                    AFLogger.afErrorLog(sb.toString(), th);
                                }
                            }
                        }
                        StringBuilder sb2 = new StringBuilder("Couldn't list KeyStore Aliases: ");
                        sb2.append(th.getClass().getName());
                        AFLogger.afErrorLog(sb2.toString(), th);
                    }
                    z = false;
                } catch (Throwable th2) {
                    th = th2;
                    z = false;
                }
            } else {
                z = false;
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private void m9(String str) {
        AFLogger.afInfoLog("Creating a new key with alias: ".concat(String.valueOf(str)));
        try {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 5);
            AlgorithmParameterSpec algorithmParameterSpecBuild = null;
            synchronized (this.f12) {
                if (!this.f9.containsAlias(str)) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        algorithmParameterSpecBuild = new KeyGenParameterSpec.Builder(str, 3).setCertificateSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
                    } else if (Build.VERSION.SDK_INT >= 18 && !"OPPO".equals(Build.BRAND)) {
                        algorithmParameterSpecBuild = new KeyPairGeneratorSpec.Builder(this.f11).setAlias(str).setSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setSerialNumber(BigInteger.ONE).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
                    }
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    keyPairGenerator.initialize(algorithmParameterSpecBuild);
                    keyPairGenerator.generateKeyPair();
                } else {
                    AFLogger.afInfoLog("Alias already exists: ".concat(String.valueOf(str)));
                }
            }
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Exception ");
            sb.append(th.getMessage());
            sb.append(" occurred");
            AFLogger.afErrorLog(sb.toString(), th);
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private String m10() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.appsflyer,");
        synchronized (this.f12) {
            sb.append("KSAppsFlyerId=");
            sb.append(this.f10);
            sb.append(",");
            sb.append("KSAppsFlyerRICounter=");
            sb.append(this.f8);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final String m15() {
        String str;
        synchronized (this.f12) {
            str = this.f10;
        }
        return str;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final int m13() {
        int i;
        synchronized (this.f12) {
            i = this.f8;
        }
        return i;
    }
}
