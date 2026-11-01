package com.facebook.marketing.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.internal.AttributionIdentifiers;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RemoteConfigManager {
    private static final String SAMPLING_ENDPOINT_PATH = "%s/button_auto_detection_device_selection";
    private static final String SAMPLING_RESULT_FIELD = "is_selected";
    private static final String TAG = "com.facebook.marketing.internal.RemoteConfigManager";
    private static final Map<String, RemoteConfig> remoteConfigs = new ConcurrentHashMap();

    public static void loadRemoteConfigAsync() {
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.internal.RemoteConfigManager.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject remoteConfigQueryResponse;
                Context applicationContext = FacebookSdk.getApplicationContext();
                String applicationId = FacebookSdk.getApplicationId();
                AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(applicationContext);
                if ((attributionIdentifiers != null ? attributionIdentifiers.getAndroidAdvertiserId() : null) == null || (remoteConfigQueryResponse = RemoteConfigManager.getRemoteConfigQueryResponse(applicationId)) == null) {
                    return;
                }
                RemoteConfigManager.parseRemoteConfigFromJSON(applicationId, remoteConfigQueryResponse);
            }
        });
    }

    @Nullable
    public static RemoteConfig getRemoteConfigWithoutQuery(String str) {
        if (str != null) {
            return remoteConfigs.get(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static JSONObject getRemoteConfigQueryResponse(String str) {
        try {
            String str2 = String.format(Locale.US, SAMPLING_ENDPOINT_PATH, str);
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.FIELDS_PARAM, SAMPLING_RESULT_FIELD);
            GraphRequest graphRequest = new GraphRequest(null, str2, bundle, HttpMethod.GET, null);
            graphRequest.setSkipClientToken(true);
            return graphRequest.executeAndWait().getJSONObject();
        } catch (Exception e) {
            Log.e(TAG, "fail to request button sampling api", e);
            return new JSONObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parseRemoteConfigFromJSON(String str, JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("data");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0 || (jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0)) == null) {
            return;
        }
        remoteConfigs.put(str, new RemoteConfig(jSONObjectOptJSONObject.optBoolean(SAMPLING_RESULT_FIELD, false)));
    }
}
