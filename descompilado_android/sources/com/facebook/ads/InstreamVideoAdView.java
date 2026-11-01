package com.facebook.ads;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.facebook.ads.internal.api.InstreamVideoAdViewApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class InstreamVideoAdView extends RelativeLayout implements Ad {
    private final InstreamVideoAdViewApi mInstreamVideoAdViewApi;

    public InstreamVideoAdView(Context context, Bundle bundle) {
        super(context);
        this.mInstreamVideoAdViewApi = DynamicLoaderFactory.makeLoader(context).createInstreamVideoAdViewApi(this, context, bundle);
    }

    public InstreamVideoAdView(Context context, String str, AdSize adSize) {
        super(context);
        this.mInstreamVideoAdViewApi = DynamicLoaderFactory.makeLoader(context).createInstreamVideoAdViewApi(this, context, str, adSize);
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        this.mInstreamVideoAdViewApi.loadAd();
    }

    @Override // com.facebook.ads.Ad
    public void loadAdFromBid(String str) {
        this.mInstreamVideoAdViewApi.loadAdFromBid(str);
    }

    @Override // com.facebook.ads.Ad
    public boolean isAdInvalidated() {
        return this.mInstreamVideoAdViewApi.isAdInvalidated();
    }

    public boolean show() {
        return this.mInstreamVideoAdViewApi.show();
    }

    public void setAdListener(InstreamVideoAdListener instreamVideoAdListener) {
        this.mInstreamVideoAdViewApi.setAdListener(instreamVideoAdListener);
    }

    @Override // com.facebook.ads.Ad
    public void setExtraHints(ExtraHints extraHints) {
        this.mInstreamVideoAdViewApi.setExtraHints(extraHints);
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        this.mInstreamVideoAdViewApi.destroy();
    }

    public boolean isAdLoaded() {
        return this.mInstreamVideoAdViewApi.isAdLoaded();
    }

    @Override // com.facebook.ads.Ad
    public String getPlacementId() {
        return this.mInstreamVideoAdViewApi.getPlacementId();
    }

    @Nullable
    public Bundle getSaveInstanceState() {
        return this.mInstreamVideoAdViewApi.getSaveInstanceState();
    }
}
