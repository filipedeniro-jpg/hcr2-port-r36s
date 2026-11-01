package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class DZ extends DY {
    public boolean B = true;

    public abstract boolean W(AbstractC0502Er abstractC0502Er);

    public abstract boolean X(AbstractC0502Er abstractC0502Er, AbstractC0502Er abstractC0502Er2, int i, int i2, int i3, int i4);

    public abstract boolean Y(AbstractC0502Er abstractC0502Er, int i, int i2, int i3, int i4);

    public abstract boolean Z(AbstractC0502Er abstractC0502Er);

    @Override // com.facebook.ads.redexgen.X.DY
    public final boolean A(@NonNull AbstractC0502Er viewHolder, @Nullable EX preLayoutInfo, @NonNull EX postLayoutInfo) {
        return (preLayoutInfo == null || (preLayoutInfo.D == postLayoutInfo.D && preLayoutInfo.C == postLayoutInfo.C)) ? W(viewHolder) : Y(viewHolder, preLayoutInfo.D, preLayoutInfo.C, postLayoutInfo.D, postLayoutInfo.C);
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final boolean B(@NonNull AbstractC0502Er oldHolder, @NonNull AbstractC0502Er newHolder, @NonNull EX preInfo, @NonNull EX postInfo) {
        int i;
        int i2;
        int toLeft = preInfo.D;
        int toTop = preInfo.C;
        if (newHolder.f()) {
            i = preInfo.D;
            i2 = preInfo.C;
        } else {
            i = postInfo.D;
            i2 = postInfo.C;
        }
        return X(oldHolder, newHolder, toLeft, toTop, i, i2);
    }

    private final void C(AbstractC0502Er item) {
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final boolean C(@NonNull AbstractC0502Er viewHolder, @NonNull EX preLayoutInfo, @Nullable EX postLayoutInfo) {
        int newLeft = preLayoutInfo.D;
        int newTop = preLayoutInfo.C;
        View disappearingItemView = viewHolder.B;
        int left = postLayoutInfo == null ? disappearingItemView.getLeft() : postLayoutInfo.D;
        int top = postLayoutInfo == null ? disappearingItemView.getTop() : postLayoutInfo.C;
        if (viewHolder.U() || (newLeft == left && newTop == top)) {
            return Z(viewHolder);
        }
        disappearingItemView.layout(left, top, disappearingItemView.getWidth() + left, disappearingItemView.getHeight() + top);
        return Y(viewHolder, newLeft, newTop, left, top);
    }

    private final void D(AbstractC0502Er item) {
    }

    @Override // com.facebook.ads.redexgen.X.DY
    public final boolean D(@NonNull AbstractC0502Er viewHolder, @NonNull EX preInfo, @NonNull EX postInfo) {
        if (preInfo.D != postInfo.D || preInfo.C != postInfo.C) {
            return Y(viewHolder, preInfo.D, preInfo.C, postInfo.D, postInfo.C);
        }
        e(viewHolder);
        return false;
    }

    private final void E(AbstractC0502Er item) {
    }

    @Override // com.facebook.ads.redexgen.X.DY
    /* JADX INFO: renamed from: E, reason: collision with other method in class */
    public final boolean mo244E(@NonNull AbstractC0502Er viewHolder) {
        return !this.B || viewHolder.R();
    }

    private final void F(AbstractC0502Er item, boolean oldItem) {
    }

    private final void G(AbstractC0502Er item, boolean oldItem) {
    }

    private final void H(AbstractC0502Er item) {
    }

    private final void I(AbstractC0502Er item) {
    }

    private final void J(AbstractC0502Er item) {
    }

    public final void a(AbstractC0502Er item) {
        D(item);
        G(item);
    }

    public final void b(AbstractC0502Er item) {
        E(item);
    }

    public final void c(AbstractC0502Er item, boolean oldItem) {
        F(item, oldItem);
        G(item);
    }

    public final void d(AbstractC0502Er item, boolean oldItem) {
        G(item, oldItem);
    }

    public final void e(AbstractC0502Er item) {
        H(item);
        G(item);
    }

    public final void f(AbstractC0502Er item) {
        I(item);
    }

    public final void g(AbstractC0502Er item) {
        J(item);
        G(item);
    }

    public final void h(AbstractC0502Er item) {
        C(item);
    }
}
