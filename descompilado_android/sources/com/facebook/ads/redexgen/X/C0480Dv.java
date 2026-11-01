package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0480Dv extends E1 implements E0 {
    private int B;
    private int C;
    private int D;
    private int E;
    private final EI F;
    private EB G;
    private boolean H;

    public C0480Dv(Context context) {
        super(context);
        this.E = -1;
        this.D = -1;
        this.C = 0;
        this.B = 0;
        this.H = false;
        this.F = new EI(context, new C03368h(), new C03378i());
        D();
    }

    public C0480Dv(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.E = -1;
        this.D = -1;
        this.C = 0;
        this.B = 0;
        this.H = false;
        this.F = new EI(context, new C03368h(), new C03378i());
        D();
    }

    public C0480Dv(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.E = -1;
        this.D = -1;
        this.C = 0;
        this.B = 0;
        this.H = false;
        this.F = new EI(context, new C03368h(), new C03378i());
        D();
    }

    private int B(int availableHeight) {
        int numItems = this.B * 2;
        int spacing = getMeasuredWidth();
        int spacing2 = (spacing - getPaddingLeft()) - numItems;
        int iC = getAdapter().C();
        int i = 0;
        int numFullItems = Integer.MAX_VALUE;
        while (numFullItems > availableHeight) {
            i++;
            if (i >= iC) {
                return availableHeight;
            }
            int numFullItems2 = i * numItems;
            numFullItems = (int) ((spacing2 - numFullItems2) / (i + 0.333f));
        }
        int itemSize = numFullItems;
        return itemSize;
    }

    private void C(int position, int offset) {
        if (position == this.E && offset == this.D) {
            return;
        }
        this.E = position;
        this.D = offset;
        if (this.G == null) {
            return;
        }
        this.G.onPageChanged(this.E, this.D);
    }

    private void D() {
        this.F.NB(0);
        setLayoutManager(this.F);
        setSaveEnabled(false);
        setSnapDelegate(this);
        J6.U(this);
    }

    @Override // com.facebook.ads.redexgen.X.E1
    public final void FA(int position, boolean isAnimation) {
        super.FA(position, isAnimation);
        C(position, 0);
    }

    @Override // com.facebook.ads.redexgen.X.E0
    public final int IC(int scrollX) {
        int iAbs = Math.abs(scrollX);
        if (iAbs <= ((E1) this).C) {
            return 0;
        }
        if (this.C == 0) {
            return 1;
        }
        return (iAbs / this.C) + 1;
    }

    public int getChildSpacing() {
        return this.B;
    }

    @Override // com.facebook.ads.redexgen.X.C0503Es, android.view.View
    public final void onMeasure(int widthSpec, int heightSpec) {
        int iRound;
        int iB;
        super.onMeasure(widthSpec, heightSpec);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.H) {
            iRound = (((int) J6.B) * JA.b(getContext())) + paddingTop;
        } else {
            iRound = Math.round(getMeasuredWidth() / 1.91f);
        }
        switch (View.MeasureSpec.getMode(heightSpec)) {
            case Integer.MIN_VALUE:
                iRound = Math.min(View.MeasureSpec.getSize(heightSpec), iRound);
                break;
            case 1073741824:
                iRound = View.MeasureSpec.getSize(heightSpec);
                break;
        }
        int i = iRound - paddingTop;
        if (this.H) {
            iB = Math.min(C0564Hc.Q, i);
        } else {
            iB = B(i);
        }
        setMeasuredDimension(getMeasuredWidth(), iB + paddingTop);
        if (!this.H) {
            setChildWidth((this.B * 2) + iB);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0503Es
    public void setAdapter(@Nullable C1G adapter) {
        this.F.OB(adapter == null ? -1 : adapter.hashCode());
        super.setAdapter(adapter);
    }

    public void setChildSpacing(int spacing) {
        this.B = spacing;
    }

    public void setChildWidth(int width) {
        this.C = width;
        int measuredWidth = getMeasuredWidth();
        this.F.QB((((measuredWidth - getPaddingLeft()) - getPaddingRight()) - this.C) / 2);
        this.F.PB(((double) this.C) / ((double) measuredWidth));
    }

    public void setCurrentPosition(int position) {
        FA(position, false);
    }

    public void setOnPageChangedListener(EB listener) {
        this.G = listener;
    }

    public void setShowTextInCarousel(boolean showTextInCarousel) {
        this.H = showTextInCarousel;
    }
}
