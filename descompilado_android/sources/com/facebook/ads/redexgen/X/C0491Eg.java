package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Eg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0491Eg {
    public C0490Ef E;
    public final /* synthetic */ C0503Es G;
    private AbstractC0500Ep J;
    public final ArrayList<AbstractC0502Er> B = new ArrayList<>();
    public ArrayList<AbstractC0502Er> D = null;
    public final ArrayList<AbstractC0502Er> C = new ArrayList<>();
    private final List<AbstractC0502Er> I = Collections.unmodifiableList(this.B);
    private int H = 2;
    public int F = 2;

    public C0491Eg(C0503Es this$0) {
        this.G = this$0;
    }

    public final void A(AbstractC0502Er holder, boolean dispatchRecycled) {
        C0503Es.O(holder);
        if (holder.O(16384)) {
            holder.c(0, 16384);
            C9Y.F(holder.B, null);
        }
        if (dispatchRecycled) {
            D(holder);
        }
        holder.G = null;
        F().H(holder);
    }

    public final void B() {
        this.B.clear();
        K();
    }

    private void B(AbstractC0502Er holder) {
        if (this.G.m249d()) {
            View view = holder.B;
            if (C9Y.S(view) == 0) {
                C9Y.D(view, 1);
            }
            if (!C9Y.O(view)) {
                holder.B(16384);
                C9Y.F(view, this.G.B.K());
            }
        }
    }

    public final void C() {
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).C();
        }
        int size2 = this.B.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.B.get(i2).C();
        }
        if (this.D != null) {
            int size3 = this.D.size();
            for (int i3 = 0; i3 < size3; i3++) {
                this.D.get(i3).C();
            }
        }
    }

    private final boolean C(AbstractC0502Er holder) {
        if (holder.U()) {
            return this.G.Z.E();
        }
        if (holder.J < 0 || holder.J >= this.G.C.C()) {
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + holder + this.G.S());
        }
        if (this.G.Z.E() || this.G.C.E(holder.J) == holder.J()) {
            return !this.G.C.F() || holder.I() == this.G.C.D(holder.J);
        }
        return false;
    }

    public final void D() {
        this.B.clear();
        if (this.D != null) {
            this.D.clear();
        }
    }

    private final void D(AbstractC0502Er holder) {
        if (this.G.Y != null) {
            this.G.Y.onViewRecycled(holder);
        }
        if (this.G.C != null) {
            this.G.C.I(holder);
        }
        if (this.G.Z != null) {
            this.G.I.P(holder);
        }
    }

    private final AbstractC0502Er E(int position) {
        int size;
        int iD;
        if (this.D == null || (size = this.D.size()) == 0) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            AbstractC0502Er abstractC0502Er = this.D.get(i);
            if (!abstractC0502Er.M() && abstractC0502Er.K() == position) {
                abstractC0502Er.B(32);
                return abstractC0502Er;
            }
        }
        if (this.G.C.F() && (iD = this.G.D.D(position)) > 0 && iD < this.G.C.C()) {
            long jD = this.G.C.D(iD);
            for (int i2 = 0; i2 < size; i2++) {
                AbstractC0502Er abstractC0502Er2 = this.D.get(i2);
                if (!abstractC0502Er2.M()) {
                    long id = abstractC0502Er2.I();
                    if (id == jD) {
                        abstractC0502Er2.B(32);
                        return abstractC0502Er2;
                    }
                }
            }
        }
        return null;
    }

    public final void E(int positionStart, int itemCount) {
        int positionEnd;
        int pos = positionStart + itemCount;
        for (int size = this.C.size() - 1; size >= 0; size--) {
            AbstractC0502Er abstractC0502Er = this.C.get(size);
            if (abstractC0502Er != null && (positionEnd = abstractC0502Er.J) >= positionStart && positionEnd < pos) {
                abstractC0502Er.B(2);
                L(size);
            }
        }
    }

    public final C0490Ef F() {
        if (this.E == null) {
            this.E = new C0490Ef();
        }
        return this.E;
    }

    private final AbstractC0502Er F(long id, int type, boolean dryRun) {
        for (int size = this.B.size() - 1; size >= 0; size--) {
            AbstractC0502Er abstractC0502Er = this.B.get(size);
            if (abstractC0502Er.I() == id && !abstractC0502Er.M()) {
                if (type == abstractC0502Er.J()) {
                    abstractC0502Er.B(32);
                    if (abstractC0502Er.U() && !this.G.Z.E()) {
                        abstractC0502Er.c(2, 14);
                        return abstractC0502Er;
                    }
                    return abstractC0502Er;
                }
                if (!dryRun) {
                    this.B.remove(size);
                    this.G.removeDetachedView(abstractC0502Er.B, false);
                    R(abstractC0502Er.B);
                }
            }
        }
        for (int size2 = this.C.size() - 1; size2 >= 0; size2--) {
            AbstractC0502Er abstractC0502Er2 = this.C.get(size2);
            if (abstractC0502Er2.I() == id) {
                if (type == abstractC0502Er2.J()) {
                    if (dryRun) {
                        return abstractC0502Er2;
                    }
                    this.C.remove(size2);
                    return abstractC0502Er2;
                }
                if (!dryRun) {
                    L(size2);
                    return null;
                }
            }
        }
        return null;
    }

    public final int G() {
        return this.B.size();
    }

    private final AbstractC0502Er G(int position, boolean dryRun) {
        View viewE;
        int scrapCount = this.B.size();
        for (int i = 0; i < scrapCount; i++) {
            AbstractC0502Er abstractC0502Er = this.B.get(i);
            if (!abstractC0502Er.M() && abstractC0502Er.K() == position && !abstractC0502Er.R() && (this.G.Z.F || !abstractC0502Er.U())) {
                abstractC0502Er.B(32);
                return abstractC0502Er;
            }
        }
        if (!dryRun && (viewE = this.G.F.E(position)) != null) {
            AbstractC0502Er abstractC0502ErQ = C0503Es.Q(viewE);
            this.G.F.Q(viewE);
            int iK = this.G.F.K(viewE);
            if (iK == -1) {
                throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + abstractC0502ErQ + this.G.S());
            }
            this.G.F.D(iK);
            U(viewE);
            abstractC0502ErQ.B(8224);
            return abstractC0502ErQ;
        }
        int scrapCount2 = this.C.size();
        for (int i2 = 0; i2 < scrapCount2; i2++) {
            AbstractC0502Er abstractC0502Er2 = this.C.get(i2);
            if (!abstractC0502Er2.R() && abstractC0502Er2.K() == position) {
                if (dryRun) {
                    return abstractC0502Er2;
                }
                this.C.remove(i2);
                return abstractC0502Er2;
            }
        }
        return null;
    }

    private final View H(int position, boolean dryRun) {
        return Y(position, dryRun, Long.MAX_VALUE).B;
    }

    public final List<AbstractC0502Er> H() {
        return this.I;
    }

    public final View I(int index) {
        return this.B.get(index).B;
    }

    private void I(ViewGroup viewGroup, boolean invalidateThis) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                I((ViewGroup) childAt, true);
            }
        }
        if (!invalidateThis) {
            return;
        }
        if (viewGroup.getVisibility() == 4) {
            viewGroup.setVisibility(0);
            viewGroup.setVisibility(4);
        } else {
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }
    }

    public final View J(int position) {
        return H(position, false);
    }

    private void J(AbstractC0502Er holder) {
        if (holder.B instanceof ViewGroup) {
            I((ViewGroup) holder.B, false);
        }
    }

    private final void K() {
        for (int i = this.C.size() - 1; i >= 0; i--) {
            L(i);
        }
        this.C.clear();
        if (C0503Es.UB) {
            this.G.W.A();
        }
    }

    public final void L() {
        int i = this.C.size();
        for (int i2 = 0; i2 < i; i2++) {
            AbstractC0502Er holder = this.C.get(i2);
            C0479Du c0479Du = (C0479Du) holder.B.getLayoutParams();
            if (c0479Du != null) {
                c0479Du.C = true;
            }
        }
    }

    private final void L(int cachedViewIndex) {
        A(this.C.get(cachedViewIndex), true);
        this.C.remove(cachedViewIndex);
    }

    public final void M() {
        if (this.G.C != null && this.G.C.F()) {
            int size = this.C.size();
            for (int i = 0; i < size; i++) {
                AbstractC0502Er abstractC0502Er = this.C.get(i);
                if (abstractC0502Er != null) {
                    abstractC0502Er.B(6);
                    abstractC0502Er.A(null);
                }
            }
            return;
        }
        K();
    }

    private boolean M(AbstractC0502Er holder, int offsetPosition, int position, long deadlineNs) {
        holder.G = this.G;
        int iJ = holder.J();
        long nanoTime = this.G.getNanoTime();
        if (deadlineNs != Long.MAX_VALUE && !this.E.I(iJ, nanoTime, deadlineNs)) {
            return false;
        }
        this.G.C.A(holder, offsetPosition);
        long endBindNs = this.G.getNanoTime();
        this.E.D(holder.J(), endBindNs - nanoTime);
        B(holder);
        if (this.G.Z.E()) {
            holder.K = position;
        }
        return true;
    }

    public final void N(int insertedAt, int count) {
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            AbstractC0502Er abstractC0502Er = this.C.get(i);
            if (abstractC0502Er != null && abstractC0502Er.J >= insertedAt) {
                abstractC0502Er.Z(count, true);
            }
        }
    }

    public final void O(int from, int to) {
        int inBetweenOffset;
        int start;
        int i;
        if (from < to) {
            inBetweenOffset = from;
            start = to;
            i = -1;
        } else {
            inBetweenOffset = to;
            start = from;
            i = 1;
        }
        int size = this.C.size();
        for (int i2 = 0; i2 < size; i2++) {
            AbstractC0502Er abstractC0502Er = this.C.get(i2);
            if (abstractC0502Er != null && abstractC0502Er.J >= inBetweenOffset && abstractC0502Er.J <= start) {
                if (abstractC0502Er.J == from) {
                    abstractC0502Er.Z(to - from, false);
                } else {
                    abstractC0502Er.Z(i, false);
                }
            }
        }
    }

    public final void P(int removedFrom, int count, boolean applyToPreLayout) {
        int i = removedFrom + count;
        for (int size = this.C.size() - 1; size >= 0; size--) {
            AbstractC0502Er abstractC0502Er = this.C.get(size);
            if (abstractC0502Er != null) {
                if (abstractC0502Er.J >= i) {
                    abstractC0502Er.Z(-count, applyToPreLayout);
                } else if (abstractC0502Er.J >= removedFrom) {
                    abstractC0502Er.B(8);
                    L(size);
                }
            }
        }
    }

    public final void Q(C1G oldAdapter, C1G newAdapter, boolean compatibleWithPrevious) {
        B();
        F().G(oldAdapter, newAdapter, compatibleWithPrevious);
    }

    public final void R(View view) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(view);
        abstractC0502ErQ.R = null;
        abstractC0502ErQ.P = false;
        abstractC0502ErQ.E();
        T(abstractC0502ErQ);
    }

    public final void S(View view) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(view);
        if (abstractC0502ErQ.W()) {
            this.G.removeDetachedView(view, false);
        }
        if (abstractC0502ErQ.V()) {
            abstractC0502ErQ.g();
        } else if (abstractC0502ErQ.M()) {
            abstractC0502ErQ.E();
        }
        T(abstractC0502ErQ);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:31:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:33:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:44:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:49:0x010c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0121 A[LOOP:0: B:43:0x00ec->B:56:0x0121, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:59:0x0102 A[EDGE_INSN: B:59:0x0102->B:46:0x0102 BREAK  A[LOOP:0: B:43:0x00ec->B:56:0x0121], SYNTHETIC] */
    public final void T(AbstractC0502Er holder) {
        boolean recycled;
        int size;
        boolean recycled2;
        C0474Dp c0474Dp;
        int targetCacheIndex;
        int i;
        if (holder.V() || holder.B.getParent() != null) {
            throw new IllegalArgumentException("Scrapped or attached views may not be recycled. isScrap:" + holder.V() + " isAttached:" + (holder.B.getParent() != null) + this.G.S());
        }
        if (holder.W()) {
            throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + holder + this.G.S());
        }
        if (holder.f()) {
            throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + this.G.S());
        }
        boolean zO = holder.O();
        int targetCacheIndex2 = (this.G.C != null && zO && this.G.C.P(holder)) ? 1 : 0;
        boolean z = false;
        boolean z2 = false;
        if (targetCacheIndex2 == 0) {
            boolean recycled3 = holder.T();
            if (recycled3) {
                if (this.F > 0) {
                    recycled = holder.O(IronSourceError.ERROR_CAPPED_PER_SESSION);
                    if (!recycled) {
                        size = this.C.size();
                        if (size >= this.F && size > 0) {
                            L(0);
                            size--;
                        }
                        recycled2 = C0503Es.UB;
                        if (recycled2 && size > 0) {
                            c0474Dp = this.G.W;
                            targetCacheIndex = holder.J;
                            if (!c0474Dp.C(targetCacheIndex)) {
                                i = size - 1;
                                while (i >= 0) {
                                    if (!this.G.W.C(this.C.get(i).J)) {
                                        break;
                                    } else {
                                        i--;
                                    }
                                }
                                size = i + 1;
                            }
                        }
                        this.C.add(size, holder);
                        z = true;
                    }
                }
                if (!z) {
                    A(holder, true);
                    z2 = true;
                }
            }
        } else {
            if (this.F > 0) {
                recycled = holder.O(IronSourceError.ERROR_CAPPED_PER_SESSION);
                if (!recycled) {
                    size = this.C.size();
                    if (size >= this.F) {
                        L(0);
                        size--;
                    }
                    recycled2 = C0503Es.UB;
                    if (recycled2) {
                        c0474Dp = this.G.W;
                        targetCacheIndex = holder.J;
                        if (!c0474Dp.C(targetCacheIndex)) {
                            i = size - 1;
                            while (i >= 0) {
                                if (!this.G.W.C(this.C.get(i).J)) {
                                    break;
                                    break;
                                }
                                i--;
                            }
                            size = i + 1;
                        }
                    }
                    this.C.add(size, holder);
                    z = true;
                }
            }
            if (!z) {
                A(holder, true);
                z2 = true;
            }
        }
        this.G.I.P(holder);
        if (!z && !z2 && zO) {
            holder.G = null;
        }
    }

    public final void U(View view) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(view);
        if (abstractC0502ErQ.O(12) || !abstractC0502ErQ.X() || this.G.I(abstractC0502ErQ)) {
            if (abstractC0502ErQ.R() && !abstractC0502ErQ.U() && !this.G.C.F()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + this.G.S());
            }
            abstractC0502ErQ.e(this, false);
            this.B.add(abstractC0502ErQ);
            return;
        }
        if (this.D == null) {
            this.D = new ArrayList<>();
        }
        abstractC0502ErQ.e(this, true);
        this.D.add(abstractC0502ErQ);
    }

    public final void V(C0490Ef pool) {
        if (this.E != null) {
            this.E.C();
        }
        this.E = pool;
        if (pool != null) {
            this.E.A(this.G.getAdapter());
        }
    }

    public final void W(AbstractC0500Ep extension) {
        this.J = extension;
    }

    public final void X(int viewCount) {
        this.H = viewCount;
        a();
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0285  */
    /* JADX WARN: Code duplicated, block: B:109:0x0297  */
    @Nullable
    public final AbstractC0502Er Y(int position, boolean dryRun, long deadlineNs) {
        C0479Du rvLayoutParams;
        View viewA;
        C0491Eg c0491Eg = this;
        if (position < 0 || position >= c0491Eg.G.Z.B()) {
            throw new IndexOutOfBoundsException("Invalid item position " + position + "(" + position + "). Item count:" + c0491Eg.G.Z.B() + c0491Eg.G.S());
        }
        boolean z = false;
        AbstractC0502Er abstractC0502ErB = null;
        if (c0491Eg.G.Z.E()) {
            abstractC0502ErB = c0491Eg.E(position);
            z = abstractC0502ErB != null;
        }
        if (abstractC0502ErB == null && (abstractC0502ErB = c0491Eg.G(position, dryRun)) != null) {
            if (!c0491Eg.C(abstractC0502ErB)) {
                if (!dryRun) {
                    abstractC0502ErB.B(4);
                    if (abstractC0502ErB.V()) {
                        c0491Eg.G.removeDetachedView(abstractC0502ErB.B, false);
                        abstractC0502ErB.g();
                    } else if (abstractC0502ErB.M()) {
                        abstractC0502ErB.E();
                    }
                    c0491Eg.T(abstractC0502ErB);
                }
                abstractC0502ErB = null;
            } else {
                z = true;
            }
        }
        if (abstractC0502ErB == null) {
            int iD = c0491Eg.G.D.D(position);
            if (iD >= 0) {
                int offsetPosition = c0491Eg.G.C.C();
                if (iD < offsetPosition) {
                    int iE = c0491Eg.G.C.E(iD);
                    if (c0491Eg.G.C.F() && (abstractC0502ErB = c0491Eg.F(c0491Eg.G.C.D(iD), iE, dryRun)) != null) {
                        c0491Eg = c0491Eg;
                        abstractC0502ErB.J = iD;
                        z = true;
                    }
                    if (abstractC0502ErB == null && c0491Eg.J != null && (viewA = c0491Eg.J.A(c0491Eg, position, iE)) != null) {
                        abstractC0502ErB = c0491Eg.G.Z(viewA);
                        if (abstractC0502ErB == null) {
                            throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + c0491Eg.G.S());
                        }
                        if (abstractC0502ErB.f()) {
                            throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + c0491Eg.G.S());
                        }
                    }
                    if (abstractC0502ErB == null && (abstractC0502ErB = c0491Eg.F().F(iE)) != null) {
                        abstractC0502ErB.a();
                        if (C0503Es.XB) {
                            c0491Eg.J(abstractC0502ErB);
                        }
                    }
                    AbstractC0502Er holder = abstractC0502ErB;
                    if (holder == null) {
                        long nanoTime = c0491Eg.G.getNanoTime();
                        if (deadlineNs == Long.MAX_VALUE || c0491Eg.E.J(iE, nanoTime, deadlineNs)) {
                            abstractC0502ErB = c0491Eg.G.C.B(c0491Eg.G, iE);
                            if (C0503Es.UB) {
                                View view = abstractC0502ErB.B;
                                C0503Es innerView = C0503Es.P(view);
                                if (innerView != null) {
                                    abstractC0502ErB.E = new WeakReference<>(innerView);
                                }
                            }
                            long nanoTime2 = c0491Eg.G.getNanoTime();
                            c0491Eg = c0491Eg;
                            c0491Eg.E.E(iE, nanoTime2 - nanoTime);
                        } else {
                            return null;
                        }
                    } else {
                        abstractC0502ErB = holder;
                    }
                }
            }
            StringBuilder sbAppend = new StringBuilder().append("Inconsistency detected. Invalid item position ").append(position).append("(offset:").append(iD).append(").").append("state:");
            int offsetPosition2 = c0491Eg.G.Z.B();
            throw new IndexOutOfBoundsException(sbAppend.append(offsetPosition2).append(c0491Eg.G.S()).toString());
        }
        if (z && !c0491Eg.G.Z.E() && abstractC0502ErB.O(8192)) {
            abstractC0502ErB.c(0, 8192);
            if (c0491Eg.G.Z.N) {
                int changeFlags = DY.B(abstractC0502ErB);
                c0491Eg.G.s(abstractC0502ErB, c0491Eg.G.N.T(c0491Eg.G.Z, abstractC0502ErB, changeFlags | 4096, abstractC0502ErB.m247N()));
            }
        }
        boolean zM = false;
        boolean bound = c0491Eg.G.Z.E();
        if (bound) {
            boolean bound2 = abstractC0502ErB.Q();
            if (bound2) {
                abstractC0502ErB.K = position;
            } else if (abstractC0502ErB.Q() || abstractC0502ErB.Y() || abstractC0502ErB.R()) {
                zM = c0491Eg.M(abstractC0502ErB, c0491Eg.G.D.D(position), position, deadlineNs);
            }
        } else if (abstractC0502ErB.Q()) {
            zM = c0491Eg.M(abstractC0502ErB, c0491Eg.G.D.D(position), position, deadlineNs);
        } else {
            zM = c0491Eg.M(abstractC0502ErB, c0491Eg.G.D.D(position), position, deadlineNs);
        }
        ViewGroup.LayoutParams layoutParams = abstractC0502ErB.B.getLayoutParams();
        if (layoutParams == null) {
            rvLayoutParams = (C0479Du) c0491Eg.G.generateDefaultLayoutParams();
            abstractC0502ErB.B.setLayoutParams(rvLayoutParams);
        } else if (!c0491Eg.G.checkLayoutParams(layoutParams)) {
            rvLayoutParams = (C0479Du) c0491Eg.G.generateLayoutParams(layoutParams);
            abstractC0502ErB.B.setLayoutParams(rvLayoutParams);
        } else {
            rvLayoutParams = (C0479Du) layoutParams;
        }
        rvLayoutParams.E = abstractC0502ErB;
        rvLayoutParams.D = z && zM;
        return abstractC0502ErB;
    }

    public final void Z(AbstractC0502Er holder) {
        if (holder.P) {
            this.D.remove(holder);
        } else {
            this.B.remove(holder);
        }
        holder.R = null;
        holder.P = false;
        holder.E();
    }

    public final void a() {
        this.F = this.H + (this.G.R != null ? this.G.R.F : 0);
        for (int size = this.C.size() - 1; size >= 0; size--) {
            int size2 = this.C.size();
            int i = this.F;
            if (size2 > i) {
                L(size);
            } else {
                return;
            }
        }
    }
}
