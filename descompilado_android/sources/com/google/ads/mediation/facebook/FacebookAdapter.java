package com.google.ads.mediation.facebook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Keep;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.MediaViewListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class FacebookAdapter extends FacebookMediationAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    public static final String KEY_ID = "id";
    public static final String KEY_SOCIAL_CONTEXT_ASSET = "social_context";
    private static final int MAX_STAR_RATING = 5;
    private AdView mAdView;
    private MediationBannerListener mBannerListener;
    private InterstitialAd mInterstitialAd;
    private MediationInterstitialListener mInterstitialListener;
    private boolean mIsImpressionRecorded;
    private MediaView mMediaView;
    private NativeAd mNativeAd;
    private MediationNativeListener mNativeListener;
    private RelativeLayout mWrappedAdView;

    private interface NativeAdMapperListener {
        void onMappingFailed();

        void onMappingSuccess();
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        AdView adView = this.mAdView;
        if (adView != null) {
            adView.destroy();
        }
        InterstitialAd interstitialAd = this.mInterstitialAd;
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        NativeAd nativeAd = this.mNativeAd;
        if (nativeAd != null) {
            nativeAd.unregisterView();
            this.mNativeAd.destroy();
        }
        MediaView mediaView = this.mMediaView;
        if (mediaView != null) {
            mediaView.destroy();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(final Context context, MediationBannerListener mediationBannerListener, Bundle bundle, final AdSize adSize, final MediationAdRequest mediationAdRequest, Bundle bundle2) {
        MediationBannerListener mediationBannerListener2;
        this.mBannerListener = mediationBannerListener;
        if (!isValidRequestParameters(context, bundle) && (mediationBannerListener2 = this.mBannerListener) != null) {
            mediationBannerListener2.onAdFailedToLoad(this, 1);
            return;
        }
        if (adSize == null) {
            Log.w(TAG, "Fail to request banner ad, adSize is null");
            this.mBannerListener.onAdFailedToLoad(this, 1);
            return;
        }
        if (getAdSize(context, adSize) == null) {
            Log.w(TAG, "The input ad size " + adSize.toString() + " is not supported at this moment.");
            this.mBannerListener.onAdFailedToLoad(this, 3);
            return;
        }
        final String string = bundle.getString("pubid");
        FacebookInitializer.getInstance().initialize(context, string, new FacebookInitializer.Listener() { // from class: com.google.ads.mediation.facebook.FacebookAdapter.1
            @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
            public void onInitializeSuccess() {
                FacebookAdapter.this.createAndLoadBannerAd(context, string, adSize, mediationAdRequest);
            }

            @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
            public void onInitializeError(String str) {
                Log.w(FacebookMediationAdapter.TAG, "Failed to load ad from Facebook: " + str);
                if (FacebookAdapter.this.mBannerListener != null) {
                    FacebookAdapter.this.mBannerListener.onAdFailedToLoad(FacebookAdapter.this, 0);
                }
            }
        });
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.mWrappedAdView;
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(final Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, final MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.mInterstitialListener = mediationInterstitialListener;
        if (!isValidRequestParameters(context, bundle)) {
            this.mInterstitialListener.onAdFailedToLoad(this, 1);
        } else {
            final String string = bundle.getString("pubid");
            FacebookInitializer.getInstance().initialize(context, string, new FacebookInitializer.Listener() { // from class: com.google.ads.mediation.facebook.FacebookAdapter.2
                @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
                public void onInitializeSuccess() {
                    FacebookAdapter.this.createAndLoadInterstitial(context, string, mediationAdRequest);
                }

                @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
                public void onInitializeError(String str) {
                    Log.w(FacebookMediationAdapter.TAG, "Failed to load ad from Facebook: " + str);
                    if (FacebookAdapter.this.mInterstitialListener != null) {
                        FacebookAdapter.this.mInterstitialListener.onAdFailedToLoad(FacebookAdapter.this, 0);
                    }
                }
            });
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        if (this.mInterstitialAd.isAdLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(final Context context, MediationNativeListener mediationNativeListener, Bundle bundle, final NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.mNativeListener = mediationNativeListener;
        if (!isValidRequestParameters(context, bundle)) {
            this.mNativeListener.onAdFailedToLoad(this, 1);
            return;
        }
        if (!nativeMediationAdRequest.isAppInstallAdRequested() || !nativeMediationAdRequest.isContentAdRequested()) {
            Log.w(TAG, "Failed to request native ad. Both app install and content ad should be requested");
            this.mNativeListener.onAdFailedToLoad(this, 1);
        } else {
            final String string = bundle.getString("pubid");
            FacebookInitializer.getInstance().initialize(context, string, new FacebookInitializer.Listener() { // from class: com.google.ads.mediation.facebook.FacebookAdapter.3
                @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
                public void onInitializeSuccess() {
                    FacebookAdapter.this.createAndLoadNativeAd(context, string, nativeMediationAdRequest);
                }

                @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
                public void onInitializeError(String str) {
                    Log.w(FacebookMediationAdapter.TAG, "Failed to load ad from Facebook: " + str);
                    if (FacebookAdapter.this.mNativeListener != null) {
                        FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 0);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int convertErrorCode(AdError adError) {
        if (adError == null) {
            return 0;
        }
        int errorCode = adError.getErrorCode();
        if (errorCode == 2000) {
            return 2;
        }
        switch (errorCode) {
            case 1000:
                return 2;
            case 1001:
                return 3;
            case 1002:
                return 1;
            default:
                return 0;
        }
    }

    private void buildAdRequest(MediationAdRequest mediationAdRequest) {
        if (mediationAdRequest != null) {
            AdSettings.setIsChildDirected(mediationAdRequest.taggedForChildDirectedTreatment() == 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAndLoadBannerAd(Context context, String str, AdSize adSize, MediationAdRequest mediationAdRequest) {
        this.mAdView = new AdView(context, str, getAdSize(context, adSize));
        this.mAdView.setAdListener(new BannerListener());
        buildAdRequest(mediationAdRequest);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context));
        this.mWrappedAdView = new RelativeLayout(context);
        this.mAdView.setLayoutParams(layoutParams);
        this.mWrappedAdView.addView(this.mAdView);
        this.mAdView.loadAd();
    }

    private class BannerListener implements AdListener {
        @Override // com.facebook.ads.AdListener
        public void onLoggingImpression(Ad ad) {
        }

        private BannerListener() {
        }

        @Override // com.facebook.ads.AdListener
        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mBannerListener.onAdOpened(FacebookAdapter.this);
            FacebookAdapter.this.mBannerListener.onAdLeftApplication(FacebookAdapter.this);
        }

        @Override // com.facebook.ads.AdListener
        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdLoaded(FacebookAdapter.this);
        }

        @Override // com.facebook.ads.AdListener
        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookMediationAdapter.TAG, errorMessage);
            }
            MediationBannerListener mediationBannerListener = FacebookAdapter.this.mBannerListener;
            FacebookAdapter facebookAdapter = FacebookAdapter.this;
            mediationBannerListener.onAdFailedToLoad(facebookAdapter, facebookAdapter.convertErrorCode(adError));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAndLoadInterstitial(Context context, String str, MediationAdRequest mediationAdRequest) {
        this.mInterstitialAd = new InterstitialAd(context, str);
        this.mInterstitialAd.setAdListener(new InterstitialListener());
        buildAdRequest(mediationAdRequest);
        this.mInterstitialAd.loadAd();
    }

    private class InterstitialListener implements InterstitialAdListener {
        @Override // com.facebook.ads.AdListener
        public void onLoggingImpression(Ad ad) {
        }

        private InterstitialListener() {
        }

        @Override // com.facebook.ads.AdListener
        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mInterstitialListener.onAdLeftApplication(FacebookAdapter.this);
        }

        @Override // com.facebook.ads.AdListener
        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdLoaded(FacebookAdapter.this);
        }

        @Override // com.facebook.ads.AdListener
        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookMediationAdapter.TAG, errorMessage);
            }
            MediationInterstitialListener mediationInterstitialListener = FacebookAdapter.this.mInterstitialListener;
            FacebookAdapter facebookAdapter = FacebookAdapter.this;
            mediationInterstitialListener.onAdFailedToLoad(facebookAdapter, facebookAdapter.convertErrorCode(adError));
        }

        @Override // com.facebook.ads.InterstitialAdListener
        public void onInterstitialDismissed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdClosed(FacebookAdapter.this);
        }

        @Override // com.facebook.ads.InterstitialAdListener
        public void onInterstitialDisplayed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdOpened(FacebookAdapter.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAndLoadNativeAd(Context context, String str, NativeMediationAdRequest nativeMediationAdRequest) {
        this.mMediaView = new MediaView(context);
        this.mNativeAd = new NativeAd(context, str);
        NativeAd nativeAd = this.mNativeAd;
        nativeAd.setAdListener(new NativeListener(nativeAd, nativeMediationAdRequest));
        buildAdRequest(nativeMediationAdRequest);
        this.mNativeAd.loadAd();
    }

    private class NativeListener implements AdListener, NativeAdListener {
        private NativeMediationAdRequest mMediationAdRequest;
        private NativeAd mNativeAd;

        private NativeListener(NativeAd nativeAd, NativeMediationAdRequest nativeMediationAdRequest) {
            this.mNativeAd = nativeAd;
            this.mMediationAdRequest = nativeMediationAdRequest;
        }

        @Override // com.facebook.ads.AdListener
        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mNativeListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mNativeListener.onAdOpened(FacebookAdapter.this);
            FacebookAdapter.this.mNativeListener.onAdLeftApplication(FacebookAdapter.this);
        }

        @Override // com.facebook.ads.AdListener
        public void onLoggingImpression(Ad ad) {
            if (!FacebookAdapter.this.mIsImpressionRecorded) {
                FacebookAdapter.this.mNativeListener.onAdImpression(FacebookAdapter.this);
                FacebookAdapter.this.mIsImpressionRecorded = true;
            } else {
                Log.d(FacebookMediationAdapter.TAG, "Received onLoggingImpression callback for a native whose impression is already recorded. Ignoring the duplicate callback.");
            }
        }

        @Override // com.facebook.ads.AdListener
        public void onAdLoaded(Ad ad) {
            if (ad != this.mNativeAd) {
                Log.w(FacebookMediationAdapter.TAG, "Ad loaded is not a native ad.");
                FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 0);
                return;
            }
            NativeAdOptions nativeAdOptions = this.mMediationAdRequest.getNativeAdOptions();
            if (this.mMediationAdRequest.isUnifiedNativeAdRequested()) {
                final UnifiedAdMapper unifiedAdMapper = FacebookAdapter.this.new UnifiedAdMapper(this.mNativeAd, nativeAdOptions);
                unifiedAdMapper.mapUnifiedNativeAd(new NativeAdMapperListener() { // from class: com.google.ads.mediation.facebook.FacebookAdapter.NativeListener.1
                    @Override // com.google.ads.mediation.facebook.FacebookAdapter.NativeAdMapperListener
                    public void onMappingSuccess() {
                        FacebookAdapter.this.mNativeListener.onAdLoaded(FacebookAdapter.this, unifiedAdMapper);
                    }

                    @Override // com.google.ads.mediation.facebook.FacebookAdapter.NativeAdMapperListener
                    public void onMappingFailed() {
                        FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 3);
                    }
                });
            } else if (this.mMediationAdRequest.isAppInstallAdRequested()) {
                final AppInstallMapper appInstallMapper = FacebookAdapter.this.new AppInstallMapper(this.mNativeAd, nativeAdOptions);
                appInstallMapper.mapNativeAd(new NativeAdMapperListener() { // from class: com.google.ads.mediation.facebook.FacebookAdapter.NativeListener.2
                    @Override // com.google.ads.mediation.facebook.FacebookAdapter.NativeAdMapperListener
                    public void onMappingSuccess() {
                        FacebookAdapter.this.mNativeListener.onAdLoaded(FacebookAdapter.this, appInstallMapper);
                    }

                    @Override // com.google.ads.mediation.facebook.FacebookAdapter.NativeAdMapperListener
                    public void onMappingFailed() {
                        FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 3);
                    }
                });
            } else {
                Log.e(FacebookMediationAdapter.TAG, "Content Ads are not supported.");
                FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 1);
            }
        }

        @Override // com.facebook.ads.AdListener
        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookMediationAdapter.TAG, errorMessage);
            }
            MediationNativeListener mediationNativeListener = FacebookAdapter.this.mNativeListener;
            FacebookAdapter facebookAdapter = FacebookAdapter.this;
            mediationNativeListener.onAdFailedToLoad(facebookAdapter, facebookAdapter.convertErrorCode(adError));
        }

        @Override // com.facebook.ads.NativeAdListener
        public void onMediaDownloaded(Ad ad) {
            Log.d(FacebookMediationAdapter.TAG, "onMediaDownloaded");
        }
    }

    private com.facebook.ads.AdSize getAdSize(Context context, AdSize adSize) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(0, new AdSize(adSize.getWidth(), 50));
        arrayList.add(1, new AdSize(adSize.getWidth(), 90));
        arrayList.add(2, new AdSize(adSize.getWidth(), 250));
        Log.i(TAG, "Potential ad sizes: " + arrayList.toString());
        AdSize adSizeFindClosestSize = findClosestSize(context, adSize, arrayList);
        if (adSizeFindClosestSize == null) {
            return null;
        }
        Log.i(TAG, "Found closest ad size: " + adSizeFindClosestSize.toString());
        if (adSizeFindClosestSize.getWidth() == com.facebook.ads.AdSize.BANNER_320_50.getWidth() && adSizeFindClosestSize.getHeight() == com.facebook.ads.AdSize.BANNER_320_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_320_50;
        }
        int height = adSizeFindClosestSize.getHeight();
        if (height == com.facebook.ads.AdSize.BANNER_HEIGHT_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_50;
        }
        if (height == com.facebook.ads.AdSize.BANNER_HEIGHT_90.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_90;
        }
        if (height == com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250.getHeight()) {
            return com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250;
        }
        return null;
    }

    public static AdSize findClosestSize(Context context, AdSize adSize, ArrayList<AdSize> arrayList) {
        AdSize adSize2 = null;
        if (arrayList != null && adSize != null) {
            float f = context.getResources().getDisplayMetrics().density;
            AdSize adSize3 = new AdSize(Math.round(adSize.getWidthInPixels(context) / f), Math.round(adSize.getHeightInPixels(context) / f));
            for (AdSize largerByArea : arrayList) {
                if (isSizeInRange(adSize3, largerByArea)) {
                    if (adSize2 != null) {
                        largerByArea = getLargerByArea(adSize2, largerByArea);
                    }
                    adSize2 = largerByArea;
                }
            }
        }
        return adSize2;
    }

    private static boolean isSizeInRange(AdSize adSize, AdSize adSize2) {
        if (adSize2 == null) {
            return false;
        }
        int width = adSize.getWidth();
        int width2 = adSize2.getWidth();
        int height = adSize.getHeight();
        int height2 = adSize2.getHeight();
        double d = width;
        Double.isNaN(d);
        if (d * 0.5d <= width2 && width >= width2) {
            double d2 = height;
            Double.isNaN(d2);
            if (d2 * 0.7d <= height2 && height >= height2) {
                return true;
            }
        }
        return false;
    }

    private static AdSize getLargerByArea(AdSize adSize, AdSize adSize2) {
        return adSize.getWidth() * adSize.getHeight() > adSize2.getWidth() * adSize2.getHeight() ? adSize : adSize2;
    }

    class AppInstallMapper extends NativeAppInstallAdMapper {
        private NativeAd mNativeAd;
        private NativeAdOptions mNativeAdOptions;

        public AppInstallMapper(NativeAd nativeAd, NativeAdOptions nativeAdOptions) {
            this.mNativeAd = nativeAd;
            this.mNativeAdOptions = nativeAdOptions;
        }

        public void mapNativeAd(NativeAdMapperListener nativeAdMapperListener) {
            if (!containsRequiredFieldsForNativeAppInstallAd(this.mNativeAd)) {
                Log.w(FacebookMediationAdapter.TAG, "Ad from Facebook doesn't have all assets required for the app install format.");
                nativeAdMapperListener.onMappingFailed();
                return;
            }
            setHeadline(this.mNativeAd.getAdHeadline());
            ArrayList arrayList = new ArrayList();
            arrayList.add(FacebookAdapter.this.new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdCoverImage().toString())));
            setImages(arrayList);
            setBody(this.mNativeAd.getAdBodyText());
            setIcon(FacebookAdapter.this.new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdIcon().toString())));
            setCallToAction(this.mNativeAd.getAdCallToAction());
            FacebookAdapter.this.mMediaView.setListener(new MediaViewListener() { // from class: com.google.ads.mediation.facebook.FacebookAdapter.AppInstallMapper.1
                @Override // com.facebook.ads.MediaViewListener
                public void onEnterFullscreen(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onExitFullscreen(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onFullscreenBackground(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onFullscreenForeground(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onPause(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onPlay(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onVolumeChange(MediaView mediaView, float f) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onComplete(MediaView mediaView) {
                    if (FacebookAdapter.this.mNativeListener != null) {
                        FacebookAdapter.this.mNativeListener.onVideoEnd(FacebookAdapter.this);
                    }
                }
            });
            setMediaView(FacebookAdapter.this.mMediaView);
            setHasVideoContent(true);
            Double rating = getRating(this.mNativeAd.getAdStarRating());
            if (rating != null) {
                setStarRating(rating.doubleValue());
            }
            Bundle bundle = new Bundle();
            bundle.putCharSequence("id", this.mNativeAd.getId());
            bundle.putCharSequence(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET, this.mNativeAd.getAdSocialContext());
            setExtras(bundle);
            nativeAdMapperListener.onMappingSuccess();
        }

        private boolean containsRequiredFieldsForNativeAppInstallAd(NativeAd nativeAd) {
            return (nativeAd.getAdHeadline() == null || nativeAd.getAdCoverImage() == null || nativeAd.getAdBodyText() == null || nativeAd.getAdIcon() == null || nativeAd.getAdCallToAction() == null || FacebookAdapter.this.mMediaView == null) ? false : true;
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
            ViewGroup viewGroup = (ViewGroup) view;
            View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            ImageView imageView = null;
            if (childAt instanceof FrameLayout) {
                NativeAdLayout nativeAdLayout = new NativeAdLayout(view.getContext());
                ((FrameLayout) childAt).addView(nativeAdLayout);
                AdOptionsView adOptionsView = new AdOptionsView(view.getContext(), this.mNativeAd, nativeAdLayout);
                ((ViewGroup) childAt).addView(adOptionsView);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) adOptionsView.getLayoutParams();
                NativeAdOptions nativeAdOptions = this.mNativeAdOptions;
                if (nativeAdOptions != null) {
                    int adChoicesPlacement = nativeAdOptions.getAdChoicesPlacement();
                    if (adChoicesPlacement == 0) {
                        layoutParams.gravity = 51;
                    } else if (adChoicesPlacement == 2) {
                        layoutParams.gravity = 85;
                    } else if (adChoicesPlacement == 3) {
                        layoutParams.gravity = 83;
                    } else {
                        layoutParams.gravity = 53;
                    }
                } else {
                    layoutParams.gravity = 53;
                }
                viewGroup.requestLayout();
            } else {
                setAdChoicesContent(new AdOptionsView(view.getContext(), this.mNativeAd, null));
            }
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, View> entry : map.entrySet()) {
                arrayList.add(entry.getValue());
                if (entry.getKey().equals(NativeAppInstallAd.ASSET_ICON) || entry.getKey().equals(UnifiedNativeAdAssetNames.ASSET_ICON)) {
                    imageView = (ImageView) entry.getValue();
                }
            }
            this.mNativeAd.registerViewForInteraction(view, FacebookAdapter.this.mMediaView, imageView, arrayList);
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void untrackView(View view) {
            super.untrackView(view);
            ViewGroup viewGroup = (ViewGroup) view;
            View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            if (childAt instanceof FrameLayout) {
                ((FrameLayout) childAt).removeAllViews();
            }
            this.mNativeAd.unregisterView();
        }

        private Double getRating(NativeAdBase.Rating rating) {
            if (rating == null) {
                return null;
            }
            return Double.valueOf((rating.getValue() * 5.0d) / rating.getScale());
        }
    }

    class UnifiedAdMapper extends UnifiedNativeAdMapper {
        private NativeAd mNativeAd;
        private NativeAdOptions mNativeAdOptions;

        public UnifiedAdMapper(NativeAd nativeAd, NativeAdOptions nativeAdOptions) {
            this.mNativeAd = nativeAd;
            this.mNativeAdOptions = nativeAdOptions;
        }

        public void mapUnifiedNativeAd(NativeAdMapperListener nativeAdMapperListener) {
            if (!containsRequiredFieldsForUnifiedNativeAd(this.mNativeAd)) {
                Log.w(FacebookMediationAdapter.TAG, "Ad from Facebook doesn't have all assets required for the Unified Ad format.");
                nativeAdMapperListener.onMappingFailed();
                return;
            }
            setHeadline(this.mNativeAd.getAdHeadline());
            ArrayList arrayList = new ArrayList();
            arrayList.add(FacebookAdapter.this.new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdCoverImage().toString())));
            setImages(arrayList);
            setBody(this.mNativeAd.getAdBodyText());
            setIcon(FacebookAdapter.this.new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdIcon().toString())));
            setCallToAction(this.mNativeAd.getAdCallToAction());
            setAdvertiser(this.mNativeAd.getAdvertiserName());
            FacebookAdapter.this.mMediaView.setListener(new MediaViewListener() { // from class: com.google.ads.mediation.facebook.FacebookAdapter.UnifiedAdMapper.1
                @Override // com.facebook.ads.MediaViewListener
                public void onEnterFullscreen(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onExitFullscreen(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onFullscreenBackground(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onFullscreenForeground(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onPause(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onPlay(MediaView mediaView) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onVolumeChange(MediaView mediaView, float f) {
                }

                @Override // com.facebook.ads.MediaViewListener
                public void onComplete(MediaView mediaView) {
                    if (FacebookAdapter.this.mNativeListener != null) {
                        FacebookAdapter.this.mNativeListener.onVideoEnd(FacebookAdapter.this);
                    }
                }
            });
            setMediaView(FacebookAdapter.this.mMediaView);
            setHasVideoContent(true);
            Double rating = getRating(this.mNativeAd.getAdStarRating());
            if (rating != null) {
                setStarRating(rating);
            }
            Bundle bundle = new Bundle();
            bundle.putCharSequence("id", this.mNativeAd.getId());
            bundle.putCharSequence(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET, this.mNativeAd.getAdSocialContext());
            setExtras(bundle);
            nativeAdMapperListener.onMappingSuccess();
        }

        private boolean containsRequiredFieldsForUnifiedNativeAd(NativeAd nativeAd) {
            return (nativeAd.getAdHeadline() == null || nativeAd.getAdCoverImage() == null || nativeAd.getAdBodyText() == null || nativeAd.getAdIcon() == null || nativeAd.getAdCallToAction() == null || FacebookAdapter.this.mMediaView == null) ? false : true;
        }

        @Override // com.google.android.gms.ads.mediation.UnifiedNativeAdMapper
        public void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
            ViewGroup viewGroup = (ViewGroup) view;
            View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            NativeAdLayout nativeAdLayout = new NativeAdLayout(view.getContext());
            if (childAt instanceof FrameLayout) {
                AdOptionsView adOptionsView = new AdOptionsView(view.getContext(), this.mNativeAd, nativeAdLayout);
                ((ViewGroup) childAt).addView(adOptionsView);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) adOptionsView.getLayoutParams();
                NativeAdOptions nativeAdOptions = this.mNativeAdOptions;
                if (nativeAdOptions != null) {
                    int adChoicesPlacement = nativeAdOptions.getAdChoicesPlacement();
                    if (adChoicesPlacement == 0) {
                        layoutParams.gravity = 51;
                    } else if (adChoicesPlacement == 2) {
                        layoutParams.gravity = 85;
                    } else if (adChoicesPlacement == 3) {
                        layoutParams.gravity = 83;
                    } else {
                        layoutParams.gravity = 53;
                    }
                } else {
                    layoutParams.gravity = 53;
                }
                viewGroup.requestLayout();
            } else {
                setAdChoicesContent(new AdOptionsView(view.getContext(), this.mNativeAd, nativeAdLayout));
            }
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            ImageView imageView = null;
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, View> entry : map.entrySet()) {
                arrayList.add(entry.getValue());
                if (entry.getKey().equals(NativeAppInstallAd.ASSET_ICON) || entry.getKey().equals(UnifiedNativeAdAssetNames.ASSET_ICON)) {
                    imageView = (ImageView) entry.getValue();
                }
            }
            this.mNativeAd.registerViewForInteraction(view, FacebookAdapter.this.mMediaView, imageView, arrayList);
        }

        @Override // com.google.android.gms.ads.mediation.UnifiedNativeAdMapper
        public void untrackView(View view) {
            super.untrackView(view);
            ViewGroup viewGroup = (ViewGroup) view;
            View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            if (childAt instanceof FrameLayout) {
                ((FrameLayout) childAt).removeAllViews();
            }
            this.mNativeAd.unregisterView();
        }

        private Double getRating(NativeAdBase.Rating rating) {
            if (rating == null) {
                return null;
            }
            return Double.valueOf((rating.getValue() * 5.0d) / rating.getScale());
        }
    }

    private class FacebookAdapterNativeAdImage extends com.google.android.gms.ads.formats.NativeAd.Image {
        private Drawable mDrawable;
        private Uri mUri;

        @Override // com.google.android.gms.ads.formats.NativeAd.Image
        public double getScale() {
            return 1.0d;
        }

        public FacebookAdapterNativeAdImage(Uri uri) {
            this.mUri = uri;
        }

        protected void setDrawable(Drawable drawable) {
            this.mDrawable = drawable;
        }

        @Override // com.google.android.gms.ads.formats.NativeAd.Image
        public Drawable getDrawable() {
            return this.mDrawable;
        }

        @Override // com.google.android.gms.ads.formats.NativeAd.Image
        public Uri getUri() {
            return this.mUri;
        }
    }
}
