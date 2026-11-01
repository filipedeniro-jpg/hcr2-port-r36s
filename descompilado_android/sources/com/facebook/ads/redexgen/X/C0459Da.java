package com.facebook.ads.redexgen.X;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Da, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0459Da extends DZ {
    private static TimeInterpolator M;
    private ArrayList<AbstractC0502Er> L = new ArrayList<>();
    private ArrayList<AbstractC0502Er> I = new ArrayList<>();
    private ArrayList<DX> K = new ArrayList<>();
    private ArrayList<DW> J = new ArrayList<>();
    public ArrayList<ArrayList<AbstractC0502Er>> C = new ArrayList<>();
    public ArrayList<ArrayList<DX>> G = new ArrayList<>();
    public ArrayList<ArrayList<DW>> E = new ArrayList<>();
    public ArrayList<AbstractC0502Er> B = new ArrayList<>();
    public ArrayList<AbstractC0502Er> F = new ArrayList<>();
    public ArrayList<AbstractC0502Er> H = new ArrayList<>();
    public ArrayList<AbstractC0502Er> D = new ArrayList<>();

    private void C(AbstractC0502Er holder) {
        View view = holder.B;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.H.add(holder);
        viewPropertyAnimatorAnimate.setDuration(P()).alpha(0.0f).setListener(new DR(this, holder, viewPropertyAnimatorAnimate, view)).start();
    }

    private final void D(List<AbstractC0502Er> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).B.animate().cancel();
        }
    }

    private void E(List<DW> list, AbstractC0502Er item) {
        for (int size = list.size() - 1; size >= 0; size--) {
            DW dw = list.get(size);
            if (G(dw, item) && dw.E == null && dw.D == null) {
                list.remove(dw);
            }
        }
    }

    private void F(DW changeInfo) {
        if (changeInfo.E != null) {
            G(changeInfo, changeInfo.E);
        }
        if (changeInfo.D != null) {
            G(changeInfo, changeInfo.D);
        }
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final boolean F(@NonNull AbstractC0502Er viewHolder, @NonNull List<Object> list) {
        return !list.isEmpty() || super.F(viewHolder, list);
    }

    private boolean G(DW changeInfo, AbstractC0502Er item) {
        boolean z = false;
        if (changeInfo.D == item) {
            changeInfo.D = null;
        } else if (changeInfo.E == item) {
            changeInfo.E = null;
            z = true;
        } else {
            return false;
        }
        item.B.setAlpha(1.0f);
        item.B.setTranslationX(0.0f);
        item.B.setTranslationY(0.0f);
        c(item, z);
        return true;
    }

    private void H(AbstractC0502Er holder) {
        if (M == null) {
            M = new ValueAnimator().getInterpolator();
        }
        holder.B.animate().setInterpolator(M);
        K(holder);
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final void K(AbstractC0502Er item) {
        View view = item.B;
        view.animate().cancel();
        for (int size = this.K.size() - 1; size >= 0; size--) {
            if (this.K.get(size).D == item) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                e(item);
                this.K.remove(size);
            }
        }
        E(this.J, item);
        if (this.L.remove(item)) {
            view.setAlpha(1.0f);
            g(item);
        }
        if (this.I.remove(item)) {
            view.setAlpha(1.0f);
            a(item);
        }
        for (int size2 = this.E.size() - 1; size2 >= 0; size2--) {
            ArrayList<DW> arrayList = this.E.get(size2);
            E(arrayList, item);
            if (arrayList.isEmpty()) {
                this.E.remove(size2);
            }
        }
        for (int size3 = this.G.size() - 1; size3 >= 0; size3--) {
            ArrayList<DX> arrayList2 = this.G.get(size3);
            for (int size4 = arrayList2.size() - 1; size4 >= 0; size4--) {
                if (arrayList2.get(size4).D == item) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    e(item);
                    arrayList2.remove(size4);
                    if (!arrayList2.isEmpty()) {
                        break;
                    }
                    this.G.remove(size3);
                    break;
                }
            }
        }
        for (int size5 = this.C.size() - 1; size5 >= 0; size5--) {
            ArrayList<AbstractC0502Er> arrayList3 = this.C.get(size5);
            if (arrayList3.remove(item)) {
                view.setAlpha(1.0f);
                a(item);
                if (arrayList3.isEmpty()) {
                    this.C.remove(size5);
                }
            }
        }
        this.H.remove(item);
        this.B.remove(item);
        this.D.remove(item);
        this.F.remove(item);
        l();
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final void L() {
        for (int size = this.K.size() - 1; size >= 0; size--) {
            DX dx = this.K.get(size);
            View view = dx.D.B;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            e(dx.D);
            this.K.remove(size);
        }
        for (int size2 = this.L.size() - 1; size2 >= 0; size2--) {
            AbstractC0502Er item = this.L.get(size2);
            g(item);
            this.L.remove(size2);
        }
        for (int size3 = this.I.size() - 1; size3 >= 0; size3--) {
            AbstractC0502Er abstractC0502Er = this.I.get(size3);
            abstractC0502Er.B.setAlpha(1.0f);
            a(abstractC0502Er);
            this.I.remove(size3);
        }
        for (int size4 = this.J.size() - 1; size4 >= 0; size4--) {
            F(this.J.get(size4));
        }
        this.J.clear();
        if (!Q()) {
            return;
        }
        int listCount = this.G.size();
        for (int i = listCount - 1; i >= 0; i--) {
            ArrayList<DX> arrayList = this.G.get(i);
            int listCount2 = arrayList.size();
            for (int j = listCount2 - 1; j >= 0; j--) {
                DX moveInfo = arrayList.get(j);
                View view2 = moveInfo.D.B;
                view2.setTranslationY(0.0f);
                view2.setTranslationX(0.0f);
                e(moveInfo.D);
                arrayList.remove(j);
                if (arrayList.isEmpty()) {
                    this.G.remove(arrayList);
                }
            }
        }
        for (int size5 = this.C.size() - 1; size5 >= 0; size5--) {
            ArrayList<AbstractC0502Er> arrayList2 = this.C.get(size5);
            for (int j2 = arrayList2.size() - 1; j2 >= 0; j2--) {
                AbstractC0502Er abstractC0502Er2 = arrayList2.get(j2);
                abstractC0502Er2.B.setAlpha(1.0f);
                a(abstractC0502Er2);
                arrayList2.remove(j2);
                if (arrayList2.isEmpty()) {
                    this.C.remove(arrayList2);
                }
            }
        }
        for (int size6 = this.E.size() - 1; size6 >= 0; size6--) {
            ArrayList<DW> arrayList3 = this.E.get(size6);
            for (int size7 = arrayList3.size() - 1; size7 >= 0; size7--) {
                F(arrayList3.get(size7));
                if (arrayList3.isEmpty()) {
                    this.E.remove(arrayList3);
                }
            }
        }
        D(this.H);
        D(this.F);
        D(this.B);
        D(this.D);
        H();
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final boolean Q() {
        return (this.I.isEmpty() && this.J.isEmpty() && this.K.isEmpty() && this.L.isEmpty() && this.F.isEmpty() && this.H.isEmpty() && this.B.isEmpty() && this.D.isEmpty() && this.G.isEmpty() && this.C.isEmpty() && this.E.isEmpty()) ? false : true;
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final void U() {
        boolean z = !this.L.isEmpty();
        boolean removalsPending = this.K.isEmpty();
        boolean z2 = !removalsPending;
        boolean movesPending = this.J.isEmpty();
        boolean z3 = !movesPending;
        boolean changesPending = this.I.isEmpty();
        boolean z4 = !changesPending;
        if (!z && !z2 && !z4 && !z3) {
            return;
        }
        Iterator<AbstractC0502Er> it = this.L.iterator();
        while (additionsPending) {
            C(it.next());
        }
        this.L.clear();
        if (z2) {
            ArrayList<DX> arrayList = new ArrayList<>();
            arrayList.addAll(this.K);
            this.G.add(arrayList);
            this.K.clear();
            DO r3 = new DO(this, arrayList);
            if (z) {
                C9Y.G(arrayList.get(0).D.B, r3, P());
            } else {
                r3.run();
            }
        }
        if (z3) {
            ArrayList<DW> arrayList2 = new ArrayList<>();
            arrayList2.addAll(this.J);
            this.E.add(arrayList2);
            this.J.clear();
            DP dp = new DP(this, arrayList2);
            if (z) {
                C9Y.G(arrayList2.get(0).E.B, dp, P());
            } else {
                dp.run();
            }
        }
        if (!z4) {
            return;
        }
        ArrayList<AbstractC0502Er> arrayList3 = new ArrayList<>();
        arrayList3.addAll(this.I);
        this.C.add(arrayList3);
        this.I.clear();
        DQ dq = new DQ(this, arrayList3);
        if (z || z2 || z3) {
            long jP = z ? P() : 0L;
            long removeDuration = z2 ? O() : 0L;
            long moveDuration = z3 ? N() : 0L;
            long changeDuration = Math.max(removeDuration, moveDuration);
            C9Y.G(arrayList3.get(0).B, dq, jP + changeDuration);
            return;
        }
        dq.run();
    }

    @Override // com.facebook.ads.redexgen.X.DZ
    public final boolean W(AbstractC0502Er holder) {
        H(holder);
        holder.B.setAlpha(0.0f);
        this.I.add(holder);
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.DZ
    public final boolean X(AbstractC0502Er oldHolder, AbstractC0502Er newHolder, int fromX, int fromY, int toX, int toY) {
        if (oldHolder == newHolder) {
            return Y(oldHolder, fromX, fromY, toX, toY);
        }
        float translationX = oldHolder.B.getTranslationX();
        float translationY = oldHolder.B.getTranslationY();
        float alpha = oldHolder.B.getAlpha();
        H(oldHolder);
        int i = (int) ((toX - fromX) - translationX);
        int i2 = (int) ((toY - fromY) - translationY);
        oldHolder.B.setTranslationX(translationX);
        oldHolder.B.setTranslationY(translationY);
        oldHolder.B.setAlpha(alpha);
        if (newHolder != null) {
            H(newHolder);
            newHolder.B.setTranslationX(-i);
            newHolder.B.setTranslationY(-i2);
            newHolder.B.setAlpha(0.0f);
        }
        this.J.add(new DW(oldHolder, newHolder, fromX, fromY, toX, toY));
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.DZ
    public final boolean Y(AbstractC0502Er holder, int fromX, int fromY, int toX, int toY) {
        View view = holder.B;
        View view2 = holder.B;
        int translationX = fromX + ((int) view2.getTranslationX());
        View view3 = holder.B;
        int translationY = fromY + ((int) view3.getTranslationY());
        H(holder);
        int deltaY = toX - translationX;
        int i = toY - translationY;
        if (deltaY == 0 && i == 0) {
            e(holder);
            return false;
        }
        if (deltaY != 0) {
            view.setTranslationX(-deltaY);
        }
        if (i != 0) {
            view.setTranslationY(-i);
        }
        this.K.add(new DX(holder, translationX, translationY, toX, toY));
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.DZ
    public final boolean Z(AbstractC0502Er holder) {
        H(holder);
        this.L.add(holder);
        return true;
    }

    public final void i(AbstractC0502Er holder) {
        View view = holder.B;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.B.add(holder);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(M()).setListener(new DS(this, holder, view, viewPropertyAnimatorAnimate)).start();
    }

    public final void j(DW changeInfo) {
        AbstractC0502Er abstractC0502Er = changeInfo.E;
        View view = abstractC0502Er == null ? null : abstractC0502Er.B;
        AbstractC0502Er abstractC0502Er2 = changeInfo.D;
        View newView = abstractC0502Er2 != null ? abstractC0502Er2.B : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(N());
            this.D.add(changeInfo.E);
            duration.translationX(changeInfo.F - changeInfo.B);
            duration.translationY(changeInfo.G - changeInfo.C);
            ViewPropertyAnimator oldViewAnim = duration.alpha(0.0f).setListener(new DU(this, changeInfo, duration, view));
            oldViewAnim.start();
        }
        if (newView != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = newView.animate();
            this.D.add(changeInfo.D);
            ViewPropertyAnimator newViewAnimation = viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f);
            newViewAnimation.setDuration(N()).alpha(1.0f).setListener(new DV(this, changeInfo, viewPropertyAnimatorAnimate, newView)).start();
        }
    }

    public final void k(AbstractC0502Er holder, int fromX, int fromY, int toX, int toY) {
        View view = holder.B;
        int i = toX - fromX;
        int deltaY = toY - fromY;
        if (i != 0) {
            view.animate().translationX(0.0f);
        }
        if (deltaY != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.F.add(holder);
        viewPropertyAnimatorAnimate.setDuration(O()).setListener(new DT(this, holder, i, view, deltaY, viewPropertyAnimatorAnimate)).start();
    }

    public final void l() {
        if (!Q()) {
            H();
        }
    }
}
