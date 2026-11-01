package com.facebook.ads.redexgen.X;

import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.internal.api.AdComponentView;
import com.facebook.ads.internal.api.AdComponentViewApi;
import com.facebook.ads.internal.api.AdComponentViewParentApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fe, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public class C0515Fe implements AdComponentViewApi {
    private AdComponentView B;
    private AdComponentViewParentApi C;
    private AdComponentViewParentApi D;

    public final void A(AdComponentViewApi targetApi) {
        this.D = targetApi;
        ((AdComponentViewApi) this.D).onAttachedToView(this.B, this.C);
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void addView(View child, int index, ViewGroup.LayoutParams params) {
        this.D.addView(child, index, params);
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewApi
    public final void onAttachedToView(AdComponentView adComponentView, AdComponentViewParentApi adComponentViewParentApi) {
        this.C = adComponentViewParentApi;
        this.B = adComponentView;
        this.D = adComponentViewParentApi;
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public void onAttachedToWindow() {
        this.D.onAttachedToWindow();
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public void onDetachedFromWindow() {
        this.D.onDetachedFromWindow();
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.D.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public void onVisibilityChanged(View changedView, int visibility) {
        this.D.onVisibilityChanged(changedView, visibility);
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        this.D.onWindowFocusChanged(hasWindowFocus);
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        this.D.setLayoutParams(params);
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void setMeasuredDimension(int measuredWidth, int measuredHeight) {
        this.D.setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
