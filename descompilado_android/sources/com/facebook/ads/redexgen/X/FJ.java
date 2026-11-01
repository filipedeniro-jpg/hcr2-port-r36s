package com.facebook.ads.redexgen.X;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FJ implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float t) {
        return t * t * t * t * t;
    }
}
