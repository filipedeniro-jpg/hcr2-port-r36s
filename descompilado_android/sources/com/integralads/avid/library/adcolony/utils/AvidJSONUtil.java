package com.integralads.avid.library.adcolony.utils;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidJSONUtil {
    public static final String KEY_CHILD_VIEWS = "childViews";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_FRIENDLY_OBSTRUCTION_FOR = "isFriendlyObstructionFor";
    public static final String KEY_ROOT_VIEW = "rootView";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_X = "x";
    public static final String KEY_Y = "y";
    private static String[] b = {"x", "y", "width", "height"};
    static float a = Resources.getSystem().getDisplayMetrics().density;

    public static void init(Context context) {
        if (context != null) {
            a = context.getResources().getDisplayMetrics().density;
        }
    }

    public static JSONObject getEmptyTreeJSONObject() {
        return getTreeJSONObject(getViewState(0, 0, 0, 0), AvidTimestamp.getCurrentTime());
    }

    public static JSONObject getTreeJSONObject(JSONObject jSONObject, double d) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("timestamp", d);
            jSONObject2.put("rootView", jSONObject);
        } catch (JSONException e) {
            AvidLogs.e("Error with creating treeJSONObject", e);
        }
        return jSONObject2;
    }

    public static JSONObject getViewState(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", a(i));
            jSONObject.put("y", a(i2));
            jSONObject.put("width", a(i3));
            jSONObject.put("height", a(i4));
        } catch (JSONException e) {
            AvidLogs.e("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    static float a(int i) {
        return i / a;
    }

    public static void addAvidId(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("id", str);
        } catch (JSONException e) {
            AvidLogs.e("Error with setting avid id", e);
        }
    }

    public static void addFriendlyObstruction(JSONObject jSONObject, List<String> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        try {
            jSONObject.put("isFriendlyObstructionFor", jSONArray);
        } catch (JSONException e) {
            AvidLogs.e("Error with setting friendly obstruction", e);
        }
    }

    public static void addChildState(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("childViews");
            if (jSONArrayOptJSONArray == null) {
                jSONArrayOptJSONArray = new JSONArray();
                jSONObject.put("childViews", jSONArrayOptJSONArray);
            }
            jSONArrayOptJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void fixStateFrame(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("childViews");
        if (jSONArrayOptJSONArray == null) {
            return;
        }
        int length = jSONArrayOptJSONArray.length();
        int iMax = 0;
        int iMax2 = 0;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                int iOptInt = jSONObjectOptJSONObject.optInt("x");
                int iOptInt2 = jSONObjectOptJSONObject.optInt("y");
                int iOptInt3 = jSONObjectOptJSONObject.optInt("width");
                int iOptInt4 = jSONObjectOptJSONObject.optInt("height");
                iMax = Math.max(iMax, iOptInt + iOptInt3);
                iMax2 = Math.max(iMax2, iOptInt2 + iOptInt4);
            }
        }
        try {
            jSONObject.put("width", iMax);
            jSONObject.put("height", iMax2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean equalStates(@NonNull JSONObject jSONObject, @Nullable JSONObject jSONObject2) {
        return jSONObject2 != null && a(jSONObject, jSONObject2) && b(jSONObject, jSONObject2) && c(jSONObject, jSONObject2) && d(jSONObject, jSONObject2);
    }

    private static boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        for (String str : b) {
            if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean b(JSONObject jSONObject, JSONObject jSONObject2) {
        return jSONObject.optString("id", "").equals(jSONObject2.optString("id", ""));
    }

    private static boolean c(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("isFriendlyObstructionFor");
        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("isFriendlyObstructionFor");
        if (!a(jSONArrayOptJSONArray, jSONArrayOptJSONArray2)) {
            return false;
        }
        if (jSONArrayOptJSONArray == null) {
            return true;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            if (!jSONArrayOptJSONArray.optString(i, "").equals(jSONArrayOptJSONArray2.optString(i, ""))) {
                return false;
            }
        }
        return true;
    }

    private static boolean d(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("childViews");
        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("childViews");
        if (!a(jSONArrayOptJSONArray, jSONArrayOptJSONArray2)) {
            return false;
        }
        if (jSONArrayOptJSONArray == null) {
            return true;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            if (!equalStates(jSONArrayOptJSONArray.optJSONObject(i), jSONArrayOptJSONArray2.optJSONObject(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray != null || jSONArray2 == null) && (jSONArray == null || jSONArray2 != null) && jSONArray.length() == jSONArray2.length();
    }
}
