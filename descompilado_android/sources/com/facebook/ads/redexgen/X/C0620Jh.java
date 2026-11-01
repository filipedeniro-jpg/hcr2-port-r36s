package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.appevents.UserDataStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0620Jh {
    private static final Map<String, List<String>> B = new HashMap();

    private C0620Jh() {
    }

    public static boolean B(String clientToken, EnumC0617Je event) {
        return B.containsKey(clientToken) && B.get(clientToken).contains(event.A());
    }

    public static void C(View view, C0618Jf handler, EnumC0617Je event) {
        view.addOnAttachStateChangeListener(new ViewOnAttachStateChangeListenerC0619Jg(handler, event));
    }

    public static void D(Context context, JSONObject ad, long requestTime) {
        JSONObject jSONObjectOptJSONObject;
        JSONArray funnelConfig = ad.optJSONArray("funnel");
        if (funnelConfig == null || (jSONObjectOptJSONObject = ad.optJSONObject("data")) == null) {
            return;
        }
        List<String> listG = G(context, funnelConfig);
        if (jSONObjectOptJSONObject.has(UserDataStore.CITY)) {
            String strOptString = jSONObjectOptJSONObject.optString(UserDataStore.CITY);
            E(strOptString, listG);
            F(context, strOptString, requestTime);
        } else {
            if (!jSONObjectOptJSONObject.has("choosable_ads")) {
                return;
            }
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("choosable_ads");
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject2 != null) {
                    String strOptString2 = jSONObjectOptJSONObject2.optString(UserDataStore.CITY);
                    E(strOptString2, listG);
                    if (i == 0) {
                        F(context, strOptString2, requestTime);
                    }
                }
            }
        }
    }

    private static void E(String clientToken, List<String> list) {
        if (TextUtils.isEmpty(clientToken) || list.isEmpty()) {
            return;
        }
        B.put(clientToken, list);
    }

    private static void F(Context context, String clientToken, long requestTime) {
        C0618Jf c0618Jf = new C0618Jf(clientToken, JX.E(context));
        HashMap map = new HashMap();
        map.put("request_time_ms", C0622Jj.F(requestTime));
        map.put("load_time_ms", C0622Jj.C(requestTime));
        c0618Jf.A(EnumC0617Je.AD_RESPONSE_RECEIVED, map);
    }

    private static List<String> G(Context context, JSONArray eventArray) {
        List<String> events = new ArrayList<>();
        for (int i = 0; i < eventArray.length(); i++) {
            try {
                events.add(eventArray.getString(i));
            } catch (JSONException e) {
                Log.d("FunnelLoggingUtils", "Error parsing funnel events list", e);
                C0585Hx.D(context, "ux_funnel_logging", C0584Hw.z, new C0583Hv(e));
            }
        }
        return events;
    }
}
