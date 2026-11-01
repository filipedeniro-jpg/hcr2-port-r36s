package com.facebook.ads.redexgen.X;

import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7n, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03167n implements DG {
    public final WeakReference<C03157m> B;

    private C03167n(C03157m mEndCardController) {
        this.B = new WeakReference<>(mEndCardController);
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void IE(C5I adViewabilityChecker, C0621Ji touchDataRecorder) {
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void WE() {
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void YE() {
        iD();
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void iD() {
        if (this.B.get() != null) {
            this.B.get().E();
        }
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void oD(boolean skipPlayStore, Map<String, String> map) {
        if (this.B.get() != null) {
            this.B.get().F(map).performClick();
        }
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void qE() {
    }
}
