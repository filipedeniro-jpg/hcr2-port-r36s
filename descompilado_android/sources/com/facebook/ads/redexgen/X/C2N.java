package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2N, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C2N extends AnonymousClass28 {
    public C2N(Context context, AnonymousClass25 adControllerConfig) {
        super(context, adControllerConfig);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void G(C0O adAdapter, IP placement, IN placementAd, Map<String, Object> map) {
        ((C01440x) adAdapter).E(this.E, new AnonymousClass18() { // from class: com.facebook.ads.redexgen.X.2M
            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void AF(AbstractC01430w adapter) {
                this.B.G.L();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void BF(AbstractC01430w adapter) {
                this.B.G.A();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void CF(AbstractC01430w adapter) {
                this.B.I = adapter;
                super/*com.facebook.ads.redexgen.X.28*/.I();
                this.B.G.B(adapter);
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void DF(AbstractC01430w abstractC01430w) {
                C0642Kd.B("onRewardedVideoAdLoggingImpression", "Rewarded Video impression fired");
                this.B.G.I();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void EF(AbstractC01430w adapter) {
                this.B.G.M();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void FF(AbstractC01430w adapter, AdError error) {
                this.B.G.D(new KQ(AdErrorType.INTERNAL_ERROR, (String) null));
                this.B.A(adapter);
                this.B.H();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void onRewardedVideoActivityDestroyed() {
                this.B.G.N();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void onRewardedVideoClosed() {
                this.B.G.O();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass18
            public final void zE(AbstractC01430w adapter) {
                this.B.G.K();
            }
        }, map, this.B.G, this.B.F, this.B.H);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void M() {
        AbstractC01430w abstractC01430w = (AbstractC01430w) this.I;
        abstractC01430w.C(this.B.D);
        abstractC01430w.B();
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    @Nullable
    public final KQ R() {
        if (!this.B.G || Q()) {
            return null;
        }
        return new KQ(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }

    public final void S(RewardData rewardData) {
        if (this.I == null) {
            throw new IllegalStateException("no adapter ready to set reward on");
        }
        if (this.I.HC() != AdPlacementType.REWARDED_VIDEO) {
            throw new IllegalStateException("can only set on rewarded video ads");
        }
        ((AbstractC01430w) this.I).D(rewardData);
    }
}
