package com.moat.analytics.mobile.cha;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.annotation.FloatRange;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class r {

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static String f420;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static int[] f421 = {-39340411, 1646369784, -593413711, -1069164445, -50787683, -1327220997, 423245644, -742130253, 54775946, -495304555, 1880137505, 1742082653, 65717847, 1497802820, 828947133, -614454858, 941569790, -1897799303};

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static int f419 = 0;

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static int f416 = 1;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static e f417 = null;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static d f418 = null;

    r() {
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d)
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static double m410() {
        try {
            AudioManager audioManager = (AudioManager) c.m294().getSystemService(m407(new int[]{-1741845568, 995393484, -1443163044, -1832527325}, 5).intern());
            double dM401 = m401();
            double streamMaxVolume = audioManager.getStreamMaxVolume(3);
            Double.isNaN(dM401);
            Double.isNaN(streamMaxVolume);
            return dM401 / streamMaxVolume;
        } catch (Exception e2) {
            o.m389(e2);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private static int m401() {
        try {
            return ((AudioManager) c.m294().getSystemService(m407(new int[]{-1741845568, 995393484, -1443163044, -1832527325}, 5).intern())).getStreamVolume(3);
        } catch (Exception e2) {
            o.m389(e2);
            return 0;
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static void m408(final Application application) {
        try {
            AsyncTask.execute(new Runnable() { // from class: com.moat.analytics.mobile.cha.r.5
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(application);
                        if (!advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            String unused = r.f420 = advertisingIdInfo.getId();
                            a.m273(3, "Util", this, "Retrieved Advertising ID = " + r.f420);
                            return;
                        }
                        a.m273(3, "Util", this, "User has limited ad tracking");
                    } catch (Exception e2) {
                        o.m389(e2);
                    }
                }
            });
        } catch (Exception e2) {
            o.m389(e2);
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static String m406() {
        return f420;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static Context m409() {
        WeakReference<Context> weakReference = ((f) MoatAnalytics.getInstance()).f316;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static e m403() {
        e eVar = f417;
        if (eVar == null || !eVar.f431) {
            f417 = new e((byte) 0);
        }
        return f417;
    }

    static class e {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private String f429;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private String f430;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private boolean f431;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private String f432;

        /* synthetic */ e(byte b) {
            this();
        }

        private e() {
            this.f431 = false;
            this.f429 = "_unknown_";
            this.f430 = "_unknown_";
            this.f432 = "_unknown_";
            try {
                Context contextM409 = r.m409();
                if (contextM409 != null) {
                    this.f431 = true;
                    PackageManager packageManager = contextM409.getPackageManager();
                    this.f430 = contextM409.getPackageName();
                    this.f429 = packageManager.getApplicationLabel(contextM409.getApplicationInfo()).toString();
                    this.f432 = packageManager.getInstallerPackageName(this.f430);
                    return;
                }
                a.m273(3, "Util", this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                o.m389(e);
            }
        }

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        final String m414() {
            return this.f429;
        }

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final String m413() {
            return this.f430;
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        final String m415() {
            String str = this.f432;
            return str != null ? str : "_unknown_";
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static String m407(int[] iArr, int i) {
        char[] cArr = new char[4];
        char[] cArr2 = new char[iArr.length << 1];
        int[] iArr2 = (int[]) f421.clone();
        int i2 = 0;
        while (true) {
            if (!(i2 >= iArr.length)) {
                cArr[0] = iArr[i2] >>> 16;
                cArr[1] = (char) iArr[i2];
                int i3 = i2 + 1;
                cArr[2] = iArr[i3] >>> 16;
                cArr[3] = (char) iArr[i3];
                com.chartboost.sdk.impl.b.a(cArr, iArr2, false);
                int i4 = i2 << 1;
                cArr2[i4] = cArr[0];
                cArr2[i4 + 1] = cArr[1];
                cArr2[i4 + 2] = cArr[2];
                cArr2[i4 + 3] = cArr[3];
                i2 += 2;
            } else {
                return new String(cArr2, 0, i);
            }
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static d m404() {
        d dVar = f418;
        if (dVar == null || !dVar.f423) {
            f418 = new d((byte) 0);
        }
        return f418;
    }

    static class d {

        /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
        boolean f423;

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        boolean f424;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        boolean f425;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        String f426;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        String f427;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        Integer f428;

        /* synthetic */ d(byte b) {
            this();
        }

        private d() {
            this.f426 = "_unknown_";
            this.f427 = "_unknown_";
            this.f428 = -1;
            this.f425 = false;
            this.f424 = false;
            this.f423 = false;
            try {
                Context contextM409 = r.m409();
                if (contextM409 != null) {
                    this.f423 = true;
                    TelephonyManager telephonyManager = (TelephonyManager) contextM409.getSystemService("phone");
                    this.f426 = telephonyManager.getSimOperatorName();
                    this.f427 = telephonyManager.getNetworkOperatorName();
                    this.f428 = Integer.valueOf(telephonyManager.getPhoneType());
                    this.f425 = r.m400();
                    this.f424 = r.m405(contextM409);
                }
            } catch (Exception e) {
                o.m389(e);
            }
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static boolean m405(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    static /* synthetic */ boolean m400() {
        int i;
        WeakReference<Context> weakReference = ((f) MoatAnalytics.getInstance()).f316;
        Context context = weakReference != null ? weakReference.get() : null;
        if (context != null) {
            int i2 = f416 + 27;
            f419 = i2 % 128;
            int i3 = i2 % 2;
            if ((Build.VERSION.SDK_INT < 17 ? (char) 22 : (char) 19) == 22) {
                i = Settings.Secure.getInt(context.getContentResolver(), m407(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
            } else {
                int i4 = f416 + 87;
                f419 = i4 % 128;
                int i5 = i4 % 2;
                i = Settings.Global.getInt(context.getContentResolver(), m407(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
            }
        } else {
            i = 0;
        }
        if (!(i == 1)) {
            return false;
        }
        int i6 = f419 + 33;
        f416 = i6 % 128;
        int i7 = i6 % 2;
        return true;
    }
}
