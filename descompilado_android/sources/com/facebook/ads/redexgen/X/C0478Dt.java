package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.api.AdNativeComponentView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0478Dt extends AdNativeComponentView {
    private static final int C = (int) (1.0f * J6.B);
    private final ImageView B;

    public C0478Dt(Context context) {
        super(context);
        this.B = new CG(context);
        this.B.setScaleType(ImageView.ScaleType.CENTER_CROP);
        MZ.C(this.B, MZ.INTERNAL_AD_MEDIA);
        addView(this.B, new ViewGroup.LayoutParams(-1, -1));
        J6.O(this.B, -2130706433);
        setPadding(C, C, C, C);
    }

    @Override // com.facebook.ads.internal.api.AdNativeComponentView
    public View getAdContentsView() {
        return this.B;
    }

    public ImageView getImageCardView() {
        return this.B;
    }
}
