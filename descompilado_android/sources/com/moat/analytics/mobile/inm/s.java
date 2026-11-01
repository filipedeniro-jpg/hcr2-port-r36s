package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.annotation.FloatRange;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
class s {
    private static String a;
    private static a b;
    private static b c;

    static class a {
        private boolean a;
        private String b;
        private String c;
        private String d;

        private a() {
            this.a = false;
            this.b = "_unknown_";
            this.c = "_unknown_";
            this.d = "_unknown_";
            try {
                Context contextC = s.c();
                if (contextC == null) {
                    p.a(3, "Util", this, "Can't get app name, appContext is null.");
                    return;
                }
                this.a = true;
                PackageManager packageManager = contextC.getPackageManager();
                this.c = contextC.getPackageName();
                this.b = packageManager.getApplicationLabel(contextC.getApplicationInfo()).toString();
                this.d = packageManager.getInstallerPackageName(this.c);
            } catch (Exception e) {
                m.a(e);
            }
        }

        String a() {
            return this.b;
        }

        String b() {
            return this.c;
        }

        String c() {
            String str = this.d;
            return str != null ? str : "_unknown_";
        }
    }

    static class b {
        String a;
        String b;
        Integer c;
        boolean d;
        boolean e;
        boolean f;

        private b() {
            this.a = "_unknown_";
            this.b = "_unknown_";
            this.c = -1;
            this.d = false;
            this.e = false;
            this.f = false;
            try {
                Context contextC = s.c();
                if (contextC != null) {
                    this.f = true;
                    TelephonyManager telephonyManager = (TelephonyManager) contextC.getSystemService("phone");
                    this.a = telephonyManager.getSimOperatorName();
                    this.b = telephonyManager.getNetworkOperatorName();
                    this.c = Integer.valueOf(telephonyManager.getPhoneType());
                    this.d = s.i();
                    this.e = s.b(contextC);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }
    }

    s() {
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d)
    static double a() {
        try {
            AudioManager audioManager = (AudioManager) com.moat.analytics.mobile.inm.a.a().getSystemService("audio");
            double dH = h();
            double streamMaxVolume = audioManager.getStreamMaxVolume(3);
            Double.isNaN(dH);
            Double.isNaN(streamMaxVolume);
            return dH / streamMaxVolume;
        } catch (Exception e) {
            m.a(e);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    static void a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() { // from class: com.moat.analytics.mobile.inm.s.1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    try {
                        Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        Class<?> cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                        Object objInvoke = cls.getMethod("getAdvertisingIdInfo", Context.class).invoke(null, context);
                        if (((Boolean) cls2.getMethod(Constants.RequestParameters.isLAT, new Class[0]).invoke(objInvoke, new Object[0])).booleanValue()) {
                            p.a(3, "Util", this, "User has limited ad tracking");
                            return;
                        }
                        String unused = s.a = (String) cls2.getMethod("getId", new Class[0]).invoke(objInvoke, new Object[0]);
                        p.a(3, "Util", this, "Retrieved Advertising ID = " + s.a);
                    } catch (ClassNotFoundException e) {
                        e = e;
                        str = "ClassNotFoundException while retrieving Advertising ID";
                        p.a("Util", this, str, e);
                    } catch (NoSuchMethodException e2) {
                        e = e2;
                        str = "NoSuchMethodException while retrieving Advertising ID";
                        p.a("Util", this, str, e);
                    } catch (Exception e3) {
                        m.a(e3);
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

    static boolean b(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static Context c() {
        WeakReference<Context> weakReference = ((k) MoatAnalytics.getInstance()).e;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    static a d() {
        a aVar = b;
        if (aVar == null || !aVar.a) {
            b = new a();
        }
        return b;
    }

    static b e() {
        b bVar = c;
        if (bVar == null || !bVar.f) {
            c = new b();
        }
        return c;
    }

    private static int h() {
        try {
            return ((AudioManager) com.moat.analytics.mobile.inm.a.a().getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            m.a(e);
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean i() {
        int i;
        Context contextC = c();
        if (contextC != null) {
            i = Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(contextC.getContentResolver(), "adb_enabled", 0) : Settings.Secure.getInt(contextC.getContentResolver(), "adb_enabled", 0);
        } else {
            i = 0;
        }
        return i == 1;
    }
}
