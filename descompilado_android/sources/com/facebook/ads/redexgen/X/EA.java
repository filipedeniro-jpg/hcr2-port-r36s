package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class EA extends E9 {
    public C0503Es B;
    private Scroller C;
    private final AbstractC0463De D = new C0507Ew(this);

    @Nullable
    public abstract int[] C(@NonNull AbstractC0481Dw abstractC0481Dw, @NonNull View view);

    @Nullable
    public abstract View G(AbstractC0481Dw abstractC0481Dw);

    public abstract int H(AbstractC0481Dw abstractC0481Dw, int i, int i2);

    @Override // com.facebook.ads.redexgen.X.E9
    public final boolean A(int velocityX, int velocityY) {
        AbstractC0481Dw layoutManager = this.B.getLayoutManager();
        if (layoutManager == null || this.B.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.B.getMinFlingVelocity();
        return (Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity) && E(layoutManager, velocityX, velocityY);
    }

    @Nullable
    private final E7 B(AbstractC0481Dw layoutManager) {
        return mo246F(layoutManager);
    }

    public final void B(@Nullable C0503Es recyclerView) throws IllegalStateException {
        if (this.B == recyclerView) {
            return;
        }
        if (this.B != null) {
            C();
        }
        this.B = recyclerView;
        if (this.B == null) {
            return;
        }
        D();
        this.C = new Scroller(this.B.getContext(), new DecelerateInterpolator());
        E();
    }

    private void C() {
        this.B.x(this.D);
        this.B.setOnFlingListener(null);
    }

    private void D() throws IllegalStateException {
        if (this.B.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.B.E(this.D);
        this.B.setOnFlingListener(this);
    }

    public final void E() {
        AbstractC0481Dw layoutManager;
        View viewG;
        if (this.B == null || (layoutManager = this.B.getLayoutManager()) == null || (viewG = G(layoutManager)) == null) {
            return;
        }
        int[] iArrC = C(layoutManager, viewG);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.B.w(iArrC[0], iArrC[1]);
    }

    private boolean E(@NonNull AbstractC0481Dw layoutManager, int velocityX, int velocityY) {
        E7 e7B;
        int iH;
        if (!(layoutManager instanceof InterfaceC0483Dy) || (e7B = B(layoutManager)) == null || (iH = H(layoutManager, velocityX, velocityY)) == -1) {
            return false;
        }
        e7B.L(iH);
        layoutManager.CA(e7B);
        return true;
    }

    @Nullable
    @Deprecated
    /* JADX INFO: renamed from: F */
    public E8 mo246F(AbstractC0481Dw layoutManager) {
        if (layoutManager instanceof InterfaceC0483Dy) {
            return new C0508Ex(this, this.B.getContext());
        }
        return null;
    }
}
