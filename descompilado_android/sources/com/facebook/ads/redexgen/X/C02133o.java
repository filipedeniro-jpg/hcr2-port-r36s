package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3o, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C02133o implements C4M<String> {
    private static final String D = C02133o.class.getSimpleName();
    private final String B;
    private final String C;

    public C02133o(String systemPropertyName) {
        this.B = systemPropertyName;
        this.C = B(systemPropertyName);
    }

    public final int A() {
        return this.B.getBytes().length + this.C.getBytes().length;
    }

    @SuppressLint({"BadMethodUse-android.util.Log.e", "CatchGeneralException"})
    private static String B(String systemPropertyName) {
        String str = "NOT_FOUND";
        try {
            Object[] objArr = {systemPropertyName};
            if (C02123n.D != null && C02123n.E != null) {
                str = (String) C02123n.E.invoke(C02123n.D, objArr);
                return str;
            }
            return "NOT_FOUND";
        } catch (Exception e) {
            Log.e(D, "Error fetching System Property", e);
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.C4M
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final JSONObject uF(String key, JSONObject inputObject) throws JSONException {
        inputObject.put(this.B, this.C);
        return inputObject;
    }

    @Override // com.facebook.ads.redexgen.X.C4M
    public final boolean SC(Object newMapSignalValueDef) {
        C02133o c02133o = (C02133o) newMapSignalValueDef;
        return this.B.equals(c02133o.B) && this.C.equals(c02133o.C);
    }
}
