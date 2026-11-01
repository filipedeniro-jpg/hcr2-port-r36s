package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4W, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C4W extends C4Z {
    private final List<C02113m> B;

    public C4W(AnonymousClass56 baseBdConfig) {
        super(baseBdConfig);
        this.B = new ArrayList();
    }

    public final void B(C02113m bdSignal) {
        if (!bdSignal.B().contains(EnumC02053g.DYNAMIC)) {
            this.B.add(bdSignal);
        }
    }

    public final void C(EnumC02083j reputationTier) {
        A(reputationTier, this.B);
    }
}
