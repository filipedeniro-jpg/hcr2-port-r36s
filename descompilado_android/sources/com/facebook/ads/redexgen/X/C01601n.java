package com.facebook.ads.redexgen.X;

import com.facebook.appevents.UserDataStore;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1n, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01601n extends C1M {
    private final C1N B;
    private final C1R C;
    private final C1V D;
    private final int E;
    private final String F;
    private final C1Z G;
    private final C01471a H;
    private boolean I;
    private final C01521f J;
    private final C01551i K;
    private final String L;
    private final C01631q M;

    private C01601n(String requestId, C01551i pageDetails, C1V adMetadata, C1Z ctaData, C1N adColorsData, C1R adMediaData, C01471a endCardsData, C01631q toolbarDetails, C01521f optOutRewardDetails, String clientToken, int anLogoType) {
        this.L = requestId;
        this.E = anLogoType;
        this.K = pageDetails;
        this.D = adMetadata;
        this.G = ctaData;
        this.B = adColorsData;
        this.C = adMediaData;
        this.H = endCardsData;
        this.F = clientToken;
        this.M = toolbarDetails;
        this.J = optOutRewardDetails;
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    public final String A() {
        return this.F;
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    public final int B() {
        return this.C.D() != null ? 1 : 0;
    }

    public static C01601n C(JSONObject dataObject) {
        return D(dataObject, dataObject.optInt("an_logo_type", 0));
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    public final int D() {
        return this.C.G();
    }

    public static C01601n D(JSONObject dataObject, int anLogoType) {
        String strOptString;
        JSONObject layoutObject;
        String strOptString2;
        C01541h c01541hD = new C01541h().D(dataObject.optString("advertiser_name"));
        if (dataObject.optJSONObject("icon") != null) {
            strOptString = dataObject.optJSONObject("icon").optString("url");
        } else {
            strOptString = "";
        }
        C01551i c01551iB = c01541hD.C(strOptString).A(dataObject.optString("ad_choices_link_url")).E(F(dataObject)).B();
        C01631q c01631qD = C01561j.D(dataObject);
        C01521f c01521fB = C01561j.B(dataObject);
        C1V c1vB = new C1U().J(dataObject.optString("title")).I(dataObject.optString(MessengerShareContentUtility.SUBTITLE)).D(dataObject.optString(TtmlNode.TAG_BODY)).G(dataObject.optString("rating_value")).C(dataObject.optString("category")).E(dataObject.optString("destination_title")).A(dataObject.optString("ad_creative_type")).H(dataObject.optString(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET)).B();
        C1Z c1z = new C1Z(dataObject.optString("fbad_command"), dataObject.optString("call_to_action"), E(dataObject));
        JSONObject jSONObjectOptJSONObject = dataObject.optJSONObject(TtmlNode.TAG_LAYOUT);
        if (jSONObjectOptJSONObject != null) {
            layoutObject = jSONObjectOptJSONObject.optJSONObject("portrait");
        } else {
            layoutObject = null;
        }
        C1N c1n = new C1N(C1Y.B(layoutObject), C1Y.B(jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optJSONObject("landscape") : null));
        C1Q c1qK = new C1Q().K(dataObject.optString("video_url"));
        if (dataObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE) != null) {
            strOptString2 = dataObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE).optString("url");
        } else {
            strOptString2 = "";
        }
        return new C01601n(dataObject.optString("request_id"), c01551iB, c1vB, c1z, c1n, c1qK.E(strOptString2).I(dataObject.optInt("skippable_seconds", -1)).J(dataObject.optInt("video_duration_sec")).G(C01571k.B(dataObject)).H(dataObject.optInt("seconds_for_reward", Integer.MAX_VALUE)).C(), new C01471a(MJ.B(dataObject.optJSONArray("end_card_images"))), c01631qD, c01521fB, dataObject.optString(UserDataStore.CITY), anLogoType);
    }

    private static String E(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "It will automatically open in [secs]s" : dataObject2.optString("delay_click_text", "It will automatically open in [secs]s");
    }

    private static String F(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Sponsored" : dataObject2.optString("sponsored", "Sponsored");
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    public final void H(String rewardUrl) {
        super.H(rewardUrl);
        this.H.C(rewardUrl);
    }

    public final C1N J() {
        return this.B;
    }

    public final C1R K() {
        return this.C;
    }

    public final C1V L() {
        return this.D;
    }

    public final int M() {
        return this.E;
    }

    public final C1Z N() {
        return this.G;
    }

    public final C01471a O() {
        return this.H;
    }

    public final C01551i P() {
        return this.K;
    }

    public final String Q() {
        return this.L;
    }

    public final C01631q R() {
        return this.M;
    }

    public final boolean S() {
        return this.I;
    }

    public final void T(boolean isPartOfCYOA) {
        this.I = isPartOfCYOA;
    }
}
