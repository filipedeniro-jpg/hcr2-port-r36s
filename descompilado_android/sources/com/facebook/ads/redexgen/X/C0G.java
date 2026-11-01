package com.facebook.ads.redexgen.X;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0G, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0G {
    private final String B;
    private final String C;
    private final String D;
    private final List<String> E;
    private final String F;
    private final String G;

    private C0G(String mPackageName, String mClassName, String mAppLinkUri, List<String> mKeyHashes, String mMarketUri, String mFallbackUrl) {
        this.G = mPackageName;
        this.C = mClassName;
        this.B = mAppLinkUri;
        this.E = mKeyHashes;
        this.F = mMarketUri;
        this.D = mFallbackUrl;
    }

    public final String A() {
        return this.B;
    }

    public static C0G B(JSONObject dataObject) {
        if (dataObject == null) {
            return null;
        }
        String appsiteUri = dataObject.optString("package");
        String mMarketUri = dataObject.optString("appsite");
        String strOptString = dataObject.optString("appsite_url");
        JSONArray jSONArrayOptJSONArray = dataObject.optJSONArray("key_hashes");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
        }
        return new C0G(appsiteUri, mMarketUri, strOptString, arrayList, dataObject.optString("market_uri"), dataObject.optString(MessengerShareContentUtility.FALLBACK_URL));
    }

    public final String B() {
        return this.C;
    }

    public final String C() {
        return this.G;
    }
}
