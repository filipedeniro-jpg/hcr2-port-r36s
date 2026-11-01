package com.applovin.impl.sdk;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.ironsource.sdk.constants.Constants;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class i {
    private static final Queue<String> a = new LinkedList();
    private static String f;
    private static String g;
    private static int h;
    private final h b;
    private final n c;
    private final Context d;
    private final Map<Class, Object> e;
    private final AtomicReference<a> i = new AtomicReference<>();

    public static class a {
        public boolean a;
        public String b = "";
    }

    public static class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public long f;
    }

    public static class c {
        public int a = -1;
        public int b = -1;
    }

    public static class d {
        public Boolean A;
        public Boolean B;
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public int h;
        public String i;
        public String j;
        public Locale k;
        public String l;
        public float m;
        public int n;
        public double o;
        public int p;
        public boolean q;
        public c r;
        public int s;
        public String t;
        public boolean u;
        public boolean v;
        public boolean w;
        public String x;
        public long y;
        public e z = new e();
    }

    public static class e {
        public long a = -1;
        public long b = -1;
        public long c = -1;
        public boolean d = false;
    }

    static {
        a.add("act");
        a.add("acm");
        a.add("adr");
        a.add("build");
        a.add("volume");
        a.add("ua");
    }

    protected i(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.b = hVar;
        this.c = hVar.w();
        this.d = hVar.z();
        this.e = Collections.synchronizedMap(new HashMap());
    }

    private d a(d dVar) {
        if (dVar == null) {
            dVar = new d();
        }
        dVar.A = com.applovin.impl.sdk.e.a(this.d);
        dVar.B = com.applovin.impl.sdk.e.b(this.d);
        dVar.r = ((Boolean) this.b.a(com.applovin.impl.sdk.b.b.em)).booleanValue() ? j() : null;
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ev)).booleanValue()) {
            dVar.q = n();
        }
        try {
            AudioManager audioManager = (AudioManager) this.d.getSystemService("audio");
            if (audioManager != null) {
                dVar.s = (int) (audioManager.getStreamVolume(3) * ((Float) this.b.a(com.applovin.impl.sdk.b.b.ew)).floatValue());
            }
        } catch (Throwable th) {
            this.c.b("DataCollector", "Unable to collect volume", th);
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ex)).booleanValue()) {
            if (f == null) {
                String strR = r();
                if (!com.applovin.impl.sdk.utils.k.b(strR)) {
                    strR = "";
                }
                f = strR;
            }
            if (com.applovin.impl.sdk.utils.k.b(f)) {
                dVar.t = f;
            }
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ep)).booleanValue()) {
            try {
                dVar.y = Environment.getDataDirectory().getFreeSpace();
            } catch (Throwable th2) {
                dVar.y = -1L;
                this.c.b("DataCollector", "Unable to collect free space.", th2);
            }
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eq)).booleanValue()) {
            try {
                ActivityManager activityManager = (ActivityManager) this.d.getSystemService("activity");
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                dVar.z.b = memoryInfo.availMem;
                dVar.z.d = memoryInfo.lowMemory;
                dVar.z.c = memoryInfo.threshold;
                dVar.z.a = memoryInfo.totalMem;
            } catch (Throwable th3) {
                this.c.b("DataCollector", "Unable to collect memory info.", th3);
            }
        }
        String str = (String) this.b.y().a(com.applovin.impl.sdk.b.b.ez);
        if (str.equalsIgnoreCase(g)) {
            dVar.p = h;
        } else {
            try {
                g = str;
                PackageInfo packageInfo = this.d.getPackageManager().getPackageInfo(str, 0);
                dVar.p = packageInfo.versionCode;
                h = packageInfo.versionCode;
            } catch (Throwable unused) {
                h = 0;
            }
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.en)).booleanValue()) {
            dVar.w = m();
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eo)).booleanValue()) {
            String strK = k();
            if (!TextUtils.isEmpty(strK)) {
                dVar.x = strK;
            }
        }
        dVar.l = g();
        return dVar;
    }

    private boolean a(String str) {
        return a(str, this.d);
    }

    public static boolean a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        }
        if (context != null) {
            return com.applovin.impl.sdk.utils.i.a(str, context.getPackageName(), context.getPackageManager()) == 0;
        }
        throw new IllegalArgumentException("No context specified");
    }

    private boolean a(String str, com.applovin.impl.sdk.b.b<String> bVar) {
        Iterator<String> it = com.applovin.impl.sdk.utils.d.a((String) this.b.a(bVar)).iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    private String b(int i) {
        String str;
        JSONObject jSONObject = new JSONObject(f());
        PriorityQueue priorityQueue = new PriorityQueue(a);
        while (true) {
            String strEncodeToString = Base64.encodeToString(jSONObject.toString().getBytes(Charset.defaultCharset()), 2);
            if (strEncodeToString.length() <= i) {
                return strEncodeToString;
            }
            do {
                str = (String) priorityQueue.poll();
                if (jSONObject.has(str)) {
                    break;
                }
            } while (!priorityQueue.isEmpty());
            if (TextUtils.isEmpty(str)) {
                this.c.d("DataCollector", "Unable to generate base64 request parameters with max length: " + i);
                return "";
            }
            jSONObject.remove(str);
        }
    }

    private String b(String str) {
        int length = str.length();
        int[] iArr = {11, 12, 10, 3, 2, 1, 15, 10, 15, 14};
        int length2 = iArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = str.charAt(i);
            for (int i2 = length2 - 1; i2 >= 0; i2--) {
                cArr[i] = (char) (cArr[i] ^ iArr[i2]);
            }
        }
        return new String(cArr);
    }

    private String c(int i) {
        if (i == 1) {
            return "receiver";
        }
        if (i == 2) {
            return "speaker";
        }
        if (i == 4 || i == 3) {
            return "headphones";
        }
        if (i == 8) {
            return "bluetootha2dpoutput";
        }
        if (i == 13 || i == 19 || i == 5 || i == 6 || i == 12 || i == 11) {
            return "lineout";
        }
        if (i == 9 || i == 10) {
            return "hdmioutput";
        }
        return null;
    }

    private Map<String, String> f() {
        return a(null, false, true);
    }

    private String g() {
        try {
            int iE = com.applovin.impl.sdk.utils.n.e(this.d);
            if (iE == 1) {
                return "portrait";
            }
            return iE == 2 ? "landscape" : "none";
        } catch (Throwable th) {
            this.b.w().b("DataCollector", "Encountered error while attempting to collect application orientation", th);
            return "none";
        }
    }

    private a h() {
        n nVar;
        String str;
        try {
            ContentResolver contentResolver = this.d.getContentResolver();
            String string = Settings.Secure.getString(contentResolver, "advertising_id");
            a aVar = new a();
            if (string == null) {
                string = "";
            }
            aVar.b = string;
            aVar.a = Settings.Secure.getInt(contentResolver, "limit_ad_tracking") != 0;
            return aVar;
        } catch (Settings.SettingNotFoundException e2) {
            e = e2;
            nVar = this.c;
            str = "Unable to determine if FireOS limited ad tracking is turned on";
            nVar.b("DataCollector", str, e);
            return null;
        } catch (Throwable th) {
            e = th;
            nVar = this.c;
            str = "Unable to collect FireOS IDFA";
            nVar.b("DataCollector", str, e);
            return null;
        }
    }

    private a i() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null && (objInvoke = cls.getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.d)) != null) {
                Class<?> cls2 = objInvoke.getClass();
                Object objInvoke2 = cls2.getMethod(Constants.RequestParameters.isLAT, (Class[]) null).invoke(objInvoke, (Object[]) null);
                String str = (String) cls2.getMethod("getId", (Class[]) null).invoke(objInvoke, (Object[]) null);
                if (str == null) {
                    str = "";
                }
                a aVar = new a();
                aVar.a = ((Boolean) objInvoke2).booleanValue();
                aVar.b = str;
                return aVar;
            }
        } catch (ClassNotFoundException e2) {
            this.c.c("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e2);
        } catch (Throwable th) {
            this.c.b("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", th);
        }
        return new a();
    }

    private c j() {
        try {
            c cVar = new c();
            Intent intentRegisterReceiver = this.d.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("level", -1) : -1;
            int intExtra2 = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("scale", -1) : -1;
            if (intExtra <= 0 || intExtra2 <= 0) {
                cVar.b = -1;
            } else {
                cVar.b = (int) ((intExtra / intExtra2) * 100.0f);
            }
            cVar.a = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("status", -1) : -1;
            return cVar;
        } catch (Throwable th) {
            this.c.b("DataCollector", "Unable to collect battery info", th);
            return null;
        }
    }

    private String k() {
        try {
            AudioManager audioManager = (AudioManager) this.d.getSystemService("audio");
            StringBuilder sb = new StringBuilder();
            if (com.applovin.impl.sdk.utils.e.g()) {
                for (AudioDeviceInfo audioDeviceInfo : audioManager.getDevices(2)) {
                    String strC = c(audioDeviceInfo.getType());
                    if (!TextUtils.isEmpty(strC)) {
                        sb.append(strC);
                        sb.append(",");
                    }
                }
            } else {
                if (audioManager.isWiredHeadsetOn()) {
                    sb.append("headphones");
                    sb.append(",");
                }
                if (audioManager.isBluetoothA2dpOn()) {
                    sb.append("bluetootha2dpoutput");
                }
            }
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            String string = sb.toString();
            if (TextUtils.isEmpty(string)) {
                this.c.a("DataCollector", "No sound outputs detected");
            }
            return string;
        } catch (Throwable th) {
            this.c.b("DataCollector", "Unable to collect sound outputs", th);
            return null;
        }
    }

    private double l() {
        double offset = TimeZone.getDefault().getOffset(new Date().getTime());
        Double.isNaN(offset);
        double dRound = Math.round((offset * 10.0d) / 3600000.0d);
        Double.isNaN(dRound);
        return dRound / 10.0d;
    }

    private boolean m() {
        try {
            if (this.b.g()) {
                return this.d.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
            }
            PackageManager packageManager = this.d.getPackageManager();
            if (com.applovin.impl.sdk.utils.e.f()) {
                return packageManager.hasSystemFeature("android.software.leanback");
            }
            return com.applovin.impl.sdk.utils.e.c() ? packageManager.hasSystemFeature("android.hardware.type.television") : !packageManager.hasSystemFeature("android.hardware.touchscreen");
        } catch (Throwable th) {
            this.c.b("DataCollector", "Failed to determine if device is TV.", th);
            return false;
        }
    }

    private boolean n() {
        try {
            return o() || p();
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean o() {
        String str = Build.TAGS;
        return str != null && str.contains(b("lz}$blpz"));
    }

    private boolean p() {
        for (String str : new String[]{"&zpz}ld&hyy&Z|yl{|zl{'hyb", "&zk`g&z|", "&zpz}ld&k`g&z|", "&zpz}ld&qk`g&z|", "&mh}h&efjhe&qk`g&z|", "&mh}h&efjhe&k`g&z|", "&zpz}ld&zm&qk`g&z|", "&zpz}ld&k`g&oh`ezhol&z|", "&mh}h&efjhe&z|"}) {
            if (new File(b(str)).exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean q() {
        return a(Build.DEVICE, com.applovin.impl.sdk.b.b.es) || a(Build.HARDWARE, com.applovin.impl.sdk.b.b.er) || a(Build.MANUFACTURER, com.applovin.impl.sdk.b.b.et) || a(Build.MODEL, com.applovin.impl.sdk.b.b.eu);
    }

    private String r() {
        final AtomicReference atomicReference = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Handler(this.d.getMainLooper()).post(new Runnable() { // from class: com.applovin.impl.sdk.i.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        atomicReference.set(new WebView(i.this.d).getSettings().getUserAgentString());
                    } catch (Throwable th) {
                        i.this.c.b("DataCollector", "Unable to collect user agent string", th);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await(((Long) this.b.a(com.applovin.impl.sdk.b.b.ey)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable unused) {
        }
        return (String) atomicReference.get();
    }

    com.applovin.impl.sdk.ad.f a(int i) {
        String strB = b(i);
        boolean z = this.i.get() != null;
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eW)).booleanValue()) {
            return new com.applovin.impl.sdk.ad.f(com.applovin.impl.sdk.utils.j.a(strB, this.b.u(), com.applovin.impl.sdk.utils.n.a(this.b)), z);
        }
        return new com.applovin.impl.sdk.ad.f(strB, z);
    }

    public d a() {
        d dVar;
        TelephonyManager telephonyManager;
        Object obj = this.e.get(d.class);
        if (obj != null) {
            dVar = (d) obj;
        } else {
            dVar = new d();
            dVar.k = Locale.getDefault();
            dVar.a = Build.MODEL;
            dVar.b = Build.VERSION.RELEASE;
            dVar.c = b();
            dVar.d = Build.MANUFACTURER;
            dVar.e = Build.BRAND;
            dVar.f = Build.HARDWARE;
            dVar.h = Build.VERSION.SDK_INT;
            dVar.g = Build.DEVICE;
            dVar.o = l();
            dVar.u = q();
            try {
                dVar.v = ((SensorManager) this.d.getSystemService("sensor")).getDefaultSensor(4) != null;
            } catch (Throwable th) {
                this.c.b("DataCollector", "Unable to retrieve gyroscope availability", th);
            }
            if (a("android.permission.READ_PHONE_STATE") && (telephonyManager = (TelephonyManager) this.d.getSystemService("phone")) != null) {
                dVar.i = telephonyManager.getSimCountryIso().toUpperCase(Locale.ENGLISH);
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                try {
                    dVar.j = URLEncoder.encode(networkOperatorName, "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    dVar.j = networkOperatorName;
                }
            }
            try {
                DisplayMetrics displayMetrics = this.d.getResources().getDisplayMetrics();
                dVar.m = displayMetrics.density;
                dVar.n = displayMetrics.densityDpi;
            } catch (Throwable unused2) {
            }
            this.e.put(d.class, dVar);
        }
        return a(dVar);
    }

    public Map<String, String> a(Map<String, String> map, boolean z, boolean z2) {
        a aVarD;
        HashMap map2 = new HashMap();
        d dVarA = a();
        map2.put("brand", com.applovin.impl.sdk.utils.k.e(dVarA.d));
        map2.put("brand_name", com.applovin.impl.sdk.utils.k.e(dVarA.e));
        map2.put("hardware", com.applovin.impl.sdk.utils.k.e(dVarA.f));
        map2.put("api_level", String.valueOf(dVarA.h));
        map2.put("carrier", com.applovin.impl.sdk.utils.k.e(dVarA.j));
        map2.put("country_code", com.applovin.impl.sdk.utils.k.e(dVarA.i));
        map2.put("locale", com.applovin.impl.sdk.utils.k.e(dVarA.k.toString()));
        map2.put("model", com.applovin.impl.sdk.utils.k.e(dVarA.a));
        map2.put("os", com.applovin.impl.sdk.utils.k.e(dVarA.b));
        map2.put("platform", com.applovin.impl.sdk.utils.k.e(dVarA.c));
        map2.put("revision", com.applovin.impl.sdk.utils.k.e(dVarA.g));
        map2.put("orientation_lock", dVarA.l);
        map2.put("tz_offset", String.valueOf(dVarA.o));
        map2.put("wvvc", String.valueOf(dVarA.p));
        map2.put("adns", String.valueOf(dVarA.m));
        map2.put("adnsd", String.valueOf(dVarA.n));
        map2.put("sim", com.applovin.impl.sdk.utils.k.a(dVarA.u));
        map2.put("gy", com.applovin.impl.sdk.utils.k.a(dVarA.v));
        map2.put("tv", com.applovin.impl.sdk.utils.k.a(dVarA.w));
        map2.put("fs", String.valueOf(dVarA.y));
        map2.put("fm", String.valueOf(dVarA.z.b));
        map2.put("tm", String.valueOf(dVarA.z.a));
        map2.put("lmt", String.valueOf(dVarA.z.c));
        map2.put("lm", String.valueOf(dVarA.z.d));
        map2.put("adr", com.applovin.impl.sdk.utils.k.a(dVarA.q));
        map2.put("volume", String.valueOf(dVarA.s));
        com.applovin.impl.sdk.utils.n.a("ua", com.applovin.impl.sdk.utils.k.e(dVarA.t), map2);
        com.applovin.impl.sdk.utils.n.a("so", com.applovin.impl.sdk.utils.k.e(dVarA.x), map2);
        c cVar = dVarA.r;
        if (cVar != null) {
            map2.put("act", String.valueOf(cVar.a));
            map2.put("acm", String.valueOf(cVar.b));
        }
        Boolean bool = dVarA.A;
        if (bool != null) {
            map2.put("huc", bool.toString());
        }
        Boolean bool2 = dVarA.B;
        if (bool2 != null) {
            map2.put("aru", bool2.toString());
        }
        Point pointA = com.applovin.impl.sdk.utils.e.a(this.d);
        map2.put("dx", Integer.toString(pointA.x));
        map2.put("dy", Integer.toString(pointA.y));
        map2.put("accept", "custom_size,launch_app,video");
        map2.put("api_did", this.b.a(com.applovin.impl.sdk.b.b.U));
        map2.put("sdk_version", AppLovinSdk.VERSION);
        map2.put("build", Integer.toString(120));
        map2.put("format", "json");
        b bVarC = c();
        map2.put("app_version", com.applovin.impl.sdk.utils.k.e(bVarC.b));
        map2.put("ia", Long.toString(bVarC.f));
        map2.put("tg", String.valueOf(bVarC.e));
        map2.put("installer_name", bVarC.d);
        com.applovin.impl.sdk.utils.n.a("mediation_provider", com.applovin.impl.sdk.utils.k.e(this.b.o()), map2);
        map2.put("network", com.applovin.impl.sdk.utils.f.f(this.b));
        com.applovin.impl.sdk.utils.n.a("plugin_version", (String) this.b.a(com.applovin.impl.sdk.b.b.eh), map2);
        map2.put("preloading", String.valueOf(z));
        com.applovin.impl.sdk.utils.n.a("test_ads", Boolean.valueOf(this.b.m().isTestAdsEnabled()), map2);
        map2.put("first_install", String.valueOf(this.b.D()));
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()) {
            map2.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.u());
        }
        map2.put("sc", this.b.a(com.applovin.impl.sdk.b.b.X));
        map2.put("sc2", this.b.a(com.applovin.impl.sdk.b.b.Y));
        map2.put("server_installed_at", com.applovin.impl.sdk.utils.k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Z)));
        com.applovin.impl.sdk.utils.n.a("persisted_data", com.applovin.impl.sdk.utils.k.e((String) this.b.a(com.applovin.impl.sdk.b.d.t)), map2);
        map2.put("v1", Boolean.toString(com.applovin.impl.sdk.utils.e.a("android.permission.WRITE_EXTERNAL_STORAGE", this.d)));
        map2.put("v2", Boolean.toString(com.applovin.impl.sdk.utils.e.a((Class<?>) AppLovinInterstitialActivity.class, this.d)));
        map2.put("v3", Boolean.toString(com.applovin.impl.sdk.utils.e.b(this.d)));
        map2.put("v4", Boolean.toString(com.applovin.impl.sdk.utils.e.c(this.d)));
        map2.put("v5", Boolean.toString(com.applovin.impl.sdk.utils.e.a((Class<?>) AppLovinWebViewActivity.class, this.d)));
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eA)).booleanValue()) {
            com.applovin.impl.sdk.c.h hVarG = this.b.G();
            map2.put("li", String.valueOf(hVarG.b(com.applovin.impl.sdk.c.g.b)));
            map2.put("si", String.valueOf(hVarG.b(com.applovin.impl.sdk.c.g.d)));
            map2.put("pf", String.valueOf(hVarG.b(com.applovin.impl.sdk.c.g.h)));
            map2.put("mpf", String.valueOf(hVarG.b(com.applovin.impl.sdk.c.g.r)));
            map2.put("gpf", String.valueOf(hVarG.b(com.applovin.impl.sdk.c.g.i)));
        }
        map2.put("vz", com.applovin.impl.sdk.utils.k.a(this.d.getPackageName(), this.b));
        if (this.b.j()) {
            map2.put("pnr", Boolean.toString(this.b.k()));
        }
        if (z2) {
            aVarD = this.i.get();
            if (aVarD == null) {
                aVarD = new a();
                map2.put("inc", Boolean.toString(true));
            }
            e();
        } else {
            aVarD = this.b.J().d();
        }
        String str = aVarD.b;
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            map2.put("idfa", str);
        }
        map2.put("dnt", Boolean.toString(aVarD.a));
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ee)).booleanValue()) {
            com.applovin.impl.sdk.utils.n.a("cuid", this.b.l(), map2);
        }
        if (map != null) {
            map2.putAll(map);
        }
        map2.put("rid", UUID.randomUUID().toString());
        return map2;
    }

    public String b() {
        return this.b.g() ? "fireos" : "android";
    }

    public b c() {
        PackageInfo packageInfo;
        Object obj = this.e.get(b.class);
        if (obj != null) {
            return (b) obj;
        }
        ApplicationInfo applicationInfo = this.d.getApplicationInfo();
        long jLastModified = new File(applicationInfo.sourceDir).lastModified();
        PackageManager packageManager = this.d.getPackageManager();
        String installerPackageName = null;
        try {
            packageInfo = packageManager.getPackageInfo(this.d.getPackageName(), 0);
            try {
                installerPackageName = packageManager.getInstallerPackageName(applicationInfo.packageName);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            packageInfo = null;
        }
        b bVar = new b();
        bVar.c = applicationInfo.packageName;
        if (installerPackageName == null) {
            installerPackageName = "";
        }
        bVar.d = installerPackageName;
        bVar.f = jLastModified;
        bVar.a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        bVar.b = packageInfo != null ? packageInfo.versionName : "";
        bVar.e = (String) this.b.a(com.applovin.impl.sdk.b.d.c);
        this.e.put(b.class, bVar);
        return bVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0025  */
    public a d() {
        a aVarI;
        if (this.b.g()) {
            aVarI = h();
            if (aVarI == null) {
                if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ej)).booleanValue()) {
                    aVarI = i();
                } else {
                    aVarI = new a();
                }
            }
        } else {
            aVarI = i();
        }
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ed)).booleanValue()) {
            return new a();
        }
        if (!aVarI.a || ((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ec)).booleanValue()) {
            return aVarI;
        }
        aVarI.b = "";
        return aVarI;
    }

    public void e() {
        this.b.F().a(new com.applovin.impl.sdk.d.i(this.b, new com.applovin.impl.sdk.d.i.a() { // from class: com.applovin.impl.sdk.i.2
            @Override // com.applovin.impl.sdk.d.i.a
            public void a(a aVar) {
                i.this.i.set(aVar);
            }
        }), com.applovin.impl.sdk.d.q.a.BACKGROUND);
    }
}
