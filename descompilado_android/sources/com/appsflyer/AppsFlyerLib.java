package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import com.applovin.sdk.AppLovinEventTypes;
import com.appsflyer.cache.CacheManager;
import com.appsflyer.cache.RequestCacheData;
import com.appsflyer.share.Constants;
import com.facebook.appevents.UserDataStore;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppsFlyerLib implements com.appsflyer.a {
    public static final String AF_PRE_INSTALL_PATH = "AF_PRE_INSTALL_PATH";
    public static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    public static final String ATTRIBUTION_ID_CONTENT_URI = "content://com.facebook.katana.provider.AttributionIdProvider";
    public static final String IS_STOP_TRACKING_USED = "is_stop_tracking_used";
    public static final String LOG_TAG = "AppsFlyer_4.8.20";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT = "/data/local/tmp/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT_ETC = "/etc/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_RO_PROP = "ro.appsflyer.preinstall.path";

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static String f16;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private static String f17;

    /* JADX INFO: renamed from: ʼॱ, reason: contains not printable characters */
    private static AppsFlyerLib f18;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private static final List<String> f19;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static AppsFlyerInAppPurchaseValidatorListener f20;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private static final List<String> f21;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static final String f22;

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private static AppsFlyerConversionListener f23;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static final String f24 = BuildConfig.VERSION_NAME.substring(0, BuildConfig.VERSION_NAME.indexOf("."));

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private static String f25;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private static final String f26;

    /* JADX INFO: renamed from: ᐝॱ, reason: contains not printable characters */
    private static AppsFlyerTrackingRequestListener f27;

    /* JADX INFO: renamed from: ʻॱ, reason: contains not printable characters */
    private long f28;

    /* JADX INFO: renamed from: ʾ, reason: contains not printable characters */
    private long f31;

    /* JADX INFO: renamed from: ʿ, reason: contains not printable characters */
    private n.a f32;

    /* JADX INFO: renamed from: ˈ, reason: contains not printable characters */
    private long f33;

    /* JADX INFO: renamed from: ˉ, reason: contains not printable characters */
    private String f34;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    String f35;

    /* JADX INFO: renamed from: ˊˊ, reason: contains not printable characters */
    private Map<Long, String> f36;

    /* JADX INFO: renamed from: ˊᐝ, reason: contains not printable characters */
    private long f38;

    /* JADX INFO: renamed from: ˋᐝ, reason: contains not printable characters */
    private boolean f41;

    /* JADX INFO: renamed from: ˌ, reason: contains not printable characters */
    private boolean f42;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    String f45;

    /* JADX INFO: renamed from: ॱᐝ, reason: contains not printable characters */
    private Map<String, String> f50;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private long f29 = -1;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private long f46 = -1;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private long f47 = TimeUnit.SECONDS.toMillis(5);

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    private com.appsflyer.d f49 = null;

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private boolean f48 = false;

    /* JADX INFO: renamed from: ʽॱ, reason: contains not printable characters */
    private ScheduledExecutorService f30 = null;
    protected Uri latestDeepLink = null;

    /* JADX INFO: renamed from: ˊˋ, reason: contains not printable characters */
    private boolean f37 = false;

    /* JADX INFO: renamed from: ˋˊ, reason: contains not printable characters */
    private boolean f39 = false;

    /* JADX INFO: renamed from: ˋˋ, reason: contains not printable characters */
    private p f40 = new p();

    /* JADX INFO: renamed from: ˎˎ, reason: contains not printable characters */
    private boolean f44 = false;

    /* JADX INFO: renamed from: ˍ, reason: contains not printable characters */
    private boolean f43 = false;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static /* synthetic */ void m51(Map map) {
        AppsFlyerConversionListener appsFlyerConversionListener = f23;
        if (appsFlyerConversionListener != null) {
            try {
                appsFlyerConversionListener.onAppOpenAttribution(map);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static /* synthetic */ String m60(String str) {
        return AppsFlyerProperties.getInstance().getString(str);
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static /* synthetic */ void m28(AppsFlyerLib appsFlyerLib, Context context, String str, String str2, String str3, String str4, boolean z, boolean z2, Intent intent, String str5) {
        String str6;
        if (context == null) {
            AFLogger.afDebugLog("sendTrackingWithEvent - got null context. skipping event/launch.");
            return;
        }
        boolean z3 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("appsflyer-data", 0);
        AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
        if (!appsFlyerLib.isTrackingStopped()) {
            StringBuilder sb = new StringBuilder("sendTrackingWithEvent from activity: ");
            sb.append(context.getClass().getName());
            AFLogger.afInfoLog(sb.toString());
        }
        boolean z4 = str2 == null;
        Map<String, Object> mapM78 = appsFlyerLib.m78(context, str, str2, str3, str4, z, sharedPreferences, z4, intent, str5);
        String str7 = (String) mapM78.get("appsflyerKey");
        if (str7 == null || str7.length() == 0) {
            AFLogger.afDebugLog("Not sending data yet, waiting for dev key");
            return;
        }
        if (!appsFlyerLib.isTrackingStopped()) {
            AFLogger.afInfoLog("AppsFlyerLib.sendTrackingWithEvent");
        }
        if (z4) {
            str6 = z2 ? f25 : f16;
        } else {
            str6 = f17;
        }
        String url = ServerConfigHandler.getUrl(str6);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(url);
        sb2.append(context.getPackageName());
        String string = sb2.toString();
        int iM24 = m24(sharedPreferences, "appsFlyerCount", false);
        if (!(AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) && mapM78.get(ServerParameters.ADVERTISING_ID_PARAM) != null) {
            try {
                if (TextUtils.isEmpty(appsFlyerLib.f45) && mapM78.remove(com.my.target.i.ANDROID_ID) != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: android_id");
                }
                if (TextUtils.isEmpty(appsFlyerLib.f35) && mapM78.remove("imei") != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: imei");
                }
            } catch (Exception e2) {
                AFLogger.afErrorLog("failed to remove IMEI or AndroidID key from params; ", e2);
            }
        }
        a aVar = new a(appsFlyerLib, string, mapM78, context.getApplicationContext(), z4, iM24, (byte) 0);
        if (z4 && m53(context)) {
            Map<String, String> map = appsFlyerLib.f50;
            if (map != null && map.size() > 0) {
                z3 = true;
            }
            if (!z3) {
                AFLogger.afDebugLog("Failed to get new referrer, wait ...");
                m39(AFExecutor.getInstance().m6(), aVar, 500L, TimeUnit.MILLISECONDS);
                return;
            }
        }
        aVar.run();
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static /* synthetic */ ScheduledExecutorService m33(AppsFlyerLib appsFlyerLib) {
        appsFlyerLib.f30 = null;
        return null;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static /* synthetic */ void m37(AppsFlyerLib appsFlyerLib, String str, String str2, String str3, WeakReference weakReference, String str4, boolean z) throws Throwable {
        URL url = new URL(str);
        StringBuilder sb = new StringBuilder("url: ");
        sb.append(url.toString());
        AFLogger.afInfoLog(sb.toString());
        f.AnonymousClass4.m127("data: ".concat(String.valueOf(str2)));
        m66((Context) weakReference.get(), LOG_TAG, "EVENT_DATA", str2);
        try {
            appsFlyerLib.m38(url, str2, str3, weakReference, str4, z);
        } catch (IOException e2) {
            AFLogger.afErrorLog("Exception in sendRequestToServer. ", e2);
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.USE_HTTP_FALLBACK, false)) {
                appsFlyerLib.m38(new URL(str.replace("https:", "http:")), str2, str3, weakReference, str4, z);
                return;
            }
            StringBuilder sb2 = new StringBuilder("failed to send requeset to server. ");
            sb2.append(e2.getLocalizedMessage());
            AFLogger.afInfoLog(sb2.toString());
            m66((Context) weakReference.get(), LOG_TAG, "ERROR", e2.getLocalizedMessage());
            throw e2;
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static /* synthetic */ boolean m54(AppsFlyerLib appsFlyerLib) {
        Map<String, String> map = appsFlyerLib.f50;
        return map != null && map.size() > 0;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(f24);
        sb.append("/androidevent?buildnumber=4.8.20&app_id=");
        f26 = sb.toString();
        StringBuilder sb2 = new StringBuilder("https://%sattr.%s/api/v");
        sb2.append(f26);
        f25 = sb2.toString();
        StringBuilder sb3 = new StringBuilder("https://%st.%s/api/v");
        sb3.append(f26);
        f16 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("https://%sevents.%s/api/v");
        sb4.append(f26);
        f17 = sb4.toString();
        StringBuilder sb5 = new StringBuilder("https://%sregister.%s/api/v");
        sb5.append(f26);
        f22 = sb5.toString();
        f21 = Arrays.asList("is_cache");
        f19 = Arrays.asList("googleplay", "playstore", "googleplaystore");
        f23 = null;
        f20 = null;
        f27 = null;
        f18 = new AppsFlyerLib();
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m75() {
        this.f31 = System.currentTimeMillis();
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m80() {
        this.f33 = System.currentTimeMillis();
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m77(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(AppsFlyerProperties.IS_MONITOR);
        if (stringExtra != null) {
            AFLogger.afInfoLog("Turning on monitoring.");
            AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_MONITOR, stringExtra.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
            m66(context, null, "START_TRACKING", context.getPackageName());
            return;
        }
        AFLogger.afInfoLog("****** onReceive called *******");
        AppsFlyerProperties.getInstance().setOnReceiveCalled();
        String stringExtra2 = intent.getStringExtra("referrer");
        AFLogger.afInfoLog("Play store referrer: ".concat(String.valueOf(stringExtra2)));
        if (stringExtra2 != null) {
            if ("AppsFlyer_Test".equals(intent.getStringExtra("TestIntegrationMode"))) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
                editorEdit.clear();
                if (Build.VERSION.SDK_INT >= 9) {
                    editorEdit.apply();
                } else {
                    editorEdit.commit();
                }
                AppsFlyerProperties.getInstance().setFirstLaunchCalled(false);
                AFLogger.afInfoLog("Test mode started..");
                this.f38 = System.currentTimeMillis();
            }
            SharedPreferences.Editor editorEdit2 = context.getSharedPreferences("appsflyer-data", 0).edit();
            editorEdit2.putString("referrer", stringExtra2);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit2.apply();
            } else {
                editorEdit2.commit();
            }
            AppsFlyerProperties.getInstance().setReferrer(stringExtra2);
            if (AppsFlyerProperties.getInstance().isFirstLaunchCalled()) {
                AFLogger.afInfoLog("onReceive: isLaunchCalled");
                if (stringExtra2 == null || stringExtra2.length() <= 5) {
                    return;
                }
                m39(AFExecutor.getInstance().m6(), new c(this, new WeakReference(context.getApplicationContext()), null, null, null, stringExtra2, true, intent, null, (byte) 0), 5L, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static void m52(JSONObject jSONObject) {
        String str;
        ArrayList arrayList = new ArrayList();
        Iterator<String> itKeys = jSONObject.keys();
        while (true) {
            if (!itKeys.hasNext()) {
                break;
            }
            try {
                JSONArray jSONArray = new JSONArray((String) jSONObject.get(itKeys.next()));
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(Long.valueOf(jSONArray.getLong(i)));
                }
            } catch (JSONException unused) {
            }
        }
        Collections.sort(arrayList);
        Iterator<String> itKeys2 = jSONObject.keys();
        loop2: while (true) {
            str = null;
            while (itKeys2.hasNext() && str == null) {
                String next = itKeys2.next();
                try {
                    JSONArray jSONArray2 = new JSONArray((String) jSONObject.get(next));
                    String str2 = str;
                    int i2 = 0;
                    while (i2 < jSONArray2.length()) {
                        try {
                            if (jSONArray2.getLong(i2) != ((Long) arrayList.get(0)).longValue() && jSONArray2.getLong(i2) != ((Long) arrayList.get(1)).longValue() && jSONArray2.getLong(i2) != ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                i2++;
                                str2 = next;
                            }
                        } catch (JSONException unused2) {
                        }
                    }
                    str = str2;
                } catch (JSONException unused3) {
                }
            }
            break loop2;
        }
        if (str != null) {
            jSONObject.remove(str);
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static void m34(Context context, String str) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        AFLogger.afDebugLog("received a new (extra) referrer: ".concat(String.valueOf(str)));
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String string = context.getSharedPreferences("appsflyer-data", 0).getString("extraReferrers", null);
            if (string == null) {
                jSONObject = new JSONObject();
                jSONArray = new JSONArray();
            } else {
                jSONObject = new JSONObject(string);
                if (jSONObject.has(str)) {
                    jSONArray = new JSONArray((String) jSONObject.get(str));
                } else {
                    jSONArray = new JSONArray();
                }
            }
            if (jSONArray.length() < 5) {
                jSONArray.put(jCurrentTimeMillis);
            }
            if (jSONObject.length() >= 4) {
                m52(jSONObject);
            }
            jSONObject.put(str, jSONArray.toString());
            String string2 = jSONObject.toString();
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
            editorEdit.putString("extraReferrers", string2);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        } catch (JSONException unused) {
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Couldn't save referrer - ");
            sb.append(str);
            sb.append(": ");
            AFLogger.afErrorLog(sb.toString(), th);
        }
    }

    private AppsFlyerLib() {
        AFVersionDeclaration.init();
    }

    public static AppsFlyerLib getInstance() {
        return f18;
    }

    public void stopTracking(boolean z, Context context) {
        this.f43 = z;
        CacheManager.getInstance().clearCache(context);
        if (this.f43) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
            editorEdit.putBoolean(IS_STOP_TRACKING_USED, true);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        }
    }

    public String getSdkVersion() {
        s.m187().m196("getSdkVersion", new String[0]);
        return "version: 4.8.20 (build 170)";
    }

    public void onPause(Context context) {
        g.b.m129(context);
        f fVarM120 = f.m120(context);
        fVarM120.f131.post(fVarM120.f136);
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private void m46(Application application) {
        AppsFlyerProperties.getInstance().loadProperties(application.getApplicationContext());
        if (Build.VERSION.SDK_INT >= 14) {
            if (Build.VERSION.SDK_INT < 14 || this.f32 != null) {
                return;
            }
            n.m160();
            this.f32 = new n.a() { // from class: com.appsflyer.AppsFlyerLib.2
                @Override // com.appsflyer.n.a
                /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
                public final void mo83(Activity activity) {
                    if (2 > AppsFlyerLib.m55(AppsFlyerLib.m56(activity))) {
                        f fVarM120 = f.m120(activity);
                        fVarM120.f131.post(fVarM120.f136);
                        fVarM120.f131.post(fVarM120.f128);
                    }
                    AFLogger.afInfoLog("onBecameForeground");
                    AppsFlyerLib.getInstance().m75();
                    AppsFlyerLib.getInstance().m79(activity, (String) null, (Map<String, Object>) null);
                    AFLogger.resetDeltaTime();
                }

                @Override // com.appsflyer.n.a
                /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
                public final void mo82(WeakReference<Context> weakReference) {
                    g.b.m129(weakReference.get());
                    f fVarM120 = f.m120(weakReference.get());
                    fVarM120.f131.post(fVarM120.f136);
                }
            };
            n.m162().m166(application, this.f32);
            return;
        }
        AFLogger.afInfoLog("SDK<14 call trackEvent manually");
        AFLogger.afInfoLog("onBecameForeground");
        getInstance().f31 = System.currentTimeMillis();
        getInstance().m79(application, (String) null, (Map<String, Object>) null);
        AFLogger.resetDeltaTime();
    }

    @Deprecated
    public void setGCMProjectID(String str) {
        s.m187().m196("setGCMProjectID", str);
        AFLogger.afWarnLog("Method 'setGCMProjectNumber' is deprecated. Please follow the documentation.");
        enableUninstallTracking(str);
    }

    @Deprecated
    public void setGCMProjectNumber(String str) {
        s.m187().m196("setGCMProjectNumber", str);
        AFLogger.afWarnLog("Method 'setGCMProjectNumber' is deprecated. Please follow the documentation.");
        enableUninstallTracking(str);
    }

    @Deprecated
    public void setGCMProjectNumber(Context context, String str) {
        s.m187().m196("setGCMProjectNumber", str);
        AFLogger.afWarnLog("Method 'setGCMProjectNumber' is deprecated. Please use 'enableUninstallTracking'.");
        enableUninstallTracking(str);
    }

    public void enableUninstallTracking(String str) {
        s.m187().m196("enableUninstallTracking", str);
        AppsFlyerProperties.getInstance().set("gcmProjectNumber", str);
    }

    public void updateServerUninstallToken(Context context, String str) {
        if (str != null) {
            u.m217(context, new com.appsflyer.c.a.C0031c(str));
        }
    }

    public void setDebugLog(boolean z) {
        s.m187().m196("setDebugLog", String.valueOf(z));
        AppsFlyerProperties.getInstance().set("shouldLog", z);
        AppsFlyerProperties.getInstance().set("logLevel", (z ? AFLogger.LogLevel.DEBUG : AFLogger.LogLevel.NONE).getLevel());
    }

    public void setImeiData(String str) {
        s.m187().m196("setImeiData", str);
        this.f35 = str;
    }

    public void setAndroidIdData(String str) {
        s.m187().m196("setAndroidIdData", str);
        this.f45 = str;
    }

    public AppsFlyerLib enableLocationCollection(boolean z) {
        this.f37 = z;
        return this;
    }

    @Deprecated
    public void setAppUserId(String str) {
        s.m187().m196("setAppUserId", str);
        setCustomerUserId(str);
    }

    public void setCustomerUserId(String str) {
        s.m187().m196("setCustomerUserId", str);
        AFLogger.afInfoLog("setCustomerUserId = ".concat(String.valueOf(str)));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.APP_USER_ID, str);
    }

    public void waitForCustomerUserId(boolean z) {
        AFLogger.afInfoLog("initAfterCustomerUserID: ".concat(String.valueOf(z)), true);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, z);
    }

    public void setCustomerIdAndTrack(String str, @NonNull Context context) {
        if (context != null) {
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null) {
                setCustomerUserId(str);
                AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false);
                StringBuilder sb = new StringBuilder("CustomerUserId set: ");
                sb.append(str);
                sb.append(" - Initializing AppsFlyer Tacking");
                AFLogger.afInfoLog(sb.toString(), true);
                String referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                if (referrer == null) {
                    referrer = "";
                }
                m36(context, string, (String) null, (String) null, referrer, context instanceof Activity ? ((Activity) context).getIntent() : null, (String) null);
                if (AppsFlyerProperties.getInstance().getString("afUninstallToken") != null) {
                    m81(context, AppsFlyerProperties.getInstance().getString("afUninstallToken"));
                    return;
                }
                return;
            }
            setCustomerUserId(str);
            AFLogger.afInfoLog("waitForCustomerUserId is false; setting CustomerUserID: ".concat(String.valueOf(str)), true);
        }
    }

    public String getOutOfStore(Context context) {
        String string = AppsFlyerProperties.getInstance().getString("api_store_value");
        if (string != null) {
            return string;
        }
        String strM68 = m68((WeakReference<Context>) new WeakReference(context), "AF_STORE");
        if (strM68 != null) {
            return strM68;
        }
        AFLogger.afInfoLog("No out-of-store value set");
        return null;
    }

    public void setOutOfStore(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            AppsFlyerProperties.getInstance().set("api_store_value", lowerCase);
            AFLogger.afInfoLog("Store API set with value: ".concat(String.valueOf(lowerCase)), true);
            return;
        }
        AFLogger.m18("Cannot set setOutOfStore with null");
    }

    public void setAppInviteOneLink(String str) {
        s.m187().m196("setAppInviteOneLink", str);
        AFLogger.afInfoLog("setAppInviteOneLink = ".concat(String.valueOf(str)));
        if (str == null || !str.equals(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID))) {
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_DOMAIN);
            AppsFlyerProperties.getInstance().remove("onelinkVersion");
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_SCHEME);
        }
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_ID, str);
    }

    public void setAdditionalData(HashMap<String, Object> map) {
        if (map != null) {
            s.m187().m196("setAdditionalData", map.toString());
            AppsFlyerProperties.getInstance().setCustomData(new JSONObject(map).toString());
        }
    }

    public void sendDeepLinkData(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            s sVarM187 = s.m187();
            StringBuilder sb = new StringBuilder("activity_intent_");
            sb.append(activity.getIntent().toString());
            sVarM187.m196("sendDeepLinkData", activity.getLocalClassName(), sb.toString());
        } else if (activity != null) {
            s.m187().m196("sendDeepLinkData", activity.getLocalClassName(), "activity_intent_null");
        } else {
            s.m187().m196("sendDeepLinkData", "activity_null");
        }
        try {
            m46(activity.getApplication());
            StringBuilder sb2 = new StringBuilder("getDeepLinkData with activity ");
            sb2.append(activity.getIntent().getDataString());
            AFLogger.afInfoLog(sb2.toString());
        } catch (Exception e2) {
            AFLogger.afInfoLog("getDeepLinkData Exception: ".concat(String.valueOf(e2)));
        }
    }

    public void sendPushNotificationData(Activity activity) {
        String string;
        long jLongValue;
        Activity activity2;
        Intent intent;
        Bundle extras;
        if (activity != null && activity.getIntent() != null) {
            s sVarM187 = s.m187();
            StringBuilder sb = new StringBuilder("activity_intent_");
            sb.append(activity.getIntent().toString());
            sVarM187.m196("sendPushNotificationData", activity.getLocalClassName(), sb.toString());
        } else if (activity != null) {
            s.m187().m196("sendPushNotificationData", activity.getLocalClassName(), "activity_intent_null");
        } else {
            s.m187().m196("sendPushNotificationData", "activity_null");
        }
        if (!(activity instanceof Activity) || (intent = (activity2 = activity).getIntent()) == null || (extras = intent.getExtras()) == null) {
            string = null;
        } else {
            string = extras.getString("af");
            if (string != null) {
                AFLogger.afInfoLog("Push Notification received af payload = ".concat(String.valueOf(string)));
                extras.remove("af");
                activity2.setIntent(intent.putExtras(extras));
            }
        }
        this.f34 = string;
        if (this.f34 != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.f36 == null) {
                AFLogger.afInfoLog("pushes: initializing pushes history..");
                this.f36 = new ConcurrentHashMap();
                jLongValue = jCurrentTimeMillis;
            } else {
                try {
                    long j = AppsFlyerProperties.getInstance().getLong("pushPayloadMaxAging", 1800000L);
                    jLongValue = jCurrentTimeMillis;
                    for (Long l : this.f36.keySet()) {
                        try {
                            JSONObject jSONObject = new JSONObject(this.f34);
                            JSONObject jSONObject2 = new JSONObject(this.f36.get(l));
                            if (jSONObject.get(Constants.URL_MEDIA_SOURCE).equals(jSONObject2.get(Constants.URL_MEDIA_SOURCE))) {
                                StringBuilder sb2 = new StringBuilder("PushNotificationMeasurement: A previous payload with same PID was already acknowledged! (old: ");
                                sb2.append(jSONObject2);
                                sb2.append(", new: ");
                                sb2.append(jSONObject);
                                sb2.append(")");
                                AFLogger.afInfoLog(sb2.toString());
                                this.f34 = null;
                                return;
                            }
                            if (jCurrentTimeMillis - l.longValue() > j) {
                                this.f36.remove(l);
                            }
                            if (l.longValue() <= jLongValue) {
                                jLongValue = l.longValue();
                            }
                        } catch (Throwable th) {
                            th = th;
                            StringBuilder sb3 = new StringBuilder("Error while handling push notification measurement: ");
                            sb3.append(th.getClass().getSimpleName());
                            AFLogger.afErrorLog(sb3.toString(), th);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    jLongValue = jCurrentTimeMillis;
                }
            }
            if (this.f36.size() == AppsFlyerProperties.getInstance().getInt("pushPayloadHistorySize", 2)) {
                StringBuilder sb4 = new StringBuilder("pushes: removing oldest overflowing push (oldest push:");
                sb4.append(jLongValue);
                sb4.append(")");
                AFLogger.afInfoLog(sb4.toString());
                this.f36.remove(Long.valueOf(jLongValue));
            }
            this.f36.put(Long.valueOf(jCurrentTimeMillis), this.f34);
            m46(activity.getApplication());
        }
    }

    @Deprecated
    public void setUserEmail(String str) {
        s.m187().m196("setUserEmail", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.USER_EMAIL, str);
    }

    public void setUserEmails(String... strArr) {
        s.m187().m196("setUserEmails", strArr);
        setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
    }

    public void setUserEmails(AppsFlyerProperties.EmailsCryptType emailsCryptType, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length + 1);
        arrayList.add(emailsCryptType.toString());
        arrayList.addAll(Arrays.asList(strArr));
        s.m187().m196("setUserEmails", (String[]) arrayList.toArray(new String[strArr.length + 1]));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EMAIL_CRYPT_TYPE, emailsCryptType.getValue());
        HashMap map = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        String str = null;
        for (String str2 : strArr) {
            int i = AnonymousClass5.f57[emailsCryptType.ordinal()];
            if (i == 2) {
                arrayList2.add(C0120r.m179(str2));
                str = "md5_el_arr";
            } else if (i == 3) {
                arrayList2.add(C0120r.m177(str2));
                str = "sha256_el_arr";
            } else if (i != 4) {
                arrayList2.add(C0120r.m180(str2));
                str = "sha1_el_arr";
            } else {
                arrayList2.add(str2);
                str = "plain_el_arr";
            }
        }
        map.put(str, arrayList2);
        AppsFlyerProperties.getInstance().setUserEmails(new JSONObject(map).toString());
    }

    /* JADX INFO: renamed from: com.appsflyer.AppsFlyerLib$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        static final /* synthetic */ int[] f57 = new int[AppsFlyerProperties.EmailsCryptType.values().length];

        static {
            try {
                f57[AppsFlyerProperties.EmailsCryptType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f57[AppsFlyerProperties.EmailsCryptType.MD5.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f57[AppsFlyerProperties.EmailsCryptType.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f57[AppsFlyerProperties.EmailsCryptType.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void setCollectAndroidID(boolean z) {
        s.m187().m196("setCollectAndroidID", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, Boolean.toString(z));
    }

    public void setCollectIMEI(boolean z) {
        s.m187().m196("setCollectIMEI", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, Boolean.toString(z));
    }

    @Deprecated
    public void setCollectFingerPrint(boolean z) {
        s.m187().m196("setCollectFingerPrint", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_FINGER_PRINT, Boolean.toString(z));
    }

    public AppsFlyerLib init(String str, AppsFlyerConversionListener appsFlyerConversionListener) {
        s sVarM187 = s.m187();
        String[] strArr = new String[2];
        strArr[0] = str;
        strArr[1] = appsFlyerConversionListener == null ? "null" : "conversionDataListener";
        sVarM187.m196(com.my.target.m.aq, strArr);
        AFLogger.m20(String.format("Initializing AppsFlyer SDK: (v%s.%s)", BuildConfig.VERSION_NAME, "170"));
        this.f42 = true;
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
        f.AnonymousClass4.m126(str);
        f23 = appsFlyerConversionListener;
        return this;
    }

    public AppsFlyerLib init(String str, AppsFlyerConversionListener appsFlyerConversionListener, Context context) {
        if (context != null) {
            if (m53(context)) {
                if (this.f49 == null) {
                    this.f49 = new com.appsflyer.d();
                    this.f49.m117(context, this);
                } else {
                    AFLogger.afWarnLog("AFInstallReferrer instance already created");
                }
            }
        } else {
            AFLogger.afWarnLog("init :: context is null, Google Install Referrer will be not initialized!");
        }
        return init(str, appsFlyerConversionListener);
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static boolean m53(@NonNull Context context) {
        if (m24(context.getSharedPreferences("appsflyer-data", 0), "appsFlyerCount", false) > 2) {
            AFLogger.afRDLog("Install referrer will not load, the counter > 2, ");
            return false;
        }
        try {
            Class.forName("com.android.installreferrer.api.InstallReferrerClient");
            if (f.AnonymousClass1.m125(context, "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE")) {
                AFLogger.afDebugLog("Install referrer is allowed");
                return true;
            }
            AFLogger.afDebugLog("Install referrer is not allowed");
            return false;
        } catch (ClassNotFoundException unused) {
            AFLogger.afRDLog("Class com.android.installreferrer.api.InstallReferrerClient not found");
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest : com.android.installreferrer.api.InstallReferrerClient", th);
            return false;
        }
    }

    public void startTracking(Application application) {
        if (!this.f42) {
            AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! The API call 'startTracking(Application)' must be called after the 'init(String, AppsFlyerConversionListener)' API method, which should be called on the Application's onCreate.");
        } else {
            startTracking(application, null);
        }
    }

    public void startTracking(Application application, String str) {
        startTracking(application, str, null);
    }

    public void startTracking(Application application, String str, AppsFlyerTrackingRequestListener appsFlyerTrackingRequestListener) {
        s.m187().m196("startTracking", str);
        AFLogger.afInfoLog(String.format("Starting AppsFlyer Tracking: (v%s.%s)", BuildConfig.VERSION_NAME, "170"));
        AFLogger.afInfoLog("Build Number: 170");
        AppsFlyerProperties.getInstance().loadProperties(application.getApplicationContext());
        if (!TextUtils.isEmpty(str)) {
            AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
            f.AnonymousClass4.m126(str);
        } else if (TextUtils.isEmpty(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY))) {
            AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the startTracking API method (should be called on Activity's onCreate).");
            return;
        }
        if (appsFlyerTrackingRequestListener != null) {
            f27 = appsFlyerTrackingRequestListener;
        }
        m46(application);
    }

    public void setAppId(String str) {
        s.m187().m196("setAppId", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.APP_ID, str);
    }

    public void setExtension(String str) {
        s.m187().m196("setExtension", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EXTENSION, str);
    }

    public void setIsUpdate(boolean z) {
        s.m187().m196("setIsUpdate", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_UPDATE, z);
    }

    public void setCurrencyCode(String str) {
        s.m187().m196("setCurrencyCode", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.CURRENCY_CODE, str);
    }

    public void trackLocation(Context context, double d2, double d3) {
        s.m187().m196("trackLocation", String.valueOf(d2), String.valueOf(d3));
        HashMap map = new HashMap();
        map.put(AFInAppEventParameterName.LONGTITUDE, Double.toString(d3));
        map.put(AFInAppEventParameterName.LATITUDE, Double.toString(d2));
        m79(context, AFInAppEventType.LOCATION_COORDINATES, map);
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m76(WeakReference<Context> weakReference) {
        if (weakReference.get() == null) {
            return;
        }
        AFLogger.afInfoLog("app went to background");
        SharedPreferences sharedPreferences = weakReference.get().getSharedPreferences("appsflyer-data", 0);
        AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
        long j = this.f33 - this.f31;
        HashMap map = new HashMap();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (string == null) {
            AFLogger.afWarnLog("[callStats] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        String string2 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            map.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        o oVarM159 = l.m159(weakReference.get().getContentResolver());
        if (oVarM159 != null) {
            map.put("amazon_aid", oVarM159.m168());
            map.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
        }
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string3 != null) {
            map.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        map.put("app_id", weakReference.get().getPackageName());
        map.put("devkey", string);
        map.put("uid", t.m211(weakReference));
        map.put("time_in_app", String.valueOf(j / 1000));
        map.put("statType", "user_closed_app");
        map.put("platform", com.ironsource.sdk.constants.Constants.JAVASCRIPT_INTERFACE_NAME);
        map.put("launch_counter", Integer.toString(m24(sharedPreferences, "appsFlyerCount", false)));
        map.put(AppsFlyerProperties.CHANNEL, m61(weakReference));
        if (string2 == null) {
            string2 = "";
        }
        map.put("originalAppsflyerId", string2);
        if (this.f44) {
            try {
                k kVar = new k(null, isTrackingStopped());
                kVar.f175 = map;
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    AFLogger.afDebugLog("Main thread detected. Running callStats task in a new thread.");
                    kVar.execute(ServerConfigHandler.getUrl("https://%sstats.%s/stats"));
                    return;
                }
                StringBuilder sb = new StringBuilder("Running callStats task (on current thread: ");
                sb.append(Thread.currentThread().toString());
                sb.append(" )");
                AFLogger.afDebugLog(sb.toString());
                kVar.onPreExecute();
                kVar.onPostExecute(kVar.doInBackground(ServerConfigHandler.getUrl("https://%sstats.%s/stats")));
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog("Could not send callStats request", th);
                return;
            }
        }
        AFLogger.afDebugLog("Stats call is disabled, ignore ...");
    }

    public void trackAppLaunch(Context context, String str) {
        if (m53(context)) {
            if (this.f49 == null) {
                this.f49 = new com.appsflyer.d();
                this.f49.m117(context, this);
            } else {
                AFLogger.afWarnLog("AFInstallReferrer instance already created");
            }
        }
        m36(context, str, (String) null, (String) null, "", (Intent) null, (String) null);
    }

    protected void setDeepLinkData(Intent intent) {
        if (intent != null) {
            try {
                if ("android.intent.action.VIEW".equals(intent.getAction())) {
                    this.latestDeepLink = intent.getData();
                    StringBuilder sb = new StringBuilder("Unity setDeepLinkData = ");
                    sb.append(this.latestDeepLink);
                    AFLogger.afDebugLog(sb.toString());
                }
            } catch (Throwable th) {
                AFLogger.afErrorLog("Exception while setting deeplink data (unity). ", th);
            }
        }
    }

    public void setPluginDeepLinkData(Intent intent) {
        setDeepLinkData(intent);
    }

    public void reportTrackSession(Context context) {
        s.m187().m196("reportTrackSession", new String[0]);
        s.m187().m191();
        m79(context, (String) null, (Map<String, Object>) null);
    }

    public void trackEvent(Context context, String str, Map<String, Object> map) {
        s.m187().m196("trackEvent", str, new JSONObject(map == null ? new HashMap<>() : map).toString());
        m79(context, str, map);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void m79(Context context, String str, Map<String, Object> map) {
        String string;
        Intent intent;
        Uri referrer = null;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            Intent intent2 = activity.getIntent();
            AFDeepLinkManager.getInstance().currentActivityHash = System.identityHashCode(activity);
            try {
                referrer = ActivityCompat.getReferrer(activity);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
            string = referrer != null ? referrer.toString() : "";
            intent = intent2;
        } else {
            string = "";
            intent = null;
        }
        if (AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY) == null) {
            AFLogger.afWarnLog("[TrackEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        JSONObject jSONObject = new JSONObject(map);
        String referrer2 = AppsFlyerProperties.getInstance().getReferrer(context);
        m36(context, (String) null, str, jSONObject.toString(), referrer2 == null ? "" : referrer2, intent, string);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static void m66(Context context, String str, String str2, String str3) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.IS_MONITOR, false)) {
            Intent intent = new Intent("com.appsflyer.MonitorBroadcast");
            intent.setPackage("com.appsflyer.nightvision");
            intent.putExtra("message", str2);
            intent.putExtra("value", str3);
            intent.putExtra("packageName", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            intent.putExtra(Constants.URL_MEDIA_SOURCE, new Integer(Process.myPid()));
            intent.putExtra("eventIdentifier", str);
            intent.putExtra("sdk", BuildConfig.VERSION_NAME);
            context.sendBroadcast(intent);
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m81(Context context, String str) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null) {
            AFLogger.afInfoLog("CustomerUserId not set, Tracking is disabled", true);
            return;
        }
        HashMap map = new HashMap();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (string == null) {
            AFLogger.afWarnLog("[registerUninstall] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            map.put("app_version_code", Integer.toString(packageInfo.versionCode));
            map.put("app_version_name", packageInfo.versionName);
            map.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
            long j = packageInfo.firstInstallTime;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            map.put("installDate", simpleDateFormat.format(new Date(j)));
        } catch (Throwable th) {
            AFLogger.afErrorLog("Exception while collecting application version info.", th);
        }
        m50(context, map);
        String string2 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
        if (string2 != null) {
            map.put("appUserId", string2);
        }
        try {
            map.put("model", Build.MODEL);
            map.put("brand", Build.BRAND);
        } catch (Throwable th2) {
            AFLogger.afErrorLog("Exception while collecting device brand and model.", th2);
        }
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            map.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        o oVarM159 = l.m159(context.getContentResolver());
        if (oVarM159 != null) {
            map.put("amazon_aid", oVarM159.m168());
            map.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
        }
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string3 != null) {
            map.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        map.put("devkey", string);
        map.put("uid", t.m211((WeakReference<Context>) new WeakReference(context)));
        map.put("af_gcm_token", str);
        map.put("launch_counter", Integer.toString(m24(context.getSharedPreferences("appsflyer-data", 0), "appsFlyerCount", false)));
        map.put("sdk", Integer.toString(Build.VERSION.SDK_INT));
        String strM61 = m61((WeakReference<Context>) new WeakReference(context));
        if (strM61 != null) {
            map.put(AppsFlyerProperties.CHANNEL, strM61);
        }
        try {
            k kVar = new k(context, isTrackingStopped());
            kVar.f175 = map;
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl(f22));
            sb.append(packageName);
            kVar.execute(sb.toString());
        } catch (Throwable th3) {
            AFLogger.afErrorLog(th3.getMessage(), th3);
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static void m47(Context context, String str) {
        Intent intent = new Intent("com.appsflyer.testIntgrationBroadcast");
        intent.putExtra("params", str);
        if (Build.VERSION.SDK_INT >= 26) {
            if (context.getPackageManager().queryBroadcastReceivers(intent, 0).toString().contains("com.appsflyer.referrerSender")) {
                Intent intent2 = new Intent(intent);
                intent2.setComponent(new ComponentName("com.appsflyer.referrerSender", "com.appsflyer.referrerSender.Receiver"));
                context.sendBroadcast(intent2);
                return;
            }
            return;
        }
        context.sendBroadcast(intent);
    }

    public void setDeviceTrackingDisabled(boolean z) {
        s.m187().m196("setDeviceTrackingDisabled", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    public static Map<String, String> m69(Context context) throws m {
        String string = context.getSharedPreferences("appsflyer-data", 0).getString("attributionId", null);
        if (string != null && string.length() > 0) {
            return m71(string);
        }
        throw new m();
    }

    public void registerConversionListener(Context context, AppsFlyerConversionListener appsFlyerConversionListener) {
        s.m187().m196("registerConversionListener", new String[0]);
        if (appsFlyerConversionListener != null) {
            f23 = appsFlyerConversionListener;
        }
    }

    public void unregisterConversionListener() {
        s.m187().m196("unregisterConversionListener", new String[0]);
        f23 = null;
    }

    public void registerValidatorListener(Context context, AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener) {
        s.m187().m196("registerValidatorListener", new String[0]);
        AFLogger.afDebugLog("registerValidatorListener called");
        if (appsFlyerInAppPurchaseValidatorListener == null) {
            AFLogger.afDebugLog("registerValidatorListener null listener");
        } else {
            f20 = appsFlyerInAppPurchaseValidatorListener;
        }
    }

    protected void getConversionData(Context context, final ConversionDataListener conversionDataListener) {
        f23 = new AppsFlyerConversionListener() { // from class: com.appsflyer.AppsFlyerLib.4
            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onAppOpenAttribution(Map<String, String> map) {
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onAttributionFailure(String str) {
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onInstallConversionDataLoaded(Map<String, String> map) {
                conversionDataListener.onConversionDataLoaded(map);
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onInstallConversionFailure(String str) {
                conversionDataListener.onConversionFailure(str);
            }
        };
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static Map<String, String> m27(Context context, String str) {
        int i;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String[] strArrSplit = str.split(com.ironsource.sdk.constants.Constants.RequestParameters.AMPERSAND);
        int length = strArrSplit.length;
        int i2 = 0;
        boolean z = false;
        while (true) {
            String str2 = "media_source";
            if (i2 < length) {
                String str3 = strArrSplit[i2];
                int iIndexOf = str3.indexOf(com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL);
                String strSubstring = iIndexOf > 0 ? str3.substring(0, iIndexOf) : str3;
                if (!linkedHashMap.containsKey(strSubstring)) {
                    if (strSubstring.equals(Constants.URL_CAMPAIGN)) {
                        str2 = FirebaseAnalytics.Param.CAMPAIGN;
                    } else {
                        if (!strSubstring.equals(Constants.URL_MEDIA_SOURCE)) {
                            if (strSubstring.equals("af_prt")) {
                                z = true;
                                str2 = "agency";
                            }
                        }
                        linkedHashMap.put(strSubstring, "");
                    }
                    strSubstring = str2;
                    linkedHashMap.put(strSubstring, "");
                }
                linkedHashMap.put(strSubstring, (iIndexOf <= 0 || str3.length() <= (i = iIndexOf + 1)) ? null : str3.substring(i));
                i2++;
            } else {
                try {
                    break;
                } catch (Exception e2) {
                    AFLogger.afErrorLog("Could not fetch install time. ", e2);
                }
            }
        }
        if (!linkedHashMap.containsKey("install_time")) {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            long j = packageInfo.firstInstallTime;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            linkedHashMap.put("install_time", simpleDateFormat.format(new Date(j)));
        }
        if (!linkedHashMap.containsKey("af_status")) {
            linkedHashMap.put("af_status", "Non-organic");
        }
        if (z) {
            linkedHashMap.remove("media_source");
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    public static Map<String, String> m71(String str) {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!f21.contains(next)) {
                    String string = jSONObject.getString(next);
                    if (!TextUtils.isEmpty(string) && !"null".equals(string)) {
                        map.put(next, string);
                    }
                }
            }
            return map;
        } catch (JSONException e2) {
            AFLogger.afErrorLog(e2.getMessage(), e2);
            return null;
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private void m36(Context context, String str, String str2, String str3, String str4, Intent intent, String str5) {
        Context applicationContext = context.getApplicationContext();
        boolean z = false;
        boolean z2 = str2 == null;
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null) {
            z = true;
        }
        if (z) {
            AFLogger.afInfoLog("CustomerUserId not set, Tracking is disabled", true);
            return;
        }
        if (z2) {
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.LAUNCH_PROTECT_ENABLED, true)) {
                if (m40()) {
                    return;
                }
            } else {
                AFLogger.afInfoLog("Allowing multiple launches within a 5 second time window.");
            }
            this.f29 = System.currentTimeMillis();
        }
        m39(AFExecutor.getInstance().m6(), new c(this, new WeakReference(applicationContext), str, str2, str3, str4, false, intent, str5, (byte) 0), 150L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private boolean m40() {
        if (this.f29 > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.f29;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z", Locale.US);
            long j = this.f29;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String str = simpleDateFormat.format(new Date(j));
            long j2 = this.f46;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String str2 = simpleDateFormat.format(new Date(j2));
            if (jCurrentTimeMillis < this.f47 && !isTrackingStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", str, str2, Long.valueOf(jCurrentTimeMillis), Long.valueOf(this.f47)));
                return true;
            }
            if (!isTrackingStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nSending launch (+%s ms)", str, str2, Long.valueOf(jCurrentTimeMillis)));
            }
        } else if (!isTrackingStopped()) {
            AFLogger.afInfoLog("Sending first launch for this session!");
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x02fb A[Catch: Throwable -> 0x0bcd, TRY_LEAVE, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:104:0x030e A[Catch: Exception -> 0x0314, Throwable -> 0x0bcd, TRY_LEAVE, TryCatch #21 {Exception -> 0x0314, blocks: (B:102:0x0300, B:104:0x030e), top: B:484:0x0300, outer: #13 }] */
    /* JADX WARN: Code duplicated, block: B:109:0x031b A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:111:0x0327 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:116:0x0341 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:118:0x0348 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x034e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:125:0x0366 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x036f A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:128:0x037e A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:129:0x0383  */
    /* JADX WARN: Code duplicated, block: B:132:0x0389 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:135:0x03a1 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:137:0x03a7 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:138:0x03af A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:140:0x03bc A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:142:0x03c8 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:148:0x03d4 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:150:0x03e8 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:154:0x03f1  */
    /* JADX WARN: Code duplicated, block: B:156:0x03f4 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:158:0x03fc A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:162:0x0405  */
    /* JADX WARN: Code duplicated, block: B:164:0x0408 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:166:0x0410 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:170:0x0419  */
    /* JADX WARN: Code duplicated, block: B:172:0x041c A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:175:0x0428  */
    /* JADX WARN: Code duplicated, block: B:177:0x042b  */
    /* JADX WARN: Code duplicated, block: B:178:0x042c A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:180:0x0439 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:182:0x043e A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:184:0x0447 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:187:0x0456 A[Catch: Throwable -> 0x0bcd, TRY_LEAVE, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:190:0x0465 A[Catch: Throwable -> 0x0bcd, TRY_ENTER, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:193:0x046f A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:195:0x047b A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:200:0x0490 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:203:0x04a1 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:204:0x04a7 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:206:0x04b3 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:208:0x04be A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:210:0x04c5 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:213:0x04d6 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:216:0x04ed A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:218:0x04f4 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:222:0x051b A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:239:0x056c A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:240:0x0572  */
    /* JADX WARN: Code duplicated, block: B:244:0x0585 A[Catch: Throwable -> 0x0bcb, TRY_ENTER, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:245:0x058e A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:247:0x05a7 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:275:0x0626 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:277:0x062c A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:278:0x062f A[PHI: r19
  0x062f: PHI (r19v4 java.lang.Object) = (r19v2 java.lang.Object), (r19v9 java.lang.Object), (r19v10 java.lang.Object) binds: [B:276:0x062a, B:256:0x05d8, B:274:0x0623] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:280:0x0632 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:281:0x063d A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:284:0x0656 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:300:0x069e A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:302:0x06a2 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:303:0x06a5  */
    /* JADX WARN: Code duplicated, block: B:305:0x06a8 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:306:0x06b3 A[Catch: Throwable -> 0x0bcb, TRY_LEAVE, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:309:0x06c3 A[Catch: Exception -> 0x06c9, Throwable -> 0x0bcb, TRY_LEAVE, TryCatch #19 {Exception -> 0x06c9, blocks: (B:307:0x06b8, B:309:0x06c3), top: B:480:0x06b8, outer: #10 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00bd A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:347:0x07e7 A[Catch: Throwable -> 0x081d, TryCatch #0 {Throwable -> 0x081d, blocks: (B:345:0x07af, B:347:0x07e7, B:349:0x07f3, B:351:0x0804, B:352:0x080a), top: B:458:0x07af }] */
    /* JADX WARN: Code duplicated, block: B:349:0x07f3 A[Catch: Throwable -> 0x081d, TryCatch #0 {Throwable -> 0x081d, blocks: (B:345:0x07af, B:347:0x07e7, B:349:0x07f3, B:351:0x0804, B:352:0x080a), top: B:458:0x07af }] */
    /* JADX WARN: Code duplicated, block: B:350:0x0802  */
    /* JADX WARN: Code duplicated, block: B:365:0x0832 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:368:0x0842 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x0100 A[Catch: Throwable -> 0x0bcd, TRY_ENTER, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:371:0x0853 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:374:0x087d A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:377:0x0888 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:379:0x0895 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:383:0x08b8 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:386:0x08e3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:38:0x010e A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:390:0x08ef  */
    /* JADX WARN: Code duplicated, block: B:392:0x08f2 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:395:0x091d A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:397:0x092c A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:398:0x0932 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:401:0x0943 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:404:0x0963 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x0118 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:411:0x09a3  */
    /* JADX WARN: Code duplicated, block: B:412:0x09a5  */
    /* JADX WARN: Code duplicated, block: B:415:0x09b5  */
    /* JADX WARN: Code duplicated, block: B:420:0x09d9  */
    /* JADX WARN: Code duplicated, block: B:423:0x09de  */
    /* JADX WARN: Code duplicated, block: B:424:0x09df  */
    /* JADX WARN: Code duplicated, block: B:427:0x0a1a A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:429:0x0a1e A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:431:0x0a2c A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:434:0x0a59 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:437:0x0a77 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x012f A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:440:0x0a83 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:442:0x0a91 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:445:0x0b85 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:448:0x0ba1 A[Catch: Throwable -> 0x0bcb, TryCatch #10 {Throwable -> 0x0bcb, blocks: (B:239:0x056c, B:241:0x0576, B:244:0x0585, B:307:0x06b8, B:309:0x06c3, B:314:0x06e0, B:319:0x06f5, B:324:0x070a, B:329:0x071f, B:330:0x0737, B:363:0x082c, B:365:0x0832, B:366:0x0839, B:368:0x0842, B:369:0x0847, B:371:0x0853, B:372:0x0860, B:374:0x087d, B:377:0x0888, B:379:0x0895, B:380:0x08aa, B:381:0x08b4, B:383:0x08b8, B:384:0x08ce, B:387:0x08e5, B:392:0x08f2, B:393:0x0911, B:395:0x091d, B:397:0x092c, B:398:0x0932, B:399:0x0939, B:401:0x0943, B:402:0x0959, B:404:0x0963, B:406:0x0969, B:408:0x0971, B:409:0x0976, B:413:0x09a6, B:417:0x09b8, B:419:0x09cd, B:425:0x09e0, B:427:0x0a1a, B:429:0x0a1e, B:431:0x0a2c, B:432:0x0a53, B:434:0x0a59, B:435:0x0a5e, B:437:0x0a77, B:440:0x0a83, B:442:0x0a91, B:443:0x0a96, B:445:0x0b85, B:446:0x0b95, B:448:0x0ba1, B:362:0x0827, B:334:0x0760, B:328:0x071a, B:323:0x0705, B:318:0x06f0, B:313:0x06cb, B:245:0x058e, B:247:0x05a7, B:249:0x05af, B:251:0x05b5, B:253:0x05c6, B:280:0x0632, B:282:0x0642, B:284:0x0656, B:286:0x065e, B:288:0x0664, B:305:0x06a8, B:306:0x06b3, B:292:0x0673, B:297:0x0686, B:299:0x0696, B:300:0x069e, B:302:0x06a2, B:281:0x063d, B:257:0x05da, B:266:0x05f3, B:268:0x0603, B:271:0x060d, B:273:0x061d, B:275:0x0626, B:277:0x062c, B:234:0x0559, B:237:0x0564, B:449:0x0bb6), top: B:471:0x0463, inners: #1, #12, #19, #20, #22, #23 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x0137  */
    /* JADX WARN: Code duplicated, block: B:460:0x06e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:472:0x077f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:478:0x025c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:47:0x013d A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:496:0x053a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x014c A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x016f A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x017c A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x019e  */
    /* JADX WARN: Code duplicated, block: B:56:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:59:0x01dc A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:63:0x01f8 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x0218 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x0232 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0236 A[Catch: Throwable -> 0x0bcd, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0241 A[Catch: Throwable -> 0x0bcd, TRY_LEAVE, TryCatch #13 {Throwable -> 0x0bcd, blocks: (B:5:0x003b, B:7:0x0041, B:11:0x004e, B:17:0x0068, B:18:0x0072, B:20:0x008e, B:21:0x0099, B:23:0x00a1, B:24:0x00a6, B:26:0x00ae, B:32:0x00bd, B:33:0x00c4, B:36:0x0100, B:38:0x010e, B:40:0x0118, B:41:0x0125, B:43:0x012f, B:45:0x0139, B:47:0x013d, B:49:0x014c, B:51:0x016f, B:53:0x017f, B:57:0x01bb, B:59:0x01dc, B:61:0x01e5, B:63:0x01f8, B:64:0x020c, B:66:0x0218, B:68:0x0232, B:69:0x0236, B:93:0x02c3, B:96:0x02db, B:98:0x02e5, B:99:0x02ef, B:101:0x02fb, B:102:0x0300, B:104:0x030e, B:109:0x031b, B:111:0x0327, B:113:0x032d, B:114:0x0332, B:116:0x0341, B:118:0x0348, B:122:0x0352, B:123:0x0357, B:125:0x0366, B:132:0x0389, B:133:0x0392, B:135:0x03a1, B:137:0x03a7, B:182:0x043e, B:138:0x03af, B:140:0x03bc, B:142:0x03c8, B:148:0x03d4, B:150:0x03e8, B:156:0x03f4, B:158:0x03fc, B:164:0x0408, B:166:0x0410, B:172:0x041c, B:178:0x042c, B:180:0x0439, B:184:0x0447, B:185:0x0450, B:187:0x0456, B:190:0x0465, B:192:0x046b, B:198:0x0484, B:200:0x0490, B:201:0x0495, B:203:0x04a1, B:208:0x04be, B:210:0x04c5, B:211:0x04ca, B:213:0x04d6, B:214:0x04e1, B:216:0x04ed, B:218:0x04f4, B:219:0x050a, B:220:0x050f, B:222:0x051b, B:223:0x0520, B:204:0x04a7, B:206:0x04b3, B:193:0x046f, B:195:0x047b, B:197:0x0481, B:126:0x036f, B:128:0x037e, B:130:0x0384, B:108:0x0316, B:52:0x017c, B:55:0x01a0, B:71:0x0241, B:73:0x0250, B:76:0x025c, B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0, B:92:0x02be, B:30:0x00b6, B:12:0x0059), top: B:476:0x003b, inners: #9, #21 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x0295  */
    /* JADX WARN: Code duplicated, block: B:84:0x02ac A[Catch: Exception -> 0x02b4, Throwable -> 0x0bcd, TryCatch #8 {Exception -> 0x02b4, blocks: (B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0), top: B:468:0x026c }] */
    /* JADX WARN: Code duplicated, block: B:85:0x02b0 A[Catch: Exception -> 0x02b4, Throwable -> 0x0bcd, TRY_LEAVE, TryCatch #8 {Exception -> 0x02b4, blocks: (B:78:0x026c, B:82:0x0299, B:84:0x02ac, B:85:0x02b0), top: B:468:0x026c }] */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final Map<String, Object> m78(Context context, String str, String str2, String str3, String str4, boolean z, SharedPreferences sharedPreferences, boolean z2, @Nullable Intent intent, @Nullable String str5) {
        Object obj;
        String str6;
        SharedPreferences sharedPreferences2;
        SharedPreferences.Editor editorEdit;
        String str7;
        String str8;
        String str9;
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String strM61;
        String strM74;
        String str10;
        SharedPreferences sharedPreferences3;
        String str11;
        String str12;
        String strM22;
        SharedPreferences sharedPreferences4;
        String string6;
        String strM23;
        String string7;
        String string8;
        String string9;
        String string10;
        String str13;
        String string11;
        String string12;
        AppsFlyerLib appsFlyerLib;
        Object obj2;
        String attributionId;
        boolean z3;
        Object obj3;
        String str14;
        boolean z4;
        String string13;
        SimpleDateFormat simpleDateFormat;
        SharedPreferences sharedPreferences5;
        Object obj4;
        Throwable th;
        String string14;
        String string15;
        long jCurrentTimeMillis;
        boolean z5;
        o oVarM159;
        String referrer;
        boolean zEquals;
        int iM24;
        boolean z6;
        boolean z7;
        HashMap map;
        com.appsflyer.b.c cVarM96;
        List<Map<String, Object>> listM122;
        Location locationM119;
        HashMap map2;
        PackageInfo packageInfo;
        String string16;
        String str15;
        String strM211;
        File fileM42;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        String strM59;
        String installerPackageName;
        long j;
        long jCurrentTimeMillis2;
        long j2;
        String string17;
        long j3;
        SharedPreferences.Editor editorEdit2;
        int i;
        AFKeystoreWrapper aFKeystoreWrapper;
        HashMap map3 = new HashMap();
        l.m158(context, map3);
        long time = new Date().getTime();
        map3.put("af_timestamp", Long.toString(time));
        String strM100 = com.appsflyer.c.m100(context, time);
        if (strM100 != null) {
            map3.put("cksm_v1", strM100);
        }
        try {
            if (!isTrackingStopped()) {
                StringBuilder sb = new StringBuilder("******* sendTrackingWithEvent: ");
                sb.append(z2 ? "Launch" : str2);
                AFLogger.afInfoLog(sb.toString());
            } else {
                AFLogger.afInfoLog("SDK tracking has been stopped");
            }
            m66(context, LOG_TAG, "EVENT_CREATED_WITH_NAME", z2 ? "Launch" : str2);
            CacheManager.getInstance().init(context);
            try {
                List listAsList = Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
                if (!listAsList.contains("android.permission.INTERNET")) {
                    AFLogger.afWarnLog("Permission android.permission.INTERNET is missing in the AndroidManifest.xml");
                    m66(context, null, "PERMISSION_INTERNET_MISSING", null);
                }
                if (!listAsList.contains("android.permission.ACCESS_NETWORK_STATE")) {
                    AFLogger.afWarnLog("Permission android.permission.ACCESS_NETWORK_STATE is missing in the AndroidManifest.xml");
                }
                if (!listAsList.contains("android.permission.ACCESS_WIFI_STATE")) {
                    AFLogger.afWarnLog("Permission android.permission.ACCESS_WIFI_STATE is missing in the AndroidManifest.xml");
                    if (z) {
                        map3.put("af_events_api", "1");
                    }
                    map3.put("brand", Build.BRAND);
                    map3.put("device", Build.DEVICE);
                    map3.put(AppLovinEventTypes.USER_VIEWED_PRODUCT, Build.PRODUCT);
                    map3.put("sdk", Integer.toString(Build.VERSION.SDK_INT));
                    map3.put("model", Build.MODEL);
                    map3.put("deviceType", Build.TYPE);
                    map3.put("deviceRm", Build.DISPLAY);
                    if (z2) {
                        if (!context.getSharedPreferences("appsflyer-data", 0).contains("appsFlyerCount")) {
                            if (!AppsFlyerProperties.getInstance().isOtherSdkStringDisabled()) {
                                map3.put(com.ironsource.sdk.constants.Constants.RequestParameters.BATTERY_LEVEL, String.valueOf(m73(context)));
                            }
                            if ("OPPO".equals(Build.BRAND)) {
                                i = 23;
                                AFLogger.afRDLog("OPPO device found");
                            } else {
                                i = 18;
                            }
                            if (Build.VERSION.SDK_INT >= i) {
                                obj = "af_timestamp";
                                if (!AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DISABLE_KEYSTORE, false)) {
                                    StringBuilder sb2 = new StringBuilder("OS SDK is=");
                                    sb2.append(Build.VERSION.SDK_INT);
                                    sb2.append("; use KeyStore");
                                    AFLogger.afRDLog(sb2.toString());
                                    aFKeystoreWrapper = new AFKeystoreWrapper(context);
                                    if (aFKeystoreWrapper.m12()) {
                                        aFKeystoreWrapper.m14();
                                    } else {
                                        aFKeystoreWrapper.m11(t.m211((WeakReference<Context>) new WeakReference(context)));
                                    }
                                    AppsFlyerProperties.getInstance().set("KSAppsFlyerId", aFKeystoreWrapper.m15());
                                    AppsFlyerProperties.getInstance().set("KSAppsFlyerRICounter", String.valueOf(aFKeystoreWrapper.m13()));
                                }
                            } else {
                                obj = "af_timestamp";
                            }
                            StringBuilder sb3 = new StringBuilder("OS SDK is=");
                            sb3.append(Build.VERSION.SDK_INT);
                            sb3.append("; no KeyStore usage");
                            AFLogger.afRDLog(sb3.toString());
                        } else {
                            obj = "af_timestamp";
                        }
                        str6 = "INSTALL_STORE";
                        j = context.getSharedPreferences("appsflyer-data", 0).getLong("AppsFlyerTimePassedSincePrevLaunch", 0L);
                        jCurrentTimeMillis2 = System.currentTimeMillis();
                        m64(context, "AppsFlyerTimePassedSincePrevLaunch", jCurrentTimeMillis2);
                        if (j > 0) {
                            j2 = (jCurrentTimeMillis2 - j) / 1000;
                        } else {
                            j2 = -1;
                        }
                        map3.put("timepassedsincelastlaunch", Long.toString(j2));
                        string17 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
                        if (string17 != null) {
                            map3.put("onelink_id", string17);
                            map3.put("ol_ver", AppsFlyerProperties.getInstance().getString("onelinkVersion"));
                        }
                        j3 = sharedPreferences.getLong("appsflyerGetConversionDataTiming", 0L);
                        if (j3 > 0) {
                            map3.put("gcd_timing", Long.toString(j3));
                            editorEdit2 = sharedPreferences.edit();
                            editorEdit2.putLong("appsflyerGetConversionDataTiming", 0L);
                            if (Build.VERSION.SDK_INT >= 9) {
                                editorEdit2.apply();
                            } else {
                                editorEdit2.commit();
                            }
                        }
                        str7 = "appsFlyerCount";
                        str8 = "appsflyer-data";
                        str9 = AppsFlyerProperties.APP_ID;
                    } else {
                        obj = "af_timestamp";
                        str6 = "INSTALL_STORE";
                        sharedPreferences2 = context.getSharedPreferences("appsflyer-data", 0);
                        editorEdit = sharedPreferences2.edit();
                        try {
                            string = sharedPreferences2.getString("prev_event_name", null);
                            str9 = AppsFlyerProperties.APP_ID;
                            if (string != null) {
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    StringBuilder sb4 = new StringBuilder();
                                    str7 = "appsFlyerCount";
                                    str8 = "appsflyer-data";
                                    try {
                                        sb4.append(sharedPreferences2.getLong("prev_event_timestamp", -1L));
                                        jSONObject.put("prev_event_timestamp", sb4.toString());
                                        jSONObject.put("prev_event_value", sharedPreferences2.getString("prev_event_value", null));
                                        jSONObject.put("prev_event_name", string);
                                        map3.put("prev_event", jSONObject.toString());
                                    } catch (Exception e2) {
                                        e = e2;
                                        AFLogger.afErrorLog("Error while processing previous event.", e);
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    str7 = "appsFlyerCount";
                                    str8 = "appsflyer-data";
                                }
                            } else {
                                str7 = "appsFlyerCount";
                                str8 = "appsflyer-data";
                            }
                            editorEdit.putString("prev_event_name", str2);
                            editorEdit.putString("prev_event_value", str3);
                            editorEdit.putLong("prev_event_timestamp", System.currentTimeMillis());
                            if (Build.VERSION.SDK_INT >= 9) {
                                editorEdit.apply();
                            } else {
                                editorEdit.commit();
                            }
                        } catch (Exception e4) {
                            e = e4;
                            str7 = "appsFlyerCount";
                            str8 = "appsflyer-data";
                            str9 = AppsFlyerProperties.APP_ID;
                        }
                    }
                    string2 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
                    string3 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerRICounter");
                    if (string2 != null && string3 != null && Integer.valueOf(string3).intValue() > 0) {
                        map3.put("reinstallCounter", string3);
                        map3.put("originalAppsflyerId", string2);
                    }
                    string4 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ADDITIONAL_CUSTOM_DATA);
                    if (string4 != null) {
                        map3.put("customData", string4);
                    }
                    try {
                        installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                        if (installerPackageName != null) {
                            map3.put("installer_package", installerPackageName);
                            string5 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.EXTENSION);
                            if (string5 != null && string5.length() > 0) {
                                map3.put(AppsFlyerProperties.EXTENSION, string5);
                            }
                            strM61 = m61((WeakReference<Context>) new WeakReference(context));
                            strM74 = m74(context, strM61);
                            if (strM74 != null) {
                                map3.put(AppsFlyerProperties.CHANNEL, strM74);
                            }
                            if ((strM74 == null && !strM74.equals(strM61)) || (strM74 == null && strM61 != null)) {
                                map3.put("af_latestchannel", strM61);
                            }
                            str10 = str8;
                            sharedPreferences3 = context.getSharedPreferences(str10, 0);
                            str11 = str6;
                            if (sharedPreferences3.contains(str11)) {
                                strM22 = sharedPreferences3.getString(str11, null);
                                str12 = str7;
                            } else {
                                str12 = str7;
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    strM22 = m22(context);
                                } else {
                                    strM22 = null;
                                }
                                m65(context, str11, strM22);
                            }
                            if (strM22 != null) {
                                map3.put("af_installstore", strM22.toLowerCase());
                            }
                            sharedPreferences4 = context.getSharedPreferences(str10, 0);
                            string6 = AppsFlyerProperties.getInstance().getString("preInstallName");
                            if (string6 == null) {
                                if (sharedPreferences4.contains("preInstallName")) {
                                    string6 = sharedPreferences4.getString("preInstallName", null);
                                } else {
                                    if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                        fileM42 = m42(m25(PRE_INSTALL_SYSTEM_RO_PROP));
                                        if (fileM42 == null && fileM42.exists()) {
                                            z8 = false;
                                        } else {
                                            z8 = true;
                                        }
                                        if (z8) {
                                            fileM42 = m42(m31(AF_PRE_INSTALL_PATH, context.getPackageManager(), context.getPackageName()));
                                        }
                                        if (fileM42 == null && fileM42.exists()) {
                                            z9 = false;
                                        } else {
                                            z9 = true;
                                        }
                                        if (z9) {
                                            fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT);
                                        }
                                        if (fileM42 == null && fileM42.exists()) {
                                            z10 = false;
                                        } else {
                                            z10 = true;
                                        }
                                        if (z10) {
                                            fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT_ETC);
                                        }
                                        if (fileM42 == null && fileM42.exists()) {
                                            z11 = false;
                                        } else {
                                            z11 = true;
                                        }
                                        if (!z11 || (strM59 = m59(fileM42, context.getPackageName())) == null) {
                                            string6 = null;
                                        } else {
                                            string6 = strM59;
                                        }
                                        if (string6 != null) {
                                            string6 = m68((WeakReference<Context>) new WeakReference(context), "AF_PRE_INSTALL_NAME");
                                        }
                                    }
                                    if (string6 != null) {
                                        m65(context, "preInstallName", string6);
                                    }
                                }
                                if (string6 != null) {
                                    AppsFlyerProperties.getInstance().set("preInstallName", string6);
                                }
                            }
                            if (string6 != null) {
                                map3.put("af_preinstall_name", string6.toLowerCase());
                            }
                            strM23 = m22(context);
                            if (strM23 != null) {
                                map3.put("af_currentstore", strM23.toLowerCase());
                            }
                            try {
                                if (str == null && str.length() >= 0) {
                                    map3.put("appsflyerKey", str);
                                } else {
                                    string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                                    if (string7 == null && string7.length() >= 0) {
                                        map3.put("appsflyerKey", string7);
                                    } else {
                                        AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                                        m66(context, LOG_TAG, "DEV_KEY_MISSING", null);
                                        AFLogger.afInfoLog("AppsFlyer will not track this event.");
                                        return null;
                                    }
                                }
                                string8 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
                                if (string8 != null) {
                                    map3.put("appUserId", string8);
                                }
                                string9 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAILS);
                                if (string9 != null) {
                                    map3.put("user_emails", string9);
                                } else {
                                    string10 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAIL);
                                    if (string10 != null) {
                                        map3.put("sha1_el", C0120r.m180(string10));
                                    }
                                }
                                if (str2 != null) {
                                    map3.put(com.ironsource.sdk.constants.Constants.ParametersKeys.EVENT_NAME, str2);
                                    if (str3 != null) {
                                        map3.put("eventValue", str3);
                                    }
                                }
                                str13 = str9;
                                if (AppsFlyerProperties.getInstance().getString(str13) != null) {
                                    map3.put(str13, AppsFlyerProperties.getInstance().getString(str13));
                                }
                                string11 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CURRENCY_CODE);
                                if (string11 != null) {
                                    if (string11.length() != 3) {
                                        StringBuilder sb5 = new StringBuilder("WARNING: currency code should be 3 characters!!! '");
                                        sb5.append(string11);
                                        sb5.append("' is not a legal value.");
                                        AFLogger.afWarnLog(sb5.toString());
                                    }
                                    map3.put("currency", string11);
                                }
                                string12 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.IS_UPDATE);
                                if (string12 != null) {
                                    map3.put("isUpdate", string12);
                                }
                                map3.put("af_preinstalled", Boolean.toString(isPreInstalledApp(context)));
                                if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true)) {
                                    try {
                                        context.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                                        appsFlyerLib = this;
                                        obj2 = obj;
                                        try {
                                            attributionId = appsFlyerLib.getAttributionId(context.getContentResolver());
                                        } catch (PackageManager.NameNotFoundException unused) {
                                            AFLogger.afWarnLog("Exception while collecting facebook's attribution ID. ");
                                            attributionId = null;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            AFLogger.afErrorLog("Exception while collecting facebook's attribution ID. ", th);
                                            attributionId = null;
                                        }
                                    } catch (PackageManager.NameNotFoundException unused2) {
                                        appsFlyerLib = this;
                                        obj2 = obj;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        appsFlyerLib = this;
                                        obj2 = obj;
                                    }
                                    if (attributionId != null) {
                                        map3.put("fb", attributionId);
                                    }
                                } else {
                                    appsFlyerLib = this;
                                    obj2 = obj;
                                }
                                if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
                                    map3.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    obj3 = obj2;
                                } else {
                                    SharedPreferences sharedPreferences6 = context.getSharedPreferences(str10, 0);
                                    z3 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI, true);
                                    String string18 = sharedPreferences6.getString("imeiCached", null);
                                    if (z3 || !TextUtils.isEmpty(appsFlyerLib.f35)) {
                                        obj3 = obj2;
                                        if (appsFlyerLib.f35 != null) {
                                            str14 = appsFlyerLib.f35;
                                        } else {
                                            str14 = null;
                                        }
                                    } else {
                                        if (m23(context)) {
                                            try {
                                                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                                                obj3 = obj2;
                                                try {
                                                    str14 = (String) telephonyManager.getClass().getMethod("getDeviceId", new Class[0]).invoke(telephonyManager, new Object[0]);
                                                    if (str14 == null) {
                                                        if (string18 != null) {
                                                            AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(string18)));
                                                            str14 = string18;
                                                        }
                                                    }
                                                } catch (InvocationTargetException unused3) {
                                                    if (string18 != null) {
                                                        AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(string18)));
                                                        str14 = string18;
                                                    } else {
                                                        str14 = null;
                                                    }
                                                    AFLogger.afWarnLog("WARNING: READ_PHONE_STATE is missing.");
                                                } catch (Exception e5) {
                                                    e = e5;
                                                    Exception exc = e;
                                                    if (string18 != null) {
                                                        AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(string18)));
                                                        str14 = string18;
                                                    } else {
                                                        str14 = null;
                                                    }
                                                    AFLogger.afErrorLog("WARNING: other reason: ", exc);
                                                }
                                            } catch (InvocationTargetException unused4) {
                                                obj3 = obj2;
                                            } catch (Exception e6) {
                                                e = e6;
                                                obj3 = obj2;
                                            }
                                        } else {
                                            obj3 = obj2;
                                        }
                                        str14 = null;
                                    }
                                    if (str14 != null) {
                                        m65(context, "imeiCached", str14);
                                        map3.put("imei", str14);
                                    } else {
                                        AFLogger.afInfoLog("IMEI was not collected.");
                                    }
                                    z4 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID, true);
                                    String string19 = sharedPreferences6.getString("androidIdCached", null);
                                    if (z4 || !TextUtils.isEmpty(appsFlyerLib.f45)) {
                                        if (appsFlyerLib.f45 != null) {
                                            string13 = appsFlyerLib.f45;
                                        } else {
                                            string13 = null;
                                        }
                                        if (string13 != null) {
                                            m65(context, "androidIdCached", string13);
                                            map3.put(com.my.target.i.ANDROID_ID, string13);
                                        } else {
                                            AFLogger.afInfoLog("Android ID was not collected.");
                                        }
                                    } else if (m23(context)) {
                                        try {
                                            string13 = Settings.Secure.getString(context.getContentResolver(), com.my.target.i.ANDROID_ID);
                                            if (string13 == null) {
                                                if (string19 != null) {
                                                    AFLogger.afDebugLog("use cached AndroidId: ".concat(String.valueOf(string19)));
                                                    string13 = string19;
                                                } else {
                                                    string13 = null;
                                                }
                                            }
                                        } catch (Exception e7) {
                                            if (string19 != null) {
                                                AFLogger.afDebugLog("use cached AndroidId: ".concat(String.valueOf(string19)));
                                                string13 = string19;
                                            } else {
                                                string13 = null;
                                            }
                                            AFLogger.afErrorLog(e7.getMessage(), e7);
                                        }
                                        if (string13 != null) {
                                            m65(context, "androidIdCached", string13);
                                            map3.put(com.my.target.i.ANDROID_ID, string13);
                                        } else {
                                            AFLogger.afInfoLog("Android ID was not collected.");
                                        }
                                    } else {
                                        string13 = null;
                                        if (string13 != null) {
                                            m65(context, "androidIdCached", string13);
                                            map3.put(com.my.target.i.ANDROID_ID, string13);
                                        } else {
                                            AFLogger.afInfoLog("Android ID was not collected.");
                                        }
                                    }
                                }
                                try {
                                    strM211 = t.m211((WeakReference<Context>) new WeakReference(context));
                                    if (strM211 != null) {
                                        map3.put("uid", strM211);
                                        try {
                                            map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                                        } catch (Exception e8) {
                                            AFLogger.afErrorLog("Exception while collecting display language name. ", e8);
                                        }
                                        try {
                                            map3.put("lang_code", Locale.getDefault().getLanguage());
                                        } catch (Exception e9) {
                                            AFLogger.afErrorLog("Exception while collecting display language code. ", e9);
                                        }
                                        try {
                                            map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                                        } catch (Exception e10) {
                                            AFLogger.afErrorLog("Exception while collecting country name. ", e10);
                                        }
                                        map3.put("platformextension", appsFlyerLib.f40.m172());
                                        m50(context, map3);
                                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                                        try {
                                            long j4 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                            map3.put("installDate", simpleDateFormat.format(new Date(j4)));
                                        } catch (Exception e11) {
                                            AFLogger.afErrorLog("Exception while collecting install date. ", e11);
                                        }
                                        try {
                                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                            sharedPreferences5 = sharedPreferences;
                                            try {
                                                if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                                    try {
                                                        m48(context, "appsflyerConversionDataRequestRetries", 0);
                                                        m48(context, "versionCode", packageInfo.versionCode);
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        obj4 = "appsflyerKey";
                                                        AFLogger.afErrorLog("Exception while collecting app version data ", th);
                                                    }
                                                }
                                                map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                                                map3.put("app_version_name", packageInfo.versionName);
                                                long j5 = packageInfo.firstInstallTime;
                                                long j6 = packageInfo.lastUpdateTime;
                                                obj4 = "appsflyerKey";
                                                try {
                                                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                                    map3.put("date1", simpleDateFormat.format(new Date(j5)));
                                                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                                    map3.put("date2", simpleDateFormat.format(new Date(j6)));
                                                    string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                                                    if (string16 == null) {
                                                        if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                                            AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                                            str15 = simpleDateFormat.format(new Date());
                                                        } else {
                                                            str15 = "";
                                                        }
                                                        string16 = str15;
                                                        m65(context, "appsFlyerFirstInstall", string16);
                                                    }
                                                    AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                                                    map3.put("firstLaunchDate", string16);
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    th = th;
                                                    AFLogger.afErrorLog("Exception while collecting app version data ", th);
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                obj4 = "appsflyerKey";
                                                th = th;
                                                AFLogger.afErrorLog("Exception while collecting app version data ", th);
                                                if (str4.length() > 0) {
                                                    map3.put("referrer", str4);
                                                }
                                                string14 = sharedPreferences5.getString("extraReferrers", null);
                                                if (string14 != null) {
                                                    map3.put("extraReferrers", string14);
                                                }
                                                string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                                                if (string15 != null) {
                                                    map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                                                }
                                                appsFlyerLib.f41 = u.m214(context);
                                                StringBuilder sb6 = new StringBuilder("didConfigureTokenRefreshService=");
                                                sb6.append(appsFlyerLib.f41);
                                                AFLogger.afDebugLog(sb6.toString());
                                                if (!appsFlyerLib.f41) {
                                                    map3.put("tokenRefreshConfigured", Boolean.FALSE);
                                                }
                                                if (z2) {
                                                    AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                                    if (appsFlyerLib.f34 != null) {
                                                        JSONObject jSONObject2 = new JSONObject(appsFlyerLib.f34);
                                                        jSONObject2.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                                        map3.put("af_deeplink", jSONObject2.toString());
                                                    }
                                                    appsFlyerLib.f34 = null;
                                                    map3.put("open_referrer", str5);
                                                }
                                                if (appsFlyerLib.f39) {
                                                    map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                                    m47(context, new JSONObject(map3).toString());
                                                    AFLogger.afInfoLog("Sent retargeting params to test app");
                                                }
                                                jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                                                String referrer2 = AppsFlyerProperties.getInstance().getReferrer(context);
                                                if (jCurrentTimeMillis <= 30000) {
                                                    z5 = false;
                                                } else {
                                                    z5 = false;
                                                }
                                                if (z5) {
                                                    map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                                    m47(context, new JSONObject(map3).toString());
                                                    AFLogger.afInfoLog("Sent params to test app");
                                                    AFLogger.afInfoLog("Test mode ended!");
                                                    appsFlyerLib.f38 = 0L;
                                                }
                                                if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                                    l.m158(context, map3);
                                                    if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                                        map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                                    } else {
                                                        map3.put("GAID_retry", "false");
                                                    }
                                                }
                                                oVarM159 = l.m159(context.getContentResolver());
                                                if (oVarM159 != null) {
                                                    map3.put("amazon_aid", oVarM159.m168());
                                                    map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                                                }
                                                referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                                                if (referrer != null) {
                                                    map3.put("referrer", referrer);
                                                }
                                                zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                                                map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                                                iM24 = m24(sharedPreferences5, str12, z2);
                                                map3.put("counter", Integer.toString(iM24));
                                                if (str2 != null) {
                                                    z6 = true;
                                                } else {
                                                    z6 = false;
                                                }
                                                map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                                                if (z2) {
                                                    z7 = true;
                                                } else {
                                                    z7 = true;
                                                }
                                                if (zEquals) {
                                                    z7 = false;
                                                }
                                                map3.put("isFirstCall", Boolean.toString(z7));
                                                map = new HashMap();
                                                map.put("cpu_abi", m25("ro.product.cpu.abi"));
                                                map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                                                map.put("arch", m25("os.arch"));
                                                map.put("build_display_id", m25("ro.build.display.id"));
                                                if (z2) {
                                                    if (appsFlyerLib.f37) {
                                                        com.appsflyer.e eVar = com.appsflyer.e.d.f123;
                                                        locationM119 = com.appsflyer.e.m119(context);
                                                        map2 = new HashMap(3);
                                                        if (locationM119 != null) {
                                                            map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                                            map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                                            map2.put("ts", String.valueOf(locationM119.getTime()));
                                                        }
                                                        if (!map2.isEmpty()) {
                                                            map.put("loc", map2);
                                                        }
                                                    }
                                                    cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                                    map.put("btl", Float.toString(cVarM96.m97()));
                                                    if (cVarM96.m98() != null) {
                                                        map.put("btch", cVarM96.m98());
                                                    }
                                                    if (2 >= iM24) {
                                                        listM122 = f.m120(context).m122();
                                                        if (!listM122.isEmpty()) {
                                                            map.put("sensors", listM122);
                                                        }
                                                    }
                                                }
                                                map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                                                map3.put("deviceData", map);
                                                new C0120r();
                                                Object obj5 = obj4;
                                                String str16 = (String) map3.get(obj5);
                                                Object obj6 = obj3;
                                                String str17 = (String) map3.get(obj6);
                                                String str18 = (String) map3.get("uid");
                                                StringBuilder sb7 = new StringBuilder();
                                                sb7.append(str16.substring(0, 7));
                                                sb7.append(str18.substring(0, 7));
                                                sb7.append(str17.substring(str17.length() - 7));
                                                map3.put("af_v", C0120r.m180(sb7.toString()));
                                                new C0120r();
                                                String str19 = (String) map3.get(obj5);
                                                StringBuilder sb8 = new StringBuilder();
                                                sb8.append(str19);
                                                sb8.append(map3.get(obj6));
                                                String string20 = sb8.toString();
                                                StringBuilder sb9 = new StringBuilder();
                                                sb9.append(string20);
                                                sb9.append(map3.get("uid"));
                                                String string21 = sb9.toString();
                                                StringBuilder sb10 = new StringBuilder();
                                                sb10.append(string21);
                                                sb10.append(map3.get("installDate"));
                                                String string22 = sb10.toString();
                                                StringBuilder sb11 = new StringBuilder();
                                                sb11.append(string22);
                                                sb11.append(map3.get("counter"));
                                                String string23 = sb11.toString();
                                                StringBuilder sb12 = new StringBuilder();
                                                sb12.append(string23);
                                                sb12.append(map3.get("iaecounter"));
                                                map3.put("af_v2", C0120r.m180(C0120r.m179(sb12.toString())));
                                                map3.put("ivc", Boolean.valueOf(m72(context)));
                                                if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                                    map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                                                }
                                                if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                                    map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                                                }
                                                return map3;
                                            }
                                        } catch (Throwable th7) {
                                            th = th7;
                                            sharedPreferences5 = sharedPreferences;
                                        }
                                        if (str4.length() > 0) {
                                            map3.put("referrer", str4);
                                        }
                                        string14 = sharedPreferences5.getString("extraReferrers", null);
                                        if (string14 != null) {
                                            map3.put("extraReferrers", string14);
                                        }
                                        string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                                        if (string15 != null) {
                                            map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                                        }
                                        appsFlyerLib.f41 = u.m214(context);
                                        StringBuilder sb13 = new StringBuilder("didConfigureTokenRefreshService=");
                                        sb13.append(appsFlyerLib.f41);
                                        AFLogger.afDebugLog(sb13.toString());
                                        if (!appsFlyerLib.f41) {
                                            map3.put("tokenRefreshConfigured", Boolean.FALSE);
                                        }
                                        if (z2) {
                                            AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                            if (appsFlyerLib.f34 != null) {
                                                JSONObject jSONObject3 = new JSONObject(appsFlyerLib.f34);
                                                jSONObject3.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                                map3.put("af_deeplink", jSONObject3.toString());
                                            }
                                            appsFlyerLib.f34 = null;
                                            map3.put("open_referrer", str5);
                                        }
                                        if (appsFlyerLib.f39) {
                                            map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                            m47(context, new JSONObject(map3).toString());
                                            AFLogger.afInfoLog("Sent retargeting params to test app");
                                        }
                                        jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                                        String referrer3 = AppsFlyerProperties.getInstance().getReferrer(context);
                                        if (jCurrentTimeMillis <= 30000 || referrer3 == null || !referrer3.contains("AppsFlyer_Test")) {
                                            z5 = false;
                                        } else {
                                            z5 = true;
                                        }
                                        if (z5) {
                                            map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                            m47(context, new JSONObject(map3).toString());
                                            AFLogger.afInfoLog("Sent params to test app");
                                            AFLogger.afInfoLog("Test mode ended!");
                                            appsFlyerLib.f38 = 0L;
                                        }
                                        if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                            l.m158(context, map3);
                                            if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                                map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                            } else {
                                                map3.put("GAID_retry", "false");
                                            }
                                        }
                                        oVarM159 = l.m159(context.getContentResolver());
                                        if (oVarM159 != null) {
                                            map3.put("amazon_aid", oVarM159.m168());
                                            map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                                        }
                                        referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                                        if (referrer != null && referrer.length() > 0 && map3.get("referrer") == null) {
                                            map3.put("referrer", referrer);
                                        }
                                        zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                                        map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                                        iM24 = m24(sharedPreferences5, str12, z2);
                                        map3.put("counter", Integer.toString(iM24));
                                        if (str2 != null) {
                                            z6 = true;
                                        } else {
                                            z6 = false;
                                        }
                                        map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                                        if (z2 || iM24 != 1) {
                                            z7 = true;
                                        } else {
                                            AppsFlyerProperties.getInstance().setFirstLaunchCalled();
                                            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
                                                z7 = true;
                                                map3.put("wait_cid", Boolean.toString(true));
                                            } else {
                                                z7 = true;
                                            }
                                        }
                                        if (zEquals) {
                                            z7 = false;
                                        }
                                        map3.put("isFirstCall", Boolean.toString(z7));
                                        map = new HashMap();
                                        map.put("cpu_abi", m25("ro.product.cpu.abi"));
                                        map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                                        map.put("arch", m25("os.arch"));
                                        map.put("build_display_id", m25("ro.build.display.id"));
                                        if (z2) {
                                            if (appsFlyerLib.f37) {
                                                com.appsflyer.e eVar2 = com.appsflyer.e.d.f123;
                                                locationM119 = com.appsflyer.e.m119(context);
                                                map2 = new HashMap(3);
                                                if (locationM119 != null) {
                                                    map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                                    map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                                    map2.put("ts", String.valueOf(locationM119.getTime()));
                                                }
                                                if (!map2.isEmpty()) {
                                                    map.put("loc", map2);
                                                }
                                            }
                                            cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                            map.put("btl", Float.toString(cVarM96.m97()));
                                            if (cVarM96.m98() != null) {
                                                map.put("btch", cVarM96.m98());
                                            }
                                            if (2 >= iM24) {
                                                listM122 = f.m120(context).m122();
                                                if (!listM122.isEmpty()) {
                                                    map.put("sensors", listM122);
                                                }
                                            }
                                        }
                                        map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                                        map3.put("deviceData", map);
                                        new C0120r();
                                        Object obj7 = obj4;
                                        String str110 = (String) map3.get(obj7);
                                        Object obj8 = obj3;
                                        String str111 = (String) map3.get(obj8);
                                        String str112 = (String) map3.get("uid");
                                        StringBuilder sb14 = new StringBuilder();
                                        sb14.append(str110.substring(0, 7));
                                        sb14.append(str112.substring(0, 7));
                                        sb14.append(str111.substring(str111.length() - 7));
                                        map3.put("af_v", C0120r.m180(sb14.toString()));
                                        new C0120r();
                                        String str113 = (String) map3.get(obj7);
                                        StringBuilder sb15 = new StringBuilder();
                                        sb15.append(str113);
                                        sb15.append(map3.get(obj8));
                                        String string24 = sb15.toString();
                                        StringBuilder sb16 = new StringBuilder();
                                        sb16.append(string24);
                                        sb16.append(map3.get("uid"));
                                        String string25 = sb16.toString();
                                        StringBuilder sb17 = new StringBuilder();
                                        sb17.append(string25);
                                        sb17.append(map3.get("installDate"));
                                        String string26 = sb17.toString();
                                        StringBuilder sb18 = new StringBuilder();
                                        sb18.append(string26);
                                        sb18.append(map3.get("counter"));
                                        String string27 = sb18.toString();
                                        StringBuilder sb19 = new StringBuilder();
                                        sb19.append(string27);
                                        sb19.append(map3.get("iaecounter"));
                                        map3.put("af_v2", C0120r.m180(C0120r.m179(sb19.toString())));
                                        map3.put("ivc", Boolean.valueOf(m72(context)));
                                        if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                            map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                                        }
                                        if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                            map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                                        }
                                    } else {
                                        map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                                        map3.put("lang_code", Locale.getDefault().getLanguage());
                                        map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                                        map3.put("platformextension", appsFlyerLib.f40.m172());
                                        m50(context, map3);
                                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                                        long j7 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                        map3.put("installDate", simpleDateFormat.format(new Date(j7)));
                                        packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                        sharedPreferences5 = sharedPreferences;
                                        if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                            m48(context, "appsflyerConversionDataRequestRetries", 0);
                                            m48(context, "versionCode", packageInfo.versionCode);
                                        }
                                        map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                                        map3.put("app_version_name", packageInfo.versionName);
                                        long j8 = packageInfo.firstInstallTime;
                                        long j9 = packageInfo.lastUpdateTime;
                                        obj4 = "appsflyerKey";
                                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                        map3.put("date1", simpleDateFormat.format(new Date(j8)));
                                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                        map3.put("date2", simpleDateFormat.format(new Date(j9)));
                                        string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                                        if (string16 == null) {
                                            if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                                AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                                str15 = simpleDateFormat.format(new Date());
                                            } else {
                                                str15 = "";
                                            }
                                            string16 = str15;
                                            m65(context, "appsFlyerFirstInstall", string16);
                                        }
                                        AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                                        map3.put("firstLaunchDate", string16);
                                        if (str4.length() > 0) {
                                            map3.put("referrer", str4);
                                        }
                                        string14 = sharedPreferences5.getString("extraReferrers", null);
                                        if (string14 != null) {
                                            map3.put("extraReferrers", string14);
                                        }
                                        string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                                        if (string15 != null) {
                                            map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                                        }
                                        appsFlyerLib.f41 = u.m214(context);
                                        StringBuilder sb110 = new StringBuilder("didConfigureTokenRefreshService=");
                                        sb110.append(appsFlyerLib.f41);
                                        AFLogger.afDebugLog(sb110.toString());
                                        if (!appsFlyerLib.f41) {
                                            map3.put("tokenRefreshConfigured", Boolean.FALSE);
                                        }
                                        if (z2) {
                                            AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                            if (appsFlyerLib.f34 != null) {
                                                JSONObject jSONObject4 = new JSONObject(appsFlyerLib.f34);
                                                jSONObject4.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                                map3.put("af_deeplink", jSONObject4.toString());
                                            }
                                            appsFlyerLib.f34 = null;
                                            map3.put("open_referrer", str5);
                                        }
                                        if (appsFlyerLib.f39) {
                                            map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                            m47(context, new JSONObject(map3).toString());
                                            AFLogger.afInfoLog("Sent retargeting params to test app");
                                        }
                                        jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                                        String referrer4 = AppsFlyerProperties.getInstance().getReferrer(context);
                                        if (jCurrentTimeMillis <= 30000) {
                                            z5 = false;
                                        } else {
                                            z5 = false;
                                        }
                                        if (z5) {
                                            map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                            m47(context, new JSONObject(map3).toString());
                                            AFLogger.afInfoLog("Sent params to test app");
                                            AFLogger.afInfoLog("Test mode ended!");
                                            appsFlyerLib.f38 = 0L;
                                        }
                                        if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                            l.m158(context, map3);
                                            if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                                map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                            } else {
                                                map3.put("GAID_retry", "false");
                                            }
                                        }
                                        oVarM159 = l.m159(context.getContentResolver());
                                        if (oVarM159 != null) {
                                            map3.put("amazon_aid", oVarM159.m168());
                                            map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                                        }
                                        referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                                        if (referrer != null) {
                                            map3.put("referrer", referrer);
                                        }
                                        zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                                        map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                                        iM24 = m24(sharedPreferences5, str12, z2);
                                        map3.put("counter", Integer.toString(iM24));
                                        if (str2 != null) {
                                            z6 = true;
                                        } else {
                                            z6 = false;
                                        }
                                        map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                                        if (z2) {
                                            z7 = true;
                                        } else {
                                            z7 = true;
                                        }
                                        if (zEquals) {
                                            z7 = false;
                                        }
                                        map3.put("isFirstCall", Boolean.toString(z7));
                                        map = new HashMap();
                                        map.put("cpu_abi", m25("ro.product.cpu.abi"));
                                        map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                                        map.put("arch", m25("os.arch"));
                                        map.put("build_display_id", m25("ro.build.display.id"));
                                        if (z2) {
                                            if (appsFlyerLib.f37) {
                                                com.appsflyer.e eVar3 = com.appsflyer.e.d.f123;
                                                locationM119 = com.appsflyer.e.m119(context);
                                                map2 = new HashMap(3);
                                                if (locationM119 != null) {
                                                    map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                                    map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                                    map2.put("ts", String.valueOf(locationM119.getTime()));
                                                }
                                                if (!map2.isEmpty()) {
                                                    map.put("loc", map2);
                                                }
                                            }
                                            cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                            map.put("btl", Float.toString(cVarM96.m97()));
                                            if (cVarM96.m98() != null) {
                                                map.put("btch", cVarM96.m98());
                                            }
                                            if (2 >= iM24) {
                                                listM122 = f.m120(context).m122();
                                                if (!listM122.isEmpty()) {
                                                    map.put("sensors", listM122);
                                                }
                                            }
                                        }
                                        map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                                        map3.put("deviceData", map);
                                        new C0120r();
                                        Object obj9 = obj4;
                                        String str114 = (String) map3.get(obj9);
                                        Object obj10 = obj3;
                                        String str115 = (String) map3.get(obj10);
                                        String str116 = (String) map3.get("uid");
                                        StringBuilder sb111 = new StringBuilder();
                                        sb111.append(str114.substring(0, 7));
                                        sb111.append(str116.substring(0, 7));
                                        sb111.append(str115.substring(str115.length() - 7));
                                        map3.put("af_v", C0120r.m180(sb111.toString()));
                                        new C0120r();
                                        String str117 = (String) map3.get(obj9);
                                        StringBuilder sb112 = new StringBuilder();
                                        sb112.append(str117);
                                        sb112.append(map3.get(obj10));
                                        String string28 = sb112.toString();
                                        StringBuilder sb113 = new StringBuilder();
                                        sb113.append(string28);
                                        sb113.append(map3.get("uid"));
                                        String string29 = sb113.toString();
                                        StringBuilder sb114 = new StringBuilder();
                                        sb114.append(string29);
                                        sb114.append(map3.get("installDate"));
                                        String string210 = sb114.toString();
                                        StringBuilder sb115 = new StringBuilder();
                                        sb115.append(string210);
                                        sb115.append(map3.get("counter"));
                                        String string211 = sb115.toString();
                                        StringBuilder sb116 = new StringBuilder();
                                        sb116.append(string211);
                                        sb116.append(map3.get("iaecounter"));
                                        map3.put("af_v2", C0120r.m180(C0120r.m179(sb116.toString())));
                                        map3.put("ivc", Boolean.valueOf(m72(context)));
                                        if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                            map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                                        }
                                        if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                            map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                                        }
                                    }
                                } catch (Exception e12) {
                                    StringBuilder sb20 = new StringBuilder("ERROR: could not get uid ");
                                    sb20.append(e12.getMessage());
                                    AFLogger.afErrorLog(sb20.toString(), e12);
                                }
                            } catch (Throwable th8) {
                                th = th8;
                                Throwable th9 = th;
                                AFLogger.afErrorLog(th9.getLocalizedMessage(), th9);
                            }
                        } else {
                            string5 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.EXTENSION);
                            if (string5 != null) {
                                map3.put(AppsFlyerProperties.EXTENSION, string5);
                            }
                            strM61 = m61((WeakReference<Context>) new WeakReference(context));
                            strM74 = m74(context, strM61);
                            if (strM74 != null) {
                                map3.put(AppsFlyerProperties.CHANNEL, strM74);
                            }
                            if (strM74 == null) {
                                map3.put("af_latestchannel", strM61);
                            } else {
                                map3.put("af_latestchannel", strM61);
                            }
                            str10 = str8;
                            sharedPreferences3 = context.getSharedPreferences(str10, 0);
                            str11 = str6;
                            if (sharedPreferences3.contains(str11)) {
                                strM22 = sharedPreferences3.getString(str11, null);
                                str12 = str7;
                            } else {
                                str12 = str7;
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    strM22 = m22(context);
                                } else {
                                    strM22 = null;
                                }
                                m65(context, str11, strM22);
                            }
                            if (strM22 != null) {
                                map3.put("af_installstore", strM22.toLowerCase());
                            }
                            sharedPreferences4 = context.getSharedPreferences(str10, 0);
                            string6 = AppsFlyerProperties.getInstance().getString("preInstallName");
                            if (string6 == null) {
                                if (sharedPreferences4.contains("preInstallName")) {
                                    string6 = sharedPreferences4.getString("preInstallName", null);
                                } else {
                                    if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                        fileM42 = m42(m25(PRE_INSTALL_SYSTEM_RO_PROP));
                                        if (fileM42 == null) {
                                            z8 = true;
                                        } else {
                                            z8 = true;
                                        }
                                        if (z8) {
                                            fileM42 = m42(m31(AF_PRE_INSTALL_PATH, context.getPackageManager(), context.getPackageName()));
                                        }
                                        if (fileM42 == null) {
                                            z9 = true;
                                        } else {
                                            z9 = true;
                                        }
                                        if (z9) {
                                            fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT);
                                        }
                                        if (fileM42 == null) {
                                            z10 = true;
                                        } else {
                                            z10 = true;
                                        }
                                        if (z10) {
                                            fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT_ETC);
                                        }
                                        if (fileM42 == null) {
                                            z11 = true;
                                        } else {
                                            z11 = true;
                                        }
                                        if (z11) {
                                            string6 = null;
                                        } else {
                                            string6 = null;
                                        }
                                        if (string6 != null) {
                                            string6 = m68((WeakReference<Context>) new WeakReference(context), "AF_PRE_INSTALL_NAME");
                                        }
                                    }
                                    if (string6 != null) {
                                        m65(context, "preInstallName", string6);
                                    }
                                }
                                if (string6 != null) {
                                    AppsFlyerProperties.getInstance().set("preInstallName", string6);
                                }
                            }
                            if (string6 != null) {
                                map3.put("af_preinstall_name", string6.toLowerCase());
                            }
                            strM23 = m22(context);
                            if (strM23 != null) {
                                map3.put("af_currentstore", strM23.toLowerCase());
                            }
                            if (str == null) {
                                string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                                if (string7 == null) {
                                }
                                AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                                m66(context, LOG_TAG, "DEV_KEY_MISSING", null);
                                AFLogger.afInfoLog("AppsFlyer will not track this event.");
                                return null;
                            }
                            string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                            if (string7 == null) {
                            }
                            AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                            m66(context, LOG_TAG, "DEV_KEY_MISSING", null);
                            AFLogger.afInfoLog("AppsFlyer will not track this event.");
                            return null;
                            string8 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
                            if (string8 != null) {
                                map3.put("appUserId", string8);
                            }
                            string9 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAILS);
                            if (string9 != null) {
                                map3.put("user_emails", string9);
                            } else {
                                string10 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAIL);
                                if (string10 != null) {
                                    map3.put("sha1_el", C0120r.m180(string10));
                                }
                            }
                            if (str2 != null) {
                                map3.put(com.ironsource.sdk.constants.Constants.ParametersKeys.EVENT_NAME, str2);
                                if (str3 != null) {
                                    map3.put("eventValue", str3);
                                }
                            }
                            str13 = str9;
                            if (AppsFlyerProperties.getInstance().getString(str13) != null) {
                                map3.put(str13, AppsFlyerProperties.getInstance().getString(str13));
                            }
                            string11 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CURRENCY_CODE);
                            if (string11 != null) {
                                if (string11.length() != 3) {
                                    StringBuilder sb21 = new StringBuilder("WARNING: currency code should be 3 characters!!! '");
                                    sb21.append(string11);
                                    sb21.append("' is not a legal value.");
                                    AFLogger.afWarnLog(sb21.toString());
                                }
                                map3.put("currency", string11);
                            }
                            string12 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.IS_UPDATE);
                            if (string12 != null) {
                                map3.put("isUpdate", string12);
                            }
                            map3.put("af_preinstalled", Boolean.toString(isPreInstalledApp(context)));
                            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true)) {
                                context.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                                appsFlyerLib = this;
                                obj2 = obj;
                                attributionId = appsFlyerLib.getAttributionId(context.getContentResolver());
                                if (attributionId != null) {
                                    map3.put("fb", attributionId);
                                }
                            } else {
                                appsFlyerLib = this;
                                obj2 = obj;
                            }
                            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
                                map3.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                obj3 = obj2;
                            } else {
                                SharedPreferences sharedPreferences7 = context.getSharedPreferences(str10, 0);
                                z3 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI, true);
                                String string110 = sharedPreferences7.getString("imeiCached", null);
                                if (z3) {
                                    obj3 = obj2;
                                    if (appsFlyerLib.f35 != null) {
                                        str14 = appsFlyerLib.f35;
                                    } else {
                                        str14 = null;
                                    }
                                } else {
                                    obj3 = obj2;
                                    if (appsFlyerLib.f35 != null) {
                                        str14 = appsFlyerLib.f35;
                                    } else {
                                        str14 = null;
                                    }
                                }
                                if (str14 != null) {
                                    m65(context, "imeiCached", str14);
                                    map3.put("imei", str14);
                                } else {
                                    AFLogger.afInfoLog("IMEI was not collected.");
                                }
                                z4 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID, true);
                                String string111 = sharedPreferences7.getString("androidIdCached", null);
                                if (z4) {
                                    if (appsFlyerLib.f45 != null) {
                                        string13 = appsFlyerLib.f45;
                                    } else {
                                        string13 = null;
                                    }
                                    if (string13 != null) {
                                        m65(context, "androidIdCached", string13);
                                        map3.put(com.my.target.i.ANDROID_ID, string13);
                                    } else {
                                        AFLogger.afInfoLog("Android ID was not collected.");
                                    }
                                } else {
                                    if (appsFlyerLib.f45 != null) {
                                        string13 = appsFlyerLib.f45;
                                    } else {
                                        string13 = null;
                                    }
                                    if (string13 != null) {
                                        m65(context, "androidIdCached", string13);
                                        map3.put(com.my.target.i.ANDROID_ID, string13);
                                    } else {
                                        AFLogger.afInfoLog("Android ID was not collected.");
                                    }
                                }
                            }
                            strM211 = t.m211((WeakReference<Context>) new WeakReference(context));
                            if (strM211 != null) {
                                map3.put("uid", strM211);
                                map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                                map3.put("lang_code", Locale.getDefault().getLanguage());
                                map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                                map3.put("platformextension", appsFlyerLib.f40.m172());
                                m50(context, map3);
                                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                                long j10 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                map3.put("installDate", simpleDateFormat.format(new Date(j10)));
                                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                sharedPreferences5 = sharedPreferences;
                                if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                    m48(context, "appsflyerConversionDataRequestRetries", 0);
                                    m48(context, "versionCode", packageInfo.versionCode);
                                }
                                map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                                map3.put("app_version_name", packageInfo.versionName);
                                long j11 = packageInfo.firstInstallTime;
                                long j12 = packageInfo.lastUpdateTime;
                                obj4 = "appsflyerKey";
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                map3.put("date1", simpleDateFormat.format(new Date(j11)));
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                map3.put("date2", simpleDateFormat.format(new Date(j12)));
                                string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                                if (string16 == null) {
                                    if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                        AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                        str15 = simpleDateFormat.format(new Date());
                                    } else {
                                        str15 = "";
                                    }
                                    string16 = str15;
                                    m65(context, "appsFlyerFirstInstall", string16);
                                }
                                AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                                map3.put("firstLaunchDate", string16);
                                if (str4.length() > 0) {
                                    map3.put("referrer", str4);
                                }
                                string14 = sharedPreferences5.getString("extraReferrers", null);
                                if (string14 != null) {
                                    map3.put("extraReferrers", string14);
                                }
                                string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                                if (string15 != null) {
                                    map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                                }
                                appsFlyerLib.f41 = u.m214(context);
                                StringBuilder sb117 = new StringBuilder("didConfigureTokenRefreshService=");
                                sb117.append(appsFlyerLib.f41);
                                AFLogger.afDebugLog(sb117.toString());
                                if (!appsFlyerLib.f41) {
                                    map3.put("tokenRefreshConfigured", Boolean.FALSE);
                                }
                                if (z2) {
                                    AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                    if (appsFlyerLib.f34 != null) {
                                        JSONObject jSONObject5 = new JSONObject(appsFlyerLib.f34);
                                        jSONObject5.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                        map3.put("af_deeplink", jSONObject5.toString());
                                    }
                                    appsFlyerLib.f34 = null;
                                    map3.put("open_referrer", str5);
                                }
                                if (appsFlyerLib.f39) {
                                    map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    m47(context, new JSONObject(map3).toString());
                                    AFLogger.afInfoLog("Sent retargeting params to test app");
                                }
                                jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                                String referrer5 = AppsFlyerProperties.getInstance().getReferrer(context);
                                if (jCurrentTimeMillis <= 30000) {
                                    z5 = false;
                                } else {
                                    z5 = false;
                                }
                                if (z5) {
                                    map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    m47(context, new JSONObject(map3).toString());
                                    AFLogger.afInfoLog("Sent params to test app");
                                    AFLogger.afInfoLog("Test mode ended!");
                                    appsFlyerLib.f38 = 0L;
                                }
                                if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                    l.m158(context, map3);
                                    if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                        map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    } else {
                                        map3.put("GAID_retry", "false");
                                    }
                                }
                                oVarM159 = l.m159(context.getContentResolver());
                                if (oVarM159 != null) {
                                    map3.put("amazon_aid", oVarM159.m168());
                                    map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                                }
                                referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                                if (referrer != null) {
                                    map3.put("referrer", referrer);
                                }
                                zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                                map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                                iM24 = m24(sharedPreferences5, str12, z2);
                                map3.put("counter", Integer.toString(iM24));
                                if (str2 != null) {
                                    z6 = true;
                                } else {
                                    z6 = false;
                                }
                                map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                                if (z2) {
                                    z7 = true;
                                } else {
                                    z7 = true;
                                }
                                if (zEquals) {
                                    z7 = false;
                                }
                                map3.put("isFirstCall", Boolean.toString(z7));
                                map = new HashMap();
                                map.put("cpu_abi", m25("ro.product.cpu.abi"));
                                map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                                map.put("arch", m25("os.arch"));
                                map.put("build_display_id", m25("ro.build.display.id"));
                                if (z2) {
                                    if (appsFlyerLib.f37) {
                                        com.appsflyer.e eVar4 = com.appsflyer.e.d.f123;
                                        locationM119 = com.appsflyer.e.m119(context);
                                        map2 = new HashMap(3);
                                        if (locationM119 != null) {
                                            map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                            map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                            map2.put("ts", String.valueOf(locationM119.getTime()));
                                        }
                                        if (!map2.isEmpty()) {
                                            map.put("loc", map2);
                                        }
                                    }
                                    cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                    map.put("btl", Float.toString(cVarM96.m97()));
                                    if (cVarM96.m98() != null) {
                                        map.put("btch", cVarM96.m98());
                                    }
                                    if (2 >= iM24) {
                                        listM122 = f.m120(context).m122();
                                        if (!listM122.isEmpty()) {
                                            map.put("sensors", listM122);
                                        }
                                    }
                                }
                                map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                                map3.put("deviceData", map);
                                new C0120r();
                                Object obj11 = obj4;
                                String str118 = (String) map3.get(obj11);
                                Object obj12 = obj3;
                                String str119 = (String) map3.get(obj12);
                                String str1110 = (String) map3.get("uid");
                                StringBuilder sb118 = new StringBuilder();
                                sb118.append(str118.substring(0, 7));
                                sb118.append(str1110.substring(0, 7));
                                sb118.append(str119.substring(str119.length() - 7));
                                map3.put("af_v", C0120r.m180(sb118.toString()));
                                new C0120r();
                                String str1111 = (String) map3.get(obj11);
                                StringBuilder sb119 = new StringBuilder();
                                sb119.append(str1111);
                                sb119.append(map3.get(obj12));
                                String string212 = sb119.toString();
                                StringBuilder sb1110 = new StringBuilder();
                                sb1110.append(string212);
                                sb1110.append(map3.get("uid"));
                                String string213 = sb1110.toString();
                                StringBuilder sb1111 = new StringBuilder();
                                sb1111.append(string213);
                                sb1111.append(map3.get("installDate"));
                                String string214 = sb1111.toString();
                                StringBuilder sb1112 = new StringBuilder();
                                sb1112.append(string214);
                                sb1112.append(map3.get("counter"));
                                String string215 = sb1112.toString();
                                StringBuilder sb1113 = new StringBuilder();
                                sb1113.append(string215);
                                sb1113.append(map3.get("iaecounter"));
                                map3.put("af_v2", C0120r.m180(C0120r.m179(sb1113.toString())));
                                map3.put("ivc", Boolean.valueOf(m72(context)));
                                if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                    map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                                }
                                if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                    map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                                }
                            } else {
                                map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                                map3.put("lang_code", Locale.getDefault().getLanguage());
                                map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                                map3.put("platformextension", appsFlyerLib.f40.m172());
                                m50(context, map3);
                                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                                long j13 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                map3.put("installDate", simpleDateFormat.format(new Date(j13)));
                                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                sharedPreferences5 = sharedPreferences;
                                if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                    m48(context, "appsflyerConversionDataRequestRetries", 0);
                                    m48(context, "versionCode", packageInfo.versionCode);
                                }
                                map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                                map3.put("app_version_name", packageInfo.versionName);
                                long j14 = packageInfo.firstInstallTime;
                                long j15 = packageInfo.lastUpdateTime;
                                obj4 = "appsflyerKey";
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                map3.put("date1", simpleDateFormat.format(new Date(j14)));
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                map3.put("date2", simpleDateFormat.format(new Date(j15)));
                                string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                                if (string16 == null) {
                                    if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                        AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                        str15 = simpleDateFormat.format(new Date());
                                    } else {
                                        str15 = "";
                                    }
                                    string16 = str15;
                                    m65(context, "appsFlyerFirstInstall", string16);
                                }
                                AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                                map3.put("firstLaunchDate", string16);
                                if (str4.length() > 0) {
                                    map3.put("referrer", str4);
                                }
                                string14 = sharedPreferences5.getString("extraReferrers", null);
                                if (string14 != null) {
                                    map3.put("extraReferrers", string14);
                                }
                                string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                                if (string15 != null) {
                                    map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                                }
                                appsFlyerLib.f41 = u.m214(context);
                                StringBuilder sb1114 = new StringBuilder("didConfigureTokenRefreshService=");
                                sb1114.append(appsFlyerLib.f41);
                                AFLogger.afDebugLog(sb1114.toString());
                                if (!appsFlyerLib.f41) {
                                    map3.put("tokenRefreshConfigured", Boolean.FALSE);
                                }
                                if (z2) {
                                    AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                    if (appsFlyerLib.f34 != null) {
                                        JSONObject jSONObject6 = new JSONObject(appsFlyerLib.f34);
                                        jSONObject6.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                        map3.put("af_deeplink", jSONObject6.toString());
                                    }
                                    appsFlyerLib.f34 = null;
                                    map3.put("open_referrer", str5);
                                }
                                if (appsFlyerLib.f39) {
                                    map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    m47(context, new JSONObject(map3).toString());
                                    AFLogger.afInfoLog("Sent retargeting params to test app");
                                }
                                jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                                String referrer6 = AppsFlyerProperties.getInstance().getReferrer(context);
                                if (jCurrentTimeMillis <= 30000) {
                                    z5 = false;
                                } else {
                                    z5 = false;
                                }
                                if (z5) {
                                    map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    m47(context, new JSONObject(map3).toString());
                                    AFLogger.afInfoLog("Sent params to test app");
                                    AFLogger.afInfoLog("Test mode ended!");
                                    appsFlyerLib.f38 = 0L;
                                }
                                if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                    l.m158(context, map3);
                                    if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                        map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    } else {
                                        map3.put("GAID_retry", "false");
                                    }
                                }
                                oVarM159 = l.m159(context.getContentResolver());
                                if (oVarM159 != null) {
                                    map3.put("amazon_aid", oVarM159.m168());
                                    map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                                }
                                referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                                if (referrer != null) {
                                    map3.put("referrer", referrer);
                                }
                                zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                                map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                                iM24 = m24(sharedPreferences5, str12, z2);
                                map3.put("counter", Integer.toString(iM24));
                                if (str2 != null) {
                                    z6 = true;
                                } else {
                                    z6 = false;
                                }
                                map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                                if (z2) {
                                    z7 = true;
                                } else {
                                    z7 = true;
                                }
                                if (zEquals) {
                                    z7 = false;
                                }
                                map3.put("isFirstCall", Boolean.toString(z7));
                                map = new HashMap();
                                map.put("cpu_abi", m25("ro.product.cpu.abi"));
                                map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                                map.put("arch", m25("os.arch"));
                                map.put("build_display_id", m25("ro.build.display.id"));
                                if (z2) {
                                    if (appsFlyerLib.f37) {
                                        com.appsflyer.e eVar5 = com.appsflyer.e.d.f123;
                                        locationM119 = com.appsflyer.e.m119(context);
                                        map2 = new HashMap(3);
                                        if (locationM119 != null) {
                                            map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                            map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                            map2.put("ts", String.valueOf(locationM119.getTime()));
                                        }
                                        if (!map2.isEmpty()) {
                                            map.put("loc", map2);
                                        }
                                    }
                                    cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                    map.put("btl", Float.toString(cVarM96.m97()));
                                    if (cVarM96.m98() != null) {
                                        map.put("btch", cVarM96.m98());
                                    }
                                    if (2 >= iM24) {
                                        listM122 = f.m120(context).m122();
                                        if (!listM122.isEmpty()) {
                                            map.put("sensors", listM122);
                                        }
                                    }
                                }
                                map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                                map3.put("deviceData", map);
                                new C0120r();
                                Object obj13 = obj4;
                                String str1112 = (String) map3.get(obj13);
                                Object obj14 = obj3;
                                String str1113 = (String) map3.get(obj14);
                                String str1114 = (String) map3.get("uid");
                                StringBuilder sb1115 = new StringBuilder();
                                sb1115.append(str1112.substring(0, 7));
                                sb1115.append(str1114.substring(0, 7));
                                sb1115.append(str1113.substring(str1113.length() - 7));
                                map3.put("af_v", C0120r.m180(sb1115.toString()));
                                new C0120r();
                                String str1115 = (String) map3.get(obj13);
                                StringBuilder sb1116 = new StringBuilder();
                                sb1116.append(str1115);
                                sb1116.append(map3.get(obj14));
                                String string216 = sb1116.toString();
                                StringBuilder sb1117 = new StringBuilder();
                                sb1117.append(string216);
                                sb1117.append(map3.get("uid"));
                                String string217 = sb1117.toString();
                                StringBuilder sb1118 = new StringBuilder();
                                sb1118.append(string217);
                                sb1118.append(map3.get("installDate"));
                                String string218 = sb1118.toString();
                                StringBuilder sb1119 = new StringBuilder();
                                sb1119.append(string218);
                                sb1119.append(map3.get("counter"));
                                String string219 = sb1119.toString();
                                StringBuilder sb11110 = new StringBuilder();
                                sb11110.append(string219);
                                sb11110.append(map3.get("iaecounter"));
                                map3.put("af_v2", C0120r.m180(C0120r.m179(sb11110.toString())));
                                map3.put("ivc", Boolean.valueOf(m72(context)));
                                if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                    map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                                }
                                if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                    map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                                }
                            }
                        }
                    } catch (Exception e13) {
                        AFLogger.afErrorLog("Exception while getting the app's installer package. ", e13);
                    }
                } else {
                    if (z) {
                        map3.put("af_events_api", "1");
                    }
                    map3.put("brand", Build.BRAND);
                    map3.put("device", Build.DEVICE);
                    map3.put(AppLovinEventTypes.USER_VIEWED_PRODUCT, Build.PRODUCT);
                    map3.put("sdk", Integer.toString(Build.VERSION.SDK_INT));
                    map3.put("model", Build.MODEL);
                    map3.put("deviceType", Build.TYPE);
                    map3.put("deviceRm", Build.DISPLAY);
                    if (z2) {
                        if (!context.getSharedPreferences("appsflyer-data", 0).contains("appsFlyerCount")) {
                            if (!AppsFlyerProperties.getInstance().isOtherSdkStringDisabled()) {
                                map3.put(com.ironsource.sdk.constants.Constants.RequestParameters.BATTERY_LEVEL, String.valueOf(m73(context)));
                            }
                            if ("OPPO".equals(Build.BRAND)) {
                                i = 23;
                                AFLogger.afRDLog("OPPO device found");
                            } else {
                                i = 18;
                            }
                            if (Build.VERSION.SDK_INT >= i) {
                                obj = "af_timestamp";
                                if (!AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DISABLE_KEYSTORE, false)) {
                                    StringBuilder sb22 = new StringBuilder("OS SDK is=");
                                    sb22.append(Build.VERSION.SDK_INT);
                                    sb22.append("; use KeyStore");
                                    AFLogger.afRDLog(sb22.toString());
                                    aFKeystoreWrapper = new AFKeystoreWrapper(context);
                                    if (aFKeystoreWrapper.m12()) {
                                        aFKeystoreWrapper.m11(t.m211((WeakReference<Context>) new WeakReference(context)));
                                    } else {
                                        aFKeystoreWrapper.m14();
                                    }
                                    AppsFlyerProperties.getInstance().set("KSAppsFlyerId", aFKeystoreWrapper.m15());
                                    AppsFlyerProperties.getInstance().set("KSAppsFlyerRICounter", String.valueOf(aFKeystoreWrapper.m13()));
                                }
                            } else {
                                obj = "af_timestamp";
                            }
                            StringBuilder sb23 = new StringBuilder("OS SDK is=");
                            sb23.append(Build.VERSION.SDK_INT);
                            sb23.append("; no KeyStore usage");
                            AFLogger.afRDLog(sb23.toString());
                        } else {
                            obj = "af_timestamp";
                        }
                        str6 = "INSTALL_STORE";
                        j = context.getSharedPreferences("appsflyer-data", 0).getLong("AppsFlyerTimePassedSincePrevLaunch", 0L);
                        jCurrentTimeMillis2 = System.currentTimeMillis();
                        m64(context, "AppsFlyerTimePassedSincePrevLaunch", jCurrentTimeMillis2);
                        if (j > 0) {
                            j2 = (jCurrentTimeMillis2 - j) / 1000;
                        } else {
                            j2 = -1;
                        }
                        map3.put("timepassedsincelastlaunch", Long.toString(j2));
                        string17 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
                        if (string17 != null) {
                            map3.put("onelink_id", string17);
                            map3.put("ol_ver", AppsFlyerProperties.getInstance().getString("onelinkVersion"));
                        }
                        j3 = sharedPreferences.getLong("appsflyerGetConversionDataTiming", 0L);
                        if (j3 > 0) {
                            map3.put("gcd_timing", Long.toString(j3));
                            editorEdit2 = sharedPreferences.edit();
                            editorEdit2.putLong("appsflyerGetConversionDataTiming", 0L);
                            if (Build.VERSION.SDK_INT >= 9) {
                                editorEdit2.apply();
                            } else {
                                editorEdit2.commit();
                            }
                        }
                        str7 = "appsFlyerCount";
                        str8 = "appsflyer-data";
                        str9 = AppsFlyerProperties.APP_ID;
                    } else {
                        obj = "af_timestamp";
                        str6 = "INSTALL_STORE";
                        sharedPreferences2 = context.getSharedPreferences("appsflyer-data", 0);
                        editorEdit = sharedPreferences2.edit();
                        string = sharedPreferences2.getString("prev_event_name", null);
                        str9 = AppsFlyerProperties.APP_ID;
                        if (string != null) {
                            JSONObject jSONObject7 = new JSONObject();
                            StringBuilder sb24 = new StringBuilder();
                            str7 = "appsFlyerCount";
                            str8 = "appsflyer-data";
                            sb24.append(sharedPreferences2.getLong("prev_event_timestamp", -1L));
                            jSONObject7.put("prev_event_timestamp", sb24.toString());
                            jSONObject7.put("prev_event_value", sharedPreferences2.getString("prev_event_value", null));
                            jSONObject7.put("prev_event_name", string);
                            map3.put("prev_event", jSONObject7.toString());
                        } else {
                            str7 = "appsFlyerCount";
                            str8 = "appsflyer-data";
                        }
                        editorEdit.putString("prev_event_name", str2);
                        editorEdit.putString("prev_event_value", str3);
                        editorEdit.putLong("prev_event_timestamp", System.currentTimeMillis());
                        if (Build.VERSION.SDK_INT >= 9) {
                            editorEdit.apply();
                        } else {
                            editorEdit.commit();
                        }
                    }
                    string2 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
                    string3 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerRICounter");
                    if (string2 != null) {
                        map3.put("reinstallCounter", string3);
                        map3.put("originalAppsflyerId", string2);
                    }
                    string4 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ADDITIONAL_CUSTOM_DATA);
                    if (string4 != null) {
                        map3.put("customData", string4);
                    }
                    installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                    if (installerPackageName != null) {
                        map3.put("installer_package", installerPackageName);
                        string5 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.EXTENSION);
                        if (string5 != null) {
                            map3.put(AppsFlyerProperties.EXTENSION, string5);
                        }
                        strM61 = m61((WeakReference<Context>) new WeakReference(context));
                        strM74 = m74(context, strM61);
                        if (strM74 != null) {
                            map3.put(AppsFlyerProperties.CHANNEL, strM74);
                        }
                        if (strM74 == null) {
                            map3.put("af_latestchannel", strM61);
                        } else {
                            map3.put("af_latestchannel", strM61);
                        }
                        str10 = str8;
                        sharedPreferences3 = context.getSharedPreferences(str10, 0);
                        str11 = str6;
                        if (sharedPreferences3.contains(str11)) {
                            strM22 = sharedPreferences3.getString(str11, null);
                            str12 = str7;
                        } else {
                            str12 = str7;
                            if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                strM22 = m22(context);
                            } else {
                                strM22 = null;
                            }
                            m65(context, str11, strM22);
                        }
                        if (strM22 != null) {
                            map3.put("af_installstore", strM22.toLowerCase());
                        }
                        sharedPreferences4 = context.getSharedPreferences(str10, 0);
                        string6 = AppsFlyerProperties.getInstance().getString("preInstallName");
                        if (string6 == null) {
                            if (sharedPreferences4.contains("preInstallName")) {
                                string6 = sharedPreferences4.getString("preInstallName", null);
                            } else {
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    fileM42 = m42(m25(PRE_INSTALL_SYSTEM_RO_PROP));
                                    if (fileM42 == null) {
                                        z8 = true;
                                    } else {
                                        z8 = true;
                                    }
                                    if (z8) {
                                        fileM42 = m42(m31(AF_PRE_INSTALL_PATH, context.getPackageManager(), context.getPackageName()));
                                    }
                                    if (fileM42 == null) {
                                        z9 = true;
                                    } else {
                                        z9 = true;
                                    }
                                    if (z9) {
                                        fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT);
                                    }
                                    if (fileM42 == null) {
                                        z10 = true;
                                    } else {
                                        z10 = true;
                                    }
                                    if (z10) {
                                        fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT_ETC);
                                    }
                                    if (fileM42 == null) {
                                        z11 = true;
                                    } else {
                                        z11 = true;
                                    }
                                    if (z11) {
                                        string6 = null;
                                    } else {
                                        string6 = null;
                                    }
                                    if (string6 != null) {
                                        string6 = m68((WeakReference<Context>) new WeakReference(context), "AF_PRE_INSTALL_NAME");
                                    }
                                }
                                if (string6 != null) {
                                    m65(context, "preInstallName", string6);
                                }
                            }
                            if (string6 != null) {
                                AppsFlyerProperties.getInstance().set("preInstallName", string6);
                            }
                        }
                        if (string6 != null) {
                            map3.put("af_preinstall_name", string6.toLowerCase());
                        }
                        strM23 = m22(context);
                        if (strM23 != null) {
                            map3.put("af_currentstore", strM23.toLowerCase());
                        }
                        if (str == null) {
                            string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                            if (string7 == null) {
                            }
                            AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                            m66(context, LOG_TAG, "DEV_KEY_MISSING", null);
                            AFLogger.afInfoLog("AppsFlyer will not track this event.");
                            return null;
                        }
                        string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                        if (string7 == null) {
                        }
                        AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                        m66(context, LOG_TAG, "DEV_KEY_MISSING", null);
                        AFLogger.afInfoLog("AppsFlyer will not track this event.");
                        return null;
                        string8 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
                        if (string8 != null) {
                            map3.put("appUserId", string8);
                        }
                        string9 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAILS);
                        if (string9 != null) {
                            map3.put("user_emails", string9);
                        } else {
                            string10 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAIL);
                            if (string10 != null) {
                                map3.put("sha1_el", C0120r.m180(string10));
                            }
                        }
                        if (str2 != null) {
                            map3.put(com.ironsource.sdk.constants.Constants.ParametersKeys.EVENT_NAME, str2);
                            if (str3 != null) {
                                map3.put("eventValue", str3);
                            }
                        }
                        str13 = str9;
                        if (AppsFlyerProperties.getInstance().getString(str13) != null) {
                            map3.put(str13, AppsFlyerProperties.getInstance().getString(str13));
                        }
                        string11 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CURRENCY_CODE);
                        if (string11 != null) {
                            if (string11.length() != 3) {
                                StringBuilder sb25 = new StringBuilder("WARNING: currency code should be 3 characters!!! '");
                                sb25.append(string11);
                                sb25.append("' is not a legal value.");
                                AFLogger.afWarnLog(sb25.toString());
                            }
                            map3.put("currency", string11);
                        }
                        string12 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.IS_UPDATE);
                        if (string12 != null) {
                            map3.put("isUpdate", string12);
                        }
                        map3.put("af_preinstalled", Boolean.toString(isPreInstalledApp(context)));
                        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true)) {
                            context.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                            appsFlyerLib = this;
                            obj2 = obj;
                            attributionId = appsFlyerLib.getAttributionId(context.getContentResolver());
                            if (attributionId != null) {
                                map3.put("fb", attributionId);
                            }
                        } else {
                            appsFlyerLib = this;
                            obj2 = obj;
                        }
                        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
                            map3.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                            obj3 = obj2;
                        } else {
                            SharedPreferences sharedPreferences8 = context.getSharedPreferences(str10, 0);
                            z3 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI, true);
                            String string112 = sharedPreferences8.getString("imeiCached", null);
                            if (z3) {
                                obj3 = obj2;
                                if (appsFlyerLib.f35 != null) {
                                    str14 = appsFlyerLib.f35;
                                } else {
                                    str14 = null;
                                }
                            } else {
                                obj3 = obj2;
                                if (appsFlyerLib.f35 != null) {
                                    str14 = appsFlyerLib.f35;
                                } else {
                                    str14 = null;
                                }
                            }
                            if (str14 != null) {
                                m65(context, "imeiCached", str14);
                                map3.put("imei", str14);
                            } else {
                                AFLogger.afInfoLog("IMEI was not collected.");
                            }
                            z4 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID, true);
                            String string113 = sharedPreferences8.getString("androidIdCached", null);
                            if (z4) {
                                if (appsFlyerLib.f45 != null) {
                                    string13 = appsFlyerLib.f45;
                                } else {
                                    string13 = null;
                                }
                                if (string13 != null) {
                                    m65(context, "androidIdCached", string13);
                                    map3.put(com.my.target.i.ANDROID_ID, string13);
                                } else {
                                    AFLogger.afInfoLog("Android ID was not collected.");
                                }
                            } else {
                                if (appsFlyerLib.f45 != null) {
                                    string13 = appsFlyerLib.f45;
                                } else {
                                    string13 = null;
                                }
                                if (string13 != null) {
                                    m65(context, "androidIdCached", string13);
                                    map3.put(com.my.target.i.ANDROID_ID, string13);
                                } else {
                                    AFLogger.afInfoLog("Android ID was not collected.");
                                }
                            }
                        }
                        strM211 = t.m211((WeakReference<Context>) new WeakReference(context));
                        if (strM211 != null) {
                            map3.put("uid", strM211);
                            map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                            map3.put("lang_code", Locale.getDefault().getLanguage());
                            map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                            map3.put("platformextension", appsFlyerLib.f40.m172());
                            m50(context, map3);
                            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            long j16 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("installDate", simpleDateFormat.format(new Date(j16)));
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            sharedPreferences5 = sharedPreferences;
                            if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                m48(context, "appsflyerConversionDataRequestRetries", 0);
                                m48(context, "versionCode", packageInfo.versionCode);
                            }
                            map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            map3.put("app_version_name", packageInfo.versionName);
                            long j17 = packageInfo.firstInstallTime;
                            long j18 = packageInfo.lastUpdateTime;
                            obj4 = "appsflyerKey";
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date1", simpleDateFormat.format(new Date(j17)));
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date2", simpleDateFormat.format(new Date(j18)));
                            string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                            if (string16 == null) {
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                    str15 = simpleDateFormat.format(new Date());
                                } else {
                                    str15 = "";
                                }
                                string16 = str15;
                                m65(context, "appsFlyerFirstInstall", string16);
                            }
                            AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                            map3.put("firstLaunchDate", string16);
                            if (str4.length() > 0) {
                                map3.put("referrer", str4);
                            }
                            string14 = sharedPreferences5.getString("extraReferrers", null);
                            if (string14 != null) {
                                map3.put("extraReferrers", string14);
                            }
                            string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                            if (string15 != null) {
                                map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                            }
                            appsFlyerLib.f41 = u.m214(context);
                            StringBuilder sb11111 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb11111.append(appsFlyerLib.f41);
                            AFLogger.afDebugLog(sb11111.toString());
                            if (!appsFlyerLib.f41) {
                                map3.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (z2) {
                                AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                if (appsFlyerLib.f34 != null) {
                                    JSONObject jSONObject8 = new JSONObject(appsFlyerLib.f34);
                                    jSONObject8.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    map3.put("af_deeplink", jSONObject8.toString());
                                }
                                appsFlyerLib.f34 = null;
                                map3.put("open_referrer", str5);
                            }
                            if (appsFlyerLib.f39) {
                                map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent retargeting params to test app");
                            }
                            jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                            String referrer7 = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (jCurrentTimeMillis <= 30000) {
                                z5 = false;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent params to test app");
                                AFLogger.afInfoLog("Test mode ended!");
                                appsFlyerLib.f38 = 0L;
                            }
                            if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                l.m158(context, map3);
                                if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                    map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                } else {
                                    map3.put("GAID_retry", "false");
                                }
                            }
                            oVarM159 = l.m159(context.getContentResolver());
                            if (oVarM159 != null) {
                                map3.put("amazon_aid", oVarM159.m168());
                                map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                            }
                            referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (referrer != null) {
                                map3.put("referrer", referrer);
                            }
                            zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                            map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                            iM24 = m24(sharedPreferences5, str12, z2);
                            map3.put("counter", Integer.toString(iM24));
                            if (str2 != null) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                            if (z2) {
                                z7 = true;
                            } else {
                                z7 = true;
                            }
                            if (zEquals) {
                                z7 = false;
                            }
                            map3.put("isFirstCall", Boolean.toString(z7));
                            map = new HashMap();
                            map.put("cpu_abi", m25("ro.product.cpu.abi"));
                            map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                            map.put("arch", m25("os.arch"));
                            map.put("build_display_id", m25("ro.build.display.id"));
                            if (z2) {
                                if (appsFlyerLib.f37) {
                                    com.appsflyer.e eVar6 = com.appsflyer.e.d.f123;
                                    locationM119 = com.appsflyer.e.m119(context);
                                    map2 = new HashMap(3);
                                    if (locationM119 != null) {
                                        map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                        map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                        map2.put("ts", String.valueOf(locationM119.getTime()));
                                    }
                                    if (!map2.isEmpty()) {
                                        map.put("loc", map2);
                                    }
                                }
                                cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                map.put("btl", Float.toString(cVarM96.m97()));
                                if (cVarM96.m98() != null) {
                                    map.put("btch", cVarM96.m98());
                                }
                                if (2 >= iM24) {
                                    listM122 = f.m120(context).m122();
                                    if (!listM122.isEmpty()) {
                                        map.put("sensors", listM122);
                                    }
                                }
                            }
                            map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                            map3.put("deviceData", map);
                            new C0120r();
                            Object obj15 = obj4;
                            String str1116 = (String) map3.get(obj15);
                            Object obj16 = obj3;
                            String str1117 = (String) map3.get(obj16);
                            String str1118 = (String) map3.get("uid");
                            StringBuilder sb11112 = new StringBuilder();
                            sb11112.append(str1116.substring(0, 7));
                            sb11112.append(str1118.substring(0, 7));
                            sb11112.append(str1117.substring(str1117.length() - 7));
                            map3.put("af_v", C0120r.m180(sb11112.toString()));
                            new C0120r();
                            String str1119 = (String) map3.get(obj15);
                            StringBuilder sb11113 = new StringBuilder();
                            sb11113.append(str1119);
                            sb11113.append(map3.get(obj16));
                            String string2110 = sb11113.toString();
                            StringBuilder sb11114 = new StringBuilder();
                            sb11114.append(string2110);
                            sb11114.append(map3.get("uid"));
                            String string2111 = sb11114.toString();
                            StringBuilder sb11115 = new StringBuilder();
                            sb11115.append(string2111);
                            sb11115.append(map3.get("installDate"));
                            String string2112 = sb11115.toString();
                            StringBuilder sb11116 = new StringBuilder();
                            sb11116.append(string2112);
                            sb11116.append(map3.get("counter"));
                            String string2113 = sb11116.toString();
                            StringBuilder sb11117 = new StringBuilder();
                            sb11117.append(string2113);
                            sb11117.append(map3.get("iaecounter"));
                            map3.put("af_v2", C0120r.m180(C0120r.m179(sb11117.toString())));
                            map3.put("ivc", Boolean.valueOf(m72(context)));
                            if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                            }
                            if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                            }
                        } else {
                            map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                            map3.put("lang_code", Locale.getDefault().getLanguage());
                            map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                            map3.put("platformextension", appsFlyerLib.f40.m172());
                            m50(context, map3);
                            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            long j19 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("installDate", simpleDateFormat.format(new Date(j19)));
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            sharedPreferences5 = sharedPreferences;
                            if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                m48(context, "appsflyerConversionDataRequestRetries", 0);
                                m48(context, "versionCode", packageInfo.versionCode);
                            }
                            map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            map3.put("app_version_name", packageInfo.versionName);
                            long j110 = packageInfo.firstInstallTime;
                            long j111 = packageInfo.lastUpdateTime;
                            obj4 = "appsflyerKey";
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date1", simpleDateFormat.format(new Date(j110)));
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date2", simpleDateFormat.format(new Date(j111)));
                            string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                            if (string16 == null) {
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                    str15 = simpleDateFormat.format(new Date());
                                } else {
                                    str15 = "";
                                }
                                string16 = str15;
                                m65(context, "appsFlyerFirstInstall", string16);
                            }
                            AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                            map3.put("firstLaunchDate", string16);
                            if (str4.length() > 0) {
                                map3.put("referrer", str4);
                            }
                            string14 = sharedPreferences5.getString("extraReferrers", null);
                            if (string14 != null) {
                                map3.put("extraReferrers", string14);
                            }
                            string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                            if (string15 != null) {
                                map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                            }
                            appsFlyerLib.f41 = u.m214(context);
                            StringBuilder sb11118 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb11118.append(appsFlyerLib.f41);
                            AFLogger.afDebugLog(sb11118.toString());
                            if (!appsFlyerLib.f41) {
                                map3.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (z2) {
                                AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                if (appsFlyerLib.f34 != null) {
                                    JSONObject jSONObject9 = new JSONObject(appsFlyerLib.f34);
                                    jSONObject9.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    map3.put("af_deeplink", jSONObject9.toString());
                                }
                                appsFlyerLib.f34 = null;
                                map3.put("open_referrer", str5);
                            }
                            if (appsFlyerLib.f39) {
                                map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent retargeting params to test app");
                            }
                            jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                            String referrer8 = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (jCurrentTimeMillis <= 30000) {
                                z5 = false;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent params to test app");
                                AFLogger.afInfoLog("Test mode ended!");
                                appsFlyerLib.f38 = 0L;
                            }
                            if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                l.m158(context, map3);
                                if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                    map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                } else {
                                    map3.put("GAID_retry", "false");
                                }
                            }
                            oVarM159 = l.m159(context.getContentResolver());
                            if (oVarM159 != null) {
                                map3.put("amazon_aid", oVarM159.m168());
                                map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                            }
                            referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (referrer != null) {
                                map3.put("referrer", referrer);
                            }
                            zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                            map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                            iM24 = m24(sharedPreferences5, str12, z2);
                            map3.put("counter", Integer.toString(iM24));
                            if (str2 != null) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                            if (z2) {
                                z7 = true;
                            } else {
                                z7 = true;
                            }
                            if (zEquals) {
                                z7 = false;
                            }
                            map3.put("isFirstCall", Boolean.toString(z7));
                            map = new HashMap();
                            map.put("cpu_abi", m25("ro.product.cpu.abi"));
                            map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                            map.put("arch", m25("os.arch"));
                            map.put("build_display_id", m25("ro.build.display.id"));
                            if (z2) {
                                if (appsFlyerLib.f37) {
                                    com.appsflyer.e eVar7 = com.appsflyer.e.d.f123;
                                    locationM119 = com.appsflyer.e.m119(context);
                                    map2 = new HashMap(3);
                                    if (locationM119 != null) {
                                        map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                        map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                        map2.put("ts", String.valueOf(locationM119.getTime()));
                                    }
                                    if (!map2.isEmpty()) {
                                        map.put("loc", map2);
                                    }
                                }
                                cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                map.put("btl", Float.toString(cVarM96.m97()));
                                if (cVarM96.m98() != null) {
                                    map.put("btch", cVarM96.m98());
                                }
                                if (2 >= iM24) {
                                    listM122 = f.m120(context).m122();
                                    if (!listM122.isEmpty()) {
                                        map.put("sensors", listM122);
                                    }
                                }
                            }
                            map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                            map3.put("deviceData", map);
                            new C0120r();
                            Object obj17 = obj4;
                            String str11110 = (String) map3.get(obj17);
                            Object obj18 = obj3;
                            String str11111 = (String) map3.get(obj18);
                            String str11112 = (String) map3.get("uid");
                            StringBuilder sb11119 = new StringBuilder();
                            sb11119.append(str11110.substring(0, 7));
                            sb11119.append(str11112.substring(0, 7));
                            sb11119.append(str11111.substring(str11111.length() - 7));
                            map3.put("af_v", C0120r.m180(sb11119.toString()));
                            new C0120r();
                            String str11113 = (String) map3.get(obj17);
                            StringBuilder sb111110 = new StringBuilder();
                            sb111110.append(str11113);
                            sb111110.append(map3.get(obj18));
                            String string2114 = sb111110.toString();
                            StringBuilder sb111111 = new StringBuilder();
                            sb111111.append(string2114);
                            sb111111.append(map3.get("uid"));
                            String string2115 = sb111111.toString();
                            StringBuilder sb111112 = new StringBuilder();
                            sb111112.append(string2115);
                            sb111112.append(map3.get("installDate"));
                            String string2116 = sb111112.toString();
                            StringBuilder sb111113 = new StringBuilder();
                            sb111113.append(string2116);
                            sb111113.append(map3.get("counter"));
                            String string2117 = sb111113.toString();
                            StringBuilder sb111114 = new StringBuilder();
                            sb111114.append(string2117);
                            sb111114.append(map3.get("iaecounter"));
                            map3.put("af_v2", C0120r.m180(C0120r.m179(sb111114.toString())));
                            map3.put("ivc", Boolean.valueOf(m72(context)));
                            if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                            }
                            if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                            }
                        }
                    } else {
                        string5 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.EXTENSION);
                        if (string5 != null) {
                            map3.put(AppsFlyerProperties.EXTENSION, string5);
                        }
                        strM61 = m61((WeakReference<Context>) new WeakReference(context));
                        strM74 = m74(context, strM61);
                        if (strM74 != null) {
                            map3.put(AppsFlyerProperties.CHANNEL, strM74);
                        }
                        if (strM74 == null) {
                            map3.put("af_latestchannel", strM61);
                        } else {
                            map3.put("af_latestchannel", strM61);
                        }
                        str10 = str8;
                        sharedPreferences3 = context.getSharedPreferences(str10, 0);
                        str11 = str6;
                        if (sharedPreferences3.contains(str11)) {
                            strM22 = sharedPreferences3.getString(str11, null);
                            str12 = str7;
                        } else {
                            str12 = str7;
                            if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                strM22 = m22(context);
                            } else {
                                strM22 = null;
                            }
                            m65(context, str11, strM22);
                        }
                        if (strM22 != null) {
                            map3.put("af_installstore", strM22.toLowerCase());
                        }
                        sharedPreferences4 = context.getSharedPreferences(str10, 0);
                        string6 = AppsFlyerProperties.getInstance().getString("preInstallName");
                        if (string6 == null) {
                            if (sharedPreferences4.contains("preInstallName")) {
                                string6 = sharedPreferences4.getString("preInstallName", null);
                            } else {
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    fileM42 = m42(m25(PRE_INSTALL_SYSTEM_RO_PROP));
                                    if (fileM42 == null) {
                                        z8 = true;
                                    } else {
                                        z8 = true;
                                    }
                                    if (z8) {
                                        fileM42 = m42(m31(AF_PRE_INSTALL_PATH, context.getPackageManager(), context.getPackageName()));
                                    }
                                    if (fileM42 == null) {
                                        z9 = true;
                                    } else {
                                        z9 = true;
                                    }
                                    if (z9) {
                                        fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT);
                                    }
                                    if (fileM42 == null) {
                                        z10 = true;
                                    } else {
                                        z10 = true;
                                    }
                                    if (z10) {
                                        fileM42 = m42(PRE_INSTALL_SYSTEM_DEFAULT_ETC);
                                    }
                                    if (fileM42 == null) {
                                        z11 = true;
                                    } else {
                                        z11 = true;
                                    }
                                    if (z11) {
                                        string6 = null;
                                    } else {
                                        string6 = null;
                                    }
                                    if (string6 != null) {
                                        string6 = m68((WeakReference<Context>) new WeakReference(context), "AF_PRE_INSTALL_NAME");
                                    }
                                }
                                if (string6 != null) {
                                    m65(context, "preInstallName", string6);
                                }
                            }
                            if (string6 != null) {
                                AppsFlyerProperties.getInstance().set("preInstallName", string6);
                            }
                        }
                        if (string6 != null) {
                            map3.put("af_preinstall_name", string6.toLowerCase());
                        }
                        strM23 = m22(context);
                        if (strM23 != null) {
                            map3.put("af_currentstore", strM23.toLowerCase());
                        }
                        if (str == null) {
                            string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                            if (string7 == null) {
                            }
                            AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                            m66(context, LOG_TAG, "DEV_KEY_MISSING", null);
                            AFLogger.afInfoLog("AppsFlyer will not track this event.");
                            return null;
                        }
                        string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                        if (string7 == null) {
                        }
                        AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                        m66(context, LOG_TAG, "DEV_KEY_MISSING", null);
                        AFLogger.afInfoLog("AppsFlyer will not track this event.");
                        return null;
                        string8 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
                        if (string8 != null) {
                            map3.put("appUserId", string8);
                        }
                        string9 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAILS);
                        if (string9 != null) {
                            map3.put("user_emails", string9);
                        } else {
                            string10 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAIL);
                            if (string10 != null) {
                                map3.put("sha1_el", C0120r.m180(string10));
                            }
                        }
                        if (str2 != null) {
                            map3.put(com.ironsource.sdk.constants.Constants.ParametersKeys.EVENT_NAME, str2);
                            if (str3 != null) {
                                map3.put("eventValue", str3);
                            }
                        }
                        str13 = str9;
                        if (AppsFlyerProperties.getInstance().getString(str13) != null) {
                            map3.put(str13, AppsFlyerProperties.getInstance().getString(str13));
                        }
                        string11 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CURRENCY_CODE);
                        if (string11 != null) {
                            if (string11.length() != 3) {
                                StringBuilder sb26 = new StringBuilder("WARNING: currency code should be 3 characters!!! '");
                                sb26.append(string11);
                                sb26.append("' is not a legal value.");
                                AFLogger.afWarnLog(sb26.toString());
                            }
                            map3.put("currency", string11);
                        }
                        string12 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.IS_UPDATE);
                        if (string12 != null) {
                            map3.put("isUpdate", string12);
                        }
                        map3.put("af_preinstalled", Boolean.toString(isPreInstalledApp(context)));
                        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true)) {
                            context.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                            appsFlyerLib = this;
                            obj2 = obj;
                            attributionId = appsFlyerLib.getAttributionId(context.getContentResolver());
                            if (attributionId != null) {
                                map3.put("fb", attributionId);
                            }
                        } else {
                            appsFlyerLib = this;
                            obj2 = obj;
                        }
                        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
                            map3.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                            obj3 = obj2;
                        } else {
                            SharedPreferences sharedPreferences9 = context.getSharedPreferences(str10, 0);
                            z3 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI, true);
                            String string114 = sharedPreferences9.getString("imeiCached", null);
                            if (z3) {
                                obj3 = obj2;
                                if (appsFlyerLib.f35 != null) {
                                    str14 = appsFlyerLib.f35;
                                } else {
                                    str14 = null;
                                }
                            } else {
                                obj3 = obj2;
                                if (appsFlyerLib.f35 != null) {
                                    str14 = appsFlyerLib.f35;
                                } else {
                                    str14 = null;
                                }
                            }
                            if (str14 != null) {
                                m65(context, "imeiCached", str14);
                                map3.put("imei", str14);
                            } else {
                                AFLogger.afInfoLog("IMEI was not collected.");
                            }
                            z4 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID, true);
                            String string115 = sharedPreferences9.getString("androidIdCached", null);
                            if (z4) {
                                if (appsFlyerLib.f45 != null) {
                                    string13 = appsFlyerLib.f45;
                                } else {
                                    string13 = null;
                                }
                                if (string13 != null) {
                                    m65(context, "androidIdCached", string13);
                                    map3.put(com.my.target.i.ANDROID_ID, string13);
                                } else {
                                    AFLogger.afInfoLog("Android ID was not collected.");
                                }
                            } else {
                                if (appsFlyerLib.f45 != null) {
                                    string13 = appsFlyerLib.f45;
                                } else {
                                    string13 = null;
                                }
                                if (string13 != null) {
                                    m65(context, "androidIdCached", string13);
                                    map3.put(com.my.target.i.ANDROID_ID, string13);
                                } else {
                                    AFLogger.afInfoLog("Android ID was not collected.");
                                }
                            }
                        }
                        strM211 = t.m211((WeakReference<Context>) new WeakReference(context));
                        if (strM211 != null) {
                            map3.put("uid", strM211);
                            map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                            map3.put("lang_code", Locale.getDefault().getLanguage());
                            map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                            map3.put("platformextension", appsFlyerLib.f40.m172());
                            m50(context, map3);
                            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            long j112 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("installDate", simpleDateFormat.format(new Date(j112)));
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            sharedPreferences5 = sharedPreferences;
                            if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                m48(context, "appsflyerConversionDataRequestRetries", 0);
                                m48(context, "versionCode", packageInfo.versionCode);
                            }
                            map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            map3.put("app_version_name", packageInfo.versionName);
                            long j113 = packageInfo.firstInstallTime;
                            long j114 = packageInfo.lastUpdateTime;
                            obj4 = "appsflyerKey";
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date1", simpleDateFormat.format(new Date(j113)));
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date2", simpleDateFormat.format(new Date(j114)));
                            string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                            if (string16 == null) {
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                    str15 = simpleDateFormat.format(new Date());
                                } else {
                                    str15 = "";
                                }
                                string16 = str15;
                                m65(context, "appsFlyerFirstInstall", string16);
                            }
                            AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                            map3.put("firstLaunchDate", string16);
                            if (str4.length() > 0) {
                                map3.put("referrer", str4);
                            }
                            string14 = sharedPreferences5.getString("extraReferrers", null);
                            if (string14 != null) {
                                map3.put("extraReferrers", string14);
                            }
                            string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                            if (string15 != null) {
                                map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                            }
                            appsFlyerLib.f41 = u.m214(context);
                            StringBuilder sb111115 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb111115.append(appsFlyerLib.f41);
                            AFLogger.afDebugLog(sb111115.toString());
                            if (!appsFlyerLib.f41) {
                                map3.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (z2) {
                                AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                if (appsFlyerLib.f34 != null) {
                                    JSONObject jSONObject10 = new JSONObject(appsFlyerLib.f34);
                                    jSONObject10.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    map3.put("af_deeplink", jSONObject10.toString());
                                }
                                appsFlyerLib.f34 = null;
                                map3.put("open_referrer", str5);
                            }
                            if (appsFlyerLib.f39) {
                                map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent retargeting params to test app");
                            }
                            jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                            String referrer9 = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (jCurrentTimeMillis <= 30000) {
                                z5 = false;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent params to test app");
                                AFLogger.afInfoLog("Test mode ended!");
                                appsFlyerLib.f38 = 0L;
                            }
                            if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                l.m158(context, map3);
                                if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                    map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                } else {
                                    map3.put("GAID_retry", "false");
                                }
                            }
                            oVarM159 = l.m159(context.getContentResolver());
                            if (oVarM159 != null) {
                                map3.put("amazon_aid", oVarM159.m168());
                                map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                            }
                            referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (referrer != null) {
                                map3.put("referrer", referrer);
                            }
                            zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                            map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                            iM24 = m24(sharedPreferences5, str12, z2);
                            map3.put("counter", Integer.toString(iM24));
                            if (str2 != null) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                            if (z2) {
                                z7 = true;
                            } else {
                                z7 = true;
                            }
                            if (zEquals) {
                                z7 = false;
                            }
                            map3.put("isFirstCall", Boolean.toString(z7));
                            map = new HashMap();
                            map.put("cpu_abi", m25("ro.product.cpu.abi"));
                            map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                            map.put("arch", m25("os.arch"));
                            map.put("build_display_id", m25("ro.build.display.id"));
                            if (z2) {
                                if (appsFlyerLib.f37) {
                                    com.appsflyer.e eVar8 = com.appsflyer.e.d.f123;
                                    locationM119 = com.appsflyer.e.m119(context);
                                    map2 = new HashMap(3);
                                    if (locationM119 != null) {
                                        map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                        map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                        map2.put("ts", String.valueOf(locationM119.getTime()));
                                    }
                                    if (!map2.isEmpty()) {
                                        map.put("loc", map2);
                                    }
                                }
                                cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                map.put("btl", Float.toString(cVarM96.m97()));
                                if (cVarM96.m98() != null) {
                                    map.put("btch", cVarM96.m98());
                                }
                                if (2 >= iM24) {
                                    listM122 = f.m120(context).m122();
                                    if (!listM122.isEmpty()) {
                                        map.put("sensors", listM122);
                                    }
                                }
                            }
                            map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                            map3.put("deviceData", map);
                            new C0120r();
                            Object obj19 = obj4;
                            String str11114 = (String) map3.get(obj19);
                            Object obj110 = obj3;
                            String str11115 = (String) map3.get(obj110);
                            String str11116 = (String) map3.get("uid");
                            StringBuilder sb111116 = new StringBuilder();
                            sb111116.append(str11114.substring(0, 7));
                            sb111116.append(str11116.substring(0, 7));
                            sb111116.append(str11115.substring(str11115.length() - 7));
                            map3.put("af_v", C0120r.m180(sb111116.toString()));
                            new C0120r();
                            String str11117 = (String) map3.get(obj19);
                            StringBuilder sb111117 = new StringBuilder();
                            sb111117.append(str11117);
                            sb111117.append(map3.get(obj110));
                            String string2118 = sb111117.toString();
                            StringBuilder sb111118 = new StringBuilder();
                            sb111118.append(string2118);
                            sb111118.append(map3.get("uid"));
                            String string2119 = sb111118.toString();
                            StringBuilder sb111119 = new StringBuilder();
                            sb111119.append(string2119);
                            sb111119.append(map3.get("installDate"));
                            String string21110 = sb111119.toString();
                            StringBuilder sb1111110 = new StringBuilder();
                            sb1111110.append(string21110);
                            sb1111110.append(map3.get("counter"));
                            String string21111 = sb1111110.toString();
                            StringBuilder sb1111111 = new StringBuilder();
                            sb1111111.append(string21111);
                            sb1111111.append(map3.get("iaecounter"));
                            map3.put("af_v2", C0120r.m180(C0120r.m179(sb1111111.toString())));
                            map3.put("ivc", Boolean.valueOf(m72(context)));
                            if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                            }
                            if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                            }
                        } else {
                            map3.put(com.my.target.i.G, Locale.getDefault().getDisplayLanguage());
                            map3.put("lang_code", Locale.getDefault().getLanguage());
                            map3.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
                            map3.put("platformextension", appsFlyerLib.f40.m172());
                            m50(context, map3);
                            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            long j115 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("installDate", simpleDateFormat.format(new Date(j115)));
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            sharedPreferences5 = sharedPreferences;
                            if (packageInfo.versionCode > sharedPreferences5.getInt("versionCode", 0)) {
                                m48(context, "appsflyerConversionDataRequestRetries", 0);
                                m48(context, "versionCode", packageInfo.versionCode);
                            }
                            map3.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            map3.put("app_version_name", packageInfo.versionName);
                            long j116 = packageInfo.firstInstallTime;
                            long j117 = packageInfo.lastUpdateTime;
                            obj4 = "appsflyerKey";
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date1", simpleDateFormat.format(new Date(j116)));
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            map3.put("date2", simpleDateFormat.format(new Date(j117)));
                            string16 = context.getSharedPreferences(str10, 0).getString("appsFlyerFirstInstall", null);
                            if (string16 == null) {
                                if (!context.getSharedPreferences(str10, 0).contains(str12)) {
                                    AFLogger.afDebugLog("AppsFlyer: first launch detected");
                                    str15 = simpleDateFormat.format(new Date());
                                } else {
                                    str15 = "";
                                }
                                string16 = str15;
                                m65(context, "appsFlyerFirstInstall", string16);
                            }
                            AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string16)));
                            map3.put("firstLaunchDate", string16);
                            if (str4.length() > 0) {
                                map3.put("referrer", str4);
                            }
                            string14 = sharedPreferences5.getString("extraReferrers", null);
                            if (string14 != null) {
                                map3.put("extraReferrers", string14);
                            }
                            string15 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                            if (string15 != null) {
                                map3.put("af_gcm_token", com.appsflyer.c.a.C0031c.m113(string15).m114());
                            }
                            appsFlyerLib.f41 = u.m214(context);
                            StringBuilder sb1111112 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb1111112.append(appsFlyerLib.f41);
                            AFLogger.afDebugLog(sb1111112.toString());
                            if (!appsFlyerLib.f41) {
                                map3.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (z2) {
                                AFDeepLinkManager.getInstance().processIntentForDeepLink(intent, context, map3);
                                if (appsFlyerLib.f34 != null) {
                                    JSONObject jSONObject11 = new JSONObject(appsFlyerLib.f34);
                                    jSONObject11.put("isPush", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    map3.put("af_deeplink", jSONObject11.toString());
                                }
                                appsFlyerLib.f34 = null;
                                map3.put("open_referrer", str5);
                            }
                            if (appsFlyerLib.f39) {
                                map3.put("testAppMode_retargeting", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent retargeting params to test app");
                            }
                            jCurrentTimeMillis = System.currentTimeMillis() - appsFlyerLib.f38;
                            String referrer10 = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (jCurrentTimeMillis <= 30000) {
                                z5 = false;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                map3.put("testAppMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                m47(context, new JSONObject(map3).toString());
                                AFLogger.afInfoLog("Sent params to test app");
                                AFLogger.afInfoLog("Test mode ended!");
                                appsFlyerLib.f38 = 0L;
                            }
                            if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
                                l.m158(context, map3);
                                if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                                    map3.put("GAID_retry", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                } else {
                                    map3.put("GAID_retry", "false");
                                }
                            }
                            oVarM159 = l.m159(context.getContentResolver());
                            if (oVarM159 != null) {
                                map3.put("amazon_aid", oVarM159.m168());
                                map3.put("amazon_aid_limit", String.valueOf(oVarM159.m169()));
                            }
                            referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                            if (referrer != null) {
                                map3.put("referrer", referrer);
                            }
                            zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(sharedPreferences5.getString("sentSuccessfully", ""));
                            map3.put("registeredUninstall", Boolean.valueOf(sharedPreferences5.getBoolean("sentRegisterRequestToAF", false)));
                            iM24 = m24(sharedPreferences5, str12, z2);
                            map3.put("counter", Integer.toString(iM24));
                            if (str2 != null) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            map3.put("iaecounter", Integer.toString(m24(sharedPreferences5, "appsFlyerInAppEventCount", z6)));
                            if (z2) {
                                z7 = true;
                            } else {
                                z7 = true;
                            }
                            if (zEquals) {
                                z7 = false;
                            }
                            map3.put("isFirstCall", Boolean.toString(z7));
                            map = new HashMap();
                            map.put("cpu_abi", m25("ro.product.cpu.abi"));
                            map.put("cpu_abi2", m25("ro.product.cpu.abi2"));
                            map.put("arch", m25("os.arch"));
                            map.put("build_display_id", m25("ro.build.display.id"));
                            if (z2) {
                                if (appsFlyerLib.f37) {
                                    com.appsflyer.e eVar9 = com.appsflyer.e.d.f123;
                                    locationM119 = com.appsflyer.e.m119(context);
                                    map2 = new HashMap(3);
                                    if (locationM119 != null) {
                                        map2.put("lat", String.valueOf(locationM119.getLatitude()));
                                        map2.put("lon", String.valueOf(locationM119.getLongitude()));
                                        map2.put("ts", String.valueOf(locationM119.getTime()));
                                    }
                                    if (!map2.isEmpty()) {
                                        map.put("loc", map2);
                                    }
                                }
                                cVarM96 = com.appsflyer.b.a.f100.m96(context);
                                map.put("btl", Float.toString(cVarM96.m97()));
                                if (cVarM96.m98() != null) {
                                    map.put("btch", cVarM96.m98());
                                }
                                if (2 >= iM24) {
                                    listM122 = f.m120(context).m122();
                                    if (!listM122.isEmpty()) {
                                        map.put("sensors", listM122);
                                    }
                                }
                            }
                            map.put(AFScreenManager.SCREEN_KEY, AFScreenManager.getScreenMetrics(context));
                            map3.put("deviceData", map);
                            new C0120r();
                            Object obj111 = obj4;
                            String str11118 = (String) map3.get(obj111);
                            Object obj112 = obj3;
                            String str11119 = (String) map3.get(obj112);
                            String str111110 = (String) map3.get("uid");
                            StringBuilder sb1111113 = new StringBuilder();
                            sb1111113.append(str11118.substring(0, 7));
                            sb1111113.append(str111110.substring(0, 7));
                            sb1111113.append(str11119.substring(str11119.length() - 7));
                            map3.put("af_v", C0120r.m180(sb1111113.toString()));
                            new C0120r();
                            String str111111 = (String) map3.get(obj111);
                            StringBuilder sb1111114 = new StringBuilder();
                            sb1111114.append(str111111);
                            sb1111114.append(map3.get(obj112));
                            String string21112 = sb1111114.toString();
                            StringBuilder sb1111115 = new StringBuilder();
                            sb1111115.append(string21112);
                            sb1111115.append(map3.get("uid"));
                            String string21113 = sb1111115.toString();
                            StringBuilder sb1111116 = new StringBuilder();
                            sb1111116.append(string21113);
                            sb1111116.append(map3.get("installDate"));
                            String string21114 = sb1111116.toString();
                            StringBuilder sb1111117 = new StringBuilder();
                            sb1111117.append(string21114);
                            sb1111117.append(map3.get("counter"));
                            String string21115 = sb1111117.toString();
                            StringBuilder sb1111118 = new StringBuilder();
                            sb1111118.append(string21115);
                            sb1111118.append(map3.get("iaecounter"));
                            map3.put("af_v2", C0120r.m180(C0120r.m179(sb1111118.toString())));
                            map3.put("ivc", Boolean.valueOf(m72(context)));
                            if (sharedPreferences5.contains(IS_STOP_TRACKING_USED)) {
                                map3.put("istu", String.valueOf(sharedPreferences5.getBoolean(IS_STOP_TRACKING_USED, false)));
                            }
                            if (AppsFlyerProperties.getInstance().getObject("consumeAfDeepLink") != null) {
                                map3.put("is_dp_api", String.valueOf(AppsFlyerProperties.getInstance().getBoolean("consumeAfDeepLink", false)));
                            }
                        }
                    }
                }
            } catch (Exception e14) {
                AFLogger.afErrorLog("Exception while validation permissions. ", e14);
            }
        } catch (Throwable th10) {
            th = th10;
            Throwable th11 = th;
            AFLogger.afErrorLog(th11.getLocalizedMessage(), th11);
            return map3;
        }
        return map3;
    }

    public void setConsumeAFDeepLinks(boolean z) {
        AppsFlyerProperties.getInstance().set("consumeAfDeepLink", z);
        s.m187().m196("setConsumeAFDeepLinks: ".concat(String.valueOf(z)), new String[0]);
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static void m50(Context context, Map<String, ? super String> map) {
        g gVar = g.d.f145;
        g.b bVarM128 = g.m128(context);
        map.put("network", bVarM128.m132());
        if (bVarM128.m131() != null) {
            map.put("operator", bVarM128.m131());
        }
        if (bVarM128.m130() != null) {
            map.put("carrier", bVarM128.m130());
        }
    }

    protected void handleDeepLinkCallback(Context context, Map<String, Object> map, Uri uri) {
        final Map<String, String> map2;
        map.put("af_deeplink", uri.toString());
        if (uri.getQueryParameter("af_deeplink") != null) {
            this.f39 = "AppsFlyer_Test".equals(uri.getQueryParameter("media_source")) && Boolean.parseBoolean(uri.getQueryParameter("is_retargeting"));
            map2 = m27(context, uri.getQuery());
            String path = uri.getPath();
            if (path != null) {
                map2.put("path", path);
            }
            String scheme = uri.getScheme();
            if (scheme != null) {
                map2.put("scheme", scheme);
            }
            String host = uri.getHost();
            if (host != null) {
                map2.put("host", host);
            }
        } else {
            map2 = new HashMap<>();
            map2.put("link", uri.toString());
        }
        final WeakReference weakReference = new WeakReference(context);
        q qVar = new q(uri, this);
        qVar.setConnProvider(new OneLinkHttpTask.HttpsUrlConnectionProvider());
        if (qVar.m176()) {
            qVar.m175(new q.e() { // from class: com.appsflyer.AppsFlyerLib.3
                @Override // com.appsflyer.q.e
                /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
                public final void mo85(String str) {
                    if (AppsFlyerLib.f23 != null) {
                        m84(map2);
                        AppsFlyerLib.f23.onAttributionFailure(str);
                    }
                }

                /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
                private void m84(Map<String, String> map3) {
                    if (weakReference.get() != null) {
                        AppsFlyerLib.m65((Context) weakReference.get(), "deeplinkAttribution", new JSONObject(map3).toString());
                    }
                }

                @Override // com.appsflyer.q.e
                /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
                public final void mo86(Map<String, String> map3) {
                    for (String str : map3.keySet()) {
                        map2.put(str, map3.get(str));
                    }
                    m84(map2);
                    AppsFlyerLib.m51(map2);
                }
            });
            AFExecutor.getInstance().getThreadPoolExecutor().execute(qVar);
            return;
        }
        AppsFlyerConversionListener appsFlyerConversionListener = f23;
        if (appsFlyerConversionListener != null) {
            try {
                appsFlyerConversionListener.onAppOpenAttribution(map2);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static boolean m41(Context context) {
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
                return true;
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("WARNING:  Google play services is unavailable. ", th);
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            AFLogger.afErrorLog("WARNING:  Google Play Services is unavailable. ", e2);
            return false;
        }
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private static boolean m23(Context context) {
        return (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) || !m41(context);
    }

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static String m22(Context context) {
        String string = AppsFlyerProperties.getInstance().getString("api_store_value");
        if (string != null) {
            return string;
        }
        String strM68 = m68((WeakReference<Context>) new WeakReference(context), "AF_STORE");
        if (strM68 != null) {
            return strM68;
        }
        return null;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static String m25(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return null;
        }
    }

    @Nullable
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static String m68(WeakReference<Context> weakReference, String str) {
        if (weakReference.get() == null) {
            return null;
        }
        return m31(str, weakReference.get().getPackageManager(), weakReference.get().getPackageName());
    }

    @Nullable
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String m31(String str, PackageManager packageManager, String str2) {
        Object obj;
        try {
            Bundle bundle = ((PackageItemInfo) packageManager.getApplicationInfo(str2, 128)).metaData;
            if (bundle == null || (obj = bundle.get(str)) == null) {
                return null;
            }
            return obj.toString();
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Could not find ");
            sb.append(str);
            sb.append(" value in the manifest");
            AFLogger.afErrorLog(sb.toString(), th);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001d A[Catch: JSONException -> 0x0019, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0019, blocks: (B:4:0x000e, B:6:0x0013, B:10:0x001d), top: B:19:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:6:0x0013 A[Catch: JSONException -> 0x0019, TryCatch #0 {JSONException -> 0x0019, blocks: (B:4:0x000e, B:6:0x0013, B:10:0x001d), top: B:19:0x000e }] */
    public void setPreinstallAttribution(String str, String str2, String str3) {
        AFLogger.afDebugLog("setPreinstallAttribution API called");
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            try {
                jSONObject.put(Constants.URL_MEDIA_SOURCE, str);
                if (str2 != null) {
                    jSONObject.put(Constants.URL_CAMPAIGN, str2);
                }
                if (str3 != null) {
                    jSONObject.put(Constants.URL_SITE_ID, str3);
                }
            } catch (JSONException e2) {
                AFLogger.afErrorLog(e2.getMessage(), e2);
            }
        } else {
            if (str2 != null) {
                jSONObject.put(Constants.URL_CAMPAIGN, str2);
            }
            if (str3 != null) {
                jSONObject.put(Constants.URL_SITE_ID, str3);
            }
        }
        if (jSONObject.has(Constants.URL_MEDIA_SOURCE)) {
            AppsFlyerProperties.getInstance().set("preInstallName", jSONObject.toString());
        } else {
            AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0060 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static String m59(File file, String str) throws Throwable {
        FileReader fileReader;
        Reader reader = null;
        try {
            try {
                try {
                    Properties properties = new Properties();
                    fileReader = new FileReader(file);
                    try {
                        properties.load(fileReader);
                        AFLogger.afInfoLog("Found PreInstall property!");
                        String property = properties.getProperty(str);
                        try {
                            fileReader.close();
                        } catch (Throwable th) {
                            AFLogger.afErrorLog(th.getMessage(), th);
                        }
                        return property;
                    } catch (FileNotFoundException unused) {
                        StringBuilder sb = new StringBuilder("PreInstall file wasn't found: ");
                        sb.append(file.getAbsolutePath());
                        AFLogger.afDebugLog(sb.toString());
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        AFLogger.afErrorLog(th.getMessage(), th);
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (0 != 0) {
                        try {
                            reader.close();
                        } catch (Throwable th4) {
                            AFLogger.afErrorLog(th4.getMessage(), th4);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                fileReader = null;
            } catch (Throwable th5) {
                th = th5;
                if (0 != 0) {
                    reader.close();
                }
                throw th;
            }
        } catch (Throwable th6) {
            AFLogger.afErrorLog(th6.getMessage(), th6);
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static File m42(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.trim().length() > 0) {
                return new File(str.trim());
            }
            return null;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    public static String m61(WeakReference<Context> weakReference) {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CHANNEL);
        if (string == null) {
            string = m68(weakReference, "CHANNEL");
        }
        if (string == null || !string.equals("")) {
            return string;
        }
        return null;
    }

    public boolean isPreInstalledApp(Context context) {
        try {
            return (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0;
        } catch (PackageManager.NameNotFoundException e2) {
            AFLogger.afErrorLog("Could not check if app is pre installed", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    public static String m74(Context context, String str) throws PackageManager.NameNotFoundException {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appsflyer-data", 0);
        if (sharedPreferences.contains("CACHED_CHANNEL")) {
            return sharedPreferences.getString("CACHED_CHANNEL", null);
        }
        m65(context, "CACHED_CHANNEL", str);
        return str;
    }

    public String getAttributionId(ContentResolver contentResolver) {
        Cursor cursorQuery = contentResolver.query(Uri.parse(ATTRIBUTION_ID_CONTENT_URI), new String[]{ATTRIBUTION_ID_COLUMN_NAME}, null, null, null);
        String string = null;
        try {
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            string = cursorQuery.getString(cursorQuery.getColumnIndex(ATTRIBUTION_ID_COLUMN_NAME));
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        }
                    } catch (Exception e2) {
                        AFLogger.afErrorLog("Could not collect cursor attribution. ", e2);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return string;
                    }
                    return string;
                } catch (Throwable th) {
                    if (cursorQuery != null) {
                        try {
                            cursorQuery.close();
                        } catch (Exception e3) {
                            AFLogger.afErrorLog(e3.getMessage(), e3);
                        }
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Exception e4) {
                    AFLogger.afErrorLog(e4.getMessage(), e4);
                }
            }
            return null;
        } catch (Exception e5) {
            AFLogger.afErrorLog(e5.getMessage(), e5);
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static SharedPreferences m56(Context context) {
        return context.getSharedPreferences("appsflyer-data", 0);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static int m55(SharedPreferences sharedPreferences) {
        return m24(sharedPreferences, "appsFlyerCount", false);
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static int m24(SharedPreferences sharedPreferences, String str, boolean z) {
        int i = sharedPreferences.getInt(str, 0);
        if (z) {
            i++;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(str, i);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        }
        if (s.m187().m190()) {
            s.m187().m199(String.valueOf(i));
        }
        return i;
    }

    public String getAppsFlyerUID(Context context) {
        s.m187().m196("getAppsFlyerUID", new String[0]);
        return t.m211((WeakReference<Context>) new WeakReference(context));
    }

    /* JADX WARN: Code duplicated, block: B:108:0x023f  */
    /* JADX WARN: Code duplicated, block: B:47:0x0154  */
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private void m38(URL url, String str, String str2, WeakReference<Context> weakReference, String str3, boolean z) throws Throwable {
        HttpURLConnection httpURLConnection;
        OutputStreamWriter outputStreamWriter;
        Context context = weakReference.get();
        boolean z2 = z && f23 != null;
        try {
            s.m187().m201(url.toString(), str);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            try {
                try {
                    httpURLConnection2.setRequestMethod(HttpRequest.METHOD_POST);
                    httpURLConnection2.setRequestProperty(HttpRequest.HEADER_CONTENT_LENGTH, String.valueOf(str.getBytes().length));
                    httpURLConnection2.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
                    httpURLConnection2.setConnectTimeout(10000);
                    httpURLConnection2.setDoOutput(true);
                    try {
                        OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(httpURLConnection2.getOutputStream(), "UTF-8");
                        try {
                            outputStreamWriter2.write(str);
                            outputStreamWriter2.close();
                            int responseCode = httpURLConnection2.getResponseCode();
                            String strM44 = m44(httpURLConnection2);
                            s.m187().m200(url.toString(), responseCode, strM44);
                            AFLogger.afInfoLog("response code: ".concat(String.valueOf(responseCode)));
                            m66(context, LOG_TAG, "SERVER_RESPONSE_CODE", Integer.toString(responseCode));
                            SharedPreferences sharedPreferences = context.getSharedPreferences("appsflyer-data", 0);
                            if (responseCode == 200) {
                                if (weakReference.get() != null && z) {
                                    this.f46 = System.currentTimeMillis();
                                    if (f27 != null) {
                                        f27.onTrackingRequestSuccess();
                                    }
                                }
                                String string = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                                if (string != null) {
                                    AFLogger.afDebugLog("Uninstall Token exists: ".concat(String.valueOf(string)));
                                    if (!sharedPreferences.getBoolean("sentRegisterRequestToAF", false)) {
                                        AFLogger.afDebugLog("Resending Uninstall token to AF servers: ".concat(String.valueOf(string)));
                                        u.m217(context, new com.appsflyer.c.a.C0031c(string));
                                    }
                                } else if (AppsFlyerProperties.getInstance().getString("gcmProjectNumber") != null) {
                                    AFLogger.afDebugLog("GCM Project number exists. Fetching token and sending to AF servers");
                                    new u.a(new WeakReference(context)).execute(new Void[0]);
                                }
                                if (this.latestDeepLink != null) {
                                    this.latestDeepLink = null;
                                }
                                if (str3 != null) {
                                    CacheManager.getInstance().deleteRequest(str3, context);
                                }
                                if (weakReference.get() == null || str3 != null) {
                                    httpURLConnection = httpURLConnection2;
                                } else {
                                    m65(context, "sentSuccessfully", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    if (this.f48) {
                                        httpURLConnection = httpURLConnection2;
                                    } else {
                                        httpURLConnection = httpURLConnection2;
                                        if (System.currentTimeMillis() - this.f28 >= 15000 && this.f30 == null) {
                                            this.f30 = AFExecutor.getInstance().m6();
                                            m39(this.f30, new d(context), 1L, TimeUnit.SECONDS);
                                        }
                                    }
                                }
                                this.f44 = ServerConfigHandler.m95(strM44).optBoolean("send_background", false);
                            } else {
                                httpURLConnection = httpURLConnection2;
                                if (f27 != null) {
                                    f27.onTrackingRequestFailure("Failure: ".concat(String.valueOf(responseCode)));
                                }
                            }
                            int i = sharedPreferences.getInt("appsflyerConversionDataRequestRetries", 0);
                            long j = sharedPreferences.getLong("appsflyerConversionDataCacheExpiration", 0L);
                            if (j != 0 && System.currentTimeMillis() - j > 5184000000L) {
                                m65(context, "attributionId", (String) null);
                                m64(context, "appsflyerConversionDataCacheExpiration", 0L);
                            }
                            if (sharedPreferences.getString("attributionId", null) == null && str2 != null && z2 && f23 != null && i <= 5) {
                                ScheduledThreadPoolExecutor scheduledThreadPoolExecutorM6 = AFExecutor.getInstance().m6();
                                m39(scheduledThreadPoolExecutorM6, new e(context.getApplicationContext(), str2, scheduledThreadPoolExecutorM6), 10L, TimeUnit.MILLISECONDS);
                            } else if (str2 == null) {
                                AFLogger.afWarnLog("AppsFlyer dev key is missing.");
                            } else if (z2 && f23 != null && sharedPreferences.getString("attributionId", null) != null && m24(sharedPreferences, "appsFlyerCount", false) > 1) {
                                try {
                                    Map<String, String> mapM69 = m69(context);
                                    if (mapM69 != null) {
                                        try {
                                            if (!mapM69.containsKey("is_first_launch")) {
                                                mapM69.put("is_first_launch", Boolean.toString(false));
                                            }
                                            f23.onInstallConversionDataLoaded(mapM69);
                                        } catch (Throwable th) {
                                            AFLogger.afErrorLog(th.getLocalizedMessage(), th);
                                        }
                                    }
                                } catch (m e2) {
                                    AFLogger.afErrorLog(e2.getMessage(), e2);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            outputStreamWriter = outputStreamWriter2;
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            } else if (f27 != null) {
                                f27.onTrackingRequestFailure("No Connectivity");
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        outputStreamWriter = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
        }
    }

    public void validateAndTrackInAppPurchase(Context context, String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        s sVarM187 = s.m187();
        String[] strArr = new String[6];
        strArr[0] = str;
        strArr[1] = str2;
        strArr[2] = str3;
        strArr[3] = str4;
        strArr[4] = str5;
        strArr[5] = map == null ? "" : map.toString();
        sVarM187.m196("validateAndTrackInAppPurchase", strArr);
        if (!isTrackingStopped()) {
            StringBuilder sb = new StringBuilder("Validate in app called with parameters: ");
            sb.append(str3);
            sb.append(" ");
            sb.append(str4);
            sb.append(" ");
            sb.append(str5);
            AFLogger.afInfoLog(sb.toString());
        }
        if (str == null || str4 == null || str2 == null || str5 == null || str3 == null) {
            AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = f20;
            if (appsFlyerInAppPurchaseValidatorListener != null) {
                appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure("Please provide purchase parameters");
                return;
            }
            return;
        }
        m39(AFExecutor.getInstance().m6(), new j(context.getApplicationContext(), AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY), str, str2, str3, str4, str5, map, context instanceof Activity ? ((Activity) context).getIntent() : null), 10L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static void m39(ScheduledExecutorService scheduledExecutorService, Runnable runnable, long j, TimeUnit timeUnit) {
        if (scheduledExecutorService != null) {
            try {
                if (!scheduledExecutorService.isShutdown() && !scheduledExecutorService.isTerminated()) {
                    scheduledExecutorService.schedule(runnable, j, timeUnit);
                    return;
                }
            } catch (RejectedExecutionException e2) {
                AFLogger.afErrorLog("scheduleJob failed with RejectedExecutionException Exception", e2);
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog("scheduleJob failed with Exception", th);
                return;
            }
        }
        AFLogger.afWarnLog("scheduler is null, shut downed or terminated");
    }

    @Override // com.appsflyer.a
    public void onHandleReferrer(Map<String, String> map) {
        this.f50 = map;
    }

    public boolean isTrackingStopped() {
        return this.f43;
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
        private String f70;

        /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
        private boolean f71;

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private String f72;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private WeakReference<Context> f73;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private String f74;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private final Intent f75;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private final String f76;

        /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
        private boolean f77;

        /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
        private String f78;

        /* synthetic */ c(AppsFlyerLib appsFlyerLib, WeakReference weakReference, String str, String str2, String str3, String str4, boolean z, Intent intent, String str5, byte b) {
            this(weakReference, str, str2, str3, str4, z, intent, str5);
        }

        private c(WeakReference<Context> weakReference, String str, String str2, String str3, String str4, boolean z, Intent intent, String str5) {
            this.f73 = weakReference;
            this.f72 = str;
            this.f74 = str2;
            this.f78 = str3;
            this.f70 = str4;
            this.f71 = true;
            this.f77 = z;
            this.f75 = intent;
            this.f76 = str5;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppsFlyerLib.m28(AppsFlyerLib.this, this.f73.get(), this.f72, this.f74, this.f78, this.f70, this.f71, this.f77, this.f75, this.f76);
        }
    }

    class a implements Runnable {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private boolean f58;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private Map<String, Object> f59;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private String f60;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private int f61;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private WeakReference<Context> f62;

        /* synthetic */ a(AppsFlyerLib appsFlyerLib, String str, Map map, Context context, boolean z, int i, byte b) {
            this(str, map, context, z, i);
        }

        private a(String str, Map<String, Object> map, Context context, boolean z, int i) {
            this.f62 = null;
            this.f60 = str;
            this.f59 = map;
            this.f62 = new WeakReference<>(context);
            this.f58 = z;
            this.f61 = i;
        }

        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            if (AppsFlyerLib.this.isTrackingStopped()) {
                return;
            }
            String string = null;
            if (this.f58 && this.f61 <= 2 && AppsFlyerLib.m54(AppsFlyerLib.this)) {
                this.f59.put("rfr", AppsFlyerLib.this.f50);
            }
            Map<String, Object> map = this.f59;
            map.putAll(new com.appsflyer.c.a(map, this.f62.get()));
            try {
                String str = (String) this.f59.get("appsflyerKey");
                string = AFHelper.convertToJsonObject(this.f59).toString();
                AppsFlyerLib.m37(AppsFlyerLib.this, this.f60, string, str, this.f62, (String) null, this.f58);
            } catch (IOException e) {
                AFLogger.afErrorLog("Exception while sending request to server. ", e);
                if (string == null || this.f62 == null || this.f60.contains("&isCachedRequest=true&timeincache=")) {
                    return;
                }
                CacheManager.getInstance().cacheRequest(new RequestCacheData(this.f60, string, BuildConfig.VERSION_NAME), this.f62.get());
                AFLogger.afErrorLog(e.getMessage(), e);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getMessage(), th);
            }
        }
    }

    class e extends b {
        public e(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
            super(context, str, scheduledExecutorService);
        }

        @Override // com.appsflyer.AppsFlyerLib.b
        /* JADX INFO: renamed from: ˎ */
        public final String mo87() {
            return ServerConfigHandler.getUrl("https://%sapi.%s/install_data/v3/");
        }

        @Override // com.appsflyer.AppsFlyerLib.b
        /* JADX INFO: renamed from: ˎ */
        protected final void mo89(Map<String, String> map) {
            map.put("is_first_launch", Boolean.toString(true));
            AppsFlyerLib.f23.onInstallConversionDataLoaded(map);
            AppsFlyerLib.m48(this.f67.get(), "appsflyerConversionDataRequestRetries", 0);
        }

        @Override // com.appsflyer.AppsFlyerLib.b
        /* JADX INFO: renamed from: ˎ */
        protected final void mo88(String str, int i) {
            AppsFlyerLib.f23.onInstallConversionFailure(str);
            if (i < 400 || i >= 500) {
                return;
            }
            AppsFlyerLib.m48(this.f67.get(), "appsflyerConversionDataRequestRetries", AppsFlyerLib.m56(this.f67.get()).getInt("appsflyerConversionDataRequestRetries", 0) + 1);
        }
    }

    abstract class b implements Runnable {

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private ScheduledExecutorService f65;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private String f66;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        WeakReference<Context> f67;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private AtomicInteger f68 = new AtomicInteger(0);

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        public abstract String mo87();

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        protected abstract void mo88(String str, int i);

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        protected abstract void mo89(Map<String, String> map);

        b(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
            this.f67 = null;
            this.f67 = new WeakReference<>(context);
            this.f66 = str;
            if (scheduledExecutorService == null) {
                this.f65 = AFExecutor.getInstance().m6();
            } else {
                this.f65 = scheduledExecutorService;
            }
        }

        /* JADX WARN: Code duplicated, block: B:74:0x021e A[Catch: all -> 0x0214, TryCatch #0 {all -> 0x0214, blocks: (B:11:0x001e, B:15:0x002e, B:18:0x0044, B:20:0x0052, B:23:0x006b, B:72:0x0218, B:74:0x021e, B:75:0x0225, B:21:0x005d), top: B:86:0x001e }] */
        /* JADX WARN: Code duplicated, block: B:78:0x0233  */
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            HttpURLConnection httpURLConnection;
            String strConcat;
            String str = this.f66;
            if (str == null || str.length() == 0 || AppsFlyerLib.this.isTrackingStopped()) {
                return;
            }
            this.f68.incrementAndGet();
            HttpURLConnection httpURLConnection2 = null;
            try {
                try {
                    Context context = this.f67.get();
                    if (context == null) {
                        this.f68.decrementAndGet();
                        return;
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    String strM74 = AppsFlyerLib.m74(context, AppsFlyerLib.m61((WeakReference<Context>) new WeakReference(context)));
                    if (strM74 == null) {
                        strConcat = "";
                    } else if (AppsFlyerLib.f19.contains(strM74.toLowerCase())) {
                        AFLogger.afWarnLog(String.format("AF detected using redundant Google-Play channel for attribution - %s. Using without channel postfix.", strM74));
                        strConcat = "";
                    } else {
                        strConcat = "-".concat(String.valueOf(strM74));
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(mo87());
                    sb.append(context.getPackageName());
                    sb.append(strConcat);
                    sb.append("?devkey=");
                    sb.append(this.f66);
                    sb.append("&device_id=");
                    sb.append(t.m211((WeakReference<Context>) new WeakReference(context)));
                    s.m187().m201(sb.toString(), "");
                    StringBuilder sb2 = new StringBuilder("Calling server for attribution url: ");
                    sb2.append(sb.toString());
                    f.AnonymousClass4.m127(sb2.toString());
                    httpURLConnection = (HttpURLConnection) new URL(sb.toString()).openConnection();
                    try {
                        httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setRequestProperty("Connection", "close");
                        httpURLConnection.connect();
                        int responseCode = httpURLConnection.getResponseCode();
                        String strM44 = AppsFlyerLib.m44(httpURLConnection);
                        s.m187().m200(sb.toString(), responseCode, strM44);
                        if (responseCode == 200) {
                            AppsFlyerLib.m64(context, "appsflyerGetConversionDataTiming", System.currentTimeMillis() - jCurrentTimeMillis);
                            f.AnonymousClass4.m127("Attribution data: ".concat(String.valueOf(strM44)));
                            if (strM44.length() > 0 && context != null) {
                                Map<String, String> mapM71 = AppsFlyerLib.m71(strM44);
                                String str2 = mapM71.get("iscache");
                                if (str2 != null && Boolean.toString(false).equals(str2)) {
                                    AppsFlyerLib.m64(context, "appsflyerConversionDataCacheExpiration", System.currentTimeMillis());
                                }
                                if (mapM71.containsKey(Constants.URL_SITE_ID)) {
                                    if (mapM71.containsKey("af_channel")) {
                                        StringBuilder sb3 = new StringBuilder(Constants.LOG_INVITE_DETECTED_APP_INVITE_VIA_CHANNEL);
                                        sb3.append(mapM71.get("af_channel"));
                                        AFLogger.afDebugLog(sb3.toString());
                                    } else {
                                        AFLogger.afDebugLog(String.format(Constants.LOG_CROSS_PROMOTION_APP_INSTALLED_FROM_CROSS_PROMOTION, mapM71.get(Constants.URL_SITE_ID)));
                                    }
                                }
                                if (mapM71.containsKey(Constants.URL_SITE_ID)) {
                                    StringBuilder sb4 = new StringBuilder(Constants.LOG_INVITE_DETECTED_APP_INVITE_VIA_CHANNEL);
                                    sb4.append(mapM71.get("af_channel"));
                                    AFLogger.afDebugLog(sb4.toString());
                                }
                                mapM71.put("is_first_launch", Boolean.toString(false));
                                String string = new JSONObject(mapM71).toString();
                                if (string != null) {
                                    AppsFlyerLib.m65(context, "attributionId", string);
                                } else {
                                    AppsFlyerLib.m65(context, "attributionId", strM44);
                                }
                                StringBuilder sb5 = new StringBuilder("iscache=");
                                sb5.append(str2);
                                sb5.append(" caching conversion data");
                                AFLogger.afDebugLog(sb5.toString());
                                if (AppsFlyerLib.f23 != null && this.f68.intValue() <= 1) {
                                    try {
                                        mapM71 = AppsFlyerLib.m69(context);
                                    } catch (m e) {
                                        AFLogger.afErrorLog("Exception while trying to fetch attribution data. ", e);
                                    }
                                    mo89(mapM71);
                                }
                            }
                        } else {
                            if (AppsFlyerLib.f23 != null) {
                                mo88("Error connection to server: ".concat(String.valueOf(responseCode)), responseCode);
                            }
                            StringBuilder sb6 = new StringBuilder("AttributionIdFetcher response code: ");
                            sb6.append(responseCode);
                            sb6.append("  url: ");
                            sb6.append((Object) sb);
                            f.AnonymousClass4.m127(sb6.toString());
                        }
                        this.f68.decrementAndGet();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        this.f68.decrementAndGet();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                    this.f65.shutdown();
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            if (AppsFlyerLib.f23 != null) {
                mo88(th.getMessage(), 0);
            }
            AFLogger.afErrorLog(th.getMessage(), th);
            this.f68.decrementAndGet();
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            this.f65.shutdown();
        }
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008b A[Catch: Throwable -> 0x008e, TRY_LEAVE, TryCatch #3 {Throwable -> 0x008e, blocks: (B:40:0x0086, B:42:0x008b), top: B:51:0x0086 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @NonNull
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static String m44(HttpURLConnection httpURLConnection) throws Throwable {
        String string;
        InputStreamReader inputStreamReader;
        ?? sb = new StringBuilder();
        ?? line = 0;
        line = 0;
        line = 0;
        line = 0;
        try {
            try {
                try {
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    if (errorStream == null) {
                        errorStream = httpURLConnection.getInputStream();
                    }
                    inputStreamReader = new InputStreamReader(errorStream);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        while (true) {
                            try {
                                line = bufferedReader.readLine();
                                if (line == 0) {
                                    break;
                                }
                                sb.append(line);
                                sb.append('\n');
                            } catch (Throwable th) {
                                th = th;
                                line = bufferedReader;
                                StringBuilder sb2 = new StringBuilder("Could not read connection response from: ");
                                sb2.append(httpURLConnection.getURL().toString());
                                AFLogger.afErrorLog(sb2.toString(), th);
                                if (line != 0) {
                                    line.close();
                                }
                                if (inputStreamReader != null) {
                                }
                                string = sb.toString();
                                new JSONObject(string);
                                return string;
                            }
                        }
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = null;
            }
            inputStreamReader.close();
        } catch (Throwable unused) {
        }
        string = sb.toString();
        try {
            new JSONObject(string);
            return string;
        } catch (JSONException unused2) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("string_response", string);
                return jSONObject.toString();
            } catch (JSONException unused3) {
                return new JSONObject().toString();
            }
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private WeakReference<Context> f80;

        public d(Context context) {
            this.f80 = null;
            this.f80 = new WeakReference<>(context);
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (AppsFlyerLib.this.f48) {
                return;
            }
            AppsFlyerLib.this.f28 = System.currentTimeMillis();
            if (this.f80 == null) {
                return;
            }
            AppsFlyerLib.this.f48 = true;
            try {
                try {
                    String strM60 = AppsFlyerLib.m60(AppsFlyerProperties.AF_KEY);
                    synchronized (this.f80) {
                        for (RequestCacheData requestCacheData : CacheManager.getInstance().getCachedRequests(this.f80.get())) {
                            StringBuilder sb = new StringBuilder("resending request: ");
                            sb.append(requestCacheData.getRequestURL());
                            AFLogger.afInfoLog(sb.toString());
                            try {
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                long j = Long.parseLong(requestCacheData.getCacheKey(), 10);
                                AppsFlyerLib appsFlyerLib = AppsFlyerLib.this;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(requestCacheData.getRequestURL());
                                sb2.append("&isCachedRequest=true&timeincache=");
                                sb2.append(Long.toString((jCurrentTimeMillis - j) / 1000));
                                AppsFlyerLib.m37(appsFlyerLib, sb2.toString(), requestCacheData.getPostData(), strM60, (WeakReference) this.f80, requestCacheData.getCacheKey(), false);
                            } catch (Exception e) {
                                AFLogger.afErrorLog("Failed to resend cached request", e);
                            }
                        }
                    }
                } catch (Exception e2) {
                    AFLogger.afErrorLog("failed to check cache. ", e2);
                }
                AppsFlyerLib.this.f48 = false;
                AppsFlyerLib.this.f30.shutdown();
                AppsFlyerLib.m33(AppsFlyerLib.this);
            } catch (Throwable th) {
                AppsFlyerLib.this.f48 = false;
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private static float m73(Context context) {
        try {
            Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
            if (intExtra == -1 || intExtra2 == -1) {
                return 50.0f;
            }
            return (intExtra / intExtra2) * 100.0f;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return 1.0f;
        }
    }

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private static boolean m72(Context context) {
        if (context != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    for (Network network : connectivityManager.getAllNetworks()) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                        if (networkCapabilities.hasTransport(4) && !networkCapabilities.hasCapability(15)) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception e2) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e2);
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                ArrayList arrayList = new ArrayList();
                try {
                    for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                        if (networkInterface.isUp()) {
                            arrayList.add(networkInterface.getName());
                        }
                    }
                    return arrayList.contains("tun0");
                } catch (Exception e3) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e3);
                }
            }
        }
        return false;
    }

    public void setLogLevel(AFLogger.LogLevel logLevel) {
        AppsFlyerProperties.getInstance().set("logLevel", logLevel.getLevel());
    }

    @Deprecated
    public void setHostName(String str) {
        AppsFlyerProperties.getInstance().set("custom_host", str);
    }

    public void setHost(String str, String str2) {
        if (str != null) {
            AppsFlyerProperties.getInstance().set("custom_host_prefix", str);
        }
        if (str2 != null && !str2.isEmpty()) {
            AppsFlyerProperties.getInstance().set("custom_host", str2);
        } else {
            AFLogger.afWarnLog("hostName cannot be null or empty");
        }
    }

    public String getHostName() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host");
        return string != null ? string : ServerParameters.DEFAULT_HOST;
    }

    public String getHostPrefix() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host_prefix");
        return string != null ? string : "";
    }

    public String getHost() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host");
        return string != null ? string : ServerParameters.DEFAULT_HOST;
    }

    public void setMinTimeBetweenSessions(int i) {
        this.f47 = TimeUnit.SECONDS.toMillis(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    public static void m65(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
        editorEdit.putString(str, str2);
        if (Build.VERSION.SDK_INT >= 9) {
            editorEdit.apply();
        } else {
            editorEdit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    public static void m48(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
        editorEdit.putInt(str, i);
        if (Build.VERSION.SDK_INT >= 9) {
            editorEdit.apply();
        } else {
            editorEdit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    public static void m64(Context context, String str, long j) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
        editorEdit.putLong(str, j);
        if (Build.VERSION.SDK_INT >= 9) {
            editorEdit.apply();
        } else {
            editorEdit.commit();
        }
    }
}
