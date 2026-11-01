package com.facebook.ads.redexgen.X;

import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9U, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(21)
public class C9U extends C9S {
    private static ThreadLocal<Rect> B;

    @Override // com.facebook.ads.redexgen.X.C9N
    public final void A(View view) {
        view.stopNestedScroll();
    }

    private static Rect B() {
        if (B == null) {
            B = new ThreadLocal<>();
        }
        Rect rect = B.get();
        if (rect == null) {
            rect = new Rect();
            B.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final void B(View view, final InterfaceC03518w listener) {
        if (listener == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.facebook.ads.redexgen.X.9T
                @Override // android.view.View.OnApplyWindowInsetsListener
                public final WindowInsets onApplyWindowInsets(View view2, WindowInsets insets) {
                    return (WindowInsets) A5.C(listener.WD(view2, A5.B(insets)));
                }
            });
        }
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final A5 D(View v, A5 insets) {
        WindowInsets windowInsets = (WindowInsets) A5.C(insets);
        WindowInsets windowInsetsDispatchApplyWindowInsets = v.dispatchApplyWindowInsets(windowInsets);
        if (windowInsetsDispatchApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(windowInsetsDispatchApplyWindowInsets);
        }
        return A5.B(windowInsets);
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final void E(View view, float elevation) {
        view.setElevation(elevation);
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final A5 J(View v, A5 insets) {
        WindowInsets windowInsets = (WindowInsets) A5.C(insets);
        WindowInsets windowInsetsOnApplyWindowInsets = v.onApplyWindowInsets(windowInsets);
        if (windowInsetsOnApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(windowInsetsOnApplyWindowInsets);
        }
        return A5.B(windowInsets);
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public void K(View view, int offset) {
        Rect parentRect = B();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            parentRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !parentRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        super.K(view, offset);
        if (z && parentRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(parentRect);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public void L(View view, int offset) {
        Rect parentRect = B();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            parentRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !parentRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        super.L(view, offset);
        if (z && parentRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(parentRect);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final float Q(View view) {
        return view.getElevation();
    }
}
