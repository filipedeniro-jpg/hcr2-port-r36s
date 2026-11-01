package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9P, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(16)
public class C9P extends C9O {
    @Override // com.facebook.ads.redexgen.X.C9N
    public void C(View view, int mode) {
        if (mode == 4) {
            mode = 2;
        }
        view.setImportantForAccessibility(mode);
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final void G(View view, Runnable action, long delayMillis) {
        view.postOnAnimationDelayed(action, delayMillis);
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final void H(View view, Runnable action) {
        view.postOnAnimation(action);
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final void I(View view) {
        view.postInvalidateOnAnimation();
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final boolean N(View view) {
        return view.hasTransientState();
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final int R(View view) {
        return view.getMinimumWidth();
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final int S(View view) {
        return view.getImportantForAccessibility();
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final int T(View view) {
        return view.getMinimumHeight();
    }
}
