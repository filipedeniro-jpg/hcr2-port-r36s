package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6L, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C6L extends AnimatorListenerAdapter {
    public final /* synthetic */ C6K B;

    public C6L(C6K this$1) {
        this.B = this$1;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        this.B.B.C.postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.6M
            @Override // java.lang.Runnable
            public final void run() {
                if (!this.B.B.B.L && this.B.B.B.B) {
                    this.B.B.B.O();
                }
            }
        }, 2000L);
    }
}
