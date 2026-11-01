package com.ironsource.sdk.controller;

import android.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OpenUrlActivity extends Activity {
    private static final String TAG = "OpenUrlActivity";
    boolean isSecondaryWebview;
    private ProgressBar mProgressBar;
    private String mUrl;
    private IronSourceWebView mWebViewController;
    private RelativeLayout mainLayout;
    private static final int WEB_VIEW_VIEW_ID = SDKUtils.generateViewId();
    private static final int PROGRESS_BAR_VIEW_ID = SDKUtils.generateViewId();
    private WebView webView = null;
    private Handler mUiThreadHandler = new Handler();
    private boolean mIsImmersive = false;
    private final Runnable decorViewSettings = new Runnable() { // from class: com.ironsource.sdk.controller.OpenUrlActivity.2
        @Override // java.lang.Runnable
        public void run() {
            OpenUrlActivity.this.getWindow().getDecorView().setSystemUiVisibility(SDKUtils.getActivityUIFlags(OpenUrlActivity.this.mIsImmersive));
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.i(TAG, "onCreate()");
        try {
            this.mWebViewController = IronSourceAdsPublisherAgent.getInstance(this).getWebViewController();
            hideActivityTitle();
            hideActivtiyStatusBar();
            Bundle extras = getIntent().getExtras();
            this.mUrl = extras.getString(IronSourceWebView.EXTERNAL_URL);
            this.isSecondaryWebview = extras.getBoolean(IronSourceWebView.SECONDARY_WEB_VIEW);
            this.mIsImmersive = getIntent().getBooleanExtra(Constants.ParametersKeys.IMMERSIVE, false);
            if (this.mIsImmersive) {
                getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.ironsource.sdk.controller.OpenUrlActivity.1
                    @Override // android.view.View.OnSystemUiVisibilityChangeListener
                    public void onSystemUiVisibilityChange(int i) {
                        if ((i & 4098) == 0) {
                            OpenUrlActivity.this.mUiThreadHandler.removeCallbacks(OpenUrlActivity.this.decorViewSettings);
                            OpenUrlActivity.this.mUiThreadHandler.postDelayed(OpenUrlActivity.this.decorViewSettings, 500L);
                        }
                    }
                });
                runOnUiThread(this.decorViewSettings);
            }
            this.mainLayout = new RelativeLayout(this);
            setContentView(this.mainLayout, new ViewGroup.LayoutParams(-1, -1));
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        createWebView();
    }

    private void createWebView() {
        if (this.webView == null) {
            this.webView = new WebView(getApplicationContext());
            this.webView.setId(WEB_VIEW_VIEW_ID);
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.setWebViewClient(new Client());
            loadUrl(this.mUrl);
        }
        if (findViewById(WEB_VIEW_VIEW_ID) == null) {
            this.mainLayout.addView(this.webView, new RelativeLayout.LayoutParams(-1, -1));
        }
        createProgressBarForWebView();
        IronSourceWebView ironSourceWebView = this.mWebViewController;
        if (ironSourceWebView != null) {
            ironSourceWebView.viewableChange(true, Constants.ParametersKeys.SECONDARY);
        }
    }

    private void createProgressBarForWebView() {
        if (this.mProgressBar == null) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.mProgressBar = new ProgressBar(new ContextThemeWrapper(this, R.style.Theme.Holo.Light.Dialog));
            } else {
                this.mProgressBar = new ProgressBar(this);
            }
            this.mProgressBar.setId(PROGRESS_BAR_VIEW_ID);
        }
        if (findViewById(PROGRESS_BAR_VIEW_ID) == null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mProgressBar.setLayoutParams(layoutParams);
            this.mProgressBar.setVisibility(4);
            this.mainLayout.addView(this.mProgressBar);
        }
    }

    private void removeWebViewFromLayout() {
        ViewGroup viewGroup;
        IronSourceWebView ironSourceWebView = this.mWebViewController;
        if (ironSourceWebView != null) {
            ironSourceWebView.viewableChange(false, Constants.ParametersKeys.SECONDARY);
            if (this.mainLayout == null || (viewGroup = (ViewGroup) this.webView.getParent()) == null) {
                return;
            }
            if (viewGroup.findViewById(WEB_VIEW_VIEW_ID) != null) {
                viewGroup.removeView(this.webView);
            }
            if (viewGroup.findViewById(PROGRESS_BAR_VIEW_ID) != null) {
                viewGroup.removeView(this.mProgressBar);
            }
        }
    }

    private void destroyWebView() {
        WebView webView = this.webView;
        if (webView != null) {
            webView.destroy();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        removeWebViewFromLayout();
    }

    public void loadUrl(String str) {
        this.webView.stopLoading();
        this.webView.clearHistory();
        try {
            this.webView.loadUrl(str);
        } catch (Throwable th) {
            Logger.e(TAG, "OpenUrlActivity:: loadUrl: " + th.toString());
            new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + th.getStackTrace()[0].getMethodName());
        }
    }

    private class Client extends WebViewClient {
        private Client() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            OpenUrlActivity.this.mProgressBar.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            OpenUrlActivity.this.mProgressBar.setVisibility(4);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            List<String> searchKeys = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
            if (searchKeys != null && !searchKeys.isEmpty()) {
                Iterator<String> it = searchKeys.iterator();
                while (it.hasNext()) {
                    if (str.contains(it.next())) {
                        try {
                            OpenUrlActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                            OpenUrlActivity.this.mWebViewController.interceptedUrlToStore();
                        } catch (Exception e) {
                            StringBuilder sb = new StringBuilder();
                            if (e instanceof ActivityNotFoundException) {
                                sb.append(Constants.ErrorCodes.STORE_ACTIVITY_FAILED_REASON_NO_ACTIVITY);
                            } else {
                                sb.append(Constants.ErrorCodes.STORE_ACTIVITY_FAILED_REASON_UNSPECIFIED);
                            }
                            OpenUrlActivity.this.mWebViewController.failedToStartStoreActivity(sb.toString(), str);
                        }
                        OpenUrlActivity.this.finish();
                        return true;
                    }
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    private void hideActivityTitle() {
        requestWindowFeature(1);
    }

    private void hideActivtiyStatusBar() {
        getWindow().setFlags(1024, 1024);
    }

    private void disableTouch() {
        getWindow().addFlags(16);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        destroyWebView();
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.isSecondaryWebview) {
            this.mWebViewController.engageEnd(Constants.ParametersKeys.SECONDARY_CLOSE);
        }
        super.finish();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mIsImmersive && z) {
            runOnUiThread(this.decorViewSettings);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mIsImmersive && (i == 25 || i == 24)) {
            this.mUiThreadHandler.postDelayed(this.decorViewSettings, 500L);
        }
        return super.onKeyDown(i, keyEvent);
    }
}
