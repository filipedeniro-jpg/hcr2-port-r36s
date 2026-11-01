package com.facebook.ads.redexgen.X;

import android.graphics.Canvas;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FR implements FQ {
    @Override // com.facebook.ads.redexgen.X.FQ
    public final void HF(View view) {
    }

    @Override // com.facebook.ads.redexgen.X.FQ
    public void fB(View view) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // com.facebook.ads.redexgen.X.FQ
    public void uD(Canvas c, C0503Es recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        view.setTranslationX(dX);
        view.setTranslationY(dY);
    }

    @Override // com.facebook.ads.redexgen.X.FQ
    public final void vD(Canvas c, C0503Es recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    }
}
