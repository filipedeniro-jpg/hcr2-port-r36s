package com.facebook.ads.redexgen.X;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5W, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C5W {
    private static final ThreadLocal<double[]> B = new ThreadLocal<>();

    private C5W() {
    }

    @ColorInt
    public static int B(@ColorInt int color, @IntRange(from = 0, to = 255) int alpha) {
        if (alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (16777215 & color) | (alpha << 24);
    }

    @ColorInt
    public static int C(@ColorInt int color1, @ColorInt int color2, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d) float ratio) {
        float inverseRatio = 1.0f - ratio;
        return Color.argb((int) ((Color.alpha(color1) * inverseRatio) + (Color.alpha(color2) * ratio)), (int) ((Color.red(color1) * inverseRatio) + (Color.red(color2) * ratio)), (int) ((Color.green(color1) * inverseRatio) + (Color.green(color2) * ratio)), (int) ((Color.blue(color1) * inverseRatio) + (Color.blue(color2) * ratio)));
    }
}
