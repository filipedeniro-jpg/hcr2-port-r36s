package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class RunnableC0476Dr implements Runnable {
    public static final ThreadLocal<RunnableC0476Dr> F = new ThreadLocal<>();
    public static Comparator<C0475Dq> G = new C0472Dn();
    public long B;
    public long C;
    public ArrayList<C0503Es> D = new ArrayList<>();
    private ArrayList<C0475Dq> E = new ArrayList<>();

    public final void A(C0503Es recyclerView) {
        this.D.add(recyclerView);
    }

    private void B() {
        C0475Dq c0475Dq;
        int size = this.D.size();
        int i = 0;
        for (int totalTaskCount = 0; totalTaskCount < size; totalTaskCount++) {
            C0503Es view = this.D.get(totalTaskCount);
            if (view.getWindowVisibility() == 0) {
                view.W.B(view, false);
                i += view.W.B;
            }
        }
        this.E.ensureCapacity(i);
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            C0503Es c0503Es = this.D.get(i3);
            int totalTaskIndex = c0503Es.getWindowVisibility();
            if (totalTaskIndex == 0) {
                C0474Dp c0474Dp = c0503Es.W;
                int totalTaskIndex2 = c0474Dp.D;
                int iAbs = Math.abs(totalTaskIndex2);
                int totalTaskIndex3 = c0474Dp.E;
                int iAbs2 = iAbs + Math.abs(totalTaskIndex3);
                for (int i4 = 0; i4 < j * 2; i4 += 2) {
                    int j = this.E.size();
                    if (i2 >= j) {
                        c0475Dq = new C0475Dq();
                        this.E.add(c0475Dq);
                    } else {
                        c0475Dq = this.E.get(i2);
                    }
                    int totalTaskCount2 = c0474Dp.C[i4 + 1];
                    c0475Dq.C = totalTaskCount2 <= iAbs2;
                    c0475Dq.F = iAbs2;
                    c0475Dq.B = totalTaskCount2;
                    c0475Dq.E = c0503Es;
                    c0475Dq.D = c0474Dp.C[i4];
                    i2++;
                }
            }
        }
        Collections.sort(this.E, G);
    }

    public final void B(C0503Es recyclerView, int prefetchDx, int prefetchDy) {
        if (recyclerView.isAttachedToWindow() && this.C == 0) {
            this.C = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.W.D(prefetchDx, prefetchDy);
    }

    private void C(C0475Dq task, long deadlineNs) {
        AbstractC0502Er abstractC0502ErH = H(task.E, task.D, task.C ? Long.MAX_VALUE : deadlineNs);
        if (abstractC0502ErH != null && abstractC0502ErH.E != null && abstractC0502ErH.Q() && !abstractC0502ErH.R()) {
            G(abstractC0502ErH.E.get(), deadlineNs);
        }
    }

    public final void C(C0503Es recyclerView) {
        this.D.remove(recyclerView);
    }

    private void D(long deadlineNs) {
        for (int i = 0; i < this.E.size(); i++) {
            C0475Dq c0475Dq = this.E.get(i);
            if (c0475Dq.E == null) {
                return;
            }
            C(c0475Dq, deadlineNs);
            c0475Dq.A();
        }
    }

    private static boolean E(C0503Es view, int position) {
        int i = view.F.I();
        for (int i2 = 0; i2 < i; i2++) {
            AbstractC0502Er abstractC0502ErQ = C0503Es.Q(view.F.H(i2));
            if (abstractC0502ErQ.J == position && !abstractC0502ErQ.R()) {
                return true;
            }
        }
        return false;
    }

    private final void F(long deadlineNs) {
        B();
        D(deadlineNs);
    }

    private void G(@Nullable C0503Es innerView, long deadlineNs) {
        if (innerView == null) {
            return;
        }
        if (innerView.H && innerView.F.I() != 0) {
            innerView.t();
        }
        C0474Dp c0474Dp = innerView.W;
        c0474Dp.B(innerView, true);
        if (c0474Dp.B == 0) {
            return;
        }
        try {
            C02946r.B("RV Nested Prefetch");
            innerView.Z.F(innerView.C);
            for (int i = 0; i < c0474Dp.B * 2; i += 2) {
                H(innerView, c0474Dp.C[i], deadlineNs);
            }
            C02946r.C();
        } catch (Throwable th) {
            C02946r.C();
            throw th;
        }
    }

    private AbstractC0502Er H(C0503Es view, int position, long deadlineNs) {
        if (E(view, position)) {
            return null;
        }
        C0491Eg c0491Eg = view.X;
        try {
            view.o();
            AbstractC0502Er abstractC0502ErY = c0491Eg.Y(position, false, deadlineNs);
            if (abstractC0502ErY != null) {
                if (abstractC0502ErY.Q() && !abstractC0502ErY.R()) {
                    c0491Eg.S(abstractC0502ErY.B);
                } else {
                    c0491Eg.A(abstractC0502ErY, false);
                }
            }
            return abstractC0502ErY;
        } finally {
            view.q(false);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            C02946r.B("RV Prefetch");
            if (!this.D.isEmpty()) {
                int size = this.D.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    C0503Es c0503Es = this.D.get(i);
                    if (c0503Es.getWindowVisibility() == 0) {
                        jMax = Math.max(c0503Es.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    long latestFrameVsyncMs = TimeUnit.MILLISECONDS.toNanos(jMax);
                    F(latestFrameVsyncMs + this.B);
                }
            }
        } finally {
            this.C = 0L;
            C02946r.C();
        }
    }
}
