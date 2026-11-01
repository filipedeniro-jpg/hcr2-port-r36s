package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.53, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass53 {
    private final List<AnonymousClass54> B = new ArrayList();

    public final void A(AnonymousClass54 toAdd) {
        this.B.add(toAdd);
    }

    public final void B(EnumC02083j reputationTier) {
        Iterator<AnonymousClass54> it = this.B.iterator();
        while (it.hasNext()) {
            it.next().AG(reputationTier);
        }
    }
}
