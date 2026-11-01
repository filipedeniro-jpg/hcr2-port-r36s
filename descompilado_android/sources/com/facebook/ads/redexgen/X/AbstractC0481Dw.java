package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC0481Dw {
    public DN C;
    public int F;
    public boolean G;
    public C0503Es H;

    @Nullable
    public E7 J;
    private int L;
    private int M;
    private int R;
    private int S;
    private final EZ N = new C0485Ea(this);
    private final EZ Q = new C0486Eb(this);
    public FA D = new FA(this.N);
    public FA K = new FA(this.Q);
    public boolean I = false;
    public boolean E = false;
    public boolean B = false;
    private boolean P = true;
    private boolean O = true;

    public abstract C0479Du W();

    public final void A(View child) {
        B(child, -1);
    }

    public final int AA() {
        return this.S;
    }

    public final boolean AB(View child, int widthSpec, int heightSpec, C0479Du lp) {
        return (!child.isLayoutRequested() && this.P && c(child.getWidth(), widthSpec, lp.width) && c(child.getHeight(), heightSpec, lp.height)) ? false : true;
    }

    public final void B(View child, int index) {
        E(child, index, true);
    }

    public final boolean BA() {
        int childCount = b();
        for (int i = 0; i < childCount; i++) {
            ViewGroup.LayoutParams layoutParams = a(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    public static int C(int spec, int desired, int min) {
        int mode = View.MeasureSpec.getMode(spec);
        int mode2 = View.MeasureSpec.getSize(spec);
        switch (mode) {
            case Integer.MIN_VALUE:
                return Math.min(mode2, Math.max(desired, min));
            case 1073741824:
                return mode2;
            default:
                return Math.max(desired, min);
        }
    }

    public final void C(View child) {
        D(child, -1);
    }

    public final void CA(E7 smoothScroller) {
        if (this.J != null && smoothScroller != this.J && this.J.E()) {
            this.J.N();
        }
        this.J = smoothScroller;
        this.J.M(this.H, this);
    }

    public static int D(int parentSize, int parentMode, int padding, int childDimension, boolean canScroll) {
        int iMax = Math.max(0, parentSize - padding);
        int i = 0;
        int i2 = 0;
        if (canScroll) {
            if (childDimension >= 0) {
                i = childDimension;
                i2 = 1073741824;
            } else if (childDimension == -1) {
                switch (parentMode) {
                    case Integer.MIN_VALUE:
                    case 1073741824:
                        i = iMax;
                        i2 = parentMode;
                        break;
                    case 0:
                        i = 0;
                        i2 = 0;
                        break;
                }
            } else if (childDimension == -2) {
                i = 0;
                i2 = 0;
            }
        } else if (childDimension >= 0) {
            i = childDimension;
            i2 = 1073741824;
        } else if (childDimension == -1) {
            i = iMax;
            i2 = parentMode;
        } else if (childDimension == -2) {
            i = iMax;
            i2 = (parentMode == Integer.MIN_VALUE || parentMode == 1073741824) ? Integer.MIN_VALUE : 0;
        }
        return View.MeasureSpec.makeMeasureSpec(i, i2);
    }

    public final void D(View child, int index) {
        E(child, index, false);
    }

    public final boolean DA() {
        return this.O;
    }

    private void E(View child, int index, boolean disappearing) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(child);
        if (disappearing || abstractC0502ErQ.U()) {
            this.H.I.B(abstractC0502ErQ);
        } else {
            this.H.I.O(abstractC0502ErQ);
        }
        C0479Du c0479Du = (C0479Du) child.getLayoutParams();
        if (abstractC0502ErQ.M() || abstractC0502ErQ.V()) {
            if (abstractC0502ErQ.V()) {
                abstractC0502ErQ.g();
            } else {
                abstractC0502ErQ.E();
            }
            this.C.C(child, index, child.getLayoutParams(), false);
        } else if (child.getParent() == this.H) {
            int iK = this.C.K(child);
            if (index == -1) {
                index = this.C.G();
            }
            if (iK == -1) {
                throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.H.indexOfChild(child) + this.H.S());
            }
            if (iK != index) {
                this.H.R.b(iK, index);
            }
        } else {
            this.C.A(child, index, false);
            c0479Du.C = true;
            if (this.J != null && this.J.E()) {
                this.J.G(child);
            }
        }
        if (c0479Du.D) {
            abstractC0502ErQ.B.invalidate();
            c0479Du.D = false;
        }
    }

    public void E(String message) {
        if (this.H != null) {
            this.H.H(message);
        }
    }

    public final boolean EA() {
        return this.J != null && this.J.E();
    }

    private void F(C0491Eg recycler, int index, View view) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(view);
        if (abstractC0502ErQ.f()) {
            return;
        }
        if (abstractC0502ErQ.R() && !abstractC0502ErQ.U() && !this.H.C.F()) {
            G(index);
            recycler.T(abstractC0502ErQ);
        } else {
            M(index);
            recycler.U(view);
            this.H.I.K(abstractC0502ErQ);
        }
    }

    public boolean F() {
        return false;
    }

    public void FA(C0503Es recyclerView, C0499Eo state, int position) {
        Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
    }

    private final void G(int index) {
        if (a(index) != null) {
            this.C.O(index);
        }
    }

    public boolean G() {
        return false;
    }

    public final void GA(View child, int left, int top, int right, int bottom) {
        C0479Du c0479Du = (C0479Du) child.getLayoutParams();
        Rect rect = c0479Du.B;
        child.layout(rect.left + left + c0479Du.leftMargin, rect.top + top + c0479Du.topMargin, (right - rect.right) - c0479Du.rightMargin, (bottom - rect.bottom) - c0479Du.bottomMargin);
    }

    private final void H(View child, int index) {
        I(child, index, (C0479Du) child.getLayoutParams());
    }

    public boolean H() {
        return false;
    }

    public final void HA(View child, int widthUsed, int heightUsed) {
        C0479Du c0479Du = (C0479Du) child.getLayoutParams();
        Rect rectA = this.H.a(child);
        int i = widthUsed + rectA.left + rectA.right;
        int widthSpec = heightUsed + rectA.top + rectA.bottom;
        int iD = D(z(), AA(), t() + u() + c0479Du.leftMargin + c0479Du.rightMargin + i, c0479Du.width, G());
        int iD2 = D(m(), n(), v() + s() + c0479Du.topMargin + c0479Du.bottomMargin + widthSpec, c0479Du.height, H());
        if (AB(child, iD, iD2, c0479Du)) {
            child.measure(iD, iD2);
        }
    }

    private final void I(View child, int index, C0479Du lp) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(child);
        if (abstractC0502ErQ.U()) {
            this.H.I.B(abstractC0502ErQ);
        } else {
            this.H.I.O(abstractC0502ErQ);
        }
        this.C.C(child, index, lp, abstractC0502ErQ.U());
    }

    public boolean I(C0479Du lp) {
        return lp != null;
    }

    public void IA(int dx) {
        if (this.H != null) {
            this.H.g(dx);
        }
    }

    public void J(int dx, int dy, C0499Eo state, InterfaceC0473Do layoutPrefetchRegistry) {
    }

    private final void J(View child) {
        this.C.N(child);
    }

    public void JA(int dy) {
        if (this.H != null) {
            this.H.h(dy);
        }
    }

    public void K(int adapterItemCount, InterfaceC0473Do layoutPrefetchRegistry) {
    }

    private final boolean K(C0491Eg recycler, C0499Eo state, View view, int action, Bundle args) {
        return false;
    }

    public final void KA(C1G oldAdapter, C1G newAdapter) {
    }

    public int L(C0499Eo state) {
        return 0;
    }

    private final boolean L(C0491Eg recycler, C0499Eo state, int action, Bundle args) {
        if (this.H == null) {
            return false;
        }
        int iM = 0;
        int iZ = 0;
        switch (action) {
            case 4096:
                if (this.H.canScrollVertically(1)) {
                    iM = (m() - v()) - s();
                }
                if (this.H.canScrollHorizontally(1)) {
                    iZ = (z() - t()) - u();
                }
                break;
            case 8192:
                if (this.H.canScrollVertically(-1)) {
                    iM = -((m() - v()) - s());
                }
                if (this.H.canScrollHorizontally(-1)) {
                    iZ = -((z() - t()) - u());
                }
                break;
        }
        if (iM == 0 && iZ == 0) {
            return false;
        }
        this.H.scrollBy(iZ, iM);
        return true;
    }

    public final boolean LA(C0503Es recyclerView, ArrayList<View> arrayList, int i, int focusableMode) {
        return false;
    }

    public int M(C0499Eo state) {
        return 0;
    }

    private final void M(int index) {
        N(index, a(index));
    }

    @CallSuper
    public void MA(C0503Es view, C0491Eg recycler) {
        W(view);
    }

    public int N(C0499Eo state) {
        return 0;
    }

    private void N(int index, View view) {
        this.C.D(index);
    }

    @Nullable
    public View NA(View focused, int direction, C0491Eg recycler, C0499Eo state) {
        return null;
    }

    public int O(C0499Eo state) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(E7 smoothScroller) {
        if (this.J == smoothScroller) {
            this.J = null;
        }
    }

    public void OA(AccessibilityEvent event) {
        V(this.H.X, this.H.Z, event);
    }

    private final int P(View child) {
        return ((C0479Du) child.getLayoutParams()).B.bottom;
    }

    public int P(C0499Eo state) {
        return 0;
    }

    public final void PA(AT info) {
        U(this.H.X, this.H.Z, info);
    }

    public int Q(C0499Eo state) {
        return 0;
    }

    @Deprecated
    private final boolean Q(C0503Es parent, View child, View focused) {
        return EA() || parent.m250e();
    }

    public final void QA(View host, AT info) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(host);
        if (abstractC0502ErQ != null && !abstractC0502ErQ.U() && !this.C.L(abstractC0502ErQ.B)) {
            RA(this.H.X, this.H.Z, host, info);
        }
    }

    public final void R(C0491Eg recycler) {
        for (int iB = b() - 1; iB >= 0; iB--) {
            View v = a(iB);
            F(recycler, iB, v);
        }
    }

    private int[] R(C0503Es parent, View child, Rect rect, boolean immediate) {
        int[] iArr = new int[2];
        int offScreenBottom = t();
        int offScreenLeft = v();
        int iZ = z();
        int parentLeft = u();
        int offScreenRight = iZ - parentLeft;
        int parentRight = m();
        int parentLeft2 = s();
        int parentBottom = parentRight - parentLeft2;
        int parentTop = child.getLeft();
        int parentLeft3 = rect.left;
        int parentTop2 = parentTop + parentLeft3;
        int parentLeft4 = child.getScrollX();
        int parentTop3 = parentTop2 - parentLeft4;
        int top = child.getTop();
        int parentLeft5 = rect.top;
        int i = top + parentLeft5;
        int parentLeft6 = child.getScrollY();
        int i2 = i - parentLeft6;
        int childTop = rect.width();
        int i3 = parentTop3 + childTop;
        int childTop2 = rect.height();
        int i4 = i2 + childTop2;
        int childTop3 = parentTop3 - offScreenBottom;
        int iMin = Math.min(0, childTop3);
        int childTop4 = i2 - offScreenLeft;
        int iMin2 = Math.min(0, childTop4);
        int offScreenTop = i3 - offScreenRight;
        int iMax = Math.max(0, offScreenTop);
        int iMax2 = Math.max(0, i4 - parentBottom);
        int childLeft = p();
        if (childLeft == 1) {
            if (iMax == 0) {
                iMax = Math.max(iMin, i3 - offScreenRight);
            }
        } else {
            iMax = iMin != 0 ? iMin : Math.min(parentTop3 - offScreenBottom, iMax);
        }
        if (iMin2 == 0) {
            iMin2 = Math.min(i2 - offScreenLeft, iMax2);
        }
        iArr[0] = iMax;
        iArr[1] = iMin2;
        return iArr;
    }

    public void RA(C0491Eg recycler, C0499Eo state, View host, AT info) {
        info.I(AR.B(H() ? w(host) : 0, 1, G() ? w(host) : 0, 1, false, false));
    }

    public final void S(C0503Es view) {
        this.E = true;
        a(view);
    }

    private final void S(C0503Es recyclerView, int positionStart, int itemCount) {
    }

    public final View SA(View focused, int direction) {
        return null;
    }

    private final int T(View child) {
        return ((C0479Du) child.getLayoutParams()).B.left;
    }

    public final void T(C0503Es view, C0491Eg recycler) {
        this.E = false;
        MA(view, recycler);
    }

    public void TA(C0503Es recyclerView, int positionStart, int itemCount) {
    }

    public final void U() {
        if (this.J != null) {
            this.J.N();
        }
    }

    private final void U(C0491Eg recycler, C0499Eo state, AT info) {
        if (this.H.canScrollVertically(-1) || this.H.canScrollHorizontally(-1)) {
            info.A(8192);
            info.F(true);
        }
        if (this.H.canScrollVertically(1) || this.H.canScrollHorizontally(1)) {
            info.A(4096);
            info.F(true);
        }
        info.L(AQ.B(x(recycler, state), m245d(recycler, state), d(recycler, state), Y(recycler, state)));
    }

    public void UA(C0503Es recyclerView) {
    }

    public View V(int position) {
        int i = b();
        for (int i2 = 0; i2 < i; i2++) {
            View viewA = a(i2);
            AbstractC0502Er abstractC0502ErQ = C0503Es.Q(viewA);
            if (abstractC0502ErQ != null && abstractC0502ErQ.K() == position && !abstractC0502ErQ.f() && (this.H.Z.E() || !abstractC0502ErQ.U())) {
                return viewA;
            }
        }
        return null;
    }

    private final void V(C0491Eg recycler, C0499Eo state, AccessibilityEvent event) {
        boolean z = true;
        if (this.H == null || event == null) {
            return;
        }
        if (!this.H.canScrollVertically(1) && !this.H.canScrollVertically(-1) && !this.H.canScrollHorizontally(-1) && !this.H.canScrollHorizontally(1)) {
            z = false;
        }
        event.setScrollable(z);
        if (this.H.C == null) {
            return;
        }
        event.setItemCount(this.H.C.C());
    }

    public void VA(C0503Es recyclerView, int from, int to, int itemCount) {
    }

    @Deprecated
    private final void W(C0503Es view) {
    }

    public void WA(C0503Es recyclerView, int positionStart, int itemCount) {
    }

    private final int X(View child) {
        return ((C0479Du) child.getLayoutParams()).B.right;
    }

    public C0479Du X(Context c, AttributeSet attrs) {
        return new C0479Du(c, attrs);
    }

    public void XA(C0503Es recyclerView, int positionStart, int itemCount, Object payload) {
        S(recyclerView, positionStart, itemCount);
    }

    private final int Y(C0491Eg recycler, C0499Eo state) {
        return 0;
    }

    public C0479Du Y(ViewGroup.LayoutParams lp) {
        if (lp instanceof C0479Du) {
            return new C0479Du((C0479Du) lp);
        }
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            return new C0479Du((ViewGroup.MarginLayoutParams) lp);
        }
        return new C0479Du(lp);
    }

    public void YA(C0491Eg recycler, C0499Eo state) {
        Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }

    public final int Z() {
        return -1;
    }

    private final int Z(View child) {
        return ((C0479Du) child.getLayoutParams()).B.top;
    }

    public void ZA(C0499Eo state) {
    }

    public final View a(int index) {
        if (this.C != null) {
            return this.C.F(index);
        }
        return null;
    }

    @CallSuper
    private final void a(C0503Es view) {
    }

    public void aA(C0491Eg recycler, C0499Eo state, int widthSpec, int heightSpec) {
        this.H.L(widthSpec, heightSpec);
    }

    public final int b() {
        if (this.C != null) {
            return this.C.G();
        }
        return 0;
    }

    private final void b(int fromIndex, int toIndex) {
        View viewA = a(fromIndex);
        if (viewA == null) {
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + fromIndex + this.H.toString());
        }
        M(fromIndex);
        H(viewA, toIndex);
    }

    public final boolean bA(C0503Es parent, C0499Eo state, View child, View focused) {
        return Q(parent, child, focused);
    }

    public final boolean c() {
        return this.H != null && this.H.G;
    }

    private static boolean c(int childSize, int spec, int dimension) {
        int mode = View.MeasureSpec.getMode(spec);
        int size = View.MeasureSpec.getSize(spec);
        if (dimension > 0 && childSize != dimension) {
            return false;
        }
        switch (mode) {
            case Integer.MIN_VALUE:
                return size >= childSize;
            case 0:
                return true;
            case 1073741824:
                return size == childSize;
            default:
                return false;
        }
    }

    public void cA(Parcelable state) {
    }

    /* JADX INFO: renamed from: d, reason: collision with other method in class */
    public int m245d(C0491Eg recycler, C0499Eo state) {
        if (this.H == null || this.H.C == null || !G()) {
            return 1;
        }
        return this.H.C.C();
    }

    private final boolean d(C0491Eg recycler, C0499Eo state) {
        return false;
    }

    public Parcelable dA() {
        return null;
    }

    public final int e(View child) {
        return child.getBottom() + P(child);
    }

    private boolean e(C0503Es parent, int dx, int dy) {
        View focusedChild = parent.getFocusedChild();
        if (focusedChild == null) {
            return false;
        }
        int iT = t();
        int iV = v();
        int iZ = z() - u();
        int iM = m() - s();
        Rect rect = this.H.a;
        f(focusedChild, rect);
        return rect.left - dx < iZ && rect.right - dx > iT && rect.top - dy < iM && rect.bottom - dy > iV;
    }

    public void eA(int state) {
    }

    public final void f(View view, Rect outBounds) {
        C0503Es.R(view, outBounds);
    }

    public final boolean fA(int action, Bundle args) {
        return L(this.H.X, this.H.Z, action, args);
    }

    public final int g(View child) {
        return child.getLeft() - T(child);
    }

    public final boolean gA(View view, int action, Bundle args) {
        return K(this.H.X, this.H.Z, view, action, args);
    }

    public final int h(View child) {
        Rect rect = ((C0479Du) child.getLayoutParams()).B;
        return child.getMeasuredHeight() + rect.top + rect.bottom;
    }

    public final void hA(C0491Eg recycler) {
        for (int iB = b() - 1; iB >= 0; iB--) {
            if (!C0503Es.Q(a(iB)).f()) {
                kA(iB, recycler);
            }
        }
    }

    public final int i(View child) {
        Rect rect = ((C0479Du) child.getLayoutParams()).B;
        return child.getMeasuredWidth() + rect.left + rect.right;
    }

    public final void iA(C0491Eg recycler) {
        int iG = recycler.G();
        for (int i = iG - 1; i >= 0; i--) {
            View viewI = recycler.I(i);
            AbstractC0502Er abstractC0502ErQ = C0503Es.Q(viewI);
            if (!abstractC0502ErQ.f()) {
                abstractC0502ErQ.d(false);
                if (abstractC0502ErQ.W()) {
                    this.H.removeDetachedView(viewI, false);
                }
                if (this.H.N != null) {
                    this.H.N.K(abstractC0502ErQ);
                }
                abstractC0502ErQ.d(true);
                recycler.R(viewI);
            }
        }
        recycler.D();
        if (iG > 0) {
            this.H.invalidate();
        }
    }

    public final int j(View child) {
        return child.getRight() + X(child);
    }

    public final void jA(View child, C0491Eg recycler) {
        J(child);
        recycler.S(child);
    }

    public final int k(View child) {
        return child.getTop() - Z(child);
    }

    public final void kA(int index, C0491Eg recycler) {
        View viewA = a(index);
        G(index);
        recycler.S(viewA);
    }

    public final View l() {
        if (this.H == null) {
            return null;
        }
        View focusedChild = this.H.getFocusedChild();
        if (focusedChild == null || this.C.L(focusedChild)) {
            return null;
        }
        return focusedChild;
    }

    public boolean lA() {
        return false;
    }

    public final int m() {
        return this.L;
    }

    public final boolean mA(C0503Es parent, View child, Rect rect, boolean immediate) {
        return nA(parent, child, rect, immediate, false);
    }

    public final int n() {
        return this.M;
    }

    public final boolean nA(C0503Es parent, View child, Rect rect, boolean immediate, boolean focusedChildVisible) {
        int[] iArrR = R(parent, child, rect, immediate);
        int i = iArrR[0];
        int i2 = iArrR[1];
        if (focusedChildVisible && !e(parent, i, i2)) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            return false;
        }
        if (immediate) {
            parent.scrollBy(i, i2);
        } else {
            parent.w(i, i2);
        }
        return true;
    }

    public final int o() {
        C1G adapter = this.H != null ? this.H.getAdapter() : null;
        if (adapter != null) {
            return adapter.C();
        }
        return 0;
    }

    public final void oA() {
        if (this.H != null) {
            this.H.requestLayout();
        }
    }

    public final int p() {
        return C9Y.R(this.H);
    }

    public final void pA() {
        this.I = true;
    }

    public final int q() {
        return C9Y.Q(this.H);
    }

    public int qA(int dx, C0491Eg recycler, C0499Eo state) {
        return 0;
    }

    public final int r() {
        return C9Y.P(this.H);
    }

    public void rA(int position) {
    }

    public final int s() {
        if (this.H != null) {
            return this.H.getPaddingBottom();
        }
        return 0;
    }

    public int sA(int dy, C0491Eg recycler, C0499Eo state) {
        return 0;
    }

    public final int t() {
        if (this.H != null) {
            return this.H.getPaddingLeft();
        }
        return 0;
    }

    public final void tA(boolean enabled) {
        this.B = enabled;
    }

    public final int u() {
        if (this.H != null) {
            return this.H.getPaddingRight();
        }
        return 0;
    }

    public final void uA(C0503Es recyclerView) {
        vA(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public final int v() {
        if (this.H != null) {
            return this.H.getPaddingTop();
        }
        return 0;
    }

    public final void vA(int wSpec, int hSpec) {
        this.R = View.MeasureSpec.getSize(wSpec);
        this.S = View.MeasureSpec.getMode(wSpec);
        if (this.S == 0 && !C0503Es.TB) {
            this.R = 0;
        }
        this.L = View.MeasureSpec.getSize(hSpec);
        this.M = View.MeasureSpec.getMode(hSpec);
        if (this.M == 0 && !C0503Es.TB) {
            this.L = 0;
        }
    }

    public final int w(View view) {
        return ((C0479Du) view.getLayoutParams()).A();
    }

    public final void wA(int widthSize, int heightSize) {
        this.H.setMeasuredDimension(widthSize, heightSize);
    }

    public int x(C0491Eg recycler, C0499Eo state) {
        if (this.H == null || this.H.C == null || !H()) {
            return 1;
        }
        return this.H.C.C();
    }

    public void xA(Rect childrenBounds, int wSpec, int hSpec) {
        wA(C(wSpec, childrenBounds.width() + t() + u(), r()), C(hSpec, childrenBounds.height() + v() + s(), q()));
    }

    public final void y(View child, boolean includeDecorInsets, Rect out) {
        Matrix matrix;
        if (includeDecorInsets) {
            Rect rect = ((C0479Du) child.getLayoutParams()).B;
            out.set(-rect.left, -rect.top, child.getWidth() + rect.right, child.getHeight() + rect.bottom);
        } else {
            out.set(0, 0, child.getWidth(), child.getHeight());
        }
        if (this.H != null && (matrix = child.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.H.b;
            rectF.set(out);
            matrix.mapRect(rectF);
            out.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        out.offset(child.getLeft(), child.getTop());
    }

    public final void yA(int widthSpec, int heightSpec) {
        int i = b();
        if (i == 0) {
            this.H.L(widthSpec, heightSpec);
            return;
        }
        int maxY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MIN_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < i; i3++) {
            View viewA = a(i3);
            Rect rect = this.H.a;
            f(viewA, rect);
            int maxX = rect.left;
            if (maxX < maxY) {
                maxY = rect.left;
            }
            int maxX2 = rect.right;
            if (maxX2 > minY) {
                minY = rect.right;
            }
            int maxX3 = rect.top;
            if (maxX3 < minX) {
                minX = rect.top;
            }
            int maxX4 = rect.bottom;
            if (maxX4 > i2) {
                i2 = rect.bottom;
            }
        }
        this.H.a.set(maxY, minX, minY, i2);
        xA(this.H.a, widthSpec, heightSpec);
    }

    public final int z() {
        return this.R;
    }

    public final void zA(C0503Es recyclerView) {
        if (recyclerView == null) {
            this.H = null;
            this.C = null;
            this.R = 0;
            this.L = 0;
        } else {
            this.H = recyclerView;
            this.C = recyclerView.F;
            this.R = recyclerView.getWidth();
            this.L = recyclerView.getHeight();
        }
        this.S = 1073741824;
        this.M = 1073741824;
    }
}
