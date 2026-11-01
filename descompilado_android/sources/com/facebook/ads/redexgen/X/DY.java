package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class DY {
    private EW E = null;
    private ArrayList<EV> D = new ArrayList<>();
    private long B = 120;
    private long G = 120;
    private long F = 250;
    private long C = 250;

    public abstract boolean A(@NonNull AbstractC0502Er abstractC0502Er, @Nullable EX ex, @NonNull EX ex2);

    public abstract boolean B(@NonNull AbstractC0502Er abstractC0502Er, @NonNull AbstractC0502Er abstractC0502Er2, @NonNull EX ex, @NonNull EX ex2);

    public abstract boolean C(@NonNull AbstractC0502Er abstractC0502Er, @NonNull EX ex, @Nullable EX ex2);

    public abstract boolean D(@NonNull AbstractC0502Er abstractC0502Er, @NonNull EX ex, @NonNull EX ex2);

    public abstract void K(AbstractC0502Er abstractC0502Er);

    public abstract void L();

    public abstract boolean Q();

    public abstract void U();

    public static int B(AbstractC0502Er viewHolder) {
        int i = viewHolder.O & 14;
        if (viewHolder.R()) {
            return 4;
        }
        if ((i & 4) != 0) {
            return i;
        }
        int iL = viewHolder.L();
        int pos = viewHolder.H();
        if (iL == -1 || pos == -1 || iL == pos) {
            return i;
        }
        return i | 2048;
    }

    private final void C(AbstractC0502Er viewHolder) {
    }

    private final EX D() {
        return new EX();
    }

    /* JADX INFO: renamed from: E */
    public boolean mo244E(@NonNull AbstractC0502Er viewHolder) {
        return true;
    }

    public boolean F(@NonNull AbstractC0502Er viewHolder, @NonNull List<Object> list) {
        return mo244E(viewHolder);
    }

    public final void G(AbstractC0502Er viewHolder) {
        C(viewHolder);
        if (this.E != null) {
            this.E.VD(viewHolder);
        }
    }

    public final void H() {
        int size = this.D.size();
        for (int i = 0; i < size; i++) {
            this.D.get(i).onAnimationsFinished();
        }
        this.D.clear();
    }

    public final long M() {
        return this.B;
    }

    public final long N() {
        return this.C;
    }

    public final long O() {
        return this.F;
    }

    public final long P() {
        return this.G;
    }

    public final boolean R(EV listener) {
        boolean zQ = Q();
        if (listener != null) {
            if (!zQ) {
                listener.onAnimationsFinished();
            } else {
                this.D.add(listener);
            }
        }
        return zQ;
    }

    @NonNull
    public final EX S(@NonNull C0499Eo state, @NonNull AbstractC0502Er viewHolder) {
        return D().A(viewHolder);
    }

    @NonNull
    public final EX T(@NonNull C0499Eo state, @NonNull AbstractC0502Er viewHolder, int changeFlags, @NonNull List<Object> list) {
        return D().A(viewHolder);
    }

    public final void V(EW listener) {
        this.E = listener;
    }
}
