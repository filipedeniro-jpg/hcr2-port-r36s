package com.appsflyer;

import android.content.pm.PackageManager;
import android.os.Build;
import com.ironsource.sdk.constants.Constants;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class s {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static s f207;

    /* JADX INFO: renamed from: ˉ, reason: contains not printable characters */
    private JSONObject f217;

    /* JADX INFO: renamed from: ˋˊ, reason: contains not printable characters */
    private int f224;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private boolean f228 = true;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private boolean f231 = true;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private final String f223 = "brand";

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private final String f218 = "model";

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private final String f235 = "platform";

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private final String f237 = "platform_version";

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private final String f208 = ServerParameters.ADVERTISING_ID_PARAM;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private final String f212 = "imei";

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private final String f210 = com.my.target.i.ANDROID_ID;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private final String f232 = "sdk_version";

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private final String f230 = "devkey";

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private final String f225 = "originalAppsFlyerId";

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private final String f229 = "uid";

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private final String f221 = "app_id";

    /* JADX INFO: renamed from: ॱᐝ, reason: contains not printable characters */
    private final String f236 = "app_version";

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    private final String f234 = AppsFlyerProperties.CHANNEL;

    /* JADX INFO: renamed from: ʻॱ, reason: contains not printable characters */
    private final String f209 = "preInstall";

    /* JADX INFO: renamed from: ᐝॱ, reason: contains not printable characters */
    private final String f238 = "data";

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private final String f233 = "r_debugging_off";

    /* JADX INFO: renamed from: ʿ, reason: contains not printable characters */
    private final String f215 = "r_debugging_on";

    /* JADX INFO: renamed from: ʼॱ, reason: contains not printable characters */
    private final String f211 = "public_api_call";

    /* JADX INFO: renamed from: ˈ, reason: contains not printable characters */
    private final String f216 = "exception";

    /* JADX INFO: renamed from: ʽॱ, reason: contains not printable characters */
    private final String f213 = "server_request";

    /* JADX INFO: renamed from: ʾ, reason: contains not printable characters */
    private final String f214 = "server_response";

    /* JADX INFO: renamed from: ˊˋ, reason: contains not printable characters */
    private final String f220 = "yyyy-MM-dd HH:mm:ssZ";

    /* JADX INFO: renamed from: ˊˊ, reason: contains not printable characters */
    private final String f219 = "MM-dd HH:mm:ss.SSS";

    /* JADX INFO: renamed from: ˍ, reason: contains not printable characters */
    private String f226 = "-1";

    /* JADX INFO: renamed from: ˊᐝ, reason: contains not printable characters */
    private JSONArray f222 = new JSONArray();

    /* JADX INFO: renamed from: ˎˎ, reason: contains not printable characters */
    private boolean f227 = false;

    private s() {
        this.f224 = 0;
        this.f224 = 0;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static s m187() {
        if (f207 == null) {
            f207 = new s();
        }
        return f207;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final synchronized void m199(String str) {
        this.f226 = str;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final synchronized void m192() {
        this.f227 = true;
        m186("r_debugging_on", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final synchronized void m197() {
        m186("r_debugging_off", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
        this.f227 = false;
        this.f228 = false;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final synchronized void m194() {
        this.f217 = null;
        this.f222 = null;
        f207 = null;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private synchronized void m185(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            this.f217.put("brand", str);
            this.f217.put("model", str2);
            this.f217.put("platform", Constants.JAVASCRIPT_INTERFACE_NAME);
            this.f217.put("platform_version", str3);
            if (str4 != null && str4.length() > 0) {
                this.f217.put(ServerParameters.ADVERTISING_ID_PARAM, str4);
            }
            if (str5 != null && str5.length() > 0) {
                this.f217.put("imei", str5);
            }
            if (str6 != null && str6.length() > 0) {
                this.f217.put(com.my.target.i.ANDROID_ID, str6);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private synchronized void m182(String str, String str2, String str3, String str4) {
        try {
            this.f217.put("sdk_version", str);
            if (str2 != null && str2.length() > 0) {
                this.f217.put("devkey", str2);
            }
            if (str3 != null && str3.length() > 0) {
                this.f217.put("originalAppsFlyerId", str3);
            }
            if (str4 != null && str4.length() > 0) {
                this.f217.put("uid", str4);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private synchronized void m188(String str, String str2, String str3, String str4) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    this.f217.put("app_id", str);
                }
            } catch (Throwable unused) {
                return;
            }
        }
        if (str2 != null && str2.length() > 0) {
            this.f217.put("app_version", str2);
        }
        if (str3 != null && str3.length() > 0) {
            this.f217.put(AppsFlyerProperties.CHANNEL, str3);
        }
        if (str4 != null && str4.length() > 0) {
            this.f217.put("preInstall", str4);
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m196(String str, String... strArr) {
        m186("public_api_call", str, strArr);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void m198(Throwable th) {
        String[] strArr;
        Throwable cause = th.getCause();
        String simpleName = th.getClass().getSimpleName();
        String message = cause == null ? th.getMessage() : cause.getMessage();
        StackTraceElement[] stackTrace = cause == null ? th.getStackTrace() : cause.getStackTrace();
        if (stackTrace == null) {
            strArr = new String[]{message};
        } else {
            String[] strArr2 = new String[stackTrace.length + 1];
            strArr2[0] = message;
            for (int i = 1; i < stackTrace.length; i++) {
                strArr2[i] = stackTrace[i].toString();
            }
            strArr = strArr2;
        }
        m186("exception", simpleName, strArr);
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m201(String str, String str2) {
        m186("server_request", str, str2);
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m200(String str, int i, String str2) {
        m186("server_response", str, String.valueOf(i), str2);
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m193(String str, String str2) {
        m186(null, str, str2);
    }

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private synchronized String m189() {
        String string;
        string = null;
        try {
            this.f217.put("data", this.f222);
            string = this.f217.toString();
            m181();
        } catch (JSONException unused) {
        }
        return string;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private synchronized void m184(String str, PackageManager packageManager) {
        AppsFlyerProperties appsFlyerProperties = AppsFlyerProperties.getInstance();
        AppsFlyerLib appsFlyerLib = AppsFlyerLib.getInstance();
        String string = appsFlyerProperties.getString("remote_debug_static_data");
        if (string != null) {
            try {
                this.f217 = new JSONObject(string);
            } catch (Throwable unused) {
            }
        } else {
            this.f217 = new JSONObject();
            m185(Build.BRAND, Build.MODEL, Build.VERSION.RELEASE, appsFlyerProperties.getString(ServerParameters.ADVERTISING_ID_PARAM), appsFlyerLib.f35, appsFlyerLib.f45);
            m182("4.8.20.170", appsFlyerProperties.getString(AppsFlyerProperties.AF_KEY), appsFlyerProperties.getString("KSAppsFlyerId"), appsFlyerProperties.getString("uid"));
            try {
                int i = packageManager.getPackageInfo(str, 0).versionCode;
                m188(str, String.valueOf(i), appsFlyerProperties.getString(AppsFlyerProperties.CHANNEL), appsFlyerProperties.getString("preInstallName"));
            } catch (Throwable unused2) {
            }
            appsFlyerProperties.set("remote_debug_static_data", this.f217.toString());
        }
        try {
            this.f217.put("launch_counter", this.f226);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private synchronized void m181() {
        this.f222 = null;
        this.f222 = new JSONArray();
        this.f224 = 0;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final synchronized void m195() {
        this.f228 = false;
        m181();
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    final void m191() {
        this.f231 = false;
    }

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    final boolean m190() {
        return this.f227;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static void m183(String str, PackageManager packageManager) {
        try {
            if (f207 == null) {
                f207 = new s();
            }
            f207.m184(str, packageManager);
            if (f207 == null) {
                f207 = new s();
            }
            String strM189 = f207.m189();
            k kVar = new k(null, AppsFlyerLib.getInstance().isTrackingStopped());
            kVar.f178 = strM189;
            kVar.m155();
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl("https://%smonitorsdk.%s/remote-debug?app_id="));
            sb.append(str);
            kVar.execute(sb.toString());
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private synchronized void m186(String str, String str2, String... strArr) {
        String str3;
        if (!(this.f231 && (this.f228 || this.f227)) || this.f224 >= 98304) {
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String string = "";
            if (strArr.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (int length = strArr.length - 1; length > 0; length--) {
                    sb.append(strArr[length]);
                    sb.append(", ");
                }
                sb.append(strArr[0]);
                string = sb.toString();
            }
            String str4 = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.ENGLISH).format(Long.valueOf(jCurrentTimeMillis));
            if (str != null) {
                str3 = String.format("%18s %5s _/%s [%s] %s %s", str4, Long.valueOf(Thread.currentThread().getId()), AppsFlyerLib.LOG_TAG, str, str2, string);
            } else {
                str3 = String.format("%18s %5s %s/%s %s", str4, Long.valueOf(Thread.currentThread().getId()), str2, AppsFlyerLib.LOG_TAG, string);
            }
            this.f222.put(str3);
            this.f224 += str3.getBytes().length;
        } catch (Throwable unused) {
        }
    }
}
