package com.vungle.warren.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import com.vungle.warren.AdvertisementPresenterFactory;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.VungleNativeAd;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.presenter.AdvertisementPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class VungleNativeView extends WebView implements AdView, VungleNativeAd {
    private static final String TAG = "com.vungle.warren.ui.VungleNativeView";
    private Handler handler;
    private AdvertisementPresenter presenter;
    private final AdvertisementPresenterFactory presenterFactory;

    @Override // com.vungle.warren.VungleNativeAd
    public View renderNativeView() {
        return this;
    }

    @Override // com.vungle.warren.ui.AdView
    public void setOrientation(int i) {
    }

    @Override // com.vungle.warren.ui.AdView
    public void showCTAOverlay(boolean z, boolean z2, int i) {
    }

    @Override // com.vungle.warren.ui.AdView
    public void updateWindow(boolean z) {
    }

    @Override // android.webkit.WebView
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resuming Flex");
        this.presenter.play();
    }

    @Override // android.webkit.WebView
    public void onPause() {
        super.onPause();
        this.presenter.stop(false, false);
    }

    public VungleNativeView(Context context, String str, DirectDownloadAdapter directDownloadAdapter, AdvertisementPresenter.EventListener eventListener) {
        super(context);
        this.presenterFactory = new AdvertisementPresenterFactory();
        setLayerType(2, null);
        this.presenter = this.presenterFactory.getAdPresenter(str, null, null);
        AdvertisementPresenter advertisementPresenter = this.presenter;
        if (advertisementPresenter == null) {
            if (eventListener != null) {
                eventListener.onError(new VungleException(10));
                return;
            }
            return;
        }
        advertisementPresenter.attach(this);
        if (directDownloadAdapter != null) {
            directDownloadAdapter.getSdkDownloadClient().setAdWebView(this);
            this.presenter.setDirectDownloadAdapter(directDownloadAdapter);
        }
        this.presenter.setEventListener(eventListener);
        this.presenter.prepare(null);
        this.handler = new Handler();
        prepare(null);
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void prepare(Bundle bundle) {
        setWebViewClient(this.presenter.getWebViewClient());
        WebSettings settings = getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setUseWideViewPort(false);
        addJavascriptInterface(new JavascriptBridge(this.presenter), Constants.JAVASCRIPT_INTERFACE_NAME);
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (Build.VERSION.SDK_INT < 17) {
            setAdVisibility(true);
        } else if (Build.VERSION.SDK_INT <= 19) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build.VERSION.SDK_INT > 19) {
            setVisibility(4);
        }
    }

    @Override // com.vungle.warren.VungleNativeAd
    public void finishDisplayingAd() {
        loadUrl("about:blank");
        this.presenter.stop(false, true);
    }

    @Override // com.vungle.warren.VungleNativeAd
    public void setAdVisibility(boolean z) {
        this.presenter.setAdVisibility(z);
    }

    @Override // com.vungle.warren.ui.AdView
    public void playVideo(Uri uri, boolean z) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a video player.");
    }

    @Override // com.vungle.warren.ui.AdView
    public void showWebsite(String str) {
        loadUrl(str);
    }

    @Override // com.vungle.warren.ui.AdView
    public String getWebsiteUrl() {
        return getUrl();
    }

    @Override // com.vungle.warren.ui.AdView
    public void close() {
        if (this.presenter.handleExit(null)) {
            finishDisplayingAd();
        }
    }

    @Override // com.vungle.warren.ui.AdView
    public void showCloseButton() {
        throw new UnsupportedOperationException("VungleNativeView does not implement a close button");
    }

    @Override // com.vungle.warren.ui.AdView
    public void open(String str) {
        Log.v(TAG, "Opening " + str);
        try {
            Intent uri = Intent.parseUri(str, 0);
            uri.addFlags(DriveFile.MODE_READ_ONLY);
            getContext().startActivity(uri);
        } catch (Exception e) {
            Log.e(TAG, "Cannot open url " + e.getLocalizedMessage());
        }
    }

    @Override // com.vungle.warren.ui.AdView
    public void showDialog(String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a dialog.");
    }

    @Override // com.vungle.warren.ui.AdView
    public void setVisibility(boolean z) {
        setVisibility(z ? 0 : 4);
    }
}
