package com.facebook.ads;

import android.content.Context;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.facebook.ads.internal.api.NativeAdBaseApi;
import com.facebook.ads.internal.api.NativeAdImageApi;
import com.facebook.ads.internal.api.NativeAdRatingApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@Keep
public abstract class NativeAdBase implements Ad {
    final NativeAdBaseApi mNativeAdBaseApi;

    @Keep
    public enum MediaCacheFlag {
        NONE,
        ALL
    }

    @Deprecated
    public boolean isNativeConfigEnabled() {
        return false;
    }

    public static NativeAdBase fromBidPayload(Context context, String str, String str2) throws Exception {
        return DynamicLoaderFactory.makeLoader(context).createNativeAdBaseFromBidPayload(context, str, str2);
    }

    @Keep
    public static class Image {
        private final NativeAdImageApi mNativeAdImageApi;

        Image(NativeAdImageApi nativeAdImageApi) {
            this.mNativeAdImageApi = nativeAdImageApi;
        }

        public int getWidth() {
            return this.mNativeAdImageApi.getWidth();
        }

        public int getHeight() {
            return this.mNativeAdImageApi.getHeight();
        }

        @Nullable
        public static Image fromJSONObject(JSONObject jSONObject) {
            NativeAdImageApi nativeAdImageApiCreateNativeAdImageApi = DynamicLoaderFactory.makeLoaderUnsafe().createNativeAdImageApi(jSONObject);
            if (nativeAdImageApiCreateNativeAdImageApi == null) {
                return null;
            }
            return new Image(nativeAdImageApiCreateNativeAdImageApi);
        }
    }

    @Keep
    public static class Rating {
        private final NativeAdRatingApi mNativeAdRatingApi;

        Rating(NativeAdRatingApi nativeAdRatingApi) {
            this.mNativeAdRatingApi = nativeAdRatingApi;
        }

        public double getValue() {
            return this.mNativeAdRatingApi.getValue();
        }

        public double getScale() {
            return this.mNativeAdRatingApi.getScale();
        }

        @Nullable
        public static Rating fromJSONObject(JSONObject jSONObject) {
            NativeAdRatingApi nativeAdRatingApiCreateNativeAdRatingApi = DynamicLoaderFactory.makeLoaderUnsafe().createNativeAdRatingApi(jSONObject);
            if (nativeAdRatingApiCreateNativeAdRatingApi == null) {
                return null;
            }
            return new Rating(nativeAdRatingApiCreateNativeAdRatingApi);
        }
    }

    @Keep
    public enum NativeComponentTag {
        AD_ICON,
        AD_TITLE,
        AD_COVER_IMAGE,
        AD_SUBTITLE,
        AD_BODY,
        AD_CALL_TO_ACTION,
        AD_SOCIAL_CONTEXT,
        AD_CHOICES_ICON,
        AD_OPTIONS_VIEW,
        AD_MEDIA;

        public static void tagView(View view, NativeComponentTag nativeComponentTag) {
            DynamicLoaderFactory.makeLoader(view.getContext()).createNativeComponentTagApi().tagView(view, nativeComponentTag);
        }
    }

    public NativeAdBase(Context context, String str) {
        this.mNativeAdBaseApi = DynamicLoaderFactory.makeLoader(context).createNativeAdBaseApi(context, str);
    }

    public NativeAdBase(NativeAdBaseApi nativeAdBaseApi) {
        this.mNativeAdBaseApi = nativeAdBaseApi;
    }

    NativeAdBase(Context context, NativeAdBase nativeAdBase) {
        this.mNativeAdBaseApi = DynamicLoaderFactory.makeLoader(context).createNativeAdBaseApi(nativeAdBase.mNativeAdBaseApi);
    }

    public NativeAdBaseApi getInternalNativeAd() {
        return this.mNativeAdBaseApi;
    }

    public void setAdListener(NativeAdListener nativeAdListener) {
        this.mNativeAdBaseApi.setAdListener(nativeAdListener, this);
    }

    @Override // com.facebook.ads.Ad
    public void setExtraHints(ExtraHints extraHints) {
        this.mNativeAdBaseApi.setExtraHints(extraHints);
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        this.mNativeAdBaseApi.loadAd();
    }

    public void loadAd(MediaCacheFlag mediaCacheFlag) {
        this.mNativeAdBaseApi.loadAd(mediaCacheFlag);
    }

    @Override // com.facebook.ads.Ad
    public void loadAdFromBid(String str) {
        this.mNativeAdBaseApi.loadAdFromBid(str);
    }

    public void loadAdFromBid(String str, MediaCacheFlag mediaCacheFlag) {
        this.mNativeAdBaseApi.loadAdFromBid(str, mediaCacheFlag);
    }

    public void downloadMedia() {
        this.mNativeAdBaseApi.downloadMedia();
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        this.mNativeAdBaseApi.destroy();
    }

    @Override // com.facebook.ads.Ad
    public String getPlacementId() {
        return this.mNativeAdBaseApi.getPlacementId();
    }

    @Override // com.facebook.ads.Ad
    public boolean isAdInvalidated() {
        return this.mNativeAdBaseApi.isAdInvalidated();
    }

    public boolean isAdLoaded() {
        return this.mNativeAdBaseApi.isAdLoaded();
    }

    public boolean hasCallToAction() {
        return this.mNativeAdBaseApi.hasCallToAction();
    }

    public Image getAdIcon() {
        if (this.mNativeAdBaseApi.getAdIcon() == null) {
            return null;
        }
        return new Image(this.mNativeAdBaseApi.getAdIcon());
    }

    public Image getAdCoverImage() {
        if (this.mNativeAdBaseApi.getAdCoverImage() == null) {
            return null;
        }
        return new Image(this.mNativeAdBaseApi.getAdCoverImage());
    }

    @Deprecated
    public NativeAdViewAttributes getAdViewAttributes() {
        return new NativeAdViewAttributes();
    }

    @Nullable
    public String getAdvertiserName() {
        return this.mNativeAdBaseApi.getAdvertiserName();
    }

    @Nullable
    public String getAdHeadline() {
        return this.mNativeAdBaseApi.getAdHeadline();
    }

    public String getAdBodyText() {
        return this.mNativeAdBaseApi.getAdBodyText();
    }

    @Nullable
    public String getAdUntrimmedBodyText() {
        return this.mNativeAdBaseApi.getAdUntrimmedBodyText();
    }

    @Nullable
    public String getAdCallToAction() {
        return this.mNativeAdBaseApi.getAdCallToAction();
    }

    @Nullable
    public String getAdSocialContext() {
        return this.mNativeAdBaseApi.getAdSocialContext();
    }

    @Nullable
    public String getAdLinkDescription() {
        return this.mNativeAdBaseApi.getAdLinkDescription();
    }

    @Nullable
    public String getSponsoredTranslation() {
        return this.mNativeAdBaseApi.getSponsoredTranslation();
    }

    @Nullable
    public String getAdTranslation() {
        return this.mNativeAdBaseApi.getAdTranslation();
    }

    @Nullable
    public String getPromotedTranslation() {
        return this.mNativeAdBaseApi.getPromotedTranslation();
    }

    @Deprecated
    public Rating getAdStarRating() {
        if (this.mNativeAdBaseApi.getAdStarRating() == null) {
            return null;
        }
        return new Rating(this.mNativeAdBaseApi.getAdStarRating());
    }

    public String getId() {
        return this.mNativeAdBaseApi.getId();
    }

    public Image getAdChoicesIcon() {
        if (this.mNativeAdBaseApi.getAdChoicesIcon() == null) {
            return null;
        }
        return new Image(this.mNativeAdBaseApi.getAdChoicesIcon());
    }

    @Nullable
    public String getAdChoicesImageUrl() {
        return this.mNativeAdBaseApi.getAdChoicesImageUrl();
    }

    public String getAdChoicesLinkUrl() {
        return this.mNativeAdBaseApi.getAdChoicesLinkUrl();
    }

    public String getAdChoicesText() {
        return this.mNativeAdBaseApi.getAdChoicesText();
    }

    public void onCtaBroadcast() {
        this.mNativeAdBaseApi.onCtaBroadcast();
    }

    public void unregisterView() {
        this.mNativeAdBaseApi.unregisterView();
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mNativeAdBaseApi.setOnTouchListener(onTouchListener);
    }
}
