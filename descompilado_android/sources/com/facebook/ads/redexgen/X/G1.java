package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class G1 extends C0P {
    public final /* synthetic */ G3 B;

    public G1(G3 this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void A() {
        if (this.B.C == null) {
            return;
        }
        this.B.C.onAdClicked(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void B(C0O adAdapter) {
        if (this.B.H == null) {
            return;
        }
        this.B.D = true;
        if (this.B.C == null) {
            return;
        }
        this.B.C.onAdLoaded(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void C(View view) {
        if (view == null) {
            throw new IllegalStateException("Cannot present null view");
        }
        this.B.N = view;
        this.B.J.removeAllViews();
        this.B.N.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.B.J.addView(this.B.N);
        this.B.M(this.B.J);
        if (Build.VERSION.SDK_INT >= 18 && JA.s(this.B.G)) {
            this.B.B = new B8();
            this.B.B.F(this.B.L);
            this.B.B.D(this.B.G.getPackageName());
            if (this.B.H != null && this.B.H.D() != null) {
                this.B.B.G(this.B.H.D().G());
            }
            this.B.N.getOverlay().add(this.B.B);
            this.B.N.setOnLongClickListener(new G0(this));
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void D(KQ error) {
        if (this.B.C == null) {
            return;
        }
        this.B.C.onError(this.B.J, KQ.E(error));
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void E() {
        if (this.B.C == null) {
            return;
        }
        this.B.C.onAdVideoComplete(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void I() {
        if (this.B.C == null) {
            return;
        }
        this.B.C.onLoggingImpression(this.B.J);
    }
}
