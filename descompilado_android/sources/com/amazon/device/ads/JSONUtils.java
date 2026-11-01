package com.amazon.device.ads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class JSONUtils {
    JSONUtils() {
    }

    public static class JSONUtilities {
        public boolean getBooleanFromJSON(JSONObject jSONObject, String str, boolean z) {
            return JSONUtils.getBooleanFromJSON(jSONObject, str, z);
        }

        public String getStringFromJSON(JSONObject jSONObject, String str, String str2) {
            return JSONUtils.getStringFromJSON(jSONObject, str, str2);
        }

        public int getIntegerFromJSON(JSONObject jSONObject, String str, int i) {
            return JSONUtils.getIntegerFromJSON(jSONObject, str, i);
        }

        public long getLongFromJSON(JSONObject jSONObject, String str, long j) {
            return JSONUtils.getLongFromJSON(jSONObject, str, j);
        }

        public JSONArray getJSONArrayFromJSON(JSONObject jSONObject, String str) {
            return JSONUtils.getJSONArrayFromJSON(jSONObject, str);
        }

        public int getIntegerFromJSONArray(JSONArray jSONArray, int i, int i2) {
            return JSONUtils.getIntegerFromJSONArray(jSONArray, i, i2);
        }

        public JSONObject getJSONObjectFromJSONArray(JSONArray jSONArray, int i) {
            return JSONUtils.getJSONObjectFromJSONArray(jSONArray, i);
        }

        public Map<String, String> createMapFromJSON(JSONObject jSONObject) {
            return JSONUtils.createMapFromJSON(jSONObject);
        }

        public void put(JSONObject jSONObject, String str, String str2) {
            JSONUtils.put(jSONObject, str, str2);
        }

        public void put(JSONObject jSONObject, String str, int i) {
            JSONUtils.put(jSONObject, str, i);
        }

        public void put(JSONObject jSONObject, String str, long j) {
            JSONUtils.put(jSONObject, str, j);
        }

        public void put(JSONObject jSONObject, String str, boolean z) {
            JSONUtils.put(jSONObject, str, z);
        }

        public JSONObject getJSONObjectFromString(String str) {
            return JSONUtils.getJSONObjectFromString(str);
        }
    }

    public static boolean getBooleanFromJSON(JSONObject jSONObject, String str, boolean z) {
        return jSONObject.isNull(str) ? z : jSONObject.optBoolean(str, z);
    }

    public static String getStringFromJSON(JSONObject jSONObject, String str, String str2) {
        return jSONObject.isNull(str) ? str2 : jSONObject.optString(str, str2);
    }

    public static int getIntegerFromJSON(JSONObject jSONObject, String str, int i) {
        return jSONObject.isNull(str) ? i : jSONObject.optInt(str, i);
    }

    public static long getLongFromJSON(JSONObject jSONObject, String str, long j) {
        return jSONObject.isNull(str) ? j : jSONObject.optLong(str, j);
    }

    public static JSONArray getJSONArrayFromJSON(JSONObject jSONObject, String str) {
        if (jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optJSONArray(str);
    }

    public static int getIntegerFromJSONArray(JSONArray jSONArray, int i, int i2) {
        return jSONArray.isNull(i) ? i2 : jSONArray.optInt(i, i2);
    }

    public static JSONObject getJSONObjectFromJSONArray(JSONArray jSONArray, int i) {
        if (jSONArray.isNull(i)) {
            return null;
        }
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void put(JSONObject jSONObject, String str, String str2) {
        if (str2 == null || str2.equals("")) {
            return;
        }
        try {
            jSONObject.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    public static void put(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
        } catch (JSONException unused) {
        }
    }

    public static void put(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
        } catch (JSONException unused) {
        }
    }

    public static void put(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException unused) {
        }
    }

    public static JSONObject getJSONObjectFromString(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Map<String, String> createMapFromJSON(JSONObject jSONObject) {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String stringFromJSON = getStringFromJSON(jSONObject, next, null);
            if (stringFromJSON != null) {
                map.put(next, stringFromJSON);
            }
        }
        return map;
    }
}
