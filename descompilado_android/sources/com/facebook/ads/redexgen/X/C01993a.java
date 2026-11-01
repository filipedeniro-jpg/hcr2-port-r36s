package com.facebook.ads.redexgen.X;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01993a extends AbstractC02043f<Integer> {
    public C01993a(long timeStamp, @InterfaceC01641r C3T signalCollectionContext, Integer signalValue) {
        super(timeStamp, signalCollectionContext, signalValue, C3S.INT);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Integer> */
    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
    public final boolean A(AbstractC02043f<Integer> abstractC02043f) {
        return ((Integer) B()).equals(abstractC02043f.m231B());
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
    public final int E() {
        return 4;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
    public final JSONObject F(JSONObject inputObject) throws JSONException {
        inputObject.put("v", B());
        return inputObject;
    }
}
