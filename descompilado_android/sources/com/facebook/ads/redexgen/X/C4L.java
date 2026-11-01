package com.facebook.ads.redexgen.X;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4L, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C4L implements C4M<Integer> {
    private final int B;
    private final int C;
    private final int D;

    public C4L(int minStreamValue, int currentStreamVolume, int maxStreamVolume) {
        this.D = minStreamValue;
        this.B = currentStreamVolume;
        this.C = maxStreamVolume;
    }

    public final int A() {
        return "mi".getBytes().length + "cu".getBytes().length + "ma".getBytes().length + 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.C4M
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final JSONObject uF(Integer key, JSONObject inputObject) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mi", this.D);
        jSONObject.put("cu", this.B);
        jSONObject.put("ma", this.C);
        inputObject.put(key.toString(), jSONObject);
        return inputObject;
    }

    @Override // com.facebook.ads.redexgen.X.C4M
    public final boolean SC(Object newMapSignalValueDef) {
        C4L c4l = (C4L) newMapSignalValueDef;
        return this.C == c4l.C && this.B == c4l.B && this.D == c4l.D;
    }
}
