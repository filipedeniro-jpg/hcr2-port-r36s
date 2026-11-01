package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class J6 {
    public static final DisplayMetrics C = Resources.getSystem().getDisplayMetrics();
    public static final float B = C.density;
    private static final AtomicInteger D = new AtomicInteger(1);
    private static final ConcurrentHashMap<Integer, Integer> E = new ConcurrentHashMap<>();

    private J6() {
    }

    public static int B(int sp) {
        return (int) TypedValue.applyDimension(2, sp, C);
    }

    public static void C(View touchDelegateView, View originalClickView, int widthPadding, int heightPadding) {
        if (originalClickView == null || touchDelegateView == null) {
            return;
        }
        touchDelegateView.post(new JB(originalClickView, touchDelegateView, widthPadding, heightPadding));
    }

    public static int D() {
        int result;
        int i;
        do {
            result = D.get();
            i = result + 1;
            if (i > 16777215) {
                i = 1;
            }
        } while (!D.compareAndSet(result, i));
        return result;
    }

    public static int E(TextView textView, int availableSpacePx) {
        int lines = W(textView);
        int i = 0;
        int lineHeight = textView.getLineHeight();
        while (availableSpacePx > lineHeight && i < lines) {
            i++;
            availableSpacePx -= lineHeight;
        }
        return i;
    }

    public static void F(@Nullable View view) {
        V(view, 8);
    }

    public static void G(ViewGroup layout) {
        if (Build.VERSION.SDK_INT > 19) {
            H(layout, 200);
        }
    }

    public static void H(ViewGroup layout, int duration) {
        if (Build.VERSION.SDK_INT > 19) {
            Y(layout, new AutoTransition(), duration);
        }
    }

    public static void I(ViewGroup layout, Transition transition) {
        if (Build.VERSION.SDK_INT > 19) {
            Y(layout, transition, 200);
        }
    }

    public static void J(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            G((ViewGroup) parent);
        }
    }

    public static void K(View child) {
        ViewParent parent = child.getParent();
        if (Build.VERSION.SDK_INT > 19 && (parent instanceof ViewGroup)) {
            AutoTransition autoTransition = new AutoTransition();
            autoTransition.addListener((Transition.TransitionListener) new JD(child));
            Y((ViewGroup) parent, autoTransition, 200);
            return;
        }
        M(child);
    }

    public static void L(@Nullable Toast toast, String message, int gravity, int xOffset, int yOffset) {
        if (toast == null) {
            return;
        }
        toast.setGravity(gravity, xOffset, yOffset);
        TextView toastTextView = X((ViewGroup) toast.getView());
        if (toastTextView == null) {
            return;
        }
        toastTextView.setText(message);
        toastTextView.setGravity(17);
    }

    public static void M(View view) {
        ViewGroup viewGroup;
        if (view == null || (viewGroup = (ViewGroup) view.getParent()) == null) {
            return;
        }
        viewGroup.removeView(view);
    }

    public static void N(View... views) {
        for (View view : views) {
            M(view);
        }
    }

    public static void O(View view, int color) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(new ColorDrawable(color));
        } else {
            view.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    public static void P(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void Q(TextView textView, boolean bold, int textSizeSp) {
        Typeface typefaceCreate;
        if (bold) {
            if (Build.VERSION.SDK_INT >= 21) {
                typefaceCreate = Typeface.create("sans-serif-medium", 0);
            } else {
                typefaceCreate = Typeface.create(Typeface.SANS_SERIF, 1);
            }
        } else {
            typefaceCreate = Typeface.create(Typeface.SANS_SERIF, 0);
        }
        textView.setTypeface(typefaceCreate);
        textView.setTextSize(2, textSizeSp);
    }

    public static void R(View view, int[][] states, int[] colors, int cornerRadiusPx) {
        if (view == null || states.length != colors.length) {
            return;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < states.length; i++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(colors[i]);
            gradientDrawable.setCornerRadius(cornerRadiusPx);
            stateListDrawable.addState(states[i], gradientDrawable);
        }
        P(view, stateListDrawable);
    }

    public static void S(@Nullable View view) {
        V(view, 0);
    }

    public static void T(int viewType, View view) {
        Integer num = E.get(Integer.valueOf(viewType));
        if (num != null) {
            view.setId(num.intValue());
        } else {
            U(view);
        }
    }

    public static void U(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setId(View.generateViewId());
        } else {
            view.setId(D());
        }
    }

    public static void V(@Nullable View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    private static int W(TextView textView) {
        Layout layout;
        int lines;
        if (textView == null || textView.getLayout() == null || (lines = (layout = textView.getLayout()).getLineCount()) <= 0) {
            return 0;
        }
        double ellipsisCount = layout.getEllipsisCount(lines - 1);
        double ellipsisCount2 = textView.getText().length();
        return (int) Math.ceil(ellipsisCount / (ellipsisCount2 - ellipsisCount));
    }

    @Nullable
    private static TextView X(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TextView) {
                return (TextView) childAt;
            }
            if (childAt instanceof ViewGroup) {
                X((ViewGroup) childAt);
            }
        }
        return null;
    }

    @TargetApi(19)
    private static void Y(ViewGroup layout, Transition transition, int duration) {
        transition.setDuration(duration);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        TransitionManager.beginDelayedTransition(layout, transition);
    }
}
