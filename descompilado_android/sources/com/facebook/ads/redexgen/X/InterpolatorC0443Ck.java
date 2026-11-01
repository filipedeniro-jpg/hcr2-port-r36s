package com.facebook.ads.redexgen.X;

import android.view.animation.Interpolator;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ck, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class InterpolatorC0443Ck implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float t) {
        float t2 = t - 1.0f;
        return (t2 * t2 * t2 * t2 * t2) + 1.0f;
    }
}
