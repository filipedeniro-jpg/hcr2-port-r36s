package com.facebook.ads.redexgen.X;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ce, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0437Ce implements IA {
    public final WeakReference<InterfaceC0525Fo> B;

    private C0437Ce(WeakReference<InterfaceC0525Fo> weakReference) {
        this.B = weakReference;
    }

    public /* synthetic */ C0437Ce(WeakReference x0, C0440Ch x1) {
        this(x0);
    }

    @Override // com.facebook.ads.redexgen.X.IA
    public final void cF() {
        if (this.B.get() != null) {
            this.B.get().aB(EnumC02926p.REWARD_SERVER_FAILED.B());
        }
    }

    @Override // com.facebook.ads.redexgen.X.IA
    public final void eF(I8 output) {
        if (this.B.get() == null) {
            return;
        }
        if (output != null && output.A()) {
            this.B.get().aB(EnumC02926p.REWARD_SERVER_SUCCESS.B());
        } else {
            this.B.get().aB(EnumC02926p.REWARD_SERVER_FAILED.B());
        }
    }
}
