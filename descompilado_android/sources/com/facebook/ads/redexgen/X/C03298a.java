package com.facebook.ads.redexgen.X;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03298a {
    private final C01491c B;
    private final int C;
    private final int D;

    public C03298a(int cardIndex, int cardCount, C01491c adInfo) {
        this.D = cardIndex;
        this.C = cardCount;
        this.B = adInfo;
    }

    public final C01491c A() {
        return this.B;
    }

    public final int B() {
        return this.D;
    }

    public final Map<String, String> C() {
        HashMap map = new HashMap();
        map.put("cardind", this.D + "");
        map.put("cardcnt", this.C + "");
        return map;
    }
}
