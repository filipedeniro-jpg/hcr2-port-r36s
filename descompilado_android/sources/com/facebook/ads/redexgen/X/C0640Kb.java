package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.my.target.i;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0640Kb {

    @Nullable
    private final Long B;

    @Nullable
    private final String C;

    @Nullable
    private final String D;

    @Nullable
    private final String E;
    private final EnumC0639Ka F;

    public C0640Kb(Context context, @Nullable String bidPayload, String placementId, KW adTemplate) throws KR {
        if (TextUtils.isEmpty(bidPayload)) {
            this.F = EnumC0639Ka.NONE;
            this.B = null;
            this.D = null;
            this.E = null;
            this.C = null;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(bidPayload);
            switch (KZ.B[EnumC0639Ka.valueOf(jSONObject.getString("type").toUpperCase()).ordinal()]) {
                case 1:
                    this.F = EnumC0639Ka.ID;
                    this.B = Long.valueOf(jSONObject.getString(i.ae));
                    this.C = jSONObject.optString("bid_time_token", null);
                    this.D = jSONObject.getString("device_id");
                    this.E = null;
                    break;
                case 2:
                    this.F = EnumC0639Ka.CREATIVE;
                    this.B = Long.valueOf(jSONObject.getString(i.ae));
                    this.C = jSONObject.optString("bid_time_token", null);
                    this.D = jSONObject.getString("device_id");
                    this.E = new JSONObject(jSONObject.getString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD)).toString();
                    break;
                default:
                    throw new KR(AdErrorType.BID_PAYLOAD_ERROR, "Unsupported BidPayload type " + jSONObject.getString("type"));
            }
            if (!jSONObject.getString("sdk_version").equals(BuildConfigApi.getVersionName())) {
                throw new KR(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for SDK version %s being used on SDK version %s", this.B, jSONObject.getString("sdk_version"), BuildConfigApi.getVersionName()));
            }
            if (!jSONObject.getString("resolved_placement_id").equals(placementId)) {
                throw new KR(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for placement %s being used on placement %s", this.B, jSONObject.getString("resolved_placement_id"), placementId));
            }
            HashSet hashSet = new HashSet(Arrays.asList(Integer.valueOf(KW.WEBVIEW_INTERSTITIAL_HORIZONTAL.A()), Integer.valueOf(KW.WEBVIEW_INTERSTITIAL_VERTICAL.A()), Integer.valueOf(KW.WEBVIEW_INTERSTITIAL_TABLET.A()), Integer.valueOf(KW.WEBVIEW_INTERSTITIAL_UNKNOWN.A())));
            if (jSONObject.getInt("template") == adTemplate.A()) {
                return;
            }
            if (hashSet.contains(Integer.valueOf(jSONObject.getInt("template"))) && hashSet.contains(Integer.valueOf(adTemplate.A()))) {
            } else {
                throw new KR(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for template %s being used on template %s", this.B, Integer.valueOf(jSONObject.getInt("template")), adTemplate));
            }
        } catch (JSONException e) {
            C0585Hx.D(context, "api", C0584Hw.N, new C0583Hv(e));
            throw new KR(AdErrorType.BID_PAYLOAD_ERROR, "Invalid BidPayload", e);
        }
    }

    @Nullable
    public final String A() {
        if (this.B == null) {
            return null;
        }
        return this.B.toString();
    }

    @Nullable
    public static KW B(String bidPayload) throws KR {
        try {
            JSONObject json = new JSONObject(bidPayload);
            int templateId = json.getInt("template");
            return KW.B(templateId);
        } catch (JSONException e) {
            throw new KR(AdErrorType.BID_PAYLOAD_ERROR, "Invalid BidPayload", e);
        }
    }

    @Nullable
    public final String B() {
        return this.C;
    }

    @Nullable
    public final String C() {
        return this.E;
    }

    public static void C(KW template) throws KR {
        if (!KW.WEBVIEW_BANNER_LEGACY.equals(template) && !KW.WEBVIEW_BANNER_50.equals(template) && !KW.WEBVIEW_BANNER_90.equals(template) && !KW.WEBVIEW_BANNER_250.equals(template)) {
            throw new KR(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Template %s is not valid banner template", Integer.valueOf(template.A())));
        }
    }

    public final boolean D() {
        return this.F != EnumC0639Ka.NONE;
    }

    public final boolean E() {
        return this.F == EnumC0639Ka.CREATIVE;
    }

    public final void F(String mIdfa) throws KR {
        if (!this.D.equals(mIdfa)) {
            throw new KR(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for IDFA %s being used on IDFA %s", this.B, this.D, mIdfa));
        }
    }
}
