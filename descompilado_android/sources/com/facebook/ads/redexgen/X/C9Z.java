package com.facebook.ads.redexgen.X;

import android.R;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9Z, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@Deprecated
public final class C9Z {
    private static Method B;

    static {
        if (Build.VERSION.SDK_INT == 25) {
            try {
                B = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);
            } catch (Exception e) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    private C9Z() {
    }

    public static float B(@NonNull ViewConfiguration config, @NonNull Context context) {
        return Build.VERSION.SDK_INT >= 26 ? config.getScaledHorizontalScrollFactor() : D(config, context);
    }

    public static float C(@NonNull ViewConfiguration config, @NonNull Context context) {
        return Build.VERSION.SDK_INT >= 26 ? config.getScaledVerticalScrollFactor() : D(config, context);
    }

    private static float D(ViewConfiguration config, Context context) {
        if (Build.VERSION.SDK_INT >= 25 && B != null) {
            try {
                return ((Integer) B.invoke(config, new Object[0])).intValue();
            } catch (Exception e) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
            return typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return 0.0f;
    }
}
