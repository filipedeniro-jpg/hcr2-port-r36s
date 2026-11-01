package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.widget.EdgeEffect;

/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(21)
public class BW extends BV {
    @Override // com.facebook.ads.redexgen.X.BV
    public final void A(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
        edgeEffect.onPull(deltaDistance, displacement);
    }
}
