package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4o, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02394o {
    private final List<InterfaceC02294e> B = new ArrayList();

    public final void A(InterfaceC02294e toAdd) {
        this.B.add(toAdd);
    }

    public final void B() {
        Iterator<InterfaceC02294e> it = this.B.iterator();
        while (it.hasNext()) {
            it.next().WF();
        }
    }
}
