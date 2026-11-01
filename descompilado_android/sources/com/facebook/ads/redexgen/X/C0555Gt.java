package com.facebook.ads.redexgen.X;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0555Gt implements H6 {
    public final WeakReference<C03217s> B;
    public final WeakReference<FV> C;

    public C0555Gt(FV chooseYourOwnAdView, C03217s adListItemView) {
        this.C = new WeakReference<>(chooseYourOwnAdView);
        this.B = new WeakReference<>(adListItemView);
    }

    @Override // com.facebook.ads.redexgen.X.H6
    public final void QD(boolean reachedEndingView) {
        if (this.C.get() != null) {
            this.C.get().setIsAdReportingLayoutVisible(false);
            if (reachedEndingView) {
                this.C.get().A();
            } else {
                this.C.get().B(false);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.H6
    public final void RD(AnonymousClass31 reason, EnumC01982z type) {
        if (this.B.get() != null) {
            this.B.get().B(reason, type);
        }
    }

    @Override // com.facebook.ads.redexgen.X.H6
    public final void SD() {
        FV fv = this.C.get();
        if (fv != null) {
            fv.setIsAdReportingLayoutVisible(true);
            fv.B(true);
        }
    }
}
