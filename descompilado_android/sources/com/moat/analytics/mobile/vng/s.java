package com.moat.analytics.mobile.vng;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import androidx.annotation.FloatRange;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes2.dex */
class s {
    private static String a;

    static class a {
        private boolean a = false;
        private String b = "_unknown_";
        private String c = "_unknown_";

        a() {
        }

        private void c() {
            try {
                Context contextC = s.c();
                if (contextC != null) {
                    PackageManager packageManager = contextC.getPackageManager();
                    this.c = contextC.getPackageName();
                    this.b = packageManager.getApplicationLabel(contextC.getApplicationInfo()).toString();
                    this.a = true;
                } else {
                    p.a(3, "Util", this, "Can't get app name, appContext is null.");
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        String a() {
            if (this.a) {
                return this.b;
            }
            c();
            return this.b;
        }

        String b() {
            if (this.a) {
                return this.c;
            }
            c();
            return this.c;
        }
    }

    s() {
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d)
    static double a() {
        try {
            AudioManager audioManager = (AudioManager) com.moat.analytics.mobile.vng.a.a().getSystemService("audio");
            double dE = e();
            double streamMaxVolume = audioManager.getStreamMaxVolume(3);
            Double.isNaN(dE);
            Double.isNaN(streamMaxVolume);
            return dE / streamMaxVolume;
        } catch (Exception e) {
            m.a(e);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    static void a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() { // from class: com.moat.analytics.mobile.vng.s.1
                @Override // java.lang.Runnable
                public void run() {
                    String str;
                    try {
                        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            str = "User has limited ad tracking";
                        } else {
                            String unused = s.a = advertisingIdInfo.getId();
                            str = "Retrieved Advertising ID = " + s.a;
                        }
                        p.a(3, "Util", this, str);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            });
        } catch (Exception e) {
            m.a(e);
        }
    }

    static String b() {
        return a;
    }

    static Context c() {
        return ((k) MoatAnalytics.getInstance()).e.get();
    }

    private static int e() {
        try {
            return ((AudioManager) com.moat.analytics.mobile.vng.a.a().getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            m.a(e);
            return 0;
        }
    }
}
