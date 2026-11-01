package com.my.target;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: UiUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class ck {

    @NonNull
    private static final AtomicInteger ko = new AtomicInteger(1);

    @NonNull
    private final Context ez;

    public static int a(int i, @NonNull Context context) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static int j(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] * 0.7f;
        return Color.HSVToColor(fArr);
    }

    public static int k(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return Color.HSVToColor(128, fArr);
    }

    public static void a(@NonNull View view, int i, int i2) {
        ColorDrawable colorDrawable = new ColorDrawable(i);
        ColorDrawable colorDrawable2 = new ColorDrawable(i2);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, colorDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, colorDrawable);
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackground(new RippleDrawable(new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed}, StateSet.WILD_CARD}, new int[]{j(i2), j(i)}), stateListDrawable, null));
        } else if (Build.VERSION.SDK_INT >= 18) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    public static void a(@NonNull View view, int i, int i2, int i3) {
        a(view, i, i2, 0, 0, i3);
    }

    public static void a(@NonNull View view, int i, int i2, int i3, int i4, int i5) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, i});
        float f = i5;
        gradientDrawable.setCornerRadius(f);
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i2, i2});
        gradientDrawable2.setCornerRadius(f);
        if (i3 != 0) {
            gradientDrawable.setStroke(i4, i3);
            gradientDrawable2.setStroke(i4, i3);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackground(new RippleDrawable(new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed}, StateSet.WILD_CARD}, new int[]{j(i2), j(i)}), stateListDrawable, null));
        } else if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    public static int br() {
        int i;
        int i2;
        if (Build.VERSION.SDK_INT < 17) {
            do {
                i = ko.get();
                i2 = i + 1;
                if (i2 > 16777215) {
                    i2 = 1;
                }
            } while (!ko.compareAndSet(i, i2));
            return i;
        }
        return View.generateViewId();
    }

    @NonNull
    public static ck x(@NonNull Context context) {
        return new ck(context);
    }

    public static void a(@NonNull View view, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            view.setAccessibilityDelegate(new a(str));
        }
    }

    public static boolean a(@NonNull Activity activity, @NonNull View view) {
        while (view.isHardwareAccelerated() && (view.getLayerType() & 1) == 0) {
            if (view.getParent() instanceof View) {
                view = (View) view.getParent();
            } else {
                Window window = activity.getWindow();
                return (window == null || (window.getAttributes().flags & 16777216) == 0) ? false : true;
            }
        }
        return false;
    }

    private ck(@NonNull Context context) {
        this.ez = context;
    }

    public int l(int i) {
        return (int) TypedValue.applyDimension(1, i, this.ez.getResources().getDisplayMetrics());
    }

    public int m(int i) {
        return (int) TypedValue.applyDimension(2, i, this.ez.getResources().getDisplayMetrics());
    }

    public static int a(@NonNull Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int i = activity.getResources().getConfiguration().orientation;
        if (1 == i) {
            return (rotation == 1 || rotation == 2) ? 9 : 1;
        }
        if (2 == i) {
            return (rotation == 2 || rotation == 3) ? 8 : 0;
        }
        g.a("Unknown screen orientation. Defaulting to portrait.");
        return 9;
    }

    public int n(int i) {
        return Math.round(i / (this.ez.getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    /* JADX INFO: compiled from: UiUtils.java */
    private static class a extends View.AccessibilityDelegate {

        @NonNull
        private final String kp;

        a(@NonNull String str) {
            this.kp = str;
        }

        @Override // android.view.View.AccessibilityDelegate
        @TargetApi(18)
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setViewIdResourceName(this.kp);
        }
    }
}
