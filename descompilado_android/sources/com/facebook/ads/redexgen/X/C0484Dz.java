package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.tonyodev.fetch.FetchService;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0484Dz extends AbstractC0481Dw implements InterfaceC0482Dx, InterfaceC0483Dy {
    public final E2 B;
    public int C;
    public ED D;
    public E6 E;
    public int F;
    public int G;
    public boolean H;
    private int I;
    private boolean J;
    private final E3 K;
    private E4 L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;

    public C0484Dz(Context context) {
        this(context, 1, false);
    }

    public C0484Dz(Context context, int orientation, boolean reverseLayout) {
        this.N = false;
        this.H = false;
        this.P = false;
        this.O = true;
        this.F = -1;
        this.G = Integer.MIN_VALUE;
        this.E = null;
        this.B = new E2(this);
        this.K = new E3();
        this.I = 2;
        NB(orientation);
        q(reverseLayout);
        tA(true);
    }

    public void BB(C0499Eo state, E4 layoutState, InterfaceC0473Do layoutPrefetchRegistry) {
        int i = layoutState.C;
        if (i >= 0 && i < state.B()) {
            layoutPrefetchRegistry.TB(i, Math.max(0, layoutState.M));
        }
    }

    public final int CB(int focusDirection) {
        switch (focusDirection) {
            case 1:
                return (this.C == 1 || !JB()) ? -1 : 1;
            case 2:
                return (this.C != 1 && JB()) ? -1 : 1;
            case 17:
                return this.C != 0 ? Integer.MIN_VALUE : -1;
            case 33:
                return this.C != 1 ? Integer.MIN_VALUE : -1;
            case 66:
                return this.C != 0 ? Integer.MIN_VALUE : 1;
            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                return this.C == 1 ? 1 : Integer.MIN_VALUE;
            default:
                return Integer.MIN_VALUE;
        }
    }

    public final void DB() {
        if (this.L == null) {
            this.L = I();
        }
        if (this.D == null) {
            this.D = ED.C(this, this.C);
        }
    }

    private void E(E2 anchorInfo) {
        O(anchorInfo.D, anchorInfo.B);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void E(String message) {
        if (this.E == null) {
            super.E(message);
        }
    }

    public final int EB() {
        View viewS = S(0, b(), true, false);
        if (viewS == null) {
            return -1;
        }
        return w(viewS);
    }

    private int F(C0499Eo state) {
        if (b() == 0) {
            return 0;
        }
        DB();
        return C0506Ev.B(state, this.D, N(!this.O, true), M(this.O ? false : true, true), this, this.O);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public boolean F() {
        return this.E == null && this.J == this.P;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public void FA(C0503Es recyclerView, C0499Eo state, int position) {
        E8 e8 = new E8(recyclerView.getContext());
        e8.L(position);
        CA(e8);
    }

    public final int FB() {
        View viewS = S(0, b(), false, true);
        if (viewS == null) {
            return -1;
        }
        return w(viewS);
    }

    private int G(C0499Eo state) {
        if (b() == 0) {
            return 0;
        }
        DB();
        return C0506Ev.C(state, this.D, N(!this.O, true), M(this.O ? false : true, true), this, this.O, this.H);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final boolean G() {
        return this.C == 0;
    }

    public final int GB() {
        View viewS = S(b() - 1, -1, false, true);
        if (viewS == null) {
            return -1;
        }
        return w(viewS);
    }

    private int H(C0499Eo state) {
        if (b() == 0) {
            return 0;
        }
        DB();
        return C0506Ev.D(state, this.D, N(!this.O, true), M(this.O ? false : true, true), this, this.O);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final boolean H() {
        return this.C == 1;
    }

    public View HB(C0491Eg recycler, C0499Eo state, int start, int end, int itemCount) {
        DB();
        View view = null;
        View view2 = null;
        int iJ = this.D.J();
        int iF = this.D.F();
        int i = end > start ? 1 : -1;
        while (start != end) {
            View viewA = a(start);
            int iW = w(viewA);
            if (iW >= 0 && iW < itemCount) {
                if (((C0479Du) viewA.getLayoutParams()).C()) {
                    if (view == null) {
                        view = viewA;
                    }
                } else {
                    if (this.D.D(viewA) < iF && this.D.A(viewA) >= iJ) {
                        return viewA;
                    }
                    if (view2 == null) {
                        view2 = viewA;
                    }
                }
            }
            start += i;
        }
        if (view2 == null) {
            view2 = view;
        }
        return view2;
    }

    private final E4 I() {
        return new E4();
    }

    public final int IB() {
        return this.C;
    }

    private final int J(C0491Eg recycler, E4 layoutState, C0499Eo state, boolean stopOnFocusable) {
        int i = layoutState.B;
        if (layoutState.M != Integer.MIN_VALUE) {
            if (layoutState.B < 0) {
                layoutState.M += layoutState.B;
            }
            j(recycler, layoutState);
        }
        int i2 = layoutState.B + layoutState.D;
        E3 e3 = this.K;
        while (true) {
            if ((!layoutState.E && i2 <= 0) || !layoutState.B(state)) {
                break;
            }
            e3.A();
            KB(recycler, state, layoutState, e3);
            if (!e3.C) {
                layoutState.J += e3.B * layoutState.I;
                if (!e3.E || this.L.L != null || !state.E()) {
                    layoutState.B -= e3.B;
                    i2 -= e3.B;
                }
                if (layoutState.M != Integer.MIN_VALUE) {
                    layoutState.M += e3.B;
                    if (layoutState.B < 0) {
                        layoutState.M += layoutState.B;
                    }
                    j(recycler, layoutState);
                }
                if (stopOnFocusable && e3.D) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - layoutState.B;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void J(int delta, int dy, C0499Eo state, InterfaceC0473Do layoutPrefetchRegistry) {
        if (this.C != 0) {
            delta = dy;
        }
        if (b() == 0 || delta == 0) {
            return;
        }
        DB();
        e(delta > 0 ? 1 : -1, Math.abs(delta), true, state);
        BB(state, this.L, layoutPrefetchRegistry);
    }

    public final boolean JB() {
        return p() == 1;
    }

    private View K(C0491Eg recycler, C0499Eo state) {
        return R(0, b());
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void K(int adapterItemCount, InterfaceC0473Do layoutPrefetchRegistry) {
        boolean z;
        int i;
        if (this.E != null && this.E.A()) {
            z = this.E.B;
            i = this.E.D;
        } else {
            o();
            z = this.H;
            if (this.F == -1) {
                i = z ? adapterItemCount - 1 : 0;
            } else {
                i = this.F;
            }
        }
        int targetPos = z ? -1 : 1;
        for (int i2 = 0; i2 < this.I && i >= 0 && i < adapterItemCount; i2++) {
            layoutPrefetchRegistry.TB(i, 0);
            i += targetPos;
        }
    }

    public void KB(C0491Eg recycler, C0499Eo state, E4 layoutState, E3 result) {
        int iV;
        int iC;
        int iT;
        int iC2;
        View viewC = layoutState.C(recycler);
        if (viewC == null) {
            result.C = true;
            return;
        }
        C0479Du c0479Du = (C0479Du) viewC.getLayoutParams();
        if (layoutState.L == null) {
            if (this.H == (layoutState.I == -1)) {
                C(viewC);
            } else {
                D(viewC, 0);
            }
        } else {
            if (this.H == (layoutState.I == -1)) {
                A(viewC);
            } else {
                B(viewC, 0);
            }
        }
        HA(viewC, 0, 0);
        result.B = this.D.B(viewC);
        if (this.C == 1) {
            if (JB()) {
                iC2 = z() - u();
                iT = iC2 - this.D.C(viewC);
            } else {
                iT = t();
                iC2 = iT + this.D.C(viewC);
            }
            if (layoutState.I == -1) {
                iC = layoutState.J;
                iV = layoutState.J - result.B;
            } else {
                iV = layoutState.J;
                iC = layoutState.J + result.B;
            }
        } else {
            iV = v();
            iC = iV + this.D.C(viewC);
            if (layoutState.I == -1) {
                iC2 = layoutState.J;
                iT = layoutState.J - result.B;
            } else {
                iT = layoutState.J;
                iC2 = layoutState.J + result.B;
            }
        }
        GA(viewC, iT, iV, iC2, iC);
        if (c0479Du.C() || c0479Du.B()) {
            result.E = true;
        }
        result.D = viewC.hasFocusable();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final int L(C0499Eo state) {
        return F(state);
    }

    private View L(C0491Eg recycler, C0499Eo state) {
        return HB(recycler, state, 0, b(), state.B());
    }

    public void LB(C0491Eg recycler, C0499Eo state, E2 anchorInfo, int firstLayoutItemDirection) {
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final int M(C0499Eo state) {
        return G(state);
    }

    private View M(boolean completelyVisible, boolean acceptPartiallyVisible) {
        return this.H ? S(0, b(), completelyVisible, acceptPartiallyVisible) : S(b() - 1, -1, completelyVisible, acceptPartiallyVisible);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void MA(C0503Es view, C0491Eg recycler) {
        super.MA(view, recycler);
        if (this.M) {
            hA(recycler);
            recycler.B();
        }
    }

    public final void MB(int position, int offset) {
        this.F = position;
        this.G = offset;
        if (this.E != null) {
            this.E.B();
        }
        oA();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final int N(C0499Eo state) {
        return H(state);
    }

    private View N(boolean completelyVisible, boolean acceptPartiallyVisible) {
        return this.H ? S(b() - 1, -1, completelyVisible, acceptPartiallyVisible) : S(0, b(), completelyVisible, acceptPartiallyVisible);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public View NA(View focused, int focusDirection, C0491Eg recycler, C0499Eo state) {
        int iCB;
        View viewT;
        View viewZ;
        o();
        if (b() != 0 && (iCB = CB(focusDirection)) != Integer.MIN_VALUE) {
            DB();
            DB();
            e(iCB, (int) (0.33333334f * this.D.K()), false, state);
            this.L.M = Integer.MIN_VALUE;
            this.L.K = false;
            J(recycler, this.L, state, true);
            if (iCB == -1) {
                viewT = U(recycler, state);
            } else {
                viewT = T(recycler, state);
            }
            if (iCB == -1) {
                viewZ = a();
            } else {
                viewZ = Z();
            }
            if (viewZ.hasFocusable()) {
                if (viewT == null) {
                    return null;
                }
                return viewZ;
            }
            return viewT;
        }
        return null;
    }

    public final void NB(int orientation) {
        if (orientation != 0 && orientation != 1) {
            throw new IllegalArgumentException("invalid orientation:" + orientation);
        }
        E((String) null);
        if (orientation == this.C) {
            return;
        }
        this.C = orientation;
        this.D = null;
        oA();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final int O(C0499Eo state) {
        return F(state);
    }

    private void O(int itemPosition, int offset) {
        this.L.B = offset - this.D.J();
        this.L.C = itemPosition;
        this.L.G = this.H ? 1 : -1;
        this.L.I = -1;
        this.L.J = offset;
        this.L.M = Integer.MIN_VALUE;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void OA(AccessibilityEvent event) {
        super.OA(event);
        if (b() > 0) {
            event.setFromIndex(FB());
            event.setToIndex(GB());
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final int P(C0499Eo state) {
        return G(state);
    }

    private View P(C0491Eg recycler, C0499Eo state) {
        return R(b() - 1, -1);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final int Q(C0499Eo state) {
        return H(state);
    }

    private View Q(C0491Eg recycler, C0499Eo state) {
        return HB(recycler, state, b() - 1, -1, state.B());
    }

    private final View R(int fromIndex, int toIndex) {
        byte b;
        int i;
        int i2;
        DB();
        if (toIndex > fromIndex) {
            b = 1;
        } else {
            b = toIndex < fromIndex ? (byte) -1 : (byte) 0;
        }
        if (b == 0) {
            return a(fromIndex);
        }
        if (this.D.D(a(fromIndex)) < this.D.J()) {
            i = 16644;
            i2 = 16388;
        } else {
            i = 4161;
            i2 = 4097;
        }
        if (this.C == 0) {
            return super.D.A(fromIndex, toIndex, i, i2);
        }
        return super.K.A(fromIndex, toIndex, i, i2);
    }

    private final View S(int fromIndex, int toIndex, boolean completelyVisible, boolean acceptPartiallyVisible) {
        int i;
        DB();
        int i2 = 0;
        if (completelyVisible) {
            i = 24579;
        } else {
            i = FetchService.ACTION_LOGGING;
        }
        if (acceptPartiallyVisible) {
            i2 = FetchService.ACTION_LOGGING;
        }
        if (this.C == 0) {
            return super.D.A(fromIndex, toIndex, i, i2);
        }
        return super.K.A(fromIndex, toIndex, i, i2);
    }

    private View T(C0491Eg recycler, C0499Eo state) {
        return this.H ? K(recycler, state) : P(recycler, state);
    }

    private View U(C0491Eg recycler, C0499Eo state) {
        return this.H ? P(recycler, state) : K(recycler, state);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final View V(int position) {
        int viewPosition = b();
        if (viewPosition == 0) {
            return null;
        }
        int iW = position - w(a(0));
        if (iW >= 0 && iW < viewPosition) {
            View viewA = a(iW);
            if (w(viewA) == position) {
                return viewA;
            }
        }
        return super.V(position);
    }

    private View V(C0491Eg recycler, C0499Eo state) {
        return this.H ? L(recycler, state) : Q(recycler, state);
    }

    private View W(C0491Eg recycler, C0499Eo state) {
        return this.H ? Q(recycler, state) : L(recycler, state);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public C0479Du W() {
        return new C0479Du(-2, -2);
    }

    private int X(int endOffset, C0491Eg recycler, C0499Eo state, boolean canOffsetChildren) {
        int iF;
        int iF2 = this.D.F() - endOffset;
        if (iF2 > 0) {
            int i = -p(-iF2, recycler, state);
            int gap = endOffset + i;
            if (canOffsetChildren && (iF = this.D.F() - gap) > 0) {
                this.D.O(iF);
                return iF + i;
            }
            return i;
        }
        return 0;
    }

    private int Y(int startOffset, C0491Eg recycler, C0499Eo state, boolean canOffsetChildren) {
        int gap;
        int iJ = startOffset - this.D.J();
        if (iJ > 0) {
            int i = -p(iJ, recycler, state);
            int gap2 = startOffset + i;
            if (canOffsetChildren && (gap = gap2 - this.D.J()) > 0) {
                this.D.O(-gap);
                return i - gap;
            }
            return i;
        }
        return 0;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public void YA(C0491Eg recycler, C0499Eo state) {
        int i;
        int i2;
        int i3;
        int i4;
        int current;
        if ((this.E != null || this.F != -1) && state.B() == 0) {
            hA(recycler);
            return;
        }
        if (this.E != null && this.E.A()) {
            this.F = this.E.D;
        }
        DB();
        this.L.K = false;
        o();
        View viewL = l();
        if (!this.B.E || this.F != -1 || this.E != null) {
            this.B.E();
            this.B.C = this.H ^ this.P;
            f(recycler, state, this.B);
            this.B.E = true;
        } else if (viewL != null && (this.D.D(viewL) >= this.D.F() || this.D.A(viewL) <= this.D.J())) {
            this.B.C(viewL);
        }
        int iB = b(state);
        if (this.L.H >= 0) {
            i = 0;
        } else {
            i = iB;
            iB = 0;
        }
        int extraForEnd = this.D.J();
        int i5 = i + extraForEnd;
        int extraForEnd2 = this.D.G();
        int i6 = iB + extraForEnd2;
        if (state.E()) {
            int extraForStart = this.F;
            if (extraForStart != -1) {
                int extraForStart2 = this.G;
                if (extraForStart2 != Integer.MIN_VALUE) {
                    int extraForStart3 = this.F;
                    View viewV = V(extraForStart3);
                    if (viewV != null) {
                        if (this.H) {
                            int extraForEnd3 = this.D.F();
                            int current2 = extraForEnd3 - this.D.A(viewV);
                            current = current2 - this.G;
                        } else {
                            int iD = this.D.D(viewV) - this.D.J();
                            int lastElement = this.G;
                            current = lastElement - iD;
                        }
                        if (current > 0) {
                            i5 += current;
                        } else {
                            i6 -= current;
                        }
                    }
                }
            }
        }
        if (this.B.C) {
            i2 = this.H ? 1 : -1;
        } else {
            i2 = this.H ? -1 : 1;
        }
        LB(recycler, state, this.B, i2);
        R(recycler);
        this.L.E = n();
        this.L.F = state.E();
        if (this.B.C) {
            E(this.B);
            this.L.D = i5;
            J(recycler, this.L, state, false);
            i4 = this.L.J;
            int i7 = this.L.C;
            int firstElement = this.L.B;
            if (firstElement > 0) {
                int firstElement2 = this.L.B;
                i6 += firstElement2;
            }
            c(this.B);
            this.L.D = i6;
            E4 e4 = this.L;
            int i8 = e4.C;
            int firstElement3 = this.L.G;
            e4.C = i8 + firstElement3;
            J(recycler, this.L, state, false);
            i3 = this.L.J;
            int endOffset = this.L.B;
            if (endOffset > 0) {
                int i9 = this.L.B;
                O(i7, i4);
                this.L.D = i9;
                J(recycler, this.L, state, false);
                i4 = this.L.J;
            }
        } else {
            c(this.B);
            this.L.D = i6;
            J(recycler, this.L, state, false);
            i3 = this.L.J;
            int i10 = this.L.C;
            int lastElement2 = this.L.B;
            if (lastElement2 > 0) {
                int lastElement3 = this.L.B;
                i5 += lastElement3;
            }
            E(this.B);
            this.L.D = i5;
            E4 e5 = this.L;
            int i11 = e5.C;
            int lastElement4 = this.L.G;
            e5.C = i11 + lastElement4;
            J(recycler, this.L, state, false);
            i4 = this.L.J;
            int lastElement5 = this.L.B;
            if (lastElement5 > 0) {
                int i12 = this.L.B;
                d(i10, i3);
                this.L.D = i12;
                J(recycler, this.L, state, false);
                i3 = this.L.J;
            }
        }
        int endOffset2 = b();
        if (endOffset2 > 0) {
            if (this.H ^ this.P) {
                int fixOffset = X(i3, recycler, state, true);
                int i13 = i4 + fixOffset;
                int i14 = i3 + fixOffset;
                int fixOffset2 = Y(i13, recycler, state, false);
                i4 = i13 + fixOffset2;
                i3 = i14 + fixOffset2;
            } else {
                int fixOffset3 = Y(i4, recycler, state, true);
                int i15 = i4 + fixOffset3;
                int i16 = i3 + fixOffset3;
                int fixOffset4 = X(i16, recycler, state, false);
                i4 = i15 + fixOffset4;
                i3 = i16 + fixOffset4;
            }
        }
        h(recycler, state, i4, i3);
        if (!state.E()) {
            this.D.I();
        } else {
            this.B.E();
        }
        this.J = this.P;
    }

    private View Z() {
        return a(this.H ? 0 : b() - 1);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public void ZA(C0499Eo state) {
        super.ZA(state);
        this.E = null;
        this.F = -1;
        this.G = Integer.MIN_VALUE;
        this.B.E();
    }

    private View a() {
        return a(this.H ? b() - 1 : 0);
    }

    private final int b(C0499Eo state) {
        if (state.D()) {
            return this.D.K();
        }
        return 0;
    }

    private void c(E2 anchorInfo) {
        d(anchorInfo.D, anchorInfo.B);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final void cA(Parcelable state) {
        if (state instanceof E6) {
            this.E = (E6) state;
            oA();
        }
    }

    private void d(int itemPosition, int offset) {
        this.L.B = this.D.F() - offset;
        this.L.G = this.H ? -1 : 1;
        this.L.C = itemPosition;
        this.L.I = 1;
        this.L.J = offset;
        this.L.M = Integer.MIN_VALUE;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final Parcelable dA() {
        if (this.E != null) {
            return new E6(this.E);
        }
        E6 state = new E6();
        if (b() > 0) {
            DB();
            boolean z = this.J ^ this.H;
            state.B = z;
            if (z) {
                View viewZ = Z();
                state.C = this.D.F() - this.D.A(viewZ);
                state.D = w(viewZ);
                return state;
            }
            View viewA = a();
            state.D = w(viewA);
            state.C = this.D.D(viewA) - this.D.J();
            return state;
        }
        state.B();
        return state;
    }

    private void e(int layoutDirection, int requiredSpace, boolean canUseExistingSpace, C0499Eo state) {
        int iJ;
        this.L.E = n();
        this.L.D = b(state);
        this.L.I = layoutDirection;
        if (layoutDirection == 1) {
            this.L.D += this.D.G();
            View viewZ = Z();
            this.L.G = this.H ? -1 : 1;
            this.L.C = w(viewZ) + this.L.G;
            this.L.J = this.D.A(viewZ);
            iJ = this.D.A(viewZ) - this.D.F();
        } else {
            View viewA = a();
            this.L.D += this.D.J();
            this.L.G = this.H ? 1 : -1;
            this.L.C = w(viewA) + this.L.G;
            this.L.J = this.D.D(viewA);
            iJ = (-this.D.D(viewA)) + this.D.J();
        }
        this.L.B = requiredSpace;
        if (canUseExistingSpace) {
            this.L.B -= iJ;
        }
        this.L.M = iJ;
    }

    private void f(C0491Eg recycler, C0499Eo state, E2 anchorInfo) {
        if (g(state, anchorInfo) || i(recycler, state, anchorInfo)) {
            return;
        }
        anchorInfo.A();
        anchorInfo.D = this.P ? state.B() - 1 : 0;
    }

    private boolean g(C0499Eo state, E2 anchorInfo) {
        int iD;
        if (state.E() || this.F == -1) {
            return false;
        }
        if (this.F < 0 || this.F >= state.B()) {
            this.F = -1;
            this.G = Integer.MIN_VALUE;
            return false;
        }
        anchorInfo.D = this.F;
        if (this.E != null && this.E.A()) {
            anchorInfo.C = this.E.B;
            if (anchorInfo.C) {
                anchorInfo.B = this.D.F() - this.E.C;
                return true;
            }
            anchorInfo.B = this.D.J() + this.E.C;
            return true;
        }
        if (this.G == Integer.MIN_VALUE) {
            View viewV = V(this.F);
            if (viewV != null) {
                if (this.D.B(viewV) > this.D.K()) {
                    anchorInfo.A();
                    return true;
                }
                if (this.D.D(viewV) - this.D.J() < 0) {
                    anchorInfo.B = this.D.J();
                    anchorInfo.C = false;
                    return true;
                }
                if (this.D.F() - this.D.A(viewV) < 0) {
                    anchorInfo.B = this.D.F();
                    anchorInfo.C = true;
                    return true;
                }
                if (anchorInfo.C) {
                    iD = this.D.A(viewV) + this.D.L();
                } else {
                    iD = this.D.D(viewV);
                }
                anchorInfo.B = iD;
                return true;
            }
            if (b() > 0) {
                anchorInfo.C = (this.F < w(a(0))) == this.H;
            }
            anchorInfo.A();
            return true;
        }
        anchorInfo.C = this.H;
        if (this.H) {
            anchorInfo.B = this.D.F() - this.G;
            return true;
        }
        anchorInfo.B = this.D.J() + this.G;
        return true;
    }

    private void h(C0491Eg recycler, C0499Eo state, int startOffset, int endOffset) {
        if (!state.C() || b() == 0 || state.E() || !F()) {
            return;
        }
        int i = 0;
        int scrapSize = 0;
        List<AbstractC0502Er> listH = recycler.H();
        int size = listH.size();
        int iW = w(a(0));
        for (int i2 = 0; i2 < size; i2++) {
            AbstractC0502Er abstractC0502Er = listH.get(i2);
            if (!abstractC0502Er.U()) {
                int position = abstractC0502Er.K();
                if (((position < iW) != this.H ? (byte) -1 : (byte) 1) == -1) {
                    int position2 = this.D.B(abstractC0502Er.B);
                    i += position2;
                } else {
                    int position3 = this.D.B(abstractC0502Er.B);
                    scrapSize += position3;
                }
            }
        }
        this.L.L = listH;
        if (i > 0) {
            O(w(a()), startOffset);
            this.L.D = i;
            this.L.B = 0;
            this.L.A();
            J(recycler, this.L, state, false);
        }
        if (scrapSize > 0) {
            d(w(Z()), endOffset);
            this.L.D = scrapSize;
            this.L.B = 0;
            this.L.A();
            J(recycler, this.L, state, false);
        }
        this.L.L = null;
    }

    private boolean i(C0491Eg recycler, C0499Eo state, E2 anchorInfo) {
        View viewW;
        int iJ;
        if (b() == 0) {
            return false;
        }
        View viewL = l();
        if (viewL != null && anchorInfo.D(viewL, state)) {
            anchorInfo.C(viewL);
            return true;
        }
        if (this.J != this.P) {
            return false;
        }
        if (anchorInfo.C) {
            viewW = V(recycler, state);
        } else {
            viewW = W(recycler, state);
        }
        if (viewW == null) {
            return false;
        }
        anchorInfo.B(viewW);
        if (!state.E() && F()) {
            if (this.D.D(viewW) >= this.D.F() || this.D.A(viewW) < this.D.J()) {
                if (anchorInfo.C) {
                    iJ = this.D.F();
                } else {
                    iJ = this.D.J();
                }
                anchorInfo.B = iJ;
            }
        }
        return true;
    }

    private void j(C0491Eg recycler, E4 layoutState) {
        if (!layoutState.K || layoutState.E) {
            return;
        }
        if (layoutState.I == -1) {
            l(recycler, layoutState.M);
        } else {
            m(recycler, layoutState.M);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0483Dy
    public final PointF jB(int targetPosition) {
        if (b() == 0) {
            return null;
        }
        int i = (targetPosition < w(a(0))) != this.H ? -1 : 1;
        if (this.C == 0) {
            return new PointF(i, 0.0f);
        }
        return new PointF(0.0f, i);
    }

    private void k(C0491Eg recycler, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return;
        }
        if (endIndex > startIndex) {
            for (int i = endIndex - 1; i >= startIndex; i--) {
                kA(i, recycler);
            }
            return;
        }
        while (startIndex > endIndex) {
            kA(startIndex, recycler);
            startIndex--;
        }
    }

    private void l(C0491Eg recycler, int dt) {
        int limit = b();
        if (dt < 0) {
            return;
        }
        int iE = this.D.E() - dt;
        if (this.H) {
            for (int i = 0; i < limit; i++) {
                View viewA = a(i);
                if (this.D.D(viewA) < iE || this.D.N(viewA) < iE) {
                    k(recycler, 0, i);
                    return;
                }
            }
            return;
        }
        for (int i2 = limit - 1; i2 >= 0; i2--) {
            View viewA2 = a(i2);
            if (this.D.D(viewA2) < iE || this.D.N(viewA2) < iE) {
                k(recycler, limit - 1, i2);
                return;
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public final boolean lA() {
        return (n() == 1073741824 || AA() == 1073741824 || !BA()) ? false : true;
    }

    private void m(C0491Eg recycler, int dt) {
        if (dt < 0) {
            return;
        }
        int iB = b();
        if (this.H) {
            for (int i = iB - 1; i >= 0; i--) {
                View viewA = a(i);
                if (this.D.A(viewA) > dt || this.D.M(viewA) > dt) {
                    k(recycler, iB - 1, i);
                    return;
                }
            }
            return;
        }
        for (int i2 = 0; i2 < iB; i2++) {
            View viewA2 = a(i2);
            if (this.D.A(viewA2) > dt || this.D.M(viewA2) > dt) {
                k(recycler, 0, i2);
                return;
            }
        }
    }

    private final boolean n() {
        return this.D.H() == 0 && this.D.E() == 0;
    }

    private void o() {
        if (this.C == 1 || !JB()) {
            this.H = this.N;
        } else {
            this.H = this.N ? false : true;
        }
    }

    private final int p(int dy, C0491Eg recycler, C0499Eo state) {
        int i = 0;
        if (b() != 0 && dy != 0) {
            this.L.K = true;
            DB();
            int i2 = dy > 0 ? 1 : -1;
            int iAbs = Math.abs(dy);
            e(i2, iAbs, true, state);
            int iJ = this.L.M + J(recycler, this.L, state, false);
            if (iJ >= 0) {
                i = iAbs > iJ ? i2 * iJ : dy;
                this.D.O(-i);
                this.L.H = i;
            }
        }
        return i;
    }

    private final void q(boolean reverseLayout) {
        E((String) null);
        if (reverseLayout == this.N) {
            return;
        }
        this.N = reverseLayout;
        oA();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public int qA(int dx, C0491Eg recycler, C0499Eo state) {
        if (this.C == 1) {
            return 0;
        }
        return p(dx, recycler, state);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public void rA(int position) {
        this.F = position;
        this.G = Integer.MIN_VALUE;
        if (this.E != null) {
            this.E.B();
        }
        oA();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0481Dw
    public int sA(int dy, C0491Eg recycler, C0499Eo state) {
        if (this.C == 0) {
            return 0;
        }
        return p(dy, recycler, state);
    }
}
