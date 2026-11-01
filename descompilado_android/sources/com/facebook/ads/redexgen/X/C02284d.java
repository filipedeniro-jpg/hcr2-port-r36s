package com.facebook.ads.redexgen.X;

import com.my.target.i;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4d, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02284d {
    private static final String[] C = {"x", "y", "z", i.WIDTH};
    private final float[] B;

    public C02284d(float[] sensorValues) {
        this.B = sensorValues;
    }

    public final boolean A(C02284d newTouchSignalRawValue) {
        float[] fArrB = newTouchSignalRawValue.B();
        if (fArrB.length != this.B.length) {
            return false;
        }
        for (int i = 0; i < fArrB.length; i++) {
            if (Math.abs(B()[i] - fArrB[i]) < 1.0E-7f) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final int m236B() {
        return this.B.length * 4;
    }

    private final float[] B() {
        return this.B;
    }

    public final JSONObject C(JSONObject inputObject) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < this.B.length; i++) {
            jSONObject.put(C[i], this.B[i]);
        }
        inputObject.put("v", jSONObject);
        return inputObject;
    }
}
