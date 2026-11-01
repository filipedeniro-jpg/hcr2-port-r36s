package com.facebook.ads.redexgen.X;

import com.facebook.appevents.UserDataStore;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0y, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01450y implements C0C {
    private final String B;
    private final String C;
    private final Collection<String> D;
    private final C0B E;
    private final String F;
    private final Map<String, String> G;
    private final String H;

    private C01450y(String mMarkup, String mActivationCommand, C0B mInvalidationBehavior, Collection<String> mDetectionStrings, Map<String, String> mMetadata, String mRequestId, String mClientToken) {
        this.F = mMarkup;
        this.B = mActivationCommand;
        this.E = mInvalidationBehavior;
        this.D = mDetectionStrings;
        this.G = mMetadata;
        this.H = mRequestId;
        this.C = mClientToken;
    }

    public final String A() {
        return this.B;
    }

    @Override // com.facebook.ads.redexgen.X.C0C, com.facebook.ads.redexgen.X.C0O
    public final String AC() {
        return this.C;
    }

    public static C01450y B(JSONObject dataObject) {
        if (dataObject == null) {
            return null;
        }
        String strOptString = dataObject.optString("markup");
        String strOptString2 = dataObject.optString("activation_command");
        String mClientToken = dataObject.optString("request_id");
        String strD = MJ.D(dataObject, UserDataStore.CITY);
        String mRequestId = dataObject.optString("invalidation_behavior");
        C0B c0bB = C0B.B(mRequestId);
        JSONArray jSONArray = null;
        try {
            jSONArray = new JSONArray(dataObject.optString("detection_strings"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Collection<String> collectionC = C0D.C(jSONArray);
        JSONObject jSONObjectOptJSONObject = dataObject.optJSONObject(TtmlNode.TAG_METADATA);
        HashMap map = new HashMap();
        if (jSONObjectOptJSONObject != null) {
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String key = jSONObjectOptJSONObject.optString(next);
                map.put(next, key);
            }
        }
        return new C01450y(strOptString, strOptString2, c0bB, collectionC, map, mClientToken, strD);
    }

    public final String B() {
        return this.F;
    }

    @Override // com.facebook.ads.redexgen.X.C0C
    public final Collection<String> BC() {
        return this.D;
    }

    @Override // com.facebook.ads.redexgen.X.C0C
    public final C0B EC() {
        return this.E;
    }
}
