package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0r, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC01380r implements InterfaceC01280h {
    private final boolean B;

    public abstract void A(boolean z);

    public abstract void B();

    public AbstractC01380r(boolean failOnCacheFailure) {
        this.B = failOnCacheFailure;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void dD() {
        if (this.B) {
            B();
        } else {
            A(false);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void nD() {
        A(true);
    }
}
