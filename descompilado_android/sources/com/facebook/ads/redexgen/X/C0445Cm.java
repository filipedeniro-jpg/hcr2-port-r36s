package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0445Cm {
    private static final Interpolator X = new InterpolatorC0443Ck();
    private final BS C;
    private View D;
    private int E;
    private int[] F;
    private int[] G;
    private int H;
    private int[] I;
    private float[] J;
    private float[] K;
    private float[] L;
    private float[] M;
    private float N;
    private float O;
    private final ViewGroup P;
    private int Q;
    private boolean R;
    private OverScroller S;
    private int U;
    private int V;
    private VelocityTracker W;
    private int B = -1;
    private final Runnable T = new RunnableC0444Cl(this);

    private C0445Cm(Context context, ViewGroup forParent, BS cb) {
        if (forParent == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (cb == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.P = forParent;
        this.C = cb;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.H = (int) ((20.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
        this.U = viewConfiguration.getScaledTouchSlop();
        this.N = viewConfiguration.getScaledMaximumFlingVelocity();
        this.O = viewConfiguration.getScaledMinimumFlingVelocity();
        this.S = new OverScroller(context, X);
    }

    public final boolean A(View child, int finalLeft, int finalTop) {
        this.D = child;
        this.B = -1;
        boolean zS = S(finalLeft, finalTop, 0, 0);
        if (!zS && this.E == 0 && this.D != null) {
            this.D = null;
        }
        return zS;
    }

    public static C0445Cm B(ViewGroup forParent, float sensitivity, BS cb) {
        C0445Cm helper = M(forParent, cb);
        helper.U = (int) (helper.U * (1.0f / sensitivity));
        return helper;
    }

    public final void B() {
        this.B = -1;
        I();
        if (this.W != null) {
            this.W.recycle();
            this.W = null;
        }
    }

    /* JADX INFO: renamed from: C, reason: collision with other method in class */
    public final void m241C(View childView, int activePointerId) {
        if (childView.getParent() != this.P) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.P + ")");
        }
        this.D = childView;
        this.B = activePointerId;
        this.C.I(childView, activePointerId);
        G(1);
    }

    private final boolean C(View toCapture, int pointerId) {
        if (toCapture == this.D && this.B == pointerId) {
            return true;
        }
        if (toCapture != null && this.C.M(toCapture, pointerId)) {
            this.B = pointerId;
            m241C(toCapture, pointerId);
            return true;
        }
        return false;
    }

    private boolean D(float delta, float odelta, int pointerId, int edge) {
        float fAbs = Math.abs(delta);
        float fAbs2 = Math.abs(odelta);
        if ((this.I[pointerId] & edge) != edge || (this.V & edge) == 0 || (this.G[pointerId] & edge) == edge || (this.F[pointerId] & edge) == edge) {
            return false;
        }
        if (fAbs <= this.U && fAbs2 <= this.U) {
            return false;
        }
        if (fAbs >= 0.5f * fAbs2 || !this.C.G(edge)) {
            return (this.F[pointerId] & edge) == 0 && fAbs > ((float) this.U);
        }
        int[] iArr = this.G;
        iArr[pointerId] = iArr[pointerId] | edge;
        return false;
    }

    public final boolean D(int finalLeft, int finalTop) {
        if (!this.R) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return S(finalLeft, finalTop, (int) this.W.getXVelocity(this.B), (int) this.W.getYVelocity(this.B));
    }

    private void E(MotionEvent ev) {
        int pointerCount = ev.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = ev.getPointerId(i);
            if (X(pointerId)) {
                float x = ev.getX(i);
                float y = ev.getY(i);
                this.L[pointerId] = x;
                this.M[pointerId] = y;
            }
        }
    }

    public final boolean E(boolean deferCallbacks) {
        if (this.E == 2) {
            boolean keepGoing = this.S.computeScrollOffset();
            int currX = this.S.getCurrX();
            int currY = this.S.getCurrY();
            int left = currX - this.D.getLeft();
            int dx = this.D.getTop();
            int i = currY - dx;
            if (left != 0) {
                C9Y.M(this.D, left);
            }
            if (i != 0) {
                C9Y.L(this.D, i);
            }
            if (left != 0 || i != 0) {
                this.C.K(this.D, currX, currY, left, i);
            }
            if (keepGoing) {
                int dy = this.S.getFinalX();
                if (currX == dy) {
                    int dy2 = this.S.getFinalY();
                    if (currY == dy2) {
                        this.S.abortAnimation();
                        keepGoing = false;
                    }
                }
            }
            if (!keepGoing) {
                if (deferCallbacks) {
                    this.P.post(this.T);
                } else {
                    G(0);
                }
            }
        }
        int dy3 = this.E;
        return dy3 == 2;
    }

    public final View F(int x, int y) {
        for (int childCount = this.P.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.P.getChildAt(this.C.C(childCount));
            if (x >= childAt.getLeft() && x < childAt.getRight() && y >= childAt.getTop() && y < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private boolean F(View child, float dx, float dy) {
        if (child == null) {
            return false;
        }
        boolean z = this.C.D(child) > 0;
        boolean z2 = this.C.E(child) > 0;
        if (z && z2) {
            return (dx * dx) + (dy * dy) > ((float) (this.U * this.U));
        }
        if (z) {
            return Math.abs(dx) > ((float) this.U);
        }
        return z2 && Math.abs(dy) > ((float) this.U);
    }

    private float G(float value, float absMin, float absMax) {
        float fAbs = Math.abs(value);
        if (fAbs < absMin) {
            return 0.0f;
        }
        if (fAbs > absMax) {
            return value <= 0.0f ? -absMax : absMax;
        }
        return value;
    }

    public final void G(int state) {
        this.P.removeCallbacks(this.T);
        if (this.E != state) {
            this.E = state;
            this.C.J(state);
            if (this.E == 0) {
                this.D = null;
            }
        }
    }

    private int H(int value, int absMin, int absMax) {
        int iAbs = Math.abs(value);
        if (iAbs < absMin) {
            return 0;
        }
        if (iAbs > absMax) {
            return value <= 0 ? -absMax : absMax;
        }
        return value;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0087  */
    public final void H(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        int action = ev.getActionIndex();
        if (actionMasked == 0) {
            B();
        }
        if (this.W == null) {
            this.W = VelocityTracker.obtain();
        }
        this.W.addMovement(ev);
        switch (actionMasked) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                int pointerId = ev.getPointerId(0);
                View viewF = F((int) x, (int) y);
                R(x, y, pointerId);
                C(viewF, pointerId);
                int i = this.I[pointerId];
                if ((this.V & i) != 0) {
                    this.C.H(this.V & i, pointerId);
                }
                break;
            case 1:
                if (this.E == 1) {
                    V();
                }
                B();
                break;
            case 2:
                if (this.E == 1) {
                    if (X(this.B)) {
                        int index = ev.findPointerIndex(this.B);
                        float x2 = ev.getX(index);
                        float y2 = ev.getY(index);
                        int i2 = (int) (x2 - this.L[this.B]);
                        float[] fArr = this.M;
                        int idx = this.B;
                        int i3 = (int) (y2 - fArr[idx]);
                        P(this.D.getLeft() + i2, this.D.getTop() + i3, i2, i3);
                        E(ev);
                    }
                } else {
                    int pointerCount = ev.getPointerCount();
                    for (int i4 = 0; i4 < pointerCount; i4++) {
                        int pointerId2 = ev.getPointerId(i4);
                        if (X(pointerId2)) {
                            float x3 = ev.getX(i4);
                            float y3 = ev.getY(i4);
                            float f = x3 - this.J[pointerId2];
                            float dx = this.K[pointerId2];
                            float f2 = y3 - dx;
                            T(f, f2, pointerId2);
                            int i5 = this.E;
                            if (i5 != 1) {
                                int i6 = (int) y3;
                                View viewF2 = F((int) x3, i6);
                                if (!F(viewF2, f, f2) || !C(viewF2, pointerId2)) {
                                }
                            }
                            E(ev);
                            break;
                        }
                    }
                    E(ev);
                }
                break;
            case 3:
                if (this.E == 1) {
                    N(0.0f, 0.0f);
                }
                B();
                break;
            case 5:
                int pointerId3 = ev.getPointerId(action);
                float x4 = ev.getX(action);
                float y4 = ev.getY(action);
                R(x4, y4, pointerId3);
                if (this.E == 0) {
                    C(F((int) x4, (int) y4), pointerId3);
                    int i7 = this.I[pointerId3];
                    if ((this.V & i7) != 0) {
                        this.C.H(this.V & i7, pointerId3);
                    }
                } else if (W((int) x4, (int) y4)) {
                    C(this.D, pointerId3);
                }
                break;
            case 6:
                int pointerId4 = ev.getPointerId(action);
                if (this.E == 1 && pointerId4 == this.B) {
                    int i8 = -1;
                    int pointerCount2 = ev.getPointerCount();
                    for (int i9 = 0; i9 < pointerCount2; i9++) {
                        int pointerId5 = ev.getPointerId(i9);
                        int id = this.B;
                        if (pointerId5 != id) {
                            if (F((int) ev.getX(i9), (int) ev.getY(i9)) == this.D && C(this.D, pointerId5)) {
                                i8 = this.B;
                                if (i8 == -1) {
                                    V();
                                }
                            }
                        }
                    }
                    if (i8 == -1) {
                        V();
                    }
                }
                J(pointerId4);
                break;
        }
    }

    private void I() {
        if (this.J == null) {
            return;
        }
        Arrays.fill(this.J, 0.0f);
        Arrays.fill(this.K, 0.0f);
        Arrays.fill(this.L, 0.0f);
        Arrays.fill(this.M, 0.0f);
        Arrays.fill(this.I, 0);
        Arrays.fill(this.F, 0);
        Arrays.fill(this.G, 0);
        this.Q = 0;
    }

    public final boolean I(View view, int x, int y) {
        return view != null && x >= view.getLeft() && x < view.getRight() && y >= view.getTop() && y < view.getBottom();
    }

    private void J(int pointerId) {
        if (this.J == null || !Y(pointerId)) {
            return;
        }
        this.J[pointerId] = 0.0f;
        this.K[pointerId] = 0.0f;
        this.L[pointerId] = 0.0f;
        this.M[pointerId] = 0.0f;
        this.I[pointerId] = 0;
        this.F[pointerId] = 0;
        this.G[pointerId] = 0;
        this.Q &= (1 << pointerId) ^ (-1);
    }

    private int K(int delta, int velocity, int motionRange) {
        int iAbs;
        if (delta == 0) {
            return 0;
        }
        int width = this.P.getWidth();
        int i = width / 2;
        float fO = i + (i * O(Math.min(1.0f, Math.abs(delta) / width)));
        int width2 = Math.abs(velocity);
        if (width2 > 0) {
            iAbs = Math.round(1000.0f * Math.abs(fO / width2)) * 4;
        } else {
            iAbs = (int) (((Math.abs(delta) / motionRange) + 1.0f) * 256.0f);
        }
        return Math.min(iAbs, 600);
    }

    private int L(View child, int dx, int dy, int xvel, int yvel) {
        float f;
        int iH = H(xvel, (int) this.O, (int) this.N);
        int iH2 = H(yvel, (int) this.O, (int) this.N);
        int iAbs = Math.abs(dx);
        int iAbs2 = Math.abs(dy);
        int addedDistance = Math.abs(iH);
        int iAbs3 = Math.abs(iH2);
        int i = addedDistance + iAbs3;
        int addedVel = iAbs + iAbs2;
        float f2 = iH != 0 ? addedDistance / i : iAbs / addedVel;
        if (iH2 != 0) {
            float xweight = i;
            f = iAbs3 / xweight;
        } else {
            f = iAbs2 / addedVel;
        }
        int addedVel2 = K(dx, iH, this.C.D(child));
        int xduration = this.C.E(child);
        return (int) ((addedVel2 * f2) + (K(dy, iH2, xduration) * f));
    }

    private static C0445Cm M(ViewGroup forParent, BS cb) {
        return new C0445Cm(forParent.getContext(), forParent, cb);
    }

    private void N(float xvel, float yvel) {
        this.R = true;
        this.C.L(this.D, xvel, yvel);
        this.R = false;
        if (this.E == 1) {
            G(0);
        }
    }

    private float O(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    private void P(int left, int top, int dx, int dy) {
        int oldLeft = top;
        int clampedDy = left;
        int left2 = this.D.getLeft();
        int top2 = this.D.getTop();
        if (dx != 0) {
            clampedDy = this.C.A(this.D, clampedDy, dx);
            int oldTop = clampedDy - left2;
            C9Y.M(this.D, oldTop);
        }
        if (dy != 0) {
            oldLeft = this.C.B(this.D, oldLeft, dy);
            int oldTop2 = oldLeft - top2;
            C9Y.L(this.D, oldTop2);
        }
        if (dx != 0 || dy != 0) {
            this.C.K(this.D, clampedDy, oldLeft, clampedDy - left2, oldLeft - top2);
        }
    }

    private void Q(int pointerId) {
        if (this.J == null || this.J.length <= pointerId) {
            float[] fArr = new float[pointerId + 1];
            float[] fArr2 = new float[pointerId + 1];
            float[] fArr3 = new float[pointerId + 1];
            float[] fArr4 = new float[pointerId + 1];
            int[] iArr = new int[pointerId + 1];
            int[] iArr2 = new int[pointerId + 1];
            int[] iArr3 = new int[pointerId + 1];
            float[] lmy = this.J;
            if (lmy != null) {
                float[] fArr5 = this.J;
                float[] lmy2 = this.J;
                System.arraycopy(fArr5, 0, fArr, 0, lmy2.length);
                float[] fArr6 = this.K;
                float[] lmy3 = this.K;
                System.arraycopy(fArr6, 0, fArr2, 0, lmy3.length);
                float[] fArr7 = this.L;
                float[] lmy4 = this.L;
                System.arraycopy(fArr7, 0, fArr3, 0, lmy4.length);
                float[] fArr8 = this.M;
                float[] lmy5 = this.M;
                System.arraycopy(fArr8, 0, fArr4, 0, lmy5.length);
                int[] iArr4 = this.I;
                float[] lmy6 = this.I;
                System.arraycopy(iArr4, 0, iArr, 0, lmy6.length);
                int[] iArr5 = this.F;
                float[] lmy7 = this.F;
                System.arraycopy(iArr5, 0, iArr2, 0, lmy7.length);
                int[] iArr6 = this.G;
                float[] lmy8 = this.G;
                System.arraycopy(iArr6, 0, iArr3, 0, lmy8.length);
            }
            this.J = fArr;
            this.K = fArr2;
            this.L = fArr3;
            this.M = fArr4;
            this.I = iArr;
            this.F = iArr2;
            this.G = iArr3;
        }
    }

    private void R(float x, float y, int pointerId) {
        Q(pointerId);
        float[] fArr = this.J;
        this.L[pointerId] = x;
        fArr[pointerId] = x;
        float[] fArr2 = this.K;
        this.M[pointerId] = y;
        fArr2[pointerId] = y;
        this.I[pointerId] = U((int) x, (int) y);
        this.Q |= 1 << pointerId;
    }

    private boolean S(int finalLeft, int finalTop, int xvel, int yvel) {
        int left = this.D.getLeft();
        int duration = this.D.getTop();
        int startLeft = finalLeft - left;
        int dx = finalTop - duration;
        if (startLeft == 0 && dx == 0) {
            this.S.abortAnimation();
            G(0);
            return false;
        }
        this.S.startScroll(left, duration, startLeft, dx, L(this.D, startLeft, dx, xvel, yvel));
        G(2);
        return true;
    }

    private void T(float dx, float dy, int pointerId) {
        int i = 0;
        if (D(dx, dy, pointerId, 1)) {
            i = 0 | 1;
        }
        if (D(dy, dx, pointerId, 4)) {
            i |= 4;
        }
        if (D(dx, dy, pointerId, 2)) {
            i |= 2;
        }
        if (D(dy, dx, pointerId, 8)) {
            i |= 8;
        }
        if (i != 0) {
            int[] iArr = this.F;
            iArr[pointerId] = iArr[pointerId] | i;
            this.C.F(i, pointerId);
        }
    }

    private int U(int x, int y) {
        int i = x < this.P.getLeft() + this.H ? 0 | 1 : 0;
        if (y < this.P.getTop() + this.H) {
            i |= 4;
        }
        if (x > this.P.getRight() - this.H) {
            i |= 2;
        }
        return y > this.P.getBottom() - this.H ? i | 8 : i;
    }

    private void V() {
        this.W.computeCurrentVelocity(1000, this.N);
        N(G(this.W.getXVelocity(this.B), this.O, this.N), G(this.W.getYVelocity(this.B), this.O, this.N));
    }

    private final boolean W(int x, int y) {
        return I(this.D, x, y);
    }

    private boolean X(int pointerId) {
        if (Y(pointerId)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + pointerId + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    private final boolean Y(int pointerId) {
        return (this.Q & (1 << pointerId)) != 0;
    }
}
