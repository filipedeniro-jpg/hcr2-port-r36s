package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.NativeAdScrollView;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.NativeAdsManager;
import com.facebook.ads.internal.api.NativeAdScrollViewApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class GV implements NativeAdScrollViewApi {
    private final NativeAdViewAttributes B;
    private final Context C;
    private final NativeAdsManager D;
    private final int E;

    @Nullable
    private final NativeAdView.Type F;
    private final GU G;

    @Nullable
    private final NativeAdScrollView.AdViewProvider H;

    public GV(NativeAdScrollView nativeAdScrollView, Context context, NativeAdsManager manager, @Nullable NativeAdScrollView.AdViewProvider viewProvider, int nativeAdViewHeightDp, @Nullable NativeAdView.Type type, NativeAdViewAttributes attributes, int maxAds) {
        if (!manager.isLoaded()) {
            throw new IllegalStateException("NativeAdsManager not loaded");
        }
        if (type == null && viewProvider == null && nativeAdViewHeightDp <= 0) {
            throw new IllegalArgumentException("Must provide a NativeAdView.Type, AdViewProvider or a nativeAdViewHeightDp");
        }
        this.C = context;
        this.D = manager;
        this.B = attributes;
        this.H = viewProvider;
        this.F = type;
        this.E = maxAds;
        GT gt = new GT(this);
        this.G = new GU(context);
        if (this.F != null) {
            this.G.C((int) (J6.B * this.F.getHeight()));
        } else if (nativeAdViewHeightDp > 0) {
            this.G.C(((int) J6.B) * nativeAdViewHeightDp);
        }
        this.G.setAdapter(gt);
        setInset(20);
        gt.N();
        nativeAdScrollView.addView(this.G);
    }

    @Override // com.facebook.ads.internal.api.NativeAdScrollViewApi
    public final void setInset(int inset) {
        if (inset > 0) {
            float f = J6.B;
            int iRound = Math.round(inset * f);
            this.G.setPadding(iRound, 0, iRound, 0);
            this.G.setPageMargin(Math.round((inset / 2) * f));
            this.G.setClipToPadding(false);
        }
    }
}
