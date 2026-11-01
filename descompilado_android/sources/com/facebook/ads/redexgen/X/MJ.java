package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MJ {
    private MJ() {
    }

    public static List<String> B(JSONArray jsonArray) {
        if (jsonArray == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            String strOptString = jsonArray.optString(i);
            if (!TextUtils.isEmpty(strOptString)) {
                arrayList.add(strOptString);
            }
        }
        return arrayList;
    }

    public static String C(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    jSONObject.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONObject.toString();
    }

    public static String D(JSONObject jsonObject, String key) {
        return E(jsonObject, key, null);
    }

    private static String E(JSONObject jsonObject, String key, String fallback) {
        String key2 = jsonObject.optString(key, fallback);
        if ("null".equals(key2)) {
            return null;
        }
        return key2;
    }
}
