package com.moat.analytics.mobile.iro;

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
final class p {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static long f626 = 9141242330850693853L;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String f627;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static a f628;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static b f629;

    p() {
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d)
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static double m580() {
        try {
            AudioManager audioManager = (AudioManager) com.moat.analytics.mobile.iro.a.m452().getSystemService(m581("㇕ꍩ\uf102߆嗠殛").intern());
            double dM571 = m571();
            double streamMaxVolume = audioManager.getStreamMaxVolume(3);
            Double.isNaN(dM571);
            Double.isNaN(streamMaxVolume);
            return dM571 / streamMaxVolume;
        } catch (Exception e) {
            o.m569(e);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private static int m571() {
        try {
            return ((AudioManager) com.moat.analytics.mobile.iro.a.m452().getSystemService(m581("㇕ꍩ\uf102߆嗠殛").intern())).getStreamVolume(3);
        } catch (Exception e) {
            o.m569(e);
            return 0;
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static void m573(final Application application) {
        try {
            AsyncTask.execute(new Runnable() { // from class: com.moat.analytics.mobile.iro.p.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(application);
                        if (!advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            String unused = p.f627 = advertisingIdInfo.getId();
                            com.moat.analytics.mobile.iro.b.m461(3, "Util", this, "Retrieved Advertising ID = " + p.f627);
                            return;
                        }
                        com.moat.analytics.mobile.iro.b.m461(3, "Util", this, "User has limited ad tracking");
                    } catch (Exception e) {
                        o.m569(e);
                    }
                }
            });
        } catch (Exception e) {
            o.m569(e);
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static String m581(String str) {
        char[] charArray = str.toCharArray();
        char c = charArray[0];
        int i = 1;
        char[] cArr = new char[charArray.length - 1];
        while (true) {
            if ((i < charArray.length ? 'K' : (char) 11) == 'K') {
                cArr[i - 1] = (char) (((long) (charArray[i] ^ (i * c))) ^ f626);
                i++;
            } else {
                return new String(cArr);
            }
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static String m579() {
        return f627;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static Context m575() {
        WeakReference<Context> weakReference = ((j) MoatAnalytics.getInstance()).f598;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static b m576() {
        b bVar = f629;
        if (bVar == null || !bVar.f640) {
            f629 = new b((byte) 0);
        }
        return f629;
    }

    static class b {

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private String f637;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private String f638;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private String f639;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private boolean f640;

        /* synthetic */ b(byte b) {
            this();
        }

        private b() {
            this.f640 = false;
            this.f638 = "_unknown_";
            this.f637 = "_unknown_";
            this.f639 = "_unknown_";
            try {
                Context contextM575 = p.m575();
                if (contextM575 != null) {
                    this.f640 = true;
                    PackageManager packageManager = contextM575.getPackageManager();
                    this.f637 = contextM575.getPackageName();
                    this.f638 = packageManager.getApplicationLabel(contextM575.getApplicationInfo()).toString();
                    this.f639 = packageManager.getInstallerPackageName(this.f637);
                    return;
                }
                com.moat.analytics.mobile.iro.b.m461(3, "Util", this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                o.m569(e);
            }
        }

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        final String m584() {
            return this.f638;
        }

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final String m583() {
            return this.f637;
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        final String m585() {
            String str = this.f639;
            return str != null ? str : "_unknown_";
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static a m572() {
        a aVar = f628;
        if (aVar == null || !aVar.f636) {
            f628 = new a((byte) 0);
        }
        return f628;
    }

    static class a {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        String f631;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        String f632;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        Integer f633;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        boolean f634;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        boolean f635;

        /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
        boolean f636;

        /* synthetic */ a(byte b) {
            this();
        }

        private a() {
            this.f632 = "_unknown_";
            this.f631 = "_unknown_";
            this.f633 = -1;
            this.f634 = false;
            this.f635 = false;
            this.f636 = false;
            try {
                Context contextM575 = p.m575();
                if (contextM575 != null) {
                    this.f636 = true;
                    TelephonyManager telephonyManager = (TelephonyManager) contextM575.getSystemService("phone");
                    this.f632 = telephonyManager.getSimOperatorName();
                    this.f631 = telephonyManager.getNetworkOperatorName();
                    this.f633 = Integer.valueOf(telephonyManager.getPhoneType());
                    this.f634 = p.m574();
                    this.f635 = p.m578(contextM575);
                }
            } catch (Exception e) {
                o.m569(e);
            }
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static boolean m578(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    static /* synthetic */ boolean m574() {
        int i;
        WeakReference<Context> weakReference = ((j) MoatAnalytics.getInstance()).f598;
        Context context = weakReference != null ? weakReference.get() : null;
        if ((context == null ? '9' : 'X') != '9') {
            if ((Build.VERSION.SDK_INT < 17 ? (char) 14 : 'J') == 'J') {
                i = Settings.Global.getInt(context.getContentResolver(), m581("涓Ｏ䦟\uda06Ⓨ녧ρ涹︧䢚픆⟨").intern(), 0);
            } else {
                i = Settings.Secure.getInt(context.getContentResolver(), m581("涓Ｏ䦟\uda06Ⓨ녧ρ涹︧䢚픆⟨").intern(), 0);
            }
        } else {
            i = 0;
        }
        return (i != 1 ? 'c' : '/') != 'c';
    }
}
