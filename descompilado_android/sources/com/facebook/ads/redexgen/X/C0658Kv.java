package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0658Kv {
    private final IP B;
    private final String C;
    private final Ku D;

    public C0658Kv(Ku mContentType) {
        this(mContentType, null, null);
    }

    public C0658Kv(Ku mContentType, IP mAdPlacement, String mAnValidationUuid) {
        this.D = mContentType;
        this.B = mAdPlacement;
        this.C = mAnValidationUuid;
    }

    public final String A() {
        return this.C;
    }

    public final Ku B() {
        return this.D;
    }

    public IP C() {
        return this.B;
    }
}
