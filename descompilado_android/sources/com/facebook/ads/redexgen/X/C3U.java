package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.util.Log;
import com.facebook.appevents.UserDataStore;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3U, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C3U {
    private static final String G = C3U.class.getSimpleName();
    private final int B;
    private final C3V E;

    @InterfaceC01641r
    private final String D = null;

    @InterfaceC01641r
    private final String C = null;

    @InterfaceC01641r
    private final String F = null;

    public C3U(C3V errorType) {
        this.E = errorType;
        this.B = errorType.A();
    }

    public final int A() {
        return this.B;
    }

    @InterfaceC01641r
    public final String B() {
        return this.C;
    }

    @InterfaceC01641r
    public final String C() {
        return this.D;
    }

    public final C3V D() {
        return this.E;
    }

    @InterfaceC01641r
    public final String E() {
        return this.F;
    }

    public final int F() {
        int length;
        int length2;
        if (this.D != null) {
            length = this.D.getBytes().length;
        } else {
            length = 0;
        }
        int i = length + 4;
        if (this.C != null) {
            length2 = this.C.getBytes().length;
        } else {
            length2 = 0;
        }
        return length2 + i + (this.F != null ? this.F.getBytes().length : 0);
    }

    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    public final JSONObject G() {
        JSONObject jsonObject = new JSONObject();
        try {
            switch (C3W.B[this.E.ordinal()]) {
                case 1:
                    jsonObject.put("ec", this.B);
                    jsonObject.put("en", this.D);
                    jsonObject.put(UserDataStore.EMAIL, this.C);
                    jsonObject.put("es", this.F);
                    break;
                default:
                    jsonObject.put("ec", this.B);
                    break;
            }
        } catch (JSONException e) {
            Log.e(G, "Error Creating JSON", e);
        }
        return jsonObject;
    }
}
