package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4X, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C4X extends C4Z {
    private final List<C02113m> B;

    public C4X(AnonymousClass56 baseBDConfig) {
        super(baseBDConfig);
        this.B = new ArrayList();
    }

    public final void B(C02113m bdSignal) {
        if (bdSignal.B().contains(EnumC02053g.DYNAMIC)) {
            this.B.add(bdSignal);
        }
    }

    public final void C(EnumC02083j reputationTier) {
        A(reputationTier, this.B);
    }

    public final List<C02113m> D() {
        return Collections.unmodifiableList(this.B);
    }
}
