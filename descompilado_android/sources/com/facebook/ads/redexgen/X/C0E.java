package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0E, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C0E extends AnonymousClass09 {
    public final boolean B;
    public final C0M C;

    public abstract void D();

    public C0E(Context context, JV adEventManager, String clientToken, @Nullable C0M userReturnTracker, boolean actionEnabled) {
        super(context, adEventManager, clientToken);
        this.C = userReturnTracker;
        this.B = actionEnabled;
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass09
    public final void A() {
        if (this.C != null) {
            this.C.B(super.C);
        }
        D();
    }

    public final void C(Map<String, String> userReturnDataMap, @Nullable AnonymousClass08 actionOutcome) {
        if (!TextUtils.isEmpty(super.C)) {
            if (this instanceof C0F) {
                super.B.yC(super.C, userReturnDataMap);
            } else {
                super.B.nC(super.C, userReturnDataMap);
            }
            boolean zB = AnonymousClass08.B(actionOutcome);
            if (this.C != null) {
                this.C.A(actionOutcome);
                if (zB) {
                    this.C.C();
                }
            } else {
                HashMap map = new HashMap();
                map.put("leave_time", Long.toString(-1L));
                map.put("back_time", Long.toString(-1L));
                map.put("outcome", AnonymousClass08.CANNOT_TRACK.name());
                super.B.CD(super.C, map);
            }
        }
        MM.B(this.D, "Click logged");
    }
}
