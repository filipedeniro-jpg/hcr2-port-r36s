package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzawo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@KeepForSdkWithMembers
@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {

    @VisibleForTesting
    private CustomEventBanner zzeiw;

    @VisibleForTesting
    private CustomEventInterstitial zzeix;

    @VisibleForTesting
    private CustomEventNative zzeiy;
    private View zznj;

    private static <T> T zzam(String str) {
        try {
            return (T) Class.forName(str).newInstance();
        } catch (Throwable th) {
            String message = th.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46 + String.valueOf(message).length());
            sb.append("Could not instantiate custom event adapter: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            zzawo.zzeu(sb.toString());
            return null;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    @VisibleForTesting
    class zza implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzeiz;
        private final MediationInterstitialListener zzeja;

        public zza(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzeiz = customEventAdapter;
            this.zzeja = mediationInterstitialListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener
        public final void onAdLoaded() {
            zzawo.zzdv("Custom event adapter called onReceivedAd.");
            this.zzeja.onAdLoaded(CustomEventAdapter.this);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int i) {
            zzawo.zzdv("Custom event adapter called onFailedToReceiveAd.");
            this.zzeja.onAdFailedToLoad(this.zzeiz, i);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzawo.zzdv("Custom event adapter called onAdClicked.");
            this.zzeja.onAdClicked(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzawo.zzdv("Custom event adapter called onAdOpened.");
            this.zzeja.onAdOpened(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzawo.zzdv("Custom event adapter called onAdClosed.");
            this.zzeja.onAdClosed(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzawo.zzdv("Custom event adapter called onAdLeftApplication.");
            this.zzeja.onAdLeftApplication(this.zzeiz);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    @VisibleForTesting
    static final class zzb implements CustomEventBannerListener {
        private final CustomEventAdapter zzeiz;
        private final MediationBannerListener zzejc;

        public zzb(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzeiz = customEventAdapter;
            this.zzejc = mediationBannerListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener
        public final void onAdLoaded(View view) {
            zzawo.zzdv("Custom event adapter called onAdLoaded.");
            this.zzeiz.zza(view);
            this.zzejc.onAdLoaded(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int i) {
            zzawo.zzdv("Custom event adapter called onAdFailedToLoad.");
            this.zzejc.onAdFailedToLoad(this.zzeiz, i);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzawo.zzdv("Custom event adapter called onAdClicked.");
            this.zzejc.onAdClicked(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzawo.zzdv("Custom event adapter called onAdOpened.");
            this.zzejc.onAdOpened(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzawo.zzdv("Custom event adapter called onAdClosed.");
            this.zzejc.onAdClosed(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzawo.zzdv("Custom event adapter called onAdLeftApplication.");
            this.zzejc.onAdLeftApplication(this.zzeiz);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    @VisibleForTesting
    static class zzc implements CustomEventNativeListener {
        private final CustomEventAdapter zzeiz;
        private final MediationNativeListener zzejd;

        public zzc(CustomEventAdapter customEventAdapter, MediationNativeListener mediationNativeListener) {
            this.zzeiz = customEventAdapter;
            this.zzejd = mediationNativeListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdLoaded(NativeAdMapper nativeAdMapper) {
            zzawo.zzdv("Custom event adapter called onAdLoaded.");
            this.zzejd.onAdLoaded(this.zzeiz, nativeAdMapper);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdLoaded(UnifiedNativeAdMapper unifiedNativeAdMapper) {
            zzawo.zzdv("Custom event adapter called onAdLoaded.");
            this.zzejd.onAdLoaded(this.zzeiz, unifiedNativeAdMapper);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int i) {
            zzawo.zzdv("Custom event adapter called onAdFailedToLoad.");
            this.zzejd.onAdFailedToLoad(this.zzeiz, i);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzawo.zzdv("Custom event adapter called onAdOpened.");
            this.zzejd.onAdOpened(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzawo.zzdv("Custom event adapter called onAdClicked.");
            this.zzejd.onAdClicked(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzawo.zzdv("Custom event adapter called onAdClosed.");
            this.zzejd.onAdClosed(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzawo.zzdv("Custom event adapter called onAdLeftApplication.");
            this.zzejd.onAdLeftApplication(this.zzeiz);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdImpression() {
            zzawo.zzdv("Custom event adapter called onAdImpression.");
            this.zzejd.onAdImpression(this.zzeiz);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onDestroy() {
        CustomEventBanner customEventBanner = this.zzeiw;
        if (customEventBanner != null) {
            customEventBanner.onDestroy();
        }
        CustomEventInterstitial customEventInterstitial = this.zzeix;
        if (customEventInterstitial != null) {
            customEventInterstitial.onDestroy();
        }
        CustomEventNative customEventNative = this.zzeiy;
        if (customEventNative != null) {
            customEventNative.onDestroy();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onPause() {
        CustomEventBanner customEventBanner = this.zzeiw;
        if (customEventBanner != null) {
            customEventBanner.onPause();
        }
        CustomEventInterstitial customEventInterstitial = this.zzeix;
        if (customEventInterstitial != null) {
            customEventInterstitial.onPause();
        }
        CustomEventNative customEventNative = this.zzeiy;
        if (customEventNative != null) {
            customEventNative.onPause();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onResume() {
        CustomEventBanner customEventBanner = this.zzeiw;
        if (customEventBanner != null) {
            customEventBanner.onResume();
        }
        CustomEventInterstitial customEventInterstitial = this.zzeix;
        if (customEventInterstitial != null) {
            customEventInterstitial.onResume();
        }
        CustomEventNative customEventNative = this.zzeiy;
        if (customEventNative != null) {
            customEventNative.onResume();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public final View getBannerView() {
        return this.zznj;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public final void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzeiw = (CustomEventBanner) zzam(bundle.getString("class_name"));
        if (this.zzeiw == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzeiw.requestBannerAd(context, new zzb(this, mediationBannerListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), adSize, mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzeix = (CustomEventInterstitial) zzam(bundle.getString("class_name"));
        if (this.zzeix == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzeix.requestInterstitialAd(context, new zza(this, mediationInterstitialListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public final void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.zzeiy = (CustomEventNative) zzam(bundle.getString("class_name"));
        if (this.zzeiy == null) {
            mediationNativeListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzeiy.requestNativeAd(context, new zzc(this, mediationNativeListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), nativeMediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        this.zzeix.showInterstitial();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(View view) {
        this.zznj = view;
    }
}
