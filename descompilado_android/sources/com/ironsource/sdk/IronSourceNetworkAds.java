package com.ironsource.sdk;

import android.app.Activity;
import android.content.Context;
import com.ironsource.sdk.ISNAdView.ISNAdView;
import com.ironsource.sdk.listeners.OnBannerListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IronSourceNetworkAds {
    ISNAdView createBanner(Activity activity, ISAdSize iSAdSize);

    void getOfferWallCredits(OnOfferWallListener onOfferWallListener);

    String getToken(Context context);

    void initBanner(String str, Map<String, String> map, OnBannerListener onBannerListener);

    void initOfferWall(Map<String, String> map, OnOfferWallListener onOfferWallListener);

    boolean isAdAvailable(IronSourceAdInstance ironSourceAdInstance);

    void loadAd(IronSourceAdInstance ironSourceAdInstance, Map<String, String> map);

    void loadBanner(JSONObject jSONObject);

    void showAd(IronSourceAdInstance ironSourceAdInstance, Map<String, String> map);

    void showOfferWall(Map<String, String> map);
}
