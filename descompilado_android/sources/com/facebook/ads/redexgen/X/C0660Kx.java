package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0660Kx extends C0658Kv {
    private final int B;
    private final String C;

    public C0660Kx(String mErrorMsg, int mErrorCode, IP adPlacement) {
        super(Ku.ERROR, adPlacement, null);
        this.B = mErrorCode;
        this.C = mErrorMsg;
    }

    @Override // com.facebook.ads.redexgen.X.C0658Kv
    public final /* bridge */ /* synthetic */ IP C() {
        return super.C();
    }

    public final int D() {
        return this.B;
    }

    public final String E() {
        return this.C;
    }
}
