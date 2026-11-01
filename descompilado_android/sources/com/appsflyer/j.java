package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.applovin.sdk.AppLovinEventParameters;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class j implements Runnable {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static String f159;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private String f160;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private final Intent f161;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private String f162;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private WeakReference<Context> f163;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private String f164;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private String f165;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private String f166;

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private String f167;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private Map<String, String> f168;

    static {
        StringBuilder sb = new StringBuilder("https://%svalidate.%s/api/v");
        sb.append(AppsFlyerLib.f24);
        sb.append("/androidevent?buildnumber=4.8.20&app_id=");
        f159 = sb.toString();
    }

    j(Context context, String str, String str2, String str3, String str4, String str5, String str6, Map<String, String> map, @Nullable Intent intent) {
        this.f163 = new WeakReference<>(context);
        this.f164 = str;
        this.f162 = str2;
        this.f166 = str4;
        this.f160 = str5;
        this.f167 = str6;
        this.f168 = map;
        this.f165 = str3;
        this.f161 = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.f164;
        if (str == null || str.length() == 0 || AppsFlyerLib.getInstance().isTrackingStopped()) {
            return;
        }
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                Context context = this.f163.get();
                if (context == null) {
                    return;
                }
                HashMap map = new HashMap();
                map.put("public-key", this.f162);
                map.put("sig-data", this.f166);
                map.put(InAppPurchaseMetaData.KEY_SIGNATURE, this.f165);
                final HashMap map2 = new HashMap();
                map2.putAll(map);
                Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() { // from class: com.appsflyer.j.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        j jVar = j.this;
                        j.m152(jVar, map2, jVar.f168, j.this.f163);
                    }
                }, 5L, TimeUnit.MILLISECONDS);
                map.put("dev_key", this.f164);
                map.put("app_id", context.getPackageName());
                map.put("uid", AppsFlyerLib.getInstance().getAppsFlyerUID(context));
                map.put(ServerParameters.ADVERTISING_ID_PARAM, AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM));
                String string = new JSONObject(map).toString();
                String url = ServerConfigHandler.getUrl("https://%ssdk-services.%s/validate-android-signature");
                s.m187().m201(url, string);
                HttpURLConnection httpURLConnectionM151 = m151(string, url);
                int responseCode = httpURLConnectionM151 != null ? httpURLConnectionM151.getResponseCode() : -1;
                AppsFlyerLib.getInstance();
                String strM44 = AppsFlyerLib.m44(httpURLConnectionM151);
                s.m187().m200(url, responseCode, strM44);
                JSONObject jSONObject = new JSONObject(strM44);
                jSONObject.put("code", responseCode);
                if (responseCode == 200) {
                    StringBuilder sb = new StringBuilder("Validate response 200 ok: ");
                    sb.append(jSONObject.toString());
                    AFLogger.afInfoLog(sb.toString());
                    m149(jSONObject.optBoolean(IronSourceConstants.EVENTS_RESULT) ? jSONObject.getBoolean(IronSourceConstants.EVENTS_RESULT) : false, this.f166, this.f160, this.f167, jSONObject.toString());
                } else {
                    AFLogger.afInfoLog("Failed Validate request");
                    m149(false, this.f166, this.f160, this.f167, jSONObject.toString());
                }
                if (httpURLConnectionM151 != null) {
                    httpURLConnectionM151.disconnect();
                }
            } catch (Throwable th) {
                if (AppsFlyerLib.f20 != null) {
                    AFLogger.afErrorLog("Failed Validate request + ex", th);
                    m149(false, this.f166, this.f160, this.f167, th.getMessage());
                }
                AFLogger.afErrorLog(th.getMessage(), th);
                if (0 != 0) {
                    httpURLConnection.disconnect();
                }
            }
        } catch (Throwable th2) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static HttpURLConnection m151(String str, String str2) throws IOException {
        try {
            k kVar = new k(null, AppsFlyerLib.getInstance().isTrackingStopped());
            kVar.f178 = str;
            kVar.m153();
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                StringBuilder sb = new StringBuilder("Main thread detected. Calling ");
                sb.append(str2);
                sb.append(" in a new thread.");
                AFLogger.afDebugLog(sb.toString());
                kVar.execute(str2);
            } else {
                StringBuilder sb2 = new StringBuilder("Calling ");
                sb2.append(str2);
                sb2.append(" (on current thread: ");
                sb2.append(Thread.currentThread().toString());
                sb2.append(" )");
                AFLogger.afDebugLog(sb2.toString());
                kVar.onPreExecute();
                kVar.onPostExecute(kVar.doInBackground(str2));
            }
            return kVar.m154();
        } catch (Throwable th) {
            AFLogger.afErrorLog("Could not send callStats request", th);
            return null;
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static void m149(boolean z, String str, String str2, String str3, String str4) {
        if (AppsFlyerLib.f20 != null) {
            StringBuilder sb = new StringBuilder("Validate callback parameters: ");
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            sb.append(" ");
            sb.append(str3);
            AFLogger.afDebugLog(sb.toString());
            if (z) {
                AFLogger.afDebugLog("Validate in app purchase success: ".concat(String.valueOf(str4)));
                AppsFlyerLib.f20.onValidateInApp();
                return;
            }
            AFLogger.afDebugLog("Validate in app purchase failed: ".concat(String.valueOf(str4)));
            AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = AppsFlyerLib.f20;
            if (str4 == null) {
                str4 = "Failed validating";
            }
            appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure(str4);
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static /* synthetic */ void m152(j jVar, Map map, Map map2, WeakReference weakReference) {
        if (weakReference.get() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl(f159));
            sb.append(((Context) weakReference.get()).getPackageName());
            String string = sb.toString();
            SharedPreferences sharedPreferences = ((Context) weakReference.get()).getSharedPreferences("appsflyer-data", 0);
            HttpURLConnection httpURLConnection = null;
            String string2 = sharedPreferences.getString("referrer", null);
            if (string2 == null) {
                string2 = "";
            }
            Map<String, Object> mapM78 = AppsFlyerLib.getInstance().m78((Context) weakReference.get(), jVar.f164, AFInAppEventType.PURCHASE, "", string2, true, sharedPreferences, false, jVar.f161, null);
            mapM78.put("price", jVar.f160);
            mapM78.put("currency", jVar.f167);
            JSONObject jSONObject = new JSONObject(mapM78);
            JSONObject jSONObject2 = new JSONObject();
            try {
                for (Map.Entry entry : map.entrySet()) {
                    jSONObject2.put((String) entry.getKey(), entry.getValue());
                }
                jSONObject.put(AppLovinEventParameters.IN_APP_PURCHASE_DATA, jSONObject2);
            } catch (JSONException e) {
                AFLogger.afErrorLog("Failed to build 'receipt_data'", e);
            }
            if (map2 != null) {
                JSONObject jSONObject3 = new JSONObject();
                try {
                    for (Map.Entry entry2 : map2.entrySet()) {
                        jSONObject3.put((String) entry2.getKey(), entry2.getValue());
                    }
                    jSONObject.put("extra_prms", jSONObject3);
                } catch (JSONException e2) {
                    AFLogger.afErrorLog("Failed to build 'extra_prms'", e2);
                }
            }
            String string3 = jSONObject.toString();
            s.m187().m201(string, string3);
            try {
                try {
                    HttpURLConnection httpURLConnectionM151 = m151(string3, string);
                    int responseCode = httpURLConnectionM151 != null ? httpURLConnectionM151.getResponseCode() : -1;
                    AppsFlyerLib.getInstance();
                    String strM44 = AppsFlyerLib.m44(httpURLConnectionM151);
                    s.m187().m200(string, responseCode, strM44);
                    JSONObject jSONObject4 = new JSONObject(strM44);
                    if (responseCode == 200) {
                        StringBuilder sb2 = new StringBuilder("Validate-WH response - 200: ");
                        sb2.append(jSONObject4.toString());
                        AFLogger.afInfoLog(sb2.toString());
                    } else {
                        StringBuilder sb3 = new StringBuilder("Validate-WH response failed - ");
                        sb3.append(responseCode);
                        sb3.append(": ");
                        sb3.append(jSONObject4.toString());
                        AFLogger.afWarnLog(sb3.toString());
                    }
                    if (httpURLConnectionM151 != null) {
                        httpURLConnectionM151.disconnect();
                    }
                } catch (Throwable th) {
                    AFLogger.afErrorLog(th.getMessage(), th);
                    if (0 != 0) {
                        httpURLConnection.disconnect();
                    }
                }
            } catch (Throwable th2) {
                if (0 != 0) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
        }
    }
}
