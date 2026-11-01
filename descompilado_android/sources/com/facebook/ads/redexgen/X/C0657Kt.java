package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.AnyThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@WorkerThread
public final class C0657Kt {
    private static C0657Kt B = new C0657Kt();

    private C0657Kt() {
    }

    public final C0658Kv A(Context context, String response, long requestTime) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONObject jSONObject = new JSONObject(response);
            switch (jSONObject.optString("type")) {
                case "ads":
                    return D(context, jSONObject, requestTime);
                case "error":
                    return E(jSONObject);
                default:
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("error");
                    if (jSONObjectOptJSONObject != null) {
                        return C(jSONObjectOptJSONObject);
                    }
                    break;
            }
        }
        return new C0658Kv(Ku.UNKNOWN);
    }

    @AnyThread
    public static synchronized C0657Kt B() {
        return B;
    }

    private C0660Kx C(JSONObject jsonResponse) {
        return new C0660Kx(jsonResponse.optString("message", ""), jsonResponse.optInt("code", 0), null);
    }

    private C0659Kw D(Context context, JSONObject jsonResponse, long requestTime) throws JSONException {
        JSONObject definition = jsonResponse.getJSONArray("placements").getJSONObject(0);
        JSONObject placement = definition.getJSONObject("definition");
        IQ placementDefinition = IQ.B(placement);
        String strOptString = definition.optString("feature_config");
        String featureConfig = definition.optString("ad_reporting_config");
        IP ip = new IP(placementDefinition, strOptString, featureConfig);
        if (definition.has("ads")) {
            JSONArray jSONArray = definition.getJSONArray("ads");
            for (int i = 0; i < i; i++) {
                JSONObject data = jSONArray.getJSONObject(i);
                C0620Jh.D(context, data, requestTime);
                ip.A(new IN(data.optString("adapter"), data.optString("data_model_type"), data.optJSONObject("data"), data.optJSONArray("trackers")));
            }
        }
        return new C0659Kw(ip, jsonResponse.optString("an_validation_uuid"));
    }

    private C0660Kx E(JSONObject jsonResponse) {
        try {
            JSONObject jSONObject = jsonResponse.getJSONArray("placements").getJSONObject(0);
            JSONObject placement = jSONObject.getJSONObject("definition");
            return new C0660Kx(jsonResponse.optString("message", ""), jsonResponse.optInt("code", 0), new IP(IQ.B(placement), jSONObject.optString("feature_config"), jSONObject.optString("ad_reporting_config")));
        } catch (JSONException e) {
            return C(jsonResponse);
        }
    }
}
