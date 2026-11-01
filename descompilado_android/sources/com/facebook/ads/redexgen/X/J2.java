package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.appsflyer.share.Constants;
import com.facebook.appevents.UserDataStore;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class J2 implements J1 {
    private static final String C = J2.class.getSimpleName();
    private final Context B;

    public J2(Context context) {
        this.B = context;
    }

    @Override // com.facebook.ads.redexgen.X.J1
    public final void pC(String level, String operation, Map<String, String> map, @Nullable String bdSessionId, @Nullable String exceptionMessage, @Nullable String exceptionStackTrace, @Nullable String ExceptionType) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("l", level);
            jSONObject.put("o", operation);
            JSONObject jSONObject2 = new JSONObject();
            for (String str : map.keySet()) {
                if (map.containsKey(str)) {
                    jSONObject2.put(str, map.get(str));
                }
            }
            jSONObject.put("oi", jSONObject2.toString());
            jSONObject.put("bid", bdSessionId);
            jSONObject.put(UserDataStore.EMAIL, exceptionMessage);
            jSONObject.put("est", exceptionMessage);
            jSONObject.put("et", exceptionMessage);
            jSONObject.put(Constants.URL_CAMPAIGN, "Java");
            C0583Hv c0583Hv = new C0583Hv(jSONObject.toString());
            c0583Hv.D(1);
            C0585Hx.D(this.B, "bd_data_logging", 3202, c0583Hv);
        } catch (JSONException e) {
            Log.e(C, "Exception building FAN logging JSON", e);
        }
    }
}
