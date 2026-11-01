package com.google.ads.mediation.nend;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.adcolony.sdk.AdColonyAppOptions;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import net.nend.android.NendAdInformationListener;
import net.nend.android.NendAdInterstitial;
import net.nend.android.NendAdInterstitialVideo;
import net.nend.android.NendAdVideo;
import net.nend.android.NendAdVideoListener;
import net.nend.android.NendAdView;

/* JADX INFO: loaded from: classes.dex */
public class NendAdapter extends NendMediationAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, NendAdInformationListener, OnContextChangedListener {
    static final String KEY_INTERSTITIAL_TYPE = "key_interstitial_type";
    private WeakReference<Activity> mActivityWeakReference;
    private MediationBannerListener mListener;
    private MediationInterstitialListener mListenerInterstitial;
    private NendAdInterstitialVideo mNendAdInterstitialVideo;
    private NendAdView mNendAdView;
    private InterstitialVideoStatus mInterstitialVideoStatus = InterstitialVideoStatus.PLAYING;
    private boolean mIsDetached = false;
    private boolean mIsRequireLoadAd = false;
    private boolean mIsRequestBannerAd = false;
    private boolean mIsPausingWebView = false;
    private View.OnAttachStateChangeListener mAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.google.ads.mediation.nend.NendAdapter.1
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            NendAdapter.this.mNendAdView.setListener(NendAdapter.this);
            if (NendAdapter.this.mIsRequireLoadAd) {
                NendAdapter.this.mNendAdView.loadAd();
                NendAdapter.this.mIsRequireLoadAd = false;
            }
            NendAdapter.this.mIsDetached = false;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            NendAdapter.this.mIsDetached = true;
        }
    };

    public enum InterstitialType {
        TYPE_VIDEO,
        TYPE_NORMAL
    }

    private enum InterstitialVideoStatus {
        PLAYING,
        PLAYING_WHEN_CLICKED,
        STOPPED
    }

    private void requireLoadAd() {
        if (!this.mIsDetached || this.mIsRequireLoadAd) {
            return;
        }
        this.mIsRequireLoadAd = true;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        this.mNendAdView = null;
        this.mListener = null;
        this.mListenerInterstitial = null;
        this.mActivityWeakReference.clear();
        this.mActivityWeakReference = null;
        NendAdInterstitialVideo nendAdInterstitialVideo = this.mNendAdInterstitialVideo;
        if (nendAdInterstitialVideo != null) {
            nendAdInterstitialVideo.releaseAd();
            this.mNendAdInterstitialVideo = null;
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
        NendAdView nendAdView = this.mNendAdView;
        if (nendAdView != null) {
            if (nendAdView.getChildAt(0) instanceof WebView) {
                this.mIsPausingWebView = true;
            }
            this.mNendAdView.pause();
            requireLoadAd();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
        NendAdView nendAdView = this.mNendAdView;
        if (nendAdView != null) {
            if (this.mIsPausingWebView) {
                nendAdView.resume();
            }
            requireLoadAd();
            this.mIsPausingWebView = false;
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.mListenerInterstitial = mediationInterstitialListener;
        if (!(context instanceof Activity)) {
            Log.w(TAG, "Failed to request ad from Nend: Context not an Activity.");
            adFailedToLoad(1);
            return;
        }
        String string = bundle.getString("apiKey");
        if (TextUtils.isEmpty(string)) {
            Log.w(TAG, "Failed to request ad from Nend: Missing or invalid API Key.");
            adFailedToLoad(1);
            return;
        }
        int i = Integer.parseInt(bundle.getString("spotId", AppEventsConstants.EVENT_PARAM_VALUE_NO));
        if (i <= 0) {
            Log.w(TAG, "Failed to request ad from Nend: Missing or invalid Spot ID.");
            adFailedToLoad(1);
            return;
        }
        this.mActivityWeakReference = new WeakReference<>((Activity) context);
        if (bundle2 != null && ((InterstitialType) bundle2.getSerializable(KEY_INTERSTITIAL_TYPE)) == InterstitialType.TYPE_VIDEO) {
            requestNendInterstialVideo(context, string, i, bundle2.getString("key_user_id", ""), mediationAdRequest);
        } else {
            requestNendInterstitial(context, string, i);
        }
    }

    private void requestNendInterstitial(Context context, String str, int i) {
        NendAdInterstitial.loadAd(context, str, i);
        NendAdInterstitial.isAutoReloadEnabled = false;
        NendAdInterstitial.setListener(new NendAdInterstitial.OnCompletionListener() { // from class: com.google.ads.mediation.nend.NendAdapter.2
            @Override // net.nend.android.NendAdInterstitial.OnCompletionListener
            public void onCompletion(NendAdInterstitial.NendAdInterstitialStatusCode nendAdInterstitialStatusCode) {
                int i2 = AnonymousClass5.$SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode[nendAdInterstitialStatusCode.ordinal()];
                if (i2 == 1) {
                    NendAdapter.this.adLoaded();
                    return;
                }
                if (i2 == 2) {
                    NendAdapter.this.adFailedToLoad(1);
                    return;
                }
                if (i2 == 3) {
                    NendAdapter.this.adFailedToLoad(0);
                } else if (i2 == 4) {
                    NendAdapter.this.adFailedToLoad(1);
                } else {
                    if (i2 != 5) {
                        return;
                    }
                    NendAdapter.this.adFailedToLoad(0);
                }
            }
        });
    }

    private void requestNendInterstialVideo(Context context, String str, int i, String str2, MediationAdRequest mediationAdRequest) {
        this.mNendAdInterstitialVideo = new NendAdInterstitialVideo(context, i, str);
        this.mNendAdInterstitialVideo.setMediationName(AdColonyAppOptions.ADMOB);
        if (!TextUtils.isEmpty(str2)) {
            this.mNendAdInterstitialVideo.setUserId(str2);
        }
        this.mNendAdInterstitialVideo.setAdListener(new NendAdVideoListener() { // from class: com.google.ads.mediation.nend.NendAdapter.3
            @Override // net.nend.android.NendAdVideoListener
            public void onLoaded(NendAdVideo nendAdVideo) {
                NendAdapter.this.adLoaded();
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onFailedToLoad(NendAdVideo nendAdVideo, int i2) {
                NendAdapter.this.adFailedToLoad(ErrorUtil.convertErrorCodeFromNendVideoToAdMob(i2));
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onFailedToPlay(NendAdVideo nendAdVideo) {
                Log.w(NendMediationAdapter.TAG, "Interstitial video ad failed to play...");
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onShown(NendAdVideo nendAdVideo) {
                NendAdapter.this.adOpened();
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onClosed(NendAdVideo nendAdVideo) {
                NendAdapter.this.adClosed();
                if (NendAdapter.this.mInterstitialVideoStatus == InterstitialVideoStatus.PLAYING_WHEN_CLICKED) {
                    NendAdapter.this.adLeftApplication();
                }
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onStarted(NendAdVideo nendAdVideo) {
                NendAdapter.this.mInterstitialVideoStatus = InterstitialVideoStatus.PLAYING;
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onStopped(NendAdVideo nendAdVideo) {
                if (NendAdapter.this.mInterstitialVideoStatus != InterstitialVideoStatus.PLAYING_WHEN_CLICKED) {
                    NendAdapter.this.mInterstitialVideoStatus = InterstitialVideoStatus.STOPPED;
                }
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onCompleted(NendAdVideo nendAdVideo) {
                NendAdapter.this.mInterstitialVideoStatus = InterstitialVideoStatus.STOPPED;
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onAdClicked(NendAdVideo nendAdVideo) {
                int i2 = AnonymousClass5.$SwitchMap$com$google$ads$mediation$nend$NendAdapter$InterstitialVideoStatus[NendAdapter.this.mInterstitialVideoStatus.ordinal()];
                if (i2 == 1 || i2 == 2) {
                    NendAdapter.this.mInterstitialVideoStatus = InterstitialVideoStatus.PLAYING_WHEN_CLICKED;
                } else {
                    NendAdapter.this.adLeftApplication();
                }
            }

            @Override // net.nend.android.NendAdVideoListener
            public void onInformationClicked(NendAdVideo nendAdVideo) {
                NendAdapter.this.adLeftApplication();
            }
        });
        this.mNendAdInterstitialVideo.loadAd();
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        if (this.mNendAdInterstitialVideo != null) {
            showNendAdInterstitialVideo();
        } else {
            showNendAdInterstitial();
        }
    }

    private void showNendAdInterstitial() {
        WeakReference<Activity> weakReference = this.mActivityWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            Log.e(TAG, "Failed to show Nend Interstitial ad: An activity context is required to show Nend ads.");
            return;
        }
        switch (NendAdInterstitial.showAd(this.mActivityWeakReference.get(), new NendAdInterstitial.OnClickListener() { // from class: com.google.ads.mediation.nend.NendAdapter.4
            @Override // net.nend.android.NendAdInterstitial.OnClickListener
            public void onClick(NendAdInterstitial.NendAdInterstitialClickType nendAdInterstitialClickType) {
                int i = AnonymousClass5.$SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialClickType[nendAdInterstitialClickType.ordinal()];
                if (i == 1) {
                    NendAdapter.this.adClosed();
                    return;
                }
                if (i == 2) {
                    NendAdapter.this.adLeftApplication();
                    NendAdapter.this.adClosed();
                } else {
                    if (i != 3) {
                        return;
                    }
                    NendAdapter.this.adLeftApplication();
                    NendAdapter.this.adClosed();
                }
            }
        })) {
            case AD_SHOW_SUCCESS:
                adOpened();
                break;
        }
    }

    private void showNendAdInterstitialVideo() {
        if (this.mNendAdInterstitialVideo.isLoaded()) {
            WeakReference<Activity> weakReference = this.mActivityWeakReference;
            if (weakReference != null && weakReference.get() != null) {
                this.mNendAdInterstitialVideo.showAd(this.mActivityWeakReference.get());
                return;
            } else {
                Log.e(TAG, "Failed to show Nend Interstitial ad: An activity context is required to show Nend ads.");
                return;
            }
        }
        Log.w(TAG, "Nend Interstitial video ad is not ready.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.mNendAdView;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        AdSize supportedAdSize = getSupportedAdSize(context, adSize);
        if (supportedAdSize == null) {
            Log.w(TAG, "Failed to request ad, AdSize is null.");
            if (mediationBannerListener != null) {
                mediationBannerListener.onAdFailedToLoad(this, 1);
                return;
            }
            return;
        }
        int width = supportedAdSize.getWidth();
        int height = supportedAdSize.getHeight();
        this.mListener = mediationBannerListener;
        if ((width == 320 && height == 50) || ((width == 320 && height == 100) || ((width == 300 && height == 250) || (width == 728 && height == 90)))) {
            String string = bundle.getString("apiKey");
            String string2 = bundle.getString("spotId");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                this.mNendAdView = new NendAdView(context, Integer.parseInt(string2), string);
                this.mNendAdView.pause();
                this.mNendAdView.setListener(this);
                this.mNendAdView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
                this.mNendAdView.loadAd();
                this.mIsRequestBannerAd = true;
                return;
            }
            Log.w(TAG, "Failed to load ad from Nend:Missing or Invalid API Key and/or Spot ID.");
            MediationBannerListener mediationBannerListener2 = this.mListener;
            if (mediationBannerListener2 != null) {
                mediationBannerListener2.onAdFailedToLoad(this, 0);
                return;
            }
            return;
        }
        Log.w(TAG, "Invalid Ad type");
        MediationBannerListener mediationBannerListener3 = this.mListener;
        if (mediationBannerListener3 != null) {
            mediationBannerListener3.onAdFailedToLoad(this, 1);
        }
    }

    AdSize getSupportedAdSize(Context context, AdSize adSize) {
        AdSize adSize2 = new AdSize(adSize.getWidth(), adSize.getHeight());
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(AdSize.BANNER);
        arrayList.add(AdSize.LARGE_BANNER);
        arrayList.add(new AdSize(300, 100));
        arrayList.add(AdSize.MEDIUM_RECTANGLE);
        arrayList.add(AdSize.LEADERBOARD);
        return findClosestSize(context, adSize2, arrayList);
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

    @Override // net.nend.android.NendAdListener
    public void onReceiveAd(NendAdView nendAdView) {
        MediationBannerListener mediationBannerListener = this.mListener;
        if (mediationBannerListener != null && this.mIsRequestBannerAd) {
            mediationBannerListener.onAdLoaded(this);
            this.mIsRequestBannerAd = false;
        } else {
            Log.d(TAG, "This ad is auto reloading by nend network.");
        }
    }

    @Override // net.nend.android.NendAdListener
    public void onFailedToReceiveAd(NendAdView nendAdView) {
        if (this.mIsRequestBannerAd) {
            this.mIsRequestBannerAd = false;
            NendAdView.NendError nendError = nendAdView.getNendError();
            if (this.mListener != null) {
                int i = AnonymousClass5.$SwitchMap$net$nend$android$NendAdView$NendError[nendError.ordinal()];
                if (i == 1) {
                    this.mListener.onAdFailedToLoad(this, 0);
                    return;
                }
                if (i == 2) {
                    this.mListener.onAdFailedToLoad(this, 1);
                    return;
                }
                if (i == 3) {
                    this.mListener.onAdFailedToLoad(this, 1);
                } else if (i == 4) {
                    this.mListener.onAdFailedToLoad(this, 0);
                } else {
                    if (i != 5) {
                        return;
                    }
                    this.mListener.onAdFailedToLoad(this, 0);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.google.ads.mediation.nend.NendAdapter$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$google$ads$mediation$nend$NendAdapter$InterstitialVideoStatus;
        static final /* synthetic */ int[] $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialClickType;
        static final /* synthetic */ int[] $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode;
        static final /* synthetic */ int[] $SwitchMap$net$nend$android$NendAdView$NendError = new int[NendAdView.NendError.values().length];

        static {
            try {
                $SwitchMap$net$nend$android$NendAdView$NendError[NendAdView.NendError.INVALID_RESPONSE_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdView$NendError[NendAdView.NendError.FAILED_AD_DOWNLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdView$NendError[NendAdView.NendError.FAILED_AD_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdView$NendError[NendAdView.NendError.AD_SIZE_TOO_LARGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdView$NendError[NendAdView.NendError.AD_SIZE_DIFFERENCES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialShowResult = new int[NendAdInterstitial.NendAdInterstitialShowResult.values().length];
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialShowResult[NendAdInterstitial.NendAdInterstitialShowResult.AD_SHOW_SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialShowResult[NendAdInterstitial.NendAdInterstitialShowResult.AD_SHOW_ALREADY.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialShowResult[NendAdInterstitial.NendAdInterstitialShowResult.AD_FREQUENCY_NOT_RECHABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialShowResult[NendAdInterstitial.NendAdInterstitialShowResult.AD_REQUEST_INCOMPLETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialShowResult[NendAdInterstitial.NendAdInterstitialShowResult.AD_LOAD_INCOMPLETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialShowResult[NendAdInterstitial.NendAdInterstitialShowResult.AD_DOWNLOAD_INCOMPLETE.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialClickType = new int[NendAdInterstitial.NendAdInterstitialClickType.values().length];
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialClickType[NendAdInterstitial.NendAdInterstitialClickType.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialClickType[NendAdInterstitial.NendAdInterstitialClickType.DOWNLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialClickType[NendAdInterstitial.NendAdInterstitialClickType.INFORMATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            $SwitchMap$com$google$ads$mediation$nend$NendAdapter$InterstitialVideoStatus = new int[InterstitialVideoStatus.values().length];
            try {
                $SwitchMap$com$google$ads$mediation$nend$NendAdapter$InterstitialVideoStatus[InterstitialVideoStatus.PLAYING.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$ads$mediation$nend$NendAdapter$InterstitialVideoStatus[InterstitialVideoStatus.PLAYING_WHEN_CLICKED.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode = new int[NendAdInterstitial.NendAdInterstitialStatusCode.values().length];
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode[NendAdInterstitial.NendAdInterstitialStatusCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode[NendAdInterstitial.NendAdInterstitialStatusCode.FAILED_AD_DOWNLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode[NendAdInterstitial.NendAdInterstitialStatusCode.FAILED_AD_INCOMPLETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode[NendAdInterstitial.NendAdInterstitialStatusCode.FAILED_AD_REQUEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$net$nend$android$NendAdInterstitial$NendAdInterstitialStatusCode[NendAdInterstitial.NendAdInterstitialStatusCode.INVALID_RESPONSE_TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    @Override // net.nend.android.NendAdListener
    public void onClick(NendAdView nendAdView) {
        MediationBannerListener mediationBannerListener = this.mListener;
        if (mediationBannerListener != null) {
            mediationBannerListener.onAdOpened(this);
            this.mListener.onAdLeftApplication(this);
        }
    }

    @Override // net.nend.android.NendAdListener
    public void onDismissScreen(NendAdView nendAdView) {
        MediationBannerListener mediationBannerListener = this.mListener;
        if (mediationBannerListener != null) {
            mediationBannerListener.onAdClosed(this);
        }
    }

    @Override // net.nend.android.NendAdInformationListener
    public void onInformationButtonClick(NendAdView nendAdView) {
        MediationBannerListener mediationBannerListener = this.mListener;
        if (mediationBannerListener != null) {
            mediationBannerListener.onAdLeftApplication(this);
        }
    }

    public void adLeftApplication() {
        MediationInterstitialListener mediationInterstitialListener = this.mListenerInterstitial;
        if (mediationInterstitialListener != null) {
            mediationInterstitialListener.onAdLeftApplication(this);
        }
    }

    public void adClosed() {
        MediationInterstitialListener mediationInterstitialListener = this.mListenerInterstitial;
        if (mediationInterstitialListener != null) {
            mediationInterstitialListener.onAdClosed(this);
        }
    }

    public void adFailedToLoad(int i) {
        MediationInterstitialListener mediationInterstitialListener = this.mListenerInterstitial;
        if (mediationInterstitialListener != null) {
            mediationInterstitialListener.onAdFailedToLoad(this, i);
        }
    }

    public void adLoaded() {
        MediationInterstitialListener mediationInterstitialListener = this.mListenerInterstitial;
        if (mediationInterstitialListener != null) {
            mediationInterstitialListener.onAdLoaded(this);
        }
    }

    public void adOpened() {
        MediationInterstitialListener mediationInterstitialListener = this.mListenerInterstitial;
        if (mediationInterstitialListener != null) {
            mediationInterstitialListener.onAdOpened(this);
        }
    }

    @Override // com.google.android.gms.ads.mediation.OnContextChangedListener
    public void onContextChanged(Context context) {
        if (!(context instanceof Activity)) {
            Log.w(TAG, "Nend Ads require an Activity context to show ads.");
        } else {
            this.mActivityWeakReference = new WeakReference<>((Activity) context);
        }
    }
}
