package com.appsflyer;

import android.text.TextUtils;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class OneLinkHttpTask implements Runnable {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private AppsFlyerLib f96;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    String f97;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private HttpsUrlConnectionProvider f98;

    /* JADX INFO: renamed from: ˊ */
    abstract void mo90(String str);

    /* JADX INFO: renamed from: ˋ */
    abstract void mo91();

    /* JADX INFO: renamed from: ˏ */
    abstract String mo92();

    /* JADX INFO: renamed from: ˏ */
    abstract void mo93(HttpsURLConnection httpsURLConnection) throws JSONException, IOException;

    OneLinkHttpTask(AppsFlyerLib appsFlyerLib) {
        this.f96 = appsFlyerLib;
    }

    public void setConnProvider(HttpsUrlConnectionProvider httpsUrlConnectionProvider) {
        this.f98 = httpsUrlConnectionProvider;
    }

    public static class HttpsUrlConnectionProvider {
        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        static HttpsURLConnection m94(String str) throws IOException {
            return (HttpsURLConnection) new URL(str).openConnection();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        String strM44;
        String string = "";
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        String strMo92 = mo92();
        AFLogger.afRDLog("oneLinkUrl: ".concat(String.valueOf(strMo92)));
        try {
            HttpsURLConnection httpsURLConnectionM94 = HttpsUrlConnectionProvider.m94(strMo92);
            httpsURLConnectionM94.addRequestProperty("content-type", "application/json");
            StringBuilder sb = new StringBuilder();
            sb.append(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY));
            sb.append(jCurrentTimeMillis);
            httpsURLConnectionM94.addRequestProperty("authorization", C0120r.m180(sb.toString()));
            httpsURLConnectionM94.addRequestProperty("af-timestamp", String.valueOf(jCurrentTimeMillis));
            httpsURLConnectionM94.setReadTimeout(3000);
            httpsURLConnectionM94.setConnectTimeout(3000);
            mo93(httpsURLConnectionM94);
            int responseCode = httpsURLConnectionM94.getResponseCode();
            strM44 = AppsFlyerLib.m44(httpsURLConnectionM94);
            try {
                if (responseCode == 200) {
                    AFLogger.afInfoLog("Status 200 ok");
                } else {
                    StringBuilder sb2 = new StringBuilder("Response code = ");
                    sb2.append(responseCode);
                    sb2.append(" content = ");
                    sb2.append(strM44);
                    string = sb2.toString();
                }
            } catch (Throwable th) {
                th = th;
                AFLogger.afErrorLog("Error while calling ".concat(String.valueOf(strMo92)), th);
                StringBuilder sb3 = new StringBuilder("Error while calling ");
                sb3.append(strMo92);
                sb3.append(" stacktrace: ");
                sb3.append(th.toString());
                string = sb3.toString();
            }
        } catch (Throwable th2) {
            th = th2;
            strM44 = "";
        }
        if (TextUtils.isEmpty(string)) {
            AFLogger.afInfoLog("Connection call succeeded: ".concat(String.valueOf(strM44)));
            mo90(strM44);
        } else {
            AFLogger.afWarnLog("Connection error: ".concat(String.valueOf(string)));
            mo91();
        }
    }
}
