package com.facebook.ads.redexgen.X;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FX extends Animation {
    public final /* synthetic */ C0513Fc B;
    public final /* synthetic */ int C;
    public final /* synthetic */ int D;

    public FX(C0513Fc this$0, int i, int i2) {
        this.B = this$0;
        this.C = i;
        this.D = i2;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float interpolatedTime, Transformation t) {
        int i = (int) (this.C + ((this.D - this.C) * interpolatedTime));
        this.B.F.getLayoutParams().width = i;
        this.B.F.requestLayout();
        this.B.D.getLayoutParams().width = i - this.C;
        this.B.D.requestLayout();
    }

    @Override // android.view.animation.Animation
    public final boolean willChangeBounds() {
        return true;
    }
}
