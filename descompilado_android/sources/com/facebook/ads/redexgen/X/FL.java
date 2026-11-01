package com.facebook.ads.redexgen.X;

import android.graphics.Canvas;
import android.os.Build;
import android.view.animation.Interpolator;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class FL {
    private static final Interpolator C = new FJ();
    private static final Interpolator D = new FK();
    private static final FQ E;
    private int B = -1;

    public abstract void A(AbstractC0502Er abstractC0502Er, int i);

    public abstract int I(C0503Es c0503Es, AbstractC0502Er abstractC0502Er);

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            E = new FS();
        } else {
            E = new FR();
        }
    }

    public static int B(int flags, int layoutDirection) {
        int i = flags & 789516;
        if (i == 0) {
            return flags;
        }
        int masked = flags & (i ^ (-1));
        if (layoutDirection == 0) {
            return masked | (i << 2);
        }
        return masked | ((i << 1) & (-789517)) | (((i << 1) & 789516) << 2);
    }

    public final void B(AbstractC0502Er viewHolder, int actionState) {
        if (viewHolder != null) {
            E.HF(viewHolder.B);
        }
    }

    private final void C(Canvas c, C0503Es recyclerView, AbstractC0502Er viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        E.vD(c, recyclerView, viewHolder.B, dX, dY, actionState, isCurrentlyActive);
    }

    public final void C(C0503Es recyclerView, AbstractC0502Er viewHolder) {
        E.fB(viewHolder.B);
    }

    public final int D(int flags, int layoutDirection) {
        int i = flags & 3158064;
        if (i == 0) {
            return flags;
        }
        int masked = flags & (i ^ (-1));
        if (layoutDirection == 0) {
            return masked | (i >> 2);
        }
        return masked | ((i >> 1) & (-3158065)) | (((i >> 1) & 3158064) >> 2);
    }

    private final void D(Canvas c, C0503Es recyclerView, AbstractC0502Er viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        E.uD(c, recyclerView, viewHolder.B, dX, dY, actionState, isCurrentlyActive);
    }

    public final int E(C0503Es recyclerView, AbstractC0502Er viewHolder) {
        return D(I(recyclerView, viewHolder), C9Y.R(recyclerView));
    }

    public final long F(C0503Es recyclerView, int animationType, float animateDx, float animateDy) {
        DY itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator == null) {
            return animationType == 8 ? 200L : 250L;
        }
        return animationType == 8 ? itemAnimator.O() : itemAnimator.P();
    }

    public final void G(Canvas c, C0503Es parent, AbstractC0502Er selected, List<FF> list, int actionState, float dX, float dY) {
        C0503Es c0503Es;
        int size = list.size();
        int i = 0;
        while (true) {
            c0503Es = parent;
            if (i >= size) {
                break;
            }
            FF ff = list.get(i);
            int recoverAnimSize = c.save();
            C(c, c0503Es, ff.K, ff.L, ff.M, ff.B, false);
            c.restoreToCount(recoverAnimSize);
            i++;
        }
        if (selected != null) {
            int recoverAnimSize2 = c.save();
            c0503Es = c0503Es;
            C(c, c0503Es, selected, dX, dY, actionState, true);
            c.restoreToCount(recoverAnimSize2);
        }
        boolean z = false;
        for (int i2 = size - 1; i2 >= 0; i2--) {
            FF ff2 = list.get(i2);
            if (ff2.D && !ff2.E) {
                list.remove(i2);
            } else if (!ff2.D) {
                z = true;
            }
        }
        if (z) {
            c0503Es.invalidate();
        }
    }

    public final void H(Canvas c, C0503Es parent, AbstractC0502Er selected, List<FF> list, int actionState, float dX, float dY) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            FF ff = list.get(i);
            ff.E();
            int iSave = c.save();
            D(c, parent, ff.K, ff.L, ff.M, ff.B, false);
            c.restoreToCount(iSave);
        }
        if (selected != null) {
            int iSave2 = c.save();
            D(c, parent, selected, dX, dY, actionState, true);
            c.restoreToCount(iSave2);
        }
    }

    public final float J(float defaultValue) {
        return defaultValue;
    }

    public final float K(AbstractC0502Er viewHolder) {
        return 0.5f;
    }

    public final float L(float defaultValue) {
        return defaultValue;
    }
}
