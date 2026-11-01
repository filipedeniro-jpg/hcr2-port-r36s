package com.google.ads.mediation.mytarget;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.my.target.ads.InterstitialAd;
import com.my.target.ads.MyTargetView;
import com.my.target.common.CustomParams;
import java.util.Date;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
public class MyTargetAdapter extends MyTargetMediationAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {

    @Nullable
    private InterstitialAd mInterstitial;

    @Nullable
    private MyTargetView mMyTargetView;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        int iCheckAndGetSlotId = MyTargetTools.checkAndGetSlotId(context, bundle);
        Log.d(TAG, "Requesting myTarget banner mediation, slotId: " + iCheckAndGetSlotId);
        if (iCheckAndGetSlotId < 0) {
            if (mediationBannerListener != null) {
                mediationBannerListener.onAdFailedToLoad(this, 1);
                return;
            }
            return;
        }
        if (adSize == null) {
            Log.w(TAG, "Failed to request ad, AdSize is null.");
            if (mediationBannerListener != null) {
                mediationBannerListener.onAdFailedToLoad(this, 1);
                return;
            }
            return;
        }
        MyTargetBannerListener myTargetBannerListener = mediationBannerListener != null ? new MyTargetBannerListener(mediationBannerListener) : null;
        if (adSize.getWidth() == 300 && adSize.getHeight() == 250) {
            Log.d(TAG, "Loading myTarget banner, size: 300x250");
            loadBanner(myTargetBannerListener, mediationAdRequest, iCheckAndGetSlotId, 1, context);
            return;
        }
        if (adSize.getWidth() == 728 && adSize.getHeight() == 90) {
            Log.d(TAG, "Loading myTarget banner, size: 728x90");
            loadBanner(myTargetBannerListener, mediationAdRequest, iCheckAndGetSlotId, 2, context);
            return;
        }
        if (adSize.getWidth() == 320 && adSize.getHeight() == 50) {
            Log.d(TAG, "Loading myTarget banner, size: 320x50");
            loadBanner(myTargetBannerListener, mediationAdRequest, iCheckAndGetSlotId, 0, context);
            return;
        }
        Log.w(TAG, "AdSize " + adSize.toString() + " is not currently supported");
        if (mediationBannerListener != null) {
            mediationBannerListener.onAdFailedToLoad(this, 3);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.mMyTargetView;
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        int iCheckAndGetSlotId = MyTargetTools.checkAndGetSlotId(context, bundle);
        Log.d(TAG, "Requesting myTarget interstitial mediation, slotId: " + iCheckAndGetSlotId);
        if (iCheckAndGetSlotId < 0) {
            if (mediationInterstitialListener != null) {
                mediationInterstitialListener.onAdFailedToLoad(this, 1);
                return;
            }
            return;
        }
        MyTargetInterstitialListener myTargetInterstitialListener = mediationInterstitialListener != null ? new MyTargetInterstitialListener(mediationInterstitialListener) : null;
        InterstitialAd interstitialAd = this.mInterstitial;
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        this.mInterstitial = new InterstitialAd(iCheckAndGetSlotId, context);
        CustomParams customParams = this.mInterstitial.getCustomParams();
        customParams.setCustomParam("mediation", "1");
        if (mediationAdRequest != null) {
            int gender = mediationAdRequest.getGender();
            Log.d(TAG, "Set gender to " + gender);
            customParams.setGender(gender);
            Date birthday = mediationAdRequest.getBirthday();
            if (birthday != null && birthday.getTime() != -1) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
                gregorianCalendar.setTimeInMillis(birthday.getTime());
                int i = gregorianCalendar2.get(1) - gregorianCalendar.get(1);
                if (i >= 0) {
                    Log.d(TAG, "Set age to " + i);
                    customParams.setAge(i);
                }
            }
        }
        this.mInterstitial.setListener(myTargetInterstitialListener);
        this.mInterstitial.load();
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        InterstitialAd interstitialAd = this.mInterstitial;
        if (interstitialAd != null) {
            interstitialAd.show();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        MyTargetView myTargetView = this.mMyTargetView;
        if (myTargetView != null) {
            myTargetView.destroy();
        }
        InterstitialAd interstitialAd = this.mInterstitial;
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
    }

    private void loadBanner(@Nullable MyTargetBannerListener myTargetBannerListener, @Nullable MediationAdRequest mediationAdRequest, int i, int i2, @NonNull Context context) {
        MyTargetView myTargetView = this.mMyTargetView;
        if (myTargetView != null) {
            myTargetView.destroy();
        }
        this.mMyTargetView = new MyTargetView(context);
        this.mMyTargetView.init(i, i2, false);
        CustomParams customParams = this.mMyTargetView.getCustomParams();
        if (customParams != null) {
            if (mediationAdRequest != null) {
                int gender = mediationAdRequest.getGender();
                customParams.setGender(gender);
                Log.d(TAG, "Set gender to " + gender);
                Date birthday = mediationAdRequest.getBirthday();
                if (birthday != null && birthday.getTime() != -1) {
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
                    gregorianCalendar.setTimeInMillis(birthday.getTime());
                    int i3 = gregorianCalendar2.get(1) - gregorianCalendar.get(1);
                    if (i3 >= 0) {
                        Log.d(TAG, "Set age to " + i3);
                        customParams.setAge(i3);
                    }
                }
            }
            customParams.setCustomParam("mediation", "1");
        }
        this.mMyTargetView.setListener(myTargetBannerListener);
        this.mMyTargetView.load();
    }

    private class MyTargetBannerListener implements MyTargetView.MyTargetViewListener {

        @NonNull
        private final MediationBannerListener listener;

        MyTargetBannerListener(MediationBannerListener mediationBannerListener) {
            this.listener = mediationBannerListener;
        }

        @Override // com.my.target.ads.MyTargetView.MyTargetViewListener
        public void onLoad(@NonNull MyTargetView myTargetView) {
            Log.d(MyTargetMediationAdapter.TAG, "Banner mediation Ad loaded");
            this.listener.onAdLoaded(MyTargetAdapter.this);
        }

        @Override // com.my.target.ads.MyTargetView.MyTargetViewListener
        public void onNoAd(@NonNull String str, @NonNull MyTargetView myTargetView) {
            Log.i(MyTargetMediationAdapter.TAG, "Banner mediation Ad failed to load: " + str);
            this.listener.onAdFailedToLoad(MyTargetAdapter.this, 3);
        }

        @Override // com.my.target.ads.MyTargetView.MyTargetViewListener
        public void onClick(@NonNull MyTargetView myTargetView) {
            Log.d(MyTargetMediationAdapter.TAG, "Banner mediation Ad clicked");
            this.listener.onAdClicked(MyTargetAdapter.this);
            this.listener.onAdOpened(MyTargetAdapter.this);
            this.listener.onAdLeftApplication(MyTargetAdapter.this);
        }
    }

    private class MyTargetInterstitialListener implements InterstitialAd.InterstitialAdListener {

        @NonNull
        private final MediationInterstitialListener listener;

        @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
        public void onVideoCompleted(@NonNull InterstitialAd interstitialAd) {
        }

        MyTargetInterstitialListener(MediationInterstitialListener mediationInterstitialListener) {
            this.listener = mediationInterstitialListener;
        }

        @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
        public void onLoad(@NonNull InterstitialAd interstitialAd) {
            Log.d(MyTargetMediationAdapter.TAG, "Interstitial mediation Ad loaded");
            this.listener.onAdLoaded(MyTargetAdapter.this);
        }

        @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
        public void onNoAd(@NonNull String str, @NonNull InterstitialAd interstitialAd) {
            Log.i(MyTargetMediationAdapter.TAG, "Interstitial mediation Ad failed to load: " + str);
            this.listener.onAdFailedToLoad(MyTargetAdapter.this, 3);
        }

        @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
        public void onClick(@NonNull InterstitialAd interstitialAd) {
            Log.d(MyTargetMediationAdapter.TAG, "Interstitial mediation Ad clicked");
            this.listener.onAdClicked(MyTargetAdapter.this);
            this.listener.onAdLeftApplication(MyTargetAdapter.this);
        }

        @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
        public void onDismiss(@NonNull InterstitialAd interstitialAd) {
            Log.d(MyTargetMediationAdapter.TAG, "Interstitial mediation Ad dismissed");
            this.listener.onAdClosed(MyTargetAdapter.this);
        }

        @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
        public void onDisplay(@NonNull InterstitialAd interstitialAd) {
            Log.d(MyTargetMediationAdapter.TAG, "Interstitial mediation Ad displayed");
            this.listener.onAdOpened(MyTargetAdapter.this);
        }
    }
}
