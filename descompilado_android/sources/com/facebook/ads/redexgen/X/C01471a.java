package com.facebook.ads.redexgen.X;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01471a implements Serializable {
    private static final long serialVersionUID = -2102939945352398575L;
    private String B;
    private final List<String> C;

    public C01471a(List<String> screenshots) {
        this.C = screenshots;
    }

    public final String A() {
        return this.B;
    }

    public final List<String> B() {
        return Collections.unmodifiableList(this.C);
    }

    public final void C(String rewardUrl) {
        this.B = rewardUrl;
    }
}
