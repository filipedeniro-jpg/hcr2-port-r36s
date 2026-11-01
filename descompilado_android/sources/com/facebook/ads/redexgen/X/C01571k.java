package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import com.facebook.share.internal.ShareConstants;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1k, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01571k implements Serializable {
    private static final B6 M = B6.PORTRAIT;
    private static final EnumC01591m N = EnumC01591m.FILE_PRECACHE;
    private static final long serialVersionUID = -5352540123250859603L;
    private String B;
    private final String C;
    private final String D;
    private final boolean E;
    private final boolean F;
    private final String G;
    private final B6 H;
    private final EnumC01591m I;
    private final String J;
    private final int K;
    private final int L;

    private C01571k(String markupUrl, String introCardIconUrl, int skippableTimeInSecs, String rewardedPlayText, String delayedClickText, B6 orientation, int webViewTimeoutInMillis, boolean isIntroCardEnabled, boolean isEndCardEnabled, EnumC01591m precachingMethod) {
        this.G = markupUrl;
        this.D = introCardIconUrl;
        this.K = skippableTimeInSecs;
        this.J = rewardedPlayText;
        this.C = delayedClickText;
        this.H = orientation;
        this.L = webViewTimeoutInMillis;
        this.F = isIntroCardEnabled;
        this.E = isEndCardEnabled;
        this.I = precachingMethod;
    }

    public final String A() {
        return this.B;
    }

    @Nullable
    public static C01571k B(JSONObject dataObject) {
        JSONObject jSONObjectOptJSONObject = dataObject.optJSONObject("playable_data");
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        EnumC01591m enumC01591mB = EnumC01591m.B(jSONObjectOptJSONObject.optString("precaching_method", N.name()));
        String strOptString = jSONObjectOptJSONObject.optString(ShareConstants.MEDIA_URI);
        String precachingMethodStr = jSONObjectOptJSONObject.optString("intro_card_icon_url");
        return new C01571k(strOptString, precachingMethodStr, F(dataObject), E(jSONObjectOptJSONObject), C(jSONObjectOptJSONObject), D(jSONObjectOptJSONObject), jSONObjectOptJSONObject.optInt("web_view_timeout_in_milliseconds", 5000), jSONObjectOptJSONObject.optBoolean("enable_intro_card", true), jSONObjectOptJSONObject.optBoolean("enable_end_card"), enumC01591mB);
    }

    public final String B() {
        return this.C;
    }

    public final String C() {
        return this.D;
    }

    private static String C(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Play Store will automatically open in [secs]s" : dataObject2.optString("delay_click_text", "Play Store will automatically open in [secs]s");
    }

    private static B6 D(JSONObject playableAdDataJson) {
        return B6.B(playableAdDataJson.optInt("orientation", M.A()));
    }

    public final String D() {
        return this.G;
    }

    public final B6 E() {
        return this.H;
    }

    private static String E(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Rewarded Play" : dataObject2.optString("rewarded_play_text", "Rewarded Play");
    }

    private static int F(JSONObject dataObject) {
        if (dataObject.has("skippable_seconds")) {
            return dataObject.optInt("skippable_seconds");
        }
        return dataObject.optInt("unskippable_seconds", 0);
    }

    public final EnumC01591m F() {
        return this.I;
    }

    public final String G() {
        return this.J;
    }

    public final int H() {
        return this.K;
    }

    public final int I() {
        return this.L;
    }

    public final boolean J() {
        return this.E;
    }

    public final boolean K() {
        return this.F;
    }

    public final void L(String cachedMarkupUrl) {
        this.B = cachedMarkupUrl;
    }
}
