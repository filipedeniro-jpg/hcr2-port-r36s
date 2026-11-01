package com.facebook.ads.redexgen.X;

import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1c, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01491c implements Serializable {
    private static final long serialVersionUID = 85021702336014823L;
    private final C1Z B;
    private final C01471a C;
    private final boolean D;
    private final boolean E;
    private final C1R F;
    private final C1V G;
    private final boolean H;
    private final boolean I;
    private final boolean J;

    private C01491c(C1V metadata, C1Z ctaData, C1R mediaData, C01471a endCardsData, boolean isWatchAndBrowse, boolean isRewardedAd, boolean shouldShowIntroTransition, boolean shouldShowExitTransition, boolean shouldShowEndCard) {
        this.G = metadata;
        this.B = ctaData;
        this.F = mediaData;
        this.C = endCardsData;
        this.E = isWatchAndBrowse;
        this.J = shouldShowIntroTransition;
        this.H = shouldShowEndCard;
        this.I = shouldShowExitTransition;
        this.D = isRewardedAd;
    }

    public final C1Z A() {
        return this.B;
    }

    public final C01471a B() {
        return this.C;
    }

    public static C01491c B(JSONObject dataObject) {
        C1V c1vB = new C1U().J(dataObject.optString("title")).I(dataObject.optString(MessengerShareContentUtility.SUBTITLE)).D(dataObject.optString(TtmlNode.TAG_BODY)).H(dataObject.optString(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET)).G(dataObject.optString("rating_value")).F(dataObject.optString(PlaceFields.RATING_COUNT)).A(dataObject.optString("ad_creative_type")).B();
        C1Z c1z = new C1Z(dataObject.optString("fbad_command"), dataObject.optString("call_to_action"), dataObject.optString("delay_click_text", "It will automatically open in [secs]s"));
        boolean zOptBoolean = dataObject.optBoolean("video_autoplay_enabled");
        boolean zOptBoolean2 = dataObject.optBoolean("is_watch_and_browse");
        boolean zOptBoolean3 = dataObject.optBoolean("is_rewarded_ad");
        boolean zOptBoolean4 = dataObject.optBoolean("show_intro_transition");
        boolean zOptBoolean5 = dataObject.optBoolean("show_end_card");
        boolean zOptBoolean6 = dataObject.optBoolean("show_exit_transition");
        C1Q c1qB = new C1Q().K(dataObject.optString("video_url")).B(zOptBoolean);
        boolean shouldShowExitTransition = dataObject.optBoolean("is_audio_muted", true);
        C1Q c1qH = c1qB.A(shouldShowExitTransition).I(dataObject.optInt("unskippable_seconds", 0)).H(dataObject.optInt("seconds_for_reward", Integer.MAX_VALUE));
        JSONObject jSONObjectOptJSONObject = dataObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE);
        if (jSONObjectOptJSONObject != null) {
            c1qH.E(jSONObjectOptJSONObject.optString("url")).F(jSONObjectOptJSONObject.optInt("width")).D(jSONObjectOptJSONObject.optInt("height"));
        }
        C01471a c01471a = new C01471a(MJ.B(dataObject.optJSONArray("end_card_images")));
        c1qH.G(C01571k.B(dataObject));
        return new C01491c(c1vB, c1z, c1qH.C(), c01471a, zOptBoolean2, zOptBoolean3, zOptBoolean4, zOptBoolean6, zOptBoolean5);
    }

    public final C1R C() {
        return this.F;
    }

    public final C1V D() {
        return this.G;
    }

    public final boolean E() {
        return this.D;
    }

    public final boolean F() {
        return this.E;
    }

    public final boolean G() {
        return this.H;
    }

    public final boolean H() {
        return this.I;
    }

    public final boolean I() {
        return this.J;
    }
}
