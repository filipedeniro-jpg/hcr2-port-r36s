package com.facebook.ads.redexgen.X;

import android.content.Context;
import com.facebook.share.internal.MessengerShareContentUtility;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8j, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03388j {
    private C03388j() {
    }

    public static String B(String s) {
        return s.replace("'", "’").replace("\"", "“").replace("\b", "\\b").replace("\f", "\\f").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }

    public static JSONObject C(Context context, JSONObject jsonObject, H2 adCacheManager) {
        try {
            JSONObject iconObject = jsonObject.optJSONObject("icon");
            if (iconObject != null && iconObject.optString("url") != null) {
                iconObject.put("url", adCacheManager.J(iconObject.optString("url")));
            }
            JSONArray jSONArrayOptJSONArray = jsonObject.optJSONArray("end_card_images");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < i; i++) {
                    jSONArrayOptJSONArray.put(i, adCacheManager.J(jSONArrayOptJSONArray.optString(i)));
                }
            }
            JSONArray jSONArrayOptJSONArray2 = jsonObject.optJSONArray("carousel");
            if (jSONArrayOptJSONArray2 != null) {
                for (int i2 = 0; i2 < i; i2++) {
                    D(jSONArrayOptJSONArray2.optJSONObject(i2), adCacheManager);
                }
            } else {
                D(jsonObject, adCacheManager);
            }
        } catch (JSONException e) {
            C0585Hx.D(context, "dynamic_layout", C0584Hw.y, new C0583Hv("Error transforming JSON in updateCachedUrls"));
        }
        return jsonObject;
    }

    private static void D(JSONObject jsonObject, H2 adCacheManager) throws JSONException {
        JSONObject jSONObjectOptJSONObject = jsonObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE);
        if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optString("url") != null) {
            jSONObjectOptJSONObject.put("url", adCacheManager.J(jSONObjectOptJSONObject.optString("url")));
        }
        if (jsonObject.optString("video_url") != null) {
            jsonObject.put("video_url", adCacheManager.K(jsonObject.optString("video_url")));
        }
    }
}
