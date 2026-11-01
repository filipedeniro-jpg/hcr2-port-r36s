package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Eq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0501Eq implements Runnable {
    public OverScroller C;
    public final /* synthetic */ C0503Es D;
    private int F;
    private int G;
    public Interpolator B = C0503Es.cB;
    private boolean E = false;
    private boolean H = false;

    public RunnableC0501Eq(C0503Es this$0) {
        this.D = this$0;
        this.C = new OverScroller(this$0.getContext(), C0503Es.cB);
    }

    public final void A(int velocityX, int velocityY) {
        this.D.setScrollState(2);
        this.G = 0;
        this.F = 0;
        this.C.fling(0, 0, velocityX, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        B();
    }

    private int B(int dx, int dy, int vx, int vy) {
        int iRound;
        int iAbs = Math.abs(dx);
        int iAbs2 = Math.abs(dy);
        int duration = iAbs > iAbs2 ? 1 : 0;
        int iSqrt = (int) Math.sqrt((vx * vx) + (vy * vy));
        int absDx = dx * dx;
        int absDy = dy * dy;
        int iSqrt2 = (int) Math.sqrt(absDx + absDy);
        int width = duration != 0 ? this.D.getWidth() : this.D.getHeight();
        int i = width / 2;
        float fD = i + (i * D(Math.min(1.0f, (1.0f * iSqrt2) / width)));
        if (iSqrt > 0) {
            iRound = Math.round(1000.0f * Math.abs(fD / iSqrt)) * 4;
        } else {
            if (duration == 0) {
                iAbs = iAbs2;
            }
            iRound = (int) (((iAbs / width) + 1.0f) * 300.0f);
        }
        return Math.min(iRound, 2000);
    }

    public final void B() {
        if (this.E) {
            this.H = true;
        } else {
            this.D.removeCallbacks(this);
            C9Y.I(this.D, this);
        }
    }

    private final void C() {
        this.H = false;
        this.E = true;
    }

    public final void C(int dx, int dy) {
        F(dx, dy, 0, 0);
    }

    private float D(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    public final void D(int dx, int dy, int duration) {
        E(dx, dy, duration, C0503Es.cB);
    }

    private final void E() {
        this.E = false;
        if (this.H) {
            B();
        }
    }

    public final void E(int dx, int dy, int duration, Interpolator interpolator) {
        if (this.B != interpolator) {
            this.B = interpolator;
            this.C = new OverScroller(this.D.getContext(), interpolator);
        }
        this.D.setScrollState(2);
        this.G = 0;
        this.F = 0;
        this.C.startScroll(0, 0, dx, dy, duration);
        if (Build.VERSION.SDK_INT < 23) {
            this.C.computeScrollOffset();
        }
        B();
    }

    private final void F(int dx, int dy, int vx, int vy) {
        D(dx, dy, B(dx, dy, vx, vy));
    }

    public final void F(int dx, int dy, Interpolator interpolator) {
        int iB = B(dx, dy, 0, 0);
        if (interpolator == null) {
            interpolator = C0503Es.cB;
        }
        E(dx, dy, iB, interpolator);
    }

    public final void G() {
        this.D.removeCallbacks(this);
        this.C.abortAnimation();
    }

    /* JADX WARN: Code duplicated, block: B:56:0x016c  */
    /* JADX WARN: Code duplicated, block: B:83:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:85:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:99:0x021e  */
    @Override // java.lang.Runnable
    public final void run() {
        boolean fullyConsumedVertical;
        boolean fullyConsumedAny;
        if (this.D.R == null) {
            G();
            return;
        }
        C();
        this.D.K();
        OverScroller overScroller = this.C;
        E7 e7 = this.D.R.J;
        if (overScroller.computeScrollOffset()) {
            int[] iArr = this.D.HB;
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int y = this.F;
            int velY = currX - y;
            int dx = this.G;
            int i = currY - dx;
            int iQA = 0;
            int iSA = 0;
            this.F = currX;
            this.G = currY;
            int i2 = 0;
            int overscrollY = 0;
            if (this.D.O(velY, i, iArr, null, 1)) {
                velY -= iArr[0];
                i -= iArr[1];
            }
            if (this.D.C != null) {
                this.D.R();
                this.D.o();
                C02946r.B("RV Scroll");
                this.D.T(this.D.Z);
                if (velY != 0) {
                    iQA = this.D.R.qA(velY, this.D.X, this.D.Z);
                    i2 = velY - iQA;
                }
                if (i != 0) {
                    iSA = this.D.R.sA(i, this.D.X, this.D.Z);
                    overscrollY = i - iSA;
                }
                C02946r.C();
                this.D.AA();
                this.D.p();
                this.D.BA(false);
                if (e7 != null && !e7.D() && e7.E()) {
                    int iB = this.D.Z.B();
                    if (iB == 0) {
                        e7.N();
                    } else if (e7.C() < iB) {
                        e7.E(velY - i2, i - overscrollY);
                    } else {
                        e7.L(iB - 1);
                        e7.E(velY - i2, i - overscrollY);
                    }
                }
            }
            if (!this.D.O.isEmpty()) {
                this.D.invalidate();
            }
            if (this.D.getOverScrollMode() != 2) {
                this.D.J(velY, i);
            }
            if (!this.D.P(iQA, iSA, i2, overscrollY, null, 1) && (i2 != 0 || overscrollY != 0)) {
                int currVelocity = (int) overScroller.getCurrVelocity();
                int i3 = 0;
                if (i2 != currX) {
                    if (i2 < 0) {
                        i3 = -currVelocity;
                    } else {
                        i3 = i2 > 0 ? currVelocity : 0;
                    }
                }
                int i4 = 0;
                if (overscrollY != currY) {
                    if (overscrollY < 0) {
                        i4 = -currVelocity;
                    } else {
                        i4 = overscrollY > 0 ? currVelocity : 0;
                    }
                }
                if (this.D.getOverScrollMode() != 2) {
                    this.D.A(i3, i4);
                }
                if (i3 != 0 || i2 == currX || overScroller.getFinalX() == 0) {
                    if (i4 == 0 && overscrollY != currY) {
                        int velX = overScroller.getFinalY();
                        if (velX == 0) {
                            overScroller.abortAnimation();
                        }
                    } else {
                        overScroller.abortAnimation();
                    }
                }
            }
            if (iQA != 0 || iSA != 0) {
                this.D.Q(iQA, iSA);
            }
            if (!this.D.awakenScrollBars()) {
                this.D.invalidate();
            }
            boolean z = i != 0 && this.D.R.H() && iSA == i;
            if (velY != 0) {
                boolean fullyConsumedVertical2 = this.D.R.G();
                if (fullyConsumedVertical2 && iQA == velY) {
                    fullyConsumedVertical = true;
                } else {
                    fullyConsumedVertical = false;
                }
            } else {
                fullyConsumedVertical = false;
            }
            boolean z2 = (velY == 0 && i == 0) || fullyConsumedVertical || z;
            boolean fullyConsumedAny2 = overScroller.isFinished();
            if (fullyConsumedAny2) {
                this.D.setScrollState(0);
                fullyConsumedAny = C0503Es.UB;
                if (fullyConsumedAny) {
                    this.D.W.A();
                }
                this.D.D(1);
            } else {
                if (!z2) {
                    boolean fullyConsumedAny3 = this.D.b(1);
                    if (!fullyConsumedAny3) {
                        this.D.setScrollState(0);
                        fullyConsumedAny = C0503Es.UB;
                        if (fullyConsumedAny) {
                            this.D.W.A();
                        }
                        this.D.D(1);
                    }
                }
                B();
                if (this.D.K != null) {
                    this.D.K.B(this.D, velY, i);
                }
            }
        }
        if (e7 != null) {
            if (e7.D()) {
                e7.E(0, 0);
            }
            if (!this.H) {
                e7.N();
            }
        }
        E();
    }
}
