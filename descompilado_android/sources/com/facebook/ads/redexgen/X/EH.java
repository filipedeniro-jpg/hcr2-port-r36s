package com.facebook.ads.redexgen.X;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class EH extends EA {

    @Nullable
    private ED B;

    @Nullable
    private ED C;

    private int B(@NonNull AbstractC0481Dw layoutManager, @NonNull View targetView, ED helper) {
        int iE;
        int iD = helper.D(targetView) + (helper.B(targetView) / 2);
        if (layoutManager.c()) {
            iE = helper.J() + (helper.K() / 2);
        } else {
            iE = helper.E() / 2;
        }
        return iD - iE;
    }

    @Nullable
    private View C(AbstractC0481Dw layoutManager, ED helper) {
        int i;
        int iB = layoutManager.b();
        if (iB == 0) {
            return null;
        }
        View view = null;
        if (layoutManager.c()) {
            int iJ = helper.J();
            int childCount = helper.K();
            i = iJ + (childCount / 2);
        } else {
            int i2 = helper.E();
            i = i2 / 2;
        }
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < iB; i4++) {
            View viewA = layoutManager.a(i4);
            int iD = helper.D(viewA);
            int i5 = helper.B(viewA);
            int i6 = Math.abs((iD + (i5 / 2)) - i);
            if (i6 < i3) {
                i3 = i6;
                view = viewA;
            }
        }
        return view;
    }

    @Override // com.facebook.ads.redexgen.X.EA
    @Nullable
    public final int[] C(@NonNull AbstractC0481Dw layoutManager, @NonNull View targetView) {
        int[] iArr = new int[2];
        if (layoutManager.G()) {
            iArr[0] = B(layoutManager, targetView, E(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.H()) {
            iArr[1] = B(layoutManager, targetView, F(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Nullable
    private View D(AbstractC0481Dw layoutManager, ED helper) {
        int iB = layoutManager.b();
        if (iB == 0) {
            return null;
        }
        View view = null;
        int startest = Integer.MAX_VALUE;
        for (int i = 0; i < iB; i++) {
            View viewA = layoutManager.a(i);
            int iD = helper.D(viewA);
            if (iD < startest) {
                startest = iD;
                view = viewA;
            }
        }
        return view;
    }

    @NonNull
    private ED E(@NonNull AbstractC0481Dw layoutManager) {
        if (this.B == null || this.B.B != layoutManager) {
            this.B = ED.B(layoutManager);
        }
        return this.B;
    }

    @Override // com.facebook.ads.redexgen.X.EA
    /* JADX INFO: renamed from: F, reason: collision with other method in class */
    public final E8 mo246F(AbstractC0481Dw layoutManager) {
        if (layoutManager instanceof InterfaceC0483Dy) {
            return new EG(this, super.B.getContext());
        }
        return null;
    }

    @NonNull
    private ED F(@NonNull AbstractC0481Dw layoutManager) {
        if (this.C == null || this.C.B != layoutManager) {
            this.C = ED.D(layoutManager);
        }
        return this.C;
    }

    @Override // com.facebook.ads.redexgen.X.EA
    @Nullable
    public final View G(AbstractC0481Dw layoutManager) {
        if (layoutManager.H()) {
            return C(layoutManager, F(layoutManager));
        }
        if (layoutManager.G()) {
            return C(layoutManager, E(layoutManager));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.ads.redexgen.X.EA
    public final int H(AbstractC0481Dw abstractC0481Dw, int velocityX, int velocityY) {
        int iW;
        boolean z;
        PointF pointFJB;
        int iO = abstractC0481Dw.o();
        if (iO == 0) {
            return -1;
        }
        View viewD = null;
        if (abstractC0481Dw.H()) {
            viewD = D(abstractC0481Dw, F(abstractC0481Dw));
        } else if (abstractC0481Dw.G()) {
            viewD = D(abstractC0481Dw, E(abstractC0481Dw));
        }
        if (viewD != null && (iW = abstractC0481Dw.w(viewD)) != -1) {
            if (abstractC0481Dw.G()) {
                z = velocityX > 0;
            } else {
                z = velocityY > 0;
            }
            boolean z2 = false;
            if ((abstractC0481Dw instanceof InterfaceC0483Dy) && (pointFJB = ((InterfaceC0483Dy) abstractC0481Dw).jB(iO - 1)) != null) {
                z2 = pointFJB.x < 0.0f || pointFJB.y < 0.0f;
            }
            if (z2) {
                return z ? iW - 1 : iW;
            }
            return z ? iW + 1 : iW;
        }
        return -1;
    }
}
