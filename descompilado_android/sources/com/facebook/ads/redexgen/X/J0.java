package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.util.Log;
import com.facebook.appevents.UserDataStore;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class J0 implements InterfaceC0612Iz {
    private static final String C = J0.class.getSimpleName();
    private final Context B;

    public J0(Context context) {
        this.B = context;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0612Iz
    public final void vC(String appSessionId, String bdSessionId, int configTimestamp, int reputationTier, int sessionEndTime, JSONObject signalsJsonData) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("asid", appSessionId);
            jSONObject.put("bid", bdSessionId);
            jSONObject.put(UserDataStore.CITY, configTimestamp);
            jSONObject.put("rt", reputationTier);
            jSONObject.put("set", sessionEndTime);
            jSONObject.put("sjd", signalsJsonData.toString());
            C0583Hv c0583Hv = new C0583Hv(jSONObject.toString());
            c0583Hv.D(1);
            C0585Hx.D(this.B, "bd_data_logging", IronSourceConstants.BN_RELOAD_FAILED, c0583Hv);
        } catch (JSONException e) {
            Log.e(C, "Exception building FAN logging JSON", e);
        }
    }
}
