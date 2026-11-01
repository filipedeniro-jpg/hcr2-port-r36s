package com.facebook.ads.redexgen.X;

import android.view.animation.Animation;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8z, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03548z extends J9 {
    public final /* synthetic */ C03428n B;

    public C03548z(C03428n this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.J9, android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        J6.G(this.B);
        J6.S(this.B.H);
        this.B.postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.90
            @Override // java.lang.Runnable
            public final void run() {
                J6.M(this.B.B.I);
                this.B.B.J(String.format(Locale.US, "viewReadyToShow('%s');", this.B.B.F));
            }
        }, 1000L);
    }
}
