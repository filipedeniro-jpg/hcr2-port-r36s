package com.facebook.ads.redexgen.X;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4b, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02264b {
    private final float B;
    private final float[] C;
    private final int D;
    private final float E;
    private final float F;
    private final int G;
    private final int H;

    public C02264b(int touchEventAction, int pointerId, int toolType, float[] point2DLocation, float size, float pressure, float orientation) {
        this.H = touchEventAction;
        this.D = pointerId;
        this.G = toolType;
        this.C = point2DLocation;
        this.F = size;
        this.E = pressure;
        this.B = orientation;
    }

    public final boolean A(C02264b newTouchSignalRawValue) {
        return H() == newTouchSignalRawValue.H() && D() == newTouchSignalRawValue.D() && G() == newTouchSignalRawValue.G() && Math.abs(C()[0] - newTouchSignalRawValue.C()[0]) < 1.0E-7f && Math.abs(C()[1] - newTouchSignalRawValue.C()[1]) < 1.0E-7f && Math.abs(F() - newTouchSignalRawValue.F()) < 1.0E-7f && Math.abs(E() - newTouchSignalRawValue.E()) < 1.0E-7f && Math.abs(B() - newTouchSignalRawValue.B()) < 1.0E-7f;
    }

    private final float B() {
        return this.B;
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final int m235B() {
        return 32;
    }

    public final JSONObject C(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("tea", this.H);
        jSONObject2.put("ti", this.D);
        jSONObject2.put(TtmlNode.TAG_TT, this.G);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("x", C3O.D(this.C[0]));
        jSONObject3.put("y", C3O.D(this.C[1]));
        jSONObject2.put("tl", jSONObject3);
        jSONObject2.put("ts", C3O.D(this.F));
        jSONObject2.put("tp", C3O.D(this.E));
        jSONObject2.put("to", C3O.D(this.B));
        jSONObject.put("v", jSONObject2);
        return jSONObject;
    }

    private final float[] C() {
        return this.C;
    }

    private final int D() {
        return this.D;
    }

    private final float E() {
        return this.E;
    }

    private final float F() {
        return this.F;
    }

    private final int G() {
        return this.G;
    }

    private final int H() {
        return this.H;
    }
}
