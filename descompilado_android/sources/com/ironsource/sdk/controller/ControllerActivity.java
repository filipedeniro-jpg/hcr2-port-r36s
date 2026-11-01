package com.ironsource.sdk.controller;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.data.AdUnitsState;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.handlers.BackButtonHandler;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ControllerActivity extends Activity implements OnWebViewChangeListener, VideoEventsListener {
    private static final String TAG = "ControllerActivity";
    private static final int WEB_VIEW_VIEW_ID = 1;
    private RelativeLayout mContainer;
    private String mProductType;
    private AdUnitsState mState;
    private IronSourceWebView mWebViewController;
    private FrameLayout mWebViewFrameContainer;
    public int currentRequestedRotation = -1;
    private boolean mIsImmersive = false;
    private Handler mUiThreadHandler = new Handler();
    private final Runnable decorViewSettings = new Runnable() { // from class: com.ironsource.sdk.controller.ControllerActivity.1
        @Override // java.lang.Runnable
        public void run() {
            ControllerActivity.this.getWindow().getDecorView().setSystemUiVisibility(SDKUtils.getActivityUIFlags(ControllerActivity.this.mIsImmersive));
        }
    };
    final RelativeLayout.LayoutParams MATCH_PARENT_LAYOUT_PARAMS = new RelativeLayout.LayoutParams(-1, -1);
    private boolean calledFromOnCreate = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Logger.i(TAG, "onCreate");
            hideActivityTitle();
            hideActivtiyStatusBar();
            this.mWebViewController = IronSourceAdsPublisherAgent.getInstance(this).getWebViewController();
            this.mWebViewController.setId(1);
            this.mWebViewController.setOnWebViewControllerChangeListener(this);
            this.mWebViewController.setVideoEventsListener(this);
            Intent intent = getIntent();
            this.mProductType = intent.getStringExtra(Constants.ParametersKeys.PRODUCT_TYPE);
            this.mIsImmersive = intent.getBooleanExtra(Constants.ParametersKeys.IMMERSIVE, false);
            if (this.mIsImmersive) {
                getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.ironsource.sdk.controller.ControllerActivity.2
                    @Override // android.view.View.OnSystemUiVisibilityChangeListener
                    public void onSystemUiVisibilityChange(int i) {
                        if ((i & 4098) == 0) {
                            ControllerActivity.this.mUiThreadHandler.removeCallbacks(ControllerActivity.this.decorViewSettings);
                            ControllerActivity.this.mUiThreadHandler.postDelayed(ControllerActivity.this.decorViewSettings, 500L);
                        }
                    }
                });
                runOnUiThread(this.decorViewSettings);
            }
            if (!TextUtils.isEmpty(this.mProductType) && SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(this.mProductType)) {
                if (bundle != null) {
                    AdUnitsState adUnitsState = (AdUnitsState) bundle.getParcelable("state");
                    if (adUnitsState != null) {
                        this.mState = adUnitsState;
                        this.mWebViewController.restoreState(adUnitsState);
                    }
                    finish();
                } else {
                    this.mState = this.mWebViewController.getSavedState();
                }
            }
            this.mContainer = new RelativeLayout(this);
            setContentView(this.mContainer, this.MATCH_PARENT_LAYOUT_PARAMS);
            this.mWebViewFrameContainer = this.mWebViewController.getLayout();
            if (this.mContainer.findViewById(1) == null && this.mWebViewFrameContainer.getParent() != null) {
                this.calledFromOnCreate = true;
                finish();
            }
            initOrientationState();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    private void initOrientationState() {
        Intent intent = getIntent();
        handleOrientationState(intent.getStringExtra(Constants.ParametersKeys.ORIENTATION_SET_FLAG), intent.getIntExtra(Constants.ParametersKeys.ROTATION_SET_FLAG, 0));
    }

    private void handleOrientationState(String str, int i) {
        if (str != null) {
            if ("landscape".equalsIgnoreCase(str)) {
                setInitiateLandscapeOrientation();
                return;
            }
            if ("portrait".equalsIgnoreCase(str)) {
                setInitiatePortraitOrientation();
                return;
            }
            if ("device".equalsIgnoreCase(str)) {
                if (DeviceStatus.isDeviceOrientationLocked(this)) {
                    setRequestedOrientation(1);
                }
            } else if (getRequestedOrientation() == -1) {
                setRequestedOrientation(4);
            }
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (TextUtils.isEmpty(this.mProductType) || !SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(this.mProductType)) {
            return;
        }
        this.mState.setShouldRestore(true);
        bundle.putParcelable("state", this.mState);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Logger.i(TAG, "onResume");
        this.mContainer.addView(this.mWebViewFrameContainer, this.MATCH_PARENT_LAYOUT_PARAMS);
        IronSourceWebView ironSourceWebView = this.mWebViewController;
        if (ironSourceWebView != null) {
            ironSourceWebView.registerConnectionReceiver(this);
            this.mWebViewController.resume();
            this.mWebViewController.viewableChange(true, Constants.ParametersKeys.MAIN);
        }
        ((AudioManager) getSystemService("audio")).requestAudioFocus(null, 3, 2);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Logger.i(TAG, "onPause");
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(null);
        IronSourceWebView ironSourceWebView = this.mWebViewController;
        if (ironSourceWebView != null) {
            ironSourceWebView.unregisterConnectionReceiver(this);
            this.mWebViewController.pause();
            this.mWebViewController.viewableChange(false, Constants.ParametersKeys.MAIN);
        }
        removeWebViewContainerView();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Logger.i(TAG, "onDestroy");
        if (this.calledFromOnCreate) {
            removeWebViewContainerView();
        }
        IronSourceWebView ironSourceWebView = this.mWebViewController;
        if (ironSourceWebView != null) {
            ironSourceWebView.setState(IronSourceWebView.State.Gone);
            this.mWebViewController.removeVideoEventsListener();
            this.mWebViewController.notifyLifeCycle(this.mProductType, "onDestroy");
        }
    }

    private void removeWebViewContainerView() {
        if (this.mContainer != null) {
            ViewGroup viewGroup = (ViewGroup) this.mWebViewFrameContainer.getParent();
            if (viewGroup.findViewById(1) != null) {
                viewGroup.removeView(this.mWebViewFrameContainer);
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnWebViewChangeListener
    public void onCloseRequested() {
        finish();
    }

    @Override // com.ironsource.sdk.listeners.OnWebViewChangeListener
    public void onOrientationChanged(String str, int i) {
        handleOrientationState(str, i);
    }

    @Override // com.ironsource.sdk.listeners.OnWebViewChangeListener
    public boolean onBackButtonPressed() {
        onBackPressed();
        return true;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        Logger.i(TAG, "onBackPressed");
        if (BackButtonHandler.getInstance().handleBackButton(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Logger.i(TAG, "onUserLeaveHint");
    }

    private void hideActivityTitle() {
        requestWindowFeature(1);
    }

    private void hideActivtiyStatusBar() {
        getWindow().setFlags(1024, 1024);
    }

    private void keepScreenOn() {
        runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.ControllerActivity.3
            @Override // java.lang.Runnable
            public void run() {
                ControllerActivity.this.getWindow().addFlags(128);
            }
        });
    }

    private void cancelScreenOn() {
        runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.ControllerActivity.4
            @Override // java.lang.Runnable
            public void run() {
                ControllerActivity.this.getWindow().clearFlags(128);
            }
        });
    }

    private void setInitiateLandscapeOrientation() {
        int applicationRotation = DeviceStatus.getApplicationRotation(this);
        Logger.i(TAG, "setInitiateLandscapeOrientation");
        if (applicationRotation == 0) {
            Logger.i(TAG, "ROTATION_0");
            setRequestedOrientation(0);
            return;
        }
        if (applicationRotation == 2) {
            Logger.i(TAG, "ROTATION_180");
            setRequestedOrientation(8);
        } else if (applicationRotation == 3) {
            Logger.i(TAG, "ROTATION_270 Right Landscape");
            setRequestedOrientation(8);
        } else if (applicationRotation == 1) {
            Logger.i(TAG, "ROTATION_90 Left Landscape");
            setRequestedOrientation(0);
        } else {
            Logger.i(TAG, "No Rotation");
        }
    }

    private void setInitiatePortraitOrientation() {
        int applicationRotation = DeviceStatus.getApplicationRotation(this);
        Logger.i(TAG, "setInitiatePortraitOrientation");
        if (applicationRotation == 0) {
            Logger.i(TAG, "ROTATION_0");
            setRequestedOrientation(1);
            return;
        }
        if (applicationRotation == 2) {
            Logger.i(TAG, "ROTATION_180");
            setRequestedOrientation(9);
        } else if (applicationRotation == 1) {
            Logger.i(TAG, "ROTATION_270 Right Landscape");
            setRequestedOrientation(1);
        } else if (applicationRotation == 3) {
            Logger.i(TAG, "ROTATION_90 Left Landscape");
            setRequestedOrientation(1);
        } else {
            Logger.i(TAG, "No Rotation");
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.mWebViewController.inCustomView()) {
            this.mWebViewController.hideCustomView();
            return true;
        }
        if (this.mIsImmersive && (i == 25 || i == 24)) {
            this.mUiThreadHandler.removeCallbacks(this.decorViewSettings);
            this.mUiThreadHandler.postDelayed(this.decorViewSettings, 500L);
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        if (this.currentRequestedRotation != i) {
            Logger.i(TAG, "Rotation: Req = " + i + " Curr = " + this.currentRequestedRotation);
            this.currentRequestedRotation = i;
            super.setRequestedOrientation(i);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mIsImmersive && z) {
            runOnUiThread(this.decorViewSettings);
        }
    }

    @Override // com.ironsource.sdk.controller.VideoEventsListener
    public void onVideoStarted() {
        toggleKeepScreen(true);
    }

    @Override // com.ironsource.sdk.controller.VideoEventsListener
    public void onVideoPaused() {
        toggleKeepScreen(false);
    }

    @Override // com.ironsource.sdk.controller.VideoEventsListener
    public void onVideoResumed() {
        toggleKeepScreen(true);
    }

    @Override // com.ironsource.sdk.controller.VideoEventsListener
    public void onVideoEnded() {
        toggleKeepScreen(false);
    }

    @Override // com.ironsource.sdk.controller.VideoEventsListener
    public void onVideoStopped() {
        toggleKeepScreen(false);
    }

    public void toggleKeepScreen(boolean z) {
        if (z) {
            keepScreenOn();
        } else {
            cancelScreenOn();
        }
    }
}
