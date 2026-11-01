package com.ironsource.sdk;

import com.ironsource.sdk.constants.Constants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceNetworkAPIUtils {
    static String manual_rewarded_instance_prefix = "ManRewInst_";

    public static String generateInstanceId(JSONObject jSONObject) {
        if (jSONObject.optBoolean(Constants.CONVERT_REWARDED)) {
            return manual_rewarded_instance_prefix + jSONObject.optString("name");
        }
        return jSONObject.optString("name");
    }
}
