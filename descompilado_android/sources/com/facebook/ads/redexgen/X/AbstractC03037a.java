package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.LinearLayout;
import com.google.ads.mediation.facebook.FacebookAdapter;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC03037a {
    private AbstractC03037a() {
    }

    public static LinearLayout B(Context context, KF nativeAd, KI attributes) {
        LinearLayout linearLayout = new LinearLayout(context);
        CH ch = new CH(context);
        ch.setText(nativeAd.L(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET));
        attributes.G(ch);
        linearLayout.addView(ch);
        return linearLayout;
    }
}
