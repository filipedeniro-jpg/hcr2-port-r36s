package com.facebook.ads.redexgen.X;

import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.internal.androidx.support.v4.view.ViewPropertyAnimatorCompat;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9N, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C9N {
    public static boolean C = false;
    public static Field D;
    private static Field E;
    private static boolean F;
    private static Field G;
    private static boolean H;
    public WeakHashMap<View, ViewPropertyAnimatorCompat> B = null;

    /* JADX WARN: Multi-variable type inference failed */
    public void A(View view) {
        if (view instanceof InterfaceC03458q) {
            ((InterfaceC03458q) view).stopNestedScroll();
        }
    }

    private static void B(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public void B(View view, InterfaceC03518w listener) {
    }

    private final long C() {
        return ValueAnimator.getFrameDelay();
    }

    public void C(View view, int mode) {
    }

    public A5 D(View v, A5 insets) {
        return insets;
    }

    public void E(View view, float elevation) {
    }

    public final void F(View v, @Nullable C8K delegate) {
        v.setAccessibilityDelegate(delegate == null ? null : delegate.C());
    }

    public void G(View view, Runnable action, long delayMillis) {
        view.postDelayed(action, C() + delayMillis);
    }

    public void H(View view, Runnable action) {
        view.postDelayed(action, C());
    }

    public void I(View view) {
        view.postInvalidate();
    }

    public A5 J(View v, A5 insets) {
        return insets;
    }

    public void K(View view, int offset) {
        view.offsetTopAndBottom(offset);
        if (view.getVisibility() == 0) {
            B(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                B((View) parent);
            }
        }
    }

    public void L(View view, int offset) {
        view.offsetLeftAndRight(offset);
        if (view.getVisibility() == 0) {
            B(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                B((View) parent);
            }
        }
    }

    public boolean M(View view) {
        return view.getWindowToken() != null;
    }

    public boolean N(View view) {
        return false;
    }

    public final boolean O(View v) {
        boolean z = true;
        if (C) {
            return false;
        }
        if (D == null) {
            try {
                D = View.class.getDeclaredField("mAccessibilityDelegate");
                D.setAccessible(true);
            } catch (Throwable th) {
                C = true;
                return false;
            }
        }
        try {
            if (D.get(v) == null) {
                z = false;
            }
            return z;
        } catch (Throwable th2) {
            C = true;
            return false;
        }
    }

    public Display P(View view) {
        if (M(view)) {
            return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public float Q(View view) {
        return 0.0f;
    }

    public int R(View view) {
        if (!H) {
            try {
                G = View.class.getDeclaredField("mMinWidth");
                G.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            H = true;
        }
        if (G != null) {
            try {
                return ((Integer) G.get(view)).intValue();
            } catch (Exception e2) {
            }
        }
        return 0;
    }

    public int S(View view) {
        return 0;
    }

    public int T(View view) {
        if (!F) {
            try {
                E = View.class.getDeclaredField("mMinHeight");
                E.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            F = true;
        }
        if (E != null) {
            try {
                return ((Integer) E.get(view)).intValue();
            } catch (Exception e2) {
            }
        }
        return 0;
    }

    public int U(View view) {
        return 0;
    }
}
