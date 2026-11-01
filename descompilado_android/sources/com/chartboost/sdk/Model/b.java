package com.chartboost.sdk.Model;

import com.appsflyer.share.Constants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.my.target.ai;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public final String a;
    public final String b;
    public final String c;

    public b(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    private static Map<String, b> b(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            Iterator<String> itKeys2 = jSONObject2.keys();
            while (itKeys2.hasNext()) {
                String next2 = itKeys2.next();
                JSONObject jSONObject3 = jSONObject2.getJSONObject(next2);
                map.put(next2, new b(next, jSONObject3.getString("filename"), jSONObject3.getString("url")));
            }
        }
        return map;
    }

    public static Map<String, b> a(JSONObject jSONObject) {
        HashMap map = new HashMap();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("videos");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("id");
                    map.put(string, new b("videos", string, jSONObject2.getString("video")));
                } catch (JSONException e) {
                    com.chartboost.sdk.Tracking.a.a(b.class, "deserializeNativeVideos (file)", e);
                }
            }
        } catch (JSONException e2) {
            com.chartboost.sdk.Tracking.a.a(b.class, "deserializeNativeVideos (videos array)", e2);
        }
        return map;
    }

    private static JSONObject a(JSONArray jSONArray) throws JSONException {
        JSONObject jSONObjectA = com.chartboost.sdk.Libraries.e.a(new com.chartboost.sdk.Libraries.e.a[0]);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String strOptString = jSONObject.optString("name");
            String strOptString2 = jSONObject.optString("type");
            String strOptString3 = jSONObject.optString("value");
            String strOptString4 = jSONObject.optString("param");
            if (!strOptString2.equals("param") && strOptString4.isEmpty()) {
                JSONObject jSONObjectOptJSONObject = jSONObjectA.optJSONObject(strOptString2);
                if (jSONObjectOptJSONObject == null) {
                    jSONObjectOptJSONObject = com.chartboost.sdk.Libraries.e.a(new com.chartboost.sdk.Libraries.e.a[0]);
                    jSONObjectA.put(strOptString2, jSONObjectOptJSONObject);
                }
                jSONObjectOptJSONObject.put(strOptString2.equals(ai.a.da) ? TtmlNode.TAG_BODY : strOptString, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("filename", strOptString), com.chartboost.sdk.Libraries.e.a("url", strOptString3)));
            }
        }
        return jSONObjectA;
    }

    public static Map<String, b> a(JSONObject jSONObject, int i) {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("cache_assets");
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                int i2 = 0;
                if (next.equals("templates")) {
                    JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("templates");
                    if (jSONArrayOptJSONArray != null) {
                        int iMin = Math.min(i, jSONArrayOptJSONArray.length());
                        while (i2 < iMin) {
                            Iterator<Map.Entry<String, b>> it = b(a(jSONArrayOptJSONArray.getJSONObject(i2).getJSONArray(MessengerShareContentUtility.ELEMENTS))).entrySet().iterator();
                            while (it.hasNext()) {
                                b value = it.next().getValue();
                                map.put(value.b, value);
                            }
                            i2++;
                        }
                    }
                } else {
                    JSONArray jSONArray = jSONObject2.getJSONArray(next);
                    while (i2 < jSONArray.length()) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                        String string = jSONObject3.getString("name");
                        map.put(string, new b(next, string, jSONObject3.getString("value")));
                        i2++;
                    }
                }
            }
        } catch (JSONException e) {
            com.chartboost.sdk.Tracking.a.a(b.class, "v2PrefetchToAssets", e);
        }
        return map;
    }

    public File a(File file) {
        return new File(file, this.a + Constants.URL_PATH_DELIMITER + this.b);
    }
}
