package com.google.ads.mediation.facebook.rtb;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.MediaViewListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.ads.mediation.facebook.FacebookMediationAdapter;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FacebookRtbNativeAd extends UnifiedNativeAdMapper {
    private MediationNativeAdConfiguration adConfiguration;
    private MediationAdLoadCallback<UnifiedNativeAdMapper, MediationNativeAdCallback> callback;
    private MediaView mMediaView;
    private NativeAd mNativeAd;
    private MediationNativeAdCallback mNativeAdCallback;

    private interface NativeAdMapperListener {
        void onMappingFailed();

        void onMappingSuccess();
    }

    public FacebookRtbNativeAd(MediationNativeAdConfiguration mediationNativeAdConfiguration, MediationAdLoadCallback<UnifiedNativeAdMapper, MediationNativeAdCallback> mediationAdLoadCallback) {
        this.callback = mediationAdLoadCallback;
        this.adConfiguration = mediationNativeAdConfiguration;
    }

    public void render() {
        String placementID = FacebookMediationAdapter.getPlacementID(this.adConfiguration.getServerParameters());
        if (placementID == null || placementID.isEmpty()) {
            this.callback.onFailure("FacebookRtbNativeAd received a null or empty placement ID.");
            return;
        }
        this.mMediaView = new MediaView(this.adConfiguration.getContext());
        this.mNativeAd = new NativeAd(this.adConfiguration.getContext(), placementID);
        NativeAd nativeAd = this.mNativeAd;
        nativeAd.setAdListener(new NativeListener(nativeAd));
        this.mNativeAd.loadAdFromBid(this.adConfiguration.getBidResponse());
    }

    private class NativeListener implements AdListener, NativeAdListener {
        private NativeAd mNativeAd;

        @Override // com.facebook.ads.AdListener
        public void onLoggingImpression(Ad ad) {
        }

        NativeListener(NativeAd nativeAd) {
            this.mNativeAd = nativeAd;
        }

        @Override // com.facebook.ads.AdListener
        public void onAdClicked(Ad ad) {
            FacebookRtbNativeAd.this.mNativeAdCallback.onAdOpened();
            FacebookRtbNativeAd.this.mNativeAdCallback.onAdLeftApplication();
        }

        @Override // com.facebook.ads.AdListener
        public void onAdLoaded(Ad ad) {
            if (ad != this.mNativeAd) {
                Log.w(FacebookAdapter.TAG, "Ad loaded is not a native ad.");
                FacebookRtbNativeAd.this.callback.onFailure("Ad Loaded is not a Native Ad");
            } else {
                FacebookRtbNativeAd.this.mapNativeAd(new NativeAdMapperListener() { // from class: com.google.ads.mediation.facebook.rtb.FacebookRtbNativeAd.NativeListener.1
                    @Override // com.google.ads.mediation.facebook.rtb.FacebookRtbNativeAd.NativeAdMapperListener
                    public void onMappingSuccess() {
                        FacebookRtbNativeAd.this.mNativeAdCallback = (MediationNativeAdCallback) FacebookRtbNativeAd.this.callback.onSuccess(FacebookRtbNativeAd.this);
                    }

                    @Override // com.google.ads.mediation.facebook.rtb.FacebookRtbNativeAd.NativeAdMapperListener
                    public void onMappingFailed() {
                        FacebookRtbNativeAd.this.callback.onFailure("Ad Failed to Load");
                    }
                });
            }
        }

        @Override // com.facebook.ads.AdListener
        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookRtbNativeAd.this.callback.onFailure(adError.getErrorMessage());
        }

        @Override // com.facebook.ads.NativeAdListener
        public void onMediaDownloaded(Ad ad) {
            Log.d(FacebookAdapter.TAG, "onMediaDownloaded");
        }
    }

    public void mapNativeAd(NativeAdMapperListener nativeAdMapperListener) {
        if (!containsRequiredFieldsForUnifiedNativeAd(this.mNativeAd)) {
            Log.w(FacebookAdapter.TAG, "Ad from Facebook doesn't have all assets required for the app install format.");
            nativeAdMapperListener.onMappingFailed();
            return;
        }
        setHeadline(this.mNativeAd.getAdHeadline());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FacebookAdapterNativeAdImage(null));
        setImages(arrayList);
        setBody(this.mNativeAd.getAdBodyText());
        setIcon(new FacebookAdapterNativeAdImage(null));
        setCallToAction(this.mNativeAd.getAdCallToAction());
        setAdvertiser(this.mNativeAd.getAdvertiserName());
        this.mMediaView.setListener(new MediaViewListener() { // from class: com.google.ads.mediation.facebook.rtb.FacebookRtbNativeAd.1
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
                if (FacebookRtbNativeAd.this.mNativeAdCallback != null) {
                    FacebookRtbNativeAd.this.mNativeAdCallback.onVideoComplete();
                }
            }
        });
        setHasVideoContent(true);
        setMediaView(this.mMediaView);
        setStarRating(null);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("id", this.mNativeAd.getId());
        bundle.putCharSequence(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET, this.mNativeAd.getAdSocialContext());
        setExtras(bundle);
        nativeAdMapperListener.onMappingSuccess();
    }

    private boolean containsRequiredFieldsForUnifiedNativeAd(NativeAd nativeAd) {
        return (nativeAd.getAdHeadline() == null || nativeAd.getAdCoverImage() == null || nativeAd.getAdBodyText() == null || nativeAd.getAdIcon() == null || nativeAd.getAdCallToAction() == null || this.mMediaView == null) ? false : true;
    }

    @Override // com.google.android.gms.ads.mediation.UnifiedNativeAdMapper
    public void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
        ViewGroup viewGroup = (ViewGroup) view;
        View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
        ImageView imageView = null;
        if (childAt instanceof FrameLayout) {
            NativeAdLayout nativeAdLayout = new NativeAdLayout(view.getContext());
            ((FrameLayout) childAt).addView(nativeAdLayout);
            AdOptionsView adOptionsView = new AdOptionsView(view.getContext(), this.mNativeAd, nativeAdLayout);
            ((ViewGroup) childAt).addView(adOptionsView);
            ((FrameLayout.LayoutParams) adOptionsView.getLayoutParams()).gravity = 53;
            viewGroup.requestLayout();
        } else {
            setAdChoicesContent(new AdOptionsView(view.getContext(), this.mNativeAd, null));
        }
        setOverrideClickHandling(true);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, View> entry : map.entrySet()) {
            arrayList.add(entry.getValue());
            if (entry.getKey().equals(UnifiedNativeAdAssetNames.ASSET_ICON)) {
                imageView = (ImageView) entry.getValue();
            }
        }
        this.mNativeAd.registerViewForInteraction(view, this.mMediaView, imageView, arrayList);
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
