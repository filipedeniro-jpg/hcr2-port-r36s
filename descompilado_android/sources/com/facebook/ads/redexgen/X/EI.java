package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class EI extends C0484Dz {
    private int B;
    private final Context C;
    private final C03378i D;
    private float E;
    private int[] F;
    private final C03368h G;
    private int H;
    private EJ I;

    public EI(Context mContext, C03368h mMeasurer, C03378i mDimensionCache) {
        super(mContext);
        this.H = 0;
        this.E = 50.0f;
        this.C = mContext;
        this.G = mMeasurer;
        this.D = mDimensionCache;
        this.B = -1;
        this.I = new EJ(this, this.C);
    }

    @Override // com.facebook.ads.redexgen.X.C0484Dz, com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void FA(C0503Es recyclerView, C0499Eo state, int position) {
        this.I.L(position);
        CA(this.I);
    }

    public final void OB(int mAdapterId) {
        this.B = mAdapterId;
    }

    public final void PB(double childWidthRatio) {
        if (childWidthRatio <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            childWidthRatio = 1.0d;
        }
        this.E = (float) (50.0d / childWidthRatio);
        this.I = new EJ(this, this.C);
    }

    public final void QB(int mScrollOffset) {
        this.H = mScrollOffset;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void aA(C0491Eg recycler, C0499Eo state, int widthSpec, int heightSpec) {
        int[] iArrB;
        int mode = View.MeasureSpec.getMode(widthSpec);
        int widthMode = View.MeasureSpec.getMode(heightSpec);
        if ((mode == 1073741824 && IB() == 1) || (widthMode == 1073741824 && IB() == 0)) {
            super.aA(recycler, state, widthSpec, heightSpec);
            return;
        }
        int size = View.MeasureSpec.getSize(widthSpec);
        int size2 = View.MeasureSpec.getSize(heightSpec);
        C03378i c03378i = this.D;
        int widthSize = this.B;
        if (c03378i.A(widthSize)) {
            C03378i c03378i2 = this.D;
            int widthSize2 = this.B;
            iArrB = c03378i2.B(widthSize2);
        } else {
            iArrB = new int[]{0, 0};
            if (state.B() >= 1) {
                int widthSize3 = b();
                int iB = widthSize3 > 0 ? 1 : b();
                for (int i = 0; i < iB; i++) {
                    View viewV = V(i);
                    C03368h c03368h = this.G;
                    int i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
                    int widthSize4 = View.MeasureSpec.makeMeasureSpec(0, 0);
                    this.F = c03368h.A(viewV, i2, widthSize4);
                    int widthSize5 = IB();
                    if (widthSize5 == 0) {
                        int i3 = iArrB[0];
                        int widthSize6 = this.F[0];
                        iArrB[0] = i3 + widthSize6;
                        if (i == 0) {
                            int i4 = this.F[1];
                            int widthSize7 = v();
                            int i5 = i4 + widthSize7;
                            int widthSize8 = s();
                            iArrB[1] = i5 + widthSize8;
                        }
                    } else {
                        int i6 = iArrB[1];
                        int widthSize9 = this.F[1];
                        iArrB[1] = i6 + widthSize9;
                        if (i == 0) {
                            int i7 = this.F[0];
                            int widthSize10 = t();
                            int i8 = i7 + widthSize10;
                            int widthSize11 = u();
                            iArrB[0] = i8 + widthSize11;
                        }
                    }
                }
                if (this.B != -1) {
                    C03378i c03378i3 = this.D;
                    int widthSize12 = this.B;
                    c03378i3.C(widthSize12, iArrB);
                }
            }
        }
        if (mode == 1073741824) {
            iArrB[0] = size;
        }
        if (widthMode == 1073741824) {
            iArrB[1] = size2;
        }
        int i9 = iArrB[0];
        int widthSize13 = iArrB[1];
        wA(i9, widthSize13);
    }

    @Override // com.facebook.ads.redexgen.X.C0484Dz, com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void rA(int position) {
        super.MB(position, this.H);
    }
}
