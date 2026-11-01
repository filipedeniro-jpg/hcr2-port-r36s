package com.appsflyer.share;

import android.content.Context;
import android.os.AsyncTask;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.ServerConfigHandler;
import com.appsflyer.ServerParameters;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CrossPromotionHelper {
    public static void trackAndOpenStore(Context context, String str, String str2) {
        trackAndOpenStore(context, str, str2, null);
    }

    public static void trackAndOpenStore(Context context, String str, String str2, Map<String, String> map) {
        LinkGenerator linkGeneratorM202 = m202(context, str, str2, map, ServerConfigHandler.getUrl(Constants.BASE_URL_APP_APPSFLYER_COM));
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, track And Open Store is disabled", true);
            return;
        }
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        map2.put("af_campaign", str2);
        AppsFlyerLib.getInstance().trackEvent(context, "af_cross_promotion", map2);
        new d(new c(), context, AppsFlyerLib.getInstance().isTrackingStopped()).execute(linkGeneratorM202.generateLink());
    }

    public static void trackCrossPromoteImpression(Context context, String str, String str2) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, Promote Impression is disabled", true);
        } else {
            new d(null, null, AppsFlyerLib.getInstance().isTrackingStopped()).execute(m202(context, str, str2, null, ServerConfigHandler.getUrl("https://%simpression.%s")).generateLink());
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static LinkGenerator m202(Context context, String str, String str2, Map<String, String> map, String str3) {
        LinkGenerator linkGenerator = new LinkGenerator("af_cross_promotion");
        linkGenerator.m207(str3).m206(str).addParameter(Constants.URL_SITE_ID, context.getPackageName());
        if (str2 != null) {
            linkGenerator.setCampaign(str2);
        }
        if (map != null) {
            linkGenerator.addParameters(map);
        }
        String string = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string != null) {
            linkGenerator.addParameter("advertising_id", string);
        }
        return linkGenerator;
    }

    static class d extends AsyncTask<String, Void, Void> {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private c f239;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private WeakReference<Context> f240;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private boolean f241;

        d(c cVar, Context context, boolean z) {
            this.f239 = cVar;
            this.f240 = new WeakReference<>(context);
            this.f241 = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:32:0x009a A[PHI: r2
  0x009a: PHI (r2v4 java.net.HttpURLConnection) = (r2v3 java.net.HttpURLConnection), (r2v7 java.net.HttpURLConnection) binds: [B:31:0x0098, B:22:0x0086] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:36:0x00a1  */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters and merged with bridge method [inline-methods] */
        public Void doInBackground(String... strArr) throws Throwable {
            HttpURLConnection httpURLConnection;
            HttpURLConnection httpURLConnection2 = null;
            if (this.f241) {
                return null;
            }
            try {
                try {
                    String str = strArr[0];
                    httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    try {
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setInstanceFollowRedirects(false);
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == 200) {
                            AFLogger.afInfoLog("Cross promotion impressions success: ".concat(String.valueOf(str)), false);
                        } else if (responseCode == 301 || responseCode == 302) {
                            AFLogger.afInfoLog("Cross promotion redirection success: ".concat(String.valueOf(str)), false);
                            if (this.f239 != null && this.f240.get() != null) {
                                this.f239.m209(httpURLConnection.getHeaderField("Location"));
                                this.f239.m208(this.f240.get());
                            }
                        } else {
                            StringBuilder sb = new StringBuilder("call to ");
                            sb.append(str);
                            sb.append(" failed: ");
                            sb.append(responseCode);
                            AFLogger.afInfoLog(sb.toString());
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        AFLogger.afErrorLog(th.getMessage(), th, true);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (0 != 0) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (0 != 0) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
            return null;
        }
    }
}
