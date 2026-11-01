package com.appsflyer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
final class g {

    static final class d {

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        static final g f145 = new g();
    }

    g() {
    }

    static final class b {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private final String f142;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private final String f143;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final String f144;

        b(@NonNull String str, @Nullable String str2, @Nullable String str3) {
            this.f143 = str;
            this.f144 = str2;
            this.f142 = str3;
        }

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        final String m132() {
            return this.f143;
        }

        @Nullable
        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        final String m130() {
            return this.f144;
        }

        @Nullable
        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final String m131() {
            return this.f142;
        }

        b() {
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        static void m129(Context context) {
            Context applicationContext = context.getApplicationContext();
            AFLogger.afInfoLog("onBecameBackground");
            AppsFlyerLib.getInstance().m80();
            AFLogger.afInfoLog("callStatsBackground background call");
            AppsFlyerLib.getInstance().m76(new WeakReference<>(applicationContext));
            s sVarM187 = s.m187();
            if (sVarM187.m190()) {
                sVarM187.m197();
                if (applicationContext != null) {
                    s.m183(applicationContext.getPackageName(), applicationContext.getPackageManager());
                }
                sVarM187.m194();
            } else {
                AFLogger.afDebugLog("RD status is OFF");
            }
            AFExecutor.getInstance().m7();
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static b m128(@NonNull Context context) {
        String simOperatorName;
        TelephonyManager telephonyManager;
        String str = "unknown";
        String networkOperatorName = null;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            try {
                if (connectivityManager != null) {
                    boolean z = false;
                    if (21 <= Build.VERSION.SDK_INT) {
                        for (Network network : connectivityManager.getAllNetworks()) {
                            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                                if (1 == networkInfo.getType()) {
                                    str = "WIFI";
                                    break;
                                }
                                if (networkInfo.getType() == 0) {
                                    str = "MOBILE";
                                    break;
                                }
                                return new b(str, networkOperatorName, simOperatorName);
                            }
                        }
                    } else {
                        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
                        if (networkInfo2 != null && networkInfo2.isConnectedOrConnecting()) {
                            str = "WIFI";
                            break;
                        }
                        NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(0);
                        if (networkInfo3 != null && networkInfo3.isConnectedOrConnecting()) {
                            str = "MOBILE";
                            break;
                        }
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                            z = true;
                        }
                        if (z) {
                            if (1 == activeNetworkInfo.getType()) {
                                str = "WIFI";
                                break;
                            }
                            if (activeNetworkInfo.getType() == 0) {
                                str = "MOBILE";
                                break;
                            }
                            return new b(str, networkOperatorName, simOperatorName);
                        }
                    }
                }
                networkOperatorName = telephonyManager.getNetworkOperatorName();
                if ((networkOperatorName == null || networkOperatorName.isEmpty()) && 2 == telephonyManager.getPhoneType()) {
                    networkOperatorName = "CDMA";
                }
            } catch (Throwable th) {
                th = th;
                AFLogger.afErrorLog("Exception while collecting network info. ", th);
            }
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            simOperatorName = telephonyManager.getSimOperatorName();
        } catch (Throwable th2) {
            th = th2;
            simOperatorName = null;
        }
        return new b(str, networkOperatorName, simOperatorName);
    }
}
