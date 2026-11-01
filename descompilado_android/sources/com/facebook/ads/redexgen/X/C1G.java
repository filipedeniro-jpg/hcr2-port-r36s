package com.facebook.ads.redexgen.X;

import android.view.ViewGroup;
import com.facebook.ads.redexgen.X.AbstractC0502Er;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1G, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C1G<VH extends AbstractC0502Er> {
    private final ER C = new ER();
    private boolean B = false;

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 6 out of bounds for length 6
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:135)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    public final void A(VH vh, int i) {
        vh.J = i;
        if (F()) {
            vh.C = D(i);
        }
        vh.c(1, 519);
        C02946r.B("RV OnBindView");
        B(vh, i, vh.m247N());
        vh.D();
        ViewGroup.LayoutParams layoutParams = vh.B.getLayoutParams();
        if (layoutParams instanceof C0479Du) {
            ((C0479Du) layoutParams).C = true;
        }
        C02946r.C();
    }

    public abstract int C();

    public abstract void M(VH vh, int i);

    public abstract VH N(ViewGroup viewGroup, int i);

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final VH B(ViewGroup viewGroup, int i) {
        C02946r.B("RV CreateView");
        VH vh = (VH) N(viewGroup, i);
        vh.D = i;
        C02946r.C();
        return vh;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    private final void B(VH holder, int position, List<Object> payloads) {
        M(holder, position);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final long D(int position) {
        return -1L;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final int E(int i) {
        return 0;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final boolean F() {
        return this.B;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final void G(ES observer) {
        this.C.unregisterObserver(observer);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final void H(ES observer) {
        this.C.registerObserver(observer);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final void I(VH holder) {
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final void J(VH holder) {
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final void K(VH holder) {
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final void L(C0503Es recyclerView) {
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final void O(C0503Es recyclerView) {
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.1G != com.facebook.ads.internal.androidx.support.v7.widget.RecyclerView$Adapter<VH extends com.facebook.ads.redexgen.X.Er> */
    public final boolean P(VH vh) {
        return false;
    }
}
