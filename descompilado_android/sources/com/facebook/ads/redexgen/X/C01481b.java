package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.appevents.UserDataStore;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1b, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01481b implements Serializable {
    private static final long serialVersionUID = 8751287062553772011L;
    private final C1N B;
    private final List<C01491c> C;
    private final int D;
    private final String E;
    private final String F;

    @Nullable
    private String G;
    private final C01521f H;
    private final C01551i J;
    private final String K;
    private final C01631q L;
    private final int N;
    private final int O;
    private int M = 200;
    private String I = "";

    private C01481b(C01551i pageDetails, C1N adColors, List<C01491c> list, C01631q toolbarDetails, C01521f optOutRewardDetails, String clientToken, String requestId, int viewabilityInitialDelay, int viewabilityInterval, String dynamicSdkLayerHtmlUrl, int anLogoType) {
        this.J = pageDetails;
        this.B = adColors;
        this.C = list;
        this.E = clientToken;
        this.K = requestId;
        this.N = viewabilityInitialDelay;
        this.O = viewabilityInterval;
        this.F = dynamicSdkLayerHtmlUrl;
        this.L = toolbarDetails;
        this.H = optOutRewardDetails;
        this.D = anLogoType;
    }

    public final List<C01491c> A() {
        return Collections.unmodifiableList(this.C);
    }

    public final int B() {
        return this.D;
    }

    public static C01481b B(JSONObject dataObject, Context context) {
        String strOptString;
        JSONObject layoutObject;
        JSONObject layoutObject2;
        C01541h c01541hD = new C01541h().D(dataObject.optString("title"));
        if (dataObject.optJSONObject("icon") != null) {
            strOptString = dataObject.optJSONObject("icon").optString("url");
        } else {
            strOptString = "";
        }
        C01551i c01551iB = c01541hD.C(strOptString).A(dataObject.optString("ad_choices_link_url")).E(C01561j.C(dataObject)).B();
        C01631q c01631qD = C01561j.D(dataObject);
        C01521f c01521fB = C01561j.B(dataObject);
        JSONObject jSONObjectOptJSONObject = dataObject.optJSONObject(TtmlNode.TAG_LAYOUT);
        if (jSONObjectOptJSONObject != null) {
            layoutObject = jSONObjectOptJSONObject.optJSONObject("portrait");
        } else {
            layoutObject = null;
        }
        C1Y c1yB = C1Y.B(layoutObject);
        if (jSONObjectOptJSONObject != null) {
            layoutObject2 = jSONObjectOptJSONObject.optJSONObject("landscape");
        } else {
            layoutObject2 = null;
        }
        C1N c1n = new C1N(c1yB, C1Y.B(layoutObject2));
        JSONArray jSONArrayOptJSONArray = dataObject.optJSONArray("carousel");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    JSONObject ad = jSONArrayOptJSONArray.getJSONObject(i);
                    arrayList.add(C01491c.B(ad));
                } catch (JSONException e) {
                    C0585Hx.D(context, "parsing", C0584Hw.jB, new C0583Hv(e));
                    e.printStackTrace();
                }
            }
        } else {
            arrayList.add(C01491c.B(dataObject));
        }
        C01481b c01481b = new C01481b(c01551iB, c1n, arrayList, c01631qD, c01521fB, dataObject.optString(UserDataStore.CITY), dataObject.optString("request_id", ""), dataObject.optInt("viewability_check_initial_delay", 0), dataObject.optInt("viewability_check_interval", 1000), dataObject.optString("dynamic_sdk_layer_html_url", ""), dataObject.optInt("an_logo_type", 0));
        c01481b.C(dataObject);
        return c01481b;
    }

    public final String C() {
        return this.E;
    }

    private void C(JSONObject originalJson) {
        if (!TextUtils.isEmpty(this.F)) {
            this.I = originalJson.toString();
        }
    }

    public final C1N D() {
        return this.B;
    }

    public final String E() {
        return this.F;
    }

    @Nullable
    public final String F() {
        return this.G;
    }

    public final C01521f G() {
        return this.H;
    }

    public final JSONObject H() {
        try {
            return new JSONObject(this.I);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    public final C01551i I() {
        return this.J;
    }

    public final String J() {
        return this.K;
    }

    public final C01631q K() {
        return this.L;
    }

    public final int L() {
        return this.M;
    }

    public final int M() {
        return this.N;
    }

    public final int N() {
        return this.O;
    }

    public final void O(@Nullable String extraHints) {
        this.G = extraHints;
    }

    public final void P(int videoTimePollingIntervalMs) {
        this.M = videoTimePollingIntervalMs;
    }
}
