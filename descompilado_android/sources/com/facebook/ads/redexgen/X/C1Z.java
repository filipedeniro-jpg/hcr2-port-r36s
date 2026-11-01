package com.facebook.ads.redexgen.X;

import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1Z, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1Z implements Serializable {
    private static final long serialVersionUID = -4041915335826065133L;
    private final String B;
    private final String C;
    private final String D;

    public C1Z(String ctaUrl, String ctaText, String ctaDelayText) {
        this.D = B(ctaUrl);
        this.C = B(ctaText);
        this.B = B(ctaDelayText);
    }

    public final String A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }

    private static String B(String ctaDataString) {
        if ("null".equalsIgnoreCase(ctaDataString)) {
            return "";
        }
        return ctaDataString;
    }

    public final String C() {
        return this.D;
    }
}
