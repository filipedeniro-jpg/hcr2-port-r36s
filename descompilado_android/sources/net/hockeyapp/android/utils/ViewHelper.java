package net.hockeyapp.android.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes2.dex */
public class ViewHelper {
    public static Drawable getGradient() {
        return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ViewCompat.MEASURED_STATE_MASK, 0});
    }
}
