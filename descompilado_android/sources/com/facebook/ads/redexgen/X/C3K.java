package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.UUID;
import javax.inject.Singleton;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3K, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@Singleton
public final class C3K {
    private C3L B;
    private final Context C;

    public C3K(Context context, C3L baseBotDetectionConfig) {
        this.B = baseBotDetectionConfig;
        this.C = context;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.facebook.ads.redexgen.X.2p] */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.facebook.ads.redexgen.X.39] */
    public final void A(String productType, String eventType, String eventId) {
        if (this.B != null && this.B.R()) {
            final C3L c3l = this.B;
            final C01872o c01872oA = new Object(c3l) { // from class: com.facebook.ads.redexgen.X.2p
                public C3L B;

                {
                    this.B = c3l;
                }

                /* JADX WARN: Type inference failed for: r2v0, types: [com.facebook.ads.redexgen.X.3J] */
                @InterfaceC01641r
                public final C01872o A(String eventId2, String productType2, String eventType2, String appBundleId) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put(EnumC01932u.ti.toString(), eventId2);
                    linkedHashMap.put(EnumC01932u.bt.toString(), productType2);
                    linkedHashMap.put(EnumC01932u.sn.toString(), eventType2);
                    linkedHashMap.put(EnumC01932u.ap.toString(), appBundleId);
                    linkedHashMap.put(EnumC01932u.r1.toString(), C2O.B());
                    final String strB = B(this.B.A(), C3J.B(linkedHashMap));
                    final String str = HttpRequest.METHOD_GET;
                    C3I c3iA = new Object(strB, str) { // from class: com.facebook.ads.redexgen.X.3J
                        private static final String D = C3J.class.getSimpleName();
                        private String B;
                        private String C;

                        {
                            this.B = strB;
                            this.C = str;
                        }

                        public final C3I A() {
                            C3I c3i;
                            HttpsURLConnection conn = null;
                            try {
                                conn = (HttpsURLConnection) new URL(this.B).openConnection();
                                conn.setRequestMethod(this.C);
                                conn.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json;charset=UTF-8");
                                conn.setRequestProperty(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8");
                                conn.setDoOutput(false);
                                conn.setDoInput(true);
                                conn.connect();
                                c3i = new C3I(conn.getResponseCode(), C(conn.getInputStream()), C(conn.getErrorStream()));
                            } catch (MalformedURLException e) {
                                Log.e(D, "BaseBotDetection.HttpClient.MalformedURLException: ", e);
                                c3i = new C3I(400, "", e.getMessage());
                            } catch (IOException e2) {
                                Log.e(D, "sedData: ", e2);
                                c3i = new C3I(400, "", e2.getMessage());
                            } catch (Exception e3) {
                                Log.e(D, "HttpClient Exception: ", e3);
                                c3i = new C3I(400, "", e3.getMessage());
                            } finally {
                                if (conn != null) {
                                    conn.disconnect();
                                }
                            }
                            return c3i;
                        }

                        public static String B(Map<String, String> map) {
                            StringBuilder sb = new StringBuilder();
                            for (String str2 : map.keySet()) {
                                if (sb.length() > 0) {
                                    sb.append(Constants.RequestParameters.AMPERSAND);
                                }
                                sb.append(str2);
                                String str3 = map.get(str2);
                                if (str3 != null) {
                                    sb.append(Constants.RequestParameters.EQUAL);
                                    try {
                                        sb.append(URLEncoder.encode(str3, "UTF-8"));
                                    } catch (UnsupportedEncodingException e) {
                                        Log.e(D, "BaseBotDetection.UrlEncoding Error: ", e);
                                    }
                                }
                            }
                            return sb.toString();
                        }

                        private static String C(InputStream in) throws IOException {
                            if (in == null) {
                                return "";
                            }
                            byte[] bArr = new byte[16384];
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                int i = in.read(bArr);
                                if (i != -1) {
                                    byteArrayOutputStream.write(bArr, 0, i);
                                } else {
                                    byteArrayOutputStream.flush();
                                    return new String(byteArrayOutputStream.toByteArray());
                                }
                            }
                        }
                    }.A();
                    C01872o c01872oD = D(c3iA);
                    if (this.B.D() != null) {
                        this.B.D().wC(eventId2, productType2, eventType2, C2O.B(), EnumC01852m.NETWORK.toString(), c3iA.B(), c3iA.A());
                    }
                    return c01872oD;
                }

                private String B(String baseUrl, String requestParams) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(baseUrl);
                    sb.append(Constants.RequestParameters.AMPERSAND);
                    sb.append(requestParams);
                    if (this.B.S() && this.B.B() > Math.random() * 100.0d) {
                        sb.append(Constants.RequestParameters.AMPERSAND);
                        sb.append(EnumC01932u._p.toString());
                        sb.append(Constants.RequestParameters.EQUAL);
                        sb.append(C(this.B.C()));
                    }
                    return sb.toString();
                }

                private static String C(int size) {
                    StringBuilder sb = new StringBuilder(size);
                    for (int i = 0; i < size / "bbbbbbbbbb".length(); i++) {
                        sb.append("bbbbbbbbbb");
                    }
                    return sb.toString();
                }

                @InterfaceC01641r
                private static C01872o D(C3I httpResponse) {
                    String strB = httpResponse.B();
                    if (strB != null) {
                        try {
                            if (httpResponse.C() == 200) {
                                JSONObject jSONObject = new JSONObject(strB);
                                String postback = jSONObject.getString("sid");
                                String response = jSONObject.getString("postback");
                                return new C01872o(postback, response);
                            }
                        } catch (JSONException e) {
                            Log.e("ContentValues", "Network Signal Response Json Parsing Error ", e);
                            return null;
                        }
                    }
                    throw new JSONException("Null Response for Config Request");
                }
            }.A(eventId, productType, eventType, this.C.getPackageName());
            if (c01872oA != null && this.B.P()) {
                final Context context = this.C;
                final C3L c3l2 = this.B;
                new Object(context, c3l2, c01872oA) { // from class: com.facebook.ads.redexgen.X.39
                    private static final String G = AnonymousClass39.class.getSimpleName();
                    private final C3L B;
                    private final Context C;
                    private final C01872o E;
                    private JSONObject F = new JSONObject();
                    private JSONObject D = new JSONObject();

                    {
                        this.C = context;
                        this.B = c3l2;
                        this.E = c01872oA;
                    }

                    /* JADX WARN: Type inference failed for: r1v10, types: [com.facebook.ads.redexgen.X.3F] */
                    /* JADX WARN: Type inference failed for: r1v13, types: [com.facebook.ads.redexgen.X.35] */
                    /* JADX WARN: Type inference failed for: r1v15, types: [com.facebook.ads.redexgen.X.3D] */
                    /* JADX WARN: Type inference failed for: r1v3, types: [com.facebook.ads.redexgen.X.33] */
                    /* JADX WARN: Type inference failed for: r1v4, types: [com.facebook.ads.redexgen.X.3C] */
                    /* JADX WARN: Type inference failed for: r1v5, types: [com.facebook.ads.redexgen.X.38] */
                    /* JADX WARN: Type inference failed for: r1v6, types: [com.facebook.ads.redexgen.X.3E] */
                    /* JADX WARN: Type inference failed for: r1v8, types: [com.facebook.ads.redexgen.X.2x] */
                    /* JADX WARN: Type inference failed for: r1v9, types: [com.facebook.ads.redexgen.X.3A] */
                    /* JADX WARN: Type inference failed for: r2v1, types: [com.facebook.ads.redexgen.X.3B] */
                    /* JADX WARN: Type inference failed for: r2v3, types: [com.facebook.ads.redexgen.X.32] */
                    /* JADX WARN: Type inference failed for: r2v4, types: [com.facebook.ads.redexgen.X.3G] */
                    /* JADX WARN: Type inference failed for: r3v1, types: [com.facebook.ads.redexgen.X.37] */
                    public final void A(String eventId2, String productType2, String eventType2) {
                        if (!this.B.P()) {
                            return;
                        }
                        if (this.B.J()) {
                            final Context context2 = this.C;
                            new C3H(context2) { // from class: com.facebook.ads.redexgen.X.3D
                                private Context B;
                                private Intent C;
                                private boolean D = false;

                                {
                                    this.B = context2;
                                    this.C = this.B.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                                }

                                private final void B() {
                                    if (C3H.B >= 21) {
                                        BatteryManager batteryManager = (BatteryManager) this.B.getSystemService("batterymanager");
                                        if (batteryManager != null) {
                                            A("battery_current_now", Integer.valueOf(batteryManager.getIntProperty(2)), true);
                                            return;
                                        } else {
                                            A("battery_current_now", "error", false);
                                            return;
                                        }
                                    }
                                    A("battery_current_now", "unknown", false);
                                }

                                private final void C() {
                                    if (this.D && this.C != null) {
                                        A("battery_health", Integer.valueOf(this.C.getIntExtra("health", 0)), true);
                                    } else {
                                        A("battery_health", "unknown", false);
                                    }
                                }

                                private final void D() {
                                    if (this.D && this.C != null) {
                                        int intExtra = this.C.getIntExtra("level", -1);
                                        int intExtra2 = this.C.getIntExtra("scale", -1);
                                        if (intExtra != -1 && intExtra2 > 0) {
                                            A("battery_percentage", Integer.valueOf((int) ((intExtra / intExtra2) * 100.0f)), true);
                                            return;
                                        } else {
                                            A("battery_percentage", "error", false);
                                            return;
                                        }
                                    }
                                    A("battery_percentage", "unknown", false);
                                }

                                private final void E() {
                                    if (this.D && this.C != null) {
                                        A("battery_status", Integer.valueOf(this.C.getIntExtra("status", 0)), true);
                                    } else {
                                        A("battery_status", "unknown", false);
                                    }
                                }

                                private final void F() {
                                    if (this.D && this.C != null) {
                                        A("battery_temperature", Float.valueOf(this.C.getIntExtra("temperature", 0) / 10.0f), true);
                                    } else {
                                        A("battery_temperature", "unknown", false);
                                    }
                                }

                                private final void G() {
                                    if (this.D && this.C != null) {
                                        A("battery_plugged", Integer.valueOf(this.C.getIntExtra("plugged", 0)), true);
                                    } else {
                                        A("battery_plugged", "unknown", false);
                                    }
                                }

                                private final void H() {
                                    if (this.C != null) {
                                        this.D = this.C.getBooleanExtra("present", true);
                                    }
                                    A("battery_present", Boolean.valueOf(this.D), true);
                                }

                                public final void I() {
                                    H();
                                    G();
                                    D();
                                    B();
                                    C();
                                    E();
                                    F();
                                }
                            }.I();
                        }
                        if (this.B.E()) {
                            final Context context3 = this.C;
                            final boolean zN = this.B.N();
                            new C3H(context3, zN) { // from class: com.facebook.ads.redexgen.X.3G
                                private final Context B;
                                private final boolean C;
                                private final ActivityManager.RunningAppProcessInfo D = new ActivityManager.RunningAppProcessInfo();

                                {
                                    this.B = context3;
                                    this.C = zN;
                                }

                                private void B() {
                                    if (C3H.B >= 16) {
                                        Integer numC = C();
                                        if (numC != null) {
                                            A("app_importance", numC, true);
                                            A("app_visible", Boolean.valueOf(numC.intValue() == 100 || numC.intValue() == 200), true);
                                            A("app_foreground", Boolean.valueOf(numC.intValue() == 100), true);
                                            return;
                                        } else {
                                            A("app_importance", "error", false);
                                            A("app_visible", "error", false);
                                            A("app_foreground", "error", false);
                                            return;
                                        }
                                    }
                                    A("app_importance", "unknown", false);
                                    A("app_visible", "unknown", false);
                                    A("app_foreground", "unknown", false);
                                }

                                @InterfaceC01641r
                                private Integer C() {
                                    if (C3H.B < 16 || this.D == null) {
                                        return null;
                                    }
                                    ActivityManager.getMyMemoryState(this.D);
                                    return Integer.valueOf(this.D.importance);
                                }

                                private void D() {
                                    ActivityManager activityManager = (ActivityManager) this.B.getSystemService("activity");
                                    if (activityManager != null) {
                                        ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
                                        if (deviceConfigurationInfo != null) {
                                            A("open_gles_version", deviceConfigurationInfo.getGlEsVersion(), true);
                                            A("keyboard_kind", Integer.valueOf(deviceConfigurationInfo.reqNavigation), true);
                                            A("input_preference", Integer.valueOf(deviceConfigurationInfo.reqKeyboardType), true);
                                            A("touchscreen_kind", Integer.valueOf(deviceConfigurationInfo.reqTouchScreen), true);
                                            return;
                                        }
                                        A("open_gles_version", "error", false);
                                        A("keyboard_kind", "error", false);
                                        A("input_preference", "error", false);
                                        A("touchscreen_kind", "error", false);
                                        return;
                                    }
                                    A("open_gles_version", "error", false);
                                    A("keyboard_kind", "error", false);
                                    A("input_preference", "error", false);
                                    A("touchscreen_kind", "error", false);
                                }

                                private void E() {
                                    ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
                                    ((ActivityManager) this.B.getSystemService("activity")).getMemoryInfo(mi);
                                    A("device_total_memory", Long.valueOf(mi.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), true);
                                }

                                public final void F() {
                                    B();
                                    D();
                                    if (this.C) {
                                        E();
                                    }
                                }
                            }.F();
                        }
                        if (this.B.U()) {
                            new C3H(this.C) { // from class: com.facebook.ads.redexgen.X.35
                                private final PowerManager B;

                                {
                                    this.B = (PowerManager) context.getSystemService("power");
                                }

                                private void B() {
                                    if (this.B != null) {
                                        if (C3H.B >= 23) {
                                            A("device_idle", Boolean.valueOf(this.B.isDeviceIdleMode()), true);
                                            return;
                                        } else {
                                            A("device_idle", "unknown", false);
                                            return;
                                        }
                                    }
                                    A("device_idle", "error", false);
                                }

                                private void C() {
                                    Boolean boolE = E();
                                    if (boolE != null) {
                                        A("display_active", boolE, true);
                                    } else {
                                        A("display_active", "error", false);
                                    }
                                }

                                private void D() {
                                    if (this.B != null) {
                                        if (C3H.B >= 21) {
                                            A("power_safe_mode", Boolean.valueOf(this.B.isPowerSaveMode()), true);
                                            return;
                                        } else {
                                            A("power_safe_mode", "unknown", false);
                                            return;
                                        }
                                    }
                                    A("power_safe_mode", "error", false);
                                }

                                @InterfaceC01641r
                                private Boolean E() {
                                    if (this.B == null || C3H.B <= 20) {
                                        return null;
                                    }
                                    return Boolean.valueOf(this.B.isInteractive());
                                }

                                /* JADX INFO: renamed from: E, reason: collision with other method in class */
                                public final void m230E() {
                                    B();
                                    C();
                                    D();
                                }
                            }.m230E();
                        }
                        if (this.B.X()) {
                            new C3H(this.C, this.B.V()) { // from class: com.facebook.ads.redexgen.X.32
                                private final Context B;
                                private final boolean C;
                                private final Runtime D = Runtime.getRuntime();

                                {
                                    this.B = context;
                                    this.C = isRootInfoNativeSignalEnabled;
                                }

                                private static boolean B() {
                                    boolean z = false;
                                    Process processExec = null;
                                    try {
                                        processExec = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
                                        if (new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine() != null) {
                                            z = true;
                                        }
                                        if (processExec != null) {
                                            processExec.destroy();
                                        }
                                    } catch (Exception e) {
                                    } finally {
                                        if (processExec != null) {
                                            processExec.destroy();
                                        }
                                    }
                                    return z;
                                }

                                private void C() {
                                    A("app_package_name", this.B.getPackageName(), true);
                                }

                                private static Integer D(Context context4) {
                                    int iValueOf = -1;
                                    try {
                                        iValueOf = Integer.valueOf(Settings.System.getInt(context4.getContentResolver(), "screen_brightness"));
                                        return iValueOf;
                                    } catch (Settings.SettingNotFoundException e) {
                                        return iValueOf;
                                    }
                                }

                                private void E() {
                                    A("current_brightness", D(this.B), true);
                                }

                                private void F() {
                                    DisplayMetrics displayMetrics = this.B.getResources().getDisplayMetrics();
                                    A("display_density", Float.valueOf(displayMetrics.density), true);
                                    A("display_density_dpi", Integer.valueOf(displayMetrics.densityDpi), true);
                                    A("display_width_pixels", Integer.valueOf(displayMetrics.widthPixels), true);
                                    A("display_height_pixels", Integer.valueOf(displayMetrics.heightPixels), true);
                                    A("display_x_dpi", Float.valueOf(displayMetrics.xdpi), true);
                                    A("display_y_dpi", Float.valueOf(displayMetrics.ydpi), true);
                                }

                                private void G() {
                                    B();
                                    A("device_rooted", Boolean.valueOf((K() || R() || Q()) ? true : B()), true);
                                }

                                private void H() {
                                    try {
                                        A("locale_language", Locale.getDefault().getISO3Language(), true);
                                    } catch (MissingResourceException e) {
                                        A("locale_language", "error", false);
                                    }
                                    try {
                                        A("locale_country", Locale.getDefault().getISO3Country(), true);
                                    } catch (MissingResourceException e2) {
                                        A("locale_country", "error", false);
                                    }
                                    A("locale_name", Locale.getDefault().getDisplayName(), true);
                                }

                                private void I() {
                                    A("processors", Integer.valueOf(this.D.availableProcessors()), true);
                                }

                                private void J() {
                                    try {
                                        A("screen_off_timeout", Integer.valueOf(Settings.System.getInt(this.B.getContentResolver(), "screen_off_timeout")), true);
                                    } catch (Exception e) {
                                        A("screen_off_timeout", "error", false);
                                    }
                                }

                                /* JADX INFO: renamed from: K, reason: collision with other method in class */
                                public final void m229K() {
                                    E();
                                    J();
                                    C();
                                    O();
                                    L();
                                    F();
                                    H();
                                    N();
                                    I();
                                    M();
                                    P();
                                    if (this.C) {
                                        G();
                                    }
                                }

                                private static boolean K() {
                                    String str = Build.TAGS;
                                    return str != null && str.contains("test-keys");
                                }

                                private void L() {
                                    A("time_zone_name", TimeZone.getDefault().getDisplayName(), true);
                                    A("time_zone", Integer.valueOf(TimeZone.getDefault().getRawOffset()), true);
                                }

                                private void M() {
                                    A("total_memory", Long.valueOf(this.D.totalMemory()), true);
                                }

                                private void N() {
                                    A("total_bytes_rx", Long.valueOf(TrafficStats.getTotalRxBytes()), true);
                                    A("total_bytes_tx", Long.valueOf(TrafficStats.getTotalTxBytes()), true);
                                    A("mobile_bytes_rx", Long.valueOf(TrafficStats.getMobileRxBytes()), true);
                                    A("mobile_bytes_tx", Long.valueOf(TrafficStats.getMobileTxBytes()), true);
                                }

                                private void O() {
                                    A("uptime", Long.valueOf(SystemClock.uptimeMillis()), true);
                                }

                                private void P() {
                                    A("wmode", false, true);
                                }

                                private static boolean Q() {
                                    return new File("/system/app/Superuser.apk").exists() || new File("/system/app/supersu.apk").exists() || new File("/system/app/su.apk").exists() || !new File("/etc/security/otacerts.zip").exists();
                                }

                                private static boolean R() {
                                    for (String str : new String[]{"/system/bin/su", "/system/xbin/su", "/sbin/su", "/system/su", "/system/bin/.ext/.su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/data/local/xbin/su", "/data/local/bin/su"}) {
                                        if (new File(str).exists()) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                            }.m229K();
                        }
                        if (this.B.T()) {
                            new C3H(this.C, this.B.F(), this.B.G()) { // from class: com.facebook.ads.redexgen.X.37
                                private final Context B;
                                private final boolean C;
                                private final boolean D;
                                private final PackageManager E;

                                @InterfaceC01641r
                                private final PackageInfo F;

                                {
                                    this.B = context;
                                    this.E = context.getPackageManager();
                                    this.F = V(context);
                                    this.C = isApkHashSignalEnabled;
                                    this.D = isAppServiceEnabled;
                                }

                                private static String B(File file) throws Exception {
                                    int i;
                                    InputStream inputStream = null;
                                    try {
                                        InputStream fis = new BufferedInputStream(new FileInputStream(file), 1024);
                                        try {
                                            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
                                            byte[] buffer = new byte[1024];
                                            do {
                                                i = fis.read(buffer);
                                                if (i > 0) {
                                                    messageDigest.update(buffer, 0, i);
                                                }
                                            } while (i != -1);
                                            String string = new BigInteger(1, messageDigest.digest()).toString(16);
                                            if (fis != null) {
                                                fis.close();
                                            }
                                            return string;
                                        } catch (Throwable th) {
                                            th = th;
                                            inputStream = fis;
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }

                                private void C() {
                                    JSONArray mActivities = new JSONArray();
                                    try {
                                        ActivityInfo[] activityInfoArr = this.E.getPackageInfo(this.B.getPackageName(), 1).activities;
                                        if (activityInfoArr != null) {
                                            int i = activityInfoArr.length < 100 ? activityInfoArr.length : 100;
                                            for (int i2 = 0; i2 < i; i2++) {
                                                mActivities.put(activityInfoArr[i2].name);
                                            }
                                            A("activities_count", Integer.valueOf(activityInfoArr.length), true);
                                            A("activities", mActivities, true);
                                            return;
                                        }
                                        A("activities_count", "error", false);
                                        A("activities", "error", false);
                                    } catch (Exception e) {
                                        A("activities", "error", false);
                                        A("activities_count", "error", false);
                                    }
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private void D() {
                                    try {
                                        String packageHash = B(new File(this.E.getApplicationInfo(this.B.getPackageName(), 0).sourceDir));
                                        A("apk_hash", packageHash, true);
                                    } catch (Exception e) {
                                        A("apk_hash", "error", false);
                                    }
                                }

                                private void E() {
                                    A("app_installer_package_name", this.E.getInstallerPackageName(this.B.getPackageName()), true);
                                }

                                private void F() {
                                    try {
                                        A("app_public_path", this.E.getApplicationInfo(this.B.getPackageName(), 0).publicSourceDir, true);
                                    } catch (Exception e) {
                                        A("app_public_path", "error", false);
                                    }
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private void G() {
                                    JSONArray jSONArray = new JSONArray();
                                    try {
                                        ServiceInfo[] serviceInfoArr = this.E.getPackageInfo(this.B.getPackageName(), 4).services;
                                        if (serviceInfoArr != null) {
                                            int length = serviceInfoArr.length < 100 ? serviceInfoArr.length : 100;
                                            for (int i = 0; i < length; i++) {
                                                jSONArray.put(serviceInfoArr[i].name);
                                            }
                                        }
                                        A("app_services_count", Integer.valueOf(serviceInfoArr.length), true);
                                        A("app_services", jSONArray, true);
                                    } catch (Exception e) {
                                        A("app_services", "error", false);
                                        A("app_services_count", "error", false);
                                    }
                                }

                                private void H() {
                                    File file;
                                    try {
                                        ApplicationInfo applicationInfo = this.E.getApplicationInfo(this.B.getPackageName(), 0);
                                        if (applicationInfo != null && (file = new File(applicationInfo.publicSourceDir)) != null) {
                                            A("app_size", Long.valueOf(file.length()), true);
                                        } else {
                                            A("app_size", "error", false);
                                        }
                                    } catch (Exception e) {
                                        A("app_size", "error", false);
                                    }
                                }

                                private void I() {
                                    A("camera_rear", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.camera")), true);
                                    A("camera_front", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.camera.front")), true);
                                    A("camera_flash", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.camera.flash")), true);
                                }

                                private void J() {
                                    A("sensor_accelerometer", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.accelerometer")), true);
                                    A("sensor_barometer", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.barometer")), true);
                                    A("sensor_gyro", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.gyroscope")), true);
                                    A("sensor_compass", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.compass")), true);
                                    A("sensor_proximity", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.proximity")), true);
                                    if (C3H.B >= 19) {
                                        A("sensor_step_count", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.stepcounter")), true);
                                    } else {
                                        A("sensor_step_count", "unknown", false);
                                    }
                                    if (C3H.B >= 19) {
                                        A("sensor_step_detector", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.stepdetector")), true);
                                    } else {
                                        A("sensor_step_detector", "unknown", false);
                                    }
                                    if (C3H.B >= 20) {
                                        A("sensor_heart", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.heartrate")), true);
                                    } else {
                                        A("sensor_heart", "unknown", false);
                                    }
                                    if (C3H.B >= 21) {
                                        A("sensor_ambient_temperature", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.ambient_temperature")), true);
                                    } else {
                                        A("sensor_ambient_temperature", "unknown", false);
                                    }
                                    if (C3H.B >= 23) {
                                        A("sensor_hifi", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.sensor.hifi_sensors")), true);
                                    } else {
                                        A("sensor_hifi", "unknown", false);
                                    }
                                }

                                private void K() {
                                    A("bluetooth_le_present", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.bluetooth_le")), true);
                                }

                                private void L() {
                                    A("bluetooth_present", Boolean.valueOf(C(this.B)), true);
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private void M() {
                                    try {
                                        Class<?> cls = Class.forName("com.facebook.ads.internal.settings.AdSdkVersion");
                                        if (cls != null) {
                                            A("client_sdk_name", "com.facebook.ads.internal.settings.AdSdkVersion", true);
                                            Field field = cls.getDeclaredField("BUILD");
                                            if (field != null) {
                                                A("client_sdk_code", String.valueOf(field.get(cls)), true);
                                            }
                                        } else {
                                            A("client_sdk_name", this.B.getPackageName(), true);
                                            A("client_sdk_code", String.valueOf(this.F.versionCode), true);
                                        }
                                    } catch (Exception e) {
                                        A("client_sdk_name", this.B.getPackageName(), true);
                                        A("client_sdk_code", String.valueOf(this.F.versionCode), true);
                                    }
                                }

                                private void N() {
                                    if (C3H.B >= 13) {
                                        A("screen_portrait", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.screen.portrait")), true);
                                        A("screen_landscape", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.screen.landscape")), true);
                                    } else {
                                        A("screen_portrait", "unknown", false);
                                        A("screen_landscape", "unknown", false);
                                    }
                                    if (C3H.B >= 26) {
                                        A("activities_on_secondary_displays", Boolean.valueOf(this.E.hasSystemFeature("android.software.activities_on_secondary_displays")), true);
                                    } else {
                                        A("activities_on_secondary_displays", "unknown", false);
                                    }
                                }

                                private void O() {
                                    try {
                                        A("fb_sdk_version", this.E.getPackageInfo(this.B.getPackageName(), 128).versionName, true);
                                    } catch (PackageManager.NameNotFoundException e) {
                                        A("fb_sdk_version", "error", false);
                                    }
                                }

                                private void P() {
                                    A("nfc", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.nfc")), true);
                                    if (C3H.B >= 12) {
                                        A("usb_host", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.usb.host")), true);
                                    } else {
                                        A("usb_host", "unknown", false);
                                    }
                                    if (C3H.B >= 14) {
                                        A("wifi_direct", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.wifi.direct")), true);
                                    } else {
                                        A("wifi_direct", "unknown", false);
                                    }
                                    if (C3H.B >= 24) {
                                        A("ethernet", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.ethernet")), true);
                                    } else {
                                        A("ethernet", "unknown", false);
                                    }
                                }

                                private void Q() {
                                    JSONArray jSONArray = new JSONArray();
                                    if (this.F != null) {
                                        String[] strArr = this.F.requestedPermissions;
                                        if (strArr != null) {
                                            for (String str : strArr) {
                                                jSONArray.put(str);
                                            }
                                        }
                                        A("manifest_permissions", jSONArray, true);
                                        return;
                                    }
                                    A("manifest_permissions", "error", false);
                                }

                                private void R() {
                                    if (C3H.B >= 16) {
                                        A("television", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.type.television")), true);
                                    } else {
                                        A("television", "unknown", false);
                                    }
                                    if (C3H.B >= 20) {
                                        A("watch", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.type.watch")), true);
                                    } else {
                                        A("watch", "unknown", false);
                                    }
                                    if (C3H.B >= 21) {
                                        A("gamepad", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.gamepad")), true);
                                        A("live_tv", Boolean.valueOf(this.E.hasSystemFeature("android.software.live_tv")), true);
                                    } else {
                                        A("gamepad", "unknown", false);
                                        A("live_tv", "unknown", false);
                                    }
                                    if (C3H.B >= 23) {
                                        A("automotive", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.type.automotive")), true);
                                    } else {
                                        A("automotive", "unknown", false);
                                    }
                                    if (C3H.B >= 26) {
                                        A("embedded", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.type.embedded")), true);
                                    } else {
                                        A("embedded", "unknown", false);
                                    }
                                }

                                private void S() {
                                    if (C3H.B >= 18) {
                                        A("app_widgets", Boolean.valueOf(this.E.hasSystemFeature("android.software.app_widgets")), true);
                                    } else {
                                        A("app_widgets", "unknown", false);
                                    }
                                    if (C3H.B >= 21) {
                                        A("open_gles_aep", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.opengles.aep")), true);
                                    } else {
                                        A("open_gles_aep", "unknown", false);
                                    }
                                }

                                private void T() {
                                    A("microphone", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.microphone")), true);
                                    if (C3H.B >= 20) {
                                        A("printing", Boolean.valueOf(this.E.hasSystemFeature("android.software.print")), true);
                                    } else {
                                        A("printing", "unknown", false);
                                    }
                                    if (C3H.B >= 23) {
                                        A("dactylogram", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.fingerprint")), true);
                                    } else {
                                        A("dactylogram", "unknown", false);
                                    }
                                }

                                private void U() {
                                    if (this.F != null) {
                                        A("app_install_date", Long.valueOf(this.F.firstInstallTime), true);
                                        A("app_last_update", Long.valueOf(this.F.lastUpdateTime), true);
                                        A("pack_version_code", Integer.valueOf(this.F.versionCode), true);
                                        A("pack_version_name", this.F.versionName, true);
                                        if (C3H.B > 21) {
                                            A("pack_base_revision_code", Integer.valueOf(this.F.baseRevisionCode), true);
                                            return;
                                        } else {
                                            A("pack_base_revision_code", "unknown", false);
                                            return;
                                        }
                                    }
                                    A("app_install_date", "error", false);
                                    A("app_last_update", "error", false);
                                    A("pack_version_code", "error", false);
                                    A("pack_version_name", "error", false);
                                    A("pack_base_revision_code", "error", false);
                                }

                                @InterfaceC01641r
                                private PackageInfo V(Context context4) {
                                    try {
                                        return this.E.getPackageInfo(context4.getPackageName(), 4096);
                                    } catch (Exception e) {
                                        return null;
                                    }
                                }

                                public final void V() {
                                    C();
                                    E();
                                    F();
                                    H();
                                    I();
                                    J();
                                    K();
                                    L();
                                    N();
                                    P();
                                    Q();
                                    R();
                                    S();
                                    T();
                                    U();
                                    W();
                                    Y();
                                    X();
                                    M();
                                    O();
                                    if (this.C) {
                                        D();
                                    }
                                    if (this.D) {
                                        G();
                                    }
                                }

                                private void W() {
                                    A("telephony_present", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.telephony")), true);
                                    A("telephony_cdma_present", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.telephony.cdma")), true);
                                    A("telephony_gsm_present", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.telephony.gsm")), true);
                                }

                                private void X() {
                                    A("touchscreen_multitouch", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.touchscreen.multitouch")), true);
                                    A("touchscreen_multitouch_distinct", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.touchscreen.multitouch.distinct")), true);
                                    A("touchscreen_multitouch_jazzhand", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand")), true);
                                    A("touchscreen", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.touchscreen")), true);
                                    A("touchscreen_faketouch_support", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.faketouch")), true);
                                    if (C3H.B >= 13) {
                                        A("touchscreen_faketouch_multitouch_distinct", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.faketouch.multitouch.distinct")), true);
                                    } else {
                                        A("touchscreen_faketouch_multitouch_distinct", "unknown", false);
                                    }
                                    if (C3H.B >= 13) {
                                        A("touchscreen_faketouch_multitouch_jazzhand", Boolean.valueOf(this.E.hasSystemFeature("android.hardware.faketouch.multitouch.jazzhand")), true);
                                    } else {
                                        A("touchscreen_faketouch_multitouch_jazzhand", "unknown", false);
                                    }
                                }

                                private void Y() {
                                    A("wifi_present", Boolean.valueOf(D(this.B)), true);
                                }
                            }.V();
                        }
                        if (this.B.H()) {
                            final Context context4 = this.C;
                            new C3H(context4) { // from class: com.facebook.ads.redexgen.X.3F
                                private final ApplicationInfo B;
                                private final Context C;

                                {
                                    this.C = context4;
                                    this.B = context4.getApplicationInfo();
                                }

                                private void B() {
                                    A("app_flags", Integer.valueOf(this.B.flags), true);
                                }

                                private void C() {
                                    A("target_sdk_version", Integer.valueOf(this.B.targetSdkVersion), true);
                                    A("app_data_dir", this.B.dataDir, true);
                                    A("app_process_name", this.B.processName, true);
                                    A("app_source_dir", this.B.sourceDir, true);
                                    A("app_task_affinity", this.B.taskAffinity, true);
                                    A("app_theme", Integer.valueOf(this.B.theme), true);
                                    if (C3H.B > 23) {
                                        A("device_protected_data_dir", this.B.deviceProtectedDataDir, true);
                                        A("min_sdk_version", Integer.valueOf(this.B.minSdkVersion), true);
                                    } else {
                                        A("device_protected_data_dir", "unknown", false);
                                        A("min_sdk_version", "unknown", false);
                                    }
                                    if (C3H.B > 25) {
                                        A("app_category", Integer.valueOf(this.B.category), true);
                                    } else {
                                        A("app_category", "unknown", false);
                                    }
                                }

                                private void D() {
                                    A("app_debuggable", Boolean.valueOf((this.B.flags & 2) != 0), true);
                                }

                                private void E() {
                                    A("persistent", Boolean.valueOf((this.B.flags & 8) != 0), true);
                                }

                                private void F() {
                                    A("ts", Long.valueOf(System.currentTimeMillis()), true);
                                }

                                private void G() {
                                    SharedPreferences sharedPreferences = this.C.getSharedPreferences("wo_pref_store", 0);
                                    String string = sharedPreferences.getString("wo_pref_store_key", null);
                                    if (string == null) {
                                        string = UUID.randomUUID().toString();
                                        sharedPreferences.edit().putString("wo_pref_store_key", string).apply();
                                    }
                                    A("wo_ssid", string, true);
                                }

                                public final void H() {
                                    D();
                                    E();
                                    B();
                                    C();
                                    G();
                                    F();
                                }
                            }.H();
                        }
                        if (this.B.O()) {
                            final Context context5 = this.C;
                            new C3H(context5) { // from class: com.facebook.ads.redexgen.X.3A
                                private final KeyguardManager B;

                                {
                                    this.B = (KeyguardManager) context5.getSystemService("keyguard");
                                }

                                private void B() {
                                    if (this.B != null) {
                                        if (C3H.B >= 22) {
                                            A("device_locked", Boolean.valueOf(this.B.isDeviceLocked()), true);
                                            return;
                                        } else {
                                            A("device_locked", "unknown", false);
                                            return;
                                        }
                                    }
                                    A("device_locked", "error", false);
                                }

                                private void C() {
                                    if (this.B != null) {
                                        if (C3H.B >= 23) {
                                            A("device_secure", Boolean.valueOf(this.B.isDeviceSecure()), true);
                                            return;
                                        } else {
                                            A("device_secure", "unknown", false);
                                            return;
                                        }
                                    }
                                    A("device_secure", "error", false);
                                }

                                private void D() {
                                    if (this.B != null) {
                                        if (C3H.B >= 16) {
                                            A("keyguard_locked", Boolean.valueOf(this.B.isKeyguardLocked()), true);
                                            return;
                                        } else {
                                            A("keyguard_locked", "unknown", false);
                                            return;
                                        }
                                    }
                                    A("keyguard_locked", "error", false);
                                }

                                private void E() {
                                    if (this.B != null) {
                                        if (C3H.B >= 16) {
                                            A("keyguard_secure", Boolean.valueOf(this.B.isKeyguardSecure()), true);
                                            return;
                                        } else {
                                            A("keyguard_secure", "unknown", false);
                                            return;
                                        }
                                    }
                                    A("keyguard_secure", "error", false);
                                }

                                public final void F() {
                                    B();
                                    C();
                                    D();
                                    E();
                                }
                            }.F();
                        }
                        if (this.B.Y()) {
                            new C3H(this.C) { // from class: com.facebook.ads.redexgen.X.2x
                                private final Context B;
                                private final TelephonyManager C;

                                {
                                    this.B = context;
                                    this.C = (TelephonyManager) context.getSystemService("phone");
                                }

                                private void B() {
                                    if (C3H.B >= 26) {
                                        if (this.C != null) {
                                            if (B(this.B, "android.permission.ACCESS_NETWORK_STATE") || B(this.B, "android.permission.MODIFY_PHONE_STATE")) {
                                                A("mobile_data_enabled", Boolean.valueOf(this.C.isDataEnabled()), true);
                                                return;
                                            } else {
                                                A("mobile_data_enabled", "no_permission", false);
                                                return;
                                            }
                                        }
                                        A("mobile_data_enabled", "error", false);
                                        return;
                                    }
                                    A("mobile_data_enabled", "unknown", false);
                                }

                                private void C() {
                                    if (this.C != null) {
                                        A("network_type", Integer.valueOf(this.C.getNetworkType()), true);
                                    } else {
                                        A("network_type", "error", false);
                                    }
                                }

                                private void D() {
                                    if (this.C != null) {
                                        A("sim_operator_name", this.C.getSimOperatorName(), true);
                                    } else {
                                        A("sim_operator_name", "error", false);
                                    }
                                }

                                private void E() {
                                    if (this.C != null) {
                                        A("network_operator", this.C.getNetworkOperator(), true);
                                    } else {
                                        A("network_operator", "error", false);
                                    }
                                }

                                private void F() {
                                    if (this.C != null) {
                                        A("network_operator_name", this.C.getNetworkOperatorName(), true);
                                    } else {
                                        A("network_operator_name", "error", false);
                                    }
                                }

                                private void G() {
                                    if (this.C != null) {
                                        A("phone_type", Integer.valueOf(this.C.getPhoneType()), true);
                                    } else {
                                        A("phone_type", "error", false);
                                    }
                                }

                                private void H() {
                                    if (this.C != null) {
                                        A("sim_country", this.C.getSimCountryIso(), true);
                                    } else {
                                        A("sim_country", "error", false);
                                    }
                                }

                                private void I() {
                                    if (this.C != null) {
                                        A("sim_state", Integer.valueOf(this.C.getSimState()), true);
                                    } else {
                                        A("sim_state", "error", false);
                                    }
                                }

                                public final void J() {
                                    B();
                                    C();
                                    D();
                                    E();
                                    F();
                                    G();
                                    H();
                                    I();
                                }
                            }.J();
                        }
                        if (this.B.M()) {
                            final Context context6 = this.C;
                            final boolean zK = this.B.K();
                            new C3H(context6, zK) { // from class: com.facebook.ads.redexgen.X.3B
                                private final Context B;
                                private final boolean C;

                                {
                                    this.B = context6;
                                    this.C = zK;
                                }

                                private static String B(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
                                    return Base64.encodeToString(MessageDigest.getInstance("MD5").digest(message.getBytes("UTF-8")), 2);
                                }

                                private void C() {
                                    A("accessibility_services_enabled", Boolean.valueOf(Settings.Secure.getInt(this.B.getContentResolver(), "accessibility_enabled", 0) == 1), true);
                                }

                                private void D() {
                                    if (C3H.B >= 24) {
                                        try {
                                            A("boot_count", Integer.valueOf(Settings.Global.getInt(this.B.getContentResolver(), "boot_count")), true);
                                            return;
                                        } catch (Settings.SettingNotFoundException e) {
                                            A("boot_count", "error", false);
                                            return;
                                        }
                                    }
                                    A("boot_count", "unknown", false);
                                }

                                private void E() {
                                    if (C3H.B >= 17) {
                                        A("install_non_market_apps_enabled", Boolean.valueOf(Settings.Secure.getInt(this.B.getContentResolver(), "install_non_market_apps", 0) == 1), true);
                                    } else {
                                        A("install_non_market_apps_enabled", "unknown", false);
                                    }
                                }

                                private void F() {
                                    if (C3H.B >= 17) {
                                        A("adb_enabled", Boolean.valueOf(String.valueOf(Settings.Global.getString(this.B.getContentResolver(), "adb_enabled")).equals("1")), true);
                                    } else {
                                        A("adb_enabled", "unknown", false);
                                    }
                                }

                                private void G() {
                                    if (C3H.B >= 17) {
                                        if (C(this.B)) {
                                            A("bluetooth_enabled", Boolean.valueOf(Settings.Secure.getInt(this.B.getContentResolver(), "bluetooth_on", 0) == 1), true);
                                            return;
                                        } else {
                                            A("bluetooth_enabled", false, true);
                                            return;
                                        }
                                    }
                                    A("bluetooth_enabled", "unknown", false);
                                }

                                private void H() {
                                    if (C3H.B >= 17) {
                                        A("developer_mode_enabled", Boolean.valueOf(Settings.Secure.getInt(this.B.getContentResolver(), "development_settings_enabled", 0) == 1), true);
                                    } else {
                                        A("developer_mode_enabled", "unknown", false);
                                    }
                                }

                                private void I() {
                                    if (C3H.B >= 17) {
                                        A("usb_mass_storage_enabled", Boolean.valueOf(Settings.Secure.getInt(this.B.getContentResolver(), "usb_mass_storage_enabled", 0) == 1), true);
                                    } else {
                                        A("usb_mass_storage_enabled", "unknown", false);
                                    }
                                }

                                private void J() {
                                    if (C3H.B >= 17) {
                                        if (D(this.B)) {
                                            A("wifi_enabled", Boolean.valueOf(Settings.Secure.getInt(this.B.getContentResolver(), "wifi_on", 0) == 1), true);
                                            return;
                                        } else {
                                            A("wifi_enabled", false, true);
                                            return;
                                        }
                                    }
                                    A("wifi_enabled", "unknown", false);
                                }

                                @SuppressLint({"MissingPermission"})
                                private final void K() {
                                    BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                                    try {
                                        String name = defaultAdapter.getName();
                                        if (name == null) {
                                            name = defaultAdapter.getAddress();
                                        }
                                        String name2 = B(name);
                                        A("bluetooth_name", name2, true);
                                    } catch (UnsupportedEncodingException | SecurityException | NoSuchAlgorithmException e) {
                                        A("bluetooth_name", "no_permission", false);
                                    }
                                }

                                private void L() {
                                    if (C3H.B >= 17) {
                                        try {
                                            A("stay_on_while_pluggedin", Integer.valueOf(Settings.Global.getInt(this.B.getContentResolver(), "stay_on_while_plugged_in")), true);
                                            return;
                                        } catch (Settings.SettingNotFoundException e) {
                                            A("stay_on_while_pluggedin", "error", false);
                                            return;
                                        }
                                    }
                                    A("stay_on_while_pluggedin", "unknown", false);
                                }

                                private void M() {
                                    A("build_brand", Build.BRAND, true);
                                    A("build_board", Build.BOARD, true);
                                    A("build_bootloader", Build.BOOTLOADER, true);
                                    A("build_manufacturer", Build.MANUFACTURER, true);
                                    A("build_model", Build.MODEL, true);
                                    A("build_host", Build.HOST, true);
                                    A("build_hardware", Build.HARDWARE, true);
                                    A("build_device", Build.DEVICE, true);
                                    A("build_print", Build.FINGERPRINT, true);
                                    A("build_display", Build.DISPLAY, true);
                                    A("build_user", Build.USER, true);
                                    A("build_product", Build.PRODUCT, true);
                                    A("build_tags", Build.TAGS, true);
                                    A("build_time", Long.valueOf(Build.TIME), true);
                                    A("build_type", Build.TYPE, true);
                                    A("build_id", Build.ID, true);
                                    A("build_codename", Build.VERSION.CODENAME, true);
                                    A("build_release", Build.VERSION.RELEASE, true);
                                    A("build_sdk_int", Integer.valueOf(Build.VERSION.SDK_INT), true);
                                    if (C3H.B >= 21 && Build.SUPPORTED_ABIS != null) {
                                        A("build_cpu_abi", Build.SUPPORTED_ABIS[0], true);
                                    } else {
                                        A("build_cpu_abi", "error", false);
                                    }
                                    if (C3H.B >= 23) {
                                        A("build_radio", Build.getRadioVersion(), true);
                                        A("build_security_patch", Build.VERSION.SECURITY_PATCH, true);
                                        A("build_base_os", Build.VERSION.BASE_OS, true);
                                    } else {
                                        A("build_radio", "unknown", false);
                                        A("build_security_patch", "unknown", false);
                                        A("build_base_os", "unknown", false);
                                    }
                                    if (C3H.B < 26) {
                                        A("build_serial", Build.SERIAL, true);
                                    } else {
                                        A("build_serial", "unknown", false);
                                    }
                                }

                                public final void N() {
                                    C();
                                    E();
                                    F();
                                    G();
                                    H();
                                    I();
                                    J();
                                    L();
                                    D();
                                    M();
                                    if (this.C) {
                                        K();
                                    }
                                }
                            }.N();
                        }
                        if (this.B.I()) {
                            final Context context7 = this.C;
                            new C3H(context7) { // from class: com.facebook.ads.redexgen.X.3E
                                private final AudioManager B;

                                {
                                    this.B = (AudioManager) context7.getSystemService("audio");
                                }

                                private void B() {
                                    JSONArray jSONArray = new JSONArray();
                                    if (C3H.B >= 23 && this.B != null) {
                                        try {
                                            for (AudioDeviceInfo audioDeviceInfo : this.B.getDevices(1)) {
                                                JSONObject jSONObject = new JSONObject();
                                                jSONObject.put("ProductName", audioDeviceInfo.getProductName().toString());
                                                jSONObject.put("Type", audioDeviceInfo.getType());
                                                jSONArray.put(jSONObject);
                                            }
                                            A("audio_devices", jSONArray, true);
                                            return;
                                        } catch (JSONException e) {
                                            A("audio_devices", "error", false);
                                            return;
                                        }
                                    }
                                    A("audio_devices", "unknown", false);
                                }

                                private void C() {
                                    if (this.B != null) {
                                        D();
                                        A("music_active", Boolean.valueOf(this.B.isMusicActive()), true);
                                        A("speakerphone", Boolean.valueOf(this.B.isSpeakerphoneOn()), true);
                                    } else {
                                        A("wired_headset", "error", false);
                                        A("music_active", "error", false);
                                        A("speakerphone", "error", false);
                                    }
                                }

                                private void D() {
                                    if (C3H.B > 22) {
                                        boolean z = false;
                                        for (AudioDeviceInfo audioDeviceInfo : this.B.getDevices(2)) {
                                            if (audioDeviceInfo.getType() == 3 || audioDeviceInfo.getType() == 4) {
                                                z = true;
                                                break;
                                            }
                                        }
                                        A("wired_headset", Boolean.valueOf(z), true);
                                        return;
                                    }
                                    A("wired_headset", "unknown", false);
                                }

                                private void E() {
                                    if (this.B != null) {
                                        A("ringer_mode", Integer.valueOf(this.B.getRingerMode()), true);
                                    } else {
                                        A("ringer_mode", "error", false);
                                    }
                                }

                                private void F() {
                                    if (this.B != null) {
                                        A("stream_system", Integer.valueOf(this.B.getStreamVolume(1)), true);
                                        A("stream_notification", Integer.valueOf(this.B.getStreamVolume(5)), true);
                                        A("stream_music", Integer.valueOf(this.B.getStreamVolume(3)), true);
                                        A("stream_ring", Integer.valueOf(this.B.getStreamVolume(2)), true);
                                        A("stream_dtmf", Integer.valueOf(this.B.getStreamVolume(8)), true);
                                        A("stream_voice_call", Integer.valueOf(this.B.getStreamVolume(0)), true);
                                        A("stream_alarm", Integer.valueOf(this.B.getStreamVolume(4)), true);
                                        return;
                                    }
                                    A("stream_system", "error", false);
                                    A("stream_notification", "error", false);
                                    A("stream_music", "error", false);
                                    A("stream_ring", "error", false);
                                    A("stream_dtmf", "error", false);
                                    A("stream_voice_call", "error", false);
                                    A("stream_alarm", "error", false);
                                }

                                public final void G() {
                                    B();
                                    C();
                                    E();
                                    F();
                                }
                            }.G();
                        }
                        if (this.B.Q()) {
                            new C3H(this.C) { // from class: com.facebook.ads.redexgen.X.38
                                private final Context B;
                                private final WifiManager C;

                                {
                                    this.C = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                                    this.B = context;
                                }

                                private void B() {
                                    A("system_os_architecture", System.getProperty("os.arch"), true);
                                    A("system_os_name", System.getProperty("os.name"), true);
                                    A("system_os_version", System.getProperty("os.version"), true);
                                }

                                private void C() {
                                    A("http_user_agent", System.getProperty("http.agent"), true);
                                }

                                private void D() {
                                    A("http_proxy_host", System.getProperty("http.proxyHost"), true);
                                    A("http_proxy_port", System.getProperty("http.proxyPort"), true);
                                    A("http_proxy_user", System.getProperty("http.proxyUser"), true);
                                    A("http_proxy_non_proxy_host", System.getProperty("http.nonProxyHosts"), true);
                                }

                                private void E() {
                                    JSONArray jArrIp;
                                    JSONArray jSONArray = new JSONArray();
                                    JSONArray jSONArray2 = new JSONArray();
                                    String string = "";
                                    String str = null;
                                    HashMap map = new HashMap();
                                    try {
                                        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                                        if (networkInterfaces == null) {
                                            A("network_interfaces", null, true);
                                            return;
                                        }
                                        while (networkInterfaces.hasMoreElements()) {
                                            try {
                                                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                                                if (networkInterfaceNextElement != null && networkInterfaceNextElement.isUp()) {
                                                    byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                                                    if (hardwareAddress != null) {
                                                        StringBuilder sb = new StringBuilder();
                                                        int i = 0;
                                                        while (i < hardwareAddress.length) {
                                                            Object[] objArr = new Object[2];
                                                            objArr[0] = Byte.valueOf(hardwareAddress[i]);
                                                            objArr[1] = i < hardwareAddress.length + (-1) ? "-" : "";
                                                            sb.append(String.format("%02X%s", objArr));
                                                            i++;
                                                        }
                                                        string = sb.toString();
                                                    }
                                                    if (str != null && str.equals(string) && map.containsKey(string)) {
                                                        jArrIp = (JSONArray) map.get(string);
                                                    } else if (str != null && !str.equals(string)) {
                                                        JSONObject jSONObject = new JSONObject();
                                                        jSONObject.put("inet_addresses", jSONArray2);
                                                        jSONObject.put("network_mac", str);
                                                        jSONObject.put("network_virtual", networkInterfaceNextElement.isVirtual());
                                                        jArrIp = new JSONArray();
                                                        jSONArray.put(jSONObject);
                                                    } else {
                                                        jArrIp = jSONArray2;
                                                    }
                                                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                                                    while (inetAddresses.hasMoreElements()) {
                                                        JSONObject jSONObject2 = new JSONObject();
                                                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                                                        jSONObject2.put("network_ip", inetAddressNextElement.getHostAddress());
                                                        jSONObject2.put("network_loopback", inetAddressNextElement.isLoopbackAddress());
                                                        if (jArrIp != null) {
                                                            jArrIp.put(jSONObject2);
                                                        }
                                                    }
                                                    map.put(string, jArrIp);
                                                    str = string;
                                                    if (!networkInterfaces.hasMoreElements()) {
                                                        JSONObject jSONObject3 = new JSONObject();
                                                        jSONObject3.put("inet_addresses", jArrIp);
                                                        jSONObject3.put("network_mac", str);
                                                        jSONObject3.put("network_virtual", networkInterfaceNextElement.isVirtual());
                                                        jSONArray.put(jSONObject3);
                                                    }
                                                } else {
                                                    jArrIp = jSONArray2;
                                                }
                                                jSONArray2 = jArrIp;
                                            } catch (SocketException e) {
                                                A("network_interfaces", "error", false);
                                                return;
                                            } catch (JSONException e2) {
                                                A("network_interfaces", "error", false);
                                                return;
                                            }
                                        }
                                        A("network_interfaces", jSONArray, true);
                                    } catch (SocketException e3) {
                                    } catch (JSONException e4) {
                                    }
                                }

                                @SuppressLint({"MissingPermission"})
                                private void F() {
                                    if (C3H.B < 21) {
                                        A("networks", "unknown", false);
                                        return;
                                    }
                                    if (!B(this.B, "android.permission.ACCESS_NETWORK_STATE")) {
                                        A("networks", "no_permission", false);
                                        return;
                                    }
                                    ConnectivityManager connectivityManager = (ConnectivityManager) this.B.getSystemService("connectivity");
                                    if (connectivityManager == null) {
                                        A("networks", "error", false);
                                        return;
                                    }
                                    if (C3H.B < 21) {
                                        return;
                                    }
                                    Network[] allNetworks = connectivityManager.getAllNetworks();
                                    JSONArray jSONArray = new JSONArray();
                                    for (Network network : allNetworks) {
                                        JSONObject jSONObject = new JSONObject();
                                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                                        if (networkCapabilities != null) {
                                            try {
                                                jSONObject.put("network_capabilities_info", networkCapabilities.toString());
                                                jSONObject.put("network_capabilities_internet", networkCapabilities.hasCapability(12));
                                                jSONObject.put("network_capabilities_not_vpn", networkCapabilities.hasCapability(15));
                                                int i = -1;
                                                if (networkCapabilities.hasTransport(0)) {
                                                    i = 0;
                                                } else if (networkCapabilities.hasTransport(1)) {
                                                    i = 1;
                                                } else if (networkCapabilities.hasTransport(2)) {
                                                    i = 2;
                                                } else if (networkCapabilities.hasTransport(3)) {
                                                    i = 3;
                                                } else if (networkCapabilities.hasTransport(4)) {
                                                    i = 4;
                                                } else if (networkCapabilities.hasTransport(5) && C3H.B >= 26) {
                                                    i = 5;
                                                }
                                                jSONObject.put("network_transport_type", i);
                                                jSONArray.put(jSONObject);
                                            } catch (NullPointerException e) {
                                                A("networks", "error", false);
                                            } catch (JSONException e2) {
                                                A("networks", "error", false);
                                            }
                                        } else {
                                            A("networks", "unknown", false);
                                        }
                                    }
                                    A("networks", jSONArray, true);
                                }

                                private void G() {
                                    A("socks_proxy_host", System.getProperty("socksProxyHost"), true);
                                    A("socks_proxy_port", System.getProperty("socksProxyPort"), true);
                                    A("socks_proxy_version", System.getProperty("socksProxyVersion"), true);
                                    A("socks_proxy_user", System.getProperty("java.net.socks.username"), true);
                                }

                                public final void H() {
                                    B();
                                    C();
                                    D();
                                    E();
                                    F();
                                    G();
                                    H(true);
                                    H(false);
                                }

                                @SuppressLint({"MissingPermission"})
                                private void H(boolean isWifiStateSignal) {
                                    if (!B(this.B, "android.permission.ACCESS_WIFI_STATE")) {
                                        A(isWifiStateSignal ? "wifi_state" : "wifi_ssid", "no_permission", false);
                                        return;
                                    }
                                    if (this.C == null) {
                                        A(isWifiStateSignal ? "wifi_state" : "wifi_ssid", "error", false);
                                        return;
                                    }
                                    if (!this.C.isWifiEnabled()) {
                                        A(isWifiStateSignal ? "wifi_state" : "wifi_ssid", "unknown", false);
                                        return;
                                    }
                                    WifiInfo connectionInfo = this.C.getConnectionInfo();
                                    if (connectionInfo == null) {
                                        A(isWifiStateSignal ? "wifi_state" : "wifi_ssid", "null", true);
                                        return;
                                    }
                                    NetworkInfo.DetailedState detailedStateOf = WifiInfo.getDetailedStateOf(connectionInfo.getSupplicantState());
                                    if (detailedStateOf == null) {
                                        A(isWifiStateSignal ? "wifi_state" : "wifi_ssid", "error", false);
                                        return;
                                    }
                                    if (isWifiStateSignal) {
                                        A("wifi_state", detailedStateOf.toString(), true);
                                        return;
                                    }
                                    if (detailedStateOf == NetworkInfo.DetailedState.CONNECTED || detailedStateOf == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                                        String ssid = connectionInfo.getSSID();
                                        if (ssid != null) {
                                            ssid = ssid.replaceAll("(^\")|(\"$)", "");
                                        }
                                        A("wifi_ssid", ssid, true);
                                        return;
                                    }
                                    A("wifi_ssid", "unknown", false);
                                }
                            }.H();
                        }
                        if (this.B.L()) {
                            final Context context8 = this.C;
                            new C3H(context8) { // from class: com.facebook.ads.redexgen.X.3C
                                private final Context B;

                                {
                                    this.B = context8;
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private void B() {
                                    try {
                                        String str = "/storage" + File.separatorChar + "sdcard" + File.separatorChar + "windows" + File.separatorChar + "BstSharedFolder";
                                        String path2 = Environment.getExternalStorageDirectory().toString() + File.separatorChar + "windows" + File.separatorChar + "BstSharedFolder";
                                        File sharedFolder = new File(str);
                                        if (sharedFolder.exists()) {
                                            A("emulator_bst_shared_folder", true, true);
                                        } else if (!str.equalsIgnoreCase(path2)) {
                                            File sharedFolder2 = new File(path2);
                                            if (sharedFolder2.exists()) {
                                                A("emulator_bst_shared_folder", true, true);
                                            } else {
                                                A("emulator_bst_shared_folder", false, true);
                                            }
                                        }
                                    } catch (Exception e) {
                                        A("emulator_bst_shared_folder", "error", false);
                                    }
                                }

                                private void C() {
                                    F("client_id_base", "ro.com.google.clientidbase");
                                    F("board_platform", "ro.board.platform");
                                    F("build_characteristics", "ro.build.characteristics");
                                    F("lte_on_cdma", "telephony.lteOnCdmaDevice");
                                    F("qemu_properties", "ro.kernel.qemu");
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private void D() {
                                    try {
                                        A("current_auto_brightness", Integer.valueOf(Settings.System.getInt(this.B.getContentResolver(), "screen_brightness_mode")), true);
                                    } catch (Exception e) {
                                        A("current_auto_brightness", "error", false);
                                    }
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private static String E(String name) throws Exception {
                                    String response = "";
                                    Class<?> cls = Class.forName("android.os.SystemProperties");
                                    if (cls == null) {
                                        return "";
                                    }
                                    try {
                                        Method method = cls.getMethod("get", String.class);
                                        Object objInvoke = method != null ? method.invoke(cls, name) : null;
                                        if (objInvoke == null) {
                                            return "";
                                        }
                                        response = (String) objInvoke;
                                        return response;
                                    } catch (Exception e) {
                                        Log.e(C3H.C, "Error getting System Property", e);
                                        return response;
                                    }
                                }

                                public final void E() {
                                    B();
                                    C();
                                    D();
                                    G();
                                    H();
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private void F(String signalName, String systemPropertyName) {
                                    try {
                                        if (signalName.equals("lte_on_cdma")) {
                                            String strE = E(systemPropertyName);
                                            A(signalName, Integer.valueOf(strE.length() == 0 ? -1 : Integer.valueOf(strE).intValue()), true);
                                        } else if (signalName.equals("qemu_properties")) {
                                            A(signalName, Boolean.valueOf(E(systemPropertyName).equals("1")), true);
                                        } else {
                                            A(signalName, E(systemPropertyName), true);
                                        }
                                    } catch (Exception e) {
                                        A(signalName, "error", false);
                                    }
                                }

                                private void G() {
                                    Bundle extras = null;
                                    Intent intentRegisterReceiver = this.B.registerReceiver(null, new IntentFilter("android.hardware.usb.action.USB_STATE"));
                                    if (intentRegisterReceiver != null) {
                                        extras = intentRegisterReceiver.getExtras();
                                    }
                                    if (extras != null) {
                                        A("usb_connected", Boolean.valueOf(extras.getBoolean("connected")), true);
                                    } else {
                                        A("usb_connected", "error", false);
                                    }
                                }

                                @SuppressLint({"CatchGeneralException"})
                                private void H() {
                                    File file = new File(Environment.getRootDirectory(), "lib/libc.so");
                                    if (!file.exists()) {
                                        file = new File(Environment.getRootDirectory(), "lib/libc64.so");
                                        if (!file.exists()) {
                                            return;
                                        }
                                    }
                                    try {
                                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                                        randomAccessFile.seek(18L);
                                        byte[] bArr = new byte[2];
                                        randomAccessFile.readFully(bArr);
                                        int i = (bArr[0] & 255) | ((bArr[1] & 255) << 8);
                                        randomAccessFile.close();
                                        if (i != 3 && i != 62) {
                                            A("computer_architecture", 0, true);
                                        } else {
                                            A("computer_architecture", 1, true);
                                        }
                                    } catch (Exception e) {
                                        A("computer_architecture", "error", false);
                                    }
                                }
                            }.E();
                        }
                        if (this.B.W()) {
                            new C3H(this.C) { // from class: com.facebook.ads.redexgen.X.33
                                public SensorManager B;
                                private boolean M;
                                private boolean N;
                                private boolean O;
                                private boolean P;
                                private boolean Q;
                                private boolean R;
                                private final Context S;
                                private final String G = "name";
                                private final String K = "vendor";
                                private final String L = "version";
                                private final String J = "type";
                                private final String E = "max_range";
                                private final String I = "resolution";
                                private final String H = "power";
                                private final String F = "min_delay";
                                private final String D = "max_delay";
                                private final String C = "id";

                                {
                                    this.S = context;
                                }

                                private void B() {
                                    this.B = (SensorManager) this.S.getSystemService("sensor");
                                    this.Q = false;
                                    this.R = false;
                                    this.P = false;
                                    this.M = false;
                                    this.O = false;
                                    this.N = false;
                                    if (C3H.B >= 20 && this.B != null) {
                                        List<Sensor> sensorList = this.B.getSensorList(-1);
                                        for (int i = 0; i < sensorList.size(); i++) {
                                            Sensor sensor = sensorList.get(i);
                                            if (sensor.getStringType() == "android.sensor.light") {
                                                C("hw_light_sensor", sensor);
                                                this.Q = true;
                                            }
                                            if (sensor.getStringType() == "android.sensor.proximity") {
                                                C("hw_proximity_sensor", sensor);
                                                this.R = true;
                                            }
                                            if (sensor.getStringType() == "android.sensor.gyroscope") {
                                                C("hw_gyro", sensor);
                                                this.P = true;
                                            }
                                            if (sensor.getStringType() == "android.sensor.accelerometer") {
                                                C("hw_accelerometer", sensor);
                                                this.M = true;
                                            }
                                            if (sensor.getStringType() == "android.sensor.geomagnetic_rotation_vector") {
                                                C("hw_compass", sensor);
                                                this.O = true;
                                            }
                                            if (sensor.getStringType() == "android.sensor.pressure") {
                                                C("hw_barometer", sensor);
                                                this.N = true;
                                            }
                                        }
                                    }
                                    D();
                                }

                                private void C(String sensorName, Sensor sensor) {
                                    int maxDelay;
                                    JSONObject jSONObject = new JSONObject();
                                    try {
                                        jSONObject.put("name", sensor.getName());
                                        jSONObject.put("vendor", sensor.getVendor());
                                        jSONObject.put("type", sensor.getType());
                                        jSONObject.put("power", sensor.getPower());
                                        jSONObject.put("vendor", sensor.getVendor());
                                        jSONObject.put("version", sensor.getVersion());
                                        jSONObject.put("min_delay", sensor.getMinDelay());
                                        if (Build.VERSION.SDK_INT >= 21) {
                                            maxDelay = sensor.getMaxDelay();
                                        } else {
                                            maxDelay = 0;
                                        }
                                        jSONObject.put("max_delay", maxDelay);
                                        jSONObject.put("max_range", sensor.getMaximumRange());
                                        jSONObject.put("resolution", sensor.getResolution());
                                        jSONObject.put("id", Build.VERSION.SDK_INT >= 24 ? sensor.getId() : 0);
                                        A(sensorName, jSONObject, true);
                                    } catch (JSONException e) {
                                        A(sensorName, "error", false);
                                    }
                                }

                                private void D() {
                                    if (!this.Q) {
                                        A("hw_light_sensor", "unknown", false);
                                    }
                                    if (!this.R) {
                                        A("hw_proximity_sensor", "unknown", false);
                                    }
                                    if (!this.P) {
                                        A("hw_gyro", "unknown", false);
                                    }
                                    if (!this.M) {
                                        A("hw_accelerometer", "unknown", false);
                                    }
                                    if (!this.O) {
                                        A("hw_compass", "unknown", false);
                                    }
                                    if (!this.N) {
                                        A("hw_barometer", "unknown", false);
                                    }
                                }

                                public final void E() {
                                    B();
                                }
                            }.E();
                        }
                        this.F = C3H.E;
                        this.D = C3H.D;
                        String strB = B();
                        if (this.B.D() == null) {
                            return;
                        }
                        this.B.D().wC(eventId2, productType2, eventType2, C2O.B(), EnumC01852m.NATIVE.toString(), strB, "{}");
                    }

                    private final String B() {
                        JSONObject dataObject = new JSONObject();
                        try {
                            JSONObject jSONObject = new JSONObject();
                            C01691w c01691w = new C01691w(this.E.B());
                            jSONObject.put("endpoint_path", this.E.A());
                            jSONObject.put("decryption_key", this.E.B());
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("values", this.F);
                            jSONObject2.put("deltas", new HashMap());
                            jSONObject2.put("errors", this.D);
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("ci", "843748");
                            jSONObject3.put("data", jSONObject2);
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("android", jSONObject3);
                            JSONObject sdkObject = new JSONObject();
                            sdkObject.put("sdk", jSONObject4);
                            jSONObject.put("data", c01691w.A(sdkObject.toString().replace("\\/", com.appsflyer.share.Constants.URL_PATH_DELIMITER)));
                            dataObject.put("wo", jSONObject);
                        } catch (JSONException e) {
                            Log.e(G, "Error building Native Signal JSON", e);
                        }
                        return dataObject.toString();
                    }
                }.A(eventId, productType, eventType);
            }
        }
    }
}
