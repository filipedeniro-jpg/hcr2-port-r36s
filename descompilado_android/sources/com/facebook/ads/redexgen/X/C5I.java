package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5I, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C5I {
    private static final String P = C5I.class.getSimpleName();
    private final View B;
    private int C;
    private final Handler D;
    private Map<String, Integer> E;
    private boolean F;
    private C5H G;
    private final WeakReference<C1B> H;
    private final boolean I;
    private int J;
    private int K;
    private Runnable L;
    private final int M;
    private final int N;
    private long O;

    public C5I(View adView, int viewabilityThreshold, int viewabilityCheckTicker, boolean repeat, C1B listener) {
        this.D = new Handler();
        this.J = 0;
        this.K = 1000;
        this.F = true;
        this.G = new C5H(C5G.UNKNOWN);
        this.E = new HashMap();
        this.O = 0L;
        this.C = 0;
        this.B = adView;
        if (this.B.getId() == -1) {
            J6.U(this.B);
        }
        this.N = viewabilityThreshold;
        this.H = new WeakReference<>(listener);
        this.I = repeat;
        if (viewabilityCheckTicker < 0) {
            if (BuildConfigApi.isDebug()) {
                Log.w(P, "The ticker cannot be negative");
            }
            viewabilityCheckTicker = 0;
        }
        this.M = viewabilityCheckTicker;
    }

    public C5I(View mAdView, int mViewabilityThreshold, C1B mListener) {
        this(mAdView, mViewabilityThreshold, 0, false, mListener);
    }

    public C5I(View mAdView, int mViewabilityThreshold, boolean mRepeat, C1B mListener) {
        this(mAdView, mViewabilityThreshold, 0, mRepeat, mListener);
    }

    public final synchronized void A(Map<String, String> map) {
        map.put("vrc", String.valueOf(this.G.B()));
        map.put("vp", String.valueOf(this.G.A()));
        map.put("vh", new JSONObject(this.E).toString());
        map.put("vt", C0622Jj.D(this.O));
        Map<String, String> extraData = this.G.C();
        map.putAll(extraData);
    }

    public final synchronized String B() {
        return C5G.values()[this.G.B()].toString() + String.format(Locale.US, " (%.1f%%)", Float.valueOf(this.G.A() * 100.0f));
    }

    public final synchronized boolean C() {
        return this.F;
    }

    public final void D(int mViewabilityCheckInitialDelayMs) {
        this.J = mViewabilityCheckInitialDelayMs;
    }

    public final void E(int mViewabilityCheckIntervalMs) {
        if (BuildConfigApi.isDebug() && !this.I) {
            Log.w(P, "Doesn't make sense to set check interval for non-repeat checker.");
        }
        this.K = mViewabilityCheckIntervalMs;
    }

    public final synchronized void F() {
        if (this.L != null) {
            G();
        }
        this.L = new AnonymousClass04<C5I>(this) { // from class: com.facebook.ads.redexgen.X.5J
            {
                super(this);
            }

            @Override // java.lang.Runnable
            public final void run() {
                int historicalCount = 0;
                C5I c5iA = A();
                if (c5iA != null) {
                    View view = c5iA.B;
                    C1B c1b = (C1B) c5iA.H.get();
                    if (view != null && c1b != null) {
                        C5H c5hR = C5I.R(view, c5iA.N);
                        if (!c5hR.D()) {
                            c5iA.C = 0;
                        } else {
                            C5I.J(c5iA);
                        }
                        boolean z = c5iA.C > c5iA.M;
                        boolean z2 = c5iA.G != null && c5iA.G.D();
                        if (z || !c5hR.D()) {
                            c5iA.G = c5hR;
                        }
                        String strValueOf = String.valueOf(c5hR.B());
                        synchronized (c5iA) {
                            if (c5iA.E.containsKey(strValueOf)) {
                                historicalCount = ((Integer) c5iA.E.get(strValueOf)).intValue();
                            }
                            c5iA.E.put(strValueOf, Integer.valueOf(historicalCount + 1));
                        }
                        if (z && !z2) {
                            c5iA.O = System.currentTimeMillis();
                            c1b.B();
                            if (BuildConfigApi.isDebug() && AdInternalSettings.isVisibleAnimation()) {
                                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                                alphaAnimation.setDuration(500L);
                                view.startAnimation(alphaAnimation);
                            }
                            if (!c5iA.I) {
                                return;
                            }
                        } else if (!z && z2) {
                            c1b.A();
                        }
                        if (c5iA.F || c5iA.L == null) {
                            return;
                        }
                        c5iA.D.postDelayed(c5iA.L, c5iA.K);
                    }
                }
            }
        };
        this.D.postDelayed(this.L, this.J);
        this.F = false;
        this.C = 0;
        this.G = new C5H(C5G.UNKNOWN);
        this.E = new HashMap();
    }

    public final synchronized void G() {
        this.D.removeCallbacks(this.L);
        this.L = null;
        this.F = true;
        this.C = 0;
    }

    public static /* synthetic */ int J(C5I x0) {
        int i = x0.C;
        x0.C = i + 1;
        return i;
    }

    @WorkerThread
    public static C5H R(View mAdView, int mViewabilityThreshold) {
        DisplayMetrics displayMetrics;
        if (mAdView == null) {
            Z(null, false, "mAdView is null.");
            return new C5H(C5G.AD_IS_NULL);
        }
        if (mAdView.getParent() == null) {
            Z(mAdView, false, "mAdView has no parent.");
            return new C5H(C5G.INVALID_PARENT);
        }
        if (!mAdView.isShown()) {
            Z(mAdView, false, "mAdView parent is not set to VISIBLE.");
            return new C5H(C5G.INVALID_PARENT);
        }
        if (mAdView.getWindowVisibility() != 0) {
            Z(mAdView, false, "mAdView window is not set to VISIBLE.");
            return new C5H(C5G.INVALID_WINDOW);
        }
        if (mAdView.getMeasuredWidth() <= 0 || mAdView.getMeasuredHeight() <= 0) {
            Z(mAdView, false, "mAdView has invisible dimensions (w=" + mAdView.getMeasuredWidth() + ", h=" + mAdView.getMeasuredHeight());
            return new C5H(C5G.INVALID_DIMENSIONS);
        }
        if (V(mAdView) < 0.9f) {
            Z(mAdView, false, "mAdView is too transparent.");
            return new C5H(C5G.AD_IS_TRANSPARENT);
        }
        int width = mAdView.getWidth();
        int height = mAdView.getHeight();
        int[] iArr = new int[2];
        try {
            mAdView.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            if (!mAdView.getGlobalVisibleRect(rect)) {
                return new C5H(C5G.AD_IS_NOT_VISIBLE);
            }
            Context context = mAdView.getContext();
            int heightPixels = Build.VERSION.SDK_INT;
            if (heightPixels >= 17) {
                Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                displayMetrics = new DisplayMetrics();
                defaultDisplay.getRealMetrics(displayMetrics);
            } else {
                displayMetrics = context.getResources().getDisplayMetrics();
            }
            Vector<Rect> rectVector = U(mAdView);
            int areaSize = W(rectVector);
            rectVector.add(rect);
            int iW = W(rectVector) - areaSize;
            int targetVisibleAreaSize = mAdView.getMeasuredHeight();
            int areaSize2 = mAdView.getMeasuredWidth();
            float f = (iW * 1.0f) / (targetVisibleAreaSize * areaSize2);
            boolean zVB = JA.VB(context);
            int iT = T(mViewabilityThreshold, mAdView);
            float f2 = iT / 100.0f;
            if (!zVB) {
                if (iArr[0] >= 0) {
                    int targetVisibleAreaSize2 = displayMetrics.widthPixels;
                    if (targetVisibleAreaSize2 - iArr[0] >= width) {
                        int i = (int) ((((double) height) * (100.0d - ((double) iT))) / 100.0d);
                        int targetVisibleAreaSize3 = rect.top;
                        int verticalInvisibleThreshold = iArr[1];
                        if (targetVisibleAreaSize3 - verticalInvisibleThreshold > i) {
                            Z(mAdView, false, "mAdView is not visible from the top.");
                            return new C5H(C5G.AD_OFFSCREEN_TOP, f);
                        }
                        int targetVisibleAreaSize4 = iArr[1];
                        int verticalInvisibleThreshold2 = rect.bottom;
                        if ((targetVisibleAreaSize4 + height) - verticalInvisibleThreshold2 > i) {
                            Z(mAdView, false, "mAdView is not visible from the bottom.");
                            return new C5H(C5G.AD_OFFSCREEN_BOTTOM, f);
                        }
                    }
                }
                Z(mAdView, false, "mAdView is not fully on screen horizontally.");
                return new C5H(C5G.AD_OFFSCREEN_HORIZONTALLY, f);
            }
            if (f < f2) {
                Z(mAdView, false, String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", Float.valueOf(f), Float.valueOf(f2)));
                return new C5H(C5G.AD_INSUFFICIENT_VISIBLE_AREA, f);
            }
            if (!C0582Hu.C(context)) {
                Z(mAdView, false, "Screen is not interactive.");
                return new C5H(C5G.SCREEN_NOT_INTERACTIVE, f);
            }
            Map<String, String> mapB = C0581Ht.B(context);
            if (J4.D(mapB)) {
                Z(mAdView, false, "Keyguard is obstructing view.");
                return new C5H(C5G.AD_IS_OBSTRUCTED_BY_KEYGUARD, f);
            }
            if (JA.HB(context) && J4.C(mapB)) {
                Z(mAdView, false, "Ad is on top of the Lockscreen.");
                return new C5H(C5G.AD_IN_LOCKSCREEN, f, mapB);
            }
            Float fB = null;
            if (JA.MB(context)) {
                fB = C5F.B(mAdView);
            }
            if (fB != null) {
                if (fB.floatValue() == -1.0f) {
                    Z(mAdView, false, "mAdView is not in the top activity");
                    return new C5H(C5G.AD_IS_NOT_IN_ACTIVITY);
                }
                if (fB.floatValue() == 0.0f) {
                    Z(mAdView, false, "mAdView is not visible");
                    return new C5H(C5G.AD_IS_NOT_VISIBLE);
                }
            }
            if (JA.LB(context) && fB != null && fB.floatValue() < f2) {
                Locale locale = Locale.US;
                Float topActivityViewabilityPercent = Float.valueOf(f2);
                Z(mAdView, false, String.format(locale, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", fB, topActivityViewabilityPercent));
                return new C5H(C5G.AD_INSUFFICIENT_VISIBLE_AREA, f, mapB);
            }
            Z(mAdView, true, "mAdView is visible.");
            return new C5H(C5G.IS_VIEWABLE, f, mapB);
        } catch (NullPointerException e) {
            Z(mAdView, false, "Cannot get location on screen.");
            return new C5H(C5G.INVALID_DIMENSIONS);
        }
    }

    private static int S(int[] values, int target) {
        int mid = 0;
        int length = values.length;
        while (mid < length) {
            int i = mid + ((length - mid) / 2);
            if (values[i] == target) {
                return i;
            }
            if (values[i] > target) {
                length = i;
            } else {
                mid = i + 1;
            }
        }
        return -1;
    }

    @VisibleForTesting
    private static int T(int viewabilityThreshold, View adView) {
        int width = adView.getWidth() * adView.getHeight();
        return (int) Math.max(viewabilityThreshold, Math.ceil(width > 0 ? 100.0f / width : 100.0f));
    }

    private static Vector<Rect> U(View child) {
        Vector<Rect> vector = new Vector<>();
        if (child.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) child.getParent();
            for (int iIndexOfChild = viewGroup.indexOfChild(child) + 1; iIndexOfChild < i; iIndexOfChild++) {
                View childAt = viewGroup.getChildAt(iIndexOfChild);
                if (!(childAt instanceof C8N)) {
                    vector.addAll(X(childAt));
                }
            }
            vector.addAll(U(viewGroup));
        }
        return vector;
    }

    @VisibleForTesting
    private static float V(View view) {
        float alpha = view.getAlpha();
        while (view.getParent() instanceof ViewGroup) {
            view = (View) view.getParent();
            float alpha2 = view.getAlpha();
            if (alpha2 < 0.0f) {
                alpha2 = 0.0f;
            }
            if (alpha2 > 1.0f) {
                alpha2 = 1.0f;
            }
            alpha *= alpha2;
        }
        return alpha;
    }

    private static int W(Vector<Rect> vector) {
        int i;
        int size = vector.size();
        int size2 = size * 2;
        int[] iArr = new int[size2];
        int size3 = size * 2;
        int[] iArr2 = new int[size3];
        int size4 = size * 2;
        int[] x = {size * 2, size4};
        boolean[][] zArr = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, x);
        int i2 = 0;
        int yPos = 0;
        for (int yPos2 = 0; yPos2 < size; yPos2++) {
            Rect rectElementAt = vector.elementAt(yPos2);
            int i3 = i2 + 1;
            iArr[i2] = rectElementAt.left;
            int i4 = yPos + 1;
            iArr2[yPos] = rectElementAt.bottom;
            i2 = i3 + 1;
            iArr[i3] = rectElementAt.right;
            yPos = i4 + 1;
            iArr2[i4] = rectElementAt.top;
        }
        Arrays.sort(iArr);
        Arrays.sort(iArr2);
        for (int i5 = 0; i5 < size; i5++) {
            Rect rectElementAt2 = vector.elementAt(i5);
            int rightEdgeIndex = S(iArr, rectElementAt2.left);
            int iS = S(iArr, rectElementAt2.right);
            int iS2 = S(iArr2, rectElementAt2.top);
            int topEdgeIndex = rectElementAt2.bottom;
            int yPos3 = S(iArr2, topEdgeIndex);
            for (int i6 = rightEdgeIndex + 1; i6 <= iS; i6++) {
                for (int i7 = iS2 + 1; i7 <= yPos3; i7++) {
                    zArr[i6][i7] = true;
                }
            }
        }
        int i8 = 0;
        for (int yPos4 = 0; yPos4 < area; yPos4++) {
            for (int i9 = 0; i9 < j; i9++) {
                if (zArr[yPos4][i9]) {
                    int j = yPos4 - 1;
                    int i10 = iArr[yPos4] - iArr[j];
                    int j2 = i9 - 1;
                    i = i10 * (iArr2[i9] - iArr2[j2]);
                } else {
                    i = 0;
                }
                i8 += i;
            }
        }
        return i8;
    }

    private static Vector<Rect> X(View view) {
        Vector<Rect> vector = new Vector<>();
        if (view.isShown() && (Build.VERSION.SDK_INT < 11 || view.getAlpha() > 0.0f)) {
            if ((view instanceof ViewGroup) && Y(view)) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    vector.addAll(X(viewGroup.getChildAt(i)));
                }
            } else {
                Rect rect = new Rect();
                if (view.getGlobalVisibleRect(rect)) {
                    vector.add(rect);
                }
            }
        }
        return vector;
    }

    private static boolean Y(View view) {
        return view.getBackground() == null || (Build.VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0);
    }

    private static void Z(View mAdView, boolean isViewable, String reason) {
        if (BuildConfigApi.isDebug()) {
            Log.v(P, "View " + mAdView + " is " + (isViewable ? "viewable" : "not viewable") + " with reason: " + reason);
        }
    }
}
