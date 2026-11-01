package com.facebook.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.RelativeLayout;
import androidx.annotation.Keep;
import com.facebook.ads.internal.api.AdViewApi;
import com.facebook.ads.internal.api.AdViewParentApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class AdView extends RelativeLayout implements Ad {
    private final AdViewApi mAdViewApi;
    private final AdViewParentApi mAdViewParentApi;

    @Deprecated
    public void disableAutoRefresh() {
    }

    public AdView(Context context, String str, AdSize adSize) {
        super(context);
        this.mAdViewParentApi = new AdViewParentApi() { // from class: com.facebook.ads.AdView.1
            @Override // com.facebook.ads.internal.api.AdViewParentApi
            public void onConfigurationChanged(Configuration configuration) {
                AdView.super.onConfigurationChanged(configuration);
            }
        };
        this.mAdViewApi = DynamicLoaderFactory.makeLoader(context).createAdViewApi(context, str, adSize, this.mAdViewParentApi, this);
    }

    public AdView(Context context, String str, String str2) throws Exception {
        super(context);
        this.mAdViewParentApi = new AdViewParentApi() { // from class: com.facebook.ads.AdView.1
            @Override // com.facebook.ads.internal.api.AdViewParentApi
            public void onConfigurationChanged(Configuration configuration) {
                AdView.super.onConfigurationChanged(configuration);
            }
        };
        this.mAdViewApi = DynamicLoaderFactory.makeLoader(context).createAdViewApi(context, str, str2, this.mAdViewParentApi, this);
    }

    public void setAdListener(AdListener adListener) {
        this.mAdViewApi.setAdListener(adListener);
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        this.mAdViewApi.loadAd();
    }

    @Override // com.facebook.ads.Ad
    public void loadAdFromBid(String str) {
        this.mAdViewApi.loadAdFromBid(str);
    }

    @Override // com.facebook.ads.Ad
    public boolean isAdInvalidated() {
        return this.mAdViewApi.isAdInvalidated();
    }

    @Override // com.facebook.ads.Ad
    public void setExtraHints(ExtraHints extraHints) {
        this.mAdViewApi.setExtraHints(extraHints);
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        this.mAdViewApi.destroy();
    }

    @Override // com.facebook.ads.Ad
    public String getPlacementId() {
        return this.mAdViewApi.getPlacementId();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        this.mAdViewApi.onConfigurationChanged(configuration);
    }
}
