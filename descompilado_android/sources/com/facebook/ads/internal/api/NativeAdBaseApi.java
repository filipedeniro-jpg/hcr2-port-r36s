package com.facebook.ads.internal.api;

import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface NativeAdBaseApi {
    void destroy();

    void downloadMedia();

    @Nullable
    String getAdBodyText();

    @Nullable
    String getAdCallToAction();

    @Nullable
    NativeAdImageApi getAdChoicesIcon();

    @Nullable
    String getAdChoicesImageUrl();

    @Nullable
    String getAdChoicesLinkUrl();

    @Nullable
    String getAdChoicesText();

    @Nullable
    NativeAdImageApi getAdCoverImage();

    @Nullable
    String getAdHeadline();

    @Nullable
    NativeAdImageApi getAdIcon();

    @Nullable
    String getAdLinkDescription();

    @Nullable
    String getAdSocialContext();

    @Nullable
    @Deprecated
    NativeAdRatingApi getAdStarRating();

    @Nullable
    String getAdTranslation();

    @Nullable
    String getAdUntrimmedBodyText();

    @Nullable
    String getAdvertiserName();

    @Nullable
    String getId();

    String getPlacementId();

    @Nullable
    String getPromotedTranslation();

    @Nullable
    String getSponsoredTranslation();

    boolean hasCallToAction();

    boolean isAdInvalidated();

    boolean isAdLoaded();

    void loadAd();

    void loadAd(NativeAdBase.MediaCacheFlag mediaCacheFlag);

    void loadAdFromBid(String str);

    void loadAdFromBid(String str, NativeAdBase.MediaCacheFlag mediaCacheFlag);

    void onCtaBroadcast();

    void setAdListener(NativeAdListener nativeAdListener, NativeAdBase nativeAdBase);

    void setExtraHints(ExtraHints extraHints);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void unregisterView();
}
