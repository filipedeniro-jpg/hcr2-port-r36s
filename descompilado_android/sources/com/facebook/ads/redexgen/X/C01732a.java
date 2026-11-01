package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.EnumSet;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01732a implements C2V {
    private static final String G = C01732a.class.getSimpleName();
    private boolean B;
    private boolean C;
    private C2I D;
    private final InterstitialAdExtendedListener E;
    private final C01782f F;

    public C01732a(C01782f interstitialAdModel, C2T messageListener, String adId) {
        this.F = interstitialAdModel;
        this.E = new C01842l(adId, messageListener, this);
    }

    public final void A(EnumSet<CacheFlag> enumSet, @Nullable String bidPayload) {
        if (!this.C && this.D != null) {
            Log.w(G, "An ad load is already in progress. You should wait for adLoaded() to be called");
        }
        this.C = false;
        if (this.B) {
            C0585Hx.D(this.F.D, "api", C0584Hw.L, new C0583Hv("Interstitial load called while showing interstitial."));
            this.E.onError(this.F.A(), new AdError(AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getErrorCode(), AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getDefaultErrorMessage()));
            return;
        }
        if (this.D != null) {
            this.D.L(new C0P() { // from class: com.facebook.ads.redexgen.X.2W
            });
            this.D.O();
            this.D = null;
        }
        AnonymousClass25 anonymousClass25 = new AnonymousClass25(this.F.I, KY.C(this.F.D.getResources().getDisplayMetrics()), AdPlacementType.INTERSTITIAL, KV.INTERSTITIAL, 1, enumSet);
        anonymousClass25.D(this.F.G);
        anonymousClass25.G(this.F.H);
        this.D = new C2I(this.F.D, anonymousClass25);
        this.D.L(new C0P() { // from class: com.facebook.ads.redexgen.X.2Y
            @Override // com.facebook.ads.redexgen.X.C0P
            public final void A() {
                this.B.E.onAdClicked(this.B.F.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void B(C0O adAdapter) {
                this.B.C = true;
                this.B.E.onAdLoaded(this.B.F.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void C(View view) {
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void D(KQ error) {
                this.B.E.onError(this.B.F.A(), KQ.E(error));
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void F() {
                this.B.B = false;
                this.B.E.onInterstitialActivityDestroyed();
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void G() {
                this.B.B = false;
                if (this.B.D != null) {
                    this.B.D.L(new C0P() { // from class: com.facebook.ads.redexgen.X.2X
                    });
                    this.B.D.O();
                    this.B.D = null;
                }
                this.B.E.onInterstitialDismissed(this.B.F.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void H() {
                this.B.E.onInterstitialDisplayed(this.B.F.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void I() {
                this.B.E.onLoggingImpression(this.B.F.A());
            }

            @Override // com.facebook.ads.redexgen.X.C0P
            public final void M() {
                this.B.E.onRewardedAdCompleted();
            }
        });
        this.D.E(bidPayload);
    }

    public final long B() {
        if (this.D != null) {
            return this.D.B();
        }
        return -1L;
    }

    public final boolean C() {
        return this.D == null || this.D.F();
    }

    public final boolean D() {
        return this.C;
    }

    public final boolean E() {
        if (!this.C) {
            this.E.onError(this.F.A(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        if (this.D == null) {
            C0585Hx.D(this.F.D, "api", C0584Hw.Q, new C0583Hv(AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL.getDefaultErrorMessage()));
            this.E.onError(this.F.A(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        this.D.N();
        this.B = true;
        this.C = false;
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.C2V
    public final void destroy() {
        if (this.D != null) {
            this.D.L(new C0P() { // from class: com.facebook.ads.redexgen.X.2Z
            });
            this.D.P(true);
            this.D = null;
            this.C = false;
            this.B = false;
        }
    }
}
