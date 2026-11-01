package com.google.ads.mediation.mytarget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.my.target.common.CustomParams;
import com.my.target.common.models.ImageData;
import com.my.target.nativeads.NativeAd;
import com.my.target.nativeads.banners.NativePromoBanner;
import com.my.target.nativeads.views.MediaAdView;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MyTargetNativeAdapter implements MediationNativeAdapter {
    public static final String EXTRA_KEY_ADVERTISING_LABEL = "advertisingLabel";
    public static final String EXTRA_KEY_AGE_RESTRICTIONS = "ageRestrictions";
    public static final String EXTRA_KEY_CATEGORY = "category";
    public static final String EXTRA_KEY_SUBCATEGORY = "subcategory";
    public static final String EXTRA_KEY_VOTES = "votes";
    private static final String PARAM_CONTENT_ONLY = "2";
    private static final String PARAM_INSTALL_ONLY = "1";
    private static final String PARAM_NATIVE_TYPE_REQUEST = "at";
    private static final String TAG = "MyTargetNativeAdapter";

    @Nullable
    private MediationNativeListener customEventNativeListener;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int findMediaAdViewPosition(@NonNull List<View> list, @NonNull MediaAdView mediaAdView) {
        for (int i = 0; i < list.size(); i++) {
            View view = list.get(i);
            if (view instanceof MediaView) {
                MediaView mediaView = (MediaView) view;
                int childCount = mediaView.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    if (mediaView.getChildAt(i2) == mediaAdView) {
                        return i;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        Date birthday;
        boolean zIsContentAdRequested;
        boolean zIsAppInstallAdRequested;
        boolean zIsUnifiedNativeAdRequested;
        boolean z;
        this.customEventNativeListener = mediationNativeListener;
        int iCheckAndGetSlotId = MyTargetTools.checkAndGetSlotId(context, bundle);
        Log.d(TAG, "Requesting myTarget mediation, slotId: " + iCheckAndGetSlotId);
        if (iCheckAndGetSlotId < 0) {
            if (mediationNativeListener != null) {
                mediationNativeListener.onAdFailedToLoad(this, 1);
                return;
            }
            return;
        }
        NativeAdOptions nativeAdOptions = null;
        int gender = 0;
        if (nativeMediationAdRequest != null) {
            nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
            gender = nativeMediationAdRequest.getGender();
            birthday = nativeMediationAdRequest.getBirthday();
            zIsContentAdRequested = nativeMediationAdRequest.isContentAdRequested();
            zIsAppInstallAdRequested = nativeMediationAdRequest.isAppInstallAdRequested();
            zIsUnifiedNativeAdRequested = nativeMediationAdRequest.isUnifiedNativeAdRequested();
        } else {
            birthday = null;
            zIsContentAdRequested = false;
            zIsAppInstallAdRequested = false;
            zIsUnifiedNativeAdRequested = false;
        }
        NativeAd nativeAd = new NativeAd(iCheckAndGetSlotId, context);
        if (nativeAdOptions != null) {
            z = !nativeAdOptions.shouldReturnUrlsForImageAssets();
            Log.d(TAG, "Set autoload images to " + z);
        } else {
            z = true;
        }
        nativeAd.setAutoLoadImages(z);
        CustomParams customParams = nativeAd.getCustomParams();
        Log.d(TAG, "Set gender to " + gender);
        customParams.setGender(gender);
        if (birthday != null && birthday.getTime() != -1) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(birthday.getTime());
            int i = gregorianCalendar2.get(1) - gregorianCalendar.get(1);
            if (i >= 0) {
                customParams.setAge(i);
            }
        }
        Log.d(TAG, "Content requested: " + zIsContentAdRequested + ", install requested: " + zIsAppInstallAdRequested + ", unified requested: " + zIsUnifiedNativeAdRequested);
        if (!zIsUnifiedNativeAdRequested && (!zIsContentAdRequested || !zIsAppInstallAdRequested)) {
            if (!zIsContentAdRequested) {
                customParams.setCustomParam(PARAM_NATIVE_TYPE_REQUEST, "1");
            } else {
                customParams.setCustomParam(PARAM_NATIVE_TYPE_REQUEST, "2");
            }
        }
        MyTargetNativeAdListener myTargetNativeAdListener = new MyTargetNativeAdListener(nativeAd, nativeMediationAdRequest, context);
        customParams.setCustomParam("mediation", "1");
        nativeAd.setListener(myTargetNativeAdListener);
        nativeAd.load();
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        this.customEventNativeListener = null;
    }

    private static class MyTargetAdmobNativeImage extends com.google.android.gms.ads.formats.NativeAd.Image {

        @Nullable
        private Drawable drawable;

        @NonNull
        private final Uri uri;

        @Override // com.google.android.gms.ads.formats.NativeAd.Image
        public double getScale() {
            return 1.0d;
        }

        MyTargetAdmobNativeImage(@NonNull ImageData imageData, @NonNull Resources resources) {
            Bitmap bitmap = imageData.getBitmap();
            if (bitmap != null) {
                this.drawable = new BitmapDrawable(resources, bitmap);
            }
            this.uri = Uri.parse(imageData.getUrl());
        }

        @Override // com.google.android.gms.ads.formats.NativeAd.Image
        @Nullable
        public Drawable getDrawable() {
            return this.drawable;
        }

        @Override // com.google.android.gms.ads.formats.NativeAd.Image
        @NonNull
        public Uri getUri() {
            return this.uri;
        }
    }

    private static class MyTargetNativeContentAdMapper extends NativeContentAdMapper {

        @NonNull
        private final MediaAdView mediaAdView;

        @NonNull
        private final NativeAd nativeAd;

        MyTargetNativeContentAdMapper(@NonNull NativeAd nativeAd, @NonNull Context context) {
            this.nativeAd = nativeAd;
            this.mediaAdView = new MediaAdView(context);
            setOverrideClickHandling(true);
            setOverrideImpressionRecording(true);
            NativePromoBanner banner = nativeAd.getBanner();
            if (banner == null) {
                return;
            }
            setBody(banner.getDescription());
            setCallToAction(banner.getCtaText());
            setHeadline(banner.getTitle());
            ImageData icon = banner.getIcon();
            if (icon != null && !TextUtils.isEmpty(icon.getUrl())) {
                setLogo(new MyTargetAdmobNativeImage(icon, context.getResources()));
            }
            setHasVideoContent(true);
            setMediaView(this.mediaAdView);
            ImageData image = banner.getImage();
            if (image != null && !TextUtils.isEmpty(image.getUrl())) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new MyTargetAdmobNativeImage(image, context.getResources()));
                setImages(arrayList);
            }
            setAdvertiser(banner.getDomain());
            Bundle bundle = new Bundle();
            String ageRestrictions = banner.getAgeRestrictions();
            if (!TextUtils.isEmpty(ageRestrictions)) {
                bundle.putString("ageRestrictions", ageRestrictions);
            }
            String advertisingLabel = banner.getAdvertisingLabel();
            if (!TextUtils.isEmpty(advertisingLabel)) {
                bundle.putString("advertisingLabel", advertisingLabel);
            }
            setExtras(bundle);
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void trackViews(final View view, Map<String, View> map, Map<String, View> map2) {
            final ArrayList arrayList = new ArrayList(map.values());
            view.post(new Runnable() { // from class: com.google.ads.mediation.mytarget.MyTargetNativeAdapter.MyTargetNativeContentAdMapper.1
                @Override // java.lang.Runnable
                public void run() {
                    int iFindMediaAdViewPosition = MyTargetNativeAdapter.findMediaAdViewPosition(arrayList, MyTargetNativeContentAdMapper.this.mediaAdView);
                    if (iFindMediaAdViewPosition >= 0) {
                        arrayList.remove(iFindMediaAdViewPosition);
                        arrayList.add(MyTargetNativeContentAdMapper.this.mediaAdView);
                    }
                    MyTargetNativeContentAdMapper.this.nativeAd.registerView(view, arrayList);
                }
            });
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void trackView(final View view) {
            view.post(new Runnable() { // from class: com.google.ads.mediation.mytarget.MyTargetNativeAdapter.MyTargetNativeContentAdMapper.2
                @Override // java.lang.Runnable
                public void run() {
                    View view2 = view;
                    if (view2 instanceof NativeContentAdView) {
                        NativeContentAdView nativeContentAdView = (NativeContentAdView) view2;
                        ArrayList arrayList = new ArrayList();
                        if (nativeContentAdView.getHeadlineView() != null) {
                            arrayList.add(nativeContentAdView.getHeadlineView());
                        }
                        if (nativeContentAdView.getBodyView() != null) {
                            arrayList.add(nativeContentAdView.getBodyView());
                        }
                        if (nativeContentAdView.getCallToActionView() != null) {
                            arrayList.add(nativeContentAdView.getCallToActionView());
                        }
                        if (nativeContentAdView.getAdvertiserView() != null) {
                            arrayList.add(nativeContentAdView.getAdvertiserView());
                        }
                        if (nativeContentAdView.getImageView() != null) {
                            arrayList.add(nativeContentAdView.getImageView());
                        }
                        if (nativeContentAdView.getLogoView() != null) {
                            arrayList.add(nativeContentAdView.getLogoView());
                        }
                        if (nativeContentAdView.getMediaView() != null) {
                            arrayList.add(MyTargetNativeContentAdMapper.this.mediaAdView);
                        }
                        MyTargetNativeContentAdMapper.this.nativeAd.registerView(view, arrayList);
                        return;
                    }
                    Log.w(MyTargetNativeAdapter.TAG, "Failed to register view for interaction.");
                }
            });
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void untrackView(View view) {
            this.nativeAd.unregisterView();
        }
    }

    private static class MyTargetNativeUnifiedAdMapper extends UnifiedNativeAdMapper {

        @NonNull
        private final MediaAdView mediaAdView;

        @NonNull
        private final NativeAd nativeAd;

        MyTargetNativeUnifiedAdMapper(@NonNull NativeAd nativeAd, @NonNull Context context) {
            this.nativeAd = nativeAd;
            this.mediaAdView = new MediaAdView(context);
            setOverrideClickHandling(true);
            setOverrideImpressionRecording(true);
            NativePromoBanner banner = nativeAd.getBanner();
            if (banner == null) {
                return;
            }
            setBody(banner.getDescription());
            setCallToAction(banner.getCtaText());
            setHeadline(banner.getTitle());
            ImageData icon = banner.getIcon();
            if (icon != null && !TextUtils.isEmpty(icon.getUrl())) {
                setIcon(new MyTargetAdmobNativeImage(icon, context.getResources()));
            }
            ImageData image = banner.getImage();
            setHasVideoContent(true);
            setMediaView(this.mediaAdView);
            if (image != null && !TextUtils.isEmpty(image.getUrl())) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new MyTargetAdmobNativeImage(image, context.getResources()));
                setImages(arrayList);
            }
            setAdvertiser(banner.getDomain());
            setStarRating(Double.valueOf(banner.getRating()));
            setStore(null);
            setPrice(null);
            Bundle bundle = new Bundle();
            String ageRestrictions = banner.getAgeRestrictions();
            if (!TextUtils.isEmpty(ageRestrictions)) {
                bundle.putString("ageRestrictions", ageRestrictions);
            }
            String advertisingLabel = banner.getAdvertisingLabel();
            if (!TextUtils.isEmpty(advertisingLabel)) {
                bundle.putString("advertisingLabel", advertisingLabel);
            }
            String category = banner.getCategory();
            if (!TextUtils.isEmpty(category)) {
                bundle.putString("category", category);
            }
            String subCategory = banner.getSubCategory();
            if (!TextUtils.isEmpty(subCategory)) {
                bundle.putString("subcategory", subCategory);
            }
            int votes = banner.getVotes();
            if (votes > 0) {
                bundle.putInt("votes", votes);
            }
            setExtras(bundle);
        }

        @Override // com.google.android.gms.ads.mediation.UnifiedNativeAdMapper
        public void trackViews(final View view, Map<String, View> map, Map<String, View> map2) {
            final ArrayList arrayList = new ArrayList(map.values());
            view.post(new Runnable() { // from class: com.google.ads.mediation.mytarget.MyTargetNativeAdapter.MyTargetNativeUnifiedAdMapper.1
                @Override // java.lang.Runnable
                public void run() {
                    int iFindMediaAdViewPosition = MyTargetNativeAdapter.findMediaAdViewPosition(arrayList, MyTargetNativeUnifiedAdMapper.this.mediaAdView);
                    if (iFindMediaAdViewPosition >= 0) {
                        arrayList.remove(iFindMediaAdViewPosition);
                        arrayList.add(MyTargetNativeUnifiedAdMapper.this.mediaAdView);
                    }
                    MyTargetNativeUnifiedAdMapper.this.nativeAd.registerView(view, arrayList);
                }
            });
        }

        @Override // com.google.android.gms.ads.mediation.UnifiedNativeAdMapper
        public void untrackView(View view) {
            this.nativeAd.unregisterView();
        }
    }

    private static class MyTargetNativeInstallAdMapper extends NativeAppInstallAdMapper {

        @NonNull
        private final MediaAdView mediaAdView;

        @NonNull
        private final NativeAd nativeAd;

        MyTargetNativeInstallAdMapper(@NonNull NativeAd nativeAd, @NonNull Context context) {
            this.nativeAd = nativeAd;
            this.mediaAdView = new MediaAdView(context);
            setOverrideClickHandling(true);
            setOverrideImpressionRecording(true);
            NativePromoBanner banner = nativeAd.getBanner();
            if (banner == null) {
                return;
            }
            setBody(banner.getDescription());
            setCallToAction(banner.getCtaText());
            setHeadline(banner.getTitle());
            ImageData icon = banner.getIcon();
            if (icon != null && !TextUtils.isEmpty(icon.getUrl())) {
                setIcon(new MyTargetAdmobNativeImage(icon, context.getResources()));
            }
            ImageData image = banner.getImage();
            setHasVideoContent(true);
            setMediaView(this.mediaAdView);
            if (image != null && !TextUtils.isEmpty(image.getUrl())) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new MyTargetAdmobNativeImage(image, context.getResources()));
                setImages(arrayList);
            }
            setStarRating(banner.getRating());
            setStore(null);
            setPrice(null);
            Bundle bundle = new Bundle();
            String ageRestrictions = banner.getAgeRestrictions();
            if (!TextUtils.isEmpty(ageRestrictions)) {
                bundle.putString("ageRestrictions", ageRestrictions);
            }
            String advertisingLabel = banner.getAdvertisingLabel();
            if (!TextUtils.isEmpty(advertisingLabel)) {
                bundle.putString("advertisingLabel", advertisingLabel);
            }
            String category = banner.getCategory();
            if (!TextUtils.isEmpty(category)) {
                bundle.putString("category", category);
            }
            String subCategory = banner.getSubCategory();
            if (!TextUtils.isEmpty(subCategory)) {
                bundle.putString("subcategory", subCategory);
            }
            int votes = banner.getVotes();
            if (votes > 0) {
                bundle.putInt("votes", votes);
            }
            setExtras(bundle);
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void trackViews(final View view, Map<String, View> map, Map<String, View> map2) {
            final ArrayList arrayList = new ArrayList(map.values());
            view.post(new Runnable() { // from class: com.google.ads.mediation.mytarget.MyTargetNativeAdapter.MyTargetNativeInstallAdMapper.1
                @Override // java.lang.Runnable
                public void run() {
                    int iFindMediaAdViewPosition = MyTargetNativeAdapter.findMediaAdViewPosition(arrayList, MyTargetNativeInstallAdMapper.this.mediaAdView);
                    if (iFindMediaAdViewPosition >= 0) {
                        arrayList.remove(iFindMediaAdViewPosition);
                        arrayList.add(MyTargetNativeInstallAdMapper.this.mediaAdView);
                    }
                    MyTargetNativeInstallAdMapper.this.nativeAd.registerView(view, arrayList);
                }
            });
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void trackView(final View view) {
            view.post(new Runnable() { // from class: com.google.ads.mediation.mytarget.MyTargetNativeAdapter.MyTargetNativeInstallAdMapper.2
                @Override // java.lang.Runnable
                public void run() {
                    View view2 = view;
                    if (view2 instanceof NativeAppInstallAdView) {
                        NativeAppInstallAdView nativeAppInstallAdView = (NativeAppInstallAdView) view2;
                        ArrayList arrayList = new ArrayList();
                        if (nativeAppInstallAdView.getHeadlineView() != null) {
                            arrayList.add(nativeAppInstallAdView.getHeadlineView());
                        }
                        if (nativeAppInstallAdView.getBodyView() != null) {
                            arrayList.add(nativeAppInstallAdView.getBodyView());
                        }
                        if (nativeAppInstallAdView.getCallToActionView() != null) {
                            arrayList.add(nativeAppInstallAdView.getCallToActionView());
                        }
                        if (nativeAppInstallAdView.getIconView() != null) {
                            arrayList.add(nativeAppInstallAdView.getIconView());
                        }
                        if (nativeAppInstallAdView.getImageView() != null) {
                            arrayList.add(nativeAppInstallAdView.getImageView());
                        }
                        if (nativeAppInstallAdView.getPriceView() != null) {
                            arrayList.add(nativeAppInstallAdView.getPriceView());
                        }
                        if (nativeAppInstallAdView.getStarRatingView() != null) {
                            arrayList.add(nativeAppInstallAdView.getStarRatingView());
                        }
                        if (nativeAppInstallAdView.getStoreView() != null) {
                            arrayList.add(nativeAppInstallAdView.getStoreView());
                        }
                        if (nativeAppInstallAdView.getMediaView() != null) {
                            arrayList.add(MyTargetNativeInstallAdMapper.this.mediaAdView);
                        }
                        MyTargetNativeInstallAdMapper.this.nativeAd.registerView(view, arrayList);
                        return;
                    }
                    Log.w(MyTargetNativeAdapter.TAG, "Failed to register view for interaction.");
                }
            });
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public void untrackView(View view) {
            this.nativeAd.unregisterView();
        }
    }

    private class MyTargetNativeAdListener implements NativeAd.NativeAdListener {

        @NonNull
        private final Context context;

        @NonNull
        private final NativeAd nativeAd;

        @Nullable
        private final NativeMediationAdRequest nativeMediationAdRequest;

        MyTargetNativeAdListener(@Nullable NativeAd nativeAd, @NonNull NativeMediationAdRequest nativeMediationAdRequest, Context context) {
            this.nativeAd = nativeAd;
            this.nativeMediationAdRequest = nativeMediationAdRequest;
            this.context = context;
        }

        @Override // com.my.target.nativeads.NativeAd.NativeAdListener
        public void onLoad(@NonNull NativeAd nativeAd) {
            NativePromoBanner banner = nativeAd.getBanner();
            if (banner == null) {
                Log.d(MyTargetNativeAdapter.TAG, "No ad: MyTarget responded with null banner");
                if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                    MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 3);
                    return;
                }
                return;
            }
            if (this.nativeAd != nativeAd) {
                Log.d(MyTargetNativeAdapter.TAG, "Failed to load: loaded native ad does not match with requested");
                if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                    MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 0);
                    return;
                }
                return;
            }
            mapAd(nativeAd, banner);
        }

        @Override // com.my.target.nativeads.NativeAd.NativeAdListener
        public void onNoAd(@NonNull String str, @NonNull NativeAd nativeAd) {
            Log.i(MyTargetNativeAdapter.TAG, "No ad: MyTarget callback with reason " + str);
            if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 3);
            }
        }

        @Override // com.my.target.nativeads.NativeAd.NativeAdListener
        public void onClick(@NonNull NativeAd nativeAd) {
            Log.d(MyTargetNativeAdapter.TAG, "Ad clicked");
            if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                MyTargetNativeAdapter.this.customEventNativeListener.onAdClicked(MyTargetNativeAdapter.this);
                MyTargetNativeAdapter.this.customEventNativeListener.onAdOpened(MyTargetNativeAdapter.this);
                MyTargetNativeAdapter.this.customEventNativeListener.onAdLeftApplication(MyTargetNativeAdapter.this);
            }
        }

        @Override // com.my.target.nativeads.NativeAd.NativeAdListener
        public void onShow(@NonNull NativeAd nativeAd) {
            Log.d(MyTargetNativeAdapter.TAG, "Ad show");
            if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                MyTargetNativeAdapter.this.customEventNativeListener.onAdImpression(MyTargetNativeAdapter.this);
            }
        }

        @Override // com.my.target.nativeads.NativeAd.NativeAdListener
        public void onVideoPlay(@NonNull NativeAd nativeAd) {
            Log.d(MyTargetNativeAdapter.TAG, "Play ad video");
        }

        @Override // com.my.target.nativeads.NativeAd.NativeAdListener
        public void onVideoPause(@NonNull NativeAd nativeAd) {
            Log.d(MyTargetNativeAdapter.TAG, "Pause ad video");
        }

        @Override // com.my.target.nativeads.NativeAd.NativeAdListener
        public void onVideoComplete(@NonNull NativeAd nativeAd) {
            Log.d(MyTargetNativeAdapter.TAG, "Complete ad video");
            if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                MyTargetNativeAdapter.this.customEventNativeListener.onVideoEnd(MyTargetNativeAdapter.this);
            }
        }

        private void mapAd(@NonNull NativeAd nativeAd, @NonNull NativePromoBanner nativePromoBanner) {
            NativeAdMapper myTargetNativeContentAdMapper;
            NativeMediationAdRequest nativeMediationAdRequest = this.nativeMediationAdRequest;
            if (nativeMediationAdRequest == null) {
                Log.d(MyTargetNativeAdapter.TAG, "Failed to load: resources or nativeMediationAdRequest null");
                if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                    MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 0);
                    return;
                }
                return;
            }
            if (nativeMediationAdRequest.isUnifiedNativeAdRequested()) {
                MyTargetNativeUnifiedAdMapper myTargetNativeUnifiedAdMapper = new MyTargetNativeUnifiedAdMapper(nativeAd, this.context);
                if (nativePromoBanner.getImage() == null || nativePromoBanner.getIcon() == null) {
                    Log.d(MyTargetNativeAdapter.TAG, "No ad: Some of the Always Included assets are not available for the ad");
                    if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                        MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 3);
                        return;
                    }
                    return;
                }
                Log.d(MyTargetNativeAdapter.TAG, "Ad loaded successfully");
                if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                    MyTargetNativeAdapter.this.customEventNativeListener.onAdLoaded(MyTargetNativeAdapter.this, myTargetNativeUnifiedAdMapper);
                    return;
                }
                return;
            }
            String navigationType = nativePromoBanner.getNavigationType();
            if ("store".equals(navigationType)) {
                if (this.nativeMediationAdRequest.isAppInstallAdRequested()) {
                    myTargetNativeContentAdMapper = new MyTargetNativeInstallAdMapper(nativeAd, this.context);
                    if (nativePromoBanner.getImage() == null || nativePromoBanner.getIcon() == null) {
                        Log.d(MyTargetNativeAdapter.TAG, "No ad: Some of the Always Included assets are not available for the ad");
                        if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                            MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 3);
                            return;
                        }
                        return;
                    }
                } else {
                    Log.d(MyTargetNativeAdapter.TAG, "No ad: AdMob request was without install ad flag, but MyTarget responded with " + navigationType + " navigation type");
                    if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                        MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 3);
                        return;
                    }
                    return;
                }
            } else if (this.nativeMediationAdRequest.isContentAdRequested()) {
                myTargetNativeContentAdMapper = new MyTargetNativeContentAdMapper(nativeAd, this.context);
                if (nativePromoBanner.getImage() == null) {
                    Log.d(MyTargetNativeAdapter.TAG, "No ad: Some of the Always Included assets are not available for the ad");
                    if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                        MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 3);
                        return;
                    }
                    return;
                }
            } else {
                Log.d(MyTargetNativeAdapter.TAG, "No ad: AdMob request was without content ad flag, but MyTarget responded with " + navigationType + " navigation type");
                if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                    MyTargetNativeAdapter.this.customEventNativeListener.onAdFailedToLoad(MyTargetNativeAdapter.this, 3);
                    return;
                }
                return;
            }
            Log.d(MyTargetNativeAdapter.TAG, "Ad loaded successfully");
            if (MyTargetNativeAdapter.this.customEventNativeListener != null) {
                MyTargetNativeAdapter.this.customEventNativeListener.onAdLoaded(MyTargetNativeAdapter.this, myTargetNativeContentAdMapper);
            }
        }
    }
}
