package com.facebook.ads.redexgen.X;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0Q, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C0Q {
    public final C0R B;
    public final C5I C;
    private final Context D;
    private boolean E;

    public abstract void B(Map<String, String> map);

    public C0Q(Context mContext, C0R impressionHelper, C5I viewabilityChecker) {
        this.D = mContext;
        this.B = impressionHelper;
        this.C = viewabilityChecker;
    }

    public final void A() {
        if (this.E) {
            return;
        }
        if (this.B != null) {
            this.B.B();
        }
        HashMap map = new HashMap();
        if (this.C != null) {
            this.C.A(map);
        }
        B(map);
        this.E = true;
        MM.B(this.D, "Impression logged");
        if (this.B == null) {
            return;
        }
        this.B.A();
    }
}
