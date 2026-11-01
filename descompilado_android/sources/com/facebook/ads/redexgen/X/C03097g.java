package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7g, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03097g extends C0401Au {
    private final ImageView B;

    public C03097g(Context context) {
        super(context);
        this.B = new ImageView(context);
        this.B.setAdjustViewBounds(true);
        addView(this.B, new RelativeLayout.LayoutParams(-2, -1));
    }

    public final void A(String url) {
        B7 b7 = new B7(this.B);
        b7.B();
        b7.E(url);
    }
}
