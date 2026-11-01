package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;
import com.facebook.ads.AdError;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.29, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass29 extends AnonymousClass28 {
    public AnonymousClass29(Context context, AnonymousClass25 adControllerConfig) {
        super(context, adControllerConfig);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void G(C0O adAdapter, IP placement, IN placementAd, final Map<String, Object> map) {
        final C0Z c0z = (C0Z) adAdapter;
        final Runnable runnable = new Runnable() { // from class: com.facebook.ads.redexgen.X.26
            @Override // java.lang.Runnable
            public final void run() {
                this.B.J(map);
                this.B.A(c0z);
                this.B.H();
            }
        };
        C().postDelayed(runnable, placement.E().H());
        c0z.A(this.E, this.C, this.B.B, new C0U() { // from class: com.facebook.ads.redexgen.X.27
            @Override // com.facebook.ads.redexgen.X.C0U
            public final void YD(C0Z adapter) {
                this.B.G.A();
            }

            @Override // com.facebook.ads.redexgen.X.C0U
            public final void ZD(C0Z adapter, View adapterView) {
                if (adapter != this.B.H) {
                    return;
                }
                this.B.C().removeCallbacks(runnable);
                C0O c0o = this.B.I;
                this.B.I = adapter;
                this.B.F = adapterView;
                if (this.B.D) {
                    this.B.G.C(adapterView);
                    this.B.A(c0o);
                } else {
                    super/*com.facebook.ads.redexgen.X.28*/.I();
                    this.B.G.B(adapter);
                }
            }

            @Override // com.facebook.ads.redexgen.X.C0U
            public final void aD(C0Z adapter, AdError error) {
                if (adapter != this.B.H) {
                    return;
                }
                this.B.C().removeCallbacks(runnable);
                this.B.A(adapter);
                this.B.H();
            }

            @Override // com.facebook.ads.redexgen.X.C0U
            public final void bD(C0Z c0z2) {
                C0642Kd.B("onBannerLoggingImpression", "Banner impression fired");
                this.B.G.I();
            }
        }, map);
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
        if (this.F != null) {
            this.G.C(this.F);
        }
    }
}
