package com.ironsource.mediationsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.mediationsdk.AbstractSmash;
import com.ironsource.mediationsdk.BannerSmash;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.ThreadExceptionHandler;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceUtils {
    private static final String ADAPTER_VERSION_KEY = "providerAdapterVersion";
    private static final String DEFAULT_IS_EVENTS_FORMATTER_TYPE = "default_is_events_formatter_type";
    private static final String DEFAULT_IS_EVENTS_URL = "default_is_events_url";
    private static final String DEFAULT_IS_OPT_OUT_EVENTS = "default_is_opt_out_events";
    private static final String DEFAULT_RV_EVENTS_FORMATTER_TYPE = "default_rv_events_formatter_type";
    private static final String DEFAULT_RV_EVENTS_URL = "default_rv_events_url";
    private static final String DEFAULT_RV_OPT_OUT_EVENTS = "default_rv_opt_out_events";
    private static final String GENERAL_PROPERTIES = "general_properties";
    public static final String KEY = "C38FB23A402222A0C17D34A92F971D1F";
    private static final String LAST_RESPONSE = "last_response";
    private static final String PROVIDER_KEY = "provider";
    private static final String PROVIDER_PRIORITY = "providerPriority";
    private static final String SDK_VERSION = "6.9.0";
    private static final String SDK_VERSION_KEY = "providerSDKVersion";
    private static final String SHARED_PREFERENCES_NAME = "Mediation_Shared_Preferences";
    private static final String SUB_PROVIDER_ID_KEY = "spId";
    private static int serr = 1;

    public static String getSDKVersion() {
        return "6.9.0";
    }

    private static void setSerr(int i) {
        serr = i;
    }

    public static int getSerr() {
        return serr;
    }

    public static String getMD5(String str) {
        try {
            String string = new BigInteger(1, MessageDigest.getInstance("MD5").digest(str.getBytes())).toString(16);
            while (string.length() < 32) {
                string = AppEventsConstants.EVENT_PARAM_VALUE_NO + string;
            }
            return string;
        } catch (Throwable th) {
            if (str == null) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getMD5(input:null)", th);
                return "";
            }
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getMD5(input:" + str + ")", th);
            return "";
        }
    }

    private static String getSHA256(String str) {
        try {
            return String.format("%064x", new BigInteger(1, MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE).digest(str.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            if (str == null) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getSHA256(input:null)", e);
                return "";
            }
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getSHA256(input:" + str + ")", e);
            return "";
        }
    }

    public static String getTransId(String str) {
        return getSHA256(str);
    }

    public static int getCurrentTimestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static String getConnectionType(Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return "none";
        }
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null) {
                return "none";
            }
            if (networkCapabilities.hasTransport(1)) {
                return "wifi";
            }
            return networkCapabilities.hasTransport(0) ? "cellular" : "none";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return "wifi";
            }
            if (activeNetworkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                return "cellular";
            }
        }
        return "none";
    }

    public static void createAndStartWorker(Runnable runnable, String str) {
        Thread thread = new Thread(runnable, str);
        thread.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        thread.start();
    }

    public static String getBase64Auth(String str, String str2) {
        return "Basic " + Base64.encodeToString((str + ":" + str2).getBytes(), 10);
    }

    private static String getDefaultEventsUrlByEventType(String str) {
        if (IronSourceConstants.INTERSTITIAL_EVENT_TYPE.equals(str)) {
            return DEFAULT_IS_EVENTS_URL;
        }
        return IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE.equals(str) ? DEFAULT_RV_EVENTS_URL : "";
    }

    private static String getDefaultOptOutEventsByEventType(String str) {
        if (IronSourceConstants.INTERSTITIAL_EVENT_TYPE.equals(str)) {
            return DEFAULT_IS_OPT_OUT_EVENTS;
        }
        return IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE.equals(str) ? DEFAULT_RV_OPT_OUT_EVENTS : "";
    }

    private static String getDefaultFormatterTypeByEventType(String str) {
        if (IronSourceConstants.INTERSTITIAL_EVENT_TYPE.equals(str)) {
            return DEFAULT_IS_EVENTS_FORMATTER_TYPE;
        }
        return IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE.equals(str) ? DEFAULT_RV_EVENTS_FORMATTER_TYPE : "";
    }

    public static synchronized void saveDefaultEventsURL(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
            editorEdit.putString(getDefaultEventsUrlByEventType(str), str2);
            editorEdit.commit();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultEventsURL(eventType: " + str + ", eventsUrl:" + str2 + ")", e);
        }
    }

    public static synchronized void saveDefaultOptOutEvents(Context context, String str, int[] iArr) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
            String string = null;
            if (iArr != null) {
                StringBuilder sb = new StringBuilder();
                for (int i : iArr) {
                    sb.append(i);
                    sb.append(",");
                }
                string = sb.toString();
            }
            editorEdit.putString(getDefaultOptOutEventsByEventType(str), string);
            editorEdit.commit();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultOptOutEvents(eventType: " + str + ", optOutEvents:" + iArr + ")", e);
        }
    }

    public static synchronized void saveDefaultEventsFormatterType(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
            editorEdit.putString(getDefaultFormatterTypeByEventType(str), str2);
            editorEdit.commit();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultEventsFormatterType(eventType: " + str + ", formatterType:" + str2 + ")", e);
        }
    }

    public static synchronized String getDefaultEventsFormatterType(Context context, String str, String str2) {
        try {
            str2 = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(getDefaultFormatterTypeByEventType(str), str2);
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getDefaultEventsFormatterType(eventType: " + str + ", defaultFormatterType:" + str2 + ")", e);
        }
        return str2;
    }

    public static synchronized String getDefaultEventsURL(Context context, String str, String str2) {
        try {
            str2 = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(getDefaultEventsUrlByEventType(str), str2);
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getDefaultEventsURL(eventType: " + str + ", defaultEventsURL:" + str2 + ")", e);
        }
        return str2;
    }

    public static synchronized int[] getDefaultOptOutEvents(Context context, String str) {
        int[] iArr;
        iArr = null;
        try {
            String string = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(getDefaultOptOutEventsByEventType(str), null);
            if (!TextUtils.isEmpty(string)) {
                StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
                ArrayList arrayList = new ArrayList();
                while (stringTokenizer.hasMoreTokens()) {
                    arrayList.add(Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken())));
                }
                iArr = new int[arrayList.size()];
                for (int i = 0; i < iArr.length; i++) {
                    iArr[i] = ((Integer) arrayList.get(i)).intValue();
                }
            }
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getDefaultOptOutEvents(eventType: " + str + ")", e);
        }
        return iArr;
    }

    public static synchronized void saveLastResponse(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editorEdit.putString(LAST_RESPONSE, str);
        editorEdit.apply();
    }

    public static String getLastResponse(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(LAST_RESPONSE, "");
    }

    static synchronized void saveGeneralProperties(Context context, JSONObject jSONObject) {
        if (context == null || jSONObject == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editorEdit.putString(GENERAL_PROPERTIES, jSONObject.toString());
        editorEdit.apply();
    }

    public static synchronized JSONObject getGeneralProperties(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context == null) {
            return jSONObject;
        }
        try {
            jSONObject = new JSONObject(context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(GENERAL_PROPERTIES, jSONObject.toString()));
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isConnected();
    }

    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public static JSONObject getProviderAdditionalData(AbstractSmash abstractSmash) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PROVIDER_PRIORITY, abstractSmash.getProviderPriority());
            jSONObject.put(SUB_PROVIDER_ID_KEY, abstractSmash.getSubProviderId());
            jSONObject.put("provider", abstractSmash.getAdSourceNameForEvents());
            jSONObject.put(SDK_VERSION_KEY, abstractSmash.getAdapter().getCoreSDKVersion());
            jSONObject.put(ADAPTER_VERSION_KEY, abstractSmash.getAdapter().getVersion());
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getProviderAdditionalData(adapter: " + abstractSmash.getName() + ")", e);
        }
        return jSONObject;
    }

    public static JSONObject getProviderAdditionalData(BannerSmash bannerSmash) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SUB_PROVIDER_ID_KEY, bannerSmash.getSubProviderId());
            jSONObject.put("provider", bannerSmash.getAdSourceNameForEvents());
            jSONObject.put(SDK_VERSION_KEY, bannerSmash.getAdapter().getCoreSDKVersion());
            jSONObject.put(ADAPTER_VERSION_KEY, bannerSmash.getAdapter().getVersion());
            jSONObject.put(PROVIDER_PRIORITY, bannerSmash.getProviderPriority());
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getProviderAdditionalData(adapter: " + bannerSmash.getName() + ")", e);
        }
        return jSONObject;
    }

    public static JSONObject getMediationAdditionalData(boolean z) {
        return getMediationAdditionalData(z, false);
    }

    public static JSONObject getMediationAdditionalData(boolean z, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("provider", "Mediation");
            if (z) {
                jSONObject.put(IronSourceConstants.EVENTS_DEMAND_ONLY, 1);
            }
            if (z2) {
                jSONObject.put(IronSourceConstants.EVENTS_PROGRAMMATIC, 1);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    static void saveStringToSharedPrefs(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    static String getStringFromSharedPrefs(Context context, String str, String str2) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(str, str2);
    }

    static void saveBooleanToSharedPrefs(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    static boolean getBooleanFromSharedPrefs(Context context, String str, boolean z) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getBoolean(str, z);
    }

    static void saveIntToSharedPrefs(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    static int getIntFromSharedPrefs(Context context, String str, int i) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getInt(str, i);
    }

    static void saveLongToSharedPrefs(Context context, String str, long j) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    static long getLongFromSharedPrefs(Context context, String str, long j) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getLong(str, j);
    }

    public static JSONObject mergeJsons(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (jSONObject == null && jSONObject2 == null) {
                return new JSONObject();
            }
            if (jSONObject == null) {
                return jSONObject2;
            }
            if (jSONObject2 == null) {
                return jSONObject;
            }
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!jSONObject.has(next)) {
                    jSONObject.put(next, jSONObject2.get(next));
                }
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void sendAutomationLog(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "automation_log:" + Long.toString(System.currentTimeMillis()) + "text:" + str, 1);
    }

    static Map<String, String> parseJsonToStringMap(JSONObject jSONObject) {
        HashMap map = new HashMap();
        try {
            if (jSONObject != JSONObject.NULL) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!jSONObject.get(next).toString().isEmpty()) {
                        map.put(next, jSONObject.get(next).toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
