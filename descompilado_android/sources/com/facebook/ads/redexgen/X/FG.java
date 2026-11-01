package com.facebook.ads.redexgen.X;

import android.animation.Animator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FG extends FF {
    public final /* synthetic */ FP B;
    public final /* synthetic */ AbstractC0502Er C;
    public final /* synthetic */ int D;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FG(FP this$0, AbstractC0502Er viewHolder, int animationType, int actionState, float startDx, float startDy, float targetX, float targetY, int i, AbstractC0502Er abstractC0502Er) {
        super(viewHolder, animationType, actionState, startDx, startDy, targetX, targetY);
        this.B = this$0;
        this.D = i;
        this.C = abstractC0502Er;
    }

    @Override // com.facebook.ads.redexgen.X.FF, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        if (this.F) {
            return;
        }
        if (this.D <= 0) {
            this.B.D.C(this.B.P, this.C);
        } else {
            this.B.N.add(this.C.B);
            this.E = true;
            if (this.D > 0) {
                this.B.G(this, this.D);
            }
        }
        if (this.B.L != this.C.B) {
            return;
        }
        this.B.F(this.C.B);
    }
}
