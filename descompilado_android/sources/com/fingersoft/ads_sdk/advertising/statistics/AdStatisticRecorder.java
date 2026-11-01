package com.fingersoft.ads_sdk.advertising.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdStatisticRecorder {
    private static final String AD_STATS = "AD_STATS";
    private static final String CLICKS = "clicks";
    private static final String IMPRESSIONS = "impressions";
    protected static Context applicationContext;
    private static final AdStatisticRecorder recorderInstance = new AdStatisticRecorder();
    private static String packageName = "";
    private static String baseUrl = null;
    private static String networkCountry = null;
    private static String simCountry = null;
    private static String locale = null;
    private static String platform = null;
    private JSONObject impressionCounters = new JSONObject();
    private JSONObject clickCounters = new JSONObject();
    private long timestamp = 0;

    public static void init(AdManager adManager, String str) {
        applicationContext = adManager.getActivity();
        baseUrl = adManager.getBaseAddress() + "api/stats";
        packageName = str;
        networkCountry = adManager.getNetworkMcc();
        simCountry = adManager.getSimMcc();
        locale = adManager.Locale();
        platform = adManager.getPlatformName();
        AdUtils.log("Started AdStatisticsRecorder with locales; network: " + networkCountry + " sim: " + simCountry + " locale: " + locale);
    }

    public static AdStatisticRecorder instance() {
        return recorderInstance;
    }

    public boolean clearCounters() {
        AdUtils.log("Clearing ad statistic counters");
        this.impressionCounters = new JSONObject();
        this.clickCounters = new JSONObject();
        this.timestamp = 0L;
        try {
            return applicationContext.getSharedPreferences(AD_STATS, 0).edit().clear().commit();
        } catch (Exception e) {
            AdUtils.log("Could not clear ad statistic settings: " + e.getMessage());
            return false;
        }
    }

    public void recordClick(String str) {
        try {
            SharedPreferences.Editor editorEdit = applicationContext.getSharedPreferences(AD_STATS, 0).edit();
            int i = this.clickCounters.has(str) ? 1 + this.clickCounters.getInt(str) : 1;
            this.clickCounters.put(str, i);
            editorEdit.putString(CLICKS, this.clickCounters.toString());
            editorEdit.commit();
            this.timestamp = System.currentTimeMillis() / 1000;
            AdUtils.log("Recording click for network: " + str + " with count: " + i);
        } catch (Exception e) {
            AdUtils.log("Could not record click for network: " + str + ", error: " + e.getMessage());
        }
    }

    public void recordImpression(String str) {
        try {
            SharedPreferences.Editor editorEdit = applicationContext.getSharedPreferences(AD_STATS, 0).edit();
            int i = this.impressionCounters.has(str) ? 1 + this.impressionCounters.getInt(str) : 1;
            this.impressionCounters.put(str, i);
            editorEdit.putString(IMPRESSIONS, this.impressionCounters.toString());
            editorEdit.commit();
            this.timestamp = System.currentTimeMillis() / 1000;
            AdUtils.log("Recording impression for network: " + str + " with count: " + i);
        } catch (Exception e) {
            AdUtils.log("Could not record impression for network: " + str + ", error: " + e.getMessage());
        }
    }

    public void sendStatistics() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("platform", platform);
            jSONObject2.put("timestamp", this.timestamp);
            if (networkCountry != null) {
                jSONObject2.put("networkCountry", networkCountry);
            }
            if (simCountry != null) {
                jSONObject2.put("simCountry", simCountry);
            }
            if (locale != null) {
                jSONObject2.put("locale", locale);
            }
            jSONObject2.put(IMPRESSIONS, this.impressionCounters);
            jSONObject2.put(CLICKS, this.clickCounters);
            jSONObject.put(packageName, jSONObject2);
        } catch (JSONException e) {
            AdUtils.log("Could not send statistics: " + e.getMessage());
        }
        try {
            clearCounters();
            AdUtils.log("Sending statistics string to server: " + jSONObject.toString());
            new AdStatisticSender().execute(jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    private class AdStatisticSender extends AsyncTask<String, Void, Void> {
        private AdStatisticSender() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            AdUtils.log("Sending Ad Statistics to " + AdStatisticRecorder.baseUrl);
            AdUtils.log("Recorded stats: " + strArr[0]);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(AdStatisticRecorder.baseUrl);
            try {
                httpPost.setHeader(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
                StringEntity stringEntity = new StringEntity(strArr[0]);
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                defaultHttpClient.execute(httpPost);
                return null;
            } catch (Exception e) {
                AdUtils.log("Could not send ad statistic to ther server: " + e.getMessage());
                return null;
            }
        }
    }
}
