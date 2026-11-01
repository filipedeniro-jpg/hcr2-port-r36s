package com.facebook.ads.redexgen.X;

import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AdError;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class G2 implements AnonymousClass11 {
    public final /* synthetic */ G3 B;

    public G2(G3 this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass11
    public final void JE(AbstractC01310k adapter) {
        if (this.B.C == null) {
            return;
        }
        this.B.C.onAdClicked(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass11
    public final void KE(AbstractC01310k adapter) {
        this.B.D = true;
        if (this.B.C == null) {
            return;
        }
        this.B.C.onAdLoaded(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass11
    public final void LE(AbstractC01310k adapter) {
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass11
    public final void ME(AbstractC01310k adapter) {
        if (this.B.C == null) {
            return;
        }
        this.B.C.onAdVideoComplete(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass11
    public final void NE(AbstractC01310k adapter, View view) {
        if (view == null) {
            throw new IllegalStateException("Cannot present null view");
        }
        this.B.N = view;
        this.B.J.removeAllViews();
        this.B.N.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.B.J.addView(this.B.N);
        this.B.M(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass11
    public final void OE(AbstractC01310k adapter, AdError error) {
        if (this.B.C == null) {
            return;
        }
        this.B.C.onError(this.B.J, error);
    }
}
