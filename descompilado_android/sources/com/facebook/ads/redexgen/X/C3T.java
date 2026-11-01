package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3T, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C3T {
    private static final String C = C3T.class.getSimpleName();
    private final String B;

    public C3T(String contextName) {
        this.B = contextName;
    }

    public final String A() {
        return this.B;
    }

    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    public final JSONObject B() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cn", this.B);
        } catch (JSONException e) {
            Log.e(C, "Error Creating JSON", e);
        }
        return jsonObject;
    }
}
