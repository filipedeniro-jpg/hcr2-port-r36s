package com.facebook.ads.redexgen.X;

import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardData;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;
import com.facebook.ads.internal.protocol.AdPlacementType;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2d, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01762d implements C2V {
    private static final String F = C01762d.class.getSimpleName();
    private boolean B = false;
    private C2N C;
    private final S2SRewardedVideoAdExtendedListener D;
    private final C01822j E;

    public C01762d(C01822j rewardedVideoAdModel, C2T messageListener, String adId) {
        this.E = rewardedVideoAdModel;
        this.D = new C01862n(adId, messageListener, this, rewardedVideoAdModel);
    }

    public final void A(String bidPayload, boolean failOnCacheFailure) {
        try {
            F(bidPayload, failOnCacheFailure);
        } catch (Exception e) {
            Log.e(F, "Error loading rewarded video ad", e);
            C0585Hx.D(this.E.D, "api", C0584Hw.Y, new C0583Hv(e));
            this.D.onError(this.E.A(), AdError.internalError(2004));
        }
    }

    public final long B() {
        if (this.C != null) {
            return this.C.B();
        }
        return -1L;
    }

    public final boolean C() {
        return this.C == null || this.C.F();
    }

    public final boolean D() {
        return this.B;
    }

    public final void E(RewardData rewardData) {
        this.E.J = rewardData;
        if (this.B) {
            this.C.S(rewardData);
        }
    }

    private void F(String bidPayload, boolean failOnCacheFailure) {
        if (!this.B && this.C != null) {
            Log.w(F, "An ad load is already in progress. You should wait for adLoaded() to be called");
        }
        G(false);
        this.B = false;
        AnonymousClass25 anonymousClass25 = new AnonymousClass25(this.E.I, KW.REWARDED_VIDEO, AdPlacementType.REWARDED_VIDEO, KV.INTERSTITIAL, 1);
        anonymousClass25.E(failOnCacheFailure);
        anonymousClass25.D(this.E.F);
        anonymousClass25.G(this.E.H);
        this.C = new C2N(this.E.D, anonymousClass25);
        this.C.L(new C0P() { // from class: com.facebook.ads.redexgen.X.2b
            @Override // com.facebook.ads.redexgen.X.C0P
            public final void A() {
                this.B.D.onAdClicked(this.B.E.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void B(C0O adapter) {
                AbstractC01430w abstractC01430w = (AbstractC01430w) adapter;
                if (this.B.E.J != null) {
                    abstractC01430w.D(this.B.E.J);
                }
                this.B.E.K = abstractC01430w.A();
                this.B.B = true;
                this.B.D.onAdLoaded(this.B.E.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void D(KQ error) {
                this.B.G(true);
                this.B.D.onError(this.B.E.A(), KQ.E(error));
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void I() {
                this.B.D.onLoggingImpression(this.B.E.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void K() {
                this.B.D.onRewardServerFailed();
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void L() {
                this.B.D.onRewardServerSuccess();
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void M() {
                this.B.D.onRewardedVideoCompleted();
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void N() {
                this.B.D.onRewardedVideoActivityDestroyed();
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void O() {
                this.B.D.onRewardedVideoClosed();
            }
        });
        this.C.E(bidPayload);
    }

    public final boolean F(int appOrientation) {
        if (!this.B) {
            this.D.onError(this.E.A(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        if (this.C != null) {
            this.C.B.C(appOrientation);
            this.C.N();
            this.B = false;
            return true;
        }
        this.B = false;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(boolean destroyAd) {
        if (this.C != null) {
            this.C.L(new C0P() { // from class: com.facebook.ads.redexgen.X.2c
            });
            this.C.P(destroyAd);
            this.C = null;
        }
    }

    @Override // com.facebook.ads.redexgen.X.C2V
    public final void destroy() {
        G(true);
    }
}
