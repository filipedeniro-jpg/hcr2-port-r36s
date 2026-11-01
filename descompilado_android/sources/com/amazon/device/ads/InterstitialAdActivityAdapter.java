package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
class InterstitialAdActivityAdapter implements AdActivity.AdActivityAdapter {
    private static final String LOGTAG = "InterstitialAdActivityAdapter";
    private AdController adController;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private final AndroidBuildInfo buildInfo = new AndroidBuildInfo();
    private Activity activity = null;

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void onConfigurationChanged(android.content.res.Configuration configuration) {
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void onResume() {
    }

    InterstitialAdActivityAdapter() {
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
        this.activity.getWindow().setFlags(1024, 1024);
        AndroidTargetUtils.hideActionAndStatusBars(this.buildInfo, this.activity);
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void onCreate() {
        AndroidTargetUtils.enableHardwareAcceleration(this.buildInfo, this.activity.getWindow());
        this.adController = getAdController();
        AdController adController = this.adController;
        if (adController == null) {
            this.logger.e("Failed to show interstitial ad due to an error in the Activity.");
            InterstitialAd.resetIsAdShowing();
            this.activity.finish();
            return;
        }
        adController.setAdActivity(this.activity);
        this.adController.addSDKEventListener(new InterstitialAdSDKEventListener());
        ViewGroup viewGroup = (ViewGroup) this.adController.getView().getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.adController.getView());
        }
        this.activity.setContentView(this.adController.getView());
        this.adController.adShown();
    }

    AdController getAdController() {
        return AdControllerFactory.getCachedAdController();
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void onPause() {
        AdController adController = this.adController;
        if (adController != null) {
            adController.fireViewableEvent();
        }
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void onStop() {
        AdController adController;
        if (!this.activity.isFinishing() || (adController = this.adController) == null) {
            return;
        }
        adController.fireViewableEvent();
        this.adController.closeAd();
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void onDestroy() {
        AdController adController = this.adController;
        if (adController != null) {
            adController.fireViewableEvent();
            this.adController.closeAd();
        }
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public boolean onBackPressed() {
        AdController adController = this.adController;
        if (adController != null) {
            return adController.onBackButtonPress();
        }
        return false;
    }

    @Override // com.amazon.device.ads.AdActivity.AdActivityAdapter
    public void onWindowFocusChanged() {
        AdController adController = this.adController;
        if (adController != null) {
            adController.fireViewableEvent();
        }
    }

    Activity getActivity() {
        return this.activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishActivity() {
        if (this.activity.isFinishing()) {
            return;
        }
        this.adController = null;
        this.activity.finish();
    }

    class InterstitialAdSDKEventListener implements SDKEventListener {
        InterstitialAdSDKEventListener() {
        }

        @Override // com.amazon.device.ads.SDKEventListener
        public void onSDKEvent(SDKEvent sDKEvent, AdControlAccessor adControlAccessor) {
            if (sDKEvent.getEventType().equals(SDKEvent.SDKEventType.CLOSED)) {
                InterstitialAdActivityAdapter.this.finishActivity();
            }
        }
    }
}
