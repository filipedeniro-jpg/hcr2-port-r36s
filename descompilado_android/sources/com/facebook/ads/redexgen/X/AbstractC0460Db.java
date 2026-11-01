package com.facebook.ads.redexgen.X;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Db, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC0460Db {
    public void A(Rect outRect, View view, C0503Es parent, C0499Eo state) {
        B(outRect, ((C0479Du) view.getLayoutParams()).A(), parent);
    }

    public void B(Canvas c, C0503Es parent, C0499Eo state) {
        C(c, parent);
    }

    @Deprecated
    private final void B(Rect outRect, int itemPosition, C0503Es parent) {
        outRect.set(0, 0, 0, 0);
    }

    @Deprecated
    private final void C(Canvas c, C0503Es parent) {
    }

    public void C(Canvas c, C0503Es parent, C0499Eo state) {
        D(c, parent);
    }

    @Deprecated
    private final void D(Canvas c, C0503Es parent) {
    }
}
