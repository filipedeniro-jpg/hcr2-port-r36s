package com.facebook.ads.redexgen.X;

import java.util.Comparator;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0472Dn implements Comparator<C0475Dq> {
    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final int compare(C0475Dq lhs, C0475Dq rhs) {
        if ((lhs.E == null) != (rhs.E == null)) {
            return lhs.E == null ? 1 : -1;
        }
        if (lhs.C != rhs.C) {
            return lhs.C ? -1 : 1;
        }
        int i = rhs.F - lhs.F;
        if (i != 0) {
            return i;
        }
        int i2 = lhs.B - rhs.B;
        if (i2 != 0) {
            return i2;
        }
        return 0;
    }
}
