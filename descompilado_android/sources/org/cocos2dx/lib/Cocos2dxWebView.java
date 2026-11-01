package org.cocos2dx.lib;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import java.net.URI;

/* JADX INFO: loaded from: classes3.dex */
public class Cocos2dxWebView extends WebView {
    private static final String TAG = Cocos2dxActivity.APP_TAG + Cocos2dxWebViewHelper.class.getSimpleName();
    private String mJSScheme;
    private int mViewTag;

    public Cocos2dxWebView(Context context) {
        this(context, -1);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public Cocos2dxWebView(Context context, int i) {
        super(context);
        this.mViewTag = i;
        this.mJSScheme = "";
        setFocusable(false);
        setFocusableInTouchMode(false);
        getSettings().setSupportZoom(false);
        getSettings().setJavaScriptEnabled(true);
        try {
            getClass().getMethod("removeJavascriptInterface", String.class).invoke(this, "searchBoxJavaBridge_");
        } catch (Exception unused) {
            Log.d(TAG, "This API level do not support `removeJavascriptInterface`");
        }
        setWebViewClient(new Cocos2dxWebViewClient());
        setWebChromeClient(new WebChromeClient());
    }

    public void setJavascriptInterfaceScheme(String str) {
        if (str == null) {
            str = "";
        }
        this.mJSScheme = str;
    }

    public void setScalesPageToFit(boolean z) {
        getSettings().setSupportZoom(z);
    }

    class Cocos2dxWebViewClient extends WebViewClient {
        Cocos2dxWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                URI uriCreate = URI.create(str);
                if (uriCreate != null && uriCreate.getScheme().equals(Cocos2dxWebView.this.mJSScheme)) {
                    Cocos2dxWebViewHelper._onJsCallback(Cocos2dxWebView.this.mViewTag, str);
                    return true;
                }
            } catch (Exception unused) {
                Log.d(Cocos2dxWebView.TAG, "Failed to create URI from url");
            }
            return Cocos2dxWebViewHelper._shouldStartLoading(Cocos2dxWebView.this.mViewTag, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            Cocos2dxWebViewHelper._didStartLoading(Cocos2dxWebView.this.mViewTag, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            Cocos2dxWebViewHelper._didFinishLoading(Cocos2dxWebView.this.mViewTag, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            Cocos2dxWebViewHelper._didFailLoading(Cocos2dxWebView.this.mViewTag, str2);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (Build.VERSION.SDK_INT >= 23) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                if (webResourceRequest.isForMainFrame()) {
                    Log.d(Cocos2dxWebView.TAG, "onReceivedHttpError: " + webResourceResponse.getStatusCode() + " " + webResourceResponse.getReasonPhrase());
                    Cocos2dxWebViewHelper._didFailLoading(Cocos2dxWebView.this.mViewTag, webView.getUrl());
                }
            }
        }
    }

    public void setWebViewRect(int i, int i2, int i3, int i4) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        layoutParams.width = i3;
        layoutParams.height = i4;
        layoutParams.gravity = 51;
        setLayoutParams(layoutParams);
    }
}
