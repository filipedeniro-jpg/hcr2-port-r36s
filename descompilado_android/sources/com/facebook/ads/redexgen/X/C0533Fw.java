package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.api.AdComponentViewApi;
import com.facebook.ads.internal.api.NativeAdLayoutApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0533Fw extends C0515Fe implements NativeAdLayoutApi {

    @Nullable
    private View B;
    private NativeAdLayout E;
    private int D = 0;
    private int C = 0;

    public final void B() {
        J6.G(this.E);
        this.E.removeView(this.B);
        this.B = null;
    }

    public final void C(AbstractC0432Bz adReportingLayout) {
        this.B = adReportingLayout;
        adReportingLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        J6.G(this.E);
        this.E.addView(this.B);
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewApiProvider
    public final AdComponentViewApi getAdComponentViewApi() {
        return this;
    }

    @Override // com.facebook.ads.internal.api.NativeAdLayoutApi
    public final void initialize(NativeAdLayout nativeAdLayout) {
        this.E = nativeAdLayout;
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.C > 0 && this.E.getMeasuredWidth() > this.C) {
            setMeasuredDimension(this.C, this.E.getMeasuredHeight());
        } else {
            if (this.E.getMeasuredWidth() >= this.D) {
                return;
            }
            setMeasuredDimension(this.D, this.E.getMeasuredHeight());
        }
    }

    @Override // com.facebook.ads.internal.api.NativeAdLayoutApi
    public final void setMaxWidth(int maxWidth) {
        this.C = maxWidth;
    }

    @Override // com.facebook.ads.internal.api.NativeAdLayoutApi
    public final void setMinWidth(int minWidth) {
        this.D = minWidth;
    }
}
