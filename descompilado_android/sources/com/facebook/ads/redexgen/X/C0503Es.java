package com.facebook.ads.redexgen.X;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Es, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0503Es extends ViewGroup implements AnonymousClass98, InterfaceC03468r {
    public static final boolean TB;
    private static final boolean UB;
    private static final boolean WB;
    public static final boolean XB;
    private static final boolean YB;
    private static final Class<?>[] ZB;
    public static final boolean bB;
    public static final Interpolator cB;
    private E9 AB;
    public C0505Eu B;
    private final ArrayList<InterfaceC0470Dl> BB;
    public C1G C;
    private C0495Ek CB;
    public DK D;
    private boolean DB;
    public boolean E;
    private EdgeEffect EB;
    public DN F;
    private float FB;
    public boolean G;
    private float GB;
    public boolean H;
    private final int[] HB;
    public final FC I;
    private AbstractC0463De IB;

    @VisibleForTesting
    public boolean J;
    private List<AbstractC0463De> JB;
    public RunnableC0476Dr K;
    private final int[] KB;
    public boolean L;
    private int LB;
    public boolean M;
    private int MB;
    public DY N;
    private C03478s NB;
    public final ArrayList<AbstractC0460Db> O;
    private final Rect OB;
    public boolean P;
    private EdgeEffect PB;
    public boolean Q;
    private int QB;

    @VisibleForTesting
    public AbstractC0481Dw R;
    private VelocityTracker RB;
    public boolean S;
    private final EN SB;
    public boolean T;

    @VisibleForTesting
    public final List<AbstractC0502Er> U;
    public boolean V;
    public C0474Dp W;
    public final C0491Eg X;
    public InterfaceC0492Eh Y;
    public final C0499Eo Z;
    public final Rect a;
    public final RectF b;
    public final Runnable c;
    public RunnableC0501Eq d;
    private final AccessibilityManager e;
    private InterfaceC0470Dl f;
    private EdgeEffect g;
    private ET h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private int m;
    private int n;
    private EW o;
    private Runnable p;
    private int q;
    private int r;
    private int s;
    private EdgeEffect t;
    private final int u;
    private final int v;
    private final int[] w;
    private final int[] x;
    private final C0493Ei y;
    private List<InterfaceC0488Ed> z;
    private static final int[] aB = {R.attr.nestedScrollingEnabled};
    private static final int[] VB = {R.attr.clipToPadding};

    static {
        XB = Build.VERSION.SDK_INT == 18 || Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20;
        TB = Build.VERSION.SDK_INT >= 23;
        bB = Build.VERSION.SDK_INT >= 16;
        UB = Build.VERSION.SDK_INT >= 21;
        WB = Build.VERSION.SDK_INT <= 15;
        YB = Build.VERSION.SDK_INT <= 15;
        ZB = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
        cB = new EM();
    }

    public C0503Es(Context context) {
        this(context, null);
    }

    public C0503Es(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public C0503Es(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.y = new C0493Ei(this);
        this.X = new C0491Eg(this);
        this.I = new FC();
        this.c = new EK(this);
        this.a = new Rect();
        this.OB = new Rect();
        this.b = new RectF();
        this.O = new ArrayList<>();
        this.BB = new ArrayList<>();
        this.j = 0;
        this.H = false;
        this.s = 0;
        this.i = 0;
        this.N = new C0459Da();
        this.MB = 0;
        this.LB = -1;
        this.FB = Float.MIN_VALUE;
        this.GB = Float.MIN_VALUE;
        this.DB = true;
        this.d = new RunnableC0501Eq(this);
        this.W = UB ? new C0474Dp() : null;
        this.Z = new C0499Eo();
        this.P = false;
        this.Q = false;
        this.o = new EY(this);
        this.V = false;
        this.w = new int[2];
        this.KB = new int[2];
        this.HB = new int[2];
        this.x = new int[2];
        this.U = new ArrayList();
        this.p = new EL(this);
        this.SB = new EO(this);
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, VB, defStyle, 0);
            this.G = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
        } else {
            this.G = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.QB = viewConfiguration.getScaledTouchSlop();
        this.FB = C9Z.B(viewConfiguration, context);
        this.GB = C9Z.C(viewConfiguration, context);
        this.v = viewConfiguration.getScaledMinimumFlingVelocity();
        this.u = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.N.V(this.o);
        GB();
        HB();
        if (C9Y.S(this) == 0) {
            C9Y.D(this, 1);
        }
        this.e = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new C0505Eu(this));
        setDescendantFocusability(262144);
        setNestedScrollingEnabled(true);
    }

    public final void A(int velocityX, int velocityY) {
        if (velocityX < 0) {
            l();
            this.t.onAbsorb(-velocityX);
        } else if (velocityX > 0) {
            m();
            this.EB.onAbsorb(velocityX);
        }
        if (velocityY < 0) {
            n();
            this.PB.onAbsorb(-velocityY);
        } else if (velocityY > 0) {
            k();
            this.g.onAbsorb(velocityY);
        }
        if (velocityX != 0 || velocityY != 0) {
            C9Y.J(this);
        }
    }

    public final void AA() {
        int i = this.F.G();
        for (int left = 0; left < i; left++) {
            View viewF = this.F.F(left);
            AbstractC0502Er abstractC0502ErZ = Z(viewF);
            if (abstractC0502ErZ != null) {
                AbstractC0502Er holder = abstractC0502ErZ.M;
                if (holder != null) {
                    AbstractC0502Er holder2 = abstractC0502ErZ.M;
                    View view = holder2.B;
                    int left2 = viewF.getLeft();
                    int top = viewF.getTop();
                    if (left2 != view.getLeft() || top != view.getTop()) {
                        view.layout(left2, top, view.getWidth() + left2, view.getHeight() + top);
                    }
                }
            }
        }
    }

    private int AB(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    public final void B(int positionStart, int itemCount, Object payload) {
        int I = this.F.I();
        int i = positionStart + itemCount;
        for (int i2 = 0; i2 < I; i2++) {
            View viewH = this.F.H(i2);
            AbstractC0502Er abstractC0502ErQ = Q(viewH);
            if (abstractC0502ErQ != null && !abstractC0502ErQ.f()) {
                int positionEnd = abstractC0502ErQ.J;
                if (positionEnd >= positionStart) {
                    int positionEnd2 = abstractC0502ErQ.J;
                    if (positionEnd2 < i) {
                        abstractC0502ErQ.B(2);
                        abstractC0502ErQ.A(payload);
                        ((C0479Du) viewH.getLayoutParams()).C = true;
                    }
                }
            }
        }
        this.X.E(positionStart, itemCount);
    }

    public final void BA(boolean performLayoutChildren) {
        if (this.j < 1) {
            this.j = 1;
        }
        if (!performLayoutChildren) {
            this.T = false;
        }
        if (this.j == 1) {
            if (performLayoutChildren && this.T && !this.S && this.R != null && this.C != null) {
                c();
            }
            if (!this.S) {
                this.T = false;
            }
        }
        this.j--;
    }

    private void BB(@NonNull View child, @Nullable View focused) {
        View view = focused != null ? focused : child;
        this.a.set(0, 0, view.getWidth(), view.getHeight());
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof C0479Du) {
            C0479Du c0479Du = (C0479Du) layoutParams;
            if (!c0479Du.C) {
                Rect rect = c0479Du.B;
                this.a.left -= rect.left;
                this.a.right += rect.right;
                this.a.top -= rect.top;
                this.a.bottom += rect.bottom;
            }
        }
        if (focused != null) {
            offsetDescendantRectToMyCoords(focused, this.a);
            offsetRectIntoDescendantCoords(child, this.a);
        }
        this.R.nA(this, child, this.a, !this.J, focused == null);
    }

    public final void C() {
        setScrollState(0);
        T();
    }

    public final void CA(int position) {
        if (this.S) {
            return;
        }
        C();
        if (this.R == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            this.R.rA(position);
            awakenScrollBars();
        }
    }

    private void CB() {
        boolean zIsFinished = false;
        if (this.t != null) {
            this.t.onRelease();
            zIsFinished = this.t.isFinished();
        }
        if (this.PB != null) {
            this.PB.onRelease();
            zIsFinished |= this.PB.isFinished();
        }
        if (this.EB != null) {
            this.EB.onRelease();
            zIsFinished |= this.EB.isFinished();
        }
        if (this.g != null) {
            this.g.onRelease();
            zIsFinished |= this.g.isFinished();
        }
        if (zIsFinished) {
            C9Y.J(this);
        }
    }

    public final void D(int type) {
        getScrollingChildHelper().N(type);
    }

    @VisibleForTesting
    public final boolean DA(AbstractC0502Er viewHolder, int importantForAccessibility) {
        if (m250e()) {
            viewHolder.I = importantForAccessibility;
            this.U.add(viewHolder);
            return false;
        }
        C9Y.D(viewHolder.B, importantForAccessibility);
        return true;
    }

    private void DB(long key, AbstractC0502Er holder, AbstractC0502Er oldChangeViewHolder) {
        int iG = this.F.G();
        for (int i = 0; i < iG; i++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.F(i));
            if (abstractC0502ErQ != holder && w(abstractC0502ErQ) == key) {
                if (this.C != null && this.C.F()) {
                    throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + abstractC0502ErQ + " \n View Holder 2:" + holder + S());
                }
                throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + abstractC0502ErQ + " \n View Holder 2:" + holder + S());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + oldChangeViewHolder + " cannot be found but it is necessary for " + holder + S());
    }

    public final void E(AbstractC0463De listener) {
        if (this.JB == null) {
            this.JB = new ArrayList();
        }
        this.JB.add(listener);
    }

    public final void EA() {
        this.H = true;
        QB();
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0083  */
    /* JADX WARN: Code duplicated, block: B:41:0x008b  */
    private void EB() {
        View viewFindViewById;
        if (this.DB && this.C != null && hasFocus() && getDescendantFocusability() != 393216) {
            if (getDescendantFocusability() == 131072 && isFocused()) {
                return;
            }
            if (!isFocused()) {
                View focusedChild = getFocusedChild();
                if (YB && (focusedChild.getParent() == null || !focusedChild.hasFocus())) {
                    if (this.F.G() == 0) {
                        requestFocus();
                        return;
                    }
                } else if (!this.F.L(focusedChild)) {
                    return;
                }
            }
            AbstractC0502Er abstractC0502ErS = null;
            if (this.Z.C != -1 && this.C.F()) {
                abstractC0502ErS = s(this.Z.C);
            }
            View viewQ = null;
            if (abstractC0502ErS != null) {
                DN dn = this.F;
                View viewToFocus = abstractC0502ErS.B;
                if (!dn.L(viewToFocus)) {
                    View viewToFocus2 = abstractC0502ErS.B;
                    if (!viewToFocus2.hasFocusable()) {
                        if (this.F.G() > 0) {
                            viewQ = q();
                        }
                    } else {
                        viewQ = abstractC0502ErS.B;
                    }
                } else if (this.F.G() > 0) {
                    viewQ = q();
                }
            } else if (this.F.G() > 0) {
                viewQ = q();
            }
            if (viewQ == null) {
                return;
            }
            if (this.Z.E != -1 && (viewFindViewById = viewQ.findViewById(this.Z.E)) != null && viewFindViewById.isFocusable()) {
                viewQ = viewFindViewById;
            }
            viewQ.requestFocus();
        }
    }

    public final void F(@NonNull AbstractC0502Er itemHolder, @Nullable EX preLayoutInfo, @NonNull EX postLayoutInfo) {
        itemHolder.d(false);
        if (this.N.A(itemHolder, preLayoutInfo, postLayoutInfo)) {
            r();
        }
    }

    private boolean FB() {
        int i = this.F.G();
        for (int i2 = 0; i2 < i; i2++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.F(i2));
            if (abstractC0502ErQ != null && !abstractC0502ErQ.f() && abstractC0502ErQ.X()) {
                return true;
            }
        }
        return false;
    }

    public final void G(@NonNull AbstractC0502Er holder, @NonNull EX preLayoutInfo, @Nullable EX postLayoutInfo) {
        S(holder);
        holder.d(false);
        if (this.N.C(holder, preLayoutInfo, postLayoutInfo)) {
            r();
        }
    }

    private final void GB() {
        this.D = new DK(new EQ(this));
    }

    public final void H(String message) {
        if (m250e()) {
            if (message == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + S());
            }
            throw new IllegalStateException(message);
        }
        if (this.i > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + S()));
        }
    }

    private void HB() {
        this.F = new DN(new EP(this));
    }

    public final boolean I(AbstractC0502Er viewHolder) {
        return this.N == null || this.N.F(viewHolder, viewHolder.m247N());
    }

    private void IB(float x, float overscrollX, float y, float overscrollY) {
        boolean z = false;
        if (overscrollX < 0.0f) {
            l();
            BX.B(this.t, (-overscrollX) / getWidth(), 1.0f - (y / getHeight()));
            z = true;
        } else if (overscrollX > 0.0f) {
            m();
            BX.B(this.EB, overscrollX / getWidth(), y / getHeight());
            z = true;
        }
        if (overscrollY < 0.0f) {
            n();
            BX.B(this.PB, (-overscrollY) / getHeight(), x / getWidth());
            z = true;
        } else if (overscrollY > 0.0f) {
            k();
            BX.B(this.g, overscrollY / getHeight(), 1.0f - (x / getWidth()));
            z = true;
        }
        if (z || overscrollX != 0.0f || overscrollY != 0.0f) {
            C9Y.J(this);
        }
    }

    public final void J(int dx, int dy) {
        boolean zIsFinished = false;
        if (this.t != null && !this.t.isFinished() && dx > 0) {
            this.t.onRelease();
            zIsFinished = this.t.isFinished();
        }
        if (this.EB != null && !this.EB.isFinished() && dx < 0) {
            this.EB.onRelease();
            zIsFinished |= this.EB.isFinished();
        }
        if (this.PB != null && !this.PB.isFinished() && dy > 0) {
            this.PB.onRelease();
            zIsFinished |= this.PB.isFinished();
        }
        if (this.g != null && !this.g.isFinished() && dy < 0) {
            this.g.onRelease();
            zIsFinished |= this.g.isFinished();
        }
        if (zIsFinished) {
            C9Y.J(this);
        }
    }

    private final void JB() {
        this.g = null;
        this.PB = null;
        this.EB = null;
        this.t = null;
    }

    public final void K() {
        if (!this.J || this.H) {
            C02946r.B("RV FullInvalidate");
            c();
            C02946r.C();
            return;
        }
        if (!this.D.F()) {
            return;
        }
        if (this.D.E(4) && !this.D.E(11)) {
            C02946r.B("RV PartialInvalidate");
            R();
            o();
            this.D.I();
            if (!this.T) {
                if (FB()) {
                    c();
                } else {
                    this.D.B();
                }
            }
            BA(true);
            p();
            C02946r.C();
            return;
        }
        if (!this.D.F()) {
            return;
        }
        C02946r.B("RV FullInvalidate");
        c();
        C02946r.C();
    }

    private void KB() {
        if (this.H) {
            this.D.H();
            this.R.UA(this);
        }
        if (LB()) {
            this.D.I();
        } else {
            this.D.C();
        }
        boolean z = this.P || this.Q;
        this.Z.N = this.J && this.N != null && (this.H || z || this.R.I) && (!this.H || this.C.F());
        this.Z.M = this.Z.N && z && !this.H && LB();
    }

    public final void L(int widthSpec, int heightSpec) {
        setMeasuredDimension(AbstractC0481Dw.C(widthSpec, getPaddingLeft() + getPaddingRight(), C9Y.P(this)), AbstractC0481Dw.C(heightSpec, getPaddingTop() + getPaddingBottom(), C9Y.Q(this)));
    }

    private boolean LB() {
        return this.N != null && this.R.F();
    }

    public final void M(View child) {
        AbstractC0502Er viewHolder = Q(child);
        RB(child);
        if (this.C != null && viewHolder != null) {
            this.C.K(viewHolder);
        }
        if (this.z != null) {
            for (int size = this.z.size() - 1; size >= 0; size--) {
                this.z.get(size).fD(child);
            }
        }
    }

    private final void MB(int dx, int dy) {
    }

    public final void N(View child) {
        AbstractC0502Er viewHolder = Q(child);
        SB(child);
        if (this.C != null && viewHolder != null) {
            this.C.J(viewHolder);
        }
        if (this.z != null) {
            for (int size = this.z.size() - 1; size >= 0; size--) {
                this.z.get(size).gD(child);
            }
        }
    }

    private boolean NB(View focused, View next, int direction) {
        if (next == null || next == this) {
            return false;
        }
        if (focused == null) {
            return true;
        }
        if (direction == 2 || direction == 1) {
            if (OB(focused, next, (direction == 2) ^ (this.R.p() == 1) ? 66 : 17)) {
                return true;
            }
            if (direction == 2) {
                return OB(focused, next, TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
            }
            return OB(focused, next, 33);
        }
        return OB(focused, next, direction);
    }

    public static void O(@NonNull AbstractC0502Er holder) {
        if (holder.E != null) {
            C0503Es c0503Es = holder.E.get();
            while (c0503Es != null) {
                if (c0503Es == holder.B) {
                    return;
                }
                Object parent = c0503Es.getParent();
                if (parent instanceof View) {
                    c0503Es = (View) parent;
                } else {
                    c0503Es = null;
                }
            }
            holder.E = null;
        }
    }

    public final boolean O(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return getScrollingChildHelper().D(dx, dy, consumed, offsetInWindow, type);
    }

    private boolean OB(View focused, View next, int direction) {
        this.a.set(0, 0, focused.getWidth(), focused.getHeight());
        this.OB.set(0, 0, next.getWidth(), next.getHeight());
        offsetDescendantRectToMyCoords(focused, this.a);
        offsetDescendantRectToMyCoords(next, this.OB);
        switch (direction) {
            case 17:
                return (this.a.right > this.OB.right || this.a.left >= this.OB.right) && this.a.left > this.OB.left;
            case 33:
                return (this.a.bottom > this.OB.bottom || this.a.top >= this.OB.bottom) && this.a.top > this.OB.top;
            case 66:
                return (this.a.left < this.OB.left || this.a.right <= this.OB.left) && this.a.right < this.OB.right;
            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                return (this.a.top < this.OB.top || this.a.bottom <= this.OB.top) && this.a.bottom < this.OB.bottom;
            default:
                throw new IllegalArgumentException("direction must be absolute. received:" + direction + S());
        }
    }

    @Nullable
    public static C0503Es P(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof C0503Es) {
            return (C0503Es) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            C0503Es descendant = P(viewGroup.getChildAt(i));
            if (descendant != null) {
                return descendant;
            }
        }
        return null;
    }

    public final boolean P(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return getScrollingChildHelper().F(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    private final void PB() {
        int i = this.F.I();
        for (int i2 = 0; i2 < i; i2++) {
            ((C0479Du) this.F.H(i2).getLayoutParams()).C = true;
        }
        this.X.L();
    }

    public static AbstractC0502Er Q(View child) {
        if (child == null) {
            return null;
        }
        return ((C0479Du) child.getLayoutParams()).E;
    }

    public final void Q(int hresult, int vresult) {
        this.i++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        MB(hresult, vresult);
        if (this.IB != null) {
            this.IB.B(this, hresult, vresult);
        }
        if (this.JB != null) {
            for (int size = this.JB.size() - 1; size >= 0; size--) {
                this.JB.get(size).B(this, hresult, vresult);
            }
        }
        this.i--;
    }

    private final void QB() {
        int i = this.F.I();
        for (int i2 = 0; i2 < i; i2++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i2));
            if (abstractC0502ErQ != null && !abstractC0502ErQ.f()) {
                abstractC0502ErQ.B(6);
            }
        }
        PB();
        this.X.M();
    }

    public final void R() {
        this.j++;
        if (this.j == 1 && !this.S) {
            this.T = false;
        }
    }

    public static void R(View view, Rect outBounds) {
        C0479Du c0479Du = (C0479Du) view.getLayoutParams();
        Rect rect = c0479Du.B;
        outBounds.set((view.getLeft() - rect.left) - c0479Du.leftMargin, (view.getTop() - rect.top) - c0479Du.topMargin, view.getRight() + rect.right + c0479Du.rightMargin, view.getBottom() + rect.bottom + c0479Du.bottomMargin);
    }

    private final void RB(View child) {
    }

    public final String S() {
        return " " + super.toString() + ", adapter:" + this.C + ", layout:" + this.R + ", context:" + getContext();
    }

    private void S(AbstractC0502Er viewHolder) {
        View view = viewHolder.B;
        boolean z = view.getParent() == this;
        this.X.Z(Z(view));
        if (viewHolder.W()) {
            this.F.C(view, -1, view.getLayoutParams(), true);
        } else if (!z) {
            this.F.B(view, true);
        } else {
            this.F.J(view);
        }
    }

    private final void SB(View child) {
    }

    private void T() {
        this.d.G();
        if (this.R != null) {
            this.R.U();
        }
    }

    public final void T(C0499Eo state) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.d.C;
            state.K = overScroller.getFinalX() - overScroller.getCurrX();
            state.L = overScroller.getFinalY() - overScroller.getCurrY();
        } else {
            state.K = 0;
            state.L = 0;
        }
    }

    private void TB(MotionEvent e) {
        int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) != this.LB) {
            return;
        }
        int i = actionIndex == 0 ? 1 : 0;
        this.LB = e.getPointerId(i);
        int x = (int) (e.getX(i) + 0.5f);
        this.q = x;
        this.m = x;
        int y = (int) (e.getY(i) + 0.5f);
        this.r = y;
        this.n = y;
    }

    private void U(@NonNull AbstractC0502Er oldHolder, @NonNull AbstractC0502Er newHolder, @NonNull EX preInfo, @NonNull EX postInfo, boolean oldHolderDisappearing, boolean newHolderDisappearing) {
        oldHolder.d(false);
        if (oldHolderDisappearing) {
            S(oldHolder);
        }
        if (oldHolder != newHolder) {
            if (newHolderDisappearing) {
                S(newHolder);
            }
            oldHolder.L = newHolder;
            S(oldHolder);
            this.X.Z(oldHolder);
            newHolder.d(false);
            newHolder.M = oldHolder;
        }
        if (this.N.B(oldHolder, newHolder, preInfo, postInfo)) {
            r();
        }
    }

    public final boolean U(int axes, int type) {
        return getScrollingChildHelper().L(axes, type);
    }

    private final void UB(int state) {
    }

    @Nullable
    public final View V(View view) {
        ViewParent view2 = view.getParent();
        while (view2 != null && view2 != this && (view2 instanceof View)) {
            view = view2;
            view2 = view.getParent();
        }
        if (view2 == this) {
            return view;
        }
        return null;
    }

    private void V() {
        y();
        setScrollState(0);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002b  */
    /* JADX WARN: Code duplicated, block: B:16:0x0035  */
    /* JADX WARN: Code duplicated, block: B:20:0x0038 A[SYNTHETIC] */
    public final AbstractC0502Er W(int position, boolean checkNewPosition) {
        int I = this.F.I();
        AbstractC0502Er abstractC0502Er = null;
        for (int i = 0; i < I; i++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i));
            if (abstractC0502ErQ != null && !abstractC0502ErQ.U()) {
                if (checkNewPosition) {
                    int i2 = abstractC0502ErQ.J;
                    if (i2 != position) {
                        continue;
                    } else if (this.F.L(abstractC0502ErQ.B)) {
                        abstractC0502Er = abstractC0502ErQ;
                    } else {
                        return abstractC0502ErQ;
                    }
                } else {
                    int i3 = abstractC0502ErQ.K();
                    if (i3 != position) {
                        continue;
                    } else if (this.F.L(abstractC0502ErQ.B)) {
                        abstractC0502Er = abstractC0502ErQ;
                    } else {
                        return abstractC0502ErQ;
                    }
                }
            }
        }
        return abstractC0502Er;
    }

    private final void W() {
        int i = this.F.I();
        for (int i2 = 0; i2 < i; i2++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i2));
            if (!abstractC0502ErQ.f()) {
                abstractC0502ErQ.C();
            }
        }
        this.X.C();
    }

    public final int X(AbstractC0502Er viewHolder) {
        if (viewHolder.O(IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT) || !viewHolder.Q()) {
            return -1;
        }
        return this.D.A(viewHolder.J);
    }

    private final void X(int dx, int dy, Interpolator interpolator) {
        if (this.R == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.S) {
            return;
        }
        if (!this.R.G()) {
            dx = 0;
        }
        if (!this.R.H()) {
            dy = 0;
        }
        if (dx == 0 && dy == 0) {
            return;
        }
        this.d.F(dx, dy, interpolator);
    }

    public final int Y(View child) {
        AbstractC0502Er abstractC0502ErQ = Q(child);
        if (abstractC0502ErQ != null) {
            return abstractC0502ErQ.K();
        }
        return -1;
    }

    private final boolean Y(AccessibilityEvent event) {
        if (!m250e()) {
            return false;
        }
        int iC = 0;
        if (event != null) {
            iC = A9.C(event);
        }
        if (iC == 0) {
            iC = 0;
        }
        this.k |= iC;
        return true;
    }

    public final AbstractC0502Er Z(View child) {
        ViewParent parent = child.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + child + " is not a direct child of " + this);
        }
        return Q(child);
    }

    private void Z(C1G adapter, boolean compatibleWithPrevious, boolean removeAndRecycleViews) {
        if (this.C != null) {
            this.C.G(this.y);
            this.C.O(this);
        }
        if (!compatibleWithPrevious || removeAndRecycleViews) {
            t();
        }
        this.D.H();
        C1G c1g = this.C;
        this.C = adapter;
        if (adapter != null) {
            adapter.H(this.y);
            adapter.L(this);
        }
        if (this.R != null) {
            this.R.KA(c1g, this.C);
        }
        this.X.Q(c1g, this.C, compatibleWithPrevious);
        this.Z.O = true;
        EA();
    }

    public final Rect a(View child) {
        C0479Du lp = (C0479Du) child.getLayoutParams();
        if (!lp.C) {
            return lp.B;
        }
        if (this.Z.E() && (lp.B() || lp.D())) {
            return lp.B;
        }
        Rect rect = lp.B;
        rect.set(0, 0, 0, 0);
        int size = this.O.size();
        for (int i = 0; i < size; i++) {
            this.a.set(0, 0, 0, 0);
            this.O.get(i).A(this.a, child, this, this.Z);
            rect.left += this.a.left;
            rect.top += this.a.top;
            rect.right += this.a.right;
            rect.bottom += this.a.bottom;
        }
        lp.C = false;
        return rect;
    }

    private boolean a(int minPositionPreLayout, int maxPositionPreLayout) {
        p(this.w);
        return (this.w[0] == minPositionPreLayout && this.w[1] == maxPositionPreLayout) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList<View> arrayList, int direction, int focusableMode) {
        if (this.R == null || !this.R.LA(this, arrayList, direction, focusableMode)) {
            super.addFocusables(arrayList, direction, focusableMode);
        }
    }

    private void b() {
        int flags = this.k;
        this.k = 0;
        if (flags != 0 && m249d()) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
            accessibilityEventObtain.setEventType(2048);
            A9.B(accessibilityEventObtain, flags);
            sendAccessibilityEventUnchecked(accessibilityEventObtain);
        }
    }

    public final boolean b(int type) {
        return getScrollingChildHelper().H(type);
    }

    private final void c() {
        if (this.C == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.R == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        this.Z.G = false;
        if (this.Z.I == 1) {
            d();
            this.R.uA(this);
            e();
        } else if (this.D.G() || this.R.z() != getWidth() || this.R.m() != getHeight()) {
            this.R.uA(this);
            e();
        } else {
            this.R.uA(this);
        }
        f();
    }

    /* JADX INFO: renamed from: c, reason: collision with other method in class */
    public final boolean m248c() {
        return !this.J || this.H || this.D.F();
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof C0479Du) && this.R.I((C0479Du) p);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        if (this.R != null && this.R.G()) {
            return this.R.L(this.Z);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        if (this.R != null && this.R.G()) {
            return this.R.M(this.Z);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        if (this.R != null && this.R.G()) {
            return this.R.N(this.Z);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        if (this.R != null && this.R.H()) {
            return this.R.O(this.Z);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        if (this.R != null && this.R.H()) {
            return this.R.P(this.Z);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        if (this.R != null && this.R.H()) {
            return this.R.Q(this.Z);
        }
        return 0;
    }

    private void d() {
        this.Z.A(1);
        T(this.Z);
        this.Z.G = false;
        R();
        this.I.F();
        o();
        KB();
        x();
        this.Z.P = this.Z.N && this.Q;
        this.Q = false;
        this.P = false;
        this.Z.F = this.Z.M;
        this.Z.H = this.C.C();
        p(this.w);
        if (this.Z.N) {
            int iG = this.F.G();
            for (int i = 0; i < iG; i++) {
                AbstractC0502Er abstractC0502ErQ = Q(this.F.F(i));
                if (!abstractC0502ErQ.f() && (!abstractC0502ErQ.R() || this.C.F())) {
                    this.I.E(abstractC0502ErQ, this.N.T(this.Z, abstractC0502ErQ, DY.B(abstractC0502ErQ), abstractC0502ErQ.m247N()));
                    if (this.Z.P && abstractC0502ErQ.X() && !abstractC0502ErQ.U() && !abstractC0502ErQ.f() && !abstractC0502ErQ.R()) {
                        this.I.C(w(abstractC0502ErQ), abstractC0502ErQ);
                    }
                }
            }
        }
        if (this.Z.M) {
            u();
            boolean z = this.Z.O;
            this.Z.O = false;
            this.R.YA(this.X, this.Z);
            this.Z.O = z;
            for (int i2 = 0; i2 < this.F.G(); i2++) {
                AbstractC0502Er abstractC0502ErQ2 = Q(this.F.F(i2));
                boolean didStructureChange = abstractC0502ErQ2.f();
                if (!didStructureChange) {
                    boolean didStructureChange2 = this.I.I(abstractC0502ErQ2);
                    if (!didStructureChange2) {
                        int iB = DY.B(abstractC0502ErQ2);
                        boolean zO = abstractC0502ErQ2.O(8192);
                        if (!zO) {
                            iB |= 4096;
                        }
                        EX exT = this.N.T(this.Z, abstractC0502ErQ2, iB, abstractC0502ErQ2.m247N());
                        if (zO) {
                            s(abstractC0502ErQ2, exT);
                        } else {
                            this.I.A(abstractC0502ErQ2, exT);
                        }
                    }
                }
            }
            W();
        } else {
            W();
        }
        p();
        BA(false);
        this.Z.I = 2;
    }

    /* JADX INFO: renamed from: d, reason: collision with other method in class */
    public final boolean m249d() {
        return this.e != null && this.e.isEnabled();
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return getScrollingChildHelper().A(velocityX, velocityY, consumed);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return getScrollingChildHelper().B(velocityX, velocityY);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return getScrollingChildHelper().C(dx, dy, consumed, offsetInWindow);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return getScrollingChildHelper().E(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override // android.view.View
    public final void draw(Canvas c) {
        super.draw(c);
        int size = this.O.size();
        for (int i = 0; i < size; i++) {
            this.O.get(i).C(c, this, this.Z);
        }
        boolean z = false;
        if (this.t != null && !this.t.isFinished()) {
            int iSave = c.save();
            int paddingBottom = this.G ? getPaddingBottom() : 0;
            c.rotate(270.0f);
            int restore = getHeight();
            c.translate((-restore) + paddingBottom, 0.0f);
            z = this.t != null && this.t.draw(c);
            c.restoreToCount(iSave);
        }
        if (this.PB != null && !this.PB.isFinished()) {
            int iSave2 = c.save();
            if (this.G) {
                int restore2 = getPaddingLeft();
                float f = restore2;
                int restore3 = getPaddingTop();
                c.translate(f, restore3);
            }
            z |= this.PB != null && this.PB.draw(c);
            c.restoreToCount(iSave2);
        }
        if (this.EB != null && !this.EB.isFinished()) {
            int width = c.save();
            int width2 = getWidth();
            int paddingTop = this.G ? getPaddingTop() : 0;
            c.rotate(90.0f);
            int restore4 = -paddingTop;
            float f2 = restore4;
            int restore5 = -width2;
            c.translate(f2, restore5);
            z |= this.EB != null && this.EB.draw(c);
            c.restoreToCount(width);
        }
        if (this.g != null && !this.g.isFinished()) {
            int iSave3 = c.save();
            c.rotate(180.0f);
            if (this.G) {
                int restore6 = getWidth();
                int i2 = -restore6;
                int restore7 = getPaddingRight();
                float f3 = i2 + restore7;
                int restore8 = getHeight();
                int i3 = -restore8;
                int restore9 = getPaddingBottom();
                c.translate(f3, i3 + restore9);
            } else {
                c.translate(-getWidth(), -getHeight());
            }
            boolean needsInvalidate = this.g != null && this.g.draw(c);
            z |= needsInvalidate;
            c.restoreToCount(iSave3);
        }
        if (!z && this.N != null && this.O.size() > 0 && this.N.Q()) {
            z = true;
        }
        if (z) {
            C9Y.J(this);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    private void e() {
        R();
        o();
        this.Z.A(6);
        this.D.C();
        this.Z.H = this.C.C();
        this.Z.B = 0;
        this.Z.F = false;
        this.R.YA(this.X, this.Z);
        this.Z.O = false;
        this.CB = null;
        this.Z.N = this.Z.N && this.N != null;
        this.Z.I = 4;
        p();
        BA(false);
    }

    /* JADX INFO: renamed from: e, reason: collision with other method in class */
    public final boolean m250e() {
        return this.s > 0;
    }

    private void f() {
        this.Z.A(4);
        R();
        o();
        this.Z.I = 1;
        if (this.Z.N) {
            for (int iG = this.F.G() - 1; iG >= 0; iG--) {
                AbstractC0502Er abstractC0502ErQ = Q(this.F.F(iG));
                if (!abstractC0502ErQ.f()) {
                    long jW = w(abstractC0502ErQ);
                    EX exS = this.N.S(this.Z, abstractC0502ErQ);
                    AbstractC0502Er abstractC0502ErG = this.I.G(jW);
                    if (abstractC0502ErG != null && !abstractC0502ErG.f()) {
                        boolean zH = this.I.H(abstractC0502ErG);
                        boolean zH2 = this.I.H(abstractC0502ErQ);
                        if (zH && abstractC0502ErG == abstractC0502ErQ) {
                            this.I.D(abstractC0502ErQ, exS);
                        } else {
                            EX exM = this.I.M(abstractC0502ErG);
                            this.I.D(abstractC0502ErQ, exS);
                            EX exL = this.I.L(abstractC0502ErQ);
                            if (exM == null) {
                                DB(jW, abstractC0502ErQ, abstractC0502ErG);
                            } else {
                                U(abstractC0502ErG, abstractC0502ErQ, exM, exL, zH, zH2);
                            }
                        }
                    } else {
                        this.I.D(abstractC0502ErQ, exS);
                    }
                }
            }
            this.I.N(this.SB);
        }
        this.R.iA(this.X);
        this.Z.J = this.Z.H;
        this.H = false;
        this.Z.N = false;
        this.Z.M = false;
        this.R.I = false;
        if (this.X.D != null) {
            this.X.D.clear();
        }
        if (this.R.G) {
            this.R.F = 0;
            this.R.G = false;
            this.X.a();
        }
        this.R.ZA(this.Z);
        p();
        BA(false);
        this.I.F();
        if (a(this.w[0], this.w[1])) {
            Q(0, 0);
        }
        EB();
        z();
    }

    public final void f(int position) {
        if (this.R == null) {
            return;
        }
        this.R.rA(position);
        awakenScrollBars();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View focused, int direction) {
        View focused2;
        View focused3 = this.R.SA(focused, direction);
        if (focused3 != null) {
            return focused3;
        }
        boolean rtl = (this.C == null || this.R == null || m250e() || this.S) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (rtl && (direction == 2 || direction == 1)) {
            boolean z = false;
            if (this.R.H()) {
                int i = direction == 2 ? TsExtractor.TS_STREAM_TYPE_HDMV_DTS : 33;
                z = focusFinder.findNextFocus(this, focused, i) == null;
                if (WB) {
                    direction = i;
                }
            }
            if (!z && this.R.G()) {
                int i2 = (direction == 2) ^ (this.R.p() == 1) ? 66 : 17;
                z = focusFinder.findNextFocus(this, focused, i2) == null;
                if (WB) {
                    direction = i2;
                }
            }
            if (z) {
                K();
                if (V(focused) == null) {
                    return null;
                }
                R();
                this.R.NA(focused, direction, this.X, this.Z);
                BA(false);
            }
            focused2 = focusFinder.findNextFocus(this, focused, direction);
        } else {
            focused2 = focusFinder.findNextFocus(this, focused, direction);
            if (focused2 == null && rtl) {
                K();
                if (V(focused) == null) {
                    return null;
                }
                R();
                focused2 = this.R.NA(focused, direction, this.X, this.Z);
                BA(false);
            }
        }
        if (focused2 != null && !focused2.hasFocusable()) {
            if (getFocusedChild() == null) {
                return super.focusSearch(focused, direction);
            }
            BB(focused2, null);
            return focused;
        }
        if (!NB(focused, focused2, direction)) {
            focused2 = super.focusSearch(focused, direction);
        }
        return focused2;
    }

    public final void g(int dx) {
        int iG = this.F.G();
        for (int i = 0; i < iG; i++) {
            this.F.F(i).offsetLeftAndRight(dx);
        }
    }

    private boolean g(MotionEvent e) {
        int action = e.getAction();
        if (this.f != null) {
            if (action == 0) {
                this.f = null;
            } else {
                this.f.onTouchEvent(this, e);
                if (action != 3 && action != 1) {
                    return true;
                }
                this.f = null;
                return true;
            }
        }
        if (action != 0) {
            int size = this.BB.size();
            for (int i = 0; i < size; i++) {
                InterfaceC0470Dl interfaceC0470Dl = this.BB.get(i);
                if (interfaceC0470Dl.onInterceptTouchEvent(this, e)) {
                    this.f = interfaceC0470Dl;
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        if (this.R == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + S());
        }
        return this.R.W();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        if (this.R == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + S());
        }
        return this.R.X(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (this.R == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + S());
        }
        return this.R.Y(p);
    }

    public C1G getAdapter() {
        return this.C;
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.R != null ? this.R.Z() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int childCount, int i) {
        return this.h == null ? super.getChildDrawingOrder(childCount, i) : this.h.EE(childCount, i);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.G;
    }

    public C0505Eu getCompatAccessibilityDelegate() {
        return this.B;
    }

    public DY getItemAnimator() {
        return this.N;
    }

    public AbstractC0481Dw getLayoutManager() {
        return this.R;
    }

    public int getMaxFlingVelocity() {
        return this.u;
    }

    public int getMinFlingVelocity() {
        return this.v;
    }

    public long getNanoTime() {
        if (UB) {
            return System.nanoTime();
        }
        return 0L;
    }

    @Nullable
    public E9 getOnFlingListener() {
        return this.AB;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.DB;
    }

    public C0490Ef getRecycledViewPool() {
        return this.X.F();
    }

    public int getScrollState() {
        return this.MB;
    }

    private C03478s getScrollingChildHelper() {
        if (this.NB == null) {
            this.NB = new C03478s(this);
        }
        return this.NB;
    }

    public final void h(int dy) {
        int iG = this.F.G();
        for (int i = 0; i < iG; i++) {
            this.F.F(i).offsetTopAndBottom(dy);
        }
    }

    private boolean h(MotionEvent e) {
        int listenerCount = e.getAction();
        if (listenerCount == 3 || listenerCount == 0) {
            this.f = null;
        }
        int size = this.BB.size();
        for (int i = 0; i < size; i++) {
            InterfaceC0470Dl interfaceC0470Dl = this.BB.get(i);
            if (interfaceC0470Dl.onInterceptTouchEvent(this, e) && listenerCount != 3) {
                this.f = interfaceC0470Dl;
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().G();
    }

    private final void i(int state) {
        if (this.R != null) {
            this.R.eA(state);
        }
        UB(state);
        if (this.IB != null) {
            this.IB.A(this, state);
        }
        if (this.JB != null) {
            for (int size = this.JB.size() - 1; size >= 0; size--) {
                this.JB.get(size).A(this, state);
            }
        }
    }

    public final void i(int positionStart, int itemCount) {
        int I = this.F.I();
        for (int i = 0; i < I; i++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i));
            if (abstractC0502ErQ != null && !abstractC0502ErQ.f() && abstractC0502ErQ.J >= positionStart) {
                abstractC0502ErQ.Z(itemCount, false);
                this.Z.O = true;
            }
        }
        this.X.N(positionStart, itemCount);
        requestLayout();
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.M;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().I();
    }

    private final void j() {
        int i;
        for (int size = this.U.size() - 1; size >= 0; size--) {
            AbstractC0502Er abstractC0502Er = this.U.get(size);
            if (abstractC0502Er.B.getParent() == this && !abstractC0502Er.f() && (i = abstractC0502Er.I) != -1) {
                C9Y.D(abstractC0502Er.B, i);
                abstractC0502Er.I = -1;
            }
        }
        this.U.clear();
    }

    public final void j(int from, int to) {
        int i;
        int i2;
        int i3;
        int start = this.F.I();
        if (from < to) {
            i = from;
            i2 = to;
            i3 = -1;
        } else {
            i = to;
            i2 = from;
            i3 = 1;
        }
        for (int i4 = 0; i4 < start; i4++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i4));
            if (abstractC0502ErQ != null) {
                int inBetweenOffset = abstractC0502ErQ.J;
                if (inBetweenOffset >= i) {
                    int inBetweenOffset2 = abstractC0502ErQ.J;
                    if (inBetweenOffset2 <= i2) {
                        int inBetweenOffset3 = abstractC0502ErQ.J;
                        if (inBetweenOffset3 == from) {
                            int inBetweenOffset4 = to - from;
                            abstractC0502ErQ.Z(inBetweenOffset4, false);
                        } else {
                            abstractC0502ErQ.Z(i3, false);
                        }
                        this.Z.O = true;
                    }
                }
            }
        }
        this.X.O(from, to);
        requestLayout();
    }

    private final void k() {
        if (this.g != null) {
            return;
        }
        this.g = new EdgeEffect(getContext());
        if (this.G) {
            this.g.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            this.g.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void k(int positionStart, int itemCount, boolean applyToPreLayout) {
        int i = positionStart + itemCount;
        int I = this.F.I();
        for (int i2 = 0; i2 < I; i2++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i2));
            if (abstractC0502ErQ != null && !abstractC0502ErQ.f()) {
                if (abstractC0502ErQ.J >= i) {
                    abstractC0502ErQ.Z(-itemCount, applyToPreLayout);
                    this.Z.O = true;
                } else if (abstractC0502ErQ.J >= positionStart) {
                    abstractC0502ErQ.G(positionStart - 1, -itemCount, applyToPreLayout);
                    this.Z.O = true;
                }
            }
        }
        this.X.P(positionStart, itemCount, applyToPreLayout);
        requestLayout();
    }

    private final void l() {
        if (this.t != null) {
            return;
        }
        this.t = new EdgeEffect(getContext());
        if (this.G) {
            this.t.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            this.t.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    private final void m() {
        if (this.EB != null) {
            return;
        }
        this.EB = new EdgeEffect(getContext());
        if (this.G) {
            this.EB.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            this.EB.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    private final void n() {
        if (this.PB != null) {
            return;
        }
        this.PB = new EdgeEffect(getContext());
        if (this.G) {
            this.PB.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            this.PB.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Nullable
    private final AbstractC0502Er o(View view) {
        View viewV = V(view);
        if (viewV == null) {
            return null;
        }
        return Z(viewV);
    }

    public final void o() {
        this.s++;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.s = 0;
        this.M = true;
        this.J = this.J && !isLayoutRequested();
        if (this.R != null) {
            this.R.S(this);
        }
        this.V = false;
        if (UB) {
            this.K = RunnableC0476Dr.F.get();
            if (this.K == null) {
                this.K = new RunnableC0476Dr();
                Display displayU = C9Y.U(this);
                float f = 60.0f;
                if (!isInEditMode() && displayU != null) {
                    float refreshRate = displayU.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f = refreshRate;
                    }
                }
                this.K.B = (long) (1.0E9f / f);
                RunnableC0476Dr.F.set(this.K);
            }
            this.K.A(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.N != null) {
            this.N.L();
        }
        C();
        this.M = false;
        if (this.R != null) {
            this.R.T(this, this.X);
        }
        this.U.clear();
        removeCallbacks(this.p);
        this.I.J();
        if (UB && this.K != null) {
            this.K.C(this);
            this.K = null;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas c) {
        super.onDraw(c);
        int size = this.O.size();
        for (int i = 0; i < size; i++) {
            this.O.get(i).B(c, this, this.Z);
        }
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent event) {
        float f;
        float axisValue;
        if (this.R != null && !this.S && event.getAction() == 8) {
            if ((event.getSource() & 2) != 0) {
                if (this.R.H()) {
                    f = -event.getAxisValue(9);
                } else {
                    f = 0.0f;
                }
                if (this.R.G()) {
                    axisValue = event.getAxisValue(10);
                } else {
                    axisValue = 0.0f;
                }
            } else if ((event.getSource() & 4194304) != 0) {
                axisValue = event.getAxisValue(26);
                if (this.R.H()) {
                    f = -axisValue;
                    axisValue = 0.0f;
                } else if (this.R.G()) {
                    f = 0.0f;
                } else {
                    f = 0.0f;
                    axisValue = 0.0f;
                }
            } else {
                f = 0.0f;
                axisValue = 0.0f;
            }
            if (f != 0.0f || axisValue != 0.0f) {
                t((int) (this.FB * axisValue), (int) (this.GB * f), event);
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent e) {
        if (this.S) {
            return false;
        }
        if (h(e)) {
            V();
            return true;
        }
        if (this.R == null) {
            return false;
        }
        boolean zG = this.R.G();
        boolean zH = this.R.H();
        if (this.RB == null) {
            this.RB = VelocityTracker.obtain();
        }
        this.RB.addMovement(e);
        int actionIndex = e.getActionMasked();
        int actionIndex2 = e.getActionIndex();
        switch (actionIndex) {
            case 0:
                if (this.l) {
                    this.l = false;
                }
                this.LB = e.getPointerId(0);
                int x = (int) (e.getX() + 0.5f);
                this.q = x;
                this.m = x;
                int y = (int) (e.getY() + 0.5f);
                this.r = y;
                this.n = y;
                if (this.MB == 2) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                }
                int[] iArr = this.x;
                this.x[1] = 0;
                iArr[0] = 0;
                int i = 0;
                if (zG) {
                    i = 0 | 1;
                }
                if (zH) {
                    i |= 2;
                }
                U(i, 0);
                break;
            case 1:
                this.RB.clear();
                D(0);
                break;
            case 2:
                int iFindPointerIndex = e.findPointerIndex(this.LB);
                if (iFindPointerIndex < 0) {
                    StringBuilder sbAppend = new StringBuilder().append("Error processing scroll; pointer index for id ");
                    int index = this.LB;
                    Log.e("RecyclerView", sbAppend.append(index).append(" not found. Did any MotionEvents get skipped?").toString());
                    return false;
                }
                int x2 = (int) (e.getX(iFindPointerIndex) + 0.5f);
                int y2 = (int) (e.getY(iFindPointerIndex) + 0.5f);
                if (this.MB != 1) {
                    int y3 = this.m;
                    int i2 = x2 - y3;
                    int dx = this.n;
                    int i3 = y2 - dx;
                    boolean z = false;
                    if (zG && Math.abs(i2) > this.QB) {
                        this.q = x2;
                        z = true;
                    }
                    if (zH && Math.abs(i3) > this.QB) {
                        this.r = y2;
                        z = true;
                    }
                    if (z) {
                        setScrollState(1);
                    }
                }
                break;
                break;
            case 3:
                V();
                break;
            case 5:
                this.LB = e.getPointerId(actionIndex2);
                int x3 = (int) (e.getX(actionIndex2) + 0.5f);
                this.q = x3;
                this.m = x3;
                int y4 = (int) (e.getY(actionIndex2) + 0.5f);
                this.r = y4;
                this.n = y4;
                break;
            case 6:
                TB(e);
                break;
        }
        return this.MB == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        C02946r.B("RV OnLayout");
        c();
        C02946r.C();
        this.J = true;
    }

    @Override // android.view.View
    public void onMeasure(int widthSpec, int heightSpec) {
        boolean z = false;
        if (this.R == null) {
            L(widthSpec, heightSpec);
            return;
        }
        if (this.R.B) {
            int mode = View.MeasureSpec.getMode(widthSpec);
            int mode2 = View.MeasureSpec.getMode(heightSpec);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.R.aA(this.X, this.Z, widthSpec, heightSpec);
            if (z || this.C == null) {
                return;
            }
            if (this.Z.I == 1) {
                d();
            }
            this.R.vA(widthSpec, heightSpec);
            this.Z.G = true;
            e();
            this.R.yA(widthSpec, heightSpec);
            if (!this.R.lA()) {
                return;
            }
            this.R.vA(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
            this.Z.G = true;
            e();
            this.R.yA(widthSpec, heightSpec);
            return;
        }
        if (this.L) {
            this.R.aA(this.X, this.Z, widthSpec, heightSpec);
            return;
        }
        if (this.E) {
            R();
            o();
            KB();
            p();
            if (this.Z.M) {
                this.Z.F = true;
            } else {
                this.D.C();
                this.Z.F = false;
            }
            this.E = false;
            BA(false);
        } else if (this.Z.M) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        if (this.C != null) {
            this.Z.H = this.C.C();
        } else {
            this.Z.H = 0;
        }
        R();
        this.R.aA(this.X, this.Z, widthSpec, heightSpec);
        BA(false);
        this.Z.F = false;
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        if (m250e()) {
            return false;
        }
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof C0495Ek)) {
            super.onRestoreInstanceState(state);
            return;
        }
        this.CB = (C0495Ek) state;
        super.onRestoreInstanceState(this.CB.A());
        if (this.R == null || this.CB.B == null) {
            return;
        }
        this.R.cA(this.CB.B);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        C0495Ek c0495Ek = new C0495Ek(super.onSaveInstanceState());
        if (this.CB != null) {
            c0495Ek.B(this.CB);
        } else if (this.R != null) {
            c0495Ek.B = this.R.dA();
        } else {
            c0495Ek.B = null;
        }
        return c0495Ek;
    }

    @Override // android.view.View
    public final void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            JB();
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent e) {
        float f;
        float f2;
        if (!this.S && !this.l) {
            if (g(e)) {
                V();
                return true;
            }
            if (this.R == null) {
                return false;
            }
            boolean zG = this.R.G();
            boolean zH = this.R.H();
            if (this.RB == null) {
                this.RB = VelocityTracker.obtain();
            }
            boolean z = false;
            MotionEvent motionEventObtain = MotionEvent.obtain(e);
            int actionMasked = e.getActionMasked();
            int actionIndex = e.getActionIndex();
            if (actionMasked == 0) {
                int[] iArr = this.x;
                this.x[1] = 0;
                iArr[0] = 0;
            }
            int actionIndex2 = this.x[0];
            float f3 = actionIndex2;
            int actionIndex3 = this.x[1];
            motionEventObtain.offsetLocation(f3, actionIndex3);
            switch (actionMasked) {
                case 0:
                    this.LB = e.getPointerId(0);
                    int y = (int) (e.getX() + 0.5f);
                    this.q = y;
                    this.m = y;
                    int y2 = (int) (e.getY() + 0.5f);
                    this.r = y2;
                    this.n = y2;
                    int i = 0;
                    if (zG) {
                        i = 0 | 1;
                    }
                    if (zH) {
                        i |= 2;
                    }
                    U(i, 0);
                    break;
                case 1:
                    this.RB.addMovement(motionEventObtain);
                    z = true;
                    this.RB.computeCurrentVelocity(1000, this.u);
                    if (zG) {
                        f = -this.RB.getXVelocity(this.LB);
                    } else {
                        f = 0.0f;
                    }
                    if (zH) {
                        float xvel = this.RB.getYVelocity(this.LB);
                        f2 = -xvel;
                    } else {
                        f2 = 0.0f;
                    }
                    if ((f == 0.0f && f2 == 0.0f) || !v((int) f, (int) f2)) {
                        setScrollState(0);
                    }
                    y();
                    break;
                case 2:
                    int iFindPointerIndex = e.findPointerIndex(this.LB);
                    if (iFindPointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.LB + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    int x = (int) (e.getX(iFindPointerIndex) + 0.5f);
                    int y3 = (int) (e.getY(iFindPointerIndex) + 0.5f);
                    int i2 = this.q - x;
                    int i3 = this.r - y3;
                    if (O(i2, i3, this.HB, this.KB, 0)) {
                        int dy = this.HB[0];
                        i2 -= dy;
                        int dy2 = this.HB[1];
                        i3 -= dy2;
                        int dy3 = this.KB[0];
                        float f4 = dy3;
                        int dy4 = this.KB[1];
                        motionEventObtain.offsetLocation(f4, dy4);
                        int[] iArr2 = this.x;
                        int i4 = iArr2[0];
                        int dy5 = this.KB[0];
                        iArr2[0] = i4 + dy5;
                        int[] iArr3 = this.x;
                        int i5 = iArr3[1];
                        int dy6 = this.KB[1];
                        iArr3[1] = i5 + dy6;
                    }
                    if (this.MB != 1) {
                        boolean z2 = false;
                        if (zG) {
                            if (Math.abs(i2) > this.QB) {
                                if (i2 > 0) {
                                    i2 -= this.QB;
                                } else {
                                    i2 += this.QB;
                                }
                                z2 = true;
                            }
                        }
                        if (zH) {
                            if (Math.abs(i3) > this.QB) {
                                if (i3 > 0) {
                                    i3 -= this.QB;
                                } else {
                                    i3 += this.QB;
                                }
                                z2 = true;
                            }
                        }
                        if (z2) {
                            setScrollState(1);
                        }
                    }
                    if (this.MB == 1) {
                        this.q = x - this.KB[0];
                        this.r = y3 - this.KB[1];
                        if (t(zG ? i2 : 0, zH ? i3 : 0, motionEventObtain)) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        if (this.K != null && (i2 != 0 || i3 != 0)) {
                            this.K.B(this, i2, i3);
                        }
                    }
                    break;
                    break;
                case 3:
                    V();
                    break;
                case 5:
                    this.LB = e.getPointerId(actionIndex);
                    int y4 = (int) (e.getX(actionIndex) + 0.5f);
                    this.q = y4;
                    this.m = y4;
                    int y5 = (int) (e.getY(actionIndex) + 0.5f);
                    this.r = y5;
                    this.n = y5;
                    break;
                case 6:
                    TB(e);
                    break;
            }
            if (!z) {
                this.RB.addMovement(motionEventObtain);
            }
            motionEventObtain.recycle();
            return true;
        }
        return false;
    }

    public final void p() {
        q(true);
    }

    private void p(int[] into) {
        int iG = this.F.G();
        if (iG == 0) {
            into[0] = -1;
            into[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < iG; i3++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.F(i3));
            if (!abstractC0502ErQ.f()) {
                int pos = abstractC0502ErQ.K();
                if (pos < i) {
                    i = pos;
                }
                if (pos > i2) {
                    i2 = pos;
                }
            }
        }
        into[0] = i;
        into[1] = i2;
    }

    @Nullable
    private View q() {
        int i = this.Z.D != -1 ? this.Z.D : 0;
        int iB = this.Z.B();
        for (int i2 = i; i2 < iB; i2++) {
            AbstractC0502Er nextFocus = r(i2);
            if (nextFocus == null) {
                break;
            }
            if (nextFocus.B.hasFocusable()) {
                return nextFocus.B;
            }
        }
        int startFocusSearchIndex = Math.min(iB, i);
        for (int i3 = startFocusSearchIndex - 1; i3 >= 0; i3--) {
            AbstractC0502Er nextFocus2 = r(i3);
            if (nextFocus2 == null) {
                return null;
            }
            if (nextFocus2.B.hasFocusable()) {
                return nextFocus2.B;
            }
        }
        return null;
    }

    public final void q(boolean enableChangeEvents) {
        this.s--;
        if (this.s < 1) {
            this.s = 0;
            if (enableChangeEvents) {
                b();
                j();
            }
        }
    }

    private final AbstractC0502Er r(int position) {
        if (this.H) {
            return null;
        }
        int I = this.F.I();
        AbstractC0502Er abstractC0502Er = null;
        for (int i = 0; i < I; i++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i));
            if (abstractC0502ErQ != null && !abstractC0502ErQ.U() && X(abstractC0502ErQ) == position) {
                if (!this.F.L(abstractC0502ErQ.B)) {
                    return abstractC0502ErQ;
                }
                abstractC0502Er = abstractC0502ErQ;
            }
        }
        return abstractC0502Er;
    }

    public final void r() {
        if (!this.V && this.M) {
            C9Y.I(this, this.p);
            this.V = true;
        }
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View child, boolean animate) {
        AbstractC0502Er abstractC0502ErQ = Q(child);
        if (abstractC0502ErQ != null) {
            if (abstractC0502ErQ.W()) {
                abstractC0502ErQ.F();
            } else if (!abstractC0502ErQ.f()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + abstractC0502ErQ + S());
            }
        }
        child.clearAnimation();
        N(child);
        super.removeDetachedView(child, animate);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View child, View focused) {
        if (!this.R.bA(this, this.Z, child, focused) && focused != null) {
            BB(child, focused);
        }
        super.requestChildFocus(child, focused);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return this.R.mA(this, child, rect, immediate);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        int size = this.BB.size();
        for (int i = 0; i < size; i++) {
            this.BB.get(i).onRequestDisallowInterceptTouchEvent(disallowIntercept);
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.j == 0 && !this.S) {
            super.requestLayout();
        } else {
            this.T = true;
        }
    }

    private final AbstractC0502Er s(long id) {
        if (this.C == null || !this.C.F()) {
            return null;
        }
        int I = this.F.I();
        AbstractC0502Er abstractC0502Er = null;
        for (int i = 0; i < I; i++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i));
            if (abstractC0502ErQ != null && !abstractC0502ErQ.U() && abstractC0502ErQ.I() == id) {
                if (!this.F.L(abstractC0502ErQ.B)) {
                    return abstractC0502ErQ;
                }
                abstractC0502Er = abstractC0502ErQ;
            }
        }
        return abstractC0502Er;
    }

    public final void s(AbstractC0502Er viewHolder, EX animationInfo) {
        viewHolder.c(0, 8192);
        if (this.Z.P && viewHolder.X() && !viewHolder.U() && !viewHolder.f()) {
            this.I.C(w(viewHolder), viewHolder);
        }
        this.I.E(viewHolder, animationInfo);
    }

    @Override // android.view.View
    public final void scrollBy(int x, int y) {
        if (this.R == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.S) {
            return;
        }
        boolean zG = this.R.G();
        boolean zH = this.R.H();
        if (!zG && !zH) {
            return;
        }
        if (!zG) {
            x = 0;
        }
        if (!zH) {
            y = 0;
        }
        t(x, y, null);
    }

    @Override // android.view.View
    public final void scrollTo(int x, int y) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        if (Y(event)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(event);
    }

    public void setAccessibilityDelegateCompat(C0505Eu accessibilityDelegate) {
        this.B = accessibilityDelegate;
        C9Y.F(this, this.B);
    }

    public void setAdapter(C1G adapter) {
        setLayoutFrozen(false);
        Z(adapter, false, true);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(ET childDrawingOrderCallback) {
        if (childDrawingOrderCallback == this.h) {
            return;
        }
        this.h = childDrawingOrderCallback;
        setChildrenDrawingOrderEnabled(this.h != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean clipToPadding) {
        if (clipToPadding != this.G) {
            JB();
        }
        this.G = clipToPadding;
        super.setClipToPadding(clipToPadding);
        if (this.J) {
            requestLayout();
        }
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        this.L = hasFixedSize;
    }

    public void setItemAnimator(DY animator) {
        if (this.N != null) {
            this.N.L();
            this.N.V(null);
        }
        this.N = animator;
        if (this.N != null) {
            this.N.V(this.o);
        }
    }

    public void setItemViewCacheSize(int size) {
        this.X.X(size);
    }

    public void setLayoutFrozen(boolean frozen) {
        if (frozen != this.S) {
            H("Do not setLayoutFrozen in layout or scroll");
            if (!frozen) {
                this.S = false;
                if (this.T && this.R != null && this.C != null) {
                    requestLayout();
                }
                this.T = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent cancelEvent = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            onTouchEvent(cancelEvent);
            this.S = true;
            this.l = true;
            C();
        }
    }

    public void setLayoutManager(AbstractC0481Dw layout) {
        if (layout == this.R) {
            return;
        }
        C();
        if (this.R != null) {
            if (this.N != null) {
                this.N.L();
            }
            this.R.hA(this.X);
            this.R.iA(this.X);
            this.X.B();
            if (this.M) {
                this.R.T(this, this.X);
            }
            this.R.zA(null);
            this.R = null;
        } else {
            this.X.B();
        }
        this.F.M();
        this.R = layout;
        if (layout != null) {
            if (layout.H != null) {
                throw new IllegalArgumentException("LayoutManager " + layout + " is already attached to a RecyclerView:" + layout.H.S());
            }
            this.R.zA(this);
            if (this.M) {
                this.R.S(this);
            }
        }
        this.X.a();
        requestLayout();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean enabled) {
        getScrollingChildHelper().J(enabled);
    }

    public void setOnFlingListener(@Nullable E9 onFlingListener) {
        this.AB = onFlingListener;
    }

    @Deprecated
    public void setOnScrollListener(AbstractC0463De listener) {
        this.IB = listener;
    }

    public void setPreserveFocusAfterLayout(boolean preserveFocusAfterLayout) {
        this.DB = preserveFocusAfterLayout;
    }

    public void setRecycledViewPool(C0490Ef pool) {
        this.X.V(pool);
    }

    public void setRecyclerListener(InterfaceC0492Eh listener) {
        this.Y = listener;
    }

    public void setScrollState(int state) {
        if (state == this.MB) {
            return;
        }
        this.MB = state;
        if (state != 2) {
            T();
        }
        i(state);
    }

    public void setScrollingTouchSlop(int slopConstant) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        switch (slopConstant) {
            case 0:
                break;
            case 1:
                this.QB = viewConfiguration.getScaledPagingTouchSlop();
                return;
            default:
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + slopConstant + "; using default value");
                break;
        }
        this.QB = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(AbstractC0500Ep extension) {
        this.X.W(extension);
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int axes) {
        return getScrollingChildHelper().K(axes);
    }

    @Override // android.view.View, com.facebook.ads.redexgen.X.InterfaceC03458q
    public final void stopNestedScroll() {
        getScrollingChildHelper().M();
    }

    public final void t() {
        if (this.N != null) {
            this.N.L();
        }
        if (this.R != null) {
            this.R.hA(this.X);
            this.R.iA(this.X);
        }
        this.X.B();
    }

    private final boolean t(int x, int y, MotionEvent ev) {
        int i = 0;
        int i2 = 0;
        int iQA = 0;
        int iSA = 0;
        K();
        if (this.C != null) {
            R();
            o();
            C02946r.B("RV Scroll");
            T(this.Z);
            if (x != 0) {
                iQA = this.R.qA(x, this.X, this.Z);
                i = x - iQA;
            }
            if (y != 0) {
                iSA = this.R.sA(y, this.X, this.Z);
                i2 = y - iSA;
            }
            C02946r.C();
            AA();
            p();
            BA(false);
        }
        if (!this.O.isEmpty()) {
            invalidate();
        }
        if (P(iQA, iSA, i, i2, this.KB, 0)) {
            this.q -= this.KB[0];
            this.r -= this.KB[1];
            if (ev != null) {
                ev.offsetLocation(this.KB[0], this.KB[1]);
            }
            int[] iArr = this.x;
            iArr[0] = iArr[0] + this.KB[0];
            int[] iArr2 = this.x;
            iArr2[1] = iArr2[1] + this.KB[1];
        } else if (getOverScrollMode() != 2) {
            if (ev != null && !C03448p.B(ev, 8194)) {
                IB(ev.getX(), i, ev.getY(), i2);
            }
            J(x, y);
        }
        if (iQA != 0 || iSA != 0) {
            Q(iQA, iSA);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (iQA == 0 && iSA == 0) ? false : true;
    }

    private final void u() {
        int i = this.F.I();
        for (int i2 = 0; i2 < i; i2++) {
            AbstractC0502Er abstractC0502ErQ = Q(this.F.H(i2));
            if (!abstractC0502ErQ.f()) {
                abstractC0502ErQ.b();
            }
        }
    }

    public final boolean u(View view) {
        R();
        boolean zP = this.F.P(view);
        if (zP) {
            AbstractC0502Er abstractC0502ErQ = Q(view);
            this.X.Z(abstractC0502ErQ);
            this.X.T(abstractC0502ErQ);
        }
        BA(!zP);
        return zP;
    }

    public final void v(int position) {
        if (this.S) {
            return;
        }
        if (this.R == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            this.R.FA(this, this.Z, position);
        }
    }

    private final boolean v(int velocityX, int velocityY) {
        if (this.R == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.S) {
            return false;
        }
        boolean zG = this.R.G();
        boolean zH = this.R.H();
        if (!zG || Math.abs(velocityX) < this.v) {
            velocityX = 0;
        }
        if (!zH || Math.abs(velocityY) < this.v) {
            velocityY = 0;
        }
        if ((velocityX == 0 && velocityY == 0) || dispatchNestedPreFling(velocityX, velocityY)) {
            return false;
        }
        boolean z = zG || zH;
        dispatchNestedFling(velocityX, velocityY, z);
        if (this.AB != null && this.AB.A(velocityX, velocityY)) {
            return true;
        }
        if (!z) {
            return false;
        }
        int i = 0;
        if (zG) {
            i = 0 | 1;
        }
        if (zH) {
            i |= 2;
        }
        U(i, 1);
        this.d.A(Math.max(-this.u, Math.min(velocityX, this.u)), Math.max(-this.u, Math.min(velocityY, this.u)));
        return true;
    }

    private final long w(AbstractC0502Er holder) {
        return this.C.F() ? holder.I() : holder.J;
    }

    public final void w(int dx, int dy) {
        X(dx, dy, null);
    }

    private void x() {
        int iH;
        View focusedChild = null;
        if (this.DB && hasFocus() && this.C != null) {
            focusedChild = getFocusedChild();
        }
        AbstractC0502Er abstractC0502ErO = focusedChild == null ? null : o(focusedChild);
        if (abstractC0502ErO == null) {
            z();
            return;
        }
        this.Z.C = this.C.F() ? abstractC0502ErO.I() : -1L;
        C0499Eo c0499Eo = this.Z;
        if (this.H) {
            iH = -1;
        } else {
            iH = abstractC0502ErO.U() ? abstractC0502ErO.F : abstractC0502ErO.H();
        }
        c0499Eo.D = iH;
        this.Z.E = AB(abstractC0502ErO.B);
    }

    public final void x(AbstractC0463De listener) {
        if (this.JB != null) {
            this.JB.remove(listener);
        }
    }

    private void y() {
        if (this.RB != null) {
            this.RB.clear();
        }
        D(0);
        CB();
    }

    private void z() {
        this.Z.C = -1L;
        this.Z.D = -1;
        this.Z.E = -1;
    }
}
