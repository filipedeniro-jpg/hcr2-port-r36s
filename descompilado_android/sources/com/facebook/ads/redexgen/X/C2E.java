package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;
import com.facebook.ads.AdError;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2E, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C2E extends AnonymousClass28 {
    public C2E(Context context, AnonymousClass25 adControllerConfig) {
        super(context, adControllerConfig);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void G(C0O adAdapter, IP placement, IN placementAd, Map<String, Object> map) {
        ((C01320l) adAdapter).C(this.E, new AnonymousClass11() { // from class: com.facebook.ads.redexgen.X.2D
            @Override // com.facebook.ads.redexgen.X.AnonymousClass11
            public final void JE(AbstractC01310k adapter) {
                this.B.G.A();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass11
            public final void KE(AbstractC01310k adapter) {
                this.B.I = adapter;
                this.B.D = false;
                super/*com.facebook.ads.redexgen.X.28*/.I();
                this.B.G.B(adapter);
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass11
            public final void LE(AbstractC01310k abstractC01310k) {
                C0642Kd.B("onInstreamVideoAdLoggingImpression", "Instream impression fired");
                this.B.G.I();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass11
            public final void ME(AbstractC01310k adapter) {
                this.B.G.E();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass11
            public final void NE(AbstractC01310k adapter, View videoView) {
                this.B.G.C(videoView);
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass11
            public final void OE(AbstractC01310k adapter, AdError error) {
                this.B.G.D(new KQ(error.getErrorCode(), error.getErrorMessage()));
            }
        }, map, this.C, this.B.E);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void K(String rawBidPayload) {
        KQ kqD = C2F.D(this.E, 0);
        if (kqD != null) {
            yD(kqD);
        } else {
            super.K(rawBidPayload);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void M() {
        ((AbstractC01310k) this.I).A();
    }
}
