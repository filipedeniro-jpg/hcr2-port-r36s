package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.view.View;
import com.facebook.ads.AdView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0519Fi extends C0P {
    public final /* synthetic */ C0520Fj B;
    public final /* synthetic */ AdView C;
    public final /* synthetic */ String D;

    public C0519Fi(C0520Fj this$0, AdView adView, String str) {
        this.B = this$0;
        this.C = adView;
        this.D = str;
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void A() {
        if (this.B.B == null) {
            return;
        }
        this.B.B.onAdClicked(this.C);
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void B(C0O adAdapter) {
        if (this.B.E == null) {
            return;
        }
        this.B.E.N();
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void C(View view) {
        if (view == null) {
            throw new IllegalStateException("Cannot present null adView");
        }
        this.B.L = view;
        this.B.D.removeAllViews();
        this.B.D.addView(this.B.L);
        if (this.B.L instanceof BB) {
            KY.E(this.B.I, this.B.L, this.B.C);
        }
        if (this.B.B != null) {
            this.B.B.onAdLoaded(this.C);
        }
        this.B.M(this.C, this.B.L);
        if (Build.VERSION.SDK_INT >= 18 && JA.s(this.B.D.getContext())) {
            this.B.F = new B8();
            this.B.F.F(this.D);
            this.B.F.D(this.B.D.getContext().getPackageName());
            if (this.B.E != null && this.B.E.D() != null) {
                this.B.F.G(this.B.E.D().G());
            }
            if (this.B.L instanceof BB) {
                this.B.F.H(((BB) this.B.L).getViewabilityChecker());
            }
            this.B.L.setOnLongClickListener(new ViewOnLongClickListenerC0518Fh(this));
            this.B.L.getOverlay().add(this.B.F);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void D(KQ error) {
        if (this.B.B == null) {
            return;
        }
        this.B.B.onError(this.C, KQ.E(error));
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void I() {
        if (this.B.B == null) {
            return;
        }
        this.B.B.onLoggingImpression(this.C);
    }
}
