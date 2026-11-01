package com.facebook.ads.redexgen.X;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.MotionEventCompat;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class FP extends AbstractC0460Db implements InterfaceC0488Ed {
    public int B;
    public int C;
    public FL D;
    public VelocityTracker E;
    public float F;
    public float G;
    public float H;
    public float I;
    public float J;
    public float K;
    public View L;
    public int M;
    public final List<View> N;
    public List<FF> O;
    public C0503Es P;
    public int Q;
    public AbstractC0502Er R;
    private ET S;
    private long T;
    private final float[] U;

    @Override // com.facebook.ads.redexgen.X.AbstractC0460Db
    public final void A(Rect outRect, View view, C0503Es parent, C0499Eo state) {
        outRect.setEmpty();
    }

    private int B(AbstractC0502Er viewHolder) {
        if (this.B == 2) {
            return 0;
        }
        int I = this.D.I(this.P, viewHolder);
        int iD = (this.D.D(I, C9Y.R(this.P)) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (iD == 0) {
            return 0;
        }
        int i = (I & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (Math.abs(this.F) > Math.abs(this.G)) {
            int swipeDir = E(viewHolder, iD);
            if (swipeDir > 0) {
                if ((i & swipeDir) != 0) {
                    return swipeDir;
                }
                return FL.B(swipeDir, C9Y.R(this.P));
            }
            int swipeDir2 = F(viewHolder, iD);
            if (swipeDir2 > 0) {
                return swipeDir2;
            }
        } else {
            int swipeDir3 = F(viewHolder, iD);
            if (swipeDir3 > 0) {
                return swipeDir3;
            }
            int swipeDir4 = E(viewHolder, iD);
            if (swipeDir4 > 0) {
                if ((i & swipeDir4) != 0) {
                    return swipeDir4;
                }
                return FL.B(swipeDir4, C9Y.R(this.P));
            }
        }
        return 0;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0460Db
    public final void B(Canvas c, C0503Es parent, C0499Eo state) {
        this.M = -1;
        float f = 0.0f;
        float dx = 0.0f;
        if (this.R != null) {
            G(this.U);
            f = this.U[0];
            dx = this.U[1];
        }
        this.D.H(c, parent, this.R, this.O, this.B, f, dx);
    }

    private void C() {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.S == null) {
            this.S = new FI(this);
        }
        this.P.setChildDrawingOrderCallback(this.S);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0460Db
    public final void C(Canvas c, C0503Es parent, C0499Eo state) {
        float f = 0.0f;
        float f2 = 0.0f;
        if (this.R != null) {
            G(this.U);
            f = this.U[0];
            f2 = this.U[1];
        }
        this.D.G(c, parent, this.R, this.O, this.B, f, f2);
    }

    private void D() {
        if (this.E != null) {
            this.E.recycle();
            this.E = null;
        }
    }

    public final void D(AbstractC0502Er selected, int actionState) {
        float fSignum;
        float fSignum2;
        int i;
        if (selected == this.R && actionState == this.B) {
            return;
        }
        this.T = Long.MIN_VALUE;
        int actionStateMask = this.B;
        E(selected, true);
        this.B = actionState;
        if (actionState == 2) {
            this.L = selected.B;
            C();
        }
        int i2 = (1 << ((actionState * 8) + 8)) - 1;
        boolean z = false;
        if (this.R != null) {
            AbstractC0502Er abstractC0502Er = this.R;
            if (abstractC0502Er.B.getParent() != null) {
                int iB = actionStateMask == 2 ? 0 : B(abstractC0502Er);
                D();
                switch (iB) {
                    case 1:
                    case 2:
                        fSignum2 = 0.0f;
                        fSignum = Math.signum(this.G) * this.P.getHeight();
                        break;
                    case 4:
                    case 8:
                    case 16:
                    case 32:
                        fSignum = 0.0f;
                        fSignum2 = Math.signum(this.F) * this.P.getWidth();
                        break;
                    default:
                        fSignum2 = 0.0f;
                        fSignum = 0.0f;
                        break;
                }
                if (actionStateMask == 2) {
                    i = 8;
                } else if (iB > 0) {
                    i = 2;
                } else {
                    i = 4;
                }
                G(this.U);
                float f = this.U[0];
                float targetTranslateY = this.U[1];
                FG fg = new FG(this, abstractC0502Er, i, actionStateMask, f, targetTranslateY, fSignum2, fSignum, iB, abstractC0502Er);
                fg.B(this.D.F(this.P, i, fSignum2 - f, fSignum - targetTranslateY));
                this.O.add(fg);
                fg.D();
                z = true;
            } else {
                F(abstractC0502Er.B);
                this.D.C(this.P, abstractC0502Er);
            }
            this.R = null;
        }
        if (selected != null) {
            this.Q = (this.D.E(this.P, selected) & i2) >> (this.B * 8);
            this.K = selected.B.getLeft();
            this.I = selected.B.getTop();
            this.R = selected;
            if (actionState == 2) {
                this.R.B.performHapticFeedback(0);
            }
        }
        ViewParent parent = this.P.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(this.R != null);
        }
        if (!z) {
            this.P.getLayoutManager().pA();
        }
        this.D.B(this.R, this.B);
        this.P.invalidate();
    }

    private int E(AbstractC0502Er viewHolder, int flags) {
        if ((flags & 12) != 0) {
            int velDirFlag = this.F > 0.0f ? 8 : 4;
            if (this.E != null && this.C > -1) {
                this.E.computeCurrentVelocity(1000, this.D.L(this.J));
                float xVelocity = this.E.getXVelocity(this.C);
                float yVelocity = this.E.getYVelocity(this.C);
                int velDirFlag2 = xVelocity <= 0.0f ? 4 : 8;
                float fAbs = Math.abs(xVelocity);
                if ((velDirFlag2 & flags) != 0 && velDirFlag == velDirFlag2 && fAbs >= this.D.J(this.H) && fAbs > Math.abs(yVelocity)) {
                    return velDirFlag2;
                }
            }
            float width = this.P.getWidth() * this.D.K(viewHolder);
            if ((flags & velDirFlag) != 0 && Math.abs(this.F) > width) {
                return velDirFlag;
            }
        }
        return 0;
    }

    public final int E(AbstractC0502Er viewHolder, boolean override) {
        for (int size = this.O.size() - 1; size >= 0; size--) {
            FF ff = this.O.get(size);
            if (ff.K == viewHolder) {
                ff.F |= override;
                if (!ff.D) {
                    ff.A();
                }
                this.O.remove(size);
                return ff.C;
            }
        }
        return 0;
    }

    private int F(AbstractC0502Er viewHolder, int flags) {
        if ((flags & 3) != 0) {
            int velDirFlag = this.G > 0.0f ? 2 : 1;
            if (this.E != null && this.C > -1) {
                this.E.computeCurrentVelocity(1000, this.D.L(this.J));
                float xVelocity = this.E.getXVelocity(this.C);
                float yVelocity = this.E.getYVelocity(this.C);
                int velDirFlag2 = yVelocity <= 0.0f ? 1 : 2;
                float fAbs = Math.abs(yVelocity);
                if ((velDirFlag2 & flags) != 0 && velDirFlag2 == velDirFlag && fAbs >= this.D.J(this.H) && fAbs > Math.abs(xVelocity)) {
                    return velDirFlag2;
                }
            }
            float height = this.P.getHeight() * this.D.K(viewHolder);
            if ((flags & velDirFlag) != 0 && Math.abs(this.G) > height) {
                return velDirFlag;
            }
        }
        return 0;
    }

    public final void F(View view) {
        if (view == this.L) {
            this.L = null;
            if (this.S != null) {
                this.P.setChildDrawingOrderCallback(null);
            }
        }
    }

    public final void G(FF anim, int swipeDir) {
        this.P.post(new FH(this, anim, swipeDir));
    }

    private void G(float[] outPosition) {
        if ((this.Q & 12) != 0) {
            outPosition[0] = (this.K + this.F) - this.R.B.getLeft();
        } else {
            outPosition[0] = this.R.B.getTranslationX();
        }
        if ((this.Q & 3) != 0) {
            outPosition[1] = (this.I + this.G) - this.R.B.getTop();
        } else {
            outPosition[1] = this.R.B.getTranslationY();
        }
    }

    public final boolean H() {
        int size = this.O.size();
        for (int i = 0; i < size; i++) {
            if (!this.O.get(i).D) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0488Ed
    public final void fD(View view) {
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0488Ed
    public final void gD(View view) {
        F(view);
        AbstractC0502Er abstractC0502ErZ = this.P.Z(view);
        if (abstractC0502ErZ == null) {
            return;
        }
        if (this.R != null && abstractC0502ErZ == this.R) {
            D((AbstractC0502Er) null, 0);
            return;
        }
        E(abstractC0502ErZ, false);
        if (!this.N.remove(abstractC0502ErZ.B)) {
            return;
        }
        this.D.C(this.P, abstractC0502ErZ);
    }
}
