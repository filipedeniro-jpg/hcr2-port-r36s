package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.97, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass97 {
    private AnonymousClass97() {
    }

    public static C9K B(final AF params, int orientation, final C1Y colors, final String clientToken, final C03308b carouselCardBehaviorHelper) {
        return orientation == 1 ? new C9K(params, colors, clientToken, carouselCardBehaviorHelper) { // from class: com.facebook.ads.redexgen.X.96
            private static final int B = (int) (20.0f * J6.B);
            private static final int C = (int) (16.0f * J6.B);

            @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
            public final boolean N() {
                return false;
            }

            @Override // com.facebook.ads.redexgen.X.C9K
            public final void P(Context context) {
                C0394An titleDescContainer = getTitleDescContainer();
                titleDescContainer.setAlignment(3);
                titleDescContainer.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                titleDescContainer.setPadding(0, 0, 0, B);
                getCtaButton().setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                LinearLayout linearLayout = new LinearLayout(context);
                J6.P(linearLayout, new ColorDrawable(-1));
                RelativeLayout.LayoutParams ctaLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
                ctaLayoutParams.addRule(3, getMediaContainer().getId());
                linearLayout.setLayoutParams(ctaLayoutParams);
                linearLayout.setOrientation(1);
                linearLayout.setPadding(C, C, C, C);
                linearLayout.addView(titleDescContainer);
                linearLayout.addView(getCtaButton());
                addView(getMediaContainer());
                addView(linearLayout);
            }
        } : new C9K(params, colors, clientToken, carouselCardBehaviorHelper) { // from class: com.facebook.ads.redexgen.X.9J
            private static final int B = (int) (12.0f * J6.B);

            @Override // com.facebook.ads.redexgen.X.C9K, com.facebook.ads.redexgen.X.AbstractC0390Aj
            public final boolean C() {
                return false;
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
            public final boolean D() {
                return false;
            }

            @Override // com.facebook.ads.redexgen.X.C9K
            public final void P(Context context) {
                C0394An titleDescContainer = getTitleDescContainer();
                titleDescContainer.setAlignment(3);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(8, getMediaContainer().getId());
                titleDescContainer.setLayoutParams(layoutParams);
                titleDescContainer.setPadding(B, B, B, B);
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -15658735});
                gradientDrawable.setCornerRadius(0.0f);
                J6.P(titleDescContainer, gradientDrawable);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams2.addRule(3, getMediaContainer().getId());
                getCtaButton().setLayoutParams(layoutParams2);
                addView(getMediaContainer());
                addView(titleDescContainer);
                addView(getCtaButton());
            }
        };
    }
}
