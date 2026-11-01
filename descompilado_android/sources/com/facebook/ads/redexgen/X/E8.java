package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class E8 extends E7 {
    public PointF F;
    private final float G;
    public final LinearInterpolator E = new LinearInterpolator();
    public final DecelerateInterpolator B = new DecelerateInterpolator();
    public int C = 0;
    public int D = 0;

    public E8(Context context) {
        this.G = Q(context.getResources().getDisplayMetrics());
    }

    private final int C(View view, int snapPreference) {
        AbstractC0481Dw abstractC0481DwB = B();
        if (abstractC0481DwB == null || !abstractC0481DwB.H()) {
            return 0;
        }
        C0479Du params = (C0479Du) view.getLayoutParams();
        int end = O(abstractC0481DwB.k(view) - params.topMargin, abstractC0481DwB.e(view) + params.bottomMargin, abstractC0481DwB.v(), abstractC0481DwB.m() - abstractC0481DwB.s(), snapPreference);
        return end;
    }

    private int D(int tmpDt, int dt) {
        int i = tmpDt - dt;
        if (tmpDt * i <= 0) {
            return 0;
        }
        return i;
    }

    private final int E() {
        if (this.F == null || this.F.y == 0.0f) {
            return 0;
        }
        return this.F.y > 0.0f ? 1 : -1;
    }

    private final void F(C0497Em action) {
        PointF pointFT = T(C());
        if (pointFT == null || (pointFT.x == 0.0f && pointFT.y == 0.0f)) {
            action.B(C());
            N();
            return;
        }
        F(pointFT);
        this.F = pointFT;
        this.C = (int) (pointFT.x * 10000.0f);
        this.D = (int) (pointFT.y * 10000.0f);
        int iS = S(10000);
        int time = (int) (this.C * 1.2f);
        action.D(time, (int) (this.D * 1.2f), (int) (iS * 1.2f), this.E);
    }

    @Override // com.facebook.ads.redexgen.X.E7
    public final void H(int dx, int dy, C0499Eo state, C0497Em action) {
        if (A() == 0) {
            N();
            return;
        }
        this.C = D(this.C, dx);
        this.D = D(this.D, dy);
        if (this.C != 0 || this.D != 0) {
            return;
        }
        F(action);
    }

    @Override // com.facebook.ads.redexgen.X.E7
    public final void I() {
    }

    @Override // com.facebook.ads.redexgen.X.E7
    public final void J() {
        this.D = 0;
        this.C = 0;
        this.F = null;
    }

    @Override // com.facebook.ads.redexgen.X.E7
    public void K(View targetView, C0499Eo state, C0497Em action) {
        int iP = P(targetView, U());
        int iC = C(targetView, E());
        int iR = R((int) Math.sqrt((iP * iP) + (iC * iC)));
        if (iR > 0) {
            action.D(-iP, -iC, iR, this.B);
        }
    }

    public final int O(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        switch (snapPreference) {
            case -1:
                return boxStart - viewStart;
            case 0:
                int boxStart2 = boxStart - viewStart;
                if (boxStart2 <= 0) {
                    int boxEnd2 = boxEnd - viewEnd;
                    if (boxEnd2 < 0) {
                        return boxEnd2;
                    }
                    return 0;
                }
                return boxStart2;
            case 1:
                return boxEnd - viewEnd;
            default:
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }

    public int P(View view, int snapPreference) {
        AbstractC0481Dw abstractC0481DwB = B();
        if (abstractC0481DwB == null || !abstractC0481DwB.G()) {
            return 0;
        }
        C0479Du params = (C0479Du) view.getLayoutParams();
        int end = O(abstractC0481DwB.g(view) - params.leftMargin, abstractC0481DwB.j(view) + params.rightMargin, abstractC0481DwB.t(), abstractC0481DwB.z() - abstractC0481DwB.u(), snapPreference);
        return end;
    }

    public float Q(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    public final int R(int dx) {
        return (int) Math.ceil(((double) S(dx)) / 0.3356d);
    }

    public int S(int dx) {
        return (int) Math.ceil(Math.abs(dx) * this.G);
    }

    @Nullable
    public PointF T(int targetPosition) {
        Object objB = B();
        if (objB instanceof InterfaceC0483Dy) {
            return ((InterfaceC0483Dy) objB).jB(targetPosition);
        }
        Log.w("LinearSmoothScroller", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + InterfaceC0483Dy.class.getCanonicalName());
        return null;
    }

    public int U() {
        if (this.F == null || this.F.x == 0.0f) {
            return 0;
        }
        return this.F.x > 0.0f ? 1 : -1;
    }
}
