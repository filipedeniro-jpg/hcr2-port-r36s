package com.facebook.ads.redexgen.X;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9s, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03739s extends ViewGroup {
    private boolean AB;
    public AbstractC03528x B;
    private final C03649j BB;
    public int C;
    private final Rect CB;
    private int D;
    private int DB;
    private List<AnonymousClass93> E;
    private int EB;
    private int F;
    private boolean G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private ArrayList<View> N;
    private final Runnable O;
    private int P;
    private VelocityTracker Q;
    private boolean R;
    private boolean S;
    private float T;
    private int U;
    private int V;
    private boolean W;
    private float X;
    private float Y;
    private AnonymousClass92 Z;
    private boolean a;
    private boolean b;
    private boolean c;
    private final ArrayList<C03649j> d;
    private float e;
    private float f;
    private float g;
    private EdgeEffect h;
    private Drawable i;
    private int j;
    private int k;
    private boolean l;
    private C03689n m;
    private int n;
    private AnonymousClass92 o;
    private List<AnonymousClass92> p;
    private int q;
    private InterfaceC03679m r;
    private int s;
    private boolean t;
    private Parcelable u;
    private ClassLoader v;
    private int w;
    private EdgeEffect x;
    private int y;
    private Scroller z;
    public static final int[] GB = {R.attr.layout_gravity};
    private static final Comparator<C03649j> FB = new Comparator<C03649j>() { // from class: com.facebook.ads.redexgen.X.9e
        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public final int compare(C03649j lhs, C03649j rhs) {
            return lhs.D - rhs.D;
        }
    };
    private static final Interpolator HB = new Interpolator() { // from class: com.facebook.ads.redexgen.X.9f
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float t) {
            float t2 = t - 1.0f;
            return (t2 * t2 * t2 * t2 * t2) + 1.0f;
        }
    };
    private static final C03729r IB = new Comparator<View>() { // from class: com.facebook.ads.redexgen.X.9r
        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public final int compare(View lhs, View rhs) {
            C03659k c03659k = (C03659k) lhs.getLayoutParams();
            C03659k c03659k2 = (C03659k) rhs.getLayoutParams();
            if (c03659k.D != c03659k2.D) {
                return c03659k.D ? 1 : -1;
            }
            return c03659k.F - c03659k2.F;
        }
    };

    public C03739s(Context context) {
        super(context);
        this.d = new ArrayList<>();
        this.BB = new C03649j();
        this.CB = new Rect();
        this.w = -1;
        this.u = null;
        this.v = null;
        this.T = -3.4028235E38f;
        this.g = Float.MAX_VALUE;
        this.n = 1;
        this.D = -1;
        this.S = true;
        this.l = false;
        this.O = new Runnable() { // from class: com.facebook.ads.redexgen.X.9g
            @Override // java.lang.Runnable
            public final void run() {
                this.B.setScrollState(0);
                this.B.C();
            }
        };
        this.y = 0;
        Y();
    }

    public final void A() {
        int iC = this.B.C();
        this.P = iC;
        boolean z = this.d.size() < (this.n * 2) + 1 && this.d.size() < iC;
        int iMax = this.C;
        boolean z2 = false;
        int i = 0;
        while (i < i) {
            C03649j c03649j = this.d.get(i);
            int iD = this.B.D(c03649j.B);
            if (iD != -1) {
                if (iD == -2) {
                    this.d.remove(i);
                    i--;
                    if (!z2) {
                        this.B.E(this);
                        z2 = true;
                    }
                    this.B.A(this, c03649j.D, c03649j.B);
                    z = true;
                    int i2 = this.C;
                    int newPos = c03649j.D;
                    if (i2 == newPos) {
                        int newPos2 = iC - 1;
                        iMax = Math.max(0, Math.min(this.C, newPos2));
                        z = true;
                    }
                } else {
                    int newPos3 = c03649j.D;
                    if (newPos3 != iD) {
                        int i3 = c03649j.D;
                        int newPos4 = this.C;
                        if (i3 == newPos4) {
                            iMax = iD;
                        }
                        c03649j.D = iD;
                        z = true;
                    }
                }
            }
            i++;
        }
        if (z2) {
            this.B.B(this);
        }
        Collections.sort(this.d, FB);
        if (z) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                C03659k lp = (C03659k) getChildAt(i4).getLayoutParams();
                if (!lp.D) {
                    lp.G = 0.0f;
                }
            }
            Q(iMax, false, true);
            requestLayout();
        }
    }

    private void B() {
        if (this.M != 0) {
            if (this.N == null) {
                this.N = new ArrayList<>();
            } else {
                this.N.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.N.add(getChildAt(i));
            }
            Collections.sort(this.N, IB);
        }
    }

    private final C03649j C(int position, int index) {
        C03649j c03649j = new C03649j();
        c03649j.D = position;
        c03649j.B = this.B.G(this, position);
        c03649j.F = this.B.F(position);
        if (index < 0 || index >= this.d.size()) {
            this.d.add(c03649j);
        } else {
            this.d.add(index, c03649j);
        }
        return c03649j;
    }

    public final void C() {
        i(this.C);
    }

    private final void D(int x, int y, int velocity) {
        int scrollX;
        int dy;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int dx = (this.z == null || this.z.isFinished()) ? 0 : 1;
        if (dx != 0) {
            boolean wasScrolling = this.b;
            scrollX = wasScrolling ? this.z.getCurrX() : this.z.getStartX();
            this.z.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int scrollY = getScrollY();
        int i = x - scrollX;
        int sx = y - scrollY;
        if (i == 0 && sx == 0) {
            I(false);
            C();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i2 = clientWidth / 2;
        int halfWidth = Math.abs(i);
        float distanceRatio = N(Math.min(1.0f, (1.0f * halfWidth) / clientWidth));
        float f = i2 + (i2 * distanceRatio);
        int iAbs = Math.abs(velocity);
        if (iAbs > 0) {
            float distance = iAbs;
            dy = Math.round(1000.0f * Math.abs(f / distance)) * 4;
        } else {
            float F = clientWidth * this.B.F(this.C);
            float fAbs = Math.abs(i);
            float pageWidth = this.q;
            dy = (int) ((1.0f + (fAbs / (pageWidth + F))) * 100.0f);
        }
        int iMin = Math.min(dy, 600);
        this.b = false;
        this.z.startScroll(scrollX, scrollY, i, sx, iMin);
        C9Y.J(this);
    }

    private final boolean E(int direction) {
        View nextFocused = findFocus();
        if (nextFocused == this) {
            nextFocused = null;
        } else if (nextFocused != null) {
            boolean z = false;
            for (ViewParent parent = nextFocused.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                if (parent == this) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                StringBuilder sb = new StringBuilder();
                sb.append(nextFocused.getClass().getSimpleName());
                for (ViewParent parent2 = nextFocused.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ").append(parent2.getClass().getSimpleName());
                }
                Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                nextFocused = null;
            }
        }
        boolean zE = false;
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, nextFocused, direction);
        if (viewFindNextFocus != null && viewFindNextFocus != nextFocused) {
            if (direction == 17) {
                int nextLeft = T(this.CB, viewFindNextFocus).left;
                zE = (nextFocused == null || nextLeft < T(this.CB, nextFocused).left) ? viewFindNextFocus.requestFocus() : e();
            } else if (direction == 66) {
                int nextLeft2 = T(this.CB, viewFindNextFocus).left;
                zE = (nextFocused == null || nextLeft2 > T(this.CB, nextFocused).left) ? viewFindNextFocus.requestFocus() : f();
            }
        } else if (direction == 17 || direction == 1) {
            zE = e();
        } else if (direction == 66 || direction == 2) {
            zE = f();
        }
        if (zE) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
        }
        return zE;
    }

    private void F(C03649j curItem, int curIndex, C03649j oldCurInfo) {
        C03649j c03649j;
        C03649j c03649j2;
        int iC = this.B.C();
        int clientWidth = getClientWidth();
        float f = clientWidth > 0 ? this.q / clientWidth : 0.0f;
        if (oldCurInfo != null) {
            int i = oldCurInfo.D;
            if (i < curItem.D) {
                int i2 = 0;
                float marginOffset = oldCurInfo.C;
                float marginOffset2 = marginOffset + oldCurInfo.F + f;
                int i3 = i + 1;
                while (i3 <= pos) {
                    int pos = this.d.size();
                    if (i2 >= pos) {
                        break;
                    }
                    C03649j c03649j3 = this.d.get(i2);
                    while (true) {
                        c03649j2 = c03649j3;
                        int pos2 = c03649j2.D;
                        if (i3 <= pos2) {
                            break;
                        }
                        int pos3 = this.d.size();
                        if (i2 >= pos3 - 1) {
                            break;
                        }
                        i2++;
                        c03649j3 = this.d.get(i2);
                    }
                    while (i3 < pos) {
                        marginOffset2 += this.B.F(i3) + f;
                        i3++;
                    }
                    c03649j2.C = marginOffset2;
                    marginOffset2 += c03649j2.F + f;
                    i3++;
                }
            } else if (i > curItem.D) {
                int size = this.d.size() - 1;
                float F = oldCurInfo.C;
                int i4 = i - 1;
                while (i4 >= pos && size >= 0) {
                    C03649j c03649j4 = this.d.get(size);
                    while (true) {
                        c03649j = c03649j4;
                        int pos4 = c03649j.D;
                        if (i4 >= pos4 || size <= 0) {
                            break;
                        }
                        size--;
                        c03649j4 = this.d.get(size);
                    }
                    while (i4 > pos) {
                        F -= this.B.F(i4) + f;
                        i4--;
                    }
                    F -= c03649j.F + f;
                    c03649j.C = F;
                    i4--;
                }
            }
        }
        int size2 = this.d.size();
        float F2 = curItem.C;
        int itemCount = curItem.D;
        int i5 = itemCount - 1;
        int itemCount2 = curItem.D;
        this.T = itemCount2 == 0 ? curItem.C : -3.4028235E38f;
        int itemCount3 = iC - 1;
        this.g = curItem.D == itemCount3 ? (curItem.C + curItem.F) - 1.0f : Float.MAX_VALUE;
        int i6 = curIndex - 1;
        while (i6 >= 0) {
            C03649j c03649j5 = this.d.get(i6);
            while (i5 > itemCount) {
                F2 -= this.B.F(i5) + f;
                i5--;
            }
            F2 -= c03649j5.F + f;
            c03649j5.C = F2;
            int itemCount4 = c03649j5.D;
            if (itemCount4 == 0) {
                this.T = F2;
            }
            i6--;
            i5--;
        }
        float F3 = curItem.C + curItem.F + f;
        int itemCount5 = curItem.D;
        int i7 = itemCount5 + 1;
        int i8 = curIndex + 1;
        while (i8 < size2) {
            C03649j c03649j6 = this.d.get(i8);
            while (i7 < itemCount) {
                F3 += this.B.F(i7) + f;
                i7++;
            }
            int itemCount6 = iC - 1;
            if (c03649j6.D == itemCount6) {
                this.g = (c03649j6.F + F3) - 1.0f;
            }
            c03649j6.C = F3;
            F3 += c03649j6.F + f;
            i8++;
            i7++;
        }
        this.l = false;
    }

    private final boolean G(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            int count = viewGroup.getChildCount();
            for (int i = count - 1; i >= 0; i--) {
                View childAt = viewGroup.getChildAt(i);
                int i2 = x + scrollX;
                int count2 = childAt.getLeft();
                if (i2 >= count2) {
                    int i3 = x + scrollX;
                    int count3 = childAt.getRight();
                    if (i3 < count3) {
                        int i4 = y + scrollY;
                        int count4 = childAt.getTop();
                        if (i4 >= count4) {
                            int i5 = y + scrollY;
                            int count5 = childAt.getBottom();
                            if (i5 < count5) {
                                int count6 = childAt.getLeft();
                                int i6 = (x + scrollX) - count6;
                                int count7 = childAt.getTop();
                                if (G(childAt, true, dx, i6, (y + scrollY) - count7)) {
                                    return true;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return checkV && v.canScrollHorizontally(-dx);
    }

    private final void H(int item, boolean smoothScroll, boolean always, int velocity) {
        if (this.B == null || this.B.C() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!always && this.C == item && this.d.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (item < 0) {
            item = 0;
        } else if (item >= this.B.C()) {
            item = this.B.C() - 1;
        }
        int i = this.n;
        if (item > this.C + i || item < this.C - i) {
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                this.d.get(i2).E = true;
            }
        }
        boolean z = this.C != item;
        if (this.S) {
            this.C = item;
            if (z) {
                L(item);
            }
            requestLayout();
            return;
        }
        i(item);
        a(item, smoothScroll, velocity, z);
    }

    private void I(boolean postEvents) {
        boolean z = this.y == 2;
        if (z) {
            setScrollingCacheEnabled(false);
            boolean needPopulate = this.z.isFinished() ? false : true;
            if (needPopulate) {
                this.z.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int oldY = this.z.getCurrX();
                int y = this.z.getCurrY();
                if (scrollX != oldY || scrollY != y) {
                    scrollTo(oldY, y);
                    if (oldY != scrollX) {
                        g(oldY);
                    }
                }
            }
        }
        this.t = false;
        for (int i = 0; i < this.d.size(); i++) {
            C03649j c03649j = this.d.get(i);
            if (c03649j.E) {
                z = true;
                c03649j.E = false;
            }
        }
        if (z) {
            if (postEvents) {
                C9Y.I(this, this.O);
            } else {
                this.O.run();
            }
        }
    }

    private int J(int currentPage, float pageOffset, int velocity, int deltaX) {
        if (Math.abs(deltaX) > this.U && Math.abs(velocity) > this.k) {
            if (velocity <= 0) {
                currentPage++;
            }
        } else {
            currentPage += (int) (pageOffset + (currentPage >= this.C ? 0.4f : 0.6f));
        }
        if (this.d.size() > 0) {
            return Math.max(this.d.get(0).D, Math.min(currentPage, this.d.get(this.d.size() - 1).D));
        }
        return currentPage;
    }

    private void K(int position, float offset, int offsetPixels) {
        if (this.o != null) {
            this.o.onPageScrolled(position, offset, offsetPixels);
        }
        if (this.p != null) {
            int size = this.p.size();
            for (int i = 0; i < size; i++) {
                AnonymousClass92 anonymousClass92 = this.p.get(i);
                if (anonymousClass92 != null) {
                    anonymousClass92.onPageScrolled(position, offset, offsetPixels);
                }
            }
        }
        if (this.Z != null) {
            this.Z.onPageScrolled(position, offset, offsetPixels);
        }
    }

    private void L(int position) {
        if (this.o != null) {
            this.o.onPageSelected(position);
        }
        if (this.p != null) {
            int size = this.p.size();
            for (int i = 0; i < size; i++) {
                AnonymousClass92 anonymousClass92 = this.p.get(i);
                if (anonymousClass92 != null) {
                    anonymousClass92.onPageSelected(position);
                }
            }
        }
        if (this.Z != null) {
            this.Z.onPageSelected(position);
        }
    }

    private void M(int state) {
        if (this.o != null) {
            this.o.onPageScrollStateChanged(state);
        }
        if (this.p != null) {
            int size = this.p.size();
            for (int i = 0; i < size; i++) {
                AnonymousClass92 anonymousClass92 = this.p.get(i);
                if (anonymousClass92 != null) {
                    anonymousClass92.onPageScrollStateChanged(state);
                }
            }
        }
        if (this.Z != null) {
            this.Z.onPageScrollStateChanged(state);
        }
    }

    private final float N(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    private void O(boolean enable) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setLayerType(enable ? this.s : 0, null);
        }
    }

    private void P() {
        this.a = false;
        this.c = false;
        if (this.Q != null) {
            this.Q.recycle();
            this.Q = null;
        }
    }

    private final void Q(int item, boolean smoothScroll, boolean always) {
        H(item, smoothScroll, always, 0);
    }

    private final boolean R(KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        switch (event.getKeyCode()) {
            case 21:
                if (event.hasModifiers(2)) {
                    return e();
                }
                return E(17);
            case 22:
                if (event.hasModifiers(2)) {
                    return f();
                }
                return E(66);
            case 61:
                if (event.hasNoModifiers()) {
                    return E(2);
                }
                if (!event.hasModifiers(1)) {
                    return false;
                }
                return E(1);
            default:
                return false;
        }
    }

    private final void S(int item, boolean smoothScroll) {
        this.t = false;
        Q(item, smoothScroll, false);
    }

    private Rect T(Rect outRect, View child) {
        if (outRect == null) {
            outRect = new Rect();
        }
        if (child == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.left = child.getLeft();
            outRect.right = child.getRight();
            outRect.top = child.getTop();
            outRect.bottom = child.getBottom();
            ViewParent parent = child.getParent();
            while ((parent instanceof ViewGroup) && parent != this) {
                ViewGroup viewGroup = (ViewGroup) parent;
                outRect.left += viewGroup.getLeft();
                outRect.right += viewGroup.getRight();
                outRect.top += viewGroup.getTop();
                outRect.bottom += viewGroup.getBottom();
                parent = viewGroup.getParent();
            }
        }
        return outRect;
    }

    private final C03649j U(View child) {
        while (true) {
            Object parent = child.getParent();
            if (parent != this) {
                if (parent == null || !(parent instanceof View)) {
                    return null;
                }
                child = (View) parent;
            } else {
                return V(child);
            }
        }
    }

    private final C03649j V(View child) {
        for (int i = 0; i < this.d.size(); i++) {
            C03649j c03649j = this.d.get(i);
            if (this.B.H(child, c03649j.B)) {
                return c03649j;
            }
        }
        return null;
    }

    private C03649j W() {
        int clientWidth = getClientWidth();
        float lastWidth = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float marginOffset = clientWidth > 0 ? this.q / clientWidth : 0.0f;
        int i = -1;
        float scrollOffset = 0.0f;
        float f = 0.0f;
        boolean z = true;
        C03649j c03649j = null;
        int i2 = 0;
        while (i2 < this.d.size()) {
            C03649j c03649j2 = this.d.get(i2);
            if (!z && c03649j2.D != i + 1) {
                c03649j2 = this.BB;
                c03649j2.C = scrollOffset + f + marginOffset;
                c03649j2.D = i + 1;
                c03649j2.F = this.B.F(c03649j2.D);
                i2--;
            }
            scrollOffset = c03649j2.C;
            float f2 = c03649j2.F + scrollOffset + marginOffset;
            if (!z && lastWidth < scrollOffset) {
                return c03649j;
            }
            if (lastWidth < f2 || i2 == this.d.size() - 1) {
                return c03649j2;
            }
            z = false;
            i = c03649j2.D;
            f = c03649j2.F;
            c03649j = c03649j2;
            i2++;
        }
        return c03649j;
    }

    private final C03649j X(int position) {
        for (int i = 0; i < this.d.size(); i++) {
            C03649j c03649j = this.d.get(i);
            if (c03649j.D == position) {
                return c03649j;
            }
        }
        return null;
    }

    private final void Y() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.z = new Scroller(context, HB);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.EB = viewConfiguration.getScaledPagingTouchSlop();
        float density = 400.0f * f;
        this.k = (int) density;
        this.j = viewConfiguration.getScaledMaximumFlingVelocity();
        this.h = new EdgeEffect(context);
        this.x = new EdgeEffect(context);
        float density2 = 25.0f * f;
        this.U = (int) density2;
        float density3 = 2.0f * f;
        this.J = (int) density3;
        float density4 = 16.0f * f;
        this.L = (int) density4;
        C9Y.F(this, new C8K() { // from class: com.facebook.ads.redexgen.X.9l
            private boolean B() {
                return this.B.B != null && this.B.B.C() > 1;
            }

            @Override // com.facebook.ads.redexgen.X.C8K
            public final void D(View host, AccessibilityEvent event) {
                super.D(host, event);
                event.setClassName(C03739s.class.getName());
                event.setScrollable(B());
                if (event.getEventType() == 4096 && this.B.B != null) {
                    event.setItemCount(this.B.B.C());
                    event.setFromIndex(this.B.C);
                    event.setToIndex(this.B.C);
                }
            }

            @Override // com.facebook.ads.redexgen.X.C8K
            public final void E(View host, AT info) {
                super.E(host, info);
                info.V(C03739s.class.getName());
                info.F(B());
                if (this.B.canScrollHorizontally(1)) {
                    info.A(4096);
                }
                if (this.B.canScrollHorizontally(-1)) {
                    info.A(8192);
                }
            }

            @Override // com.facebook.ads.redexgen.X.C8K
            public final boolean H(View host, int action, Bundle args) {
                if (super.H(host, action, args)) {
                    return true;
                }
                switch (action) {
                    case 4096:
                        if (!this.B.canScrollHorizontally(1)) {
                            return false;
                        }
                        this.B.setCurrentItem(this.B.C + 1);
                        return true;
                    case 8192:
                        if (!this.B.canScrollHorizontally(-1)) {
                            return false;
                        }
                        this.B.setCurrentItem(this.B.C - 1);
                        return true;
                    default:
                        return false;
                }
            }
        });
        if (C9Y.S(this) == 0) {
            C9Y.D(this, 1);
        }
        C9Y.C(this, new InterfaceC03518w() { // from class: com.facebook.ads.redexgen.X.9h
            private final Rect C = new Rect();

            @Override // com.facebook.ads.redexgen.X.InterfaceC03518w
            public final A5 WD(View v, A5 originalInsets) {
                A5 a5K = C9Y.K(v, originalInsets);
                if (a5K.E()) {
                    return a5K;
                }
                Rect rect = this.C;
                rect.left = a5K.B();
                rect.top = a5K.D();
                rect.right = a5K.C();
                rect.bottom = a5K.A();
                int childCount = this.B.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    A5 a5H = C9Y.H(this.B.getChildAt(i), a5K);
                    rect.left = Math.min(a5H.B(), rect.left);
                    rect.top = Math.min(a5H.D(), rect.top);
                    rect.right = Math.min(a5H.C(), rect.right);
                    rect.bottom = Math.min(a5H.A(), rect.bottom);
                }
                return a5K.F(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    private static boolean Z(@NonNull View view) {
        Class<?> clazz = view.getClass();
        return clazz.getAnnotation(InterfaceC03639i.class) != null;
    }

    private void a(int item, boolean smoothScroll, int velocity, boolean dispatchSelected) {
        C03649j c03649jX = X(item);
        int clientWidth = 0;
        if (c03649jX != null) {
            clientWidth = (int) (getClientWidth() * Math.max(this.T, Math.min(c03649jX.C, this.g)));
        }
        if (smoothScroll) {
            D(clientWidth, 0, velocity);
            if (dispatchSelected) {
                L(item);
                return;
            }
            return;
        }
        if (dispatchSelected) {
            L(item);
        }
        I(false);
        scrollTo(clientWidth, 0);
        g(clientWidth);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList<View> arrayList, int direction, int focusableMode) {
        C03649j c03649jV;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() == 0 && (c03649jV = V(childAt)) != null && c03649jV.D == this.C) {
                    childAt.addFocusables(arrayList, direction, focusableMode);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((focusableMode & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || arrayList == null) {
            return;
        }
        arrayList.add(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addTouchables(ArrayList<View> arrayList) {
        C03649j c03649jV;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (c03649jV = V(childAt)) != null && c03649jV.D == this.C) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!checkLayoutParams(params)) {
            params = generateLayoutParams(params);
        }
        C03659k c03659k = (C03659k) params;
        c03659k.D |= Z(child);
        if (this.W) {
            if (c03659k != null && c03659k.D) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            c03659k.E = true;
            addViewInLayout(child, index, params);
            return;
        }
        super.addView(child, index, params);
    }

    private final boolean b(float x, float dx) {
        return (x < ((float) this.V) && dx > 0.0f) || (x > ((float) (getWidth() - this.V)) && dx < 0.0f);
    }

    @CallSuper
    private final void c(int position, float offset, int offsetPixels) {
        int iMax;
        if (this.K > 0) {
            int i = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingLeft2 = getPaddingRight();
            int paddingRight = getWidth();
            int scrollX = getChildCount();
            for (int i2 = 0; i2 < scrollX; i2++) {
                View childAt = getChildAt(i2);
                C03659k c03659k = (C03659k) childAt.getLayoutParams();
                if (c03659k.D) {
                    switch (c03659k.C & 7) {
                        case 1:
                            int hgrav = childAt.getMeasuredWidth();
                            iMax = Math.max((paddingRight - hgrav) / 2, paddingLeft);
                            break;
                        case 2:
                        case 4:
                        default:
                            iMax = paddingLeft;
                            break;
                        case 3:
                            iMax = paddingLeft;
                            int hgrav2 = childAt.getWidth();
                            paddingLeft += hgrav2;
                            break;
                        case 5:
                            int hgrav3 = childAt.getMeasuredWidth();
                            iMax = (paddingRight - paddingLeft2) - hgrav3;
                            int hgrav4 = childAt.getMeasuredWidth();
                            paddingLeft2 += hgrav4;
                            break;
                    }
                    int hgrav5 = childAt.getLeft();
                    int i3 = (iMax + i) - hgrav5;
                    if (i3 != 0) {
                        childAt.offsetLeftAndRight(i3);
                    }
                }
            }
        }
        K(position, offset, offsetPixels);
        if (this.r != null) {
            int scrollX2 = getScrollX();
            int scrollX3 = getChildCount();
            for (int i4 = 0; i4 < scrollX3; i4++) {
                View childAt2 = getChildAt(i4);
                if (!((C03659k) childAt2.getLayoutParams()).D) {
                    this.r.transformPage(childAt2, (childAt2.getLeft() - scrollX2) / getClientWidth());
                }
            }
        }
        this.G = true;
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int direction) {
        if (this.B == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (direction < 0) {
            return scrollX > ((int) (((float) clientWidth) * this.T));
        }
        if (direction > 0) {
            return scrollX < ((int) (((float) clientWidth) * this.g));
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof C03659k) && super.checkLayoutParams(p);
    }

    @Override // android.view.View
    public final void computeScroll() {
        this.b = true;
        if (!this.z.isFinished() && this.z.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.z.getCurrX();
            int oldY = this.z.getCurrY();
            if (scrollX != currX || scrollY != oldY) {
                scrollTo(currX, oldY);
                if (!g(currX)) {
                    this.z.abortAnimation();
                    scrollTo(0, oldY);
                }
            }
            C9Y.J(this);
            return;
        }
        I(true);
    }

    private void d(MotionEvent ev) {
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) != this.D) {
            return;
        }
        int i = pointerIndex == 0 ? 1 : 0;
        this.e = ev.getX(i);
        this.D = ev.getPointerId(i);
        if (this.Q != null) {
            this.Q.clear();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || R(event);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        C03649j ii;
        if (event.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(event);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (ii = V(childAt)) != null) {
                int i2 = ii.D;
                if (i2 == this.C && childAt.dispatchPopulateAccessibilityEvent(event)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        boolean zDraw = false;
        int overScrollMode = getOverScrollMode();
        if (overScrollMode == 0 || (overScrollMode == 1 && this.B != null && this.B.C() > 1)) {
            if (!this.h.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                int overScrollMode2 = -height;
                int width2 = getPaddingTop();
                canvas.translate(overScrollMode2 + width2, this.T * width);
                this.h.setSize(height, width);
                zDraw = false | this.h.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.x.isFinished()) {
                int iSave2 = canvas.save();
                int width3 = getWidth();
                int height2 = getHeight();
                int width4 = getPaddingTop();
                int i = height2 - width4;
                int width5 = getPaddingBottom();
                int i2 = i - width5;
                canvas.rotate(90.0f);
                int width6 = getPaddingTop();
                canvas.translate(-width6, (-(this.g + 1.0f)) * width3);
                this.x.setSize(i2, width3);
                zDraw |= this.x.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.h.finish();
            this.x.finish();
        }
        if (zDraw) {
            C9Y.J(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.i;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    private final boolean e() {
        if (this.C <= 0) {
            return false;
        }
        S(this.C - 1, true);
        return true;
    }

    private final boolean f() {
        if (this.B == null || this.C >= this.B.C() - 1) {
            return false;
        }
        S(this.C + 1, true);
        return true;
    }

    private boolean g(int xpos) {
        if (this.d.size() == 0) {
            if (this.S) {
                return false;
            }
            this.G = false;
            c(0, 0.0f, 0);
            if (this.G) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        C03649j c03649jW = W();
        int clientWidth = getClientWidth();
        int width = this.q;
        int i = clientWidth + width;
        int widthWithMargin = this.q;
        int i2 = c03649jW.D;
        float f = ((xpos / clientWidth) - c03649jW.C) / (c03649jW.F + (widthWithMargin / clientWidth));
        float pageOffset = i;
        int offsetPixels = (int) (pageOffset * f);
        this.G = false;
        c(i2, f, offsetPixels);
        if (!this.G) {
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C03659k();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new C03659k(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    public AbstractC03528x getAdapter() {
        return this.B;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int childCount, int i) {
        return ((C03659k) this.N.get(this.M == 2 ? (childCount - 1) - i : i).getLayoutParams()).B;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public int getCurrentItem() {
        return this.C;
    }

    public int getOffscreenPageLimit() {
        return this.n;
    }

    public int getPageMargin() {
        return this.q;
    }

    private boolean h(float x) {
        boolean z = false;
        float f = this.e - x;
        this.e = x;
        float over = getScrollX() + f;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth;
        float scrollX2 = scrollX * this.T;
        float f2 = clientWidth * this.g;
        boolean z2 = true;
        boolean z3 = true;
        C03649j c03649j = this.d.get(0);
        C03649j c03649j2 = this.d.get(this.d.size() - 1);
        if (c03649j.D != 0) {
            z2 = false;
            float scrollX3 = c03649j.C;
            scrollX2 = scrollX3 * clientWidth;
        }
        if (c03649j2.D != this.B.C() - 1) {
            z3 = false;
            f2 = c03649j2.C * clientWidth;
        }
        if (over < scrollX2) {
            if (z2) {
                this.h.onPull(Math.abs(scrollX2 - over) / clientWidth);
                z = true;
            }
            over = scrollX2;
        } else if (over > f2) {
            if (z3) {
                this.x.onPull(Math.abs(over - f2) / clientWidth);
                z = true;
            }
            over = f2;
        }
        this.e += over - ((int) over);
        scrollTo((int) over, getScrollY());
        g((int) over);
        return z;
    }

    private final void i(int newCurrentItem) {
        String hexString;
        C03649j c03649jV;
        float paddingLeft;
        float rightWidthNeeded;
        C03649j c03649jX = null;
        if (this.C != newCurrentItem) {
            c03649jX = X(this.C);
            this.C = newCurrentItem;
        }
        if (this.B == null) {
            B();
            return;
        }
        if (this.t) {
            B();
            return;
        }
        if (getWindowToken() == null) {
            return;
        }
        this.B.E(this);
        int i = this.n;
        int pageLimit = this.C;
        int iMax = Math.max(0, pageLimit - i);
        int iC = this.B.C();
        int startPos = iC - 1;
        int N = this.C;
        int iMin = Math.min(startPos, N + i);
        int N2 = this.P;
        if (iC == N2) {
            C03649j c03649jC = null;
            int i2 = 0;
            while (i2 < curIndex) {
                C03649j c03649j = this.d.get(i2);
                if (c03649j.D >= this.C) {
                    if (c03649j.D != this.C) {
                        break;
                    }
                    c03649jC = c03649j;
                    break;
                }
                i2++;
            }
            if (c03649jC == null && iC > 0) {
                c03649jC = C(this.C, i2);
            }
            if (c03649jC != null) {
                float f = 0.0f;
                int i3 = i2 - 1;
                C03649j c03649j2 = i3 >= 0 ? this.d.get(i3) : null;
                int clientWidth = getClientWidth();
                if (clientWidth <= 0) {
                    paddingLeft = 0.0f;
                } else {
                    float rightWidthNeeded2 = c03649jC.F;
                    float rightWidthNeeded3 = clientWidth;
                    paddingLeft = (2.0f - rightWidthNeeded2) + (getPaddingLeft() / rightWidthNeeded3);
                }
                for (int i4 = this.C - 1; i4 >= 0; i4--) {
                    if (f >= paddingLeft && i4 < iMax) {
                        if (c03649j2 == null) {
                            break;
                        }
                        if (i4 == c03649j2.D && !c03649j2.E) {
                            this.d.remove(i3);
                            this.B.A(this, i4, c03649j2.B);
                            i3--;
                            i2--;
                            c03649j2 = i3 >= 0 ? this.d.get(i3) : null;
                        }
                    } else if (c03649j2 != null && i4 == c03649j2.D) {
                        f += c03649j2.F;
                        i3--;
                        c03649j2 = i3 >= 0 ? this.d.get(i3) : null;
                    } else {
                        f += C(i4, i3 + 1).F;
                        i2++;
                        c03649j2 = i3 >= 0 ? this.d.get(i3) : null;
                    }
                }
                float f2 = c03649jC.F;
                int i5 = i2 + 1;
                if (f2 < 2.0f) {
                    C03649j c03649j3 = i5 < this.d.size() ? this.d.get(i5) : null;
                    if (clientWidth <= 0) {
                        rightWidthNeeded = 0.0f;
                    } else {
                        float rightWidthNeeded4 = getPaddingRight();
                        rightWidthNeeded = (rightWidthNeeded4 / clientWidth) + 2.0f;
                    }
                    for (int i6 = this.C + 1; i6 < iC; i6++) {
                        if (f2 >= rightWidthNeeded && i6 > iMin) {
                            if (c03649j3 == null) {
                                break;
                            }
                            if (i6 == c03649j3.D && !c03649j3.E) {
                                this.d.remove(i5);
                                this.B.A(this, i6, c03649j3.B);
                                c03649j3 = i5 < this.d.size() ? this.d.get(i5) : null;
                            }
                        } else if (c03649j3 != null && i6 == c03649j3.D) {
                            f2 += c03649j3.F;
                            i5++;
                            c03649j3 = i5 < this.d.size() ? this.d.get(i5) : null;
                        } else {
                            C03649j c03649jC2 = C(i6, i5);
                            i5++;
                            f2 += c03649jC2.F;
                            c03649j3 = i5 < this.d.size() ? this.d.get(i5) : null;
                        }
                    }
                }
                F(c03649jC, i2, c03649jX);
            }
            this.B.M(this, this.C, c03649jC != null ? c03649jC.B : null);
            this.B.B(this);
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                C03659k c03659k = (C03659k) childAt.getLayoutParams();
                c03659k.B = i7;
                if (!c03659k.D && c03659k.G == 0.0f && (c03649jV = V(childAt)) != null) {
                    c03659k.G = c03649jV.F;
                    c03659k.F = c03649jV.D;
                }
            }
            B();
            if (!hasFocus()) {
                return;
            }
            View currentFocused = findFocus();
            C03649j c03649jU = currentFocused != null ? U(currentFocused) : null;
            if (c03649jU != null && c03649jU.D == this.C) {
                return;
            }
            for (int i8 = 0; i8 < getChildCount(); i8++) {
                View childAt2 = getChildAt(i8);
                C03649j c03649jV2 = V(childAt2);
                if (c03649jV2 != null && c03649jV2.D == this.C && childAt2.requestFocus(2)) {
                    return;
                }
            }
            return;
        }
        try {
            hexString = getResources().getResourceName(getId());
        } catch (Resources.NotFoundException e) {
            hexString = Integer.toHexString(getId());
        }
        throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.P + ", found: " + iC + " Pager id: " + hexString + " Pager class: " + getClass() + " Problematic adapter: " + this.B.getClass());
    }

    private void j(int width, int oldWidth, int margin, int oldMargin) {
        if (oldWidth > 0 && !this.d.isEmpty()) {
            if (!this.z.isFinished()) {
                this.z.setFinalX(getCurrentItem() * getClientWidth());
                return;
            }
            int width2 = ((width - getPaddingLeft()) - getPaddingRight()) + margin;
            int widthWithMargin = getPaddingLeft();
            int oldWidth2 = oldWidth - widthWithMargin;
            int widthWithMargin2 = getPaddingRight();
            int oldWidthWithMargin = (oldWidth2 - widthWithMargin2) + oldMargin;
            int widthWithMargin3 = getScrollX();
            int i = (int) (width2 * (widthWithMargin3 / oldWidthWithMargin));
            int xpos = getScrollY();
            scrollTo(i, xpos);
            return;
        }
        int xpos2 = this.C;
        C03649j c03649jX = X(xpos2);
        int paddingLeft = (int) (((width - getPaddingLeft()) - getPaddingRight()) * (c03649jX != null ? Math.min(c03649jX.C, this.g) : 0.0f));
        int scrollPos = getScrollX();
        if (paddingLeft == scrollPos) {
            return;
        }
        I(false);
        int scrollPos2 = getScrollY();
        scrollTo(paddingLeft, scrollPos2);
    }

    private void k() {
        int i = 0;
        while (i < getChildCount()) {
            C03659k lp = (C03659k) getChildAt(i).getLayoutParams();
            if (!lp.D) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    private boolean l() {
        this.D = -1;
        P();
        this.h.onRelease();
        this.x.onRelease();
        return this.h.isFinished() || this.x.isFinished();
    }

    private void m(boolean disallowIntercept) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.S = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.O);
        if (this.z != null && !this.z.isFinished()) {
            this.z.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        if (this.q > 0 && this.i != null && this.d.size() > 0 && this.B != null) {
            int width = getScrollX();
            int width2 = getWidth();
            int scrollX = this.q;
            float f2 = scrollX / width2;
            int i = 0;
            C03649j c03649j = this.d.get(0);
            float f3 = c03649j.C;
            int size = this.d.size();
            int firstPos = size - 1;
            int i2 = this.d.get(firstPos).D;
            for (int i3 = c03649j.D; i3 < i2; i3++) {
                while (i3 > pos && i < size) {
                    i++;
                    c03649j = this.d.get(i);
                }
                int pos = c03649j.D;
                if (i3 == pos) {
                    f = (c03649j.C + c03649j.F) * width2;
                    f3 = c03649j.C + c03649j.F + f2;
                } else {
                    float F = this.B.F(i3);
                    f = (f3 + F) * width2;
                    f3 += F + f2;
                }
                int pos2 = this.q;
                if (pos2 + f > width) {
                    Drawable drawable = this.i;
                    int iRound = Math.round(f);
                    int i4 = this.DB;
                    int pos3 = this.q;
                    int iRound2 = Math.round(pos3 + f);
                    int pos4 = this.F;
                    drawable.setBounds(iRound, i4, iRound2, pos4);
                    this.i.draw(canvas);
                }
                int pos5 = width + width2;
                if (f > pos5) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0119  */
    /* JADX WARN: Code duplicated, block: B:52:0x0120  */
    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent ev) {
        float yDiff;
        float f;
        int action = ev.getAction() & 255;
        if (action == 3 || action == 1) {
            l();
            return false;
        }
        if (action != 0) {
            if (this.a) {
                return true;
            }
            if (this.c) {
                return false;
            }
        }
        switch (action) {
            case 0:
                float x = ev.getX();
                this.X = x;
                this.e = x;
                float y = ev.getY();
                this.Y = y;
                this.f = y;
                this.D = ev.getPointerId(0);
                this.c = false;
                this.b = true;
                this.z.computeScrollOffset();
                if (this.y == 2 && Math.abs(this.z.getFinalX() - this.z.getCurrX()) > this.J) {
                    this.z.abortAnimation();
                    this.t = false;
                    C();
                    this.a = true;
                    m(true);
                    setScrollState(1);
                } else {
                    I(false);
                    this.a = false;
                }
                break;
            case 2:
                int i = this.D;
                if (i != -1) {
                    int iFindPointerIndex = ev.findPointerIndex(i);
                    float y2 = ev.getX(iFindPointerIndex);
                    float x2 = this.e;
                    float f2 = y2 - x2;
                    float fAbs = Math.abs(f2);
                    float y3 = ev.getY(iFindPointerIndex);
                    float xDiff = this.Y;
                    float fAbs2 = Math.abs(y3 - xDiff);
                    if (f2 != 0.0f) {
                        float yDiff2 = this.e;
                        if (!b(yDiff2, f2) && G(this, false, (int) f2, (int) y2, (int) y3)) {
                            this.e = y2;
                            this.f = y3;
                            this.c = true;
                            return false;
                        }
                    }
                    float yDiff3 = this.EB;
                    if (fAbs > yDiff3) {
                        float yDiff4 = 0.5f * fAbs;
                        if (yDiff4 > fAbs2) {
                            this.a = true;
                            m(true);
                            setScrollState(1);
                            if (f2 > 0.0f) {
                                float f3 = this.X;
                                float yDiff5 = this.EB;
                                f = f3 + yDiff5;
                            } else {
                                float f4 = this.X;
                                float yDiff6 = this.EB;
                                f = f4 - yDiff6;
                            }
                            this.e = f;
                            this.f = y3;
                            setScrollingCacheEnabled(true);
                        } else {
                            yDiff = this.EB;
                            if (fAbs2 > yDiff) {
                                this.c = true;
                            }
                        }
                    } else {
                        yDiff = this.EB;
                        if (fAbs2 > yDiff) {
                            this.c = true;
                        }
                    }
                    if (this.a && h(y2)) {
                        C9Y.J(this);
                    }
                }
                break;
            case 6:
                d(ev);
                break;
        }
        if (this.Q == null) {
            this.Q = VelocityTracker.obtain();
        }
        this.Q.addMovement(ev);
        return this.a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        C03649j c03649jV;
        int iMax;
        int vgrav;
        int childCount = getChildCount();
        int childTop = r - l;
        int childWidth = b - t;
        int count = getPaddingLeft();
        int decorCount = getPaddingTop();
        int height = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int hgrav = getScrollX();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int scrollX = childAt.getVisibility();
            if (scrollX != 8) {
                C03659k c03659k = (C03659k) childAt.getLayoutParams();
                if (c03659k.D) {
                    int paddingTop = c03659k.C;
                    int i3 = paddingTop & 7;
                    int paddingTop2 = c03659k.C;
                    int scrollX2 = paddingTop2 & 112;
                    switch (i3) {
                        case 1:
                            int paddingTop3 = childAt.getMeasuredWidth();
                            iMax = Math.max((childTop - paddingTop3) / 2, count);
                            break;
                        case 2:
                        case 4:
                        default:
                            iMax = count;
                            break;
                        case 3:
                            iMax = count;
                            int paddingTop4 = childAt.getMeasuredWidth();
                            count += paddingTop4;
                            break;
                        case 5:
                            int paddingTop5 = childAt.getMeasuredWidth();
                            iMax = (childTop - height) - paddingTop5;
                            int paddingTop6 = childAt.getMeasuredWidth();
                            height += paddingTop6;
                            break;
                    }
                    switch (scrollX2) {
                        case 16:
                            int paddingTop7 = childAt.getMeasuredHeight();
                            vgrav = Math.max((childWidth - paddingTop7) / 2, decorCount);
                            break;
                        case 48:
                            vgrav = decorCount;
                            int paddingTop8 = childAt.getMeasuredHeight();
                            decorCount += paddingTop8;
                            break;
                        case 80:
                            int paddingTop9 = childAt.getMeasuredHeight();
                            vgrav = (childWidth - paddingBottom) - paddingTop9;
                            int paddingTop10 = childAt.getMeasuredHeight();
                            paddingBottom += paddingTop10;
                            break;
                        default:
                            vgrav = decorCount;
                            break;
                    }
                    int i4 = iMax + hgrav;
                    int measuredWidth = childAt.getMeasuredWidth() + i4;
                    int paddingTop11 = childAt.getMeasuredHeight();
                    childAt.layout(i4, vgrav, measuredWidth, paddingTop11 + vgrav);
                    i++;
                }
            }
        }
        int childTop2 = (childTop - count) - height;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt2 = getChildAt(i5);
            if (childAt2.getVisibility() != 8) {
                C03659k c03659k2 = (C03659k) childAt2.getLayoutParams();
                if (!c03659k2.D && (c03649jV = V(childAt2)) != null) {
                    int paddingTop12 = (int) (childTop2 * c03649jV.C);
                    int i6 = count + paddingTop12;
                    if (c03659k2.E) {
                        c03659k2.E = false;
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (childTop2 * c03659k2.G), 1073741824);
                        int widthSpec = View.MeasureSpec.makeMeasureSpec((childWidth - decorCount) - paddingBottom, 1073741824);
                        childAt2.measure(iMakeMeasureSpec, widthSpec);
                    }
                    childAt2.layout(i6, decorCount, childAt2.getMeasuredWidth() + i6, childAt2.getMeasuredHeight() + decorCount);
                }
            }
        }
        this.DB = decorCount;
        this.F = childWidth - paddingBottom;
        this.K = i;
        if (this.S) {
            a(this.C, false, 0, false);
        }
        this.S = false;
    }

    @Override // android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        C03659k c03659k;
        C03659k c03659k2;
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int maxGutterSize = getMeasuredWidth();
        int measuredWidth = maxGutterSize / 10;
        this.V = Math.min(measuredWidth, this.L);
        int maxGutterSize2 = (maxGutterSize - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int measuredWidth2 = childAt.getVisibility();
            if (measuredWidth2 != 8 && (c03659k2 = (C03659k) childAt.getLayoutParams()) != null && c03659k2.D) {
                int i2 = c03659k2.C & 7;
                int hgrav = c03659k2.C;
                int i3 = hgrav & 112;
                int i4 = Integer.MIN_VALUE;
                int i5 = Integer.MIN_VALUE;
                boolean z = i3 == 48 || i3 == 80;
                boolean z2 = i2 == 3 || i2 == 5;
                if (z) {
                    i4 = 1073741824;
                } else if (z2) {
                    i5 = 1073741824;
                }
                int i6 = maxGutterSize2;
                int i7 = measuredHeight;
                int heightSize = c03659k2.width;
                if (heightSize != -2) {
                    i4 = 1073741824;
                    int heightSize2 = c03659k2.width;
                    if (heightSize2 != -1) {
                        i6 = c03659k2.width;
                    }
                }
                int heightSize3 = c03659k2.height;
                if (heightSize3 != -2) {
                    i5 = 1073741824;
                    int heightSize4 = c03659k2.height;
                    if (heightSize4 != -1) {
                        i7 = c03659k2.height;
                    }
                }
                int heightSize5 = View.MeasureSpec.makeMeasureSpec(i6, i4);
                int widthSize = View.MeasureSpec.makeMeasureSpec(i7, i5);
                childAt.measure(heightSize5, widthSize);
                if (z) {
                    int widthSize2 = childAt.getMeasuredHeight();
                    measuredHeight -= widthSize2;
                } else if (z2) {
                    int widthSize3 = childAt.getMeasuredWidth();
                    maxGutterSize2 -= widthSize3;
                }
            }
        }
        this.I = View.MeasureSpec.makeMeasureSpec(maxGutterSize2, 1073741824);
        this.H = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.W = true;
        C();
        this.W = false;
        int childCount2 = getChildCount();
        for (int i8 = 0; i8 < childCount2; i8++) {
            View childAt2 = getChildAt(i8);
            if (childAt2.getVisibility() != 8 && ((c03659k = (C03659k) childAt2.getLayoutParams()) == null || !c03659k.D)) {
                int heightSpec = (int) (maxGutterSize2 * c03659k.G);
                int heightSpec2 = View.MeasureSpec.makeMeasureSpec(heightSpec, 1073741824);
                int widthSize4 = this.H;
                childAt2.measure(heightSpec2, widthSize4);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        int increment;
        int index;
        C03649j ii;
        int i = getChildCount();
        if ((direction & 2) != 0) {
            increment = 0;
            index = 1;
        } else {
            increment = i - 1;
            index = -1;
            i = -1;
        }
        while (increment != i) {
            View childAt = getChildAt(increment);
            if (childAt.getVisibility() == 0 && (ii = V(childAt)) != null && ii.D == this.C && childAt.requestFocus(direction, previouslyFocusedRect)) {
                return true;
            }
            increment += index;
        }
        return false;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof C03709p)) {
            super.onRestoreInstanceState(state);
            return;
        }
        C03709p c03709p = (C03709p) state;
        super.onRestoreInstanceState(c03709p.A());
        if (this.B != null) {
            this.B.K(c03709p.B, c03709p.C);
            Q(c03709p.D, false, true);
        } else {
            this.w = c03709p.D;
            this.u = c03709p.B;
            this.v = c03709p.C;
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        C03709p c03709p = new C03709p(superState);
        c03709p.D = this.C;
        if (this.B != null) {
            Parcelable superState2 = this.B.L();
            c03709p.B = superState2;
        }
        return c03709p;
    }

    @Override // android.view.View
    public final void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            j(w, oldw, this.q, this.q);
        }
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0151  */
    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent ev) {
        float f;
        if (this.R) {
            return true;
        }
        if ((ev.getAction() == 0 && ev.getEdgeFlags() != 0) || this.B == null || this.B.C() == 0) {
            return false;
        }
        if (this.Q == null) {
            this.Q = VelocityTracker.obtain();
        }
        this.Q.addMovement(ev);
        int action = ev.getAction();
        boolean zH = false;
        switch (action & 255) {
            case 0:
                this.z.abortAnimation();
                this.t = false;
                C();
                float x = ev.getX();
                this.X = x;
                this.e = x;
                float y = ev.getY();
                this.Y = y;
                this.f = y;
                this.D = ev.getPointerId(0);
                break;
            case 1:
                if (this.a) {
                    VelocityTracker velocityTracker = this.Q;
                    velocityTracker.computeCurrentVelocity(1000, this.j);
                    int xVelocity = (int) velocityTracker.getXVelocity(this.D);
                    this.t = true;
                    int clientWidth = getClientWidth();
                    int scrollX = getScrollX();
                    C03649j c03649jW = W();
                    float f2 = this.q / clientWidth;
                    int i = c03649jW.D;
                    float f3 = ((scrollX / clientWidth) - c03649jW.C) / (c03649jW.F + f2);
                    float x2 = ev.getX(ev.findPointerIndex(this.D));
                    float pageOffset = this.X;
                    H(J(i, f3, xVelocity, (int) (x2 - pageOffset)), true, true, xVelocity);
                    zH = l();
                }
                break;
            case 2:
                if (!this.a) {
                    int nextPage = this.D;
                    int totalDelta = ev.findPointerIndex(nextPage);
                    if (totalDelta == -1) {
                        zH = l();
                    } else {
                        float x3 = ev.getX(totalDelta);
                        float x4 = this.e;
                        float fAbs = Math.abs(x3 - x4);
                        float y2 = ev.getY(totalDelta);
                        float y3 = this.f;
                        float fAbs2 = Math.abs(y2 - y3);
                        float yDiff = this.EB;
                        if (fAbs > yDiff && fAbs > fAbs2) {
                            this.a = true;
                            m(true);
                            float yDiff2 = this.X;
                            if (x3 - yDiff2 > 0.0f) {
                                float f4 = this.X;
                                float yDiff3 = this.EB;
                                f = f4 + yDiff3;
                            } else {
                                float f5 = this.X;
                                int activePointerIndex = this.EB;
                                f = f5 - activePointerIndex;
                            }
                            this.e = f;
                            this.f = y2;
                            setScrollState(1);
                            setScrollingCacheEnabled(true);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                        if (this.a) {
                            int activePointerIndex2 = ev.findPointerIndex(this.D);
                            zH = false | h(ev.getX(activePointerIndex2));
                        }
                    }
                } else if (this.a) {
                    int activePointerIndex3 = ev.findPointerIndex(this.D);
                    zH = false | h(ev.getX(activePointerIndex3));
                }
                break;
            case 3:
                if (this.a) {
                    a(this.C, true, 0, false);
                    zH = l();
                }
                break;
            case 5:
                int actionIndex = ev.getActionIndex();
                this.e = ev.getX(actionIndex);
                this.D = ev.getPointerId(actionIndex);
                break;
            case 6:
                d(ev);
                this.e = ev.getX(ev.findPointerIndex(this.D));
                break;
        }
        if (zH) {
            C9Y.J(this);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (this.W) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [com.facebook.ads.redexgen.X.9n] */
    public void setAdapter(AbstractC03528x adapter) {
        if (this.B != null) {
            this.B.J(null);
            this.B.E(this);
            for (int i = 0; i < this.d.size(); i++) {
                C03649j c03649j = this.d.get(i);
                this.B.A(this, c03649j.D, c03649j.B);
            }
            this.B.B(this);
            this.d.clear();
            k();
            this.C = 0;
            scrollTo(0, 0);
        }
        AbstractC03528x abstractC03528x = this.B;
        this.B = adapter;
        this.P = 0;
        if (this.B != null) {
            if (this.m == null) {
                this.m = new DataSetObserver() { // from class: com.facebook.ads.redexgen.X.9n
                    @Override // android.database.DataSetObserver
                    public final void onChanged() {
                        this.B.A();
                    }

                    @Override // android.database.DataSetObserver
                    public final void onInvalidated() {
                        this.B.A();
                    }
                };
            }
            this.B.J(this.m);
            this.t = false;
            boolean z = this.S;
            this.S = true;
            this.P = this.B.C();
            if (this.w >= 0) {
                this.B.K(this.u, this.v);
                Q(this.w, false, true);
                this.w = -1;
                this.u = null;
                this.v = null;
            } else if (!z) {
                C();
            } else {
                requestLayout();
            }
        }
        if (this.E != null && !this.E.isEmpty()) {
            int size = this.E.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.E.get(i2).onAdapterChanged(this, abstractC03528x, adapter);
            }
        }
    }

    public void setCurrentItem(int item) {
        this.t = false;
        Q(item, !this.S, false);
    }

    public void setOffscreenPageLimit(int limit) {
        if (limit < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + limit + " too small; defaulting to 1");
            limit = 1;
        }
        if (limit != this.n) {
            this.n = limit;
            C();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(AnonymousClass92 listener) {
        this.o = listener;
    }

    public void setPageMargin(int marginPixels) {
        int i = this.q;
        this.q = marginPixels;
        int width = getWidth();
        j(width, width, marginPixels, i);
        requestLayout();
    }

    public void setPageMarginDrawable(@DrawableRes int resId) {
        setPageMarginDrawable(C02223x.B(getContext(), resId));
    }

    public void setPageMarginDrawable(Drawable d) {
        this.i = d;
        if (d != null) {
            refreshDrawableState();
        }
        setWillNotDraw(d == null);
        invalidate();
    }

    public void setScrollState(int newState) {
        if (this.y == newState) {
            return;
        }
        this.y = newState;
        if (this.r != null) {
            O(newState != 0);
        }
        M(newState);
    }

    private void setScrollingCacheEnabled(boolean enabled) {
        if (this.AB != enabled) {
            this.AB = enabled;
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.i;
    }
}
