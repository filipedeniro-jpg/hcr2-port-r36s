package com.facebook.ads.redexgen.X;

import android.view.animation.Interpolator;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ao, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractInterpolatorC0395Ao implements Interpolator {
    private final float B;
    private final float[] C;

    public AbstractInterpolatorC0395Ao(float[] values) {
        this.C = values;
        this.B = 1.0f / (this.C.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float input) {
        if (input >= 1.0f) {
            return 1.0f;
        }
        if (input <= 0.0f) {
            return 0.0f;
        }
        int iMin = Math.min((int) ((this.C.length - 1) * input), this.C.length - 2);
        float f = (input - (iMin * this.B)) / this.B;
        float quantized = this.C[iMin];
        float weight = this.C[iMin + 1];
        return quantized + ((weight - this.C[iMin]) * f);
    }
}
