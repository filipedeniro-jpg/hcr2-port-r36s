package com.ironsource.mediationsdk.sdk;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GeneralProperties {
    public static final String ALLOW_LOCATION_SHARED_PREFS_KEY = "GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY";
    public static final String USER_ID_TYPE = "userIdType";
    private static GeneralProperties mInstance;
    private JSONObject mProperties = new JSONObject();

    private GeneralProperties() {
    }

    public static synchronized GeneralProperties getProperties() {
        if (mInstance == null) {
            mInstance = new GeneralProperties();
        }
        return mInstance;
    }

    public synchronized void putKeys(Map<String, Object> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                putKey(str, map.get(str));
            }
        }
    }

    public synchronized void putKey(String str, Object obj) {
        try {
            this.mProperties.put(str, obj);
        } catch (Exception unused) {
        }
    }

    public synchronized String get(String str) {
        return this.mProperties.optString(str);
    }

    public synchronized JSONObject toJSON() {
        return this.mProperties;
    }
}
