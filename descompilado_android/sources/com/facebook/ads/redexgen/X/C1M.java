package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1M, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C1M implements Serializable {
    private static final long serialVersionUID = -5352540727250859603L;

    @Nullable
    private String B;
    private String C;
    private int D = 200;

    public abstract String A();

    public abstract int B();

    public abstract int D();

    public static C1M B(JSONObject dataObject) {
        return dataObject.has("choosable_ads") ? C1W.C(dataObject) : C01601n.C(dataObject);
    }

    @Nullable
    public final String C() {
        return this.B;
    }

    public final String E() {
        return this.C;
    }

    public final int F() {
        return this.D;
    }

    public void G(@Nullable String extraHints) {
        this.B = extraHints;
    }

    public void H(String rewardUrl) {
        this.C = rewardUrl;
    }

    public final void I(int videoTimePollingIntervalMs) {
        this.D = videoTimePollingIntervalMs;
    }
}
