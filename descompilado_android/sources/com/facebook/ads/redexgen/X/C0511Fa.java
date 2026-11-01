package com.facebook.ads.redexgen.X;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0511Fa extends Animation {
    public final /* synthetic */ C0513Fc B;
    public final /* synthetic */ int C;
    public final /* synthetic */ int D;

    public C0511Fa(C0513Fc this$0, int i, int i2) {
        this.B = this$0;
        this.D = i;
        this.C = i2;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float interpolatedTime, Transformation t) {
        int i = (int) (this.D + ((this.C - this.D) * interpolatedTime));
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
