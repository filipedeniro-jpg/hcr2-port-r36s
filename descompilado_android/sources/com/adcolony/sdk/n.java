package com.adcolony.sdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.facebook.AccessToken;
import com.facebook.appevents.UserDataStore;
import com.facebook.internal.NativeProtocol;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class n {
    static final String g = "Production";
    boolean b;
    boolean c;
    String a = "";
    JSONObject d = y.a();
    int e = 2;
    private String h = "android";
    private String i = "android_native";
    String f = "";
    private JSONArray j = y.b();

    String C() {
        return "3.3.5";
    }

    String v() {
        return "";
    }

    n() {
    }

    void a(JSONObject jSONObject) {
        this.d = jSONObject;
    }

    JSONObject a() {
        return this.d;
    }

    String b() {
        Activity activityC = com.adcolony.sdk.a.c();
        return activityC == null ? "" : Settings.Secure.getString(activityC.getContentResolver(), com.my.target.i.ANDROID_ID);
    }

    String c() {
        return this.a;
    }

    String d() {
        return System.getProperty("os.arch").toLowerCase();
    }

    void e() {
        this.b = false;
        com.adcolony.sdk.a.a("Device.get_info", new ah() { // from class: com.adcolony.sdk.n.1
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.n.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (n.this.r() < 14) {
                            n.this.new a(afVar, n.this, false).execute(new Void[0]);
                        } else {
                            n.this.new a(afVar, n.this, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        }
                    }
                });
            }
        });
        com.adcolony.sdk.a.a("Device.application_exists", new ah() { // from class: com.adcolony.sdk.n.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, IronSourceConstants.EVENTS_RESULT, aw.a(y.b(afVar.c(), "name")));
                y.a(jSONObjectA, "success", true);
                afVar.a(jSONObjectA).b();
            }
        });
    }

    String f() {
        return s() ? "tablet" : "phone";
    }

    boolean g() {
        return this.c;
    }

    String h() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) activityC.getSystemService("phone");
        String networkOperatorName = telephonyManager != null ? telephonyManager.getNetworkOperatorName() : "";
        return networkOperatorName.length() == 0 ? "unknown" : networkOperatorName;
    }

    int i() {
        ActivityManager activityManager;
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null || (activityManager = (ActivityManager) activityC.getApplicationContext().getSystemService("activity")) == null) {
            return 0;
        }
        return activityManager.getMemoryClass();
    }

    String j() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) activityC.getSystemService("phone");
        String simCountryIso = telephonyManager == null ? null : telephonyManager.getSimCountryIso();
        return simCountryIso == null ? "" : simCountryIso;
    }

    String k() {
        return TimeZone.getDefault().getID();
    }

    int l() {
        return TimeZone.getDefault().getOffset(15L) / 60000;
    }

    int m() {
        TimeZone timeZone = TimeZone.getDefault();
        if (timeZone.inDaylightTime(new Date())) {
            return timeZone.getDSTSavings() / 60000;
        }
        return 0;
    }

    long n() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / ((long) 1048576);
    }

    float o() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return 0.0f;
        }
        return activityC.getResources().getDisplayMetrics().density;
    }

    int p() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activityC.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    int q() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activityC.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    int r() {
        return Build.VERSION.SDK_INT;
    }

    double a(Context context) {
        Intent intentRegisterReceiver;
        if (context == null || (intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) == null) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
        if (intExtra < 0 || intExtra2 < 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        double d = intExtra;
        double d2 = intExtra2;
        Double.isNaN(d);
        Double.isNaN(d2);
        return d / d2;
    }

    boolean s() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return false;
        }
        DisplayMetrics displayMetrics = activityC.getResources().getDisplayMetrics();
        float f = displayMetrics.widthPixels / displayMetrics.xdpi;
        float f2 = displayMetrics.heightPixels / displayMetrics.ydpi;
        return Math.sqrt((double) ((f * f) + (f2 * f2))) >= 6.0d;
    }

    String t() {
        return Locale.getDefault().getLanguage();
    }

    String u() {
        return Locale.getDefault().getCountry();
    }

    String w() {
        return Build.MANUFACTURER;
    }

    String x() {
        return Build.MODEL;
    }

    String y() {
        return Build.VERSION.RELEASE;
    }

    JSONArray z() {
        return this.j;
    }

    int A() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return 2;
        }
        int i = activityC.getResources().getConfiguration().orientation;
        if (i != 1) {
            return i != 2 ? 2 : 1;
        }
        return 0;
    }

    int B() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activityC.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    boolean D() {
        if (!com.adcolony.sdk.a.d()) {
            return false;
        }
        int iA = A();
        if (iA != 0) {
            if (iA == 1 && this.e == 0) {
                new aa.a().a("Sending device info update").a(aa.d);
                this.e = iA;
                if (r() < 14) {
                    new a(null, this, true).execute(new Void[0]);
                } else {
                    new a(null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
                return true;
            }
        } else if (this.e == 1) {
            new aa.a().a("Sending device info update").a(aa.d);
            this.e = iA;
            if (r() < 14) {
                new a(null, this, true).execute(new Void[0]);
            } else {
                new a(null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
            return true;
        }
        return false;
    }

    class a extends AsyncTask<Void, Void, JSONObject> {
        af a;
        n b;
        boolean c;

        a(af afVar, n nVar, boolean z) {
            this.a = afVar;
            this.b = nVar;
            this.c = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public JSONObject doInBackground(Void... voidArr) {
            if (Build.VERSION.SDK_INT < 14) {
                return null;
            }
            return n.this.a(this.b);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(JSONObject jSONObject) {
            if (this.c) {
                new af("Device.update_info", 1, jSONObject).b();
            } else {
                this.a.a(jSONObject).b();
            }
        }
    }

    JSONObject a(n nVar) {
        JSONObject jSONObjectA = y.a();
        l lVarA = com.adcolony.sdk.a.a();
        y.a(jSONObjectA, "carrier_name", nVar.h());
        y.a(jSONObjectA, "data_path", com.adcolony.sdk.a.a().o().e());
        y.b(jSONObjectA, "device_api", nVar.r());
        y.b(jSONObjectA, "display_width", nVar.p());
        y.b(jSONObjectA, "display_height", nVar.q());
        y.b(jSONObjectA, "screen_width", nVar.p());
        y.b(jSONObjectA, "screen_height", nVar.q());
        y.b(jSONObjectA, "display_dpi", nVar.B());
        y.a(jSONObjectA, "device_type", nVar.f());
        y.a(jSONObjectA, "locale_language_code", nVar.t());
        y.a(jSONObjectA, UserDataStore.LAST_NAME, nVar.t());
        y.a(jSONObjectA, "locale_country_code", nVar.u());
        y.a(jSONObjectA, "locale", nVar.u());
        y.a(jSONObjectA, "mac_address", nVar.v());
        y.a(jSONObjectA, "manufacturer", nVar.w());
        y.a(jSONObjectA, "device_brand", nVar.w());
        y.a(jSONObjectA, "media_path", com.adcolony.sdk.a.a().o().d());
        y.a(jSONObjectA, "temp_storage_path", com.adcolony.sdk.a.a().o().f());
        y.b(jSONObjectA, "memory_class", nVar.i());
        y.b(jSONObjectA, "network_speed", 20);
        y.a(jSONObjectA, "memory_used_mb", nVar.n());
        y.a(jSONObjectA, "model", nVar.x());
        y.a(jSONObjectA, "device_model", nVar.x());
        y.a(jSONObjectA, "sdk_type", "android_native");
        y.a(jSONObjectA, "sdk_version", nVar.C());
        y.a(jSONObjectA, "network_type", lVarA.d.c());
        y.a(jSONObjectA, "os_version", nVar.y());
        y.a(jSONObjectA, "os_name", "android");
        y.a(jSONObjectA, "platform", "android");
        y.a(jSONObjectA, "arch", nVar.d());
        y.a(jSONObjectA, AccessToken.USER_ID_KEY, y.b(lVarA.d().d, AccessToken.USER_ID_KEY));
        y.a(jSONObjectA, "app_id", lVarA.d().a);
        y.a(jSONObjectA, "app_bundle_name", aw.d());
        y.a(jSONObjectA, "app_bundle_version", aw.b());
        y.a(jSONObjectA, "battery_level", nVar.a(com.adcolony.sdk.a.c()));
        y.a(jSONObjectA, "cell_service_country_code", nVar.j());
        y.a(jSONObjectA, "timezone_ietf", nVar.k());
        y.b(jSONObjectA, "timezone_gmt_m", nVar.l());
        y.b(jSONObjectA, "timezone_dst_m", nVar.m());
        y.a(jSONObjectA, "launch_metadata", nVar.a());
        y.a(jSONObjectA, "controller_version", lVarA.b());
        this.e = nVar.A();
        y.b(jSONObjectA, "current_orientation", this.e);
        JSONArray jSONArrayB = y.b();
        if (aw.a("com.android.vending")) {
            jSONArrayB.put("google");
        }
        if (aw.a("com.amazon.venezia")) {
            jSONArrayB.put("amazon");
        }
        y.a(jSONObjectA, "available_stores", jSONArrayB);
        this.j = aw.b((Context) com.adcolony.sdk.a.c());
        y.a(jSONObjectA, NativeProtocol.RESULT_ARGS_PERMISSIONS, this.j);
        int i = 40;
        while (!nVar.b && i > 0) {
            try {
                Thread.sleep(50L);
                i--;
            } catch (Exception unused) {
            }
        }
        y.a(jSONObjectA, "advertiser_id", nVar.c());
        y.a(jSONObjectA, "limit_tracking", nVar.g());
        if (nVar.c() == null || nVar.c().equals("")) {
            y.a(jSONObjectA, "android_id_sha1", aw.c(nVar.b()));
        }
        return jSONObjectA;
    }
}
