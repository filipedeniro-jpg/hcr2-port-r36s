package com.applovin.impl.sdk.utils;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static float a(JSONObject jSONObject, String str, float f, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return f;
        }
        try {
            double d = jSONObject.getDouble(str);
            return (-3.4028234663852886E38d >= d || d >= 3.4028234663852886E38d) ? f : (float) d;
        } catch (JSONException e) {
            if (hVar == null) {
                return f;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve float property for key = " + str, e);
            return f;
        }
    }

    public static long a(JSONObject jSONObject, String str, long j, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return j;
        }
        try {
            return jSONObject.getLong(str);
        } catch (JSONException e) {
            if (hVar == null) {
                return j;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve int property for key = " + str, e);
            return j;
        }
    }

    public static Boolean a(JSONObject jSONObject, String str, Boolean bool, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return bool;
        }
        try {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        } catch (JSONException unused) {
            return Boolean.valueOf(b(jSONObject, str, (bool == null || !bool.booleanValue()) ? 0 : 1, hVar) > 0);
        }
    }

    private static Object a(Object obj) throws JSONException {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj);
        }
        return obj instanceof JSONArray ? b((JSONArray) obj) : obj;
    }

    public static Object a(JSONArray jSONArray, int i, Object obj, com.applovin.impl.sdk.h hVar) {
        if (jSONArray == null || jSONArray.length() <= i) {
            return obj;
        }
        try {
            return jSONArray.get(i);
        } catch (JSONException e) {
            if (hVar == null) {
                return obj;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve object at index " + i + " for JSON array", e);
            return obj;
        }
    }

    public static Object a(JSONObject jSONObject, String str, Object obj, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return obj;
        }
        try {
            Object obj2 = jSONObject.get(str);
            return obj2 != null ? obj2 : obj;
        } catch (JSONException e) {
            if (hVar == null) {
                return obj;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve Object for key = " + str, e);
            return obj;
        }
    }

    public static String a(Map<String, Object> map, String str, com.applovin.impl.sdk.h hVar) {
        try {
            return a((Map<String, ?>) map).toString();
        } catch (JSONException e) {
            hVar.w().b("JsonUtils", "Failed to convert map '" + map + "' to JSON string.", e);
            return str;
        }
    }

    public static String a(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.toString(4);
        } catch (JSONException e) {
            if (hVar != null) {
                hVar.w().b("JsonUtils", "Failed to convert to indented string", e);
            }
            return jSONObject.toString();
        }
    }

    public static String a(JSONObject jSONObject, String str, String str2, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return str2;
        }
        try {
            return jSONObject.getString(str);
        } catch (JSONException e) {
            if (hVar == null) {
                return str2;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve string property for key = " + str, e);
            return str2;
        }
    }

    public static ArrayList<Bundle> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new ArrayList<>();
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(c(jSONArray.optJSONObject(i)));
        }
        return arrayList;
    }

    public static Map<String, String> a(Bundle bundle) throws JSONException {
        HashMap map = new HashMap(bundle.size());
        for (String str : bundle.keySet()) {
            map.put(str, String.valueOf(bundle.get(str)));
        }
        return map;
    }

    public static Map<String, Object> a(String str, Map<String, Object> map, com.applovin.impl.sdk.h hVar) {
        try {
            return b(new JSONObject(str));
        } catch (JSONException e) {
            hVar.w().b("JsonUtils", "Failed to convert json string '" + str + "' to map", e);
            return map;
        }
    }

    public static Map<String, String> a(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, a(jSONObject.get(next)).toString());
        }
        return map;
    }

    public static JSONArray a(JSONObject jSONObject, String str, JSONArray jSONArray, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return jSONArray;
        }
        try {
            return jSONObject.getJSONArray(str);
        } catch (JSONException e) {
            if (hVar == null) {
                return jSONArray;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve JSON array for key = " + str, e);
            return jSONArray;
        }
    }

    public static JSONObject a(String str, com.applovin.impl.sdk.h hVar) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            hVar.w().d("JsonUtils", "Failed to deserialize into JSON: " + str);
            return null;
        }
    }

    public static JSONObject a(String str, JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            if (hVar != null) {
                hVar.w().b("JsonUtils", "Failed to convert JSON string '" + str + "' to JSONObject", e);
            }
            return jSONObject;
        }
    }

    public static JSONObject a(Map<String, ?> map) throws JSONException {
        if (map == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    public static JSONObject a(JSONArray jSONArray, int i, JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        if (jSONArray == null || i >= jSONArray.length()) {
            return jSONObject;
        }
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException e) {
            if (hVar == null) {
                return jSONObject;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve JSON object from array for index = " + i, e);
            return jSONObject;
        }
    }

    public static JSONObject a(JSONObject jSONObject, String str, JSONObject jSONObject2, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return jSONObject2;
        }
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException e) {
            if (hVar == null) {
                return jSONObject2;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve JSON property for key = " + str, e);
            return jSONObject2;
        }
    }

    public static void a(JSONObject jSONObject, String str, int i, com.applovin.impl.sdk.h hVar) {
        if (jSONObject != null) {
            try {
                jSONObject.put(str, i);
            } catch (JSONException e) {
                if (hVar != null) {
                    hVar.w().b("JsonUtils", "Failed to put int property for key = " + str, e);
                }
            }
        }
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static int b(JSONObject jSONObject, String str, int i, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return i;
        }
        try {
            return jSONObject.getInt(str);
        } catch (JSONException e) {
            if (hVar == null) {
                return i;
            }
            hVar.w().b("JsonUtils", "Failed to retrieve int property for key = " + str, e);
            return i;
        }
    }

    public static List b(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.get(i)));
        }
        return arrayList;
    }

    public static Map<String, Object> b(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, a(jSONObject.get(next)));
        }
        return map;
    }

    public static void b(JSONObject jSONObject, String str, long j, com.applovin.impl.sdk.h hVar) {
        if (jSONObject != null) {
            try {
                jSONObject.put(str, j);
            } catch (JSONException e) {
                if (hVar != null) {
                    hVar.w().b("JsonUtils", "Failed to put long property for key = " + str, e);
                }
            }
        }
    }

    public static Bundle c(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return Bundle.EMPTY;
        }
        Bundle bundle = new Bundle();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (jSONObject.isNull(next)) {
                bundle.putString(next, null);
            } else {
                Object objOpt = jSONObject.opt(next);
                if (objOpt instanceof JSONObject) {
                    bundle.putBundle(next, c((JSONObject) objOpt));
                } else if (objOpt instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) objOpt;
                    if (jSONArray.length() == 0) {
                        bundle.putStringArrayList(next, new ArrayList<>(0));
                    } else if (a(jSONArray, 0, (Object) null, (com.applovin.impl.sdk.h) null) instanceof String) {
                        ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
                        for (int i = 0; i < jSONArray.length(); i++) {
                            arrayList.add((String) a(jSONArray, i, (Object) null, (com.applovin.impl.sdk.h) null));
                        }
                        bundle.putStringArrayList(next, arrayList);
                    } else {
                        bundle.putParcelableArrayList(next, a(jSONArray));
                    }
                } else if (objOpt instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) objOpt).booleanValue());
                } else if (objOpt instanceof String) {
                    bundle.putString(next, (String) objOpt);
                } else if (objOpt instanceof Integer) {
                    bundle.putInt(next, ((Integer) objOpt).intValue());
                } else if (objOpt instanceof Long) {
                    bundle.putLong(next, ((Long) objOpt).longValue());
                } else if (objOpt instanceof Double) {
                    bundle.putDouble(next, ((Double) objOpt).doubleValue());
                }
            }
        }
        return bundle;
    }
}
