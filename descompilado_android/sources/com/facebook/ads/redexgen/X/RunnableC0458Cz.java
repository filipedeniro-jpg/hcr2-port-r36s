package com.facebook.ads.redexgen.X;

import android.R;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0458Cz implements Runnable {
    public final /* synthetic */ AnimationAnimationListenerC0457Cy B;

    public RunnableC0458Cz(AnimationAnimationListenerC0457Cy this$1) {
        this.B = this$1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.B.C.finish();
        this.B.C.B().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
