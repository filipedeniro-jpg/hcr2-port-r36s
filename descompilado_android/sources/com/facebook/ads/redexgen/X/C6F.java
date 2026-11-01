package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6F, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C6F extends AnonymousClass79 {
    private final C6G B;
    private final ImageView C;

    public C6F(Context context, C6G fill) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.B = fill;
        this.C = new ImageView(context);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -1728053248});
        gradientDrawable.setCornerRadius(0.0f);
        J6.P(this.C, gradientDrawable);
        addView(this.C, new RelativeLayout.LayoutParams(-1, this.B == C6G.FILL ? -1 : -2));
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.bringChildToFront(this);
        }
    }

    public ImageView getGradientBackgroundView() {
        return this.C;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && this.B != C6G.FILL) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.C.getLayoutParams();
            layoutParams.height = (b - t) / 2;
            layoutParams.addRule(this.B == C6G.TOP_HALF ? 10 : 12);
            this.C.setLayoutParams(layoutParams);
        }
    }
}
