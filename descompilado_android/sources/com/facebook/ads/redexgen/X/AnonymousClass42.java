package com.facebook.ads.redexgen.X;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.42, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnonymousClass42 implements C4M<String> {
    private final String B;
    private final boolean C;

    public AnonymousClass42(String featureName) {
        this.B = featureName;
        this.C = AnonymousClass41.B.hasSystemFeature(featureName);
    }

    public final int A() {
        return this.B.getBytes().length + 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.C4M
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final JSONObject uF(String key, JSONObject inputObject) throws JSONException {
        inputObject.put(this.B, this.C);
        return inputObject;
    }

    @Override // com.facebook.ads.redexgen.X.C4M
    public final boolean SC(Object newMapSignalValueDef) {
        AnonymousClass42 anonymousClass42 = (AnonymousClass42) newMapSignalValueDef;
        return this.B.equals(anonymousClass42.B) && this.C == anonymousClass42.C;
    }
}
