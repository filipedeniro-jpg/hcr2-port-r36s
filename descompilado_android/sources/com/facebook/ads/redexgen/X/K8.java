package com.facebook.ads.redexgen.X;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.facebook.ads.internal.api.AdNativeComponentView;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class K8 extends C1B {
    public final /* synthetic */ KF B;
    public final /* synthetic */ boolean C;
    public final /* synthetic */ View D;

    public K8(KF this$0, View view, boolean z) {
        this.B = this$0;
        this.D = view;
        this.C = z;
    }

    @Override // com.facebook.ads.redexgen.X.C1B
    public final void B() {
        Drawable background;
        if (JA.NB(this.B.H()) && (this.D instanceof AdNativeComponentView)) {
            View adContentsView = ((AdNativeComponentView) this.D).getAdContentsView();
            if ((adContentsView instanceof C7C) && !((C7C) adContentsView).B(1)) {
                return;
            }
        }
        if (this.C && ((background = this.D.getBackground()) == null || !background.equals(this.B.T))) {
            return;
        }
        this.B.k.G();
        if (this.B.j != null && this.B.j.get() != null) {
            ((C1B) this.B.j.get()).B();
        }
        if (this.B.f.D()) {
            return;
        }
        this.B.f.G();
        if (this.B.X == null || this.B.G == null || this.B.c == null) {
            return;
        }
        this.B.X.N(this.B.G);
        this.B.X.I(this.B.c);
        this.B.X.J(this.B.a);
        this.B.X.E(this.B.O);
        this.B.X.M(this.B.R);
        this.B.X.L(this.B.Q);
        this.B.X.G(this.B.k());
        this.B.X.F(this.B.V);
        this.B.X.K(this.B.P);
        this.B.X.H(C0431By.D(this.B.Y));
        this.B.X.D(this.B.N);
        this.B.X.A();
    }
}
