package com.ironsource.mediationsdk;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.BannerListener;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceBannerLayout extends FrameLayout {
    private boolean isDestroyed;
    private Activity mActivity;
    private BannerListener mBannerListener;
    private View mBannerView;
    private boolean mIsBannerDisplayed;
    private String mPlacementName;
    private ISBannerSize mSize;

    public IronSourceBannerLayout(Activity activity, ISBannerSize iSBannerSize) {
        super(activity);
        this.isDestroyed = false;
        this.mIsBannerDisplayed = false;
        this.mActivity = activity;
        this.mSize = iSBannerSize == null ? ISBannerSize.BANNER : iSBannerSize;
    }

    protected void destroyBanner() {
        this.isDestroyed = true;
        this.mBannerListener = null;
        this.mActivity = null;
        this.mSize = null;
        this.mPlacementName = null;
        this.mBannerView = null;
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    public View getBannerView() {
        return this.mBannerView;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public ISBannerSize getSize() {
        return this.mSize;
    }

    public String getPlacementName() {
        return this.mPlacementName;
    }

    public void setPlacementName(String str) {
        this.mPlacementName = str;
    }

    public void setBannerListener(BannerListener bannerListener) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "setBannerListener()", 1);
        this.mBannerListener = bannerListener;
    }

    public void removeBannerListener() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "removeBannerListener()", 1);
        this.mBannerListener = null;
    }

    public BannerListener getBannerListener() {
        return this.mBannerListener;
    }

    void sendBannerAdLoaded(BannerSmash bannerSmash) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "onBannerAdLoaded() | internal | adapter: " + bannerSmash.getName(), 0);
        if (this.mBannerListener != null && !this.mIsBannerDisplayed) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdLoaded()", 1);
            this.mBannerListener.onBannerAdLoaded();
        }
        this.mIsBannerDisplayed = true;
    }

    void sendBannerAdLoadFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdLoadFailed()  error=" + ironSourceError, 1);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.IronSourceBannerLayout.1
            @Override // java.lang.Runnable
            public void run() {
                if (IronSourceBannerLayout.this.mIsBannerDisplayed) {
                    IronSourceBannerLayout.this.mBannerListener.onBannerAdLoadFailed(ironSourceError);
                    return;
                }
                try {
                    if (IronSourceBannerLayout.this.mBannerView != null) {
                        IronSourceBannerLayout.this.removeView(IronSourceBannerLayout.this.mBannerView);
                        IronSourceBannerLayout.this.mBannerView = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (IronSourceBannerLayout.this.mBannerListener != null) {
                    IronSourceBannerLayout.this.mBannerListener.onBannerAdLoadFailed(ironSourceError);
                }
            }
        });
    }

    void sendBannerAdClicked() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdClicked()", 1);
            this.mBannerListener.onBannerAdClicked();
        }
    }

    void sendBannerAdScreenPresented() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdScreenPresented()", 1);
            this.mBannerListener.onBannerAdScreenPresented();
        }
    }

    void sendBannerAdScreenDismissed() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdScreenDismissed()", 1);
            this.mBannerListener.onBannerAdScreenDismissed();
        }
    }

    void sendBannerAdLeftApplication() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdLeftApplication()", 1);
            this.mBannerListener.onBannerAdLeftApplication();
        }
    }

    void addViewWithFrameLayoutParams(final View view, final FrameLayout.LayoutParams layoutParams) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.IronSourceBannerLayout.2
            @Override // java.lang.Runnable
            public void run() {
                IronSourceBannerLayout.this.removeAllViews();
                IronSourceBannerLayout.this.mBannerView = view;
                IronSourceBannerLayout.this.addView(view, 0, layoutParams);
            }
        });
    }
}
